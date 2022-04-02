package com.cartoonishvillain.coldsnaphorde.events;

import com.cartoonishvillain.cartoonishhorde.EntityHordeData;
import com.cartoonishvillain.cartoonishhorde.Horde;
import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.HordeDataManager;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.Utils;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapGunner;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapSnowballer;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapStabber;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember;
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


public class HordeEventTier1 extends Horde {
    public HordeEventTier1(MinecraftServer server) {
        super(server);
    }

    @Override
    public void Stop(HordeStopReasons stopReason) {
        switch (stopReason) {
            case VICTORY -> {
                broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordevictory").withStyle(ChatFormatting.AQUA));
                for (ServerPlayer player : players) {
                    giveAdvancement(player, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "sliced_snowmen"));
                }
                giveAdvancement(hordeAnchorPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "sliced_snowmen"));
                HordeDataManager.getInstance().updateHighestLevelBeaten(server, 1);
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
        allowedActive = ColdSnapHorde.config.coldSnapSettings.TIER1HORDESIZE;
    }

    @Override
    public void SetUpHorde(ServerPlayer serverPlayer) {
        super.SetUpHorde(serverPlayer);
        if(!trueBiomeCheck(world,  center) || !Utils.tier1Valid(world, center)) return;
        hordeDataManager.setCooldownTicks(-1);

        bossInfo.setCreateWorldFog(true);
        bossInfo.setColor(BossEvent.BossBarColor.BLUE);
        bossInfo.setName(new TextComponent("Cold Snap Horde (Tier 1)").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
        giveAdvancement(serverPlayer, server, new ResourceLocation(ColdSnapHorde.MOD_ID, "snow_day"));
        broadcast(server, new TranslatableComponent("message.coldsnaphorde.hordestart", serverPlayer.getDisplayName()).withStyle(ChatFormatting.AQUA));
        hordeDataManager.setCurrentHordeLevel(1);
    }

    @Override
    public void setEasyDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVEEASY;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVEEASY;
    }

    @Override
    public void setNormalDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVENORMAL;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVENORMAL;
    }

    @Override
    public void setHardDifficultyStats() {
        Alive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVEHARD;
        initAlive = ColdSnapHorde.config.coldSnapSettings.TIER1ALIVEHARD;
    }

    @Override
    protected void updateCenter() {
        if (updateCenter <= 0 && hordeAnchorPlayer != null) {
            center = hordeAnchorPlayer.blockPosition();
            updateCenter = ColdSnapHorde.config.coldSnapSettings.UPDATETICK;
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
        return trueBiomeCheck(world, pos) && Utils.tier1Valid(world, pos);
    }

    @Override
    protected void spawnHordeMember() {
        ArrayList<Integer> SpawnWeights = new ArrayList<>();
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.GUNNER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.STABBER);
        SpawnWeights.add(ColdSnapHorde.config.spawnconfig.SNOWBALLER);
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
            coldSnapGunner = new StandardHorde.StandardGunner(Register.COLDSNAPGUNNER, world);
        }
        return coldSnapGunner;
    }

    private ColdSnapStabber stabberSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapStabber coldSnapStabber = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            coldSnapStabber = new StandardHorde.StandardStabber(Register.COLDSNAPSTABBER, world);
        }
        return coldSnapStabber;
    }


    private ColdSnapSnowballer snowballerSpawnRules(ServerLevel world, BlockPos pos) {
        ColdSnapSnowballer coldSnapSnowballer = null;
        if(!Utils.isEnd(world) && !Utils.isNether(world) && !Utils.isSwamp(world.getBiome(pos))) {
            coldSnapSnowballer = new StandardHorde.StandardSnowballer(Register.COLDSNAPSNOWBALLER, world);
        }
        return coldSnapSnowballer;
    }
}
