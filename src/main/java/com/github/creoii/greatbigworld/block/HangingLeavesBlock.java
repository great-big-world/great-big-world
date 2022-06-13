package com.github.creoii.greatbigworld.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class HangingLeavesBlock extends Block implements Fertilizable {
    public static final EnumProperty<Half> HALF = EnumProperty.of("half", Half.class);
    protected static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);
    protected static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);

    public HangingLeavesBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HALF, Half.SMALL));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState up = world.getBlockState(pos.up());
        return up.getBlock() == this || up.isIn(BlockTags.LEAVES) || up.isIn(BlockTags.LOGS);
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
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        world.setBlockState(pos, state.with(HALF, world.getBlockState(pos.down()).getBlock() == this ? Half.LARGE : Half.SMALL));
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