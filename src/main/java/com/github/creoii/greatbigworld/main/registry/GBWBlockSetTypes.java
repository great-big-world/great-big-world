package com.github.creoii.greatbigworld.main.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.util.Identifier;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public final class GBWBlockSetTypes {
    public static final BlockSetType MAHOGANY = BlockSetTypeRegistry.registerWood(new Identifier(NAMESPACE, "mahogany"));
    public static final BlockSetType ASPEN = BlockSetTypeRegistry.registerWood(new Identifier(NAMESPACE, "aspen"));
    public static final BlockSetType ACAI = BlockSetTypeRegistry.registerWood(new Identifier(NAMESPACE, "acai"));
    public static final BlockSetType WISTERIA = BlockSetTypeRegistry.registerWood(new Identifier(NAMESPACE, "wisteria"));
    public static final BlockSetType PINE = BlockSetTypeRegistry.registerWood(new Identifier(NAMESPACE, "pine"));
}
