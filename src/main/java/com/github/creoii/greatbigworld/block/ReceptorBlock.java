package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.block.blockentity.ReceptorBlockEntity;
import com.github.creoii.greatbigworld.main.registry.BlockEntityRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ReceptorBlock extends BlockWithEntity {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final IntProperty POWER = Properties.POWER;

    public ReceptorBlock() {
        super(FabricBlockSettings.copy(Blocks.REPEATER));
        setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH).with(POWER, 0));
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return hasTopRim(world, pos.down());
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction face = ctx.getPlayerFacing();
        int light = ctx.getWorld().getLightLevel(LightType.BLOCK, ctx.getBlockPos().offset(face));
        return getDefaultState().with(FACING, face).with(POWER, light);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == state.get(FACING)) {
            return state.with(POWER, world.getLightLevel(LightType.BLOCK, neighborPos));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(POWER) > 0 && random.nextFloat() < .25f) {
            Direction direction = state.get(FACING).getOpposite();
            double x = (double)pos.getX() + .5d + .1d * (double)direction.getOffsetX();
            double y = (double)pos.getY() + .25d + .1d * (double)direction.getOffsetY();
            double z = (double)pos.getZ() + .5d + .1d * (double)direction.getOffsetZ();
            world.addParticle(new DustParticleEffect(DustParticleEffect.RED, .5f), x, y, z, random.nextDouble(), random.nextDouble(), random.nextDouble());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean hasComparatorOutput(BlockState state) {
        return state.get(POWER) > 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(POWER);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWER);
    }

    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ReceptorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return !world.isClient ? checkType(type, BlockEntityRegistry.RECEPTOR, ReceptorBlock::tick) : null;
    }

    private static void tick(World world, BlockPos pos, BlockState state, ReceptorBlockEntity blockEntity) {
        int light = world.getLightLevel(LightType.BLOCK, pos.offset(state.get(FACING)));

        if (light > 0 && world.isRaining() && world.isSkyVisible(pos)) --light;

        if (light != state.get(POWER)) {
            world.setBlockState(pos, state.with(POWER, light), 3);
        }
    }
}
