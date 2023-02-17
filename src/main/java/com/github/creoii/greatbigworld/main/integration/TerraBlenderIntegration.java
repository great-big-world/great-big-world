package com.github.creoii.greatbigworld.main.integration;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.world.region.GreatBigOverworldRegion;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;
import terrablender.worldgen.TBSurfaceRuleData;

public class TerraBlenderIntegration implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new GreatBigOverworldRegion(3));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, createSurfaceRules());
    }

    public static MaterialRules.MaterialRule createSurfaceRules() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(BiomeRegistry.HOT_SPRINGS), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, MaterialRules.block(BlockRegistry.PEACH_TRAVERTINE.getDefaultState())), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, -.012d), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_STATE_SELECTOR, 0d), MaterialRules.block(Blocks.CALCITE.getDefaultState()))), MaterialRules.block(BlockRegistry.IVORY_TRAVERTINE.getDefaultState()))))),
                MaterialRules.condition(MaterialRules.surface(), TBSurfaceRuleData.overworld())
        );
    }
}
