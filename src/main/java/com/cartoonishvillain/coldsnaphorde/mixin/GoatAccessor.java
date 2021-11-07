package com.cartoonishvillain.coldsnaphorde.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.goat.Goat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Goat.class)
public interface GoatAccessor {
    @Accessor("DATA_IS_SCREAMING_GOAT")
    EntityDataAccessor<Boolean> getDATA_IS_SCREAMING_GOAT();
}
