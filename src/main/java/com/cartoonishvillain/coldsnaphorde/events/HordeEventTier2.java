package com.cartoonishvillain.coldsnaphorde.events;

import com.cartoonishvillain.cartoonishhorde.EntityHordeData;
import com.cartoonishvillain.cartoonishhorde.Horde;
import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.HordeDataManager;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.Utils;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.*;
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


public class HordeEventTier2 extends Horde {
    public HordeEventTier2 (MinecraftServer server) {
        super(server);
    }

    @Override
    public void Stop(HordeStopReasons stopReason) {
        switch (stopReason) {
            case VICTORY -> {
                broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordevictory").withStyle(ChatFormatting.AQUA));
                for (ServerPlayer player : players) {
                    giveAdvancement(player, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "diced_snowmen"));
                }
                giveAdvancement(hordeAnchorPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "diced_snowmen"));
                HordeDataManager.getInstance().updateHighestLevelBeaten(server, 2);
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
        allowedActive = ColdSnapHorde.config.coldSnapSettings.TIER2HORDESIZE;
    }

    @Override
    public void SetUpHorde(ServerPlayer serverPlayer) {
        super.SetUpHorde(serverPlayer);
        if(!trueBiomeCheck(world,  center) || !Utils.tier2Valid(world, center)) return;
        hordeDataManager.setCooldownTicks(-1);

        bossInfo.setCreateWorldFog(true);
        bossInfo.setColor(BossEvent.BossBarColor.BLUE);
        if (Utils.isSwamp(world.getBiome(center))) {
            bossInfo.setColor(BossEvent.BossBarColor.GREEN);
            bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 2)").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        } else {
            bossInfo.setColor(BossEvent.BossBarColor.BLUE);
            bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 2)").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
        }
        giveAdvancement(serverPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "snow_storm"));
        broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordestart", serverPlayer.getDisplayName()).withStyle(ChatFormatting.AQUA));
        hordeDataManager.setCurrentHordeLevel(2);
    }

    @Override
    public void setEasyDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVEEASY;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVEEASY;
    }

    @Override
    public void setNormalDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVENORMAL;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVENORMAL;
    }

    @Override
    public void setHardDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVEHARD;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER2ALIVEHARD;
    }

    @Override
    protected void updateCenter() {
        if (updateCenter <= 0 && hordeAnchorPlayer != null) {
            center = hordeAnchorPlayer.blockPosition();
            updateCenter = ColdSnapHorde.config.coldSnapSettings.UPDATETICK;
            if (Utils.isSwamp(world.getBiome(center))) {
                bossInfo.setColor(BossEvent.BossBarColor.GREEN);
                bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 2)").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
            } else {
                bossInfo.setColor(BossEvent.BossBarColor.BLUE);
                bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 2)").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
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
            //If the spawn is not within additional Horde parameters, clear it out. We will accept extra attempts for this.
            if (hordeSpawn.isPresent() && !additionalHordeCheck(hordeSpawn.get(), world))
                hordeSpawn = Optional.empty();
            attempts++;
            if (hordeSpawn.isEmpty() && attempts >= 10) {
                this.Stop(HordeStopReasons.SPAWN_ERROR);
                return null;
            }
        }
        return hordeSpawn.get();
    }

    protected boolean additionalHordeCheck(BlockPos pos, ServerLevel world) {
        return trueBiomeCheck(world, pos) && Utils.tier2Valid(world, center);
    }

    @Override
    protected void spawnHordeMember() {
        ArrayList<Integer> SpawnWeights = new ArrayList<>();
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.GUNNER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.STABBER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.SNOWBALLER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.GIFTER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.ZAPPER);
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
                ColdSnapGifter coldSnapGifter = new StandardHorde.StandardGifter(Register.COLDSNAPGIFTER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapGifter.getType());
                if (pos == null) return;
                coldSnapGifter = gifterSpawnRules(world, pos);
                coldSnapGifter.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapGifter, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapGifter);
                activeHordeMembers.add(coldSnapGifter);
            }
            case 4 -> {
                ColdSnapZapper coldSnapZapper = new StandardHorde.StandardZapper(Register.COLDSNAPSNOWBALLER, world);
                BlockPos pos = hordeSpawnAttempter(coldSnapZapper.getType());
                if (pos == null) return;
                coldSnapZapper = zapperSpawnRules(world, pos);
                coldSnapZapper.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                injectGoal(coldSnapZapper, ColdSnapHorde.defaultHordeData, ColdSnapHorde.defaultHordeData.getGoalMovementSpeed());
                world.addFreshEntity(coldSnapZapper);
                activeHordeMembers.add(coldSnapZapper);
            }
            case 5 -> {
                ColdSnapBrawler coldSnapBrawler = new StandardHorde.StandardBrawler(Register.COLDSNAPSNOWBALLER, world);
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
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapGunner = new PlagueHorde.PlagueGunner(Register.PCOLDSNAPGUNNER, world);
            } else {
                coldSnapGunner = new StandardHorde.StandardGunner(Register.COLDSNAPGUNNER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapGunner = new PlagueHorde.PlagueGunner(Register.PCOLDSNAPGUNNER, world);
        }
        return coldSnapGunner;
    }

    private ColdSnapStabber stabberSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapStabber coldSnapStabber = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapStabber = new PlagueHorde.PlagueStabber(Register.PCOLDSNAPSTABBER, world);
            } else {
                coldSnapStabber = new StandardHorde.StandardStabber(Register.COLDSNAPSTABBER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapStabber = new PlagueHorde.PlagueStabber(Register.PCOLDSNAPSTABBER, world);
        }
        return coldSnapStabber;
    }


    private ColdSnapSnowballer snowballerSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapSnowballer coldSnapSnowballer = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapSnowballer = new PlagueHorde.PlagueSnowballer(Register.PCOLDSNAPSNOWBALLER, world);
            } else {
                coldSnapSnowballer = new StandardHorde.StandardSnowballer(Register.COLDSNAPSNOWBALLER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapSnowballer = new PlagueHorde.PlagueSnowballer(Register.PCOLDSNAPSNOWBALLER, world);
        }
        return coldSnapSnowballer;
    }

    private ColdSnapGifter gifterSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapGifter coldSnapGifter = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapGifter = new PlagueHorde.PlagueGifter(Register.PCOLDSNAPGIFTER, world);
            } else {
                coldSnapGifter = new StandardHorde.StandardGifter(Register.COLDSNAPGIFTER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapGifter = new PlagueHorde.PlagueGifter(Register.PCOLDSNAPGIFTER, world);
        }
        return coldSnapGifter;
    }

    private ColdSnapZapper zapperSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapZapper coldSnapZapper = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapZapper = new PlagueHorde.PlagueZapper(Register.PCOLDSNAPZAPPER, world);
            } else {
                coldSnapZapper = new StandardHorde.StandardZapper(Register.COLDSNAPZAPPER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapZapper = new PlagueHorde.PlagueZapper(Register.PCOLDSNAPZAPPER, world);
        }
        return coldSnapZapper;
    }

    private ColdSnapBrawler brawlerSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapBrawler coldSnapBrawler = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            if (world.random.nextInt(20) == 1) {
                coldSnapBrawler = new PlagueHorde.PlagueBrawler(Register.PCOLDSNAPBRAWLER, world);
            } else {
                coldSnapBrawler = new StandardHorde.StandardBrawler(Register.COLDSNAPBRAWLER, world);
            }
        } else if (!Utils.isEnd(world) && !Utils.isNether(world) && Utils.isSwamp(world.getBiome(pos))) {
            coldSnapBrawler = new PlagueHorde.PlagueBrawler(Register.PCOLDSNAPBRAWLER, world);
        }
        return coldSnapBrawler;
    }
}
