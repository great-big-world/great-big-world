package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.decorator.BranchTreeDecorator;
import com.github.creoii.greatbigworld.world.placer.AspenFoliagePlacer;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class PlacedFeatureRegistry implements Register {
    public static RegistryEntry<PlacedFeature> MAHOGANY;
    public static RegistryEntry<PlacedFeature> YELLOW_ASPEN;
    public static RegistryEntry<PlacedFeature> GREEN_ASPEN;

    @Override
    public void register() {
        MAHOGANY = PlacedFeatures.register("mahogany", ConfiguredFeatureRegistry.MAHOGANY, PlacedFeatures.wouldSurvive(BlockRegistry.MAHOGANY_SAPLING));
        YELLOW_ASPEN = PlacedFeatures.register("yellow_aspen", ConfiguredFeatureRegistry.YELLOW_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.YELLOW_ASPEN_SAPLING));
        GREEN_ASPEN = PlacedFeatures.register("green_aspen", ConfiguredFeatureRegistry.GREEN_ASPEN, PlacedFeatures.wouldSurvive(BlockRegistry.GREEN_ASPEN_SAPLING));
    }
}
