package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.registry.GBWBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {
    @Shadow private static Optional<PointedDripstoneBlock.DrippingFluid> getFluid(World world, BlockPos pos, BlockState state) { return null; }
    @Shadow private static Optional<BlockPos> getSupportingPos(World world, BlockPos pos, BlockState state, int range) { return null; }

    @Inject(method = "dripTick", at = @At(value = "INVOKE", target = "Ljava/util/Optional;get()Ljava/lang/Object;", ordinal = 1, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void gbw_convertToElderPrismarine(BlockState state, ServerWorld world, BlockPos pos, float dripChance, CallbackInfo ci, Optional<PointedDripstoneBlock.DrippingFluid> optional, Fluid fluid, float f, BlockPos blockPos) {
        if (optional.get().sourceState().isOf(Blocks.PRISMARINE) && fluid == Fluids.WATER) {
            BlockState blockState = GBWBlocks.ELDER_PRISMARINE.getDefaultState();
            world.setBlockState(optional.get().pos(), blockState);
            Block.pushEntitiesUpBeforeBlockChange(optional.get().sourceState(), blockState, world, optional.get().pos());
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos(), GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(1504, blockPos, 0);
        }
    }

    @Redirect(method = "dripTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PointedDripstoneBlock;getFluid(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Ljava/util/Optional;"))
    private static Optional<PointedDripstoneBlock.DrippingFluid> gbw_allowElderPrismarineCheck(World world, BlockPos pos, BlockState state) {
        Optional<BlockPos> supportingPos = getSupportingPos(world, pos, state, 11);
        if (supportingPos.isPresent()) {
            BlockState upState = world.getBlockState(supportingPos.get().up());
            if (upState.isOf(Blocks.PRISMARINE)) {
                return supportingPos.map(pos1 -> new PointedDripstoneBlock.DrippingFluid(pos1.up(), Fluids.WATER, world.getBlockState(pos1.up())));
            }
        }
        return getFluid(world, pos, state);
    }
}
