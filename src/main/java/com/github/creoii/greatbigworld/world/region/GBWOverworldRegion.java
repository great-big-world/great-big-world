package com.github.creoii.greatbigworld.world.region;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWBiomes;
import com.github.creoii.greatbigworld.main.registry.GBWSurfaceRules;
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
        new GBWSurfaceRules().register();
        addModifiedVanillaOverworldBiomes(mapper, builder -> {
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.COOL)
                    .humidity(Humidity.DRY, Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.ASPEN_FOREST));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.ICY)
                    .humidity(Humidity.DRY, Humidity.NEUTRAL)
                    .continentalness(Continentalness.COAST, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3, Erosion.EROSION_4, Erosion.EROSION_6).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1), Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.VALLEY, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .offset(0f)
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.SNOWY_ASPEN_FOREST));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.NEUTRAL, Temperature.COOL)
                    .humidity(Humidity.ARID, Humidity.NEUTRAL)
                    .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.HOT_SPRINGS));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.NEUTRAL)
                    .humidity(Humidity.ARID)
                    .continentalness(Continentalness.NEAR_INLAND, Continentalness.MID_INLAND, Continentalness.FAR_INLAND, Continentalness.COAST).continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND)).continentalness(Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND)).continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_3, Erosion.FULL_RANGE, Erosion.EROSION_6, Erosion.EROSION_4).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_5)).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_4)).erosion(Erosion.span(Erosion.EROSION_3, Erosion.EROSION_4)).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_6)).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_5))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING).weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING))
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.BLOOMING_FLOWER_FOREST));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.HOT)
                    .humidity(Humidity.span(Humidity.ARID, Humidity.NEUTRAL)).humidity(Humidity.DRY)
                    .continentalness(Continentalness.NEAR_INLAND, Continentalness.MID_INLAND, Continentalness.FAR_INLAND).continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND)).continentalness(Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_2)).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING).weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)).weirdness(Weirdness.span(Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)).weirdness(Weirdness.span(Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING))
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.RED_ROCK_SLOPES));
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(Temperature.HOT)
                    .humidity(Humidity.FULL_RANGE, Humidity.ARID)
                    .continentalness(Continentalness.FAR_INLAND, Continentalness.MID_INLAND, Continentalness.NEAR_INLAND).continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND)).continentalness(Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1).erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_1)).erosion(Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3)).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_3))
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.MID_SLICE_VARIANT_DESCENDING, Weirdness.LOW_SLICE_VARIANT_ASCENDING).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING)).weirdness(Weirdness.span(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)).weirdness(Weirdness.span(Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING)).weirdness(Weirdness.span(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING))
                    .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.RED_ROCK_PEAKS));
        });
    }
}