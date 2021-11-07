package com.cartoonishvillain.coldsnaphorde.client.renderers.netherrenders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.models.nethermodel.NetherColdSnapSnowballerModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapSnowballer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.cartoonishvillain.coldsnaphorde.client.ColdSnapClientInitializer.NCOLDSNAPSNOWBALLER;

public class RenderNetherColdSnapSnowballer extends MobRenderer<ColdSnapSnowballer, NetherColdSnapSnowballerModel<ColdSnapSnowballer>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/ncoldsnapsnowballer.png");

    public RenderNetherColdSnapSnowballer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new NetherColdSnapSnowballerModel<>(p_174304_.bakeLayer(NCOLDSNAPSNOWBALLER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapSnowballer entity) {
        return TEXTURE;
    }
}
