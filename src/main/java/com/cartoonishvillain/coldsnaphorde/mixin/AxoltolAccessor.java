package com.cartoonishvillain.coldsnaphorde.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Axolotl.class)
public interface AxoltolAccessor {
    @Accessor("DATA_VARIANT")
    EntityDataAccessor<Integer> getDATA_VARIANT();
}
