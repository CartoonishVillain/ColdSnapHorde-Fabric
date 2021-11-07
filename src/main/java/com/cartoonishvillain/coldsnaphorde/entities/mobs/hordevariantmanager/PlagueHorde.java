package com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class PlagueHorde {
    public static class PlagueStabber extends ColdSnapStabber {
        public PlagueStabber(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
    public static class PlagueGunner extends ColdSnapGunner {
        public PlagueGunner(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
    public static class PlagueSnowballer extends ColdSnapSnowballer {
        public PlagueSnowballer(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
    public static class PlagueGifter extends ColdSnapGifter {
        public PlagueGifter(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
    public static class PlagueZapper extends ColdSnapZapper {
        public PlagueZapper(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
    public static class PlagueBrawler extends ColdSnapBrawler {
        public PlagueBrawler(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(3);
        }
    }
}