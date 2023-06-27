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
            world.syncWorldEvent(5000, pos, 0);
            world.getEntitiesByClass(LivingEntity.class, Box.from(BlockBox.create(pos.subtract(new Vec3i(-1, 0, -1)), pos.add(new Vec3i(1, 4, 1)))), livingEntity -> true).forEach(livingEntity -> {
                Vec3d vec3d = livingEntity.getVelocity();
                livingEntity.setVelocity(vec3d.x, Math.min(.7d, vec3d.y + .06d), vec3d.z);
                livingEntity.fallDistance = 0f;
            });

            if (!world.isClient) {
                for (int i = 0; i < 2; ++i) {
                    world.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + world.random.nextDouble(), pos.getY() + 1d, (double)pos.getZ() + world.random.nextDouble(), 1, 0d, 0d, 0d, .6d);
                    world.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + world.random.nextDouble(), pos.getY() + 1d, (double)pos.getZ() + world.random.nextDouble(), 1, 0d, .01d, 0d, .2d);
                }
            }
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
