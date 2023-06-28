package com.github.creoii.greatbigworld.main.mixin.block;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
    private void gbw_blanchCobblestone(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (GreatBigWorld.HEAT_CONVERSIONS.containsKey(state.getBlock())) {
            if (!world.isDay() || !canHeatConvert(world, pos)) return;

            for (int i = 0; i < random.nextInt(2) + 1; ++i) {
                world.spawnParticles(ParticleTypes.SMOKE, (double) pos.getX() + world.random.nextDouble(), pos.getY() + .75d, (double) pos.getZ() + world.random.nextDouble(), 1, 0d, 0d, 0d, 0d);
            }
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, .6f, 1f, false);
            if (random.nextInt(8) != 0) return;

            RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
            if (!biomeEntry.hasKeyAndValue()) return;
            if (biomeEntry.value().getTemperature() < .55f) return;

            world.setBlockState(pos, GreatBigWorld.HEAT_CONVERSIONS.get(state.getBlock()).getDefaultState());
        }
    }

    private boolean canHeatConvert(ServerWorld world, BlockPos pos) {
        boolean glass = false;
        BlockPos.Mutable mutable = pos.mutableCopy();
        // add one so we don't iterate over the heatable block
        for (int i = pos.getY() + 1; i < world.getHeight(); ++i) {
            mutable.setY(i);
            BlockState state = world.getBlockState(mutable);
            if (!glass && state.isIn(Tags.BlockTags.SOLAR_HEAT_TRANSFERRABLES)) {
                glass = true;
                continue;
            }

            if (!state.isAir()) return false;
        }
        return glass;
    }
}
