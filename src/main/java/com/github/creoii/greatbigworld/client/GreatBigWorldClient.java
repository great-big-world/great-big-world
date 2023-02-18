package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class GreatBigWorldClient implements ClientModInitializer {
    public static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    public static final ParticleFactoryRegistry PARTICLE_FACTORY_REGISTRY = ParticleFactoryRegistry.getInstance();
    public static final boolean gbwTitle = false;
    public static final Register[] CLIENT_REGISTERS = new Register[]{
            new BlockRegistry(),
            new ItemRegistry(),
            new EntityRegistry(),
            new ParticleRegistry(),
            new RenderRegistry()
    };

    @Override
    public void onInitializeClient() {
        for (Register register : GreatBigWorld.REGISTERS) {
            register.registerClient();
        }
    }
}
