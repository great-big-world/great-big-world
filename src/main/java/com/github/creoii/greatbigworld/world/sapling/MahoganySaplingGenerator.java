package com.github.creoii.greatbigworld.world.sapling;

import com.github.creoii.greatbigworld.main.registry.ConfiguredFeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class MahoganySaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ConfiguredFeatureRegistry.MAHOGANY;
    }
}
