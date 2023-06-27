package com.github.creoii.greatbigworld.main.util;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class GBWStats {
    public static final Identifier INTERACT_WITH_KILN = registerStat("interact_with_kiln", StatFormatter.DEFAULT);
    public static final Identifier INTERACT_WITH_SAWMILL = registerStat("interact_with_sawmill", StatFormatter.DEFAULT);

    private static Identifier registerStat(String id, StatFormatter formatter) {
        Identifier identifier = new Identifier(id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }
}
