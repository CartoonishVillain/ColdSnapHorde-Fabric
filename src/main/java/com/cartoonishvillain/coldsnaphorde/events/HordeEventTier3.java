package com.cartoonishvillain.coldsnaphorde.events;

import com.cartoonishvillain.cartoonishhorde.EntityHordeData;
import com.cartoonishvillain.cartoonishhorde.Horde;
import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.Utils;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.StandardHorde;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.giveAdvancement;
import static com.cartoonishvillain.coldsnaphorde.ColdSnapHorde.hordeDataManager;


public class HordeEventTier3 extends Horde {
    public HordeEventTier3(MinecraftServer server) {
        super(server);
    }

    boolean isSwamp;
    boolean isEnd;
    boolean isNether;

    @Override
    public void Stop(HordeStopReasons stopReason) {
        switch (stopReason) {
            case VICTORY -> {
                broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordevictory").withStyle(ChatFormatting.AQUA));
                ResourceLocation extraAchievement = null;
                if (isSwamp) {
                    extraAchievement = new ResourceLocation(ColdSnapHorde.MOD_ID, "pestilence");
                } else if (isEnd) {
                    extraAchievement = new ResourceLocation(ColdSnapHorde.MOD_ID, "horde_end");
                } else if (isNether) {
                    extraAchievement = new ResourceLocation(ColdSnapHorde.MOD_ID, "meltdown");
                }

                for (ServerPlayer player : players) {
                    giveAdvancement(player, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "horde_breaker"));
                    if (extraAchievement != null) {
                        giveAdvancement(player, server, extraAchievement);
                    }
                }

                giveAdvancement(hordeAnchorPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "horde_breaker"));
                giveAdvancement(hordeAnchorPlayer, server, extraAchievement);
                hordeDataManager.updateHighestLevelBeaten(server, 3);
            }
            case DEFEAT -> broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordedefeat").withStyle(ChatFormatting.RED));
            case PEACEFUL -> broadcast(server, new TranslatableComponent("message.coldsnaphorde.peaceful").withStyle(ChatFormatting.YELLOW));
            case SPAWN_ERROR -> broadcast(server, new TranslatableComponent("message.coldsnaphorde.confused").withStyle(ChatFormatting.RED));
        }
        hordeDataManager.setCooldownTicks(ColdSnapHorde.config.coldSnapSettings.GLOBALHORDECOOLDOWN * 20);
        hordeDataManager.setCurrentHordeLevel(0);
        super.Stop(stopReason);
    }

    @Override
    public void setActiveMemberCount() {
        allowedActive = ColdSnapHorde.config.coldSnapSettings.TIER3HORDESIZE;
    }

    @Override
    public void SetUpHorde(ServerPlayer serverPlayer) {
        super.SetUpHorde(serverPlayer);
        hordeDataManager.setCooldownTicks(-1);

        isSwamp = false;
        isEnd = false;
        isNether = false;

        bossInfo.setCreateWorldFog(true);
        if (hordeAnchorPlayer.level.dimension().toString().contains("end")) {
            bossInfo.setColor(BossEvent.BossBarColor.PURPLE);
            bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
            isEnd = true;
        } else if (hordeAnchorPlayer.level.dimension().toString().contains("nether")) {
            bossInfo.setColor(BossEvent.BossBarColor.RED);
            bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            isNether = true;
        } else {
            if (Utils.isSwamp(world.getBiome(center))) {
                bossInfo.setColor(BossEvent.BossBarColor.GREEN);
                bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
                isSwamp = true;
            } else {
                bossInfo.setColor(BossEvent.BossBarColor.BLUE);
                bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
            }
        }
        giveAdvancement(serverPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "hail_storm"));
        broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordestart", serverPlayer.getDisplayName()).withStyle(ChatFormatting.AQUA));
        hordeDataManager.setCurrentHordeLevel(3);
    }

    @Override
    public void setEasyDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVEEASY;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVEEASY;
    }

    @Override
    public void setNormalDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVENORMAL;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVENORMAL;
    }

    @Override
    public void setHardDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVEHARD;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER3ALIVEHARD;
    }

    @Override
    protected void updateCenter() {
        if (updateCenter == 0) {
            center = hordeAnchorPlayer.blockPosition();
            updateCenter = ColdSnapHorde.config.coldSnapSettings.UPDATETICK;
            if (!world.dimension().toString().contains("nether") && !world.dimension().toString().contains("end")) {
                if (Utils.isSwamp(world.getBiome(center))) {
                    bossInfo.setColor(BossEvent.BossBarColor.GREEN);
                    bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
                } else {
                    bossInfo.setColor(BossEvent.BossBarColor.BLUE);
                    bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 3)").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
                    isSwamp = false;
                }
            }
            updatePlayers();
            updateHorde();
        } else {
            updateCenter--;
        }
    }

    private void broadcast(MinecraftServer server, Component translationTextComponent) {
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }

    private boolean trueBiomeCheck(ServerLevel world, BlockPos pos) {
        int protlvl = ColdSnapHorde.config.spawnconfig.HEATPROT;
        float temp = world.getBiome(pos).value().getBaseTemperature();
        int code = -1;
        if (temp < 0.3) {
            code = 0;
        } else if (temp >= 0.3 && temp < 0.9) {
            code = 1;
        } else if (temp >= 0.9 && temp < 1.5) {
            code = 2;
        } else if (temp >= 1.5) {
            code = 3;
        }

        return code <= protlvl;
    }

    protected BlockPos hordeSpawnAttempter(EntityType type) {
        Optional<BlockPos> hordeSpawn = Optional.empty();
        int attempts = 0;
        while (hordeSpawn.isEmpty()) {
            hordeSpawn = this.getValidSpawn(2, type);
            attempts++;
            if (hordeSpawn.isEmpty() && attempts >= 5) {
                this.Stop(HordeStopReasons.SPAWN_ERROR);
                return null;
            }
        }
        return hordeSpawn.get();
    }

    @Override
    protected void spawnHordeMember() {
        ArrayList<Integer> SpawnWeights = new ArrayList<>();
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.GUNNER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.STABBER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.SNOWBALLER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.ZAPPER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.GIFTER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.BRAWLER);
        int combined = 0;
        for (Integer weight : SpawnWeights) combined += weight;
        Random random = new Random();
        int rng = random.nextInt(combined);
        int selected = -1;
        int counter = 0;
        for (Integer weights : SpawnWeights) {
            if ((rng - weights) <= 0) {
                selected = counter;
                break;
            } else counter++;
            rng -= weights;
        }

        switch (selected) {
            case 0 -> {
                ColdSnapGunner coldSnapGunner = new StandardHorde.StandardGunner(Register.COLDSNAPGUNNER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapGunner.getType());
                if (pos == null) return;
                coldSnapGunner = gunnerSpawnRules(world, pos);
                coldSnapGunner.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapGunner, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapGunner);
                activeHordeMembers.add(coldSnapGunner);
            }
            case 1 -> {
                ColdSnapStabber coldSnapStabber = new StandardHorde.StandardStabber(Register.COLDSNAPSTABBER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapStabber.getType());
                if (pos == null) return;
                coldSnapStabber = stabberSpawnRules(world, pos);
                coldSnapStabber.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapStabber, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapStabber);
                activeHordeMembers.add(coldSnapStabber);
            }
            case 2 -> {
                ColdSnapSnowballer coldSnapSnowballer = new StandardHorde.StandardSnowballer(Register.COLDSNAPSNOWBALLER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapSnowballer.getType());
                if (pos == null) return;
                coldSnapSnowballer = snowballerSpawnRules(world, pos);
                coldSnapSnowballer.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapSnowballer, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapSnowballer);
                activeHordeMembers.add(coldSnapSnowballer);
            }
            case 3 -> {
                ColdSnapZapper coldSnapZapper = new StandardHorde.StandardZapper(Register.COLDSNAPZAPPER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapZapper.getType());
                if (pos == null) return;
                coldSnapZapper = zapperSpawnRules(world, pos);
                coldSnapZapper.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapZapper, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapZapper);
                activeHordeMembers.add(coldSnapZapper);
            }
            case 4 -> {
                ColdSnapGifter coldSnapGifter = new StandardHorde.StandardGifter(Register.COLDSNAPGIFTER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapGifter.getType());
                if (pos == null) return;
                coldSnapGifter = gifterSpawnRules(world, pos);
                coldSnapGifter.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapGifter, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapGifter);
                activeHordeMembers.add(coldSnapGifter);
            }
            case 5 -> {
                ColdSnapBrawler coldSnapBrawler = new StandardHorde.StandardBrawler(Register.COLDSNAPBRAWLER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapBrawler.getType());
                if (pos == null) return;
                coldSnapBrawler = brawlerSpawnRules(world, pos);
                coldSnapBrawler.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapBrawler, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapBrawler);
                activeHordeMembers.add(coldSnapBrawler);
            }
        }
    }

    @Override
    public void injectGoal(PathfinderMob entity, EntityHordeData entityHordeData, double movementSpeedModifier) {
        super.injectGoal(entity, entityHordeData, movementSpeedModifier);
        if(entity instanceof GenericHordeMember) {((GenericHordeMember) entity).setHordeStatus(true);}
    }


    private ColdSnapGunner gunnerSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapGunner coldSnapGunner = null;
        if (Utils.isSwamp(world.getBiome(pos))) {
            coldSnapGunner = new PlagueHorde.PlagueGunner(Register.PCOLDSNAPGUNNER, world);
        } else if (world.dimension().toString().contains("end")) {
            coldSnapGunner = new EndHorde.EndGunner(Register.ECOLDSNAPGUNNER, world);
        } else if (world.dimension().toString().contains("nether")) {
            coldSnapGunner = new EndHorde.EndGunner(Register.NCOLDSNAPGUNNER, world);
        } else if (trueBiomeCheck(world, pos)) {
            if (!world.isRainingAt(pos)) {
                Random random = new Random();
                int chance = random.nextInt(100);
                if (chance <= 5) {
                    coldSnapGunner = new NetherHorde.NetherGunner(Register.NCOLDSNAPGUNNER, world);
                }
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapGunner == null)
                    coldSnapGunner = new EndHorde.EndGunner(Register.ECOLDSNAPGUNNER, world);
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapGunner == null)
                    coldSnapGunner = new PlagueHorde.PlagueGunner(Register.PCOLDSNAPGUNNER, world);
                if (coldSnapGunner == null)
                    coldSnapGunner = new StandardHorde.StandardGunner(Register.COLDSNAPGUNNER, world);
            } else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if (chance <= 10 && coldSnapGunner == null)
                    coldSnapGunner = new PlagueHorde.PlagueGunner(Register.PCOLDSNAPGUNNER, world);
                if (coldSnapGunner == null)
                    coldSnapGunner = new StandardHorde.StandardGunner(Register.COLDSNAPGUNNER, world);
            }

        } else coldSnapGunner = new NetherHorde.NetherGunner(Register.NCOLDSNAPGUNNER, world);
        return coldSnapGunner;
    }

    private ColdSnapStabber stabberSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapStabber coldSnapStabber = null;
        if (Utils.isSwamp(world.getBiome(pos))) {
            coldSnapStabber = new PlagueHorde.PlagueStabber(Register.PCOLDSNAPSTABBER, world);
        } else if (world.dimension().toString().contains("end")) {
            coldSnapStabber = new EndHorde.EndStabber(Register.ECOLDSNAPSTABBER, world);
        } else if (world.dimension().toString().contains("nether")) {
            coldSnapStabber = new NetherHorde.NetherStabber(Register.NCOLDSNAPSTABBER, world);
        } else if (trueBiomeCheck(world, pos)) {
            if (!world.isRainingAt(pos)) {
                Random random = new Random();
                int chance = random.nextInt(100);
                if (chance <= 5) {
                    coldSnapStabber = new NetherHorde.NetherStabber(Register.NCOLDSNAPSTABBER, world);
                }
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapStabber == null)
                    coldSnapStabber = new EndHorde.EndStabber(Register.ECOLDSNAPSTABBER, world);
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapStabber == null)
                    coldSnapStabber = new PlagueHorde.PlagueStabber(Register.PCOLDSNAPSTABBER, world);
                if (coldSnapStabber == null)
                    coldSnapStabber = new StandardHorde.StandardStabber(Register.COLDSNAPSTABBER, world);
            } else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if (chance <= 10 && coldSnapStabber == null)
                    coldSnapStabber = new PlagueHorde.PlagueStabber(Register.PCOLDSNAPSTABBER, world);
                if (coldSnapStabber == null)
                    coldSnapStabber = new StandardHorde.StandardStabber(Register.COLDSNAPSTABBER, world);
            }

        } else coldSnapStabber = new NetherHorde.NetherStabber(Register.NCOLDSNAPSTABBER, world);
        return coldSnapStabber;
    }


    private ColdSnapSnowballer snowballerSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapSnowballer coldSnapSnowballer = null;
        if (Utils.isSwamp(world.getBiome(pos))) {
            coldSnapSnowballer = new PlagueHorde.PlagueSnowballer(Register.PCOLDSNAPSNOWBALLER, world);
        } else if (world.dimension().toString().contains("end")) {
            coldSnapSnowballer = new EndHorde.EndSnowballer(Register.ECOLDSNAPSNOWBALLER, world);
        } else if (world.dimension().toString().contains("nether")) {
            coldSnapSnowballer = new NetherHorde.NetherSnowballer(Register.NCOLDSNAPSNOWBALLER, world);
        }
        else if (trueBiomeCheck(world, pos)) {
            if (!world.isRainingAt(pos)) {
                Random random = new Random();
                int chance = random.nextInt(100);
                if (chance <= 5) {
                    coldSnapSnowballer = new NetherHorde.NetherSnowballer(Register.NCOLDSNAPSNOWBALLER, world);}
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapSnowballer == null)
                    coldSnapSnowballer = new EndHorde.EndSnowballer(Register.ECOLDSNAPSNOWBALLER, world);
                chance = random.nextInt(100);
                if (chance <= 5 && coldSnapSnowballer == null)
                    coldSnapSnowballer = new PlagueHorde.PlagueSnowballer(Register.PCOLDSNAPSNOWBALLER, world);
                if (coldSnapSnowballer == null)
                    coldSnapSnowballer = new StandardHorde.StandardSnowballer(Register.COLDSNAPSNOWBALLER, world);
            } else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if (chance <= 10 && coldSnapSnowballer == null)
                    coldSnapSnowballer = new PlagueHorde.PlagueSnowballer(Register.PCOLDSNAPSNOWBALLER, world);
                if (coldSnapSnowballer == null)
                    coldSnapSnowballer = new StandardHorde.StandardSnowballer(Register.COLDSNAPSNOWBALLER, world);
            }

        } else coldSnapSnowballer = new NetherHorde.NetherSnowballer(Register.NCOLDSNAPSNOWBALLER, world);
        return coldSnapSnowballer;
    }

    private ColdSnapGifter gifterSpawnRules(ServerLevel world, BlockPos pos){
        ColdSnapGifter coldSnapGifter = null;
        if (Utils.isSwamp(world.getBiome(pos))){
            coldSnapGifter = new PlagueHorde.PlagueGifter(Register.PCOLDSNAPGIFTER, world);
        }else if(world.dimension().toString().contains("end")){
            coldSnapGifter = new EndHorde.EndGifter(Register.ECOLDSNAPGIFTER, world);
        }else if(world.dimension().toString().contains("nether")){
            coldSnapGifter = new NetherHorde.NetherGifter(Register.NCOLDSNAPGIFTER, world);
        } else if(trueBiomeCheck(world, pos)){
            if(!world.isRainingAt(pos)){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance <= 5){coldSnapGifter = new NetherHorde.NetherGifter(Register.NCOLDSNAPGIFTER, world);}
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapGifter == null) coldSnapGifter = new EndHorde.EndGifter(Register.ECOLDSNAPGIFTER, world);
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapGifter == null) coldSnapGifter = new PlagueHorde.PlagueGifter(Register.PCOLDSNAPGIFTER, world);
                if(coldSnapGifter == null) coldSnapGifter = new StandardHorde.StandardGifter(Register.COLDSNAPGIFTER, world);
            }else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if(chance <= 10 && coldSnapGifter == null) coldSnapGifter = new PlagueHorde.PlagueGifter(Register.PCOLDSNAPGIFTER, world);
                if(coldSnapGifter == null) coldSnapGifter = new StandardHorde.StandardGifter(Register.COLDSNAPGIFTER, world);
            }

        }
        else coldSnapGifter = new NetherHorde.NetherGifter(Register.NCOLDSNAPGIFTER, world);
        return coldSnapGifter;
    }

    private ColdSnapZapper zapperSpawnRules(ServerLevel world, BlockPos pos){
        ColdSnapZapper coldSnapZapper = null;
        if (Utils.isSwamp(world.getBiome(pos))){coldSnapZapper = new PlagueHorde.PlagueZapper(Register.PCOLDSNAPZAPPER, world);
        }else if(world.dimension().toString().contains("end")){
            coldSnapZapper = new EndHorde.EndZapper(Register.ECOLDSNAPZAPPER, world);
        }else if(world.dimension().toString().contains("nether")){
            coldSnapZapper = new NetherHorde.NetherZapper(Register.NCOLDSNAPZAPPER, world);
        }else if(trueBiomeCheck(world, pos)){
            if(!world.isRainingAt(pos)){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance <= 5){coldSnapZapper = new NetherHorde.NetherZapper(Register.NCOLDSNAPZAPPER, world);}
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapZapper == null) coldSnapZapper = new EndHorde.EndZapper(Register.ECOLDSNAPZAPPER, world);
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapZapper == null) coldSnapZapper = new PlagueHorde.PlagueZapper(Register.PCOLDSNAPZAPPER, world);
                if(coldSnapZapper == null) coldSnapZapper = new StandardHorde.StandardZapper(Register.COLDSNAPZAPPER, world);
            }else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if(chance <= 10 && coldSnapZapper == null) coldSnapZapper = new PlagueHorde.PlagueZapper(Register.PCOLDSNAPZAPPER, world);
                if(coldSnapZapper == null) coldSnapZapper = new StandardHorde.StandardZapper(Register.COLDSNAPZAPPER, world);
            }

        }
        else coldSnapZapper = new NetherHorde.NetherZapper(Register.NCOLDSNAPZAPPER, world);
        return coldSnapZapper;
    }

    private ColdSnapBrawler brawlerSpawnRules(ServerLevel world, BlockPos pos){
        ColdSnapBrawler coldSnapBrawler = null;
        if (Utils.isSwamp(world.getBiome(pos))){
            coldSnapBrawler = new PlagueHorde.PlagueBrawler(Register.PCOLDSNAPBRAWLER, world);
        }else if(world.dimension().toString().contains("end")){
            coldSnapBrawler = new EndHorde.EndBrawler(Register.ECOLDSNAPBRAWLER, world);
        }else if(world.dimension().toString().contains("nether")){
            coldSnapBrawler = new NetherHorde.NetherBrawler(Register.NCOLDSNAPBRAWLER, world);
        } else if(trueBiomeCheck(world, pos)){
            if(!world.isRainingAt(pos)){
                Random random = new Random();
                int chance = random.nextInt(100);
                if(chance <= 5){coldSnapBrawler = new NetherHorde.NetherBrawler(Register.NCOLDSNAPBRAWLER, world);}
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapBrawler == null) coldSnapBrawler = new EndHorde.EndBrawler(Register.ECOLDSNAPBRAWLER, world);
                chance = random.nextInt(100);
                if(chance <= 5 && coldSnapBrawler == null) coldSnapBrawler = new PlagueHorde.PlagueBrawler(Register.PCOLDSNAPBRAWLER, world);
                if(coldSnapBrawler == null) coldSnapBrawler = new StandardHorde.StandardBrawler(Register.COLDSNAPBRAWLER, world);
            }else {
                Random random = new Random();
                int chance = random.nextInt(150);
                if(chance <= 10 && coldSnapBrawler == null) coldSnapBrawler = new PlagueHorde.PlagueBrawler(Register.PCOLDSNAPBRAWLER, world);
                if(coldSnapBrawler == null) coldSnapBrawler = new StandardHorde.StandardBrawler(Register.COLDSNAPBRAWLER, world);
            }

        }
        else coldSnapBrawler = new NetherHorde.NetherBrawler(Register.NCOLDSNAPBRAWLER, world);
        return coldSnapBrawler;
    }
}
