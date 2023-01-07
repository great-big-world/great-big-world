package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class GreatBigWorldClient implements ClientModInitializer {
    public static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    public static final boolean gbwTitle = false;

    @Override
    public void onInitializeClient() {
        for (Register register : GreatBigWorld.REGISTERS) {
            register.registerClient();
        }
    }
}
