package com.github.creoii.greatbigworld.block;

public interface Crushable {
    default int getMinimumFallDistance() {
        return 1;
    }
}
