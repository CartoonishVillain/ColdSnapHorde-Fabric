package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapZapperModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapZapper;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapZapper extends MobRenderer<ColdSnapZapper, OldColdSnapZapperModel<ColdSnapZapper>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapzapper.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapzapper.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapzapper.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapzapper.png");

    public OldRenderColdSnapZapper(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OldColdSnapZapperModel<>(p_174304_.bakeLayer(OldColdSnapZapperModel.LAYER_LOCATION)), 0.5f);
    }

//    public RenderColdSnapZapper(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapZapperModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapZapper entity) {
        if(entity instanceof EndHorde.EndZapper) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherZapper) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueZapper) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
