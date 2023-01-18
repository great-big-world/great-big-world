package com.github.creoii.greatbigworld.world.processor;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.StructureRegistry;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.Map;

public class SwampPyramidStructureProcessor extends StructureProcessor {
    public static final SwampPyramidStructureProcessor INSTANCE = new SwampPyramidStructureProcessor();
    public static final Codec<SwampPyramidStructureProcessor> CODEC = Codec.unit(() -> INSTANCE);
    private final Map<Block, BlockStateProvider> replacementMap = Util.make(Maps.newHashMap(), (replacements) -> {
        replacements.put(BlockRegistry.COBBLESTONE_BRICKS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICKS.getDefaultState(), 5).build()));
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_SLAB, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_SLAB.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_SLAB.getDefaultState(), 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_SLAB.getDefaultState(), 5).build()));
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_STAIRS.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_STAIRS.getDefaultState(), 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_STAIRS.getDefaultState(), 5).build()));
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_WALL, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_WALL.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState(), 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_WALL.getDefaultState(), 5).build()));
    });

    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos);
        Block block = currentBlockInfo.state.getBlock();
        if (random.nextFloat() < .6f && replacementMap.containsKey(block)) {
            if (random.nextFloat() < .1f) return null;
            BlockState state = replacementMap.get(block).get(random, currentBlockInfo.pos);
            BlockState blockState = currentBlockInfo.state;
            if (blockState.contains(StairsBlock.FACING)) {
                state = state.with(StairsBlock.FACING, blockState.get(StairsBlock.FACING));
            }

            if (blockState.contains(StairsBlock.HALF)) {
                state = state.with(StairsBlock.HALF, blockState.get(StairsBlock.HALF));
            }

            if (blockState.contains(StairsBlock.SHAPE)) {
                state = state.with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE));
            }

            if (blockState.contains(SlabBlock.TYPE)) {
                state = state.with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE));
            }

            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, state, currentBlockInfo.nbt);
        }
        return currentBlockInfo;
    }

    protected StructureProcessorType<?> getType() {
        return StructureRegistry.SWAMP_PYRAMID_PROCESSOR;
    }
}