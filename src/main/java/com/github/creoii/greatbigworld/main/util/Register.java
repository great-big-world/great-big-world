package com.github.creoii.greatbigworld.main.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface Register {
    void register();

    @Environment(EnvType.CLIENT)
    default void registerClient() { }
}
