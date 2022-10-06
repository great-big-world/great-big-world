package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.StructureType;

public class StructureRegistry implements Register {
    public static final StructureType<JigsawStructure> PILLAGER_CASTLE = () -> JigsawStructure.CODEC;

    @Override
    public void register() {
        Registry.register(Registry.STRUCTURE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "pillager_castle"), PILLAGER_CASTLE);
    }
}
