package com.github.creoii.greatbigworld.world.region;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.GBWBiomes;
import com.github.creoii.greatbigworld.main.registry.GBWSurfaceRules;
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

import java.util.function.Consumer;

public class GBWOverworldRegion extends Region {
    private final Type type;

    public GBWOverworldRegion(Type type) {
        super(new Identifier(GreatBigWorld.NAMESPACE, "overworld_" + type.getSuffix()), RegionType.OVERWORLD, type.getWeight());
        this.type = type;
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        new GBWSurfaceRules().register();
        if (type == Type.NORMAL) {
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
                builder.replaceBiome(BiomeKeys.FLOWER_FOREST, GBWBiomes.BLOOMING_FLOWER_FOREST);
                new ParameterUtils.ParameterPointListBuilder()
                        .temperature(Temperature.HOT)
                        .humidity(Humidity.ARID, Humidity.NEUTRAL).humidity(Humidity.span(Humidity.NEUTRAL, Humidity.WET))
                        .continentalness(Continentalness.FAR_INLAND).continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                        .erosion(Erosion.EROSION_0, Erosion.EROSION_1).erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_2))
                        .depth(Depth.SURFACE, Depth.FLOOR)
                        .weirdness(Weirdness.PEAK_NORMAL, Weirdness.PEAK_VARIANT)
                        .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.RED_ROCK_PEAKS));
            });
        } else if (type == Type.RARE) {
            addModifiedVanillaOverworldBiomes(mapper, builder -> {
                new ParameterUtils.ParameterPointListBuilder()
                        .temperature(Temperature.NEUTRAL, Temperature.COOL)
                        .humidity(Humidity.ARID, Humidity.NEUTRAL)
                        .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                        .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
                        .depth(Depth.SURFACE, Depth.FLOOR, Depth.UNDERGROUND)
                        .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING)
                        .build().forEach(point -> builder.replaceBiome(point, GBWBiomes.HOT_SPRINGS));
            });
        }
    }

    public enum Type {
        NORMAL("normal", 4),
        RARE("rare", 2);

        private final String suffix;
        private final int weight;

        Type(String suffix, int weight) {
            this.suffix = suffix;
            this.weight = weight;
        }

        public String getSuffix() {
            return suffix;
        }

        public int getWeight() {
            return weight;
        }
    }
}