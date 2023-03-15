package com.github.creoii.greatbigworld.world.decorator;

import com.github.creoii.greatbigworld.block.HangingLeavesBlock;
import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.jetbrains.annotations.Nullable;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), BlockState.CODEC.optionalFieldOf("leaves_state", Blocks.AIR.getDefaultState()).forGetter(config -> {
            return config.leavesState;
        }), Codec.intRange(0, 10).fieldOf("min_length").forGetter(config -> {
            return config.minLength;
        }), Codec.intRange(0, 10).fieldOf("max_length").forGetter(config -> {
            return config.maxLength;
        }), Codec.floatRange(0f, 1f).fieldOf("probability").forGetter(config -> {
            return config.probability;
        }), PositionInclusion.CODEC.fieldOf("position_inclusion").orElse(PositionInclusion.LEAVES).forGetter(config -> {
            return config.positionInclusion;
        })).apply(instance, HangingLeavesTreeDecorator::new);
    });
    private final BlockState state;
    private final BlockState leavesState;
    private final int minLength;
    private final int maxLength;
    private final float probability;
    private final PositionInclusion positionInclusion;

    public HangingLeavesTreeDecorator(BlockState state, BlockState leavesState, int minLength, int maxLength, float probability, PositionInclusion positionInclusion) {
        this.state = state;
        this.leavesState = leavesState;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.probability = probability;
        this.positionInclusion = positionInclusion;
    }

    public HangingLeavesTreeDecorator(BlockState state, int minLength, int maxLength, float probability, PositionInclusion positionInclusion) {
        this(state, Blocks.AIR.getDefaultState(), minLength, maxLength, probability, positionInclusion);
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return PlacerRegistry.HANGING_LEAVES_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        BlockState blockstate;
        int length;
        BlockPos place;
        ObjectArrayList<BlockPos> positions = new ObjectArrayList<>();
        switch (positionInclusion) {
            case ALL -> {
                positions.addAll(generator.getLogPositions());
                positions.addAll(generator.getLeavesPositions());
            }
            case LOGS -> positions.addAll(generator.getLogPositions());
            case LEAVES -> positions.addAll(generator.getLeavesPositions());
        }
        for (BlockPos pos : positions) {
            if (!(generator.getRandom().nextFloat() >= probability)) {
                if (generator.isAir(pos.down()) && state.getBlock() instanceof HangingLeavesBlock) {
                    boolean placeLeaves = leavesState != Blocks.AIR.getDefaultState();
                    blockstate = placeLeaves ? leavesState : state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                    length = (generator.getRandom().nextInt(maxLength) + minLength) - 1;
                    int j, bottom = pos.down().getY() - length;
                    if (placeLeaves) {
                        for (j = pos.down().getY(); j >= bottom; --j) {
                            if (j == bottom) {
                                blockstate = state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                            } else if (j == bottom + 1) {
                                blockstate = state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                            }
                            place = new BlockPos(pos.getX(), j, pos.getZ());
                            if (generator.isAir(place)) generator.replace(place, blockstate);
                            else break;
                        }
                    } else {
                        for (j = pos.down().getY(); j >= bottom; --j) {
                            if (j == bottom) {
                                blockstate = state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                            }
                            place = new BlockPos(pos.getX(), j, pos.getZ());
                            if (generator.isAir(place)) generator.replace(place, blockstate);
                            else break;
                        }
                    }
                }
            }
        }
    }
}