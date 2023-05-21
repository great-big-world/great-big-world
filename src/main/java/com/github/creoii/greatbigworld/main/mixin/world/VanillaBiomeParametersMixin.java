package com.github.creoii.greatbigworld.main.mixin.world;

import com.github.creoii.greatbigworld.main.registry.GBWBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.apache.commons.lang3.tuple.Pair;
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
    private final MultiNoiseUtil.ParameterRange mushroomIslandRange = MultiNoiseUtil.ParameterRange.of(-1f, -.018f);
    private final MultiNoiseUtil.ParameterRange tropicalIslandRange = MultiNoiseUtil.ParameterRange.of(.018f, 1f);

    @Shadow
    protected abstract void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome);
    @Shadow @Final
    private MultiNoiseUtil.ParameterRange defaultParameter;
    @Shadow @Final private MultiNoiseUtil.ParameterRange mushroomFieldsContinentalness;

    @Inject(method = "writeOceanBiomes", at = @At("HEAD"))
    private void great_big_world_writeIslandBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.18f, -1.13f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-.3f, 1f), 0f, GBWBiomes.ISLAND_JUNGLE);
        writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.18f, -1.13f), defaultParameter, MultiNoiseUtil.ParameterRange.of(-1, -.3f), 0f, GBWBiomes.ISLAND_SPARSE_JUNGLE);
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.VALLEY.parameter()}) {
            writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.13f, -1.05f), MultiNoiseUtil.ParameterRange.of(-1f, .4f), weirdness, 0f, GBWBiomes.ISLAND_BEACH);
            writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.19f, -1.175f), defaultParameter, weirdness, 0f, GBWBiomes.VOLCANIC_BEACH);
        }
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.VALLEY.parameter(), ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING.parameter()}) {
            //writeBiomeParameters(parameters, defaultParameter, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.13f, -1.05f), MultiNoiseUtil.ParameterRange.of(.2f, 1f), weirdness, 0f, BiomeRegistry.ISLAND_MANGROVE_SWAMP);
            writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.13f, -1.05f), MultiNoiseUtil.ParameterRange.of(.4f, 1f), weirdness, 0f, BiomeKeys.MANGROVE_SWAMP);
        }
        for (MultiNoiseUtil.ParameterRange weirdness : new MultiNoiseUtil.ParameterRange[]{ParameterUtils.Weirdness.PEAK_NORMAL.parameter(), ParameterUtils.Weirdness.PEAK_VARIANT.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING.parameter()}) {
            writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.199f, -1.175f), defaultParameter, weirdness, 0f, GBWBiomes.VOLCANIC_SLOPES);
            writeBiomeParameters(parameters, tropicalIslandRange, defaultParameter, MultiNoiseUtil.ParameterRange.of(-1.2f, -1.199f), defaultParameter, weirdness, 0f, GBWBiomes.VOLCANIC_CRATER);
        }
    }

    @Redirect(method = "writeOceanBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;writeBiomeParameters(Ljava/util/function/Consumer;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;FLnet/minecraft/util/registry/RegistryKey;)V", ordinal = 0))
    private void great_big_world_overrideMushroomIslands(VanillaBiomeParameters instance, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
        writeBiomeParameters(parameters, mushroomIslandRange, defaultParameter, mushroomFieldsContinentalness, defaultParameter, defaultParameter, 0f, BiomeKeys.MUSHROOM_FIELDS);
    }
}