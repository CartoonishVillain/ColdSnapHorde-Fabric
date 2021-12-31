package com.cartoonishvillain.coldsnaphorde.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class PlayerCooldownComponent implements ComponentV3 {
    protected int ticks = 0;
    private final Object provider;

    public PlayerCooldownComponent(Object provider){
        this.provider = provider;
    }




    public int getCooldownTicks() {return ticks;}


    public void setCooldownTicks(int ticks) {this.ticks = ticks;}


    public void tickCooldown() {
        if(!(ticks <= 0)) ticks--;
        if(ticks < 0) ticks = 0;
    }


    @Override
    public void readFromNbt(CompoundTag nbt) {
        ticks = nbt.getInt("cooldown");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("cooldown", ticks);
    }
}
