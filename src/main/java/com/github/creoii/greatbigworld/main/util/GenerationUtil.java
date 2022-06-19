package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.HashSet;

public class GenerationUtil {
    public static HashSet<BlockPos> randomWalk(BlockPos start, int iterations, int walkLength, boolean randomStart, int randomStartX, int randomStartY, boolean horizontal) {
        HashSet<BlockPos> path = new HashSet<>();
        path.add(start);
        BlockPos prev = start;

        BlockPos next;
        for (int i = 0; i < iterations; ++i)
        {
            for (int j = 0; j < walkLength; ++j)
            {
                next = prev.offset(horizontal ? Direction.Type.HORIZONTAL.random(GreatBigWorld.RANDOM) : Direction.random(GreatBigWorld.RANDOM));
                path.add(next);
                prev = next;
            }
            prev = randomStart ? start.offset(horizontal ? Direction.Type.HORIZONTAL.random(GreatBigWorld.RANDOM) : Direction.random(GreatBigWorld.RANDOM), GreatBigWorld.RANDOM.nextBetween(randomStartX, randomStartY)) : start;
        }

        return path;
    }

    public static BlockPos randomInRange(Random random, BlockPos center, int xRadius, int yRadius, int zRadius) {
        return new BlockPos(
                random.nextBetween(center.getX() - xRadius, center.getX() + xRadius),
                random.nextBetween(center.getY() - yRadius, center.getY() + yRadius),
                random.nextBetween(center.getZ() - zRadius, center.getZ() + zRadius)
        );
    }
}
