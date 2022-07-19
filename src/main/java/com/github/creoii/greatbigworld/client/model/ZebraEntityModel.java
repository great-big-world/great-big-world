package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.ZebraEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ZebraEntityModel<T extends ZebraEntity> extends AnimalModel<T> {
    protected final ModelPart body;
    protected final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindBabyLeg;
    private final ModelPart leftHindBabyLeg;
    private final ModelPart rightFrontBabyLeg;
    private final ModelPart leftFrontBabyLeg;
    private final ModelPart tail;
    private final ModelPart[] saddle;
    private final ModelPart[] straps;

    public ZebraEntityModel(ModelPart root) {
        super(true, 16.2F, 1.36F, 2.7272F, 2.0F, 20.0F);
        this.body = root.getChild("body");
        this.head = root.getChild("head_parts");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightHindBabyLeg = root.getChild("right_hind_baby_leg");
        this.leftHindBabyLeg = root.getChild("left_hind_baby_leg");
        this.rightFrontBabyLeg = root.getChild("right_front_baby_leg");
        this.leftFrontBabyLeg = root.getChild("left_front_baby_leg");
        this.tail = this.body.getChild("tail");
        ModelPart modelPart = this.body.getChild("saddle");
        ModelPart modelPart2 = this.head.getChild("left_saddle_mouth");
        ModelPart modelPart3 = this.head.getChild("right_saddle_mouth");
        ModelPart modelPart4 = this.head.getChild("left_saddle_line");
        ModelPart modelPart5 = this.head.getChild("right_saddle_line");
        ModelPart modelPart6 = this.head.getChild("head_saddle");
        ModelPart modelPart7 = this.head.getChild("mouth_saddle_wrap");
        this.saddle = new ModelPart[]{modelPart, modelPart2, modelPart3, modelPart6, modelPart7};
        this.straps = new ModelPart[]{modelPart4, modelPart5};
    }

    public static TexturedModelData getTexturedModelData() {
        Dilation dilation = new Dilation(0f, 5.5f, 0f);
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, 11.0F, 5.0F));
        ModelPartData modelPartData3 = modelPartData.addChild("head_parts", ModelPartBuilder.create().uv(0, 35).cuboid(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F), ModelTransform.of(0.0F, 4.0F, -12.0F, 0.5235988F, 0.0F, 0.0F));
        ModelPartData modelPartData4 = modelPartData3.addChild("head", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, dilation), ModelTransform.NONE);
        modelPartData3.addChild("mane", ModelPartBuilder.create().uv(56, 36).cuboid(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, dilation), ModelTransform.NONE);
        modelPartData3.addChild("upper_mouth", ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, dilation), ModelTransform.NONE);
        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(4.0F, 14.0F, 7.0F));
        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(-4.0F, 14.0F, 7.0F));
        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(4.0F, 14.0F, -12.0F));
        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(-4.0F, 14.0F, -12.0F));
        modelPartData.addChild("left_hind_baby_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(4.0F, 14.0F, 7.0F));
        modelPartData.addChild("right_hind_baby_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(-4.0F, 14.0F, 7.0F));
        modelPartData.addChild("left_front_baby_leg", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(4.0F, 14.0F, -12.0F));
        modelPartData.addChild("right_front_baby_leg", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation), ModelTransform.pivot(-4.0F, 14.0F, -12.0F));
        modelPartData2.addChild("tail", ModelPartBuilder.create().uv(42, 36).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, dilation), ModelTransform.of(0.0F, -5.0F, 2.0F, 0.5235988F, 0.0F, 0.0F));
        modelPartData2.addChild("saddle", ModelPartBuilder.create().uv(26, 0).cuboid(-5.0F, -8.0F, -9.0F, 10.0F, 9.0F, 9.0F, new Dilation(0.5F)), ModelTransform.NONE);
        modelPartData3.addChild("left_saddle_mouth", ModelPartBuilder.create().uv(29, 5).cuboid(2.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, dilation), ModelTransform.NONE);
        modelPartData3.addChild("right_saddle_mouth", ModelPartBuilder.create().uv(29, 5).cuboid(-3.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, dilation), ModelTransform.NONE);
        modelPartData3.addChild("left_saddle_line", ModelPartBuilder.create().uv(32, 2).cuboid(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, dilation), ModelTransform.rotation(-0.5235988F, 0.0F, 0.0F));
        modelPartData3.addChild("right_saddle_line", ModelPartBuilder.create().uv(32, 2).cuboid(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, dilation), ModelTransform.rotation(-0.5235988F, 0.0F, 0.0F));
        modelPartData3.addChild("head_saddle", ModelPartBuilder.create().uv(1, 1).cuboid(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, new Dilation(0.2F)), ModelTransform.NONE);
        modelPartData3.addChild("mouth_saddle_wrap", ModelPartBuilder.create().uv(19, 0).cuboid(-2.0F, -11.0F, -4.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.2F)), ModelTransform.NONE);
        modelPartData4.addChild("left_ear", ModelPartBuilder.create().uv(19, 16).cuboid(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.NONE);
        modelPartData4.addChild("right_ear", ModelPartBuilder.create().uv(19, 16).cuboid(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void setAngles(T abstractHorseEntity, float f, float g, float h, float i, float j) {
        boolean bl2 = abstractHorseEntity.hasPassengers();
        ModelPart[] var9 = this.saddle;
        int var10 = var9.length;

        int var11;
        ModelPart modelPart;

        var9 = this.straps;
        var10 = var9.length;

        for(var11 = 0; var11 < var10; ++var11) {
            modelPart = var9[var11];
            modelPart.visible = bl2;
        }

        this.body.pivotY = 11.0F;
    }

    public Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.rightHindBabyLeg, this.leftHindBabyLeg, this.rightFrontBabyLeg, this.leftFrontBabyLeg);
    }

    public void animateModel(T abstractHorseEntity, float f, float g, float h) {
        super.animateModel(abstractHorseEntity, f, g, h);
        float i = MathHelper.lerpAngle(abstractHorseEntity.prevBodyYaw, abstractHorseEntity.bodyYaw, h);
        float j = MathHelper.lerpAngle(abstractHorseEntity.prevHeadYaw, abstractHorseEntity.headYaw, h);
        float k = MathHelper.lerp(h, abstractHorseEntity.prevPitch, abstractHorseEntity.getPitch());
        float l = j - i;
        float m = k * 0.017453292F;
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += MathHelper.cos(f * 0.4F) * 0.15F * g;
        }

        this.head.pivotY = 4.0F;
        this.head.pivotZ = -12.0F;
        this.body.pitch = 0.0F;
        this.head.pitch = 0.5235988F + m;
        this.head.yaw = l * 0.017453292F;
        float s = abstractHorseEntity.isTouchingWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + 3.1415927F);
        this.rightFrontLeg.pivotY = this.leftFrontLeg.pivotY;
        this.rightFrontLeg.pivotZ = this.leftFrontLeg.pivotZ;
        this.tail.pitch = 0.5235988F + g * 0.75F;
        this.tail.pivotY = -5.0F + g;
        this.tail.pivotZ = 2.0F + g * 2.0F;
        this.tail.yaw = 0.0F;

        this.rightHindBabyLeg.pivotY = this.rightHindLeg.pivotY;
        this.rightHindBabyLeg.pivotZ = this.rightHindLeg.pivotZ;
        this.rightHindBabyLeg.pitch = this.rightHindLeg.pitch;
        this.leftHindBabyLeg.pivotY = this.leftHindLeg.pivotY;
        this.leftHindBabyLeg.pivotZ = this.leftHindLeg.pivotZ;
        this.leftHindBabyLeg.pitch = this.leftHindLeg.pitch;
        this.rightFrontBabyLeg.pivotY = this.rightFrontLeg.pivotY;
        this.rightFrontBabyLeg.pivotZ = this.rightFrontLeg.pivotZ;
        this.rightFrontBabyLeg.pitch = this.rightFrontLeg.pitch;
        this.leftFrontBabyLeg.pivotY = this.leftFrontLeg.pivotY;
        this.leftFrontBabyLeg.pivotZ = this.leftFrontLeg.pivotZ;
        this.leftFrontBabyLeg.pitch = this.leftFrontLeg.pitch;
        boolean bl2 = abstractHorseEntity.isBaby();
        this.rightHindLeg.visible = !bl2;
        this.leftHindLeg.visible = !bl2;
        this.rightFrontLeg.visible = !bl2;
        this.leftFrontLeg.visible = !bl2;
        this.rightHindBabyLeg.visible = bl2;
        this.leftHindBabyLeg.visible = bl2;
        this.rightFrontBabyLeg.visible = bl2;
        this.leftFrontBabyLeg.visible = bl2;
        this.body.pivotY = bl2 ? 10.8F : 0.0F;
    }
}
