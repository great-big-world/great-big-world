package com.github.creoii.greatbigworld.world.placement;

import com.github.creoii.greatbigworld.main.registry.PlacementRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class SkyVisiblePlacementModifier extends AbstractConditionalPlacementModifier {
    private static final SkyVisiblePlacementModifier INSTANCE = new SkyVisiblePlacementModifier();
    public static Codec<SkyVisiblePlacementModifier> CODEC = Codec.unit(() -> INSTANCE);

    private SkyVisiblePlacementModifier() {}

    public static SkyVisiblePlacementModifier of() {
        return INSTANCE;
    }

    @Override
    public boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        return context.getWorld().getLightLevel(LightType.SKY, pos) <= 7;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementRegistry.SKY_VISIBLE;
    }
}
