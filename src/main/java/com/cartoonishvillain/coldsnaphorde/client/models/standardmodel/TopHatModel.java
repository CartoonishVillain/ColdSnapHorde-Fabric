package com.cartoonishvillain.coldsnaphorde.client.models.standardmodel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class TopHatModel<T extends LivingEntity> extends EntityModel<T> {
    private final ModelPart hat;
    private static final String HAT = "hat";

    TopHatModel(ModelPart part){
        super(RenderType::entityCutoutNoCull);
        hat = part.getChild(HAT);
        hat.setPos(0, -7,0);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubeDeformation = new CubeDeformation(-0.5f);
        partdefinition.addOrReplaceChild(HAT, CubeListBuilder.create()
                .texOffs(0, 12).addBox(-4.0F, -6.125F, -4.0F, 8.0F, 6.0F, 8.0F, cubeDeformation)
                .texOffs(0,0).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 2.0F, 10.0F, cubeDeformation), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T p_102618_, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.hat.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.hat.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        hat.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
