package com.github.creoii.greatbigworld.block.base;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class HangingLeavesBlock extends Block implements Fertilizable {
    public static final EnumProperty<Half> HALF = EnumProperty.of("half", Half.class);
    protected static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);
    protected static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);

    public HangingLeavesBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HALF, Half.SMALL));
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        ItemUtil.appendStackInGroup(stacks, new ItemStack(this), BlockRegistry.CHARRED_LEAVES.asItem());
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState up = world.getBlockState(pos.up());
        return up.getBlock() == this || up.isIn(BlockTags.LEAVES) || up.isIn(BlockTags.LOGS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) world.breakBlock(pos, true);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == Half.SMALL ? SMALL_SHAPE : LARGE_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state.with(HALF, world.getBlockState(pos.down()).getBlock() == this ? Half.LARGE : Half.SMALL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canGrow(World world, net.minecraft.util.math.random.Random random, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.down()).isAir();
    }

    @Override
    public void grow(ServerWorld world, net.minecraft.util.math.random.Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos.down(), this.getDefaultState());
    }

    public enum Half implements StringIdentifiable {
        LARGE,
        SMALL;

        @Override
        public String asString() {
            return this == LARGE ? "large" : "small";
        }
    }
}