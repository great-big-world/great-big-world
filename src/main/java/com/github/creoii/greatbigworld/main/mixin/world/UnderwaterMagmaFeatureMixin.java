package com.github.creoii.greatbigworld.main.mixin.world;

import com.github.creoii.greatbigworld.main.registry.GBWConfiguredFeatures;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.UnderwaterMagmaFeature;
import net.minecraft.world.gen.feature.UnderwaterMagmaFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.OptionalInt;

@Mixin(UnderwaterMagmaFeature.class)
public class UnderwaterMagmaFeatureMixin {
    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;stream(Lnet/minecraft/util/math/Box;)Ljava/util/stream/Stream;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void gbw_generateLavarockWithMagma(FeatureContext<UnderwaterMagmaFeatureConfig> context, CallbackInfoReturnable<Boolean> cir, StructureWorldAccess structureWorldAccess, BlockPos blockPos, UnderwaterMagmaFeatureConfig underwaterMagmaFeatureConfig, Random random, OptionalInt optionalInt, BlockPos blockPos2, Vec3i vec3i, Box box) {
        ConfiguredFeature<?, ?> oreLavarock = structureWorldAccess.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).get(GBWConfiguredFeatures.ORE_LAVAROCK);
        if (oreLavarock != null)
            oreLavarock.generate(structureWorldAccess, context.getGenerator(), random, blockPos2);
    }
}
