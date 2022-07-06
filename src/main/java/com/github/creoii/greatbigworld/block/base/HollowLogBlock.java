package com.github.creoii.greatbigworld.block.base;

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
    private static final VoxelShape SHAPE_X1 = Block.createCuboidShape(.25d, .75d, 0d, .75d, 1d, 1d);
    private static final VoxelShape SHAPE_X2 = Block.createCuboidShape(.25d, 0d, 0d, .75d, .25d, 1d);

    private static final VoxelShape SHAPE_Y1 = Block.createCuboidShape(0d, 0d, 0d, .25d, 1d, 1d);
    private static final VoxelShape SHAPE_Y2 = Block.createCuboidShape(.75d, 0d, 0d, 1d, 1d, 1d);
    private static final VoxelShape SHAPE_Y3 = Block.createCuboidShape(.25d, 0d, 0d, .75d, 1d, .25d);
    private static final VoxelShape SHAPE_Y4 = Block.createCuboidShape(.25d, 0d, .75d, .75d, 1d, 1d);

    private static final VoxelShape SHAPE_Z1 = Block.createCuboidShape(0d, 0d, 0d, 1d, 1d, 0.25d);
    private static final VoxelShape SHAPE_Z2 = Block.createCuboidShape(0d, 0d, 0.75d, 1d, 1d, 1d);
    private static final VoxelShape SHAPE_Z3 = Block.createCuboidShape(0d, 0.75d, 0.25d, 1d, 1d, 0.75d);
    private static final VoxelShape SHAPE_Z4 = Block.createCuboidShape(0d, 0d, 0.25d, 1d, 0.25d, 0.75d);

    private static final VoxelShape SHAPE_X = VoxelShapes.union(SHAPE_Y1, SHAPE_Y2, SHAPE_X1, SHAPE_X2);
    private static final VoxelShape SHAPE_Y = VoxelShapes.union(SHAPE_Y1, SHAPE_Y2, SHAPE_Y3, SHAPE_Y4);
    private static final VoxelShape SHAPE_Z = VoxelShapes.union(SHAPE_Z1, SHAPE_Z2, SHAPE_Z3, SHAPE_Z4);

    public HollowLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(AXIS) == Direction.Axis.X ? SHAPE_X : state.get(AXIS) == Direction.Axis.Y ? SHAPE_Y : SHAPE_Z;
    }
}
