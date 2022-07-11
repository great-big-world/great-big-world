package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.world.feature.config.TwoSimpleBlocksFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SimpleBlockWithBaseFeature extends Feature<TwoSimpleBlocksFeatureConfig> {
    public SimpleBlockWithBaseFeature(Codec<TwoSimpleBlocksFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<TwoSimpleBlocksFeatureConfig> context) {
        TwoSimpleBlocksFeatureConfig simpleBlockFeatureConfig = context.getConfig();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        if ((simpleBlockFeatureConfig.placeOn.isEmpty() || simpleBlockFeatureConfig.placeOn.contains(structureWorldAccess.getBlockState(blockPos.down()))) && (simpleBlockFeatureConfig.placeIn.isEmpty() || simpleBlockFeatureConfig.placeIn.contains(structureWorldAccess.getBlockState(blockPos))) && (simpleBlockFeatureConfig.placeUnder.isEmpty() || simpleBlockFeatureConfig.placeUnder.contains(structureWorldAccess.getBlockState(blockPos.up())))) {
            BlockState blockState = simpleBlockFeatureConfig.toPlace.getBlockState(context.getRandom(), blockPos);
            BlockState blockState1 = simpleBlockFeatureConfig.base.getBlockState(context.getRandom(), blockPos);
            boolean bl = false;
            if (blockState.canPlaceAt(structureWorldAccess, blockPos)) {
                if (blockState.getBlock() instanceof TallPlantBlock tallPlantBlock) {
                    if (!structureWorldAccess.isAir(blockPos.up())) {
                        return false;
                    }

                    tallPlantBlock.placeAt(structureWorldAccess, blockState, blockPos, 2);
                } else {
                    structureWorldAccess.setBlockState(blockPos, blockState, 2);
                }
                bl = true;
            }

            if (blockState1.canPlaceAt(structureWorldAccess, blockPos.down())) {
                structureWorldAccess.setBlockState(blockPos.down(), blockState1, 2);
                bl = true;
            }

            return bl;
        }

        return false;
    }
}