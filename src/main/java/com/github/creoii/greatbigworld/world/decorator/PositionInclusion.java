package com.github.creoii.greatbigworld.world.decorator;

import net.minecraft.util.StringIdentifiable;

public enum PositionInclusion implements StringIdentifiable {
    LOGS,
    LEAVES,
    ALL;

    @SuppressWarnings("deprecation")
    public static final StringIdentifiable.Codec<PositionInclusion> CODEC = StringIdentifiable.createCodec(PositionInclusion::values);

    @Override
    public String asString() {
        return name().toLowerCase();
    }
}
