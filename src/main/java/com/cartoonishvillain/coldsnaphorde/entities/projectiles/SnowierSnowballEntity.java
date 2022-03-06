package com.cartoonishvillain.coldsnaphorde.entities.projectiles;

import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SnowierSnowballEntity extends ThrowableItemProjectile {

    public SnowierSnowballEntity(EntityType<? extends ThrowableItemProjectile> type, Level worldIn, LivingEntity livingEntityIn) {
        super(type, livingEntityIn, worldIn);
    }

    public SnowierSnowballEntity(EntityType<SnowierSnowballEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public ItemStack getItem() { return new ItemStack(Register.SNOWIERSNOWBALL); }

    @Override
    protected Item getDefaultItem() {
        {return Register.SNOWIERSNOWBALL;}
    }

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        int i = entity instanceof Blaze ? 5 : 0;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
        int chance = random.nextInt(20);
        if (chance <= 2 && entity instanceof LivingEntity && !this.level.isClientSide()){((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 7*20, 0));}
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        BlockState blockstate = Blocks.SNOW_BLOCK.defaultBlockState();
        BlockPos blockpos = new BlockPos(result.getLocation());
        if (this.level.isEmptyBlock(blockpos) && this.level.getBiome(blockpos).value().getBaseTemperature() < 0.8F && blockstate.canSurvive(this.level, blockpos) && !level.isClientSide()) {
            this.level.setBlockAndUpdate(blockpos, blockstate);
        } else if(this.level.getBlockState(blockpos) == Blocks.LAVA.defaultBlockState() && !level.isClientSide()){
            this.level.setBlockAndUpdate(blockpos, Blocks.OBSIDIAN.defaultBlockState());
        }else if(this.level.getBlockState(blockpos) == Blocks.WATER.defaultBlockState() && !level.isClientSide()){
            this.level.setBlockAndUpdate(blockpos, Blocks.ICE.defaultBlockState());
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos position = this.blockPosition();
        if(!level.isClientSide()){
            if(level.getBlockState(position) == Blocks.WATER.defaultBlockState()){
                level.setBlockAndUpdate(position, Blocks.ICE.defaultBlockState());
                this.remove(RemovalReason.DISCARDED);
            }else if(level.getBlockState(position) == Blocks.LAVA.defaultBlockState()){
                level.setBlockAndUpdate(position, Blocks.OBSIDIAN.defaultBlockState());
                this.remove(RemovalReason.DISCARDED);
            }

        }
    }
}
