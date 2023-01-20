package com.github.creoii.greatbigworld.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class LeafPileBlock extends Block implements Waterloggable {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0d, 0d, 0d, 16d, 2d, 16d);
    public static final BooleanProperty SNOWY = BooleanProperty.of("snowy");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public LeafPileBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SNOWY, false).with(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(SNOWY)) {
            if (world.getLightLevel(LightType.BLOCK, pos) > 11) {
                world.setBlockState(pos, state.with(SNOWY, false), 3);
            }
        } else if (world.isSkyVisibleAllowingSea(pos) && world.isRaining() && world.getBiome(pos).value().getPrecipitation() == Biome.Precipitation.SNOW) {
            world.setBlockState(pos, state.with(SNOWY, true), 3);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        if (world.getBlockState(pos).isOf(Blocks.SNOW)) {
            return getDefaultState().with(SNOWY, true);
        }
        return getDefaultState().with(WATERLOGGED, world.getFluidState(pos).isOf(Fluids.WATER));
    }

    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return !world.isAir(pos.down());
    }


    @SuppressWarnings("deprecation")
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, WATERLOGGED);
    }
}
