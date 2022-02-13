package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_renders;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model.OldColdSnapGunnerModel;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.ColdSnapGunner;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.EndHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.NetherHorde;
import com.cartoonishvillain.coldsnaphorde.entities.mobs.hordevariantmanager.PlagueHorde;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OldRenderColdSnapGunner extends MobRenderer<ColdSnapGunner, OldColdSnapGunnerModel<ColdSnapGunner>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldcoldsnapgunner.png");
    protected static final ResourceLocation FTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldflamingcoldsnapgunner.png");
    protected static final ResourceLocation ETEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldendercoldsnapgunner.png");
    protected static final ResourceLocation PTEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/entity/old/oldplaguecoldsnapgunner.png");

    public OldRenderColdSnapGunner(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new OldColdSnapGunnerModel<>(p_174304_.bakeLayer(OldColdSnapGunnerModel.OLDCOLDSNAPLAYER)), 0.5F);
    }

//    public RenderColdSnapGunner(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new ColdSnapGunnerModel<>(), 0.5F);
//    }

    @Override
    public ResourceLocation getTextureLocation(ColdSnapGunner entity) {
        if(entity instanceof EndHorde.EndGunner) {
            return ETEXTURE;
        } else if (entity instanceof NetherHorde.NetherGunner) {
            return FTEXTURE;
        } else if (entity instanceof PlagueHorde.PlagueGunner) {
            return PTEXTURE;
        } else return TEXTURE;
    }
}
