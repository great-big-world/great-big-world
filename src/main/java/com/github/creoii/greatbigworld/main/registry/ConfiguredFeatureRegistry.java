package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.placer.TwistingTrunkPlacer;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ConfiguredFeatureRegistry implements Register {
    public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MAHOGANY;

    @Override
    public void register() {
        MAHOGANY = ConfiguredFeatures.register("mahogany", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(BlockRegistry.MAHOGANY_LOG), new TwistingTrunkPlacer(3, 2, 0, UniformIntProvider.create(4, 6), UniformIntProvider.create(1, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(1, 2), false), BlockStateProvider.of(BlockRegistry.MAHOGANY_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1)), new TwoLayersFeatureSize(9, 2, 6)).ignoreVines().build());
    }
}
