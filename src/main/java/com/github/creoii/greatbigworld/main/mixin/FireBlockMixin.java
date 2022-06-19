package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireBlock.class)
public class FireBlockMixin {
    @Redirect(method = "trySpreadingFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;removeBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"))
    private boolean great_big_world_injectCharredWood(World world, BlockPos pos, boolean move) {
        BlockState state = world.getBlockState(pos);
        if (state.isIn(BlockTags.LOGS_THAT_BURN)) {
            return world.setBlockState(pos, BlockRegistry.CHARRED_LOG.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
        }
        else return world.removeBlock(pos, move);
    }
}
