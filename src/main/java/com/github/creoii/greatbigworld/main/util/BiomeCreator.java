package com.github.creoii.greatbigworld.main.util;

import com.github.creoii.greatbigworld.main.registry.PlacedFeatureRegistry;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class BiomeCreator {
    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static Biome createDirtCaves() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addClayOre(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_PACKED_DIRT);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.DEPOSIT_GOLD);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.DEPOSIT_IRON);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.DEPOSIT_COPPER);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.DEPOSIT_CLAY);
        builder2.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.DEPOSIT_PEAT);
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.DIRT_CAVES_CEILING_VEGETATION);
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.DIRT_CAVES_VEGETATION);
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_LUSH_CAVES);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.UNDERGROUND).temperature(.5f).downfall(.5f).spawnSettings(builder.build()).generationSettings(builder2.build()).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(.5f)).music(musicSound).build()).build();
    }
}
