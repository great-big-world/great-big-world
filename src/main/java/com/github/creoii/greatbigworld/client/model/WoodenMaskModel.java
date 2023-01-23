package com.github.creoii.greatbigworld.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class WoodenMaskModel extends EntityModel<LivingEntity> {
	public final ModelPart mask;

	public WoodenMaskModel(ModelPart root) {
		mask = root.getChild("mask");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("mask", ModelPartBuilder.create().uv(0, 18).cuboid(-5f, -9f, -5f, 10f, 10f, 4f, Dilation.NONE)
				.uv(0, 0).cuboid(-9f, -13f, -3f, 18f, 18f, 0f, Dilation.NONE), ModelTransform.pivot(0f, 11f, 0f));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(LivingEntity livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) { }

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		mask.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}