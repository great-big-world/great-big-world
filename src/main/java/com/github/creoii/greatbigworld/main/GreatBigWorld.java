package com.github.creoii.greatbigworld.main;

import com.github.creoii.creolib.api.util.block.BlockUtil;
import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.Random;

import java.util.Map;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    public static final String VERSION = "1.3.0";
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

    public static final Map<Block, Block> HEAT_CONVERSIONS = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.COBBLESTONE, GBWBlocks.RED_ROCK)
            .put(Blocks.BRICKS, GBWBlocks.BLANCHED_BRICKS)
            .put(Blocks.COBBLESTONE_STAIRS, GBWBlocks.RED_ROCK_STAIRS)
            .put(Blocks.COBBLESTONE_SLAB, GBWBlocks.RED_ROCK_SLAB)
            .put(Blocks.COBBLESTONE_WALL, GBWBlocks.RED_ROCK_WALL)
            .put(Blocks.BRICK_STAIRS, GBWBlocks.BLANCHED_BRICK_STAIRS)
            .put(Blocks.BRICK_SLAB, GBWBlocks.BLANCHED_BRICK_SLAB)
            .put(Blocks.BRICK_WALL, GBWBlocks.BLANCHED_BRICK_WALL)
            .build();

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
