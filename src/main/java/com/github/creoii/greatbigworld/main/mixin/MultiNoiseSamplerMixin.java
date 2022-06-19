package com.github.creoii.greatbigworld.main.mixin;

import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MultiNoiseUtil.MultiNoiseSampler.class)
public class MultiNoiseSamplerMixin {
    @Mutable @Shadow @Final private List<MultiNoiseUtil.NoiseHypercube> spawnTarget;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void great_big_world_randomSpawnBiomes(DensityFunction densityFunction, DensityFunction densityFunction2, DensityFunction densityFunction3, DensityFunction densityFunction4, DensityFunction densityFunction5, DensityFunction densityFunction6, List<MultiNoiseUtil.NoiseHypercube> list, CallbackInfo ci) {
        spawnTarget = List.of(MultiNoiseUtil.createNoiseHypercube(MultiNoiseUtil.ParameterRange.of(-1, 1), MultiNoiseUtil.ParameterRange.of(-1, 1), MultiNoiseUtil.ParameterRange.of(-1, 1), MultiNoiseUtil.ParameterRange.of(-1, 1), MultiNoiseUtil.ParameterRange.of(-1, 1), MultiNoiseUtil.ParameterRange.of(-1, 1), 0f));
    }
}
