package com.github.creoii.greatbigworld.main;

import com.github.creoii.creolib.api.util.block.BlockUtil;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.Random;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    public static final String VERSION = "1.3.0";
    public static final boolean CONFIG_AVAILABLE = FabricLoader.getInstance().isModLoaded("modmenu") && FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
    public static final Register[] REGISTERS = new Register[]{
            new GBWBlocks(),
            new GBWBlockEntities(),
            new GBWItems(),
            new GBWEntityTypes(),
            new GBWDecorators(),
            new GBWConfiguredFeatures(),
            new GBWPlacedFeatures(),
            new GBWStructures(),
            new GBWBiomes(),
            new GBWScreens(),
            new GBWRecipes(),
            new GBWEnchantments(),
            new GBWPotions(),
            new GBWSoundEvents(),
            new GBWGameEvents(),
            new GBWParticles(),
            new GBWStats(),
            new GBWCriteria(),
            new GBWAdvancements(),
            new GBWLootTables()
    };

    @Override
    public void onInitialize() {
        if (CONFIG_AVAILABLE && ModMenuIntegration.CONFIG != null) {
            ModMenuIntegration.CONFIG.preload();
        }

        for (Register register : REGISTERS) {
            register.register();
        }

        BlockUtil.setHasRandomTicks(Blocks.COBBLESTONE);
    }
}
