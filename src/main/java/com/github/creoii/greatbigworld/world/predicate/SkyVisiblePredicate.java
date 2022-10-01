package com.github.creoii.greatbigworld.world.predicate;

import com.github.creoii.greatbigworld.main.registry.PredicateRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.blockpredicate.BlockPredicateType;

public record SkyVisiblePredicate(BlockPos offset, float chance) implements BlockPredicate {
    public static final Codec<SkyVisiblePredicate> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockPos.CODEC.optionalFieldOf("offset", BlockPos.ORIGIN).forGetter(predicate -> {
            return predicate.offset;
        }), Codec.floatRange(0f, 1f).optionalFieldOf("chance", 1f).forGetter(predicate -> {
            return predicate.chance;
        })).apply(instance, SkyVisiblePredicate::new);
    });

    public static SkyVisiblePredicate of(float chance) {
        return new SkyVisiblePredicate(BlockPos.ORIGIN, chance);
    }

    @Override
    public BlockPredicateType<?> getType() {
        return PredicateRegistry.SKY_VISIBLE;
    }

    @Override
    public boolean test(StructureWorldAccess structureWorldAccess, BlockPos pos) {
        return structureWorldAccess.getRandom().nextFloat() <= chance && structureWorldAccess.isSkyVisible(pos.add(offset));
    }
}
