package com.github.creoii.greatbigworld.world.structure;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.StructurePieceRegistry;
import com.github.creoii.greatbigworld.main.registry.StructureRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class ShackStructure extends Structure {
    private static final Identifier[] SURFACE_SHACKS = new Identifier[]{new Identifier(GreatBigWorld.NAMESPACE, "shacks/big_mountain"), new Identifier(GreatBigWorld.NAMESPACE, "shacks/small_mountain")};
    private static final Identifier[] UNDERGROUND_SHACKS = new Identifier[]{new Identifier(GreatBigWorld.NAMESPACE, "shacks/big_underground"), new Identifier(GreatBigWorld.NAMESPACE, "shacks/small_underground")};
    public static final Codec<ShackStructure> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(configCodecBuilder(instance), Codec.BOOL.fieldOf("surface").forGetter((shackStructure) -> {
            return shackStructure.surface;
        })).apply(instance, ShackStructure::new);
    });
    private final boolean surface;

    public ShackStructure(Config config, boolean surface) {
        super(config);
        this.surface = surface;
    }

    private void addPieces(Context context, StructurePiecesHolder holder) {
        BlockPos center = context.chunkPos().getCenterAtY(0);
        if (surface) holder.addPiece(new Piece(context.structureTemplateManager(), SURFACE_SHACKS[context.random().nextInt(SURFACE_SHACKS.length)], new BlockPos(center.getX(), context.chunkGenerator().getHeightInGround(center.getX(), center.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig()), center.getZ())));
        else holder.addPiece(new Piece(context.structureTemplateManager(), UNDERGROUND_SHACKS[context.random().nextInt(UNDERGROUND_SHACKS.length)], new BlockPos(center.getX(), context.chunkGenerator().getHeightInGround(center.getX(), center.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig()), center.getZ())));
    }

    @Override
    public Optional<StructurePosition> getStructurePosition(Context context) {
        return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG, (collector) -> {
            addPieces(context, collector);
        });
    }

    @Override
    public StructureType<?> getType() {
        return StructureRegistry.SHACK;
    }

    public static class Piece extends SimpleStructurePiece {
        public Piece(StructureTemplateManager manager, Identifier identifier, BlockPos pos) {
            super(StructurePieceRegistry.SHACK, 0, manager, identifier, identifier.toString(), createPlacementData(identifier), pos);
        }

        public Piece(StructureContext context, NbtCompound nbt) {
            super(StructurePieceRegistry.SHACK, nbt, context.structureTemplateManager(), Piece::createPlacementData);
        }

        private static StructurePlacementData createPlacementData(Identifier identifier) {
            return new StructurePlacementData().setMirror(BlockMirror.NONE).setPosition(BlockPos.ORIGIN).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, net.minecraft.util.math.random.Random random, BlockBox boundingBox) {
            if (metadata.equals("mountain_chest")) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                BlockEntity blockEntity = world.getBlockEntity(pos.down());
                if (blockEntity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockEntity).setLootTable(LootTables.PILLAGER_OUTPOST_CHEST, random.nextLong());
                }
            } else if (metadata.equals("underground_chest")) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                BlockEntity blockEntity = world.getBlockEntity(pos.down());
                if (blockEntity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockEntity).setLootTable(LootTables.ABANDONED_MINESHAFT_CHEST, random.nextLong());
                }
            }
        }
    }
}