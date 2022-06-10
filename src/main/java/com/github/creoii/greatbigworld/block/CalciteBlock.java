package com.github.creoii.greatbigworld.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class CalciteBlock extends Block {
    public static final Map<DyeColor, BlockState> COLOR_TO_STATES = new HashMap<DyeColor, BlockState>();
    private final DyeColor color;

    public CalciteBlock(DyeColor color) {
        super(FabricBlockSettings.copy(Blocks.CALCITE));
        this.color = color;
    }

    public static void loadMap() {
        COLOR_TO_STATES.put(DyeColor.WHITE, Blocks.CALCITE.getDefaultState());
    }

    public static void dyeCalciteInRange(World world, int size, DyeColor color) {
        BlockPos pos;
        Block state;
        for (int z = 0; z < size; ++z) {
            for (int y = 0; y < size; ++y) {
                for (int x = 0; x < size; ++x) {
                    pos = new BlockPos(x, y, z);
                    state = world.getBlockState(pos).getBlock();
                    if (state instanceof CalciteBlock || state == Blocks.CALCITE) {
                        world.setBlockState(pos, COLOR_TO_STATES.get(color), 3);
                    }
                }
            }
        }
    }
}
