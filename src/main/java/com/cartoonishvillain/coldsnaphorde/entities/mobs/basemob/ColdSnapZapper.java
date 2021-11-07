package com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ColdSnapZapper extends GenericHordeMember {
    public ColdSnapZapper(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    LivingEntity ZapTarget;
    int timer = 60;
    float transponderprogress = 1;

    public LivingEntity getZapTarget() {
        return ZapTarget;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.targetSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PathfinderMob.class, 6.0F, 0.75D, 0.75D, this::avoid));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D, this::avoid));
        this.goalSelector.addGoal(2, new CustomMeleeAttackGoal(this, 0.75D, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Villager.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Blaze.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SnowGolem.class, 10, true, false, this::shouldAttack));

    }

    private boolean avoid(@Nullable LivingEntity entity){
        if (entity == null || entity != ZapTarget) {
            return false;
        } else return true;

    }

    public static AttributeSupplier.Builder customAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 1D);
    }

    public boolean shouldAttack(@Nullable LivingEntity entity){
        return entity != null && (!entity.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(Register.TOPHAT) || this.isHordeMember()) && entity != ZapTarget;
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        int chance = random.nextInt(100);
        if(chance >= ColdSnapHorde.config.coldSnapSettings.STICKTRANSPONDER && entityIn instanceof LivingEntity && transponderprogress == 1){
            ZapTarget = (LivingEntity) entityIn;
            transponderprogress = 0;
        }
        switch(this.getHordeVariant()){
            case 0 -> {
                int chance2 = random.nextInt(100);
            }
            case 1 -> {
                int chance2 = random.nextInt(100);
                if (chance2 <= 75) {
                    ((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20*5, 1));
                }

            }
            case 2 -> {
                int chance2 = random.nextInt(20);
                if(chance2 <= 2) ((LivingEntity) entityIn).randomTeleport(entityIn.getX() + random.nextInt(5+5)-5,entityIn.getY() + random.nextInt(5+5)-5,entityIn.getZ() + random.nextInt(5+5)-5, true);
                else if(chance2 <=4) this.randomTeleport(this.getX() + random.nextInt(5+5)-5,this.getY() + random.nextInt(5+5)-5,this.getZ() + random.nextInt(5+5)-5, true);
            }
            case 3 -> {
                Infection((LivingEntity) entityIn);
            }
        }
        return super.doHurtTarget(entityIn);
    }

    public void aiStep() {
        super.aiStep();

        if(ZapTarget != null && !this.level.isClientSide()){
            timer -= 1;
            if (timer == 0){
                EntityType.LIGHTNING_BOLT.spawn((ServerLevel) ZapTarget.getCommandSenderWorld(), new ItemStack(Items.AIR), null, ZapTarget.blockPosition(), MobSpawnType.TRIGGERED, true, false);
                ZapTarget = null;
                timer = 60;
            }
        }
        if(transponderprogress < 1){
            transponderprogress += 0.0025;
            if (transponderprogress > 1) transponderprogress = 1;
        }
    }


}

class CustomMeleeAttackGoal extends MeleeAttackGoal {
    public CustomMeleeAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean canContinueToUse() {
        if(this.mob instanceof ColdSnapZapper && this.mob.getTarget() == ((ColdSnapZapper) this.mob).getZapTarget()){return false;}
        return super.canContinueToUse();
    }
}

