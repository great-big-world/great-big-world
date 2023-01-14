package com.github.creoii.greatbigworld.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class WoodenMaskModel extends EntityModel<LivingEntity> {
	public final ModelPart mask;

	public WoodenMaskModel(ModelPart root) {
		mask = root.getChild("mask");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("mask", ModelPartBuilder.create().uv(0, 18).cuboid(-5.0F, -9.0F, -6.0F, 10.0F, 10.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-9.0F, -13.0F, -4.0F, 18.0F, 18.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(LivingEntity livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) { }

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		mask.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}