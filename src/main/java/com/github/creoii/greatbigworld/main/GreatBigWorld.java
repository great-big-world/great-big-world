package com.github.creoii.greatbigworld.main;

import com.github.creoii.greatbigworld.main.integration.ModMenuIntegration;
import com.github.creoii.greatbigworld.main.registry.*;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
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
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.condition(MaterialRules.surface(), MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.ISLAND_BEACH), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(Blocks.SANDSTONE.getDefaultState())), MaterialRules.block(Blocks.SMOOTH_SANDSTONE.getDefaultState()))),
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.ISLAND_MANGROVE_SWAMP), MaterialRules.block(Blocks.MUD.getDefaultState())),
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_BEACH), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState())), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.4545d), MaterialRules.block(Blocks.SMOOTH_SANDSTONE.getDefaultState())), MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState()))),
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_SLOPES), MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState())), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.block(BlockRegistry.GRASSY_LAVAROCK.getDefaultState())), MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState()))),
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_CRATER), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState())), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.5454d), MaterialRules.block(BlockRegistry.GRASSY_LAVAROCK.getDefaultState())), MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState())))
        )));
    }
}
