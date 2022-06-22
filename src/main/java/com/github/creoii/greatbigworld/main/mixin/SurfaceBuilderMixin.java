package com.github.creoii.greatbigworld.main.mixin;

import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SurfaceBuilder.class)
public class SurfaceBuilderMixin {
    @Redirect(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/RegistryEntry;matchesKey(Lnet/minecraft/util/registry/RegistryKey;)Z"))
    private boolean great_big_world_buildRedRockPeaksSurface(RegistryEntry<Biome> instance, RegistryKey<Biome> tRegistryKey) {
        return instance.matchesKey(BiomeKeys.ERODED_BADLANDS) || instance.matchesKey(BiomeRegistry.RED_ROCK_PEAKS);
    }
}
