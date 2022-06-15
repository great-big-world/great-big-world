package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.BearEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

@Environment(EnvType.CLIENT)
public class BearEntityModel<T extends BearEntity> extends QuadrupedEntityModel<T> {

	public BearEntityModel(ModelPart root) {
		super(root, false, 10f, 4f, 2f, 2f, 24);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, 0F, 0.0F, 12.0F, 12.0F, 12.0F), ModelTransform.NONE);
		root.addChild("body_midsection", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -17.0F, -10.0F, 10.0F, 11.0F, 10.0F), ModelTransform.NONE);
		root.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -14.0F, 12.0F, 4.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-10.0F, -16.0F, -16.0F, 8.0F, 8.0F, 6.0F), ModelTransform.NONE);
		root.addChild("nose", ModelPartBuilder.create().uv(0, 0).cuboid(-8.5F, -12.0F, -19.0F, 5.0F, 3.0F, 3.0F), ModelTransform.NONE);
		root.addChild("right_ear", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -17.0F, -13.0F, 2.0F, 2.0F, 1.0F), ModelTransform.NONE);
		root.addChild("left_ear", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -17.0F, -13.0F, 2.0F, 2.0F, 1.0F), ModelTransform.NONE);

		root.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -6.0F, 6.0F, 4.0F, 6.0F, 4.0F), ModelTransform.NONE);
		root.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, -6.0F, 6.0F, 4.0F, 6.0F, 4.0F), ModelTransform.NONE);
		root.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F), ModelTransform.NONE);
		root.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F), ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}