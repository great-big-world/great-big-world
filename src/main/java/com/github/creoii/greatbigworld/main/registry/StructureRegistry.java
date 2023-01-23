package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.processor.SwampPyramidStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.structure.Structure;

public class StructureRegistry implements Register {
    public static RegistryKey<Structure> SWAMP_PYRAMID;

    public static final StructureProcessorType<SwampPyramidStructureProcessor> SWAMP_PYRAMID_PROCESSOR = () -> SwampPyramidStructureProcessor.CODEC;

    @Override
    public void register() {
        SWAMP_PYRAMID = RegistryKey.of(Registry.STRUCTURE_KEY, new Identifier(GreatBigWorld.NAMESPACE, "swamp_pyramid"));
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(GreatBigWorld.NAMESPACE, "swamp_pyramid"), SWAMP_PYRAMID_PROCESSOR);
    }
}
