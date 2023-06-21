package com.github.creoii.greatbigworld.block.thatch;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThatchStairsBlock extends StairsBlock {
    private final Block trimmedBlock;

    public ThatchStairsBlock(Settings settings, BlockState baseState, Block trimmedBlock) {
        super(baseState, settings);
        this.trimmedBlock = trimmedBlock;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);
        if (held.getItem() instanceof ShearsItem) {
            if (!world.isClient) {
                held.damage(1, player, p -> {
                    p.sendToolBreakStatus(hand);
                });
                world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1f, 1f);
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayerEntity, pos, held);
                }
                world.setBlockState(pos, trimmedBlock.getStateWithProperties(state), 3);
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}