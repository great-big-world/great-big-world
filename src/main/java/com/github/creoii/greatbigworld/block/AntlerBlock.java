package com.github.creoii.greatbigworld.block;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AntlerBlock extends HorizontalFacingBlock {
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(3d, 0d, 0d, 13d, 16d, 12d);
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3d, 0d, 4d, 13d, 16d, 16d);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0d, 0d, 3d, 12d, 16d, 13d);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(4d, 0d, 3d, 16d, 16d, 13d);
    private static final Map<Direction, VoxelShape> DIRECTION_TO_SHAPE = new ImmutableMap.Builder<Direction, VoxelShape>()
            .put(Direction.NORTH, NORTH_SHAPE)
            .put(Direction.SOUTH, SOUTH_SHAPE)
            .put(Direction.EAST, EAST_SHAPE)
            .put(Direction.WEST, WEST_SHAPE)
            .build();

    public AntlerBlock() {
        super(FabricBlockSettings.copy(Blocks.BONE_BLOCK).nonOpaque().noCollision());
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return DIRECTION_TO_SHAPE.get(state.get(FACING));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = getDefaultState();
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction.getOpposite());
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return blockState;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos offset = pos.offset(direction.getOpposite());
        return world.getBlockState(offset).isSideSolidFullSquare(world, offset, direction);
    }

    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
