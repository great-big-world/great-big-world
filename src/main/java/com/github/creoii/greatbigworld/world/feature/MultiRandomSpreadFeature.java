package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.FeatureRegistry;
import com.github.creoii.greatbigworld.main.util.GenerationUtil;
import com.github.creoii.greatbigworld.world.feature.config.MultiRandomSpreadFeatureConfig;
import com.github.creoii.greatbigworld.world.feature.config.RandomSpreadFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class MultiRandomSpreadFeature extends Feature<MultiRandomSpreadFeatureConfig> {
    public MultiRandomSpreadFeature(Codec<MultiRandomSpreadFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<MultiRandomSpreadFeatureConfig> context) {
        MultiRandomSpreadFeatureConfig config = context.getConfig();
        for (int i = 0; i < config.tries().get(GreatBigWorld.RANDOM); ++i) {
            RandomSpreadFeatureConfig randomSpread = config.randomSpreads().get(GreatBigWorld.RANDOM.nextInt(config.randomSpreads().size()));
            for (BlockPos pos : GenerationUtil.randomWalk(context.getOrigin().offset(Direction.Type.HORIZONTAL.random(GreatBigWorld.RANDOM), GreatBigWorld.RANDOM.nextBetween(config.randomStartRadius().get(0), config.randomStartRadius().get(1))), randomSpread.iterations().get(GreatBigWorld.RANDOM), randomSpread.walkLength().get(GreatBigWorld.RANDOM), randomSpread.randomStart(), config.randomStartRadius().get(0), config.randomStartRadius().get(1))) {
                context.getWorld().setBlockState(pos, randomSpread.state().getBlockState(GreatBigWorld.RANDOM, pos), 3);
            }
        }
        return false;
    }
}
