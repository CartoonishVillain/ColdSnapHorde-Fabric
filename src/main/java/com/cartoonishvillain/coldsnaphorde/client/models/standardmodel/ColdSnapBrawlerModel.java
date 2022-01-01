package com.cartoonishvillain.coldsnaphorde.client.models.standardmodel;// Made with Blockbench 4.0.0-beta.0
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ColdSnapBrawlerModel<T extends LivingEntity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    //public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
    private final ModelPart lowbody;
    private final ModelPart body;
    private final ModelPart left_hand;
    private final ModelPart head;
    private final ModelPart right_hand;

    public ColdSnapBrawlerModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.body = root.getChild("body");
        this.left_hand = root.getChild("left_hand");
        this.head = root.getChild("head");
        this.right_hand = root.getChild("right_hand");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition left_hand = partdefinition.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, -0.5F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(4.5F, 5.8F, 0.0F, -1.1007F, 1.2321F, -0.6458F));

        PartDefinition lglove = left_hand.addOrReplaceChild("lglove", CubeListBuilder.create().texOffs(48, 48).addBox(-0.5F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.5F))
                .texOffs(48, 55).addBox(-1.3F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(7.2F, 0.857F, -0.075F, 2.4739F, 0.2217F, 0.2719F));

        PartDefinition cube_r1 = lglove.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -1.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.8F, 0.1046F, 1.6954F, 0.1719F, -0.7703F, -0.2444F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 60).addBox(-4.0F, -8.4F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(31, 11).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_hand = partdefinition.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-11.5F, -0.5F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 5.8F, 0.0F, -1.1007F, -1.2321F, 0.6458F));

        PartDefinition lglove2 = right_hand.addOrReplaceChild("lglove2", CubeListBuilder.create().texOffs(48, 48).mirror().addBox(-4.5F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.5F)).mirror(false)
                .texOffs(48, 55).mirror().addBox(-3.7F, -1.3954F, -1.8046F, 5.0F, 3.0F, 4.0F, new CubeDeformation(-0.6F)).mirror(false), PartPose.offsetAndRotation(-7.2F, 0.857F, -0.075F, 2.4739F, -0.2217F, -0.2719F));

        PartDefinition cube_r2 = lglove2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -0.5F, -1.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-0.8F, 0.1046F, 1.6954F, 0.1719F, 0.7703F, 0.2444F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F) * 0.25F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lowbody.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        left_hand.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        right_hand.render(poseStack, buffer, packedLight, packedOverlay);
    }
}