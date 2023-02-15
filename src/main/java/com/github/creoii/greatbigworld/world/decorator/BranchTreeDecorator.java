package com.github.creoii.greatbigworld.world.decorator;

import com.github.creoii.greatbigworld.main.registry.PlacerRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.function.Predicate;

public class BranchTreeDecorator extends TreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter(decorator -> {
            return decorator.state;
        }), Codec.FLOAT.fieldOf("chance").forGetter(decorator -> {
            return decorator.chance;
        }), Codec.BOOL.fieldOf("random_facing").forGetter(decorator -> {
            return decorator.randomFacing;
        }), Codec.STRING.fieldOf("replaceable").forGetter(decorator -> {
            return decorator.replaceableTag.toString();
        }), IntProvider.createValidatingCodec(0, 16).fieldOf("start_offset").forGetter(decorator -> {
            return decorator.startOffset;
        }), Codec.BOOL.fieldOf("no_multi_vertical_placement").orElse(false).forGetter(decorator -> {
            return decorator.noMultiVerticalPlacement;
        })).apply(instance, BranchTreeDecorator::new);
    });
    private final BlockState state;
    private final float chance;
    private final boolean randomFacing;
    private final TagKey<Block> replaceableTag;
    private final IntProvider startOffset;
    private final boolean noMultiVerticalPlacement;

    public BranchTreeDecorator(BlockState state, float chance, boolean randomFacing, String replaceableTag, IntProvider startOffset, boolean noMultiVerticalPlacement) {
        this.state = state;
        this.chance = chance;
        this.randomFacing = randomFacing;
        this.replaceableTag = TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(replaceableTag));
        this.startOffset = startOffset;
        this.noMultiVerticalPlacement = noMultiVerticalPlacement;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return PlacerRegistry.BRANCH_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.chance)) {
            TestableWorld world = generator.getWorld();
            Direction direction;
            for (int i = startOffset.get(random); i < generator.getLogPositions().size(); ++i) {
                BlockPos pos = generator.getLogPositions().get(i);
                direction = Direction.Type.HORIZONTAL.random(random);
                BlockPos blockpos1 = pos.offset(direction);
                if (random.nextFloat() <= chance && world.testBlockState(blockpos1, BlockStatePredicate.forBlock(Blocks.AIR).or(state1 -> state1.isIn(replaceableTag)))) {
                    if (noMultiVerticalPlacement && world.testBlockState(blockpos1.down(), Predicate.not(BlockStatePredicate.forBlock(state.getBlock()))))
                        return;

                    if (state.getProperties().contains(Properties.FACING)) {
                        generator.replace(blockpos1, state.with(Properties.FACING, randomFacing ? Direction.random(random) : direction.getOpposite()));
                    } else if (state.getProperties().contains(Properties.HORIZONTAL_FACING)) {
                        generator.replace(blockpos1, state.with(Properties.HORIZONTAL_FACING, randomFacing ? Direction.Type.HORIZONTAL.random(random) : direction.getOpposite()));
                    } else if (state.getProperties().contains(Properties.AXIS)) {
                        generator.replace(blockpos1, state.with(Properties.AXIS, randomFacing ? Direction.random(random).getAxis() : direction.getOpposite().getAxis()));
                    } else if (state.getProperties().contains(Properties.HORIZONTAL_AXIS)) {
                        generator.replace(blockpos1, state.with(Properties.HORIZONTAL_AXIS, randomFacing ? Direction.Type.HORIZONTAL.random(random).getAxis() : direction.getOpposite().getAxis()));
                    } else generator.replace(blockpos1, state);
                }
            }
        }
    }
}