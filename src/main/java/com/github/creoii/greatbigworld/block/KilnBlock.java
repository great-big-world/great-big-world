package com.github.creoii.greatbigworld.block;

import com.github.creoii.creolib.api.util.block.CBlockSettings;
import com.github.creoii.greatbigworld.block.entity.KilnBlockEntity;
import com.github.creoii.greatbigworld.main.registry.GBWBlockEntities;
import com.github.creoii.greatbigworld.main.util.GBWStats;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KilnBlock extends AbstractFurnaceBlock {
    public KilnBlock() {
        super(CBlockSettings.copy(Blocks.BLAST_FURNACE).strength(2f, 6f));
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof KilnBlockEntity kilnBlockEntity) {
            player.openHandledScreen(kilnBlockEntity);
            player.incrementStat(GBWStats.INTERACT_WITH_KILN);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KilnBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return KilnBlock.checkType(world, type, GBWBlockEntities.KILN);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }
        double d = (double)pos.getX() + .5d;
        double e = pos.getY();
        double f = (double)pos.getZ() + .5d;
        if (random.nextDouble() < .1d) {
            world.playSound(d, e, f, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1f, 1f, false);
        }
        world.addParticle(ParticleTypes.SMOKE, d, e + 1.1d, f, 0d, 0d, 0d);
    }
}
