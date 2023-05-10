package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpreadableBlock.class)
public abstract class SpreadableBlockMixin extends SnowyBlock {
    public SpreadableBlockMixin(Settings settings) {
        super(settings);
    }

    private static boolean cannotSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return false;
        }
        if (blockState.getFluidState().getLevel() == 8) {
            return true;
        }
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i >= world.getMaxLightLevel();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (cannotSurvive(state, world, pos)) {
            if (state.isOf(BlockRegistry.GRASSY_LAVAROCK))
                world.setBlockState(pos, BlockRegistry.LAVAROCK.getDefaultState());
            else world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            return;
        }
        if (world.getLightLevel(pos.up()) >= 9) {
            BlockState defaultState = getDefaultState();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                boolean isDirt = world.getBlockState(blockPos).isOf(Blocks.DIRT);
                boolean isLavarock = world.getBlockState(blockPos).isOf(BlockRegistry.LAVAROCK);
                if (cannotSurvive(defaultState, world, blockPos)) continue;
                if (isDirt) {
                    world.setBlockState(blockPos, defaultState.with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                } else if (isLavarock && !defaultState.isOf(Blocks.MYCELIUM)) world.setBlockState(blockPos, BlockRegistry.GRASSY_LAVAROCK.getDefaultState().with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
            }
        }
    }
}
