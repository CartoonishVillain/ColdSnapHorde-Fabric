package com.cartoonishvillain.coldsnaphorde.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class WorldCooldownComponent implements ComponentV3 {
    protected int ticks = 0;

    public int getCooldownTicks() {return ticks;}


    public void setCooldownTicks(int ticks) {this.ticks = ticks;}


    public void addCooldownTicks(int ticks) {this.ticks += ticks;}

    @Override
    public void readFromNbt(CompoundTag nbt) {
        ticks = nbt.getInt("cooldown");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("cooldown", ticks);
    }
}
