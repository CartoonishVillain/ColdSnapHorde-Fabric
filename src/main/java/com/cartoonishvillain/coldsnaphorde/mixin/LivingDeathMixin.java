package com.cartoonishvillain.coldsnaphorde.mixin;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingDeathMixin {
    @Inject(at = @At("TAIL"), method = "die")
    private void die(DamageSource damageSource, CallbackInfo ci){
        LivingEntity dyingEntity = ((LivingEntity) (Object) this);
        if(!(dyingEntity instanceof Player) && !dyingEntity.level.isClientSide && ColdSnapHorde.isInHolidayWindow) {
            int random = dyingEntity.level.random.nextInt(15);
            if(random == 1) {
                switch (ColdSnapHorde.hordeDataManager.getHighestLevelBeaten()) {
                    default -> {
                        ItemEntity itemEntity = new ItemEntity(dyingEntity.level, dyingEntity.getX(), dyingEntity.getY(), dyingEntity.getZ(), new ItemStack(Register.SMALLPRESENT, 1));
                        dyingEntity.level.addFreshEntity(itemEntity);
                    }
                    case 2 -> {
                        ItemEntity itemEntity = new ItemEntity(dyingEntity.level, dyingEntity.getX(), dyingEntity.getY(), dyingEntity.getZ(), new ItemStack(Register.PRESENT, 1));
                        dyingEntity.level.addFreshEntity(itemEntity);
                    }
                    case 3 -> {
                        ItemEntity itemEntity = new ItemEntity(dyingEntity.level, dyingEntity.getX(), dyingEntity.getY(), dyingEntity.getZ(), new ItemStack(Register.LARGEPRESENT, 1));
                        dyingEntity.level.addFreshEntity(itemEntity);
                    }
                }
            }
        }

    }
}
