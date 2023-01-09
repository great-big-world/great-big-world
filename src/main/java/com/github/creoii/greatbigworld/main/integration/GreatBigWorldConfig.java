package com.github.creoii.greatbigworld.main.integration;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "great_big_world")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class GreatBigWorldConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean mahoganyInOtherBiomes = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean aspenInOtherBiomes = true;
}
