package com.cartoonishvillain.coldsnaphorde.client.renderers.netherrenders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.models.nethermodel.NetherColdSnapZapperModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapZapper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.cartoonishvillain.coldsnaphorde.client.ColdSnapClientInitializer.NCOLDSNAPZAPPER;

public class RenderNetherColdSnapZapper extends MobRenderer<ColdSnapZapper, NetherColdSnapZapperModel<ColdSnapZapper>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/ncoldsnapzapper.png");


    public RenderNetherColdSnapZapper(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new NetherColdSnapZapperModel<>(p_174304_.bakeLayer(NCOLDSNAPZAPPER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapZapper entity) {
        return TEXTURE;
    }
}
