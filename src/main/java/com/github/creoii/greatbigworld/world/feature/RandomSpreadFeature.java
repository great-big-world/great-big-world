package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.GenerationUtil;
import com.github.creoii.greatbigworld.world.feature.config.RandomSpreadFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class RandomSpreadFeature extends Feature<RandomSpreadFeatureConfig> {
    public RandomSpreadFeature(Codec<RandomSpreadFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<RandomSpreadFeatureConfig> context) {
        RandomSpreadFeatureConfig config = context.getConfig();
        for (BlockPos pos : GenerationUtil.randomWalk(context.getOrigin(), config.iterations().get(GreatBigWorld.RANDOM), config.walkLength().get(GreatBigWorld.RANDOM), config.randomStart(), config.randomStartRadius().get(0), config.randomStartRadius().get(1), true)) {
            if (context.getWorld().getFluidState(pos.down()).isOf(Fluids.WATER)) {
                context.getWorld().setBlockState(pos, config.state().getBlockState(GreatBigWorld.RANDOM, pos), 3);
            }
        }
        return false;
    }
}
