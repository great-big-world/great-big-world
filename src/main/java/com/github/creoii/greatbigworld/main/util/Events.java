package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.block.LayerConcretePowderBlock;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static net.minecraft.state.property.Properties.LAYERS;

public class Events {
    public static void loadEvents() {
        UseBlockCallback.EVENT.register(Events::onRightClickBlock);
    }

    private static ActionResult onRightClickBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hit) {
        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof ConcretePowderBlock) {
            ItemStack held = player.getStackInHand(hand);
            if (held.getItem() instanceof ShovelItem && hit.getSide() == Direction.UP) {
                if (LayerConcretePowderBlock.POWDER_TO_LAYER.containsKey(state.getBlock())) {
                    if (!player.isCreative() && !world.isClient) held.damage(1, player, e -> e.sendToolBreakStatus(hand));
                    world.setBlockState(pos, LayerConcretePowderBlock.POWDER_TO_LAYER.get(state.getBlock()).getDefaultState().with(LAYERS, 7).with(LayerConcretePowderBlock.WATERLOGGED, world.getFluidState(pos).isIn(FluidTags.WATER)), 3);
                    return ActionResult.success(world.isClient);
                }
            }
        }

        return ActionResult.PASS;
    }
}
