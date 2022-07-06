package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.block.base.LeavesBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.BambooLeaves;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BambooTorchBlock extends TorchBlock {
	protected static final VoxelShape TORCH_SMALL = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);
	protected static final VoxelShape TORCH_LARGE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);
	protected static final VoxelShape TORCH_COLLISION = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 9.5D, 14.0D, 9.5D);
	protected static final BooleanProperty LARGE = BooleanProperty.of("large");
	protected static final BooleanProperty LEAVES = BooleanProperty.of("leaves");

	public BambooTorchBlock(ParticleEffect particle) {
		super(FabricBlockSettings.copy(Blocks.TORCH), particle);
		setDefaultState(getStateManager().getDefaultState().with(LARGE, false).with(LEAVES, false));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		return sideCoversSmallSquare(worldIn, pos.down(), Direction.UP) || downState.getBlock() instanceof LeavesBlock || downState.getBlock() instanceof BambooBlock;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(worldIn, pos);
		return state.get(LARGE) ? TORCH_LARGE.offset(vec3d.x, vec3d.y, vec3d.z) : TORCH_SMALL.offset(vec3d.x, vec3d.y, vec3d.z);
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
		if (downState.getBlock() instanceof BambooBlock) return this.getDefaultState().with(LEAVES, downState.get(BambooBlock.LEAVES) != BambooLeaves.NONE || downState.get(BambooBlock.AGE) == 0).with(LARGE, downState.get(BambooBlock.AGE) == 1);
		else if (downState.getBlock() instanceof BambooSaplingBlock && context.getSide() == Direction.UP) {
			world.setBlockState(pos.down(), Blocks.BAMBOO.getDefaultState(), 3);
			return this.getDefaultState();
		}
		return this.getDefaultState();
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LARGE, LEAVES);
	}
}