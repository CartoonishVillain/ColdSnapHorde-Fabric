package com.cartoonishvillain.coldsnaphorde.mixin;

import com.cartoonishvillain.coldsnaphorde.events.MixinEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerAttackMixin {

        @Inject(at = @At("HEAD"), method = "attack")
        private void coldSnapHurt(Entity entity, CallbackInfo ci){
            MixinEvents.PlayerAttack((Player) (Object) this, entity);
        }
}
