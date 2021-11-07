package com.cartoonishvillain.coldsnaphorde.entities;

//Todo: Convert all of *this* into the fabric equivalent.

public class Spawns {
//    public static void SnowmanSpawner(BiomeLoadingEvent event){
//        final String BiomeList = ColdSnapHorde.cconfig.BiomeExclusion.get();
//        String[] biomeExclusion = BiomeList.split(",");
//        int exclusionLength = biomeExclusion.length;
//        ResourceLocation[] finalBiomeExclusion = new ResourceLocation[exclusionLength];
//        int counter = 0;
//        for(String i : biomeExclusion){
//            ResourceLocation newResource = new ResourceLocation(i);
//            finalBiomeExclusion[counter] = newResource;
//            counter++;
//        }
//        MobSpawnSettings.SpawnerData spawners = new MobSpawnSettings.SpawnerData(Register.COLDSNAPSTABBER.get(), ColdSnapHorde.cconfig.STABBER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners1 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPGUNNER.get(), ColdSnapHorde.cconfig.GUNNER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners2 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPSNOWBALLER.get(), ColdSnapHorde.cconfig.SNOWBALLER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners3 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPGIFTER.get(), ColdSnapHorde.cconfig.GIFTER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners4 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPZAPPER.get(), ColdSnapHorde.cconfig.ZAPPER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners5 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPBRAWLER.get(), ColdSnapHorde.cconfig.BRAWLER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData spawners6 = new MobSpawnSettings.SpawnerData(Register.COLDSNAPCOW.get(), 8,4, 4);
//
//
//        MobSpawnSettings.SpawnerData nspawners = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPSTABBER.get(), ColdSnapHorde.cconfig.DSTABBER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData nspawners1 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPGUNNER.get(), ColdSnapHorde.cconfig.DGUNNER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData nspawners2 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPSNOWBALLER.get(), ColdSnapHorde.cconfig.DSNOWBALLER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData nspawners3 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPGIFTER.get(), ColdSnapHorde.cconfig.DGIFTER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData nspawners4 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPZAPPER.get(), ColdSnapHorde.cconfig.DZAPPER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData nspawners5 = new MobSpawnSettings.SpawnerData(Register.NCOLDSNAPBRAWLER.get(), ColdSnapHorde.cconfig.DBRAWLER.get(), 1, 1);
//
//        MobSpawnSettings.SpawnerData espawners = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPSTABBER.get(), ColdSnapHorde.cconfig.DSTABBER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData espawners1 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPGUNNER.get(), ColdSnapHorde.cconfig.DGUNNER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData espawners2 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPSNOWBALLER.get(), ColdSnapHorde.cconfig.DSNOWBALLER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData espawners3 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPGIFTER.get(), ColdSnapHorde.cconfig.DGIFTER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData espawners4 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPZAPPER.get(), ColdSnapHorde.cconfig.DZAPPER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData espawners5 = new MobSpawnSettings.SpawnerData(Register.ECOLDSNAPBRAWLER.get(), ColdSnapHorde.cconfig.DBRAWLER.get(), 1, 1);
//
//        MobSpawnSettings.SpawnerData pspawners = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPSTABBER.get(), ColdSnapHorde.cconfig.STABBER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData pspawners1 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPGUNNER.get(), ColdSnapHorde.cconfig.GUNNER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData pspawners2 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPSNOWBALLER.get(), ColdSnapHorde.cconfig.SNOWBALLER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData pspawners3 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPGIFTER.get(), ColdSnapHorde.cconfig.GIFTER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData pspawners4 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPZAPPER.get(), ColdSnapHorde.cconfig.ZAPPER.get(), 1, 1);
//        MobSpawnSettings.SpawnerData pspawners5 = new MobSpawnSettings.SpawnerData(Register.PCOLDSNAPBRAWLER.get(), ColdSnapHorde.cconfig.BRAWLER.get(), 1, 1);
//
//        if (BiomeExclusion(finalBiomeExclusion, event.getName()) && !event.getName().toString().contains("swamp") && !(event.getCategory() == Biome.BiomeCategory.NETHER || event.getCategory() == Biome.BiomeCategory.THEEND)){
//            if(ColdSnapHorde.cconfig.SPAWNTEMPS.get() == 0 && event.getClimate().temperature < 0.3f){
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners1);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners2);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners3);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners4);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners5);
//                event.getSpawns().addSpawn(MobCategory.CREATURE, spawners6);
//            }
//            else if (ColdSnapHorde.cconfig.SPAWNTEMPS.get() == 1 && event.getClimate().temperature < 0.9f){
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners1);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners2);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners3);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners4);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners5);
//                event.getSpawns().addSpawn(MobCategory.CREATURE, spawners6);
//            }
//            else if (ColdSnapHorde.cconfig.SPAWNTEMPS.get() == 2 && event.getClimate().temperature < 1.5f){
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners1);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners2);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners3);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners4);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners5);
//                event.getSpawns().addSpawn(MobCategory.CREATURE, spawners6);
//
//            }
//            else if (ColdSnapHorde.cconfig.SPAWNTEMPS.get() == 3){
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners1);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners2);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners3);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners4);
//                event.getSpawns().addSpawn(MobCategory.MONSTER, spawners5);
//                event.getSpawns().addSpawn(MobCategory.CREATURE, spawners6);
//            }
//        }
//        else if(event.getName().toString().contains("swamp")){
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners1);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners2);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners3);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners4);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, pspawners5);
//        }
//        else if(event.getCategory() == Biome.BiomeCategory.NETHER && ColdSnapHorde.cconfig.DSPAWN.get()){
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners1);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners2);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners3);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners4);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, nspawners5);
//        }
//        else if(event.getCategory() == Biome.BiomeCategory.THEEND && ColdSnapHorde.cconfig.DSPAWN.get()){
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners1);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners2);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners3);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners4);
//            event.getSpawns().addSpawn(MobCategory.MONSTER, espawners5);
//        }
//
//    }
//
//
//
//    public static void PlacementManager(){
//        SpawnPlacements.register(Register.COLDSNAPSTABBER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPSNOWBALLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPGUNNER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPGIFTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPZAPPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPBRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPSTABBER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPSNOWBALLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPGUNNER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPGIFTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPZAPPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.NCOLDSNAPBRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPSTABBER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPSNOWBALLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPGUNNER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPGIFTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPZAPPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.ECOLDSNAPBRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPSTABBER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPSNOWBALLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPGUNNER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPGIFTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPZAPPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.PCOLDSNAPBRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(Register.COLDSNAPCOW.get(),SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
//    }
//
//    public static Boolean BiomeExclusion(ResourceLocation[] BiomeExclusion, ResourceLocation name){
//        for (ResourceLocation i : BiomeExclusion){
//            if (i.toString().equals(name.toString())){return false;}
//        }
//        return true;
//    }
}
