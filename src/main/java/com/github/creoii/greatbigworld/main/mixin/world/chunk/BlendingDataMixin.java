package com.github.creoii.greatbigworld.main.mixin.world.chunk;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.BlendingData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BlendingData.class)
public class BlendingDataMixin {
    @Mutable
    @Shadow @Final private static List<Block> SURFACE_BLOCKS;

    static {
        SURFACE_BLOCKS = List.of(Blocks.PODZOL, Blocks.GRAVEL, Blocks.GRASS_BLOCK, Blocks.STONE, Blocks.COARSE_DIRT, Blocks.SAND, Blocks.RED_SAND, Blocks.MYCELIUM, Blocks.SNOW_BLOCK, Blocks.TERRACOTTA, Blocks.DIRT, GBWBlocks.LAVAROCK, GBWBlocks.GRASSY_LAVAROCK, GBWBlocks.RED_ROCK);
    }
}
