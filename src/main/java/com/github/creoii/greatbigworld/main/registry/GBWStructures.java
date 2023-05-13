package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.world.processor.MoaiStructureProcessor;
import com.github.creoii.greatbigworld.world.processor.SwampPyramidStructureProcessor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class GBWStructures implements Register {
    public static RegistryKey<Structure> SWAMP_PYRAMID;
    public static RegistryKey<Structure> LIGHTHOUSE;
    public static RegistryKey<Structure> MOAI;

    public static final StructureProcessorType<SwampPyramidStructureProcessor> SWAMP_PYRAMID_PROCESSOR = () -> SwampPyramidStructureProcessor.CODEC;
    public static final StructureProcessorType<MoaiStructureProcessor> MOAI_PROCESSOR = () -> MoaiStructureProcessor.CODEC;

    @Override
    public void register() {
        SWAMP_PYRAMID = RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(GreatBigWorld.NAMESPACE, "swamp_pyramid"));
        LIGHTHOUSE = RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(GreatBigWorld.NAMESPACE, "lighthouse"));
        MOAI = RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(GreatBigWorld.NAMESPACE, "moai"));
        Registry.register(Registries.STRUCTURE_PROCESSOR, new Identifier(GreatBigWorld.NAMESPACE, "swamp_pyramid"), SWAMP_PYRAMID_PROCESSOR);
        Registry.register(Registries.STRUCTURE_PROCESSOR, new Identifier(GreatBigWorld.NAMESPACE, "moai"), MOAI_PROCESSOR);
    }
}
