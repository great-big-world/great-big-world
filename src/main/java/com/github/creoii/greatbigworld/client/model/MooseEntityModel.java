package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class MooseEntityModel<T extends MooseEntity> extends QuadrupedEntityModel<T> {
	private final ModelPart main;

	public MooseEntityModel(ModelPart root) {
		super(root, false, 10f, 4f, 2f, 2f, 24);
		main = root.getChild("main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.NONE);

		modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(58, 12).cuboid(-7.0F, -14.0F, 14.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(58, 12).cuboid(8.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 10.0F, 16.0F));
		modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(58, 12).cuboid(-7.0F, -14.0F, -8.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(58, 12).cuboid(3.0F, -14.0F, -8.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		modelPartData.addChild("head", ModelPartBuilder.create().uv(20, 0).cuboid(1.0F, -21.0F, 0.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(20, 0).mirrored().cuboid(-5.0F, -21.0F, 0.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 16).cuboid(-3.0F, -12.0F, -16.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F))
				.uv(58, 0).cuboid(1.0F, -26.0F, 2.0F, 16.0F, 12.0F, 0.0F, new Dilation(0.0F))
				.uv(58, 0).mirrored().cuboid(-17.0F, -26.0F, 2.0F, 16.0F, 12.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 46).cuboid(-5.0F, -18.0F, -10.0F, 10.0F, 10.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -8.0F));

		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -10.0F, -12.0F, 14.0F, 16.0F, 30.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 6.0F, 2.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		float k = entity.getHeadPitch();
		if (k != 0f) head.pitch = k;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		super.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}