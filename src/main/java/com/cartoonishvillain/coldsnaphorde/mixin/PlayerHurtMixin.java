package com.cartoonishvillain.coldsnaphorde.mixin;

import com.cartoonishvillain.coldsnaphorde.component.ComponentStarter;
import com.cartoonishvillain.coldsnaphorde.component.PlayerCooldownComponent;
import com.cartoonishvillain.coldsnaphorde.events.MixinEvents;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class PlayerHurtMixin {

        @Inject(at = @At("TAIL"), method = "hurt")
        private void coldSnapHurt(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir){
            MixinEvents.PlayerHurt((ServerPlayer) (Object) this, damageSource);
        }
}
