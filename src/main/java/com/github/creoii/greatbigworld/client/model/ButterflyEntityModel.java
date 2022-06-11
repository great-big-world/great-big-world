package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.ButterflyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.TintableCompositeModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ButterflyEntityModel extends TintableCompositeModel<ButterflyEntity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public ButterflyEntityModel(ModelPart root) {
		this.root = root;
		body = root.getChild("body");
		rightWing = body.getChild("right_wing");
		leftWing = body.getChild("left_wing");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();
		ModelPartData head = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 10.0F), ModelTransform.NONE);
		head.addChild("left_wing", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.0F, -8.0F, 8.0F, 0.0F, 16.0F), ModelTransform.NONE);
		head.addChild("right_wing", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -1.0F, -8.0F, 8.0F, 0.0F, 16.0F), ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 16);
	}

	public ModelPart getPart() {
		return this.root;
	}

	@Override
	public void setAngles(ButterflyEntity butterflyEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		if (butterflyEntity.isSitting()) {
			this.rightWing.setPivot(-3f, 0f, 3f);
			this.leftWing.setPivot(3f, 0f, 3f);
			this.body.pitch = 3.1415927f;
			this.rightWing.pitch = -.15707964f;
			this.rightWing.yaw = -1.2566371f;
			leftWing.pitch = rightWing.pitch;
			leftWing.yaw = -rightWing.yaw;
		} else {
			this.rightWing.setPivot(0f, 0f, 0f);
			this.leftWing.setPivot(0f, 0f, 0f);
			this.body.pitch = 0f;
			this.body.yaw = .7853982f + MathHelper.cos(animationProgress * .1f) * .15f;
			rightWing.roll = MathHelper.cos(animationProgress * 74.48451f * .017453292f) * 3.1415927f * .25f;
			leftWing.roll = -rightWing.roll;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}