package com.github.creoii.greatbigworld.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SteamingTravertineBlock extends Block {
    public SteamingTravertineBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (random.nextInt(20) == 0) {
            System.out.println("explode");
            world.syncWorldEvent(5000, pos, 0);
            world.getEntitiesByClass(LivingEntity.class, Box.from(BlockBox.create(pos.subtract(new Vec3i(-1, 0, -1)), pos.add(new Vec3i(1, 4, 1)))), livingEntity -> true).forEach(livingEntity -> {
                System.out.println(livingEntity.getType().toString());
                livingEntity.addVelocity(new Vec3d(0d, 4d, 0d));
            });
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = pos.getX();
        double e = pos.getY();
        double f = pos.getZ();
        world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d + .5d, e, f + .5d, 0d, .08d, 0d);
        world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d + random.nextDouble(), e + random.nextDouble(), f + random.nextDouble(), 0d, .1d, 0d);
    }
}
