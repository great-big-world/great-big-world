package com.github.creoii.greatbigworld.block;

import com.github.creoii.creolib.api.util.block.BlockUtil;
import com.github.creoii.creolib.api.util.block.CBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class TallBeachgrassBlock extends TallPlantBlock {
    public TallBeachgrassBlock() {
        super(CBlockSettings.copyOf(Blocks.TALL_GRASS).fireSettings(new BlockUtil.FireSettings(75, 120)));
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.SAND);
    }
}
