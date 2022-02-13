package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapStabberModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapStabber;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapStabber extends MobRenderer<ColdSnapStabber, OldColdSnapStabberModel<ColdSnapStabber>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapstabber.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapstabber.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapstabber.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapstabber.png");

    public OldRenderColdSnapStabber(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OldColdSnapStabberModel<>(p_174304_.bakeLayer(OldColdSnapStabberModel.LAYER_LOCATION)), 0.65f);
    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapStabber entity) {
        if(entity instanceof EndHorde.EndStabber) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherStabber) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueStabber) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
