package com.github.creoii.greatbigworld.main.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

import static com.github.creoii.greatbigworld.main.GreatBigWorld.NAMESPACE;

public final class GBWWoodTypes {
    public static final WoodType MAHOGANY = WoodTypeRegistry.register(new Identifier(NAMESPACE, "mahogany"), GBWBlockSetTypes.MAHOGANY);
    public static final WoodType ASPEN = WoodTypeRegistry.register(new Identifier(NAMESPACE, "aspen"), GBWBlockSetTypes.ASPEN);
    public static final WoodType ACAI = WoodTypeRegistry.register(new Identifier(NAMESPACE, "acai"), GBWBlockSetTypes.ACAI);
    public static final WoodType WISTERIA = WoodTypeRegistry.register(new Identifier(NAMESPACE, "wisteria"), GBWBlockSetTypes.WISTERIA);
    public static final WoodType PINE = WoodTypeRegistry.register(new Identifier(NAMESPACE, "pine"), GBWBlockSetTypes.PINE);
    public static final WoodType PALO_VERDE = WoodTypeRegistry.register(new Identifier(NAMESPACE, "palo_verde"), GBWBlockSetTypes.PALO_VERDE);
}
