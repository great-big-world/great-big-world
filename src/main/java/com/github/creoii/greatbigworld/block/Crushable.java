package com.github.creoii.greatbigworld.block;

/**
 * Implement to allow your block to be crushable by an anvil
 */
public interface Crushable {
    /**
     * Minimum fall distance for the anvil to crush the block
     *
     * Return -1 for any distance
     */
    default int getMinimumFallDistance() {
        return 1;
    }

    default boolean shouldDropOnBreak() {
        return true;
    }
}
