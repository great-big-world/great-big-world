package com.github.creoii.greatbigworld.main.util;

public interface Register {
    void register();
    default void registerClient() { }
}
