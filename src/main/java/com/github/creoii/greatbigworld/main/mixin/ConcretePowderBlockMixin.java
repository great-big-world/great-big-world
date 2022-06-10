package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.block.LayerConcretePowderBlock;
import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

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
}
