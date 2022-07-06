package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.block.base.LeavesBlock;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Map;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class BambooWallTorchBlock extends WallTorchBlock {
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 16.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 16.0D, 5.0D), Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 16.0D, 10.5D), Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 16.0D, 10.5D)));

	public BambooWallTorchBlock(ParticleEffect particle) {
		super(FabricBlockSettings.copy(Blocks.WALL_TORCH), particle);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPES.get(state.get(HORIZONTAL_FACING));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(HORIZONTAL_FACING);
		BlockPos oppositePos = pos.offset(direction.getOpposite());
		BlockState oppositeState = world.getBlockState(oppositePos);
		return oppositeState.isSideSolidFullSquare(world, oppositePos, direction) || oppositeState.getBlock() instanceof LeavesBlock;
	}
}