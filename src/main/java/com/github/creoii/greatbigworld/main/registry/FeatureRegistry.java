package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.feature.*;
import com.github.creoii.greatbigworld.world.feature.config.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class FeatureRegistry {
    public static final Feature<RandomSpreadFeatureConfig> RANDOM_SPREAD = new RandomSpreadFeature(RandomSpreadFeatureConfig.CODEC);
    public static final Feature<MultiRandomSpreadFeatureConfig> MULTI_RANDOM_SPREAD = new MultiRandomSpreadFeature(MultiRandomSpreadFeatureConfig.CODEC);
    public static final Feature<HugeFungusFeatureConfig> HUGE_TWISTED_FUNGUS = new HugeTwistedFungusFeature(HugeFungusFeatureConfig.CODEC);
    public static final Feature<BlockSpikeFeatureConfig> BLOCK_SPIKE = new BlockSpikeFeature(BlockSpikeFeatureConfig.CODEC);
    public static final Feature<RockFeatureConfig> ROCK = new RockFeature(RockFeatureConfig.CODEC);
    public static final Feature<TwoSimpleBlocksFeatureConfig> SIMPLE_BLOCK_WITH_BASE = new SimpleBlockWithBaseFeature(TwoSimpleBlocksFeatureConfig.CODEC);
    public static final Feature<SingleStateFeatureConfig> BLOCK_PILLAR = new BlockPillarFeature(SingleStateFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "random_spread"), RANDOM_SPREAD);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "multi_random_spread"), MULTI_RANDOM_SPREAD);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "huge_twisted_fungus"), HUGE_TWISTED_FUNGUS);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "block_spike"), BLOCK_SPIKE);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "rock"), ROCK);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "simple_block_with_base"), SIMPLE_BLOCK_WITH_BASE);
        Registry.register(Registry.FEATURE, new Identifier(GreatBigWorld.NAMESPACE, "block_pillar"), BLOCK_PILLAR);
    }
}
