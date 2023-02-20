package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.SurfaceRuleManager;

public class GreatBigWorld implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Random RANDOM = Random.create();
    public static final String VERSION = "1.1.1";
    public static final Register[] REGISTERS = new Register[]{
            new BlockRegistry(),
            new ItemRegistry(),
            new EntityRegistry(),
            new PlacerRegistry(),
            new DecoratorRegistry(),
            new PlacementRegistry(),
            new FeatureRegistry(),
            new ConfiguredFeatureRegistry(),
            new PlacedFeatureRegistry(),
            new StructureRegistry(),
            new BiomeRegistry(),
            new EnchantmentRegistry(),
            new PotionRegistry(),
            new SoundRegistry(),
            new GameEventRegistry(),
            new ParticleRegistry(),
            new RenderRegistry(),
            new ModMenuIntegration()
    };

    @Override
    public void onInitialize() {
        for (Register register : REGISTERS) {
            register.register();
        }

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.ISLAND_BEACH), MaterialRules.sequence(MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.CEILING), MaterialRules.block(Blocks.WHITE_TERRACOTTA.getDefaultState())), MaterialRules.block(Blocks.SANDSTONE.getDefaultState())))
        ));
    }
}
