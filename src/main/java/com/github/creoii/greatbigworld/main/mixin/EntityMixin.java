package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.util.Entity3Dimensions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput {
    @Shadow private EntityDimensions dimensions;
    @Shadow @Final protected Random random;
    @Shadow public World world;
    @Shadow public abstract double getX();
    @Shadow public abstract double getZ();
    @Shadow public abstract double getY();
    @Shadow public abstract Vec3d getEyePos();
    @Shadow public abstract void setPosition(Vec3d pos);
    @Shadow public abstract EntityDimensions getDimensions(EntityPose pose);

    //@Inject(method = "onSwimmingStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 0), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    //private void great_big_world_triDimensionsOnSwimmingStartBubble(CallbackInfo ci, Entity entity, float f, Vec3d vec3d, float g, float h, int i, double d, double e) {
    //    if (dimensions instanceof Entity3Dimensions triDimensions) {
    //        double l = (random.nextDouble() * 2d - 1d) * (double) triDimensions.length;
    //        double w = (random.nextDouble() * 2d - 1d) * (double) triDimensions.width;
    //        world.addParticle(ParticleTypes.BUBBLE, getX() + l, h + 1d, getZ() + w, vec3d.x, vec3d.y - random.nextDouble() * .20000000298023224d, vec3d.z);
    //        ci.cancel();
    //    }
    //}

    //@Inject(method = "onSwimmingStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 1), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    //private void great_big_world_triDimensionsOnSwimmingStartSplash(CallbackInfo ci, Entity entity, float f, Vec3d vec3d, float g, float h, int i, double d, double e) {
    //    if (dimensions instanceof Entity3Dimensions triDimensions) {
    //        double l = (random.nextDouble() * 2d - 1d) * (double) triDimensions.length;
    //        double w = (random.nextDouble() * 2d - 1d) * (double) triDimensions.width;
    //        world.addParticle(ParticleTypes.SPLASH, getX() + l, h + 1d, getZ() + w, vec3d.x, vec3d.y, vec3d.z);
    //        ci.cancel();
    //    }
    //}

    @Inject(method = "spawnSprintingParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void great_big_world_3DimensionsSpawnSprintingParticles(CallbackInfo ci, int i, int j, int k, BlockPos blockPos, BlockState blockState, Vec3d vec3d) {
        if (dimensions instanceof Entity3Dimensions triDimensions) {
            world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), getX() + (random.nextDouble() - .5d) * (double)triDimensions.length, getY() + .1d, getZ() + (random.nextDouble() - .5d) * (double)triDimensions.width, vec3d.x * -4d, 1.5d, vec3d.z * -4d);
            ci.cancel();
        }
    }

    @Inject(method = "isInsideWall", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void great_big_world_3DimensionsIsInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (dimensions instanceof Entity3Dimensions triDimensions) {
            float l = triDimensions.width * .8f;
            float w = triDimensions.width * .8f;
            Box box1 = Box.of(getEyePos(), l, 1e-6d, w);
            cir.setReturnValue(BlockPos.stream(box1).anyMatch((pos) -> {
                BlockState blockState = world.getBlockState(pos);
                return !blockState.isAir() && blockState.shouldSuffocate(world, pos) && VoxelShapes.matchesAnywhere(blockState.getCollisionShape(world, pos).offset(pos.getX(), pos.getY(), pos.getZ()), VoxelShapes.cuboid(box1), BooleanBiFunction.AND);
            }));
        }
    }

    //@Inject(method = "calculateDimensions", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/shape/VoxelShapes;cuboid(Lnet/minecraft/util/math/Box;)Lnet/minecraft/util/shape/VoxelShape;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    //private void great_big_world_calculate3Dimensions(CallbackInfo ci, EntityDimensions entityDimensions, EntityPose entityPose, EntityDimensions entityDimensions2, boolean bl, Vec3d vec3d, double d, double e) {
    //    if (entityDimensions instanceof Entity3Dimensions triDimensions && entityDimensions2 instanceof Entity3Dimensions triDimensions2) {
    //        double l = (double) Math.max(0f, triDimensions2.length - triDimensions.length) + 1e-6d;
    //        double w = (double) Math.max(0f, triDimensions2.width - triDimensions.width) + 1e-6d;
    //        VoxelShape voxelShape = VoxelShapes.cuboid(Box.of(vec3d, l, e, w));
    //        world.findClosestCollision((Entity) (Object) this, voxelShape, vec3d, triDimensions2.length, triDimensions2.height, triDimensions2.width).ifPresent(pos -> {
    //            setPosition(pos.add(0d, (double) -triDimensions2.height / 2d, 0d));
    //        });
    //        ci.cancel();
    //    }
    //}

    @Inject(method = "calculateBoundsForPose", at = @At("HEAD"), cancellable = true)
    private void great_big_world_3DimensionsCalculateBoundsForPose(EntityPose pos, CallbackInfoReturnable<Box> cir) {
        EntityDimensions entityDimensions = getDimensions(pos);
        if (entityDimensions instanceof Entity3Dimensions triDimensions) {
            float l = triDimensions.length / 2f;
            float w = triDimensions.width / 2f;
            Vec3d vec3d = new Vec3d(getX() - (double)l, getY(), getZ() - (double)w);
            Vec3d vec3d2 = new Vec3d(getX() + (double)l, getY() + (double)triDimensions.height, getZ() + (double)w);
            cir.setReturnValue(new Box(vec3d, vec3d2));
        }
    }
}
