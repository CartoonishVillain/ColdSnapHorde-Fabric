package com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.giveAdvancement;

public class ColdSnapCow extends Cow implements SnowCreature {

    private static final EntityDataAccessor<Integer> HARVESTTIMER = SynchedEntityData.defineId(ColdSnapStabber.class, EntityDataSerializers.INT);

    public ColdSnapCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
    }

    @Override
    public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
        ItemStack itemStack = p_28298_.getItemInHand(p_28299_);
        if(itemStack.getItem().equals(Items.BUCKET) && !this.isBaby() && getHarvestTimer() <= 0){
            p_28298_.playSound(SoundEvents.POWDER_SNOW_FALL, 1.0F, 1.0F);
            ItemStack newBucket = ItemUtils.createFilledResult(itemStack, p_28298_, Items.POWDER_SNOW_BUCKET.getDefaultInstance());
            p_28298_.setItemInHand(p_28299_, newBucket);
            setHarvestTimer(ColdSnapHorde.config.coldSnapSettings.FROSTYHARVESTCOOLDOWN);
            if(p_28298_.getServer() != null) {
                giveAdvancement((ServerPlayer) p_28298_, p_28298_.getServer(), new ResourceLocation(ColdSnapHorde.MOD_ID, "snow_farmer"));
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }else return super.mobInteract(p_28298_, p_28299_);
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_27576_) {
        super.readAdditionalSaveData(p_27576_);
        this.setHarvestTimer(p_27576_.getInt("harvesttimer"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_27587_) {
        super.addAdditionalSaveData(p_27587_);
        p_27587_.putInt("harvesttimer", this.getHarvestTimer());
    }

    public static AttributeSupplier.Builder customAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(HARVESTTIMER, 0);
    }

    public int getHarvestTimer() {
        return getEntityData().get(HARVESTTIMER);
    }

    public void setHarvestTimer(int timer) {
        getEntityData().set(HARVESTTIMER, timer);
    }


    @Override
    public Cow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
        ColdSnapCow coldSnapCow = new ColdSnapCow(Register.COLDSNAPCOW, p_148890_);
        coldSnapCow.setBaby(true);
        coldSnapCow.setHarvestTimer(900);
        return coldSnapCow;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldOverHeat(this.level.getBiome(this.blockPosition()).value().getBaseTemperature(), ColdSnapHorde.config.spawnconfig.HEATPROT)) {
            this.hurt(DamageSource.ON_FIRE, 1.0F);
        }

        if(getHarvestTimer() > 0){
            setHarvestTimer(getHarvestTimer() - 1);
        }
    }

    public boolean shouldOverHeat(float currentTemp, int protectionlevel){
            return switch (protectionlevel) {
                case 0 -> currentTemp > 0.3f;
                case 1 -> currentTemp > 0.9f;
                case 2 -> currentTemp > 1.5f;
                case 3 -> false;
                default -> true;
            };
    }
}
