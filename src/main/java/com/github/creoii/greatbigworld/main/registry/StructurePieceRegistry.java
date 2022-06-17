package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.world.structure.ShackStructure;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;

public class StructurePieceRegistry {
    public static StructurePieceType SHACK;

    public static void register() {
        SHACK = Registry.register(Registry.STRUCTURE_PIECE, "sha", ShackStructure.Piece::new);
    }
}
