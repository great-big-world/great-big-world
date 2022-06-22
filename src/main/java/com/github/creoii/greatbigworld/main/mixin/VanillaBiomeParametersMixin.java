package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public abstract class VanillaBiomeParametersMixin {
    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Inject(method = "writeCaveBiomes", at = @At("TAIL"))
    private void world_of_plenty_injectNewCaveBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(0.08f, .25f), defaultParameter, 0f), BiomeRegistry.DIRT_CAVES));
    }

    @Inject(method = "getBadlandsBiome", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void world_of_plenty_injectRedRockPeaksBiomeFirst(int humidity, MultiNoiseUtil.ParameterRange weirdness, CallbackInfoReturnable<RegistryKey<Biome>> cir) {
        cir.setReturnValue(humidity < 3 ? weirdness.max() < 0L ? BiomeKeys.BADLANDS : BiomeRegistry.RED_ROCK_PEAKS : BiomeKeys.ERODED_BADLANDS);
    }

    @Inject(method = "getBadlandsBiome", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void world_of_plenty_injectRedRockPeaksBiomeSecond(int humidity, MultiNoiseUtil.ParameterRange weirdness, CallbackInfoReturnable<RegistryKey<Biome>> cir) {
        cir.setReturnValue(humidity < 3 ? weirdness.max() < 0L ? BiomeKeys.BADLANDS : BiomeRegistry.RED_ROCK_PEAKS : BiomeKeys.WOODED_BADLANDS);
    }
}
