package com.github.creoii.greatbigworld.world.processor;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import com.github.creoii.greatbigworld.main.registry.GBWStructures;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
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

public class MoaiStructureProcessor extends StructureProcessor {
    public static final Codec<MoaiStructureProcessor> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.STRING.fieldOf("type").forGetter(processor -> {
            return processor.type;
        })).apply(instance, MoaiStructureProcessor::new);
    });
    private final Map<Block, BlockStateProvider> calciteReplacementMap = Util.make(Maps.newHashMap(), replacements -> {
        replacements.put(Blocks.CALCITE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DIORITE.getDefaultState(), 3).add(Blocks.POLISHED_DIORITE.getDefaultState(), 5).add(Blocks.STONE.getDefaultState(), 1).build()));
        replacements.put(Blocks.DIORITE_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.DIORITE_STAIRS.getDefaultState(), 3).add(Blocks.POLISHED_DIORITE_STAIRS.getDefaultState(), 5).add(Blocks.STONE_STAIRS.getDefaultState(), 1).build()));
    });
    private final Map<Block, BlockStateProvider> stoneReplacementMap = Util.make(Maps.newHashMap(), replacements -> {
        replacements.put(Blocks.STONE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE.getDefaultState(), 1).add(Blocks.POLISHED_ANDESITE.getDefaultState(), 1).build()));
        replacements.put(Blocks.STONE_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_STAIRS.getDefaultState(), 1).add(Blocks.POLISHED_ANDESITE_STAIRS.getDefaultState(), 1).build()));
    });
    private final Map<Block, BlockStateProvider> mossyReplacementMap = Util.make(Maps.newHashMap(), replacements -> {
        replacements.put(Blocks.MOSSY_COBBLESTONE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE.getDefaultState(), 6).add(GBWBlocks.MOSSY_COBBLESTONE_BRICKS.getDefaultState(), 6).add(GBWBlocks.COBBLESTONE_BRICKS.getDefaultState(), 3).add(GBWBlocks.CHISELED_COBBLESTONE_BRICKS.getDefaultState(), 1).build()));
        replacements.put(Blocks.MOSSY_COBBLESTONE_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.COBBLESTONE_STAIRS.getDefaultState(), 2).add(GBWBlocks.MOSSY_COBBLESTONE_BRICK_STAIRS.getDefaultState(), 2).add(GBWBlocks.COBBLESTONE_BRICK_STAIRS.getDefaultState(), 1).build()));
    });
    private final Map<Block, BlockStateProvider> volcanicReplacementMap = Util.make(Maps.newHashMap(), replacements -> {
        replacements.put(Blocks.COBBLED_DEEPSLATE, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.POLISHED_DEEPSLATE.getDefaultState(), 1).add(Blocks.SMOOTH_BASALT.getDefaultState(), 3).build()));
        replacements.put(Blocks.COBBLED_DEEPSLATE_STAIRS, new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.POLISHED_DEEPSLATE_STAIRS.getDefaultState(), 1).build()));
    });
    private final String type;

    public MoaiStructureProcessor(String type) {
        this.type = type;
    }

    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos);
        Block block = currentBlockInfo.state.getBlock();
        Map<Block, BlockStateProvider> replacementMap = switch (type) {
            case "stone" -> stoneReplacementMap;
            case "mossy" -> mossyReplacementMap;
            case "volcanic" -> volcanicReplacementMap;
            default -> calciteReplacementMap;
        };
        float chance = switch (type) {
            case "stone" -> .4f;
            case "volcanic" -> .7f;
            default -> .5f;
        };
        if (random.nextFloat() < chance && replacementMap.containsKey(block)) {
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

            if (blockState.contains(Properties.WATERLOGGED)) {
                state = state.with(Properties.WATERLOGGED, blockState.get(Properties.WATERLOGGED));
            }

            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, state, currentBlockInfo.nbt);
        }
        return currentBlockInfo;
    }

    protected StructureProcessorType<?> getType() {
        return GBWStructures.MOAI_PROCESSOR;
    }
}