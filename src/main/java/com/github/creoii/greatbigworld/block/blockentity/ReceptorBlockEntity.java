package com.github.creoii.greatbigworld.block.blockentity;

import com.github.creoii.greatbigworld.main.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ReceptorBlockEntity extends BlockEntity {
    public ReceptorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.RECEPTOR, pos, state);
    }
}
