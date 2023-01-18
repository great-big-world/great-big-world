package com.github.creoii.greatbigworld.world.processor;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.StructureRegistry;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

import java.util.Map;
import java.util.Optional;

public class SwampPyramidStructureProcessor extends StructureProcessor {
    public static final SwampPyramidStructureProcessor INSTANCE = new SwampPyramidStructureProcessor();
    public static final Codec<SwampPyramidStructureProcessor> CODEC = Codec.unit(() -> INSTANCE);
    private final Map<Block, DataPool<Block>> replacementMap = Util.make(Maps.newHashMap(), (replacements) -> {
        replacements.put(BlockRegistry.COBBLESTONE_BRICKS, DataPool.<Block>builder().add(Blocks.COBBLESTONE, 1).add(Blocks.MOSSY_COBBLESTONE, 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICKS, 5).build());
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_SLAB, DataPool.<Block>builder().add(Blocks.COBBLESTONE_SLAB, 1).add(Blocks.MOSSY_COBBLESTONE_SLAB, 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_SLAB, 5).build());
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_STAIRS, DataPool.<Block>builder().add(Blocks.COBBLESTONE_STAIRS, 1).add(Blocks.MOSSY_COBBLESTONE_STAIRS, 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_STAIRS, 5).build());
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_WALL, DataPool.<Block>builder().add(Blocks.COBBLESTONE_WALL, 1).add(Blocks.MOSSY_COBBLESTONE_WALL, 3).add(BlockRegistry.MOSSY_COBBLESTONE_BRICK_WALL, 5).build());
    });

    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos);
        if (random.nextFloat() < .6f) {
            Optional<Block> optional = replacementMap.get(currentBlockInfo.state.getBlock()).getDataOrEmpty(random);
            if (optional.isEmpty()) return currentBlockInfo;
            else {
                BlockState blockState = currentBlockInfo.state;
                BlockState blockState2 = optional.get().getDefaultState();
                if (blockState.contains(StairsBlock.FACING)) {
                    blockState2 = blockState2.with(StairsBlock.FACING, blockState.get(StairsBlock.FACING));
                }

                if (blockState.contains(StairsBlock.HALF)) {
                    blockState2 = blockState2.with(StairsBlock.HALF, blockState.get(StairsBlock.HALF));
                }

                if (blockState.contains(SlabBlock.TYPE)) {
                    blockState2 = blockState2.with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE));
                }

                return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, blockState2, currentBlockInfo.nbt);
            }
        }
        return currentBlockInfo;
    }

    protected StructureProcessorType<?> getType() {
        return StructureRegistry.SWAMP_PYRAMID_PROCESSOR;
    }
}