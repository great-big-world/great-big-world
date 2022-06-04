package com.github.creoii.greatbigworld.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HollowLogBlock extends PillarBlock {
    private static final VoxelShape SHAPE_X_1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    private static final VoxelShape SHAPE_X_2 = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_Y_1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    private static final VoxelShape SHAPE_Y_2 = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_Z_1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_Z_2 = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SHAPE_X = VoxelShapes.union(SHAPE_Y_1, SHAPE_Y_2, SHAPE_Z_1, SHAPE_Z_2);
    private static final VoxelShape SHAPE_Y = VoxelShapes.union(SHAPE_X_1, SHAPE_X_2, SHAPE_Z_1, SHAPE_Z_2);
    private static final VoxelShape SHAPE_Z = VoxelShapes.union(SHAPE_X_1, SHAPE_X_2, SHAPE_Y_1, SHAPE_Y_2);

    public HollowLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(AXIS) == Direction.Axis.X ? SHAPE_X : state.get(AXIS) == Direction.Axis.Y ? SHAPE_Y : SHAPE_Z;
    }
}
