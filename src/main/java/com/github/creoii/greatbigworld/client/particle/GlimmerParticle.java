package com.github.creoii.greatbigworld.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class GlimmerParticle extends AscendingParticle {
    public GlimmerParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, .1f, .1f, .1f, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, .8f, 60, .006f, true);
    }

    @Override
    public float getSize(float tickDelta) {
        return scale * (1f - ((float)age + tickDelta) / ((float)maxAge / 2)) * 1.4f;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new GlimmerParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, 1f, spriteProvider);
        }
    }
}
