package com.github.creoii.greatbigworld.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SteamingTravertineBlock extends Block {
    public SteamingTravertineBlock(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (random.nextInt(1) == 0) {
            world.getNonSpectatingEntities(Entity.class, Box.of(pos.toCenterPos(), 3d, 3d, 3d)).forEach(livingEntity -> {
                System.out.println(livingEntity.getType().getLootTableId());
                Vec3d vec3d = livingEntity.getVelocity();
                livingEntity.setVelocity(vec3d.x, Math.min(.7d, vec3d.y + .06d), vec3d.z);
                livingEntity.fallDistance = 0f;
            });

            if (!world.isClient) {
                System.out.println("particles");
                for (int i = 0; i < 2; ++i) {
                    world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + world.random.nextDouble(), (double) pos.getY() + 1d, (double)pos.getZ() + world.random.nextDouble(), random.nextDouble() * (random.nextBoolean() ? -.5d : .5d), .5d, random.nextDouble() * (random.nextBoolean() ? -.5d : .5d));
                    world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + world.random.nextDouble(), (double) pos.getY() + 1d, (double)pos.getZ() + world.random.nextDouble(), random.nextDouble() * (random.nextBoolean() ? -.5d : .5d), .5d, random.nextDouble() * (random.nextBoolean() ? -.5d : .5d));
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = pos.getX();
        double e = pos.getY();
        double f = pos.getZ();
        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d + .5d, e, f + .5d, 0d, .08d, 0d);
        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d + random.nextDouble(), e + random.nextDouble(), f + random.nextDouble(), 0d, .1d, 0d);
    }
}
