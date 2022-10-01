package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
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
    public static final IntProperty LIGHT = IntProperty.of("light", 2, 14);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public GlimmeringMushroomBlock() {
        super(FabricBlockSettings.of(Material.WOOD).strength(.1f).sounds(BlockSoundGroup.WOOD).luminance(state -> state.get(LIGHT)).ticksRandomly().nonOpaque().emissiveLighting((state, world, pos) -> true));
        setDefaultState(getDefaultState().with(MUSHROOMS, 1).with(LIGHT, 8).with(WATERLOGGED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(MUSHROOMS) == 1 ? SMALL_SHAPE : state.get(MUSHROOMS) == 2 ? MEDIUM_SHAPE : LARGE_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
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
        BlockState down = world.getBlockState(pos.down());
        return !down.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || down.isSideSolidFullSquare(world, pos, Direction.UP);
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
        }
        else if (world.getLightLevel(LightType.SKY, pos) - world.getAmbientDarkness() < 7 || world.isRaining()) {
            world.setBlockState(pos, BlockRegistry.MIDNIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        } else {
            world.setBlockState(pos, BlockRegistry.DAYLIGHT_MUSHROOM.getDefaultState().with(MUSHROOMS, state.get(MUSHROOMS)).with(WATERLOGGED, state.get(WATERLOGGED)), 3);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.with(LIGHT, player.isSneaking() ? MathHelper.clamp(state.get(LIGHT) - 2, 2, 14) : MathHelper.clamp(state.get(LIGHT) + 2, 2, 14)), 3);
        return ActionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(asItem()) && state.get(MUSHROOMS) < 3 || super.canReplace(state, context);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MUSHROOMS, LIGHT, WATERLOGGED);
    }
}
