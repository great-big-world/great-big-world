package com.github.creoii.greatbigworld.world.placer;

import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.BiConsumer;

public class AspenFoliagePlacer extends FoliagePlacer {
    public static final Codec<AspenFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> {
        return fillFoliagePlacerFields(instance).and(IntProvider.createValidatingCodec(0, 16).fieldOf("trunk_height").forGetter(placer -> {
            return placer.trunkHeight;
        })).apply(instance, AspenFoliagePlacer::new);
    });
    private final IntProvider trunkHeight;

    public AspenFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return PlacerRegistry.ASPEN_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        int leafBottom = offset - foliageHeight;

        for (int y = offset; y >= leafBottom; --y) {
            int baseWidth = radius;
            if (y >= offset - 1) baseWidth = radius - 2;
            else if (y >= offset - 3 || y <= leafBottom + 1 || random.nextInt(4) == 0) baseWidth = radius - 1;

            int width = baseWidth + treeNode.getFoliageRadius();

            BlockPos.Mutable mutable = treeNode.getCenter().mutableCopy();
            for (int i = 0; i < 4 + random.nextInt(5); ++i) {
                mutable = mutable.set(treeNode.getCenter(), 0, y, 0);
                int x = mutable.getX();
                int z = mutable.getZ();
                int length = 2 + width + random.nextInt(6);

                for (int j = 0; j < length && Math.abs(x - mutable.getX()) <= width && Math.abs(z - mutable.getZ()) <= width; ++j) {
                    placeFoliageBlock(world, placer, random, config, mutable);
                    BlockPos.Mutable next = mutable.move(Direction.Type.HORIZONTAL.random(random));
                    while (!mutable.isWithinDistance(next, 2 + width)) {
                        next = mutable.move(Direction.Type.HORIZONTAL.random(random));
                    }
                    mutable = next;
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
        return false;
    }
}