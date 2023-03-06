package com.github.creoii.greatbigworld.world.region;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

public class GBWOverworldRegion extends Region {
    private static final MultiNoiseUtil.ParameterRange TROPICAL_ISLAND_RANGE = MultiNoiseUtil.ParameterRange.of(.018f, 1f);

    public GBWOverworldRegion(int weight) {
        super(new Identifier(GreatBigWorld.NAMESPACE, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, builder -> {
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.COOL)
                    .humidity(Humidity.DRY, Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, BiomeRegistry.ASPEN_FOREST));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.ICY)
                    .humidity(Humidity.DRY, Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, BiomeRegistry.SNOWY_ASPEN_FOREST));
            /*
            List<MultiNoiseUtil.NoiseHypercube> islandJunglePoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.18f, -1.13f))
                    .erosion(Erosion.FULL_RANGE)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(MultiNoiseUtil.ParameterRange.of(-.3f, 1f))
                    .offset(0f)
                    .build();
            islandJunglePoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.ISLAND_JUNGLE));
            List<MultiNoiseUtil.NoiseHypercube> islandSparseJunglePoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.18f, -1.13f))
                    .erosion(Erosion.FULL_RANGE)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(MultiNoiseUtil.ParameterRange.of(-1f, -.3f))
                    .offset(0f)
                    .build();
            islandSparseJunglePoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.ISLAND_SPARSE_JUNGLE));

            List<MultiNoiseUtil.NoiseHypercube> islandBeachPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.13f, -1.05f))
                    .erosion(MultiNoiseUtil.ParameterRange.of(-1f, .4f))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.VALLEY)
                    .offset(0f)
                    .build();
            islandBeachPoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.ISLAND_BEACH));
            List<MultiNoiseUtil.NoiseHypercube> volcanicBeachPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.19f, -1.175f))
                    .erosion(Erosion.FULL_RANGE)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.VALLEY)
                    .offset(0f)
                    .build();
            volcanicBeachPoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.VOLCANIC_BEACH));

            List<MultiNoiseUtil.NoiseHypercube> mangroveSwampPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.13f, -1.05f))
                    .erosion(MultiNoiseUtil.ParameterRange.of(.4f, 1f))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.VALLEY, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build();
            mangroveSwampPoints.forEach(point -> builder.replaceBiome(point, BiomeKeys.MANGROVE_SWAMP));

            List<MultiNoiseUtil.NoiseHypercube> volcanicSlopesPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.199f, -1.175f))
                    .erosion(Erosion.FULL_RANGE)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.PEAK_NORMAL, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build();
            volcanicSlopesPoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.VOLCANIC_SLOPES));
            List<MultiNoiseUtil.NoiseHypercube> volcanicCraterPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(TROPICAL_ISLAND_RANGE)
                    .humidity(Humidity.FULL_RANGE)
                    .continentalness(MultiNoiseUtil.ParameterRange.of(-1.2f, -1.199f))
                    .erosion(Erosion.FULL_RANGE)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.PEAK_NORMAL, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build();
            volcanicCraterPoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.VOLCANIC_CRATER));
            */
        });
    }
}