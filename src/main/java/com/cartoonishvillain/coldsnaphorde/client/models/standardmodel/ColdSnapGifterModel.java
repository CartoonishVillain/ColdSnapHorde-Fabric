package com.cartoonishvillain.coldsnaphorde.client.models.standardmodel;
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

public class ColdSnapGifterModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "coldsnapgiftermodel"), "main");
    private final ModelPart lowbody;
    private final ModelPart body;
    private final ModelPart right_hand;
    private final ModelPart left_hand;
    private final ModelPart head;
    private final ModelPart present;

    public ColdSnapGifterModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.body = root.getChild("body");
        this.right_hand = root.getChild("right_hand");
        this.left_hand = root.getChild("left_hand");
        this.head = root.getChild("head");
        this.present = root.getChild("present");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition right_hand = partdefinition.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(48, 11).addBox(-11.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-5.0F, 6.0F, 0.0F, 0.0F, -1.5272F, 0.0F));

        PartDefinition left_hand = partdefinition.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(48, 11).mirror().addBox(-1.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(5.0F, 6.0F, 0.0F, 0.0F, 1.5272F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 0).addBox(-0.95F, -4.05F, -4.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F))
                .texOffs(4, 0).addBox(-0.95F, -4.05F, -5.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(30, 34).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 44).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition present = partdefinition.addOrReplaceChild("present", CubeListBuilder.create().texOffs(32, 46).addBox(-5.0F, -3.0F, -3.0F, 11.0F, 3.0F, 5.0F, new CubeDeformation(-0.1F))
                .texOffs(36, 0).addBox(-6.0F, -5.0F, -4.0F, 13.0F, 4.0F, 7.0F, new CubeDeformation(-0.6F)), PartPose.offset(-0.45F, 7.0F, -10.0F));

        PartDefinition bow2_r1 = present.addOrReplaceChild("bow2_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -4.4F, -0.5F, 0.0F, -0.7418F, 0.0F));

        PartDefinition bow1_r1 = present.addOrReplaceChild("bow1_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -4.4F, -0.5F, 0.0F, 0.7418F, 0.0F));

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
        right_hand.render(poseStack, buffer, packedLight, packedOverlay);
        left_hand.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
        present.render(poseStack, buffer, packedLight, packedOverlay);
    }
}