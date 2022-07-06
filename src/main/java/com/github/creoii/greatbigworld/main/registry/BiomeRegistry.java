package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.DirectionalNyliumBlock;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.NetherPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.SurfaceRuleManager;

public class BiomeRegistry {
    public static final RegistryKey<Biome> DIRT_CAVES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "dirt_caves"));
    public static final RegistryKey<Biome> TWISTED_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "twisted_forest"));
    public static final RegistryKey<Biome> RED_ROCK_PEAKS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GreatBigWorld.NAMESPACE, "red_rock_peaks"));

    public static final MultiNoiseUtil.NoiseHypercube DIRT_CAVES_POINT = MultiNoiseUtil.createNoiseHypercube(MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(.5f, 1f), MultiNoiseUtil.ParameterRange.of(.1f, .2f), MultiNoiseUtil.ParameterRange.of(-1f, 1f), 0f);
    public static final MultiNoiseUtil.NoiseHypercube TWISTED_FOREST_POINT = MultiNoiseUtil.createNoiseHypercube(0f, .75f, 0f, 0f, 0f, 0f, .575f);
    public static final MultiNoiseUtil.NoiseHypercube RED_ROCK_PEAKS_POINT = MultiNoiseUtil.createNoiseHypercube(MultiNoiseUtil.ParameterRange.of(.55f, 1f), MultiNoiseUtil.ParameterRange.of(-.1f, .1f), MultiNoiseUtil.ParameterRange.of(.25f, 1f), MultiNoiseUtil.ParameterRange.of(-.9f, -.33f), MultiNoiseUtil.ParameterRange.of(-.9f, .9f), MultiNoiseUtil.ParameterRange.of(0f), 0f);

    public static void register() {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, DIRT_CAVES.getValue(), createDirtCaves());
        BuiltinRegistries.add(BuiltinRegistries.BIOME, TWISTED_FOREST.getValue(), createTwistedForest());
        BuiltinRegistries.add(BuiltinRegistries.BIOME, RED_ROCK_PEAKS.getValue(), createRedRockPeaks());

        NetherBiomes.addNetherBiome(TWISTED_FOREST, TWISTED_FOREST_POINT);

        modifyBiomes();
    }

    public static void registerSurfaces() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, GreatBigWorld.NAMESPACE, MaterialRules.condition(MaterialRules.biome(RED_ROCK_PEAKS), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(256), 0), MaterialRules.block(Blocks.ORANGE_TERRACOTTA.getDefaultState())), MaterialRules.condition(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(74), 1), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.909d, -.5454d), MaterialRules.block(Blocks.TERRACOTTA.getDefaultState())), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -.1818d, .1818d), MaterialRules.block(Blocks.TERRACOTTA.getDefaultState())), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, .5454d, .909d), MaterialRules.block(Blocks.TERRACOTTA.getDefaultState())), MaterialRules.terracottaBands())), MaterialRules.condition(MaterialRules.water(-1, 0), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(Blocks.RED_SANDSTONE.getDefaultState())), MaterialRules.block(Blocks.RED_SAND.getDefaultState()))), MaterialRules.condition(MaterialRules.not(MaterialRules.hole()), MaterialRules.block(Blocks.ORANGE_TERRACOTTA.getDefaultState())), MaterialRules.condition(MaterialRules.waterWithStoneDepth(-6, -1), MaterialRules.block(Blocks.WHITE_TERRACOTTA.getDefaultState())), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.block(Blocks.STONE.getDefaultState())), MaterialRules.block(Blocks.GRAVEL.getDefaultState())))), MaterialRules.condition(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1), MaterialRules.sequence(MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(63), 0), MaterialRules.condition(MaterialRules.not(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(74), 1)), MaterialRules.block(Blocks.ORANGE_TERRACOTTA.getDefaultState()))), MaterialRules.terracottaBands())), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(MaterialRules.waterWithStoneDepth(-6, -1), MaterialRules.block(Blocks.WHITE_TERRACOTTA.getDefaultState()))))));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, GreatBigWorld.NAMESPACE, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHERRACK, .54, 1.7976931348623157)), MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(31), 0), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17, 1.7976931348623157), MaterialRules.block(BlockRegistry.TWISTED_WART_BLOCK.getDefaultState())), MaterialRules.block(BlockRegistry.TWISTED_NYLIUM.getDefaultState())))))), MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHERRACK, .54, 1.7976931348623157)), MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(31), 0), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17, 1.7976931348623157), MaterialRules.block(BlockRegistry.TWISTED_WART_BLOCK.getDefaultState())), MaterialRules.block(BlockRegistry.TWISTED_NYLIUM.getDefaultState().with(DirectionalNyliumBlock.FACING, Direction.DOWN)))))))));
    }

    private static void modifyBiomes() {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_RIVER), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.ALGAE_PATCH.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE).and(BiomeSelectors.excludeByKey(BiomeKeys.SPARSE_JUNGLE)), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.MAHOGANY.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_PALO_VERDE.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.GRASSY_STONE_PATCH.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.GRASSY_DEEPSLATE_PATCH.getKey().get());

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_JUNGLE).and(BiomeSelectors.excludeByKey(BiomeKeys.FLOWER_FOREST))), SpawnGroup.AMBIENT, EntityRegistry.BUTTERFLY, 8, 2, 5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST).or(BiomeSelectors.includeByKey(BiomeKeys.SUNFLOWER_PLAINS)), SpawnGroup.AMBIENT, EntityRegistry.BUTTERFLY, 12, 2, 5);
    }

    private static int getSkyColor(float temperature) {
        float f = temperature / 3f;
        f = MathHelper.clamp(f, -1f, 1f);
        return MathHelper.hsvToRgb(.62222224f - f * .05f, .5f + f * .1f, 1f);
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
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).temperature(.5f).downfall(.5f).spawnSettings(builder.build()).generationSettings(builder2.build()).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(.5f)).music(musicSound).build()).build();
    }

    public static Biome createTwistedForest() {
        SpawnSettings spawnSettings = new SpawnSettings.Builder().spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = new GenerationSettings.Builder().carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
                .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TWISTED_FUNGI);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(2.0F).downfall(0.0F).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(3413825).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome createRedRockPeaks() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addExtraGoldOre(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addBadlandsGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addBadlandsVegetation(generationSettings);
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(2f).downfall(0f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(2f)).foliageColor(10387789).grassColor(9470285).moodSound(BiomeMoodSound.CAVE).build()).generationSettings(generationSettings.build()).spawnSettings(spawnSettings.build()).build();
    }
}
