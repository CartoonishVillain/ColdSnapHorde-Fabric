package com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob;

import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.TOPHATS;

public class ColdSnapBrawler extends GenericHordeMember{
    public ColdSnapBrawler(EntityType<? extends Monster> type, Level worldIn) { super(type, worldIn);}

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.9D, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SnowGolem.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Villager.class, 10, true, false, this::shouldAttack));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, IronGolem.class, 10, true, false, this::shouldAttack));
    }

    public static AttributeSupplier.Builder customAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.45D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.ATTACK_KNOCKBACK, 3d);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        switch(this.getHordeVariant()){
            case 0 -> {

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

    public boolean shouldAttack(@Nullable LivingEntity entity){
        return entity != null && (!TOPHATS.contains(entity.getItemBySlot(EquipmentSlot.HEAD).getItem()) || getHordeMember());
    }

}
