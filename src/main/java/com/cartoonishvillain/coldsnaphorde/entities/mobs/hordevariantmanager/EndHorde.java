package com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EndHorde {
    public static class EndStabber extends ColdSnapStabber {
        public EndStabber(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
    public static class EndGunner extends ColdSnapGunner {
        public EndGunner(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
    public static class EndSnowballer extends ColdSnapSnowballer {
        public EndSnowballer(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
    public static class EndGifter extends ColdSnapGifter {
        public EndGifter(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
    public static class EndZapper extends ColdSnapZapper {
        public EndZapper(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
    public static class EndBrawler extends ColdSnapBrawler {
        public EndBrawler(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(2);
        }
    }
}