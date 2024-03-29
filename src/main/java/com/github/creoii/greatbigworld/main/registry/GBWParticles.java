package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.particle.GlimmerParticle;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GBWParticles implements Register {
    public static final DefaultParticleType DAY_GLIMMER = FabricParticleTypes.simple(true);
    public static final DefaultParticleType NIGHT_GLIMMER = FabricParticleTypes.simple(true);
    public static final DefaultParticleType DARK_GLIMMER = FabricParticleTypes.simple(true);

    @Override
    public void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "day_glimmer"), DAY_GLIMMER);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "night_glimmer"), NIGHT_GLIMMER);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "dark_glimmer"), DARK_GLIMMER);
    }

    @Override
    public void registerClient() {
        ParticleFactoryRegistry.getInstance().register(DAY_GLIMMER, GlimmerParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(NIGHT_GLIMMER, GlimmerParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DARK_GLIMMER, GlimmerParticle.Factory::new);
    }
}
