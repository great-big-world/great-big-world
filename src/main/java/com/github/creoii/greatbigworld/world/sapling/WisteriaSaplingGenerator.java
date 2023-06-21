package com.github.creoii.greatbigworld.world.sapling;

import com.github.creoii.greatbigworld.main.registry.GBWConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class WisteriaSaplingGenerator extends SaplingGenerator {
    private final int type;

    public WisteriaSaplingGenerator(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return switch (type) {
            case 1 -> GBWConfiguredFeatures.YELLOW_WISTERIA;
            case 2 -> GBWConfiguredFeatures.BLUE_WISTERIA;
            case 3 -> GBWConfiguredFeatures.PINK_WISTERIA;
            case 4 -> GBWConfiguredFeatures.PURPLE_WISTERIA;
            default -> GBWConfiguredFeatures.WHITE_WISTERIA;
        };
    }
}
