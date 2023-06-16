package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.random.Random;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    public static final String VERSION = "1.2.6";
    public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
    public static final Register[] REGISTERS = new Register[]{
            new GBWBlocks(),
            new GBWItems(),
            new GBWEntityTypes(),
            new GBWDecorators(),
            new GBWConfiguredFeatures(),
            new GBWPlacedFeatures(),
            new GBWStructures(),
            new GBWBiomes(),
            new GBWEnchantments(),
            new GBWPotions(),
            new GBWSoundEvents(),
            new GBWGameEvents(),
            new GBWParticles(),
            new GBWCriteria(),
            new GBWAdvancements(),
            new GBWLootTables()
    };

    @Override
    public void onInitialize() {
        for (Register register : REGISTERS) {
            register.register();
        }
    }
}
