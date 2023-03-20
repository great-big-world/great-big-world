package com.github.creoii.greatbigworld.world.placer;

import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> {
        return fillFoliagePlacerFields(instance).and(IntProvider.createValidatingCodec(0, 16).fieldOf("trunk_height").forGetter(placer -> {
            return placer.trunkHeight;
        })).apply(instance, PalmFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return PlacerRegistry.PALM_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.getCenter().down();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (Direction direction : Direction.values()) {
            for (int i = 0; i < radius + 1; ++i) {
                mutable.set(center.offset(direction, i));
                placeFoliageBlock(world, placer, random, config, mutable);

                if (i <= radius) {
                    if (i == radius && random.nextBoolean()) continue;
                    switch (direction.getAxis()) {
                        case X -> mutable.set(center.offset(direction, i).offset(Direction.Axis.Y, i).offset(Direction.Axis.Z, i));
                        case Y -> mutable.set(center.offset(direction, i).offset(Direction.Axis.X, i).offset(Direction.Axis.Z, i));
                        case Z -> mutable.set(center.offset(direction, i).offset(Direction.Axis.X, i).offset(Direction.Axis.Y, i));
                    }
                    placeFoliageBlock(world, placer, random, config, mutable);
                    switch (direction.getAxis()) {
                        case X -> mutable.set(center.offset(direction, i).offset(Direction.Axis.Y, -i).offset(Direction.Axis.Z, -i));
                        case Y -> mutable.set(center.offset(direction, i).offset(Direction.Axis.X, -i).offset(Direction.Axis.Z, -i));
                        case Z -> mutable.set(center.offset(direction, i).offset(Direction.Axis.X, -i).offset(Direction.Axis.Y, -i));
                    }
                    placeFoliageBlock(world, placer, random, config, mutable);
                }
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return Math.max(4, trunkHeight - this.trunkHeight.get(random));
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0);
    }
}
