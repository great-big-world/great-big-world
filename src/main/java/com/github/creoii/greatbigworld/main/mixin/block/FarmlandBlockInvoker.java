package com.github.creoii.greatbigworld.main.mixin.block;

import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FarmlandBlock.class)
public interface FarmlandBlockInvoker {
    @Invoker("hasCrop")
    static boolean hasCrop(BlockView world, BlockPos pos) {
        throw new AssertionError();
    }

    @Invoker("isWaterNearby")
    static boolean isWaterNearby(WorldView world, BlockPos pos) {
        throw new AssertionError();
    }
}
