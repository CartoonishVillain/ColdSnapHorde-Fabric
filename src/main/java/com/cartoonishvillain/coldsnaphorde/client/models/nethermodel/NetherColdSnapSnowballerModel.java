package com.cartoonishvillain.coldsnaphorde.client.models.nethermodel;
// Made with Blockbench 4.0.0-beta.0
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class NetherColdSnapSnowballerModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nethercoldsnapsnowballermodel"), "main");
    private final ModelPart lowbody;
    private final ModelPart upper;

    public NetherColdSnapSnowballerModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.upper = root.getChild("upper");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -11.5F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 104).addBox(-6.0F, -11.55F, -0.5F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(50, 36).addBox(-6.0F, -9.7F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition upper = partdefinition.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 14.8F, -1.0F, -0.0436F, 0.0F, 0.3054F));

        PartDefinition head = upper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(3.0F, -9.4F, -0.2F, 0.3491F, 0.0F, -0.3491F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -1.5F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(31, 11).addBox(-4.0F, -6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition body = upper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(3.0F, 0.5F, 1.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 61).addBox(-8.0F, -13.5F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-2.5F)), PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, 0.48F, 0.0F, -0.48F));

        PartDefinition left_hand = body.addOrReplaceChild("left_hand", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, -6.8F, 0.0F, 0.0F, 1.0472F, 0.7854F));

        PartDefinition left_hand_r1 = left_hand.addOrReplaceChild("left_hand_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-0.8F, -0.5F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0121F, 0.005F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition right_hand = body.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-12.7121F, 5.505F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-4.5F, -6.8F, 0.0F, 0.0F, 0.0F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lowbody.render(poseStack, buffer, packedLight, packedOverlay);
        upper.render(poseStack, buffer, packedLight, packedOverlay);
    }
}