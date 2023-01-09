package com.github.creoii.greatbigworld.main.integration;

import com.github.creoii.greatbigworld.main.util.Register;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ModMenuIntegration implements Register, ModMenuApi {
    public static GreatBigWorldConfig CONFIG = new GreatBigWorldConfig();

    @Override
    public void register() {
        AutoConfig.register(GreatBigWorldConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(GreatBigWorldConfig.class).getConfig();
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(GreatBigWorldConfig.class, parent).get();
    }
}