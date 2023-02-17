package com.github.creoii.greatbigworld.main.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
@Environment(EnvType.CLIENT)
public class WorldRendererMixin {
    @Shadow private @Nullable ClientWorld world;

    @Inject(method = "processWorldEvent", at = @At("HEAD"), cancellable = true)
    private void creo_lib_processWorldEvents(int eventId, BlockPos pos, int data, CallbackInfo ci) {
        Random random = world.random;
        if (eventId == 5000) {
            double d = pos.getX() - .5d;
            double e = pos.getY();
            double f = pos.getZ() - .5d;
            for (int i = 0; i < 20; ++i) {
                d += random.nextBetween(-1, 1);
                e += random.nextBetween(-1, 1);
                f += random.nextBetween(-1, 1);
                world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d, e, f, (random.nextDouble() - .5d) * .1d, .08d, (random.nextDouble() - .5d) * .1d);
                world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d + random.nextDouble(), e + random.nextDouble(), f + random.nextDouble(), (random.nextDouble() - .5d) * .1d, .1d, (random.nextDouble() - .5d) * .1d);
            }
            ci.cancel();
        }
    }
}
