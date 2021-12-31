package com.cartoonishvillain.coldsnaphorde.entities;


import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spawns {

    public static void initSpawns() {
        final String BiomeList = ColdSnapHorde.config.spawnconfig.BiomeExclusion;
        String[] biomeExclusion = BiomeList.split(",");
        int exclusionLength = biomeExclusion.length;
        ResourceLocation[] finalBiomeExclusion = new ResourceLocation[exclusionLength];
        int counter = 0;
        for (String i : biomeExclusion) {
            ResourceLocation newResource = new ResourceLocation(i);
            finalBiomeExclusion[counter] = newResource;
            counter++;
        }

        MobSpawnSettings.SpawnerData spawners = new MobSpawnSettings.SpawnerData(Register.COLDSNAPSTABBER, ColdSnapHorde.config.spawnconfig.STABBER, 1, 1);
        MobSpawnSettings.SpawnerData spawners1 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPGUNNER, ColdSnapHorde.config.spawnconfig.GUNNER, 1, 1);
        MobSpawnSettings.SpawnerData spawners2 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPSNOWBALLER, ColdSnapHorde.config.spawnconfig.SNOWBALLER, 1, 1);
        MobSpawnSettings.SpawnerData spawners3 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPGIFTER, ColdSnapHorde.config.spawnconfig.GIFTER, 1, 1);
        MobSpawnSettings.SpawnerData spawners4 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPZAPPER, ColdSnapHorde.config.spawnconfig.ZAPPER, 1, 1);
        MobSpawnSettings.SpawnerData spawners5 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPBRAWLER, ColdSnapHorde.config.spawnconfig.BRAWLER, 1, 1);
        MobSpawnSettings.SpawnerData spawners6 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPCOW, ColdSnapHorde.config.spawnconfig.SNOWCOW, 4, 4);


        MobSpawnSettings.SpawnerData nspawners = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPSTABBER, ColdSnapHorde.config.spawnconfig.DSTABBER, 1, 1);
        MobSpawnSettings.SpawnerData nspawners1 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPGUNNER, ColdSnapHorde.config.spawnconfig.DGUNNER, 1, 1);
        MobSpawnSettings.SpawnerData nspawners2 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPSNOWBALLER, ColdSnapHorde.config.spawnconfig.DSNOWBALLER, 1, 1);
        MobSpawnSettings.SpawnerData nspawners3 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPGIFTER, ColdSnapHorde.config.spawnconfig.DGIFTER, 1, 1);
        MobSpawnSettings.SpawnerData nspawners4 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPZAPPER, ColdSnapHorde.config.spawnconfig.DZAPPER, 1, 1);
        MobSpawnSettings.SpawnerData nspawners5 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPBRAWLER, ColdSnapHorde.config.spawnconfig.DBRAWLER, 1, 1);

        MobSpawnSettings.SpawnerData espawners = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPSTABBER, ColdSnapHorde.config.spawnconfig.DSTABBER, 1, 1);
        MobSpawnSettings.SpawnerData espawners1 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPGUNNER, ColdSnapHorde.config.spawnconfig.DGUNNER, 1, 1);
        MobSpawnSettings.SpawnerData espawners2 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPSNOWBALLER, ColdSnapHorde.config.spawnconfig.DSNOWBALLER, 1, 1);
        MobSpawnSettings.SpawnerData espawners3 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPGIFTER, ColdSnapHorde.config.spawnconfig.DGIFTER, 1, 1);
        MobSpawnSettings.SpawnerData espawners4 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPZAPPER, ColdSnapHorde.config.spawnconfig.DZAPPER, 1, 1);
        MobSpawnSettings.SpawnerData espawners5 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPBRAWLER, ColdSnapHorde.config.spawnconfig.DBRAWLER, 1, 1);

        MobSpawnSettings.SpawnerData pspawners = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPSTABBER, ColdSnapHorde.config.spawnconfig.STABBER, 1, 1);
        MobSpawnSettings.SpawnerData pspawners1 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPGUNNER, ColdSnapHorde.config.spawnconfig.GUNNER, 1, 1);
        MobSpawnSettings.SpawnerData pspawners2 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPSNOWBALLER, ColdSnapHorde.config.spawnconfig.SNOWBALLER, 1, 1);
        MobSpawnSettings.SpawnerData pspawners3 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPGIFTER, ColdSnapHorde.config.spawnconfig.GIFTER, 1, 1);
        MobSpawnSettings.SpawnerData pspawners4 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPZAPPER, ColdSnapHorde.config.spawnconfig.ZAPPER, 1, 1);
        MobSpawnSettings.SpawnerData pspawners5 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPBRAWLER, ColdSnapHorde.config.spawnconfig.BRAWLER, 1, 1);

        for (Biome event : BuiltinRegistries.BIOME) {
            if (BiomeExclusion(finalBiomeExclusion, event.toString()) && !event.toString().contains("swamp") && !(event.getBiomeCategory() == Biome.BiomeCategory.NETHER || event.getBiomeCategory() == Biome.BiomeCategory.THEEND || event.getBiomeCategory() == Biome.BiomeCategory.MUSHROOM)) {
                if (ColdSnapHorde.config.spawnconfig.SPAWNTEMPS == 0 && event.getBaseTemperature() < 0.3f) {
                    List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                    ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                    newSpawnerList.add(spawners);
                    newSpawnerList.add(spawners1);
                    newSpawnerList.add(spawners2);
                    newSpawnerList.add(spawners3);
                    newSpawnerList.add(spawners4);
                    newSpawnerList.add(spawners5);
                    newSpawnerList.add(spawners6);
                    HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                    newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                    event.getMobSettings().spawners = newSpawnerMap;
                } else if (ColdSnapHorde.config.spawnconfig.SPAWNTEMPS == 1 && event.getBaseTemperature() < 0.9f) {
                    List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                    ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                    newSpawnerList.add(spawners);
                    newSpawnerList.add(spawners1);
                    newSpawnerList.add(spawners2);
                    newSpawnerList.add(spawners3);
                    newSpawnerList.add(spawners4);
                    newSpawnerList.add(spawners5);
                    newSpawnerList.add(spawners6);
                    HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                    newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                    event.getMobSettings().spawners = newSpawnerMap;
                } else if (ColdSnapHorde.config.spawnconfig.SPAWNTEMPS == 2 && event.getBaseTemperature() < 1.5f) {
                    List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                    ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                    newSpawnerList.add(spawners);
                    newSpawnerList.add(spawners1);
                    newSpawnerList.add(spawners2);
                    newSpawnerList.add(spawners3);
                    newSpawnerList.add(spawners4);
                    newSpawnerList.add(spawners5);
                    newSpawnerList.add(spawners6);
                    HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                    newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                    event.getMobSettings().spawners = newSpawnerMap;

                } else if (ColdSnapHorde.config.spawnconfig.SPAWNTEMPS == 3) {
                    List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                    ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                    newSpawnerList.add(spawners);
                    newSpawnerList.add(spawners1);
                    newSpawnerList.add(spawners2);
                    newSpawnerList.add(spawners3);
                    newSpawnerList.add(spawners4);
                    newSpawnerList.add(spawners5);
                    newSpawnerList.add(spawners6);
                    HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                    newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                    event.getMobSettings().spawners = newSpawnerMap;
                }
            } else if (event.toString().contains("swamp")) {
                List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                newSpawnerList.add(pspawners);
                newSpawnerList.add(pspawners1);
                newSpawnerList.add(pspawners2);
                newSpawnerList.add(pspawners3);
                newSpawnerList.add(pspawners4);
                newSpawnerList.add(pspawners5);
                HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                event.getMobSettings().spawners = newSpawnerMap;
            } else if (event.getBiomeCategory() == Biome.BiomeCategory.NETHER) {
                List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                newSpawnerList.add(nspawners);
                newSpawnerList.add(nspawners1);
                newSpawnerList.add(nspawners2);
                newSpawnerList.add(nspawners3);
                newSpawnerList.add(nspawners4);
                newSpawnerList.add(nspawners5);
                HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                event.getMobSettings().spawners = newSpawnerMap;
            } else if (event.getBiomeCategory() == Biome.BiomeCategory.THEEND) {
                List<MobSpawnSettings.SpawnerData> spawnersList = event.getMobSettings().spawners.get(MobCategory.MONSTER).unwrap();
                ArrayList<MobSpawnSettings.SpawnerData> newSpawnerList = new ArrayList<>(spawnersList);
                newSpawnerList.add(espawners);
                newSpawnerList.add(espawners1);
                newSpawnerList.add(espawners2);
                newSpawnerList.add(espawners3);
                newSpawnerList.add(espawners4);
                newSpawnerList.add(espawners5);
                HashMap<MobCategory, WeightedRandomList<MobSpawnSettings.SpawnerData>> newSpawnerMap = new HashMap<>(event.getMobSettings().spawners);
                newSpawnerMap.put(MobCategory.MONSTER, WeightedRandomList.create(newSpawnerList));
                event.getMobSettings().spawners = newSpawnerMap;
            }
        }
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPSTABBER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPSNOWBALLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPGUNNER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPGIFTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPZAPPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPBRAWLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPSTABBER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPSNOWBALLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPGUNNER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPGIFTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPZAPPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.NCOLDSNAPBRAWLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPSTABBER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPSNOWBALLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPGUNNER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPGIFTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPZAPPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.ECOLDSNAPBRAWLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPSTABBER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPSNOWBALLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPGUNNER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPGIFTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPZAPPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.PCOLDSNAPBRAWLER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnRestrictionAccessor.callRegister(Register.COLDSNAPCOW,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }


        public static Boolean BiomeExclusion(ResourceLocation[] BiomeExclusion, String name) {
            for (ResourceLocation i : BiomeExclusion) {
                if (i.toString().equals(name)) {
                    return false;
                }
            }
            return true;


        }
}
