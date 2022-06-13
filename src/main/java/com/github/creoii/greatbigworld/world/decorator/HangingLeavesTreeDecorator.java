package com.github.creoii.greatbigworld.world.decorator;

import com.github.creoii.greatbigworld.block.HangingLeavesBlock;
import com.github.creoii.greatbigworld.main.registry.DecoratorRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.intRange(0, 10).fieldOf("min_length").forGetter((config) -> {
            return config.minLength;
        }), Codec.intRange(0, 10).fieldOf("max_length").forGetter((config) -> {
            return config.maxLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> {
            return config.probability;
        }), Codec.BOOL.fieldOf("with_log_positions").orElse(false).forGetter((config) -> {
            return config.withLogPositions;
        })).apply(p_236568_0_, HangingLeavesTreeDecorator::new);
    });
    private final BlockState state;
    private final int minLength;
    private final int maxLength;
    private final float probability;
    private final boolean withLogPositions;

    public HangingLeavesTreeDecorator(BlockState state, int minLength, int maxLength, float probability, boolean withLogPositions) {
        this.state = state;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.probability = probability;
        this.withLogPositions = withLogPositions;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return DecoratorRegistry.HANGING_LEAVES_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        BlockState blockstate;
        int length;
        BlockPos place;
        ObjectArrayList<BlockPos> positions = generator.getLeavesPositions();
        if (withLogPositions) positions.addAll(generator.getLogPositions());
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
}