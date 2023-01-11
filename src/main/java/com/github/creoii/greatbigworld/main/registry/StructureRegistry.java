package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class StructureRegistry implements Register {
    public static RegistryKey<Structure> SWAMP_PYRAMID;
    
    @Override
    public void register() {
        SWAMP_PYRAMID = RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(GreatBigWorld.NAMESPACE, "swamp_pyramid"));
    }
}
