package com.cartoonishvillain.coldsnaphorde.client.models.standardmodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class TopHatModel extends HumanoidModel<LivingEntity> {
    private static final String HAT = "tophat";

    public TopHatModel(ModelPart part){
        super(part);
//        hat = part.getChild(HAT);
//        hat.setPos(0, -7,0);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = createMesh(CubeDeformation.NONE, 0);
        PartDefinition headDefinition = meshdefinition.getRoot().getChild(PartNames.HEAD);
        CubeDeformation cubeDeformation = new CubeDeformation(-0.5f);
        headDefinition.addOrReplaceChild(HAT, CubeListBuilder.create()
                .texOffs(0, 36).addBox(-4.0F, -6.125F, -4.0F, 8.0F, 6.0F, 8.0F, cubeDeformation)
                .texOffs(0,24).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 2.0F, 10.0F, cubeDeformation), PartPose.offset(0, -7, 0));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
//
//    @Override
//    public void setupAnim(LivingEntity p_102618_, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.hat.yRot = netHeadYaw * ((float)Math.PI / 180F);
//        this.hat.xRot = headPitch * ((float)Math.PI / 180F);
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        hat.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
}
