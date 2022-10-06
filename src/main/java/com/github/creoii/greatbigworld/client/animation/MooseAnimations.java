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
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, -5.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4667F, AnimationHelper.method_41829(7.5F, -2.67F, -7.5F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.2667F, AnimationHelper.method_41829(22.5F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.8F, AnimationHelper.method_41829(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41829(0.0F, -5.0F, 0.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 0.1F, -2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4667F, AnimationHelper.method_41823(-0.5F, -0.25F, -0.13F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41823(-0.5F, 0.1F, 2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.5332F, AnimationHelper.method_41823(0.5F, 1.0F, -0.11F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41823(0.0F, 0.1F, -2.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.125F, AnimationHelper.method_41829(22.5F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4583F, AnimationHelper.method_41829(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41829(0.0F, 5.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.5332F, AnimationHelper.method_41829(7.5F, 2.33F, 7.5F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.5F, 0.1F, 2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4667F, AnimationHelper.method_41823(-0.5F, 1.0F, 0.12F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41823(0.0F, 0.1F, -2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.5332F, AnimationHelper.method_41823(0.5F, -0.25F, -0.13F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41823(0.5F, 0.1F, 2.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.1667F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4667F, AnimationHelper.method_41829(45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41829(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.2667F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(2F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 0.1F, 1.2F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.1667F, AnimationHelper.method_41823(0.0F, 0.1F, 2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4583F, AnimationHelper.method_41823(0.0F, 2.0F, 1.06F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.2667F, AnimationHelper.method_41823(0.0F, 0.1F, -1.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41823(0.0F, 0.1F, 1.2F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(-33.75F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.0417F, AnimationHelper.method_41829(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.1667F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.2667F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.5332F, AnimationHelper.method_41829(45.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41829(-33.75F, 0.0F, 0.0F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 1.14F, 0.11F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.1667F, AnimationHelper.method_41823(0.0F, 0.1F, -1.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.2667F, AnimationHelper.method_41823(0.0F, 0.1F, 2.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.8F, AnimationHelper.method_41823(0.0F, 2.0F, 0.95F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41823(0.0F, 1.14F, 0.11F), Transformation.Interpolations.field_37884)))
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 5.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(0.4667F, AnimationHelper.method_41829(-7.5F, 0.33F, 7.5F), Transformation.Interpolations.field_37884),
                    new Keyframe(1F, AnimationHelper.method_41829(0.0F, -5.0F, 0.0F), Transformation.Interpolations.field_37884),
                    new Keyframe(1.5332F, AnimationHelper.method_41829(-7.5F, 0.33F, -7.5F), Transformation.Interpolations.field_37884),
                    new Keyframe(2f, AnimationHelper.method_41829(0.0F, 5.0F, 0.0F), Transformation.Interpolations.field_37884)))
            .build();
    public static final Animation SWIMMING = Animation.Builder.create(1.0417f).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.3333F, AnimationHelper.method_41829(10.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.6667F, AnimationHelper.method_41829(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(90.0F, 22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41829(45.0F, 22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.6667F, AnimationHelper.method_41829(-22.5F, -22.5F, -22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.875F, AnimationHelper.method_41829(-45.0F, -22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41829(22.5F, 0.0F, 22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41829(90.0F, 22.5F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, -0.64F, 2.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41823(0.0F, -0.64F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.6667F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.875F, AnimationHelper.method_41823(0.0F, -0.27F, -1.14F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41823(0.0F, -1.45F, 0.43F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41823(0.0F, -0.64F, 2.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(90.0F, -22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41829(45.0F, -22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.6667F, AnimationHelper.method_41829(-22.5F, 22.5F, 22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.875F, AnimationHelper.method_41829(-45.0F, 22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41829(22.5F, 0.0F, -22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41829(90.0F, -22.5F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, -0.64F, 2.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41823(0.0F, -0.64F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.6667F, AnimationHelper.method_41823(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.875F, AnimationHelper.method_41823(0.0F, -0.27F, -1.14F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41823(0.0F, -1.45F, 0.43F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41823(0.0F, -0.64F, 2.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.25F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41829(67.5F, -45.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.7917F, AnimationHelper.method_41829(90.0F, 45.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.25F, AnimationHelper.method_41823(-2.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41823(1.0F, -2.0F, -1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.7917F, AnimationHelper.method_41823(0.58F, 0.0F, -2.83F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41823(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41823(-2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.25F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41829(67.5F, 45.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.7917F, AnimationHelper.method_41829(90.0F, -45.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41829(90.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.25F, AnimationHelper.method_41823(2.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.4583F, AnimationHelper.method_41823(-1.0F, -2.0F, -1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.7917F, AnimationHelper.method_41823(-0.58F, 0.0F, -2.83F), Transformation.Interpolations.field_37885),
                    new Keyframe(0.9583F, AnimationHelper.method_41823(2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0417F, AnimationHelper.method_41823(2.5F, 0.0F, 1.0F), Transformation.Interpolations.field_37885)))
            .build();
    public static final Animation IDLING_IN_WATER = Animation.Builder.create(3f).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.625F, AnimationHelper.method_41829(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41829(0.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, -22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(2.2083F, AnimationHelper.method_41829(0.0F, 0.0F, -45.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41829(0.0F, 0.0F, -22.5F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(2.2083F, AnimationHelper.method_41823(-1.0F, -0.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41823(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(0.0F, 0.0F, 22.5F), Transformation.Interpolations.field_37885),
                    new Keyframe(2.2083F, AnimationHelper.method_41829(0.0F, 0.0F, 45.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41829(0.0F, 0.0F, 22.5F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_front_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(1.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(2.2083F, AnimationHelper.method_41823(1.0F, -0.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41823(1.0F, 0.0F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(22.5F, -22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0F, AnimationHelper.method_41829(22.5F, -22.5F, -45.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41829(22.5F, -22.5F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("left_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0F, AnimationHelper.method_41823(0.0F, -1.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41823(0.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.method_41829(22.5F, 22.5F, 0.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0F, AnimationHelper.method_41829(22.5F, 22.5F, 45.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41829(22.5F, 22.5F, 0.0F), Transformation.Interpolations.field_37885)))
            .addBoneAnimation("right_hind_leg", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.method_41823(0.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(1.0F, AnimationHelper.method_41823(0.0F, -1.0F, 1.0F), Transformation.Interpolations.field_37885),
                    new Keyframe(3.0F, AnimationHelper.method_41823(0.0F, 0.0F, 1.0F), Transformation.Interpolations.field_37885)))
            .build();
}
