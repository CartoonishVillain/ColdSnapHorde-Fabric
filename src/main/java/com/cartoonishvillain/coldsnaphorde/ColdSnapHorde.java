package com.cartoonishvillain.coldsnaphorde;

import com.cartoonishvillain.coldsnaphorde.component.WorldCooldownComponent;
import com.cartoonishvillain.coldsnaphorde.config.ColdSnapConfig;
import com.cartoonishvillain.coldsnaphorde.entities.Spawns;
import com.cartoonishvillain.coldsnaphorde.events.Horde;
import io.netty.buffer.Unpooled;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.cartoonishvillain.coldsnaphorde.component.ComponentStarter.WORLDCOMPONENT;

public class ColdSnapHorde implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("coldsnaphorde");
	public static final String MOD_ID = "coldsnaphorde";
	public static boolean isCalyxLoaded;
	public static boolean isInHolidayWindow;
	public static Horde Horde;
	public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(new ResourceLocation(ColdSnapHorde.MOD_ID, "immortuostab"), () -> new ItemStack(Register.ROCKYSNOWBALL));
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

		ServerLifecycleEvents.SERVER_STARTING.register(ServerStartListener.getInstance());

		ServerTickEvents.END_WORLD_TICK.register(WorldTickListener.getInstance());

		Spawns.initSpawns();
	}

	public static class ServerStartListener implements ServerLifecycleEvents.ServerStarting{
		private static final ServerStartListener INSTANCE = new ServerStartListener();

		public static ServerStartListener getInstance() {return INSTANCE;}

		@Override
		public void onServerStarting(MinecraftServer server) {
			Horde = new Horde(server);

			for(ServerLevel serverWorld : server.getAllLevels()){
				WorldCooldownComponent h = WORLDCOMPONENT.get(serverWorld);
					if(h.getCooldownTicks() <= 0){h.setCooldownTicks(config.coldSnapSettings.GLOBALHORDECOOLDOWN * 20);}
			}
		}
	}

	public static class WorldTickListener implements ServerTickEvents.EndWorldTick{
		private static final WorldTickListener INSTANCE = new WorldTickListener();

		public static WorldTickListener getInstance() {return INSTANCE;}

		@Override
		public void onEndTick(ServerLevel world) {
			WorldCooldownComponent h = WORLDCOMPONENT.get(world);

			if(h.getCooldownTicks() > 0){
					h.addCooldownTicks(-1);
				}
			ColdSnapHorde.Horde.tick();
		}
	}
}
