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
import terrablender.api.ParameterUtils;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public abstract class VanillaBiomeParametersMixin {
    private final MultiNoiseUtil.ParameterRange mushroomIslandRange = MultiNoiseUtil.ParameterRange.of(-1f, .1f);
    private final MultiNoiseUtil.ParameterRange tropicalIslandRange = MultiNoiseUtil.ParameterRange.of(.2f, 1f);

    @Shadow protected abstract void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome);
    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Inject(method = "writeOceanBiomes", at = @At("HEAD"))
    private void great_big_world_writeIslandBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.165f, -1.125f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-.3f, 1f), 0f, BiomeRegistry.ISLAND_JUNGLE);
        writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.165f, -1.125f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-1, -.3f), 0f, BiomeRegistry.ISLAND_SPARSE_JUNGLE);
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.VALLEY.parameter()}) {
            writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.125f, -1.05f), MultiNoiseUtil.ParameterRange.of(-1f, 0f), weirdness, 0f, BiomeRegistry.ISLAND_BEACH);
            writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.175f), defaultParameter, weirdness, 0f, BiomeRegistry.VOLCANIC_BEACH);
        }
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.VALLEY.parameter(), ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING.parameter()}) {
            writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.125f, -1.05f), MultiNoiseUtil.ParameterRange.of(0f, 1f), weirdness, 0f, BiomeRegistry.ISLAND_MANGROVE_SWAMP);
        }
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.PEAK_NORMAL.parameter(), ParameterUtils.Weirdness.PEAK_VARIANT.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING.parameter()}) {
            writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.165f), defaultParameter, weirdness, 0f, BiomeRegistry.VOLCANIC_PEAKS);
        }
    }

    @Redirect(method = "writeOceanBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;writeBiomeParameters(Ljava/util/function/Consumer;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;FLnet/minecraft/registry/RegistryKey;)V", ordinal = 0))
    private void great_big_world_overrideMushroomIslands(VanillaBiomeParameters instance, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
        //writeBiomeParameters(parameters, mushroomIslandRange, mushroomIslandRange, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.05f), mushroomIslandRange, defaultParameter, 0f, BiomeKeys.MUSHROOM_FIELDS);
    }
}
