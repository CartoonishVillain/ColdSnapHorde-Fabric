package com.cartoonishvillain.coldsnaphorde.client;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.client.models.endmodel.EndColdSnapSnowballerModel;
import com.cartoonishvillain.coldsnaphorde.client.models.nethermodel.*;
import com.cartoonishvillain.coldsnaphorde.client.models.standardmodel.*;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.*;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders.*;
import com.cartoonishvillain.coldsnaphorde.client.renderers.TopHatRenderer;
import com.cartoonishvillain.coldsnaphorde.client.renderers.endrenders.RenderEndColdSnapSnowballer;
import com.cartoonishvillain.coldsnaphorde.client.renderers.netherrenders.*;
import com.cartoonishvillain.coldsnaphorde.client.renderers.standardrenders.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.fabricmc.fabric.impl.client.rendering.EntityModelLayerImpl;
import net.fabricmc.fabric.mixin.client.rendering.EntityModelLayersAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class ColdSnapClientInitializer implements ClientModInitializer {
    public static ModelLayerLocation COLDSNAPSTABBER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:stabber"), "stabber");
    public static ModelLayerLocation COLDSNAPGUNNER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:gunner"), "gunner");
    public static ModelLayerLocation COLDSNAPSNOWBALLER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:snowballer"), "snowballer");
    public static ModelLayerLocation COLDSNAPGIFTER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:gifter"), "gifter");
    public static ModelLayerLocation COLDSNAPZAPPER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:zapper"), "zapper");
    public static ModelLayerLocation COLDSNAPBRAWLER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:brawler"), "brawler");

    public static ModelLayerLocation NCOLDSNAPSTABBER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:nstabber"), "nstabber");
    public static ModelLayerLocation NCOLDSNAPGUNNER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:ngunner"), "ngunner");
    public static ModelLayerLocation NCOLDSNAPBRAWLER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:nbrawler"), "nbrawler");
    public static ModelLayerLocation NCOLDSNAPSNOWBALLER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:nsnowballer"), "nsnowballer");
    public static ModelLayerLocation NCOLDSNAPGIFTER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:ngifter"), "ngifter");
    public static ModelLayerLocation NCOLDSNAPZAPPER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:nzapper"), "nzapper");

    public static ModelLayerLocation ECOLDSNAPSNOWBALLER = new ModelLayerLocation( new ResourceLocation("coldsnaphorde:esnowballer"), "esnowballer");
    public static ModelLayerLocation TOPHAT = new ModelLayerLocation(new ResourceLocation("coldsnaphorde:tophat"), "main");



    @Override
    public void onInitializeClient() {
        if(!ColdSnapHorde.config.coldSnapClientSettings.OLD_SNOW) {
            EntityRendererRegistry.register(Register.COLDSNAPSTABBER, RenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.COLDSNAPGUNNER, RenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.COLDSNAPSNOWBALLER, RenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.COLDSNAPGIFTER, RenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.COLDSNAPZAPPER, RenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.COLDSNAPBRAWLER, RenderColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.NCOLDSNAPSTABBER, RenderNetherColdSnapStabber::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPGUNNER, RenderNetherColdSnapGunner::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPSNOWBALLER, RenderNetherColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPGIFTER, RenderNetherColdSnapGifter::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPZAPPER, RenderNetherColdSnapZapper::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPBRAWLER, RenderNetherColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.ECOLDSNAPSTABBER, RenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPGUNNER, RenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPSNOWBALLER, RenderEndColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPGIFTER, RenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPZAPPER, RenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPBRAWLER, RenderColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.PCOLDSNAPSTABBER, RenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPGUNNER, RenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPSNOWBALLER, RenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPGIFTER, RenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPZAPPER, RenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPBRAWLER, RenderColdSnapBrawler::new);
        } else {
            EntityRendererRegistry.register(Register.COLDSNAPSTABBER, OldRenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.COLDSNAPGUNNER, OldRenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.COLDSNAPSNOWBALLER, OldRenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.COLDSNAPGIFTER, OldRenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.COLDSNAPZAPPER, OldRenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.COLDSNAPBRAWLER, OldRenderColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.NCOLDSNAPSTABBER, OldRenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPGUNNER, OldRenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPSNOWBALLER, OldRenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPGIFTER, OldRenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPZAPPER, OldRenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.NCOLDSNAPBRAWLER, OldRenderColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.ECOLDSNAPSTABBER, OldRenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPGUNNER, OldRenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPSNOWBALLER, OldRenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPGIFTER, OldRenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPZAPPER, OldRenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.ECOLDSNAPBRAWLER, OldRenderColdSnapBrawler::new);

            EntityRendererRegistry.register(Register.PCOLDSNAPSTABBER, OldRenderColdSnapStabber::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPGUNNER, OldRenderColdSnapGunner::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPSNOWBALLER, OldRenderColdSnapSnowballer::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPGIFTER, OldRenderColdSnapGifter::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPZAPPER, OldRenderColdSnapZapper::new);
            EntityRendererRegistry.register(Register.PCOLDSNAPBRAWLER, OldRenderColdSnapBrawler::new);
        }

        EntityRendererRegistry.register(Register.COLDSNAPCOW, RenderColdSnapCow::new);
        EntityRendererRegistry.register(Register.GUNNERPROJECTILE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(Register.LIGHTNINGSNOWBALLPROJECTILE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(Register.ROCKSNOWBALLPROJECTILE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(Register.SNOWIERSNOWBALLPROJECTILE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(Register.THROWNCHORUSPROJECTILE, ThrownItemRenderer::new);


        EntityModelLayerRegistry.registerModelLayer(COLDSNAPSTABBER, ColdSnapStabberModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(COLDSNAPGUNNER, ColdSnapGunnerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(COLDSNAPSNOWBALLER, ColdSnapSnowballerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(COLDSNAPGIFTER, ColdSnapGifterModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(COLDSNAPZAPPER, ColdSnapZapperModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(COLDSNAPBRAWLER, ColdSnapBrawlerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ECOLDSNAPSNOWBALLER, EndColdSnapSnowballerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPSTABBER, NetherColdSnapStabberModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPGUNNER, NetherColdSnapGunnerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPSNOWBALLER, NetherColdSnapSnowballerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPGIFTER, NetherColdSnapGifterModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPZAPPER, NetherColdSnapZapperModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(NCOLDSNAPBRAWLER, NetherColdSnapBrawlerModel::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(OldColdSnapStabberModel.LAYER_LOCATION, OldColdSnapStabberModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OldColdSnapGunnerModel.OLDCOLDSNAPLAYER, OldColdSnapGunnerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OldColdSnapSnowballerModel.LAYER_LOCATION, OldColdSnapSnowballerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OldColdSnapGifterModel.LAYER_LOCATION, OldColdSnapGifterModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OldColdSnapZapperModel.LAYER_LOCATION, OldColdSnapZapperModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(OldColdSnapBrawler.OLDBRAWLERLAYER, OldColdSnapBrawler::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(TOPHAT, TopHatModel::createLayer);

        ArmorRenderer.register(new TopHatRenderer(), Register.TOPHAT);
        ArmorRenderer.register(new TopHatRenderer(), Register.REDTOPHAT);
        ArmorRenderer.register(new TopHatRenderer(), Register.BLUETOPHAT);
        ArmorRenderer.register(new TopHatRenderer(), Register.GREENTOPHAT);
        ArmorRenderer.register(new TopHatRenderer(), Register.PURPLETOPHAT);



    }

//    public static void AddToPlayerModels(Map<String, EntityRenderer<? extends Player>> map){
//        for(Map.Entry<String, EntityRenderer<? extends Player>> entry : map.entrySet()){
//            if(entry.getValue() instanceof LivingEntityRenderer livingEntityRenderer){
//                livingEntityRenderer.addLayer(new TopHatLayer(livingEntityRenderer));
//            }
//        }
//    }
}
