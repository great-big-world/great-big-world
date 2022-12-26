package com.github.creoii.greatbigworld.client.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

@Environment(EnvType.CLIENT)
public class MooseAnimations {
    public static final Animation WALKING = Animation.Builder.create(2f).looping()
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4667F, AnimationHelper.createRotationalVector(7.5F, -2.67F, -7.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.2667F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.8F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, -2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4667F, AnimationHelper.createTranslationalVector(-0.5F, -0.25F, -0.13F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createTranslationalVector(-0.5F, 0.1F, 2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5332F, AnimationHelper.createTranslationalVector(0.5F, 1.0F, -0.11F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createTranslationalVector(0.0F, 0.1F, -2.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.125F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4583F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5332F, AnimationHelper.createRotationalVector(7.5F, 2.33F, 7.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.5F, 0.1F, 2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4667F, AnimationHelper.createTranslationalVector(-0.5F, 1.0F, 0.12F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, -2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5332F, AnimationHelper.createTranslationalVector(0.5F, -0.25F, -0.13F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createTranslationalVector(0.5F, 0.1F, 2.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.1667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4667F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.2667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 1.2F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.1667F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4583F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, 1.06F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.2667F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, -1.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 1.2F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-33.75F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.0417F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.1667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.2667F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5332F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createRotationalVector(-33.75F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 1.14F, 0.11F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.1667F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, -1.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.2667F, AnimationHelper.createTranslationalVector(0.0F, 0.1F, 2.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.8F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, 0.95F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createTranslationalVector(0.0F, 1.14F, 0.11F), Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.4667F, AnimationHelper.createRotationalVector(-7.5F, 0.33F, 7.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.5332F, AnimationHelper.createRotationalVector(-7.5F, 0.33F, -7.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2f, AnimationHelper.createRotationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR)))
            .build();
    public static final Animation SWIMMING = Animation.Builder.create(1.04167f).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.3333F, AnimationHelper.createRotationalVector(10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(90.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createRotationalVector(45.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-22.5F, -22.5F, -22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.875F, AnimationHelper.createRotationalVector(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createRotationalVector(90.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 2.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.6667F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.875F, AnimationHelper.createTranslationalVector(0.0F, -0.27F, -1.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createTranslationalVector(0.0F, -1.45F, 0.43F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 2.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(90.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createRotationalVector(45.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.6667F, AnimationHelper.createRotationalVector(-22.5F, 22.5F, 22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.875F, AnimationHelper.createRotationalVector(-45.0F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createRotationalVector(22.5F, 0.0F, -22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createRotationalVector(90.0F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 2.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.6667F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.875F, AnimationHelper.createTranslationalVector(0.0F, -0.27F, -1.14F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createTranslationalVector(0.0F, -1.45F, 0.43F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createTranslationalVector(0.0F, -0.64F, 2.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createRotationalVector(67.5F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.7917F, AnimationHelper.createRotationalVector(90.0F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(-2.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createTranslationalVector(1.0F, -2.0F, -1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.7917F, AnimationHelper.createTranslationalVector(0.58F, 0.0F, -2.83F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createTranslationalVector(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createTranslationalVector(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createRotationalVector(67.5F, 45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.7917F, AnimationHelper.createRotationalVector(90.0F, -45.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(2.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.4583F, AnimationHelper.createTranslationalVector(-1.0F, -2.0F, -1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.7917F, AnimationHelper.createTranslationalVector(-0.58F, 0.0F, -2.83F), Transformation.Interpolations.CUBIC),
                    new Keyframe(0.9583F, AnimationHelper.createTranslationalVector(2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0417F, AnimationHelper.createTranslationalVector(2.5F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC)))
            .build();
    public static final Animation IDLING_IN_WATER = Animation.Builder.create(3f).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.625F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -45.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -22.5F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083F, AnimationHelper.createTranslationalVector(-1.0F, -0.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 45.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 22.5F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(2.2083F, AnimationHelper.createTranslationalVector(1.0F, -0.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createTranslationalVector(1.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(22.5F, -22.5F, -45.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createRotationalVector(22.5F, -22.5F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 45.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createRotationalVector(22.5F, 22.5F, 0.0F), Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(3.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 1.0F), Transformation.Interpolations.CUBIC)))
            .build();
}
