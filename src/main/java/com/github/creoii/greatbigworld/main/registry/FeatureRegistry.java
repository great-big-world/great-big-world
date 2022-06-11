package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.feature.MultiRandomSpreadFeature;
import com.github.creoii.greatbigworld.world.feature.RandomSpreadFeature;
import com.github.creoii.greatbigworld.world.feature.config.MultiRandomSpreadFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.RandomSpreadFeatureConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {
    public static final Feature<RandomSpreadFeatureConfig> RANDOM_SPREAD = new RandomSpreadFeature(RandomSpreadFeatureConfig.CODEC);
    public static final Feature<MultiRandomSpreadFeatureConfig> MULTI_RANDOM_SPREAD = new MultiRandomSpreadFeature(MultiRandomSpreadFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.MOD_ID, "random_spread"), RANDOM_SPREAD);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.MOD_ID, "multi_random_spread"), MULTI_RANDOM_SPREAD);
    }
}
