package com.cartoonishvillain.coldsnaphorde.client.renderers.standardrenders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.models.standardmodel.ColdSnapSnowballerModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapSnowballer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.cartoonishvillain.coldsnaphorde.client.ColdSnapClientInitializer.COLDSNAPSNOWBALLER;

public class RenderColdSnapSnowballer extends MobRenderer<ColdSnapSnowballer, ColdSnapSnowballerModel<ColdSnapSnowballer>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/coldsnapsnowballer.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/pcoldsnapsnowballer.png");

    public RenderColdSnapSnowballer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new ColdSnapSnowballerModel<>(p_174304_.bakeLayer(COLDSNAPSNOWBALLER)), 0.5f);
    }

//    public RenderNetherColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapSnowballer entity) {
        if(entity.getHordeVariant() == 3) {return PTEXTURE;}
        else return TEXTURE;
    }
}
