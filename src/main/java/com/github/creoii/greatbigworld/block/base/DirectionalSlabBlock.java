package com.github.creoii.greatbigworld.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class DirectionalSlabBlock extends SlabBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public DirectionalSlabBlock(Settings properties) {
        super(properties);
        setDefaultState(getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        Direction direction = context.getPlayer().getHorizontalFacing();
        if (state.getBlock() == this) return state.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false).with(FACING, state.get(FACING));
        else {
            FluidState fluidState = context.getWorld().getFluidState(pos);
            boolean waterlogged = fluidState.getFluid() == Fluids.WATER;
            if (context.getHitPos().y - (double) pos.getY() <= 0.5D) return getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, waterlogged).with(FACING, direction);
            else return getDefaultState().with(TYPE, SlabType.TOP).with(WATERLOGGED, waterlogged).with(FACING, direction);
        }
    }
}