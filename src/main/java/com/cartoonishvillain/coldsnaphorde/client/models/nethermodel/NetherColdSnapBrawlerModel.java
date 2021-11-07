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

public class NetherColdSnapBrawlerModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nethercoldsnapbrawlermodel"), "main");
    private final ModelPart lowbody;
    private final ModelPart bone;

    public NetherColdSnapBrawlerModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(51, 90).addBox(-6.0F, -12.0F, 2.25F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 97).addBox(-6.0F, -10.17F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 14.4F, 0.0F, 0.1043F, -0.092F, 0.3041F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition left_hand = bone.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, -0.5F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(4.5F, -6.7F, 0.0F, -0.8198F, 0.9435F, -0.3915F));

        PartDefinition lglove = left_hand.addOrReplaceChild("lglove", CubeListBuilder.create().texOffs(48, 48).addBox(-0.5F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.5F))
                .texOffs(48, 55).addBox(-1.3F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(7.2F, 0.857F, -0.075F, 2.5703F, 0.4259F, 0.5713F));

        PartDefinition cube_r1 = lglove.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -1.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.8F, 0.1046F, 1.6954F, 0.1719F, -0.7703F, -0.2444F));

        PartDefinition right_hand = bone.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-11.5F, -0.5F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -7.3F, 1.3F, -0.4799F, -0.8743F, -0.0509F));

        PartDefinition lglove2 = right_hand.addOrReplaceChild("lglove2", CubeListBuilder.create().texOffs(48, 48).mirror().addBox(-4.5F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.5F)).mirror(false)
                .texOffs(48, 55).mirror().addBox(-3.7F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(-7.2F, 0.857F, -0.075F, 2.5394F, -0.3776F, -0.4923F));

        PartDefinition cube_r2 = lglove2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -0.5F, -1.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-0.8F, 0.1046F, 1.6954F, 0.1719F, 0.7703F, 0.2444F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 60).addBox(-4.0F, -8.4F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -8.5F, 0.0F, -0.2164F, -0.0283F, -0.2151F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -1.5F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(31, 11).addBox(-4.05F, -6.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.48F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.1744F, 0.0076F, 0.043F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lowbody.render(poseStack, buffer, packedLight, packedOverlay);
        bone.render(poseStack, buffer, packedLight, packedOverlay);
    }
}