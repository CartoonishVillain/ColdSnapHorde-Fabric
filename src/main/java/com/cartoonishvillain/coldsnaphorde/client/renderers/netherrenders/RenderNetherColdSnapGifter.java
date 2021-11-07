package com.cartoonishvillain.coldsnaphorde.client.renderers.netherrenders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.models.nethermodel.NetherColdSnapGifterModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapGifter;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.cartoonishvillain.coldsnaphorde.client.ColdSnapClientInitializer.NCOLDSNAPGIFTER;


public class RenderNetherColdSnapGifter extends MobRenderer<ColdSnapGifter, NetherColdSnapGifterModel<ColdSnapGifter>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/ncoldsnapgifter.png");

    public RenderNetherColdSnapGifter(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new NetherColdSnapGifterModel<>(p_174304_.bakeLayer(NCOLDSNAPGIFTER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapGifter entity) {
        return TEXTURE;
    }
}
