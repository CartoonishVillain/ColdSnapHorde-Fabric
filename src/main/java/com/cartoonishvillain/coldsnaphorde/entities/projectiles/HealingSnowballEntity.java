package com.cartoonishvillain.coldsnaphorde.entities.projectiles;

import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.items.Tier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class HealingSnowballEntity extends ThrowableItemProjectile {
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

    @Override
    protected Item getDefaultItem() {
        int ordinal = this.entityData.get(TIERORDINAL);
        switch (ordinal) {
            default -> {
                return Register.LESSERHEALINGBALL;
            }
            case 1 ->  {
                return Register.HEALINGBALL;
            }
            case 2 -> {
                return Register.GREATERHEALINGBALL;
            }
        }
    }

    public HealingSnowballEntity(EntityType<? extends ThrowableItemProjectile> type, Level worldIn, LivingEntity livingEntityIn, Tier tier) {
        super(type, livingEntityIn, worldIn);
        this.tier = tier;
        setTierOrdinal(tier.ordinal());
    }

    public HealingSnowballEntity(EntityType<HealingSnowballEntity> healingSnowballEntityEntityType, Level level) {
        super(healingSnowballEntityEntityType, level);
        this.tier = Tier.ONE;
        setTierOrdinal(tier.ordinal());
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        if (!p_37259_.getEntity().level.isClientSide && p_37259_.getEntity() instanceof LivingEntity entity) {
            switch (tier) {
                default -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
                }
                case TWO -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
                }
                case THREE -> {
                    entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 2));
                }
            }
        }
    }

    public void setTierOrdinal(int tierOrdinal) {
        this.entityData.set(TIERORDINAL, tierOrdinal);
    }


    @Override
    protected void onHit(HitResult p_37260_) {
        super.onHit(p_37260_);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public ItemStack getItem() {
        int ordinal = this.entityData.get(TIERORDINAL);
        switch (ordinal) {
            default -> {
                return new ItemStack(Register.LESSERHEALINGBALL);
            }
            case 1 ->  {
                return new ItemStack(Register.HEALINGBALL);
            }
            case 2 -> {
                return new ItemStack(Register.GREATERHEALINGBALL);
            }
        }
    }
}
