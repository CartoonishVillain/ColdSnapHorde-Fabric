package com.cartoonishvillain.coldsnaphorde.client.old_snow.old_model;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class OldColdSnapBrawler<T extends LivingEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation OLDBRAWLERLAYER = new ModelLayerLocation(new ResourceLocation("coldsnaphorde", "oldcoldsnapbrawler"), "main");
	private final ModelPart lowbody;
	private final ModelPart body;
	private final ModelPart left_hand;
	private final ModelPart right_hand;
	private final ModelPart head;
	private final ModelPart rglove;
	private final ModelPart lglove;

	public OldColdSnapBrawler(ModelPart root) {
		this.lowbody = root.getChild("lowbody");
		this.body = root.getChild("body");
		this.left_hand = root.getChild("left_hand");
		this.right_hand = root.getChild("right_hand");
		this.head = root.getChild("head");
		this.rglove = root.getChild("rglove");
		this.lglove = root.getChild("lglove");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition left_hand = partdefinition.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(5.0F, 6.0F, 0.0F, 0.0F, 1.4399F, -0.4363F));

		PartDefinition right_hand = partdefinition.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(32, 0).addBox(-11.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-5.0F, 6.0F, 0.0F, 0.0F, -1.4399F, 0.4363F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(63, 13).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
		.texOffs(31, 11).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rglove = partdefinition.addOrReplaceChild("rglove", CubeListBuilder.create().texOffs(56, 37).addBox(-0.5F, -1.375F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(56, 37).addBox(1.5F, -1.375F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(56, 37).addBox(0.5F, -1.375F, -2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-6.375F, 5.457F, -8.35F, -0.1745F, 1.5272F, -1.309F));

		PartDefinition cube_r1 = rglove.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 37).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(1.015F, 1.129F, -0.996F, -1.5708F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = rglove.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 37).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(1.015F, -0.871F, -0.996F, -1.5708F, 1.5708F, 0.0F));

		PartDefinition lglove = partdefinition.addOrReplaceChild("lglove", CubeListBuilder.create().texOffs(52, 27).addBox(-0.502F, -1.5336F, -1.0739F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(52, 27).addBox(1.498F, -1.5336F, -2.0739F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.5F))
		.texOffs(52, 27).addBox(0.498F, -1.5336F, -2.0739F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(6.1F, 5.557F, -7.975F, 0.0873F, 1.5272F, -1.8326F));

		PartDefinition cube_r3 = lglove.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 27).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(1.013F, 0.9704F, -1.0699F, -1.5708F, 1.5708F, 0.0F));

		PartDefinition cube_r4 = lglove.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(52, 27).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(1.013F, -1.0296F, -1.0699F, -1.5708F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lowbody.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		left_hand.render(poseStack, buffer, packedLight, packedOverlay);
		right_hand.render(poseStack, buffer, packedLight, packedOverlay);
		head.render(poseStack, buffer, packedLight, packedOverlay);
		rglove.render(poseStack, buffer, packedLight, packedOverlay);
		lglove.render(poseStack, buffer, packedLight, packedOverlay);
	}
}