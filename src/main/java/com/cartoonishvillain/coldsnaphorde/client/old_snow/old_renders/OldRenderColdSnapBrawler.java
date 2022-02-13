package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapBrawler;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapBrawler;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapBrawler extends MobRenderer<ColdSnapBrawler, OldColdSnapBrawler<ColdSnapBrawler>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapbrawler.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapbrawler.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapbrawler.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapbrawler.png");

    public OldRenderColdSnapBrawler(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OldColdSnapBrawler<>(p_174304_.bakeLayer(OldColdSnapBrawler.OLDBRAWLERLAYER)), 0.5f);
    }

//    public RenderColdSnapBrawler(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapBrawlerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapBrawler entity) {
        if(entity instanceof EndHorde.EndBrawler) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherBrawler) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueBrawler) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
