package com.github.creoii.greatbigworld.block;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CheeseCauldronBlock extends CauldronBlock {
    private final boolean moldy;

    public CheeseCauldronBlock(boolean moldy) {
        super(FabricBlockSettings.copy(Blocks.CAULDRON));
        this.moldy = moldy;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.getStackInHand(hand).isEmpty()|| (moldy && player.getStackInHand(hand).isOf(ItemRegistry.MOLDY_CHEESE)) || (!moldy && player.getStackInHand(hand).isOf(ItemRegistry.CHEESE))) {
                player.giveItemStack(new ItemStack(moldy ? ItemRegistry.MOLDY_CHEESE : ItemRegistry.CHEESE, 3));
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, SoundEvents.ENTITY_COW_MILK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < .14566669f) {
            world.setBlockState(pos, BlockRegistry.MOLDY_CHEESE_CAULDRON.getDefaultState(), 3);
        }
    }
}
