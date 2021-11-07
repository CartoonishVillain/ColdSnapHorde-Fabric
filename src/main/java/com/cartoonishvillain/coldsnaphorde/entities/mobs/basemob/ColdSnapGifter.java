package com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob;

import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.behaviors.GifterSurprise;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ColdSnapGifter extends GenericHordeMember {
    public ColdSnapGifter(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        this.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10000*20, 5, false, false));
    }

    int timer = 50;
    boolean exploding = false;

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.targetSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.75D, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Villager.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Blaze.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, SnowGolem.class, 10, true, false, this::shouldAttack));
    }

    public static AttributeSupplier.Builder customAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 0D);
    }

    public boolean shouldAttack(@Nullable LivingEntity entity){
        return entity != null && (!entity.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(Register.TOPHAT) || this.isHordeMember());
    }

    public int getTimer() {return timer;}

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {}

    public void aiStep() {
        super.aiStep();
        LivingEntity livingEntity = this.getTarget();
        if (livingEntity != null) {
            double distance = this.distanceToSqr(livingEntity);
            if (distance < 4.5D && !level.isClientSide()) {
                if(!exploding){this.playSound(SoundEvents.TNT_PRIMED, 1F, 1F); this.playSound(Register.GIFTERATTACK, 1F, 1F);}
                exploding = true;
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 10, false, false));
            }

            if (exploding && distance < 36D) {
                timer -= 1;
                if (!level.isClientSide() && timer == 0) {
                    this.dead = true;
                    GifterSurprise gifterSurprise = new GifterSurprise(this.level, this, this.getX(), this.getY(), this.getZ(), 5);
                    gifterSurprise.StageDetonation();
                    gifterSurprise.DetonateBlockDamage();
                    gifterSurprise.DetonateLivingHarm();
                    this.playSound(SoundEvents.GENERIC_EXPLODE, 1f, 1.5f);
                    this.remove(RemovalReason.KILLED);
                }
            } else if (exploding && distance > 36D) {
                exploding = false;
                timer = 50;
            }
        }
    }
}
