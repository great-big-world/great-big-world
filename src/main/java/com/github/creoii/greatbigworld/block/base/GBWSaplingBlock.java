package com.github.creoii.greatbigworld.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class GBWSaplingBlock extends SaplingBlock {
    private final float chance;

    public GBWSaplingBlock(Settings settings, RegistryKey<ConfiguredFeature<?, ?>> configuredTreeFeature, float chance) {
        super(new SaplingGenerator() {
            @Nullable @Override
            protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                return configuredTreeFeature;
            }
        }, settings);
        this.chance = chance;
    }

    public GBWSaplingBlock(Settings settings, RegistryKey<ConfiguredFeature<?, ?>> configuredTreeFeature) {
        this(settings, configuredTreeFeature, .45f);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return random.nextFloat() < chance;
    }
}
