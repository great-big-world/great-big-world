package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.HashSet;

public class GenerationUtil {
    public static HashSet<BlockPos> randomWalk(BlockPos start, int iterations, int walkLength, boolean randomStart, int randomStartX, int randomStartY) {
        HashSet<BlockPos> path = new HashSet<>();
        path.add(start);
        BlockPos prev = start;

        BlockPos next;
        for (int i = 0; i < iterations; ++i)
        {
            for (int j = 0; j < walkLength; ++j)
            {
                next = prev.offset(Direction.random(GreatBigWorld.RANDOM));
                path.add(next);
                prev = next;
            }
            prev = randomStart ? start.offset(Direction.Type.HORIZONTAL.random(GreatBigWorld.RANDOM), GreatBigWorld.RANDOM.nextBetween(randomStartX, randomStartY)) : start;
        }

        return path;
    }
}
