package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.registry.PlacedFeatureRegistry;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class GBWBiomeCreator {
    public static Biome createAspenForest() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_TAIGA);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_TAIGA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_ASPEN_YELLOW_DENSE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_ASPEN_GREEN_DENSE);

        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder2);
        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).temperature(.6f).downfall(.6f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(MathHelper.hsvToRgb(.61222224f, .52f, 1f)).moodSound(BiomeMoodSound.CAVE).music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST)).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build();
    }
}
