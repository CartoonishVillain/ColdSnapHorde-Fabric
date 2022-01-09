package com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.component.ComponentStarter;
import com.cartoonishvillain.coldsnaphorde.component.PlayerCooldownComponent;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.behaviors.HordeMovementGoal;
import com.cartoonishvillain.immortuoscalyx.component.InfectionHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class GenericHordeMember extends Monster implements SnowCreature {
    private BlockPos target = null;
    private Boolean HordeMember = false;
    public static final EntityDataAccessor<Integer> variant = SynchedEntityData.defineId(GenericHordeMember.class, EntityDataSerializers.INT);

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if((damageSource.equals(DamageSource.ON_FIRE) || damageSource.equals(DamageSource.IN_FIRE) || damageSource.equals(DamageSource.LAVA)) && ColdSnapHorde.config.coldSnapSettings.HORDETAKESMOREFIRE)
            f = f *2;
        return super.hurt(damageSource, f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(GenericHordeMember.class));
        this.goalSelector.addGoal(3, new HordeMovementGoal<>(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ColdSnapGifter.class, 6.0F, 1.0D, 1.2D, this::avoid));
    }

    private boolean avoid(@Nullable LivingEntity entity) {
        if (entity instanceof ColdSnapGifter) {
            int timer = ((ColdSnapGifter) entity).getTimer();
            return timer < 50;
        }
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putInt("variant", this.getHordeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        this.setHordeVariant(p_21450_.getInt("variant"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(variant, -1);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount == 2){
            String check = this.getType().toString();
            int variantcheck = this.getHordeVariant();
            if(check.contains("ncoldsnap") && variantcheck != 1)
            {setHordeVariant(1);}
            else if(check.contains("ecoldsnap") && variantcheck != 2)
            {setHordeVariant(2);}
            else if(check.contains("pcoldsnap") && variantcheck != 3)
            {setHordeVariant(3);}
        }
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void die(DamageSource cause) {
        int random = level.random.nextInt(100);
        int check;
        if(ColdSnapHorde.isInHolidayWindow) check = 67; else check = 75;
        if(random > check && !level.isClientSide() && isHordeMember()){
            ItemEntity itemEntity = new ItemEntity(level, this.getX(), this.getY(), this.getZ(), new ItemStack(Register.PRESENT, 1));
            level.addFreshEntity(itemEntity);
        }
        super.die(cause);
    }

    public boolean isHordeMember(){return HordeMember;}

    public void toggleHordeMember(BlockPos center) {
        this.target = center; HordeMember = true;
        ColdSnapHorde.Horde.SpawnUnit();
    }

    public void updateHordeMember(BlockPos center) {this.target = center;}

    public void cancelHordeMembership(){
        this.target = null; this.HordeMember = false;
    }

    protected GenericHordeMember(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    public void setHordeVariant(int hordeVariant) {this.getEntityData().set(variant, hordeVariant);}

    public int getHordeVariant() {
        int variant = this.getEntityData().get(GenericHordeMember.variant);
        return variant;
    }

    @Override
    public boolean isSensitiveToWater() {
        int hordeVariant = this.getEntityData().get(variant);
        return hordeVariant == 1|| hordeVariant == 2;
    }

    @Override
    public boolean fireImmune() {return this.getEntityData().get(variant) == 1;}


    @Override
    protected void actuallyHurt(DamageSource p_21240_, float p_21241_) {
        super.actuallyHurt(p_21240_, p_21241_);
        if(this.getEntityData().get(variant) == 2){
            int chance = random.nextInt(10);
            if(chance <= 2) this.randomTeleport(this.getX() + random.nextInt(5+5)-5,this.getY() + random.nextInt(5+5)-5,this.getZ() + random.nextInt(5+5)-5, true);

        }
    }




    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    public BlockPos getLoc() {return target;}

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide()) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY());
            int k = Mth.floor(this.getZ());
            if (shouldOverHeat(this.level.getBiome(this.blockPosition()).getBaseTemperature(), ColdSnapHorde.config.spawnconfig.HEATPROT)) {
                this.hurt(DamageSource.ON_FIRE, 1.0F);
            }

            if (!level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                return;
            }

            BlockState blockstate = null;
            if (this.getEntityData().get(variant) == 0 || this.getEntityData().get(variant) == 3) {
                blockstate = Blocks.SNOW.defaultBlockState();
            }
            if (this.getEntityData().get(variant) == 1) {
                blockstate = Register.SLUSH.defaultBlockState();
            }


            if (blockstate == Blocks.SNOW.defaultBlockState()) {
                for (int l = 0; l < 4; ++l) {
                    i = Mth.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                    j = Mth.floor(this.getY());
                    k = Mth.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                    BlockPos blockpos = new BlockPos(i, j, k);
                    if (this.level.isEmptyBlock(blockpos) && !shouldOverHeat(this.level.getBiome(this.blockPosition()).getBaseTemperature(), ColdSnapHorde.config.spawnconfig.SNOWTRAIL) && blockstate.canSurvive(this.level, blockpos)) {
                        this.level.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            } else if (blockstate == Register.SLUSH.defaultBlockState()) {
                for (int l = 0; l < 4; ++l) {
                    i = Mth.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                    j = Mth.floor(this.getY());
                    k = Mth.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                    BlockPos blockpos = new BlockPos(i, j, k);
                    if (this.level.getBlockState(blockpos).equals(Blocks.AIR.defaultBlockState()) && (blockstate.canSurvive(this.level, blockpos)))
                        this.level.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }


    public boolean shouldOverHeat(float currentTemp, int protectionlevel){
        if(this.getEntityData().get(variant) == 0) {
            return switch (protectionlevel) {
                case 0 -> currentTemp > 0.3f;
                case 1 -> currentTemp > 0.9f;
                case 2 -> currentTemp > 1.5f;
                case 3 -> false;
                default -> true;
            };
        }else return false;
    }

    public static void Infection(LivingEntity entity){
        if(ColdSnapHorde.isCalyxLoaded){
            InfectionHandler.infectEntity(entity, 15, 1);
        }else{
            if (entity instanceof Player){
                PlayerCooldownComponent h = ComponentStarter.PLAYERCOMPONENT.get(entity);
                if(h.getCooldownTicks() == 0) {
                    int chance = entity.getRandom().nextInt(10);
                    switch (chance) {
                        default -> {
                        }
                        case 3 -> {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 20, 0));
                            h.setCooldownTicks(20*60);
                        }
                        case 4 -> {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 20, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 20, 0));
                            h.setCooldownTicks(20*60);
                        }
                        case 5 -> {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 40, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 40, 0));
                            h.setCooldownTicks(20*60);
                        }
                        case 6 -> {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 30, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 30, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 10, 0));
                            h.setCooldownTicks(20*60);
                        }
                        case 7 -> {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 25, 1));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 25, 1));
                            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 20, 0));
                            h.setCooldownTicks(20*60);
                        }
                    }
                }

            }

        }
    }
}
