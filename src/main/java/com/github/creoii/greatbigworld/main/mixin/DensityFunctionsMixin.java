package com.github.creoii.greatbigworld.main.mixin;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DensityFunctions.class)
public class DensityFunctionsMixin {
    //@Inject(method = "createSurfaceNoiseRouter", at = @At(value = "INVOKE", target = ""), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void great_big_world_createBiomeNoise(Registry<DensityFunction> registry, boolean bl, boolean bl2, CallbackInfoReturnable<NoiseRouter> cir) {

    }
}
