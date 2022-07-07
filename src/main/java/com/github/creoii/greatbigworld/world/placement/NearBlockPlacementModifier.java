package com.github.creoii.greatbigworld.world.placement;

import com.github.creoii.greatbigworld.main.registry.PlacementModifierRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.List;

public class NearBlockPlacementModifier extends AbstractConditionalPlacementModifier {
    public static final Codec<NearBlockPlacementModifier> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(Identifier.CODEC.listOf().fieldOf("blocks").forGetter(predicate -> {
            return predicate.blocks;
        }), Codec.INT.fieldOf("radius").forGetter(predicate -> {
            return predicate.radius;
        })).apply(instance, NearBlockPlacementModifier::new);
    });

    private final List<Identifier> blocks;
    private final int radius;

    private NearBlockPlacementModifier(List<Identifier> blocks, int radius) {
        this.blocks = blocks;
        this.radius = radius;
    }

    public static NearBlockPlacementModifier of(List<Identifier> blocks, int radius) {
        return new NearBlockPlacementModifier(blocks, radius);
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierRegistry.NEAR_BLOCK;
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        Iterable<BlockPos> positions = BlockPos.iterate(pos.subtract(Vec3i.ZERO.multiply(radius)), pos.add(Vec3i.ZERO.multiply(radius)));
        for (BlockPos pos1 : positions) {
            for (Identifier identifier : blocks) {
                if (context.getWorld().getBlockState(pos1).isOf(Registry.BLOCK.get(identifier))) return true;
            }
        }

        return false;
    }
}
