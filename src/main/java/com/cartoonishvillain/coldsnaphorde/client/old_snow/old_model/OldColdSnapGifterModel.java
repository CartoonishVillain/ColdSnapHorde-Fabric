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
import net.minecraft.world.entity.Entity;

public class OldColdSnapGifterModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("coldsnaphorde", "oldcoldsnapgiftermodel"), "main");
	private final ModelPart lowbody;
	private final ModelPart body;
	private final ModelPart left_hand;
	private final ModelPart right_hand;
	private final ModelPart head;
	private final ModelPart present;

	public OldColdSnapGifterModel(ModelPart root) {
		this.lowbody = root.getChild("lowbody");
		this.body = root.getChild("body");
		this.left_hand = root.getChild("left_hand");
		this.right_hand = root.getChild("right_hand");
		this.head = root.getChild("head");
		this.present = root.getChild("present");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lowbody = partdefinition.addOrReplaceChild("lowbody", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition left_hand = partdefinition.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(48, 11).addBox(-1.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(5.0F, 6.0F, 0.0F, 0.0F, 1.4399F, 0.0F));

		PartDefinition right_hand = partdefinition.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(48, 11).addBox(-11.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-5.0F, 6.0F, 0.0F, 0.0F, -1.5272F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(30, 34).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.5F))
		.texOffs(0, 44).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition present = partdefinition.addOrReplaceChild("present", CubeListBuilder.create().texOffs(32, 46).addBox(-5.0F, -3.0F, -3.0F, 11.0F, 3.0F, 5.0F, new CubeDeformation(-0.05F))
		.texOffs(36, 0).addBox(-6.0F, -5.0F, -4.0F, 13.0F, 4.0F, 7.0F, new CubeDeformation(-0.6F)), PartPose.offset(-0.25F, 7.0F, -10.0F));

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
		present.render(poseStack, buffer, packedLight, packedOverlay);
	}
}