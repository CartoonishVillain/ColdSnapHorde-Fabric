package com.cartoonishvillain.coldsnaphorde.entities.projectiles;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import static com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember.Infection;

public class ThrownChorusEntity extends ThrowableItemProjectile {



    public ThrownChorusEntity(EntityType<? extends ThrowableItemProjectile> type, Level worldIn, LivingEntity entity) {
        super(type, entity, worldIn);}

    public ThrownChorusEntity(EntityType<ThrownChorusEntity> gunnerProjectileEntityEntityType, Level world) {
        super(gunnerProjectileEntityEntityType, world);
    }


    private ParticleOptions makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    protected Item getDefaultItem() {return Items.CHORUS_FRUIT;}

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.CHORUS_FRUIT);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        int i = entity instanceof Blaze ? 3 : 1;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
        int chance = random.nextInt(20);
        if(this.getOwner() instanceof GenericHordeMember && entity instanceof LivingEntity && !this.level.isClientSide()){
            GenericHordeMember member = (GenericHordeMember) this.getOwner();
            switch(member.getHordeVariant()){
                case 0 -> {
                    if(chance <= 2)  {((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5*20, 0));
                    if(chance == 1) ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5*20, 0));}
                }
                case 1 -> {
                    int chance2 = random.nextInt(100);
                    if (chance2 <= 75) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20*5, 1));
                    }
                }
                case 2 -> {
                    int chance2 = random.nextInt(20);
                    if(chance2 <= 2) ((LivingEntity) entity).randomTeleport(entity.getX() + random.nextInt(5+5)-5,entity.getY() + random.nextInt(5+5)-5,entity.getZ() + random.nextInt(5+5)-5, true);
                    else if(chance2 <=4) member.randomTeleport(this.getX() + random.nextInt(5+5)-5,this.getY() + random.nextInt(5+5)-5,this.getZ() + random.nextInt(5+5)-5, true);
                }
                case 3 -> {
                    Infection((LivingEntity) entity);
                }
            }
        }
        else if (chance <= 2 && entity instanceof LivingEntity && !this.level.isClientSide()){((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5*20, 0));
            if (chance == 1) ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5*20, 0));}
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        BlockState blockstate = Blocks.SNOW_BLOCK.defaultBlockState();
        int snowchance = random.nextInt(20);
        BlockPos blockpos = new BlockPos(result.getLocation());
        if (this.level.isEmptyBlock(blockpos) && this.level.getBiome(blockpos).getBaseTemperature() < 0.8F && blockstate.canSurvive(this.level, blockpos) && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) && snowchance == 1 && !level.isClientSide()) {
            this.level.setBlockAndUpdate(blockpos, blockstate);
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
