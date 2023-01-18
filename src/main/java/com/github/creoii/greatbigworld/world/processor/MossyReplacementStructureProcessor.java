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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.Map;

public class MossyReplacementStructureProcessor extends StructureProcessor {
    public static final MossyReplacementStructureProcessor INSTANCE = new MossyReplacementStructureProcessor();
    public static final Codec<MossyReplacementStructureProcessor> CODEC = Codec.unit(() -> INSTANCE);
    private final Map<Block, Block> replacementMap = Util.make(Maps.newHashMap(), (replacements) -> {
        replacements.put(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE);
        replacements.put(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB);
        replacements.put(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS);
        replacements.put(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL);
        replacements.put(BlockRegistry.COBBLESTONE_BRICKS, BlockRegistry.MOSSY_COBBLESTONE_BRICKS);
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_SLAB, BlockRegistry.MOSSY_COBBLESTONE_BRICK_SLAB);
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_STAIRS, BlockRegistry.MOSSY_COBBLESTONE_BRICK_STAIRS);
        replacements.put(BlockRegistry.COBBLESTONE_BRICK_WALL, BlockRegistry.MOSSY_COBBLESTONE_BRICK_WALL);
        replacements.put(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        replacements.put(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB);
        replacements.put(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS);
        replacements.put(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL);
    });

    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        if (data.getRandom(currentBlockInfo.pos).nextFloat() < .6f) {
            Block block = replacementMap.get(currentBlockInfo.state.getBlock());
            if (block == null) {
                return currentBlockInfo;
            } else {
                BlockState blockState = currentBlockInfo.state;
                BlockState blockState2 = block.getDefaultState();
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
        return StructureRegistry.MOSSY_REPLACE;
    }
}