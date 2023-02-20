package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.SurfaceRuleManager;

public class SurfaceRuleRegistry implements Register {
    private static final MaterialRules.MaterialRule LAVAROCK = MaterialRules.block(BlockRegistry.LAVAROCK.getDefaultState());
    private static final MaterialRules.MaterialRule GRASSY_LAVAROCK = MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.block(BlockRegistry.GRASSY_LAVAROCK.getDefaultState()));
    private static final MaterialRules.MaterialRule BEACH_SAND = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(Blocks.SANDSTONE.getDefaultState())), MaterialRules.block(Blocks.SMOOTH_SANDSTONE.getDefaultState()));

    @Override
    public void register() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.condition(MaterialRules.surface(), MaterialRules.sequence(
                createIslandBeachRule(),
                createIslandMangroveSwampRule(),
                createVolcanicBeachRule(),
                createVolcanicSlopesRule(),
                createVolcanicCraterRule()
        )));
    }

    public MaterialRules.MaterialRule createIslandBeachRule() {
        return MaterialRules.condition(MaterialRules.biome(BiomeRegistry.ISLAND_BEACH), BEACH_SAND);
    }

    public MaterialRules.MaterialRule createIslandMangroveSwampRule() {
        return MaterialRules.condition(MaterialRules.biome(BiomeRegistry.ISLAND_MANGROVE_SWAMP), MaterialRules.block(Blocks.MUD.getDefaultState()));
    }

    public MaterialRules.MaterialRule createVolcanicBeachRule() {
        return MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_BEACH), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, LAVAROCK), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.4545d), BEACH_SAND), LAVAROCK));
    }

    public MaterialRules.MaterialRule createVolcanicSlopesRule() {
        return MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_SLOPES), MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), LAVAROCK), GRASSY_LAVAROCK, LAVAROCK));
    }

    public MaterialRules.MaterialRule createVolcanicCraterRule() {
        return MaterialRules.condition(MaterialRules.biome(BiomeRegistry.VOLCANIC_CRATER), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, LAVAROCK), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.5454d), GRASSY_LAVAROCK), MaterialRules.condition(MaterialRules.steepSlope(), GRASSY_LAVAROCK)), LAVAROCK));
    }
}
