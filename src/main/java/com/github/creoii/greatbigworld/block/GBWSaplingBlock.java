package com.github.creoii.greatbigworld.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class GBWSaplingBlock extends SaplingBlock {
    public GBWSaplingBlock(Settings settings, RegistryKey<ConfiguredFeature<?, ?>> configuredTreeFeature) {
        super(new SaplingGenerator() {
            @Nullable @Override
            protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                return configuredTreeFeature;
            }
        }, settings);
    }
}
