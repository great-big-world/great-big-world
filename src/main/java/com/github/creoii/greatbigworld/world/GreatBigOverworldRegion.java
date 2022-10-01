package com.github.creoii.greatbigworld.world;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GreatBigOverworldRegion extends Region {
    public GreatBigOverworldRegion(int weight) {
        super(new Identifier(GreatBigWorld.NAMESPACE, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addBiomeSimilar(mapper, BiomeKeys.BIRCH_FOREST, BiomeRegistry.ASPEN_FOREST_KEY);
        //addModifiedVanillaOverworldBiomes(mapper, builder -> {
        //    List<MultiNoiseUtil.NoiseHypercube> aspenForestPoints = new ParameterUtils.ParameterPointListBuilder()
        //            .temperature(Temperature.COOL, Temperature.NEUTRAL)
        //            .humidity(Humidity.DRY, Humidity.NEUTRAL, Humidity.WET)
        //            .continentalness(Continentalness.span(Continentalness.COAST, Continentalness.NEAR_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.MID_INLAND), Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
        //            .erosion(Erosion.span(Erosion.EROSION_2, Erosion.EROSION_3)).erosion(Erosion.EROSION_4, Erosion.EROSION_5, Erosion.EROSION_6)
        //            .depth(Depth.SURFACE, Depth.FLOOR)
        //            .weirdness(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL, Weirdness.HIGH_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING, Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.PEAK_VARIANT, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
        //            .build();
        //    aspenForestPoints.forEach(point -> builder.replaceBiome(point, BiomeRegistry.ASPEN_FOREST_KEY));
        //});
    }
}
