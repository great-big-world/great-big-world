package com.github.creoii.greatbigworld.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Map;

public class AntlerBlock extends HorizontalFacingBlock {
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3d, 0d, 0d, 13d, 16d, 12d);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(3d, 0d, 4d, 13d, 16d, 16d);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0d, 0d, 3d, 12d, 16d, 13d);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(4d, 0d, 3d, 16d, 16d, 13d);
    private static final Map<Direction, VoxelShape> DIRECTION_TO_SHAPE = new ImmutableMap.Builder<Direction, VoxelShape>()
            .put(Direction.NORTH, NORTH_SHAPE)
            .put(Direction.SOUTH, SOUTH_SHAPE)
            .put(Direction.EAST, EAST_SHAPE)
            .put(Direction.WEST, WEST_SHAPE)
            .build();

    public AntlerBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return DIRECTION_TO_SHAPE.get(state.get(Properties.HORIZONTAL_FACING));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        return sideCoversSmallSquare(worldIn, pos.down(), Direction.UP);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }
}