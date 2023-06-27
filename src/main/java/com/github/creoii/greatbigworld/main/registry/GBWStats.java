package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class GBWStats implements Register {
    public static Identifier INTERACT_WITH_KILN;
    public static Identifier INTERACT_WITH_SAWMILL;
    public static Identifier ANTLER_SHEDS_SEEN;
    public static Identifier MOOSE_ONE_CM;

    @Override
    public void register() {
        INTERACT_WITH_KILN = registerStat(new Identifier(GreatBigWorld.NAMESPACE, "interact_with_kiln"), StatFormatter.DEFAULT);
        INTERACT_WITH_SAWMILL = registerStat(new Identifier(GreatBigWorld.NAMESPACE, "interact_with_sawmill"), StatFormatter.DEFAULT);
        ANTLER_SHEDS_SEEN = registerStat(new Identifier(GreatBigWorld.NAMESPACE, "antler_sheds_seen"), StatFormatter.DEFAULT);
        MOOSE_ONE_CM = registerStat(new Identifier(GreatBigWorld.NAMESPACE, "moose_one_cm"), StatFormatter.DISTANCE);
    }

    private static Identifier registerStat(Identifier id, StatFormatter formatter) {
        Registry.register(Registries.CUSTOM_STAT, id, id);
        Stats.CUSTOM.getOrCreateStat(id, formatter);
        return id;
    }
}
