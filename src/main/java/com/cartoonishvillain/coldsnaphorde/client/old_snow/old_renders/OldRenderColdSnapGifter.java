package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapGifterModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapGifter;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapGifter extends MobRenderer<ColdSnapGifter, OldColdSnapGifterModel<ColdSnapGifter>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapgifter.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapgifter.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapgifter.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapgifter.png");

    public OldRenderColdSnapGifter(EntityRendererProvider.Context p_174304) {
        super(p_174304, new OldColdSnapGifterModel<>(p_174304.bakeLayer(OldColdSnapGifterModel.LAYER_LOCATION)), 0.5f);
    }

//    public RenderColdSnapGifter(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapGifterModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapGifter entity) {
        if(entity instanceof EndHorde.EndGifter) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherGifter) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueGifter) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
