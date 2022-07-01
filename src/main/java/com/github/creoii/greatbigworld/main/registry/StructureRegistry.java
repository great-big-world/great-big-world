package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.world.structure.ShackStructure;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.StructureType;

public class StructureRegistry {
    public static StructureType<ShackStructure> SHACK;
    public static StructureType<JigsawStructure> DEEP_DUNGEON;

    public static void register() {
        SHACK = Registry.register(Registry.STRUCTURE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "shack"), () -> ShackStructure.CODEC);
        DEEP_DUNGEON = Registry.register(Registry.STRUCTURE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "deep_dungeon"), () -> JigsawStructure.CODEC);
    }
}
