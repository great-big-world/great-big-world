package com.github.creoii.greatbigworld.world.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;

public class HugeTwistedFungusFeature extends Feature<HugeFungusFeatureConfig> {
    protected static final ArrayList<Direction> HORIZONTAL = Lists.newArrayList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

    public HugeTwistedFungusFeature(Codec<HugeFungusFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<HugeFungusFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        ChunkGenerator chunkGenerator = context.getGenerator();
        HugeFungusFeatureConfig hugeFungusFeatureConfig = (HugeFungusFeatureConfig)context.getConfig();
        Block block = hugeFungusFeatureConfig.validBaseBlock.getBlock();
        BlockPos blockPos2 = null;
        BlockState blockState = structureWorldAccess.getBlockState(blockPos.down());
        if (blockState.isOf(block)) {
            blockPos2 = blockPos;
        }

        if (blockPos2 == null) {
            return false;
        } else {
            int i = MathHelper.nextInt(random, 4, 13);
            if (random.nextInt(12) == 0) {
                i *= 2;
            }

            if (!hugeFungusFeatureConfig.planted) {
                int j = chunkGenerator.getWorldHeight();
                if (blockPos2.getY() + i + 1 >= j) {
                    return false;
                }
            }

            boolean bl = !hugeFungusFeatureConfig.planted && random.nextFloat() < 0.06F;
            structureWorldAccess.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 4);
            generateStem(structureWorldAccess, random, hugeFungusFeatureConfig, blockPos2);
            return true;
        }
    }

    private static boolean isReplaceable(WorldAccess world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> state.getMaterial().isReplaceable());
    }

    private void generateStem(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos pos) {
        int height = random.nextBetween(3, 4);

        for (int i = 0; i <= height; ++i) {
            world.setBlockState(pos.up(i), config.stemState, 3);
        }

        int branchCount = random.nextBetween(1, 2);
        ArrayList<Direction> directions = new ArrayList<>(HORIZONTAL);
        Direction horizontal = directions.get(random.nextInt(Math.max(1, directions.size() - 1)));
        for (int i = 0; i <= branchCount; ++i) {
            directions.remove(horizontal);
            BlockPos current = pos.up(height);
            boolean flag = false;
            int twistCount = random.nextBetween(1, 2);

            int length;
            for (int j = 0; j <= twistCount; ++j) {
                Direction direction = flag ? Direction.UP : horizontal;
                length = random.nextBetween(1, 3);

                for (int k = 0; k <= length - 1; ++k) {
                    world.setBlockState(current.offset(direction, k), config.stemState.with(PillarBlock.AXIS, direction.getAxis()), 3);
                }

                current = current.offset(direction, length);
                if (j == twistCount) {
                    generateHat(world, random, config, current, i);

                };
                flag = !flag;
            }

            if (directions.size() > 0) horizontal = directions.get(random.nextInt(Math.max(1, directions.size() - 1)));
        }
    }

    private void generateHat(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos pos, int hatHeight) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        boolean bl = config.hatState.isOf(Blocks.NETHER_WART_BLOCK);
        int i = Math.min(random.nextInt(1 + hatHeight / 3) + 5, hatHeight);
        int j = hatHeight - i;

        for(int k = j; k <= hatHeight; ++k) {
            int l = k < hatHeight - random.nextInt(3) ? 2 : 1;
            if (i > 8 && k < j + 4) {
                l = 3;
            }

            for(int m = -l; m <= l; ++m) {
                for(int n = -l; n <= l; ++n) {
                    boolean bl2 = m == -l || m == l;
                    boolean bl3 = n == -l || n == l;
                    boolean bl4 = !bl2 && !bl3 && k != hatHeight;
                    boolean bl5 = bl2 && bl3;
                    mutable.set(pos, m, k, n);
                    if (isReplaceable(world, mutable)) {
                        if (config.planted && !world.getBlockState(mutable.down()).isAir()) {
                            world.breakBlock(mutable, true);
                        }

                        if (bl4) {
                            this.placeHatBlock(world, random, config, mutable, 0.1F, 0.2F, bl ? 0.1F : 0.0F);
                        } else if (bl5) {
                            this.placeHatBlock(world, random, config, mutable, 0.01F, 0.7F, bl ? 0.083F : 0.0F);
                        } else {
                            this.placeHatBlock(world, random, config, mutable, 5.0E-4F, 0.98F, bl ? 0.07F : 0.0F);
                        }
                    }
                }
            }
        }

    }

    private void placeHatBlock(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos.Mutable pos, float decorationChance, float generationChance, float vineChance) {
        if (random.nextFloat() < decorationChance) {
            setBlockState(world, pos, config.decorationState);
        } else if (random.nextFloat() < generationChance) {
            setBlockState(world, pos, config.hatState);
        }
    }
}
