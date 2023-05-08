package com.github.creoii.greatbigworld.block;

import com.github.creoii.creolib.api.util.block.CBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.BambooLeaves;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BambooTorchBlock extends TorchBlock {
	protected static final VoxelShape TORCH_SMALL = Block.createCuboidShape(5d, 0d, 5d, 11d, 14d, 11d);
	protected static final VoxelShape TORCH_LARGE = Block.createCuboidShape(3d, 0d, 3d, 13d, 14d, 13d);
	protected static final VoxelShape TORCH_COLLISION = Block.createCuboidShape(6.5d, 0d, 6.5d, 9.5d, 14d, 9.5d);
	protected static final BooleanProperty LARGE = BooleanProperty.of("large");
	protected static final BooleanProperty LEAVES = BooleanProperty.of("leaves");

	public BambooTorchBlock(ParticleEffect particle) {
		super(CBlockSettings.copy(Blocks.TORCH).offset(OffsetType.XZ), particle);
		setDefaultState(getStateManager().getDefaultState().with(LARGE, false).with(LEAVES, false));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		return sideCoversSmallSquare(worldIn, pos.down(), Direction.UP) || downState.isIn(BlockTags.LEAVES) || downState.getBlock() instanceof BambooBlock;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		Vec3d offset = state.getModelOffset(worldIn, pos.down());
		return state.get(LARGE) ? TORCH_LARGE.offset(offset.x, offset.y, offset.z) : TORCH_SMALL.offset(offset.x, offset.y, offset.z);
	}

	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getCollisionShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(worldIn, pos);
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof BambooBlock) return TORCH_COLLISION.offset(vec3d.x, vec3d.y, vec3d.z);
		return VoxelShapes.empty();
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState downState = world.getBlockState(pos.down());
		if (downState.getBlock() instanceof BambooBlock) return getDefaultState().with(LEAVES, downState.get(BambooBlock.LEAVES) != BambooLeaves.NONE || downState.get(BambooBlock.AGE) == 0).with(LARGE, downState.get(BambooBlock.AGE) == 1);
		else if (downState.getBlock() instanceof BambooSaplingBlock && context.getSide() == Direction.UP) {
			world.setBlockState(pos.down(), Blocks.BAMBOO.getDefaultState(), 3);
			return getDefaultState();
		}
		return getDefaultState();
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		Vec3d offset = state.getModelOffset(world, pos);
		double d = pos.getX() + .5d + offset.x;
		double e = pos.getY() + .9d;
		double f = pos.getZ() + .5d + offset.z;
		world.addParticle(ParticleTypes.SMOKE, d, e, f, 0d, 0d, 0d);
		world.addParticle(particle, d, e, f, 0d, 0d, 0d);
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LARGE, LEAVES);
	}
}