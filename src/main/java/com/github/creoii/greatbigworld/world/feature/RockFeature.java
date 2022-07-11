package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.world.feature.config.RockFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class RockFeature extends Feature<RockFeatureConfig> {
    public RockFeature(Codec<RockFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<RockFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();

        RockFeatureConfig config;
        for (config = context.getConfig(); blockPos.getY() > structureWorldAccess.getBottomY() + 3; blockPos = blockPos.down()) {
            if (!structureWorldAccess.isAir(blockPos.down())) {
                BlockState blockState = structureWorldAccess.getBlockState(blockPos.down());
                if (isSoil(blockState) || isStone(blockState) || blockState.isIn(BlockTags.SAND)) {
                    break;
                }
            }
        }

        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 3) return false;
        else {
            for (int i = 0; i < 3; ++i) {
                int j = random.nextInt(config.size().get(random));
                int k = random.nextInt(config.size().get(random));
                int l = random.nextInt(config.size().get(random));
                float f = (float)(j + k + l) * 0.333F + 0.5F;

                for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-j, -k, -l), blockPos.add(j, k, l))) {
                    if (blockPos2.getSquaredDistance(blockPos) <= (double) (f * f)) {
                        structureWorldAccess.setBlockState(blockPos2, config.state(), 4);
                    }
                }

                blockPos = blockPos.add(-1 + random.nextInt(config.size().get(random)), -random.nextInt(config.size().get(random)), -1 + random.nextInt(config.size().get(random)));
            }

            return true;
        }
    }
}