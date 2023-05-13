package com.github.creoii.greatbigworld.block;

import com.github.creoii.creolib.api.util.block.CBlockSettings;
import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class GlimmeringMushroomBlock extends Block implements Waterloggable {
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(4d, 0d, 4d, 12d, 8d, 12d);
    private static final VoxelShape MEDIUM_SHAPE = Block.createCuboidShape(2d, 0d, 2d, 14d, 10d, 14d);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1d, 0d, 1d, 15d, 12d, 15d);
    public static final IntProperty MUSHROOMS = IntProperty.of("mushrooms", 1, 3);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final ParticleEffect particleEffect;
    private final StatusEffectInstance statusEffect;
    private final int cloudColor;

    public GlimmeringMushroomBlock(ParticleEffect particleEffect, StatusEffectInstance statusEffect, int light, int cloudColor) {
        super(CBlockSettings.of(Material.PLANT, MapColor.CLEAR).strength(.1f).sounds(BlockSoundGroup.WOOD).luminance(state -> light).ticksRandomly().nonOpaque().noCollision().emissiveLighting((state, world, pos) -> true));
        setDefaultState(getStateManager().getDefaultState().with(MUSHROOMS, 1).with(WATERLOGGED, false));
        this.particleEffect = particleEffect;
        this.statusEffect = statusEffect;
        this.cloudColor = cloudColor;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(MUSHROOMS) == 1 ? SMALL_SHAPE : state.get(MUSHROOMS) == 2 ? MEDIUM_SHAPE : LARGE_SHAPE;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(MUSHROOMS, Math.min(3, blockState.get(MUSHROOMS) + 1));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            return super.getPlacementState(ctx).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState floor = world.getBlockState(blockPos);
        return !floor.getCollisionShape(world, blockPos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, blockPos, Direction.UP);
    }

    @SuppressWarnings("deprecation")
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(asItem()) && state.get(MUSHROOMS) < 3 || super.canReplace(state, context);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.SKY, pos) == 0) {
            world.setBlockState(pos, GBWBlocks.DARKBLIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        } else if (world.getLightLevel(LightType.SKY, pos) - world.getAmbientDarkness() < 7 || world.isRaining()) {
            world.setBlockState(pos, GBWBlocks.MIDNIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        } else {
            world.setBlockState(pos, GBWBlocks.DAYLIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.isCreative()) {
            AreaEffectCloudEntity areaEffectCloud = new AreaEffectCloudEntity(world, pos.getX() + .5f, pos.getY(), pos.getZ() + .5f);
            areaEffectCloud.setRadius(.75f);
            areaEffectCloud.setRadiusOnUse(-.5f);
            areaEffectCloud.setWaitTime(0);
            areaEffectCloud.setDuration(100);
            areaEffectCloud.setRadiusGrowth(-areaEffectCloud.getRadius() / (float) areaEffectCloud.getDuration());
            areaEffectCloud.addEffect(statusEffect);
            areaEffectCloud.setColor(cloudColor);
            world.spawnEntity(areaEffectCloud);
            for (int i = 0; i < world.random.nextInt(10); ++i) {
                spawnParticle(world, pos, world.getRandom(), world.random.nextFloat(), world.random.nextFloat(), world.random.nextFloat());
            }
        }
        super.onBreak(world, pos, state, player);
    }

    private void spawnParticle(World world, BlockPos pos, Random random, double velocityX, double velocityY, double velocityZ) {
        double x = pos.getX() + .5d + .25d * (random.nextInt(2) * 2 - 1);
        double y = pos.getY() + random.nextFloat();
        double z = pos.getZ() + .5d + .25d * (random.nextInt(2) * 2 - 1);
        world.addParticle(particleEffect, x, y, z, velocityX, velocityY, velocityZ);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(WATERLOGGED) && random.nextInt(6) <= (state.get(MUSHROOMS))) {
            spawnParticle(world, pos, random, 0d, 0d, 0d);
        }
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MUSHROOMS, WATERLOGGED);
    }
}
