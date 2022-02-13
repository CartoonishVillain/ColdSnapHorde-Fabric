package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapSnowballerModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapSnowballer;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapSnowballer extends MobRenderer<ColdSnapSnowballer, OldColdSnapSnowballerModel<ColdSnapSnowballer>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapsnowballer.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapsnowballer.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapsnowballer.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapsnowballer.png");

    public OldRenderColdSnapSnowballer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OldColdSnapSnowballerModel<>(p_174304_.bakeLayer(OldColdSnapSnowballerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapSnowballer entity) {
        if(entity instanceof EndHorde.EndSnowballer) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherSnowballer) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueSnowballer) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
