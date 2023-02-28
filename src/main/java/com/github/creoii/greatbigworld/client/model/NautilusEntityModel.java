package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.NautilusEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class NautilusEntityModel<T extends NautilusEntity> extends SinglePartEntityModel<T> {
	private final ModelPart shell;
	private final ModelPart head;
	private final ModelPart tentacle1;
	private final ModelPart tentacle2;
	private final ModelPart tentacle3;
	private final ModelPart tentacle4;

	public NautilusEntityModel(ModelPart root) {
		shell = root.getChild("shell");
		head = shell.getChild("head");
		tentacle1 = head.getChild("tentacle1");
		tentacle2 = head.getChild("tentacle2");
		tentacle3 = head.getChild("tentacle3");
		tentacle4 = head.getChild("tentacle4");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData shell = modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.5F, -0.25F, 6.0F, 12.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 19).cuboid(-3.0F, -4.5F, -6.25F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.5F, 1.25F));

		ModelPartData head = shell.addChild("head", ModelPartBuilder.create().uv(18, 10).cuboid(-4.0F, -3.0F, -5.5F, 8.0F, 1.0F, 9.0F, new Dilation(0.0F))
		.uv(24, 20).cuboid(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -2.75F));

		head.addChild("tentacle1", ModelPartBuilder.create().uv(19, -5).cuboid(0.0F, -2.5F, -5.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 0.0F, -2.5F));
		head.addChild("tentacle2", ModelPartBuilder.create().uv(19, -5).cuboid(0.0F, -2.5F, -5.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.0F, -2.5F));
		head.addChild("tentacle3", ModelPartBuilder.create().uv(24, 0).cuboid(-2.5F, 0.0F, -5.0F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, -2.5F));
		head.addChild("tentacle4", ModelPartBuilder.create().uv(24, 0).cuboid(-2.5F, 0.0F, -5.0F, 5.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.5F, -2.5F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(NautilusEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		//animations
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		shell.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return shell;
	}
}