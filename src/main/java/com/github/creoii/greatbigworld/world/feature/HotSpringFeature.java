package com.github.creoii.greatbigworld.world.feature;

import com.github.creoii.greatbigworld.world.feature.config.HotSpringFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Arrays;

public class HotSpringFeature extends Feature<HotSpringFeatureConfig> {
    public HotSpringFeature(Codec<HotSpringFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<HotSpringFeatureConfig> context) {
        PerlinNoiseSampler noiseSampler = new PerlinNoiseSampler(Random.create());
        StructureWorldAccess world = context.getWorld();
        HotSpringFeatureConfig config = context.getConfig();
        BlockPos origin = context.getOrigin().offset(Direction.Axis.Y, config.yOffset());
        Random random = context.getRandom();
        int height = config.height().get(random);
        int radius = config.startRadius().get(random);
        int rimSize = config.rimSize().get(random);
        int innerDepth = Math.min(config.innerDepth().get(random), height);
        for (int y = 0; y < height; ++y) {
            double scale = (y / (double) height) * 5;
            for (int x = -radius; x <= radius; ++x) {
                for (int z = -radius; z <= radius; ++z) {
                    BlockPos pos = origin.add(x, y, z);
                    double distance = Math.sqrt(x * x + z * z);
                    double radiusMod = radius + noiseSampler.sample(x * scale, y, z * scale);
                    if (distance < radiusMod - rimSize && y > 0 && y <= innerDepth) {
                        world.setBlockState(pos, config.innerState().get(random, pos), 3);
                        if (config.solidRim()) {
                            BlockPos.Mutable offset = new BlockPos.Mutable();
                            Arrays.stream(Direction.values()).filter(direction -> direction != Direction.UP).forEach(direction -> {
                                offset.set(pos.offset(direction));
                                if (world.isAir(offset)) {
                                    world.setBlockState(offset, config.rimState().get(random, offset), 3);
                                }
                            });
                        }
                    } else if (distance < radiusMod) {
                        world.setBlockState(pos, config.rimState().get(random, pos), 3);
                    }
                }
            }
            radius += random.nextBoolean() ? 1 : 0;
        }
        return true;
    }
}
