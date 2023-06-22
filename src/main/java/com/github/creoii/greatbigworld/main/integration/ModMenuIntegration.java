package com.github.creoii.greatbigworld.main.integration;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.jetbrains.annotations.Nullable;

public class ModMenuIntegration implements ModMenuApi {
    @Nullable
    public static GBWConfig CONFIG = GreatBigWorld.CONFIG_AVAILABLE ? new GBWConfig() : null;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (CONFIG != null)
                return CONFIG.getYACL().generateScreen(parent);
            return null;
        };
    }
}