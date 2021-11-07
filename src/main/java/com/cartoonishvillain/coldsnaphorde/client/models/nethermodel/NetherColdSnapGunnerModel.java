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

public class NetherColdSnapGunnerModel<T extends GenericHordeMember> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nethercoldsnapgunnermodel"), "main");
    private final ModelPart lowbody;
    private final ModelPart bone;

    public NetherColdSnapGunnerModel(ModelPart root) {
        this.lowbody = root.getChild("lowbody");
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 60).addBox(-6.0F, -12.0F, -1.45F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F))
                .texOffs(50, 36).addBox(-6.0F, -10.17F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 14.4F, 0.0F, -0.2937F, 0.3009F, -0.1827F));

        PartDefinition right_hand = bone.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-11.3895F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(-0.2F, -3.2F, 0.4F, -1.5137F, 0.1122F, 0.5334F));

        PartDefinition gun = right_hand.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(120, 124).addBox(-1.0F, -4.6F, -7.4F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.51F))
                .texOffs(103, 119).addBox(-1.0F, -5.0F, -6.9F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(94, 115).addBox(-1.0F, -5.0F, -4.5F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(114, 94).addBox(-1.0F, -2.0F, -4.5F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(114, 102).addBox(-1.0F, -2.75F, -2.1F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(112, 115).addBox(-0.5F, -3.5F, -6.1F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(72, 119).addBox(-0.5F, -6.5F, -3.7F, 2.0F, 4.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(120, 124).addBox(-1.0F, -3.4F, -7.4F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.51F)), PartPose.offsetAndRotation(-11.1F, -0.1F, 0.5F, 1.6281F, 0.2374F, -0.547F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -8.5F, 0.0F, 0.3143F, -0.2103F, 0.2279F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -1.5F, -8.5F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
                .texOffs(31, 11).addBox(-4.0F, -6.5F, -7.5F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.499F)), PartPose.offsetAndRotation(0.0F, -4.6F, 3.7F, -0.3491F, 0.0F, 0.0F));

        PartDefinition left_hand = bone.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-0.6105F, -0.5038F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(2.7F, -2.0F, 0.4F, -2.7293F, 0.3466F, -0.7453F));

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