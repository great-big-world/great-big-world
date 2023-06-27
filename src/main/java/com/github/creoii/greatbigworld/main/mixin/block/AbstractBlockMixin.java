package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void gbw_bleachCobblestone(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (GreatBigWorld.HEAT_CONVERSIONS.containsKey(state.getBlock())) {
            if (random.nextInt(5) != 0 || !world.isDay() || !canHeatConvert(world, pos)) return;

            RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
            if (!biomeEntry.hasKeyAndValue()) return;
            if (biomeEntry.value().getTemperature() < .55f) return;

            world.setBlockState(pos, GreatBigWorld.HEAT_CONVERSIONS.get(state.getBlock()).getDefaultState());

        }
    }

    private boolean canHeatConvert(ServerWorld world, BlockPos pos) {
        int glass = 0;
        BlockPos.Mutable mutable = pos.mutableCopy();
        // add one so we don't iterate over the heatable block
        for (int i = pos.getY() + 1; i < world.getHeight(); ++i) {
            mutable.setY(i);
            BlockState state = world.getBlockState(mutable);
            if (state.isIn(Tags.BlockTags.SOLAR_HEAT_TRANSFERRABLES)) {
                ++glass;
                continue;
            }
            if (!state.isAir()) return false;
        }
        return glass > 0;
    }
}
