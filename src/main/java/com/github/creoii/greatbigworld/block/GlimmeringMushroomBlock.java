package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
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
        super(FabricBlockSettings.of(Material.PLANT, MapColor.CLEAR).strength(.1f).sounds(BlockSoundGroup.WOOD).luminance(state -> light).ticksRandomly().nonOpaque().noCollision().emissiveLighting((state, world, pos) -> true));
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
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);
        FluidState fluidState = world.getFluidState(pos);
        System.out.println("placement");
        System.out.println(ctx.getStack().getName());
        if (state.isOf(this)) {
            System.out.println("this");
            return state.with(MUSHROOMS, Math.min(3, state.get(MUSHROOMS) + 1)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        } else {
            return getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState floor = world.getBlockState(blockPos);
        return !floor.getCollisionShape(world, blockPos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, blockPos, Direction.UP);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.SKY, pos) == 0) {
            world.setBlockState(pos, BlockRegistry.DARKBLIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        } else if (world.getLightLevel(LightType.SKY, pos) - world.getAmbientDarkness() < 7 || world.isRaining()) {
            world.setBlockState(pos, BlockRegistry.MIDNIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        } else {
            world.setBlockState(pos, BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
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
        if (!state.get(WATERLOGGED) && random.nextInt(5) <= (state.get(MUSHROOMS))) {
            spawnParticle(world, pos, random, 0d, 0d, 0d);
        }
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
