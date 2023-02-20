package com.github.creoii.greatbigworld.main.integration;

import com.github.creoii.greatbigworld.world.region.GreatBigOverworldRegion;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class TerraBlenderIntegration implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new GreatBigOverworldRegion(3));
    }
}
