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

public class NetherColdSnapGifterModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nethercoldsnapgiftermodel"), "main");
    private final ModelPart bone4;

    public NetherColdSnapGifterModel(ModelPart root) {
        this.bone4 = root.getChild("bone4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 24.5F, 0.0F));

        PartDefinition bone2 = bone4.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.1F, 0.0F, 0.2559F, 0.056F, -0.1237F));

        PartDefinition head = bone2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -7.3F, -1.5F, -0.6545F, 0.0F, 0.0F));

        PartDefinition bone3 = head.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.95F, -4.05F, -4.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F))
                .texOffs(4, 0).addBox(-0.95F, -4.05F, -5.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(1.0F, -1.2F, 0.6F, 0.2355F, 0.3829F, 0.0894F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(36, 0).addBox(-6.0F, -3.4F, -4.0F, 13.0F, 4.0F, 7.0F, new CubeDeformation(-0.6F)), PartPose.offsetAndRotation(-0.45F, -7.2F, -0.5F, -0.0859F, 0.0151F, 0.1739F));

        PartDefinition bow1_r1 = bone.addOrReplaceChild("bow1_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -2.8F, -0.5F, 0.0F, 0.7418F, 0.0F));

        PartDefinition bow2_r1 = bone.addOrReplaceChild("bow2_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 4.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -2.8F, -0.5F, 0.0F, -0.7418F, 0.0F));

        PartDefinition left_hand = bone2.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(48, 11).mirror().addBox(-0.9782F, -0.5F, -0.5005F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(4.5F, -6.0F, 0.0F, 0.662F, 1.1366F, 0.6271F));

        PartDefinition present = left_hand.addOrReplaceChild("present", CubeListBuilder.create(), PartPose.offset(9.8718F, 0.1F, 0.9995F));

        PartDefinition base_r1 = present.addOrReplaceChild("base_r1", CubeListBuilder.create().texOffs(32, 46).addBox(-5.6282F, -2.903F, -2.5F, 11.0F, 3.0F, 5.0F, new CubeDeformation(-0.099F)), PartPose.offsetAndRotation(0.6282F, -0.1F, -0.5F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_hand = bone2.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(48, 11).addBox(-8.9F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-3.1F, -4.8F, 0.9F, 0.2943F, -0.6534F, -0.3822F));

        PartDefinition body = bone2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition hat = bone4.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(30, 34).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 44).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -13.1F, -3.0F, -1.3111F, 0.0338F, -0.1265F));

        PartDefinition lowbody = bone4.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(12, 72).addBox(-6.0F, -10.17F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(51, 92).addBox(-6.0F, -12.0F, 0.4F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 104).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone4.render(poseStack, buffer, packedLight, packedOverlay);
    }
}