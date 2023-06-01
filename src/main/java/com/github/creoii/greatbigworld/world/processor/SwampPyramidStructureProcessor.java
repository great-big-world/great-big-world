package com.github.creoii.greatbigworld.world.processor;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.registry.GBWStructures;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.state.property.Properties;
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
    private static final RegistryEntryList<Block> STAIRS = RegistryEntryList.of(RegistryEntry::of, Blocks.COBBLESTONE_STAIRS, GBWBlocks.COBBLESTONE_BRICK_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS, GBWBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS);
    private static final Map<Block, BlockStateProvider> REPLACEMENT_MAP = Util.make(Maps.newHashMap(), replacements -> {
        replacements.put(GBWBlocks.COBBLESTONE_BRICKS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE.getDefaultState(), 2).add(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 5).add(GBWBlocks.MOSSY_COBBLESTONE_BRICKS.getDefaultState(), 9).add(GBWBlocks.CHISELED_COBBLESTONE_BRICKS.getDefaultState(), 1).build()));
        replacements.put(GBWBlocks.COBBLESTONE_BRICK_SLAB, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_SLAB.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_SLAB.getDefaultState(), 3).add(GBWBlocks.MOSSY_COBBLESTONE_BRICK_SLAB.getDefaultState(), 5).build()));
        replacements.put(GBWBlocks.COBBLESTONE_BRICK_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_STAIRS.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_STAIRS.getDefaultState(), 3).add(GBWBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS.getDefaultState(), 5).build()));
        replacements.put(GBWBlocks.COBBLESTONE_BRICK_WALL, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_WALL.getDefaultState(), 1).add(Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState(), 3).add(GBWBlocks.MOSSY_COBBLESTONE_BRICK_WALL.getDefaultState(), 5).build()));
    });

    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos.up());
        if (random.nextFloat() < .95f && world.getBlockState(currentBlockInfo.pos.up()).isIn(STAIRS)) {
            if (currentBlockInfo.state.isOf(GBWBlocks.COBBLESTONE_BRICKS)) {
                return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, GBWBlocks.CHISELED_COBBLESTONE_BRICKS.getDefaultState(), currentBlockInfo.nbt);
            }
        }
        random = data.getRandom(currentBlockInfo.pos);
        BlockState current = currentBlockInfo.state;
        Block block = current.getBlock();
        if (random.nextFloat() < .6f && REPLACEMENT_MAP.containsKey(block)) {
            if (random.nextFloat() < .08f) return null;
            BlockState state = REPLACEMENT_MAP.get(block).get(random, currentBlockInfo.pos);
            if (current.contains(StairsBlock.FACING)) {
                state = state.with(StairsBlock.FACING, current.get(StairsBlock.FACING));
            }

            if (current.contains(StairsBlock.HALF)) {
                state = state.with(StairsBlock.HALF, current.get(StairsBlock.HALF));
            }

            if (current.contains(StairsBlock.SHAPE)) {
                state = state.with(StairsBlock.SHAPE, current.get(StairsBlock.SHAPE));
            }

            if (current.contains(SlabBlock.TYPE)) {
                state = state.with(SlabBlock.TYPE, current.get(SlabBlock.TYPE));
            }

            if (current.contains(Properties.WATERLOGGED)) {
                state = state.with(Properties.WATERLOGGED, current.get(Properties.WATERLOGGED));
            }

            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, state, currentBlockInfo.nbt);
        }
        return currentBlockInfo;
    }

    protected StructureProcessorType<?> getType() {
        return GBWStructures.SWAMP_PYRAMID_PROCESSOR;
    }
}