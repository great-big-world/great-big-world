package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;

import java.util.ArrayList;
import java.util.List;

public class GenerationUtil {
    public static final Direction[] HORIZONTAL = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
    public static final double DEG_2_RAD = .017453292519943295d;

    public static BlockPos[] findVerticalSurfaces(StructureWorldAccess world, BlockPos origin, int upSearchLength, int downSearchLength, BlockPredicate predicate) {
        BlockPos[] ret = new BlockPos[2];

        BlockPos.Mutable mutable = origin.mutableCopy();
        for (int i = 0; i < upSearchLength; ++i) {
            if (predicate.test(world, mutable)) {
                ret[0] = mutable;
                break;
            }
            mutable.move(Direction.UP);
        }

        mutable = origin.mutableCopy();
        for (int i = 0; i < downSearchLength; ++i) {
            if (predicate.test(world, mutable)) {
                ret[0] = mutable;
                break;
            }
            mutable.move(Direction.DOWN);
        }

        return ret;
    }

    //region Ellipses
    public static List<BlockPos> circle(BlockPos center, int radius) {
        return ellipse(center, radius, radius);
    }

    public static List<BlockPos> irregularCircle(BlockPos center, int radius, IntProvider irregularityProvider) {
        return irregularEllipse(center, radius, radius, irregularityProvider);
    }

    public static List<BlockPos> ellipse(BlockPos center, int xRadius, int zRadius) {
        return irregularEllipse(center, xRadius, zRadius, ConstantIntProvider.create(0));
    }

    /**
     * Picks a number of steps based on the radii. At each step, calculate the distance based on
     * the radii and irregularity.
     *
     * @param center - Center position of the ellipse.
     * @param xRadius - Radius on the x axis.
     * @param zRadius - Radius on the z axis.
     * @param irregularityProvider - Variation of the radius (Keep low for low radii)
     * @return - Array of block positions in the formation of an irregular ellipse.
     * @author - creoii
     */
    public static List<BlockPos> irregularEllipse(BlockPos center, int xRadius, int zRadius, IntProvider irregularityProvider) {
        List<BlockPos> ret = new ArrayList<>();
        int steps = (xRadius * 2) + (zRadius * 2);
        double angleStep = 360d / steps;

        int xRadius1 = xRadius;
        int zRadius1 = zRadius;

        double angle;
        int prevRandX = 0;
        int prevRandZ = 0;
        for (int i = 0; i < steps; ++i) {
            angle = i * angleStep;

            int irregularity = irregularityProvider.get(GreatBigWorld.RANDOM);
            if (irregularity > 0) {
                int randX = GreatBigWorld.RANDOM.nextInt(irregularity + irregularity + 1) - irregularity;
                int randZ = GreatBigWorld.RANDOM.nextInt(irregularity + irregularity + 1) - irregularity;
                xRadius1 = MathHelper.clamp(xRadius1 + randX, xRadius - irregularity, xRadius + irregularity);
                zRadius1 = MathHelper.clamp(zRadius1 + randZ, zRadius - irregularity, zRadius + irregularity);

                if (Math.abs(randX - prevRandX) > irregularity) {
                    --i;
                }
                if (Math.abs(randZ - prevRandZ) > irregularity) {
                    --i;
                }

                prevRandX = randX;
                prevRandZ = randZ;
            }

            int x = (int) (center.getX() + Math.cos(angle * DEG_2_RAD) * xRadius1);
            int z = (int) (center.getZ() + Math.sin(angle * DEG_2_RAD) * zRadius1);
            ret.add(new BlockPos(x, center.getY(), z));
        }

        return ret;
    }
    //endregion
}
