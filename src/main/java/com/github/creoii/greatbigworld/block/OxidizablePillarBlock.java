package com.github.creoii.greatbigworld.block;

import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;

public class OxidizablePillarBlock extends PillarBlock implements Oxidizable {
    private final OxidationLevel oxidationLevel;

    public OxidizablePillarBlock(OxidationLevel oxidationLevel, Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return oxidationLevel;
    }
}
