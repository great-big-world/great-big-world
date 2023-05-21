package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class AcaiBerryClumpBlock extends Block implements Fertilizable {
    private static final VoxelShape BIG_NORTH_SHAPE = Block.createCuboidShape(4d, 6d, 8d, 12d, 16d, 16d);
    private static final VoxelShape BIG_SOUTH_SHAPE = Block.createCuboidShape(4d, 6d, 0d, 12d, 16d, 8d);
    private static final VoxelShape BIG_EAST_SHAPE = Block.createCuboidShape(0d, 6d, 4d, 8d, 16d, 12d);
    private static final VoxelShape BIG_WEST_SHAPE = Block.createCuboidShape(8d, 6d, 4d, 16d, 16d, 12d);
    private static final VoxelShape SMALL_NORTH_SHAPE = Block.createCuboidShape(4d, 9d, 8d, 12d, 16d, 16d);
    private static final VoxelShape SMALL_SOUTH_SHAPE = Block.createCuboidShape(4d, 9d, 0d, 12d, 16d, 8d);
    private static final VoxelShape SMALL_EAST_SHAPE = Block.createCuboidShape(0d, 9d, 4d, 8d, 16d, 12d);
    private static final VoxelShape SMALL_WEST_SHAPE = Block.createCuboidShape(8d, 9d, 4d, 16d, 16d, 12d);
    public static final IntProperty BERRIES = IntProperty.of("berries", 1, 5);
    public static final DirectionProperty HORIZONTAL_FACING = Properties.HORIZONTAL_FACING;

    public AcaiBerryClumpBlock(Settings settings) {
        super(settings.ticksRandomly().noCollision());
        setDefaultState(getStateManager().getDefaultState().with(BERRIES, 1).with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int i = state.get(BERRIES);
        return switch (state.get(HORIZONTAL_FACING)) {
            case SOUTH -> i < 3 ? SMALL_SOUTH_SHAPE : BIG_SOUTH_SHAPE;
            case EAST -> i < 3 ? SMALL_EAST_SHAPE : BIG_EAST_SHAPE;
            case WEST -> i < 3 ? SMALL_WEST_SHAPE : BIG_WEST_SHAPE;
            default -> i < 3 ? SMALL_NORTH_SHAPE : BIG_NORTH_SHAPE;
        };
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (state.isOf(this)) {
            return state.with(BERRIES, Math.min(5, state.get(BERRIES) + 1));
        }
        state = getDefaultState();
        Direction[] directions = ctx.getPlacementDirections();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        for (Direction direction : directions) {
            if (!direction.getAxis().isHorizontal() || !(state = state.with(HORIZONTAL_FACING, direction.getOpposite())).canPlaceAt(worldView, blockPos)) continue;
            return state;
        }
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(HORIZONTAL_FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction) && blockState.isIn(Tags.BlockTags.ACAI_BERRY_PLACEABLE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(HORIZONTAL_FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return state;
    }

    @SuppressWarnings("deprecation")
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(asItem()) && state.get(BERRIES) < 5 || super.canReplace(state, context);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.get(BERRIES);
        if (i < 5 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            BlockState blockState = state.with(BERRIES, i + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(BERRIES) < 5;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(BERRIES, Math.min(5, state.get(BERRIES) + 1)), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BERRIES, HORIZONTAL_FACING);
    }
}
