package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.client.animation.MooseAnimations;
import com.github.creoii.greatbigworld.entity.MooseEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MooseEntityModel<T extends MooseEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;

	public MooseEntityModel(ModelPart root) {
		this.root = root.getChild("root");
		body = this.root.getChild("body");
		head = body.getChild("head");
		leftHindLeg = this.root.getChild("left_hind_leg");
		rightHindLeg = this.root.getChild("right_hind_leg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		root.addChild("left_hind_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-1.99F, -2.0F, -1.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -16.0F, 11.0F));
		root.addChild("right_hind_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -2.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -16.0F, 11.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -14.0F, -12.0F, 14.0F, 16.0F, 16.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-8.0F, -12.0F, 4.0F, 14.0F, 14.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -18.0F, -3.0F));
		body.addChild("right_front_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -6.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 2.0F, -3.0F));
		body.addChild("left_front_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-1.99F, -2.0F, -2.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 2.0F, -7.0F));
		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(96, 30).cuboid(-3.0F, -2.0F, -20.0F, 6.0F, 8.0F, 10.0F, new Dilation(0.0F))
				.uv(60, 0).cuboid(-5.0F, -4.0F, -10.0F, 10.0F, 10.0F, 12.0F, new Dilation(0.0F))
				.uv(60, 40).cuboid(-4.0F, 6.0F, -7.99F, 8.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -14.0F, -14.0F));
		head.addChild("left_antler", ModelPartBuilder.create().uv(50, 22).mirrored().cuboid(-1.5F, -10.01F, -10.0F, 16.0F, 8.0F, 10.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.5F, -2.0F, 2.0F));
		head.addChild("right_antler", ModelPartBuilder.create().uv(50, 22).cuboid(-14.5F, -10.01F, -10.0F, 16.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -2.0F, 2.0F));

		return TexturedModelData.of(modelData, 128, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		getPart().traverse().forEach(ModelPart::resetTransform);
		updateAnimation(entity.walkingAnimationState, MooseAnimations.WALKING, ageInTicks, Math.min((float)entity.getVelocity().lengthSquared() * 200f, 8f));
		updateAnimation(entity.swimmingAnimationState, MooseAnimations.SWIMMING, ageInTicks);
		updateAnimation(entity.idlingInWaterAnimationState, MooseAnimations.IDLING_IN_WATER, ageInTicks);
		head.getChild("left_antler").visible = entity.hasLeftAntler();
		head.getChild("right_antler").visible = entity.hasRightAntler();
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		if (child) {
			matrices.push();
			matrices.scale(.5f, .5f, .5f);
			matrices.translate(0d, 3d, 0d);
			body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
			leftHindLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
			rightHindLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
			matrices.pop();
		} else super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}