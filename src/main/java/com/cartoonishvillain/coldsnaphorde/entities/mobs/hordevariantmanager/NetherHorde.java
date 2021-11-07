package com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class NetherHorde {
    public static class NetherStabber extends ColdSnapStabber {
        public NetherStabber(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
    public static class NetherGunner extends ColdSnapGunner {
        public NetherGunner(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
    public static class NetherSnowballer extends ColdSnapSnowballer {
        public NetherSnowballer(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
    public static class NetherGifter extends ColdSnapGifter {
        public NetherGifter(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
    public static class NetherZapper extends ColdSnapZapper {
        public NetherZapper(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
    public static class NetherBrawler extends ColdSnapBrawler {
        public NetherBrawler(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(1);
        }
    }
}