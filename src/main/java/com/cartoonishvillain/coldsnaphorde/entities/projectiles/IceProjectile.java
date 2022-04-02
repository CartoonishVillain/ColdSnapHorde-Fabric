package com.cartoonishvillain.coldsnaphorde.entities.projectiles;

import com.cartoonishvillain.coldsnaphorde.items.Tier;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class IceProjectile extends ThrowableItemProjectile {
    Tier tier;

    private static final EntityDataAccessor<Integer> TIERORDINAL = SynchedEntityData.defineId(HealingSnowballEntity.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIERORDINAL, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putInt("tier", tier.ordinal());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        switch (p_21450_.getInt("tier")) {
            default -> {
                tier = Tier.ONE;
            }
            case 1 ->  {
                tier = Tier.TWO;
            }
            case 2 -> {
                tier = Tier.THREE;
            }
        }
    }

    public IceProjectile(EntityType<? extends ThrowableItemProjectile> type, Level worldIn, LivingEntity entity, Tier tier) {
        super(type, entity, worldIn);
        this.tier = tier;
        setTierOrdinal(tier.ordinal());
    }

    public IceProjectile(EntityType<IceProjectile> gunnerProjectileEntityEntityType, Level world) {
        super(gunnerProjectileEntityEntityType, world);
        tier = Tier.ONE;
        setTierOrdinal(tier.ordinal());
    }

    public void setTierOrdinal(int tierOrdinal) {
        this.entityData.set(TIERORDINAL, tierOrdinal);
    }

    @Override
    protected Item getDefaultItem() {
        int ordinal = this.entityData.get(TIERORDINAL);
        switch (ordinal) {
            default -> {
                return Items.ICE;
            }
            case 1 ->  {
                return Items.PACKED_ICE;
            }
            case 2 -> {
                return Items.BLUE_ICE;
            }
        }
    }

    @Override
    public ItemStack getItem() {
        int ordinal = this.entityData.get(TIERORDINAL);
        switch (ordinal) {
            default -> {
                return new ItemStack(Items.ICE);
            }
            case 1 ->  {
                return new ItemStack(Items.PACKED_ICE);
            }
            case 2 -> {
                return new ItemStack(Items.BLUE_ICE);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        BlockState blockstate;
        switch (tier) {
            default -> {
                blockstate = Blocks.ICE.defaultBlockState();
            }
            case TWO -> {
                blockstate = Blocks.PACKED_ICE.defaultBlockState();
            }
            case THREE -> {
                blockstate = Blocks.BLUE_ICE.defaultBlockState();
            }
        }
        BlockPos blockpos = new BlockPos(result.getLocation());
        if (this.level.isEmptyBlock(blockpos) && blockstate.canSurvive(this.level, blockpos) && !level.isClientSide()) {
            this.level.setBlockAndUpdate(blockpos, blockstate);
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
