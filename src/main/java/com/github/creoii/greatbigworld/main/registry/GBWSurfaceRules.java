package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.creolib.api.world.surface.SteepMaterialCondition;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.region.GBWOverworldRegion;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.SurfaceRuleManager;

public class GBWSurfaceRules implements Register {
    private static final MaterialRules.MaterialRule LAVAROCK = MaterialRules.block(GBWBlocks.LAVAROCK.getDefaultState());
    private static final MaterialRules.MaterialRule GRASSY_LAVAROCK = MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.condition(MaterialRules.water(0, 0), MaterialRules.block(GBWBlocks.GRASSY_LAVAROCK.getDefaultState())));
    private static final MaterialRules.MaterialRule BEACH_SAND = MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(Blocks.SANDSTONE.getDefaultState())), MaterialRules.block(Blocks.SAND.getDefaultState())));
    private static final MaterialRules.MaterialRule VOLCANIC_BEACH_SAND = MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(GBWBlocks.LAVAROCK.getDefaultState())), MaterialRules.block(GBWBlocks.VOLCANIC_SAND.getDefaultState())));
    private static final MaterialRules.MaterialRule SMOOTH_BASALT = MaterialRules.block(Blocks.SMOOTH_BASALT.getDefaultState());
    private static final MaterialRules.MaterialRule RED_ROCK = MaterialRules.sequence(MaterialRules.condition(new SteepMaterialCondition(2), MaterialRules.block(Blocks.TERRACOTTA.getDefaultState())), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, -0.0125, 0.0125), MaterialRules.block(Blocks.GRANITE.getDefaultState())), MaterialRules.block(GBWBlocks.RED_ROCK.getDefaultState()));

    @Override
    public void register() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.condition(MaterialRules.surface(), MaterialRules.sequence(
                createIslandBeachRule(),
                createVolcanicBeachRule(),
                createVolcanicSlopesRule(),
                createVolcanicCraterRule(),
                createHotSpringsRule(),
                createRedRockPeaksRule()
        )));
    }

    public MaterialRules.MaterialRule createIslandBeachRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.ISLAND_BEACH), BEACH_SAND);
    }

    public MaterialRules.MaterialRule createVolcanicBeachRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.VOLCANIC_BEACH), VOLCANIC_BEACH_SAND);
    }

    public MaterialRules.MaterialRule createVolcanicSlopesRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.VOLCANIC_SLOPES), MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), LAVAROCK), GRASSY_LAVAROCK, LAVAROCK));
    }

    public MaterialRules.MaterialRule createVolcanicCraterRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.VOLCANIC_CRATER), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, LAVAROCK), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.5454d), GRASSY_LAVAROCK), MaterialRules.condition(MaterialRules.steepSlope(), GRASSY_LAVAROCK)), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, -.0125d, .0125d), SMOOTH_BASALT), LAVAROCK));
    }

    public MaterialRules.MaterialRule createHotSpringsRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.HOT_SPRINGS), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, MaterialRules.block(GBWBlocks.PEACH_TRAVERTINE.getDefaultState())), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, -.012d), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_STATE_SELECTOR, 0d), MaterialRules.block(Blocks.CALCITE.getDefaultState()))), MaterialRules.block(GBWBlocks.IVORY_TRAVERTINE.getDefaultState())))));
    }

    public MaterialRules.MaterialRule createRedRockPeaksRule() {
        return MaterialRules.condition(MaterialRules.biome(GBWBiomes.RED_ROCK_PEAKS), RED_ROCK);
    }
}
