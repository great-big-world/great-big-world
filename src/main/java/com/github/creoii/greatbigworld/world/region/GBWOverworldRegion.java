package com.github.creoii.greatbigworld.world.region;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GBWOverworldRegion extends Region {
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
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, BiomeRegistry.ASPEN_FOREST));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.ICY)
                    .humidity(Humidity.DRY, Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, BiomeRegistry.SNOWY_ASPEN_FOREST));
            /*new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.NEUTRAL)
                    .humidity(Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, BiomeRegistry.BLOOMING_FLOWER_FOREST));
             */
        });
    }
}