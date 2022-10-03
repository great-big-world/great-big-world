package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class MooseEntityModel<T extends MooseEntity> extends QuadrupedEntityModel<T> {
	private final ModelPart leftAntler;
	private final ModelPart rightAntler;

	public MooseEntityModel(ModelPart root) {
		super(root, true, 10f, 4f, 2f, 2f, 24);
		leftAntler = head.getChild("left_antler");
		rightAntler = head.getChild("right_antler");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.NONE);

		modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -2.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 8.0F, 11.0F));
		modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -1.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 8.0F, 11.0F));
		modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -6.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 8.0F, -6.0F));
		modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(104, 0).cuboid(-2.01F, -2.0F, -2.0F, 4.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 8.0F, -10.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(96, 30).cuboid(-4.0F, -2.0F, -20.0F, 8.0F, 8.0F, 10.0F, new Dilation(0.0F))
				.uv(60, 0).cuboid(-5.0F, -4.0F, -10.0F, 10.0F, 10.0F, 12.0F, new Dilation(0.0F))
				.uv(60, 40).cuboid(-6.0F, 6.0F, -8.0F, 10.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, -17.0F));
		head.addChild("left_antler", ModelPartBuilder.create().uv(50, 22).mirrored().cuboid(-1.5F, -10.01F, -10.0F, 16.0F, 8.0F, 10.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.5F, -2.0F, 2.0F));
		head.addChild("right_antler", ModelPartBuilder.create().uv(50, 22).cuboid(-14.5F, -10.01F, -10.0F, 16.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -2.0F, 2.0F));

		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -14.0F, -12.0F, 14.0F, 16.0F, 16.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-8.0F, -12.0F, 4.0F, 14.0F, 14.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 6.0F, -3.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}

	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float k = entity.getHeadPitch();
		if (k != 0f) head.pitch = k;
	}
}