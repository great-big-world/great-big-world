package com.github.creoii.greatbigworld.world.sapling;

import com.github.creoii.greatbigworld.main.registry.ConfiguredFeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class GreenAspenSaplingGenerator extends SaplingGenerator {
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return BuiltinRegistries.CONFIGURED_FEATURE.entryOf(ConfiguredFeatureRegistry.GREEN_ASPEN);
    }
}
