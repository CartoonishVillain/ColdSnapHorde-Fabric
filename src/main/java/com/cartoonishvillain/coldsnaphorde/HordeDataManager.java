package com.cartoonishvillain.coldsnaphorde;

import com.cartoonishvillain.coldsnaphorde.component.WorldCooldownComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import static com.cartoonishvillain.coldsnaphorde.component.ComponentStarter.WORLDCOMPONENT;

public class HordeDataManager {
    int highestLevelBeaten = 0;
    int cooldownTicks = 0;
    int currentHordeLevel = 0;

    private static HordeDataManager instance = null;

    public static HordeDataManager getInstance() {
        if (instance == null) instance = new HordeDataManager();
        return instance;
    }

    private HordeDataManager() {

    }

    public int getHighestLevelBeaten() {
        return highestLevelBeaten;
    }

    public void setupHighestLevelBeaten(MinecraftServer server) {
        for(ServerLevel serverWorld : server.getAllLevels()){
            if(serverWorld.dimension().location().getPath().contains("overworld")) {
                WorldCooldownComponent h = WORLDCOMPONENT.get(serverWorld);
                highestLevelBeaten = h.getLevelBeaten();
            }
        }
    }

    public void updateHighestLevelBeaten(MinecraftServer server, int level) {
        for(ServerLevel serverWorld : server.getAllLevels()){
            if(serverWorld.dimension().location().getPath().contains("overworld")) {
                WorldCooldownComponent h = WORLDCOMPONENT.get(serverWorld);
                if (h.getLevelBeaten() < level) {
                    h.setLevelBeaten(level);
                    highestLevelBeaten = level;
                }
            }
        }
    }

    public void setHighestLevelBeaten(MinecraftServer server, int level) {
        for(ServerLevel serverWorld : server.getAllLevels()){
            if(serverWorld.dimension().location().getPath().contains("overworld")) {
                WorldCooldownComponent h = WORLDCOMPONENT.get(serverWorld);
                h.setLevelBeaten(level);
                highestLevelBeaten = level;
            }
        }
    }

    public void setCooldownTicks(int ticks) {
        cooldownTicks = ticks;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public void tickCooldown() {
        if(cooldownTicks > 0) cooldownTicks--;
    }

    public void setCurrentHordeLevel(int level) {
        currentHordeLevel = level;
    }

    public int getCurrentHordeLevel() {
        return currentHordeLevel;
    }
}
