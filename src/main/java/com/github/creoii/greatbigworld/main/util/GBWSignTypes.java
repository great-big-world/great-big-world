package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.fabric.api.object.builder.v1.sign.SignTypeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class GBWSignTypes {
    public static final SignType MAHOGANY = SignTypeRegistry.registerSignType(new Identifier(GreatBigWorld.NAMESPACE, "mahogany"));
    public static final SignType ASPEN = SignTypeRegistry.registerSignType(new Identifier(GreatBigWorld.NAMESPACE, "aspen"));
}
