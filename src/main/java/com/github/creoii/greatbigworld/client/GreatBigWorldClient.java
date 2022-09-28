package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GreatBigWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Register register : GreatBigWorld.REGISTERS) {
            register.registerClient();
        }
    }
}
