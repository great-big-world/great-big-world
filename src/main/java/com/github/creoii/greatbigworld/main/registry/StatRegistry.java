package com.github.creoii.greatbigworld.main.registry;

import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatRegistry {
    public static Identifier INTERACT_WITH_SAWMILL;

    public static void register() {
        INTERACT_WITH_SAWMILL = registerStat("interact_with_sawmill", StatFormatter.DEFAULT);
    }

    public static Identifier registerStat(String id, StatFormatter formatter) {
        Identifier identifier = new Identifier(id);
        Registry.register(Registry.CUSTOM_STAT, id, identifier);
        return Stats.CUSTOM.getOrCreateStat(identifier, formatter).getValue();
    }
}
