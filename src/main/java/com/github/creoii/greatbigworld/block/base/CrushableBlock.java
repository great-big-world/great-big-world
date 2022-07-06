package com.github.creoii.greatbigworld.block.base;

import com.github.creoii.greatbigworld.block.Crushable;
import net.minecraft.block.Block;

public class CrushableBlock extends Block implements Crushable {
    private final int minFallDistance;

    public CrushableBlock(int minFallDistance, Settings settings) {
        super(settings);
        this.minFallDistance = minFallDistance;
    }

    @Override
    public int getMinimumFallDistance() {
        return minFallDistance;
    }
}
