package com.github.creoii.greatbigworld.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Map;

public class BambooWallTorchBlock extends WallTorchBlock {
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5d, 3d, 11d, 10.5d, 16d, 16d), Direction.SOUTH, Block.createCuboidShape(5.5d, 3d, 0d, 10.5d, 16d, 5d), Direction.WEST, Block.createCuboidShape(11d, 3d, 5.5d, 16d, 16d, 10.5d), Direction.EAST, Block.createCuboidShape(0d, 3d, 5.5d, 16d, 16d, 10.5d)));

	public BambooWallTorchBlock(ParticleEffect particle) {
		super(FabricBlockSettings.copy(Blocks.WALL_TORCH), particle);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPES.get(state.get(Properties.HORIZONTAL_FACING));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(Properties.HORIZONTAL_FACING);
		BlockPos oppositePos = pos.offset(direction.getOpposite());
		BlockState oppositeState = world.getBlockState(oppositePos);
		return oppositeState.isSideSolidFullSquare(world, oppositePos, direction) || oppositeState.isIn(BlockTags.LEAVES);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		double d = pos.getX() + .5d;
		double e = pos.getY() + .85d;
		double f = pos.getZ() + .5d;
		Direction direction = state.get(FACING).getOpposite();
		world.addParticle(ParticleTypes.SMOKE, d + .17d * direction.getOffsetX(), e + .22d, f + .17d * direction.getOffsetZ(), 0d, 0d, 0d);
		world.addParticle(particle, d + .17d * direction.getOffsetX(), e + .22d, f + .17d * direction.getOffsetZ(), 0d, 0d, 0d);
	}
}