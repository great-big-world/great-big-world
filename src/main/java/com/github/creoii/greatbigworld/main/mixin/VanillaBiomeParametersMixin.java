package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.util.BiomeKeys;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public abstract class VanillaBiomeParametersMixin {
    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Inject(method = "writeCaveBiomes", at = @At("TAIL"))
    private void world_of_plenty$injectNewCaveBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(0.08f, .25f), defaultParameter, 0f), BiomeKeys.DIRT_CAVES));
    }
}
