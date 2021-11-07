package com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;


public class StandardHorde {
    public static class StandardStabber extends ColdSnapStabber{
        public StandardStabber(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
    public static class StandardGunner extends ColdSnapGunner {
        public StandardGunner(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
    public static class StandardSnowballer extends ColdSnapSnowballer {
        public StandardSnowballer(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
    public static class StandardGifter extends ColdSnapGifter {
        public StandardGifter(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
    public static class StandardZapper extends ColdSnapZapper {
        public StandardZapper(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
    public static class StandardBrawler extends ColdSnapBrawler {
        public StandardBrawler(EntityType<? extends Monster> type, Level worldIn) {
            super(type, worldIn);
            setHordeVariant(0);
        }
    }
}