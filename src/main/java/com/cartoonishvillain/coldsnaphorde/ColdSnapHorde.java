package com.cartoonishvillain.coldsnaphorde;

import com.cartoonishvillain.cartoonishhorde.EntityHordeData;
import com.cartoonishvillain.coldsnaphorde.commands.GetHordeDefeatedLevel;
import com.cartoonishvillain.coldsnaphorde.commands.SetHordeDefeatedLevel;
import com.cartoonishvillain.coldsnaphorde.commands.StartHorde;
import com.cartoonishvillain.coldsnaphorde.commands.StopHorde;
import com.cartoonishvillain.coldsnaphorde.component.WorldCooldownComponent;
import com.cartoonishvillain.coldsnaphorde.config.ColdSnapConfig;
import com.cartoonishvillain.coldsnaphorde.entities.Spawns;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapGunner;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.events.HordeEventTier1;
import com.cartoonishvillain.coldsnaphorde.events.HordeEventTier2;
import com.cartoonishvillain.coldsnaphorde.events.HordeEventTier3;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.cartoonishvillain.coldsnaphorde.component.ComponentStarter.WORLDCOMPONENT;

public class ColdSnapHorde implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("coldsnaphorde");
	public static final String MOD_ID = "coldsnaphorde";
	public static ArrayList<Item> TOPHATS = new ArrayList<>();
	public static boolean isCalyxLoaded;
	public static boolean isInHolidayWindow;
	public static EntityHordeData defaultHordeData;
	public static HordeEventTier1 hordeTier1;
	public static HordeEventTier2 hordeTier2;
	public static HordeEventTier3 hordeTier3;
	public static HordeDataManager hordeDataManager = null;
	public static ArrayList<String> tier1PresentPossibilities = new ArrayList<>();
	public static ArrayList<Float> tier1PresentWeights = new ArrayList<>();
	public static ArrayList<String> tier2PresentPossibilities = new ArrayList<>();
	public static ArrayList<Float> tier2PresentWeights = new ArrayList<>();
	public static ArrayList<String> tier3PresentPossibilities = new ArrayList<>();
	public static ArrayList<Float> tier3PresentWeights = new ArrayList<>();
	public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(new ResourceLocation(ColdSnapHorde.MOD_ID, "coldgroup"), () -> new ItemStack(Register.ROCKYSNOWBALL));
	public static ColdSnapConfig config;

	@Override
	public void onInitialize() {
		Register.init();
		AutoConfig.register(ColdSnapConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ColdSnapConfig.class).getConfig();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		isCalyxLoaded = FabricLoader.getInstance().isModLoaded("immortuoscalyx") && config.coldSnapSettings.PLAGUEIMMORTUOSCOMPAT;
		holidayWindowCheck();

		CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
			GetHordeDefeatedLevel.register(dispatcher);
			SetHordeDefeatedLevel.register(dispatcher);
			StartHorde.register(dispatcher);
			StopHorde.register(dispatcher);
		}));

		ServerLifecycleEvents.SERVER_STARTING.register(ServerStartListener.getInstance());

		ServerTickEvents.END_WORLD_TICK.register(WorldTickListener.getInstance());

		ServerPlayConnectionEvents.JOIN.register(JoinListener.getInstance());


		Spawns.addSpawns();
	}

	public static void holidayWindowCheck(){
		Date date = Date.from(Instant.now());
		Date december = Date.from(Instant.now());
		december.setMonth(Calendar.DECEMBER);
		december.setDate(15);
		december.setHours(0);
		december.setMinutes(0);
		if(date.getMonth() == Calendar.JANUARY){
			december.setYear(december.getYear()-1);
		}
		Date January = Date.from(Instant.now());
		January.setMonth(Calendar.JANUARY);
		January.setDate(5);
		January.setHours(0);
		January.setMinutes(0);
		if(date.getMonth() != Calendar.JANUARY){
			January.setYear(January.getYear() + 1);
		}
		isInHolidayWindow = ((date.compareTo(december) >= 0) && (date.compareTo(January) <= 0));
	}

	public static class ServerStartListener implements ServerLifecycleEvents.ServerStarting{
		private static final ServerStartListener INSTANCE = new ServerStartListener();

		public static ServerStartListener getInstance() {return INSTANCE;}

		@Override
		public void onServerStarting(MinecraftServer server) {
			defaultHordeData = new EntityHordeData(3, 0.5D, 1, Register.COLDSNAPGUNNER, ColdSnapGunner.class);

			for(ServerLevel serverWorld : server.getAllLevels()){
				WorldCooldownComponent h = WORLDCOMPONENT.get(serverWorld);
					if(h.getLevelBeaten() <= 0){h.setLevelBeaten(config.coldSnapSettings.GLOBALHORDECOOLDOWN * 20);}
			}

			hordeTier1 = new HordeEventTier1(server);
			hordeTier2 = new HordeEventTier2(server);
			hordeTier3 = new HordeEventTier3(server);

			hordeDataManager = HordeDataManager.getInstance();

			tier1PresentPossibilities.add("coal"); tier1PresentWeights.add(15f);
			tier1PresentPossibilities.add("snow"); tier1PresentWeights.add(15f);
			tier1PresentPossibilities.add("ice"); tier1PresentWeights.add(20f);
			tier1PresentPossibilities.add("packedice"); tier1PresentWeights.add(15f);
			tier1PresentPossibilities.add("blueice"); tier1PresentWeights.add(5f);
			tier1PresentPossibilities.add("candycane"); tier1PresentWeights.add(20f);
			tier1PresentPossibilities.add("iceshard"); tier1PresentWeights.add(10f);

			tier2PresentPossibilities.add("coal"); tier2PresentWeights.add(30f);
			tier2PresentPossibilities.add("snow"); tier2PresentWeights.add(15f);
			tier2PresentPossibilities.add("ice"); tier2PresentWeights.add(20f);
			tier2PresentPossibilities.add("packedice"); tier2PresentWeights.add(15f);
			tier2PresentPossibilities.add("blueice"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("doggo"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("cats"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("birb"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("friendsnowman"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("music"); tier2PresentWeights.add(15f);
			tier2PresentPossibilities.add("rollercoaster"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("horse"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("pig"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("candycane"); tier2PresentWeights.add(20f);
			tier2PresentPossibilities.add("axolotl"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("screamgoat"); tier2PresentWeights.add(5f);
			tier2PresentPossibilities.add("panda"); tier2PresentWeights.add(5f);
			tier2PresentPossibilities.add("icesword"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("transposerpiece"); tier2PresentWeights.add(10f);
			tier2PresentPossibilities.add("iceshard"); tier2PresentWeights.add(15f);
			tier2PresentPossibilities.add("transposer"); tier2PresentWeights.add(5f);
			tier2PresentPossibilities.add("frostcore"); tier2PresentWeights.add(5f);

			tier3PresentPossibilities.add("blueice"); tier3PresentWeights.add(20f);
			tier3PresentPossibilities.add("doggo"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("cats"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("birb"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("friendsnowman"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("music"); tier3PresentWeights.add(15f);
			tier3PresentPossibilities.add("rollercoaster"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("horse"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("pig"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("candycane"); tier3PresentWeights.add(20f);
			tier3PresentPossibilities.add("axolotl"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("screamgoat"); tier3PresentWeights.add(5f);
			tier3PresentPossibilities.add("panda"); tier3PresentWeights.add(5f);
			tier3PresentPossibilities.add("icesword"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("transposerpiece"); tier3PresentWeights.add(10f);
			tier3PresentPossibilities.add("iceshard"); tier3PresentWeights.add(15f);
			tier3PresentPossibilities.add("transposer"); tier3PresentWeights.add(5f);
			tier3PresentPossibilities.add("frostcore"); tier3PresentWeights.add(10f);
		}
	}

	public static class JoinListener implements ServerPlayConnectionEvents.Join{
		private static final JoinListener INSTANCE = new JoinListener();
		@Override
		public void onPlayReady(ServerGamePacketListenerImpl handler, PacketSender sender, MinecraftServer server) {
			if(ColdSnapHorde.isInHolidayWindow){
				handler.player.sendMessage(new TranslatableComponent("info.coldsnaphorde.holiday").withStyle(ChatFormatting.AQUA), UUID.randomUUID());
			}
		}
		public static JoinListener getInstance() {return INSTANCE;}

	}

	public static class WorldTickListener implements ServerTickEvents.EndWorldTick{
		private static final WorldTickListener INSTANCE = new WorldTickListener();

		public static WorldTickListener getInstance() {return INSTANCE;}

		@Override
		public void onEndTick(ServerLevel world) {
			WorldCooldownComponent h = WORLDCOMPONENT.get(world);

			hordeDataManager.tickCooldown();
			hordeTier1.tick();
			hordeTier2.tick();
			hordeTier3.tick();
		}
	}

	public static void giveAdvancement(ServerPlayer player, MinecraftServer server, ResourceLocation advancementResource){
		Advancement advancement = server.getAdvancements().getAdvancement(advancementResource);
		if(advancement != null) {
			AdvancementProgress advancementprogress = player.getAdvancements().getOrStartProgress(advancement);
			if (!advancementprogress.isDone()) {
				for(String s : advancementprogress.getRemainingCriteria()) {
					player.getAdvancements().award(advancement, s);
				}
			}
		}
	}
}
