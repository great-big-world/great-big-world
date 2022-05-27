package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.util.predicate.SkyVisiblePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PlacedFeatureRegistry {
    public static RegistryEntry<PlacedFeature> DIRT_CAVES_VEGETATION;
    public static RegistryEntry<PlacedFeature> DIRT_CAVES_CEILING_VEGETATION;
    public static RegistryEntry<PlacedFeature> DEPOSIT_GOLD;
    public static RegistryEntry<PlacedFeature> DEPOSIT_IRON;
    public static RegistryEntry<PlacedFeature> DEPOSIT_COPPER;
    public static RegistryEntry<PlacedFeature> DEPOSIT_CLAY;
    public static RegistryEntry<PlacedFeature> DEPOSIT_PEAT;
    public static RegistryEntry<PlacedFeature> ORE_PACKED_DIRT;

    public static void register() {
        DIRT_CAVES_VEGETATION = PlacedFeatures.register("dirt_caves_vegetation", ConfiguredFeatureRegistry.DIRT_PATCH, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), BiomePlacementModifier.of(), BlockFilterPlacementModifier.of(new SkyVisiblePredicate(BlockPos.ORIGIN, .4f)));
        DIRT_CAVES_CEILING_VEGETATION = PlacedFeatures.register("dirt_caves_ceiling_vegetation", ConfiguredFeatureRegistry.DIRT_PATCH_CEILING, CountPlacementModifier.of(125), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)), BiomePlacementModifier.of());

        DEPOSIT_GOLD = PlacedFeatures.register("deposit_gold", ConfiguredFeatureRegistry.GOLD_DEPOSIT, modifiers(56, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
        DEPOSIT_IRON = PlacedFeatures.register("deposit_iron", ConfiguredFeatureRegistry.IRON_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
        DEPOSIT_COPPER = PlacedFeatures.register("deposit_copper", ConfiguredFeatureRegistry.COPPER_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
        DEPOSIT_CLAY = PlacedFeatures.register("deposit_clay", ConfiguredFeatureRegistry.CLAY_DEPOSIT, modifiers(42, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
        DEPOSIT_PEAT = PlacedFeatures.register("deposit_peat", ConfiguredFeatureRegistry.PEAT_DEPOSIT, modifiers(64, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
        ORE_PACKED_DIRT = PlacedFeatures.register("ore_packed_dirt", ConfiguredFeatureRegistry.ORE_PACKED_DIRT, modifiers(192, HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));
    }

    private static List<PlacementModifier> modifiers(int count, PlacementModifier heightModifier) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
}
