package com.github.creoii.greatbigworld.main.integration;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "great_big_world")
@Config.Gui.Background("great_big_world:textures/block/cobblestone_bricks.png")
public class GBWConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean bambooTorchesOnLeaves = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.Category("general_settings")
    public boolean masksAngerEndermen = false;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean pillagersAttackMoose = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Category("general_settings")
    public boolean wolvesAttackMoose = true;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = -1, max = 32)
    @ConfigEntry.Category("general_settings")
    public int maxDistanceForRootConversion = 4;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = -1, max = 24000)
    @ConfigEntry.Category("general_settings")
    public int shedAntlerBaseRegrowTime = 4800;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = -1, max = 9999)
    @ConfigEntry.Category("general_settings")
    public int nautilusOxidizeChance = 1200;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 16)
    @ConfigEntry.Category("general_settings")
    public float dilutingModifier = .5f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart(value = false)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 16)
    @ConfigEntry.Category("general_settings")
    public float auraEffectTransferModifier = .5f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 4800)
    @ConfigEntry.Category("general_settings")
    public int woodenMaskDurability = 140;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 200)
    @ConfigEntry.Category("general_settings")
    public int woodenMaskProtection = 2;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 32)
    @ConfigEntry.Category("general_settings")
    public float woodenMaskToughness = 0f;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 200)
    @ConfigEntry.Category("general_settings")
    public int woodenMaskEnchantability = 11;
}
