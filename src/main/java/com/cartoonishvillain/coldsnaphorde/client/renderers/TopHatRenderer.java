package com.cartoonishvillain.coldsnaphorde.client.renderers;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.client.models.standardmodel.TopHatModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import static com.cartoonishvillain.coldsnaphorde.client.ColdSnapClientInitializer.TOPHAT;

public class TopHatRenderer implements ArmorRenderer {
    private HumanoidModel<LivingEntity> topHatModel;
    private static final ResourceLocation TEXTURE = new ResourceLocation(ColdSnapHorde.MOD_ID, "textures/armor/tophatmodel.png");
    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
            if(topHatModel == null){
                topHatModel = new TopHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(TOPHAT));
            }
            contextModel.copyPropertiesTo(topHatModel);
            topHatModel.setAllVisible(false);
            topHatModel.head.visible = slot == EquipmentSlot.HEAD;
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, topHatModel, TEXTURE);
    }
}
