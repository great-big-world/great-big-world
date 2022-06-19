package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.block.LayerConcretePowderBlock;
import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.state.property.Properties.LAYERS;

@Mixin(ConcretePowderBlock.class)
public class ConcretePowderBlockMixin extends FallingBlock {
    public ConcretePowderBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isAir(pos.down()) || LayerConcretePowderBlock.canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
            FallingConcretePowderEntity.spawnFromBlock(world, pos, state);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.getBlock() instanceof ConcretePowderBlock) {
            ItemStack held = player.getStackInHand(hand);
            if (held.getItem() instanceof ShovelItem && hit.getSide() == Direction.UP) {
                if (LayerConcretePowderBlock.POWDER_TO_LAYERED.containsKey(state.getBlock())) {
                    if (!player.isCreative() && !world.isClient) held.damage(1, player, e -> e.sendToolBreakStatus(hand));
                    world.setBlockState(pos, LayerConcretePowderBlock.POWDER_TO_LAYERED.get(state.getBlock()).getDefaultState().with(LAYERS, 7).with(LayerConcretePowderBlock.WATERLOGGED, world.getFluidState(pos).isIn(FluidTags.WATER)), 3);
                    return ActionResult.success(world.isClient);
                }
            }
        }

        return ActionResult.PASS;    }
}
