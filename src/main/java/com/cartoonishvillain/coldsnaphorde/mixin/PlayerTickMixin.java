package com.cartoonishvillain.coldsnaphorde.mixin;

import com.cartoonishvillain.coldsnaphorde.component.ComponentStarter;
import com.cartoonishvillain.coldsnaphorde.component.PlayerCooldownComponent;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerTickMixin {

        @Inject(at = @At("TAIL"), method = "tick")
        private void coldSnapTick(CallbackInfo ci){
            PlayerCooldownComponent h = ComponentStarter.PLAYERCOMPONENT.get((ServerPlayer) (Object) this);
            h.tickCooldown();
        }
}
