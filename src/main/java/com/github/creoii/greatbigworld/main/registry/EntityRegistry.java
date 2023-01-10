package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.model.MooseEntityModel;
import com.github.creoii.greatbigworld.client.render.AncientEntityRenderer;
import com.github.creoii.greatbigworld.client.render.MooseEntityRenderer;
import com.github.creoii.greatbigworld.client.render.StoneArrowEntityRenderer;
import com.github.creoii.greatbigworld.entity.AncientEntity;
import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.entity.StoneArrowEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.main.util.Tags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class EntityRegistry implements Register {
    public static final EntityType<MooseEntity> MOOSE = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MooseEntity::new).dimensions(EntityDimensions.fixed(1.6f, 2.1f)).trackRangeChunks(10).build();
    public static final EntityType<StoneArrowEntity> STONE_ARROW = FabricEntityTypeBuilder.<StoneArrowEntity>create(SpawnGroup.MISC, StoneArrowEntity::new).dimensions(EntityDimensions.fixed(.5f, .5f)).trackRangeChunks(4).trackedUpdateRate(20).build();
    public static final EntityType<AncientEntity> ANCIENT = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AncientEntity::new).dimensions(EntityDimensions.fixed(.6f, 1.99f)).trackRangeChunks(8).build();

    @Override
    public void register() {
        registerMobEntity(MOOSE, new Identifier(GreatBigWorld.NAMESPACE, "moose"), new EntitySettings<>(MOOSE, MooseEntity.createMooseAttributes(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn, BiomeSelectors.tag(Tags.BiomeTags.MOOSE_SPAWNABLE), SpawnGroup.CREATURE, 8, 1, 3, 8211498, 4276545));
        registerEntity(STONE_ARROW, new Identifier(GreatBigWorld.NAMESPACE, "stone_arrow"));
        registerMobEntity(ANCIENT, new Identifier(GreatBigWorld.NAMESPACE, "ancient"), new EntitySettings<>(ANCIENT, AncientEntity.createAbstractSkeletonAttributes(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn, null, SpawnGroup.CREATURE, 0, 1, 3, 7631731, 7377453));
    }

    public static final EntityModelLayer MOOSE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "main");
    public static final EntityModelLayer MOOSE_SADDLE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "saddle");
    public static final EntityModelLayer ANCIENT_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "ancient"), "main");
    public static final EntityModelLayer ANCIENT_OUTER_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "ancient"), "outer");

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(EntityRegistry.MOOSE, MooseEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ANCIENT, AncientEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.STONE_ARROW, StoneArrowEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_SADDLE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ANCIENT_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ANCIENT_OUTER_MODEL_LAYER, SkeletonEntityModel::getTexturedModelData);
    }

    public static <L extends Entity, T extends EntityType<L>> void registerEntity(T entityType, Identifier id) {
        Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }

    public static <L extends MobEntity, T extends EntityType<L>> void registerMobEntity(T entityType, Identifier id, EntitySettings<L, T> settings) {
        registerEntity(entityType, id);
        settings.register();
    }

    public static record EntitySettings<L extends MobEntity, T extends EntityType<L>>(T entityType, DefaultAttributeContainer.Builder builder, SpawnRestriction.Location location, Heightmap.Type heightmap, SpawnRestriction.SpawnPredicate<L> predicate, @Nullable Predicate<BiomeSelectionContext> biomeSelector, SpawnGroup group, int weight, int minGroupSize, int maxGroupSize, int primaryEggColor, int secondaryEggColor) {
        public void register() {
            FabricDefaultAttributeRegistry.register(entityType, builder);
            SpawnRestriction.register(entityType, location, heightmap, predicate);
            if (biomeSelector != null)
                BiomeModifications.addSpawn(biomeSelector, group, entityType, weight, minGroupSize, maxGroupSize);
            ItemRegistry.registerItem(new Identifier(GreatBigWorld.NAMESPACE, Registries.ENTITY_TYPE.getId(entityType).getPath() + "_spawn_egg"), new SpawnEggItem(entityType, primaryEggColor, secondaryEggColor, new FabricItemSettings()), ItemGroups.SPAWN_EGGS);
        }
    }
}
