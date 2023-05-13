package com.github.creoii.greatbigworld.world.decorator;

import com.github.creoii.greatbigworld.block.HangingLeavesBlock;
import com.github.creoii.greatbigworld.main.registry.GBWDecorators;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
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
    private final int minLength;
    private final int maxLength;
    private final float probability;
    private final PositionInclusion positionInclusion;

    public HangingLeavesTreeDecorator(BlockState state, int minLength, int maxLength, float probability, PositionInclusion positionInclusion) {
        this.state = state;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.probability = probability;
        this.positionInclusion = positionInclusion;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return GBWDecorators.HANGING_LEAVES_TREE_DECORATOR;
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
                    blockstate = state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                    length = (generator.getRandom().nextInt(maxLength) + minLength) - 1;
                    for (int j = pos.down().getY(); j >= pos.down().getY() - length; --j) {
                        if (j == pos.down().getY() - length)
                            blockstate = blockstate.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.SMALL);
                        place = new BlockPos(pos.getX(), j, pos.getZ());
                        if (generator.isAir(place)) generator.replace(place, blockstate);
                    }
                }
            }
        }
    }

    public enum PositionInclusion implements StringIdentifiable {
        LOGS,
        LEAVES,
        ALL;

        @SuppressWarnings("deprecation")
        public static final StringIdentifiable.Codec<PositionInclusion> CODEC = StringIdentifiable.createCodec(PositionInclusion::values);

        @Override
        public String asString() {
            return name().toLowerCase();
        }
    }
}