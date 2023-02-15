package com.github.creoii.greatbigworld.main.integration;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.registry.BiomeRegistry;
import com.github.creoii.greatbigworld.world.region.GreatBigOverworldRegion;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class TerraBlenderIntegration implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new GreatBigOverworldRegion(3));
        registerSurfaceRules();
    }

    public void registerSurfaceRules() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.condition(MaterialRules.biome(BiomeRegistry.HOT_SPRINGS), MaterialRules.block(Blocks.CALCITE.getDefaultState())));
    }
}
