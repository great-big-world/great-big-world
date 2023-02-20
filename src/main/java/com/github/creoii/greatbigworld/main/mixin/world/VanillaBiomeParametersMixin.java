package com.github.creoii.greatbigworld.main.mixin.world;

import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public abstract class VanillaBiomeParametersMixin {
    private final MultiNoiseUtil.ParameterRange mushroomIslandRange = MultiNoiseUtil.ParameterRange.of(-1f, .1f);
    private final MultiNoiseUtil.ParameterRange tropicalIslandRange = MultiNoiseUtil.ParameterRange.of(.2f, 1f);

    @Shadow protected abstract void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome);
    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Inject(method = "writeOceanBiomes", at = @At("HEAD"))
    private void great_big_world_writeIslandBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.125f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-.3f, 1f), 0f, BiomeRegistry.ISLAND_JUNGLE);
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.125f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-1, -.3f), 0f, BiomeRegistry.ISLAND_SPARSE_JUNGLE);
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.125f, -1.05f), MultiNoiseUtil.ParameterRange.of(-1f, .4f), defaultParameter, 0f, BiomeRegistry.ISLAND_BEACH);
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.125f, -1.05f), MultiNoiseUtil.ParameterRange.of(.4f, 1f), defaultParameter, 0f, BiomeRegistry.ISLAND_STONY_SHORE);
    }

    @Redirect(method = "writeOceanBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;writeBiomeParameters(Ljava/util/function/Consumer;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;FLnet/minecraft/registry/RegistryKey;)V", ordinal = 0))
    private void great_big_world_overrideMushroomIslands(VanillaBiomeParameters instance, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
        //writeBiomeParameters(parameters, mushroomIslandRange, mushroomIslandRange, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.05f), mushroomIslandRange, defaultParameter, 0f, BiomeKeys.MUSHROOM_FIELDS);
    }
}
