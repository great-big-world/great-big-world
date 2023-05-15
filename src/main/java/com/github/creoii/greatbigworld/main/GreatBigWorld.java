package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.integration.GBWConfig;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.random.Random;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    public static final String VERSION = "1.2.2";
    public static GBWConfig CONFIG = new GBWConfig();
    public static final Register[] REGISTERS = new Register[]{
            new GBWBlocks(),
            new GBWItems(),
            new GBWEntityTypes(),
            new GBWLootTables(),
            new GBWDecorators(),
            new GBWConfiguredFeatures(),
            new GBWPlacedFeatures(),
            new GBWStructures(),
            new GBWBiomes(),
            new GBWSurfaceRules(),
            new GBWEnchantments(),
            new GBWPotions(),
            new GBWSoundEvents(),
            new GBWGameEvents(),
            new GBWParticles(),
            new GBWCriteria(),
            new GBWAdvancements()
    };

    @Override
    public void onInitialize() {
        AutoConfig.register(GBWConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(GBWConfig.class).getConfig();
        for (Register register : REGISTERS) {
            register.register();
        }
    }
}
