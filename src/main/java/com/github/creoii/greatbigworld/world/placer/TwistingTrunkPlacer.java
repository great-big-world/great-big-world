package com.github.creoii.greatbigworld.world.placer;

import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class TwistingTrunkPlacer extends TrunkPlacer {
    protected static final ArrayList<Direction> HORIZONTAL = Lists.newArrayList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    protected static final ArrayList<Direction> VERTICAL = Lists.newArrayList(Direction.UP, Direction.DOWN);

    public static final Codec<TwistingTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.createValidatingCodec(1, 64).fieldOf("twists").forGetter((placer) -> {
            return placer.twists;
        }), IntProvider.createValidatingCodec(1, 64).fieldOf("horizontal_length").forGetter((placer) -> {
            return placer.horizontalLength;
        }), IntProvider.createValidatingCodec(1, 64).fieldOf("vertical_length").forGetter((placer) -> {
            return placer.verticalLength;
        }), IntProvider.createValidatingCodec(0, 4).fieldOf("branches").forGetter((placer) -> {
            return placer.verticalLength;
        }), Codec.BOOL.fieldOf("allow_multiple_horizontal").orElse(false).forGetter((placer) -> {
            return placer.allowMultipleHorizontal;
        }))).apply(instance, TwistingTrunkPlacer::new);
    });
    protected final IntProvider twists;
    protected final IntProvider horizontalLength;
    protected final IntProvider verticalLength;
    protected final IntProvider branches;
    protected final boolean allowMultipleHorizontal;

    public TwistingTrunkPlacer(int i, int j, int k, IntProvider twists, IntProvider horizontalLength, IntProvider verticalLength, IntProvider branches, boolean allowMultipleHorizontal) {
        super(i, j, k);
        this.twists = twists;
        this.horizontalLength = horizontalLength;
        this.verticalLength = verticalLength;
        this.branches = branches;
        this.allowMultipleHorizontal = allowMultipleHorizontal;
    }

    protected TrunkPlacerType<?> getType() {
        return PlacerRegistry.TWISTING_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();

        for (int i = 0; i <= height; ++i) {
            getAndSetState(world, replacer, random, startPos.up(i), config);
        }

        int branchCount = branches.get(random) - 1;
        ArrayList<Direction> directions = new ArrayList<>(HORIZONTAL);
        Direction horizontal = directions.get(random.nextInt(Math.max(1, directions.size() - 1)));
        for (int i = 0; i <= branchCount; ++i) {
            if (!allowMultipleHorizontal) directions.remove(horizontal);
            BlockPos current = startPos.up(height);
            boolean flag = false;
            int twistCount = twists.get(random);

            int length;
            for (int j = 0; j <= twistCount; ++j) {
                Direction direction;
                if (allowMultipleHorizontal) {
                    direction = random.nextInt(3) == 0 ? Direction.UP : random.nextBoolean() ? horizontal : horizontal;
                } else direction = flag ? Direction.UP : horizontal;

                if (direction == Direction.UP) length = verticalLength.get(random);
                else length = horizontalLength.get(random);

                for (int k = 0; k <= length - 1; ++k) {
                    getAndSetState(world, replacer, random, current.offset(direction, k), config, (state) -> state.with(PillarBlock.AXIS, direction.getAxis()));
                }

                current = current.offset(direction, length);
                if (j == twistCount) list.add(new FoliagePlacer.TreeNode(current, 0, false));
                if (!allowMultipleHorizontal) flag = !flag;
            }

            if (directions.size() > 0) horizontal = directions.get(random.nextInt(Math.max(1, directions.size() - 1)));
        }
        return list;
    }
}
