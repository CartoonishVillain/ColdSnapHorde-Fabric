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

public class NetherColdSnapStabberModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nethercoldsnapstabbermodel"), "main");
    private final ModelPart lowbody;
    private final ModelPart bone2;

    public NetherColdSnapStabberModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.bone2 = root.getChild("bone2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(53, 95).addBox(-6.0F, -12.0F, -0.5F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(62, 36).addBox(-6.0F, -10.17F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 14.4F, 0.0F, 0.1222F, 0.0F, -0.1745F));

        PartDefinition body = bone2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 96).addBox(-2.9F, -4.3F, -2.9F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 80).addBox(-2.9F, -11.3F, -2.9F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(32, 96).addBox(1.48F, -11.3F, 0.6F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition head = bone2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 60).addBox(-4.9F, -2.7F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.7F)), PartPose.offsetAndRotation(0.0F, -9.7F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -1.5F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(63, 1).addBox(-5.0F, -0.5F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(31, 11).addBox(-4.0F, -6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.49F)), PartPose.offsetAndRotation(0.0F, -6.1F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition left_hand = bone2.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-0.6105F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(3.3F, -9.1F, -1.0F, 0.0F, 0.2182F, 1.1345F));

        PartDefinition right_hand = bone2.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-11.3895F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -6.8F, 0.0F, 0.0F, -0.6109F, 1.4835F));

        PartDefinition bone = right_hand.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.1F, 1.0F, -0.2F, -3.1416F, 0.4363F, 3.1416F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 35).addBox(-12.0F, 0.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
                .texOffs(50, 47).addBox(-12.0F, -0.4F, 6.6F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
                .texOffs(50, 43).addBox(-11.1F, -0.4F, 2.8F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
                .texOffs(44, 43).addBox(-12.0F, -0.4F, 2.8F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(11.0F, 0.0F, 1.0F, 3.1416F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lowbody.render(poseStack, buffer, packedLight, packedOverlay);
        bone2.render(poseStack, buffer, packedLight, packedOverlay);
    }
}