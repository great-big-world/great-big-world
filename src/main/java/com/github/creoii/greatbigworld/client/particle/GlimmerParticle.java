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
        super(world, x, y, z, .05f, .05f, .05f, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 1.5f, 60, .01f, true);
        float f = (world.random.nextInt(60) + 40f) / 100f;
        red += f;
        green += f;
        blue += f;
        scale *= .5f + f;
    }

    @Override
    public float getSize(float tickDelta) {
        return scale * (1f - (age + tickDelta) / ((float) maxAge / 2f)) * 1.4f;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new GlimmerParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, 1f, spriteProvider);
        }
    }
}
