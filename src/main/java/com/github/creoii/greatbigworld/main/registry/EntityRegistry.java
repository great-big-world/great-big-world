package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.model.MooseEntityModel;
import com.github.creoii.greatbigworld.client.render.MooseEntityRenderer;
import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.ItemUtil;
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
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

import java.util.function.Predicate;

public class EntityRegistry implements Register {
    public static final EntityType<MooseEntity> MOOSE = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MooseEntity::new).dimensions(new EntityDimensions(1.2f, 2f, true)).trackRangeChunks(10).build();

    @Override
    public void register() {
        registerMobEntity(MOOSE, new Identifier(GreatBigWorld.NAMESPACE, "moose"), new EntitySettings<>(MOOSE, MooseEntity.createMooseAttributes(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn, BiomeSelectors.tag(Tags.BiomeTags.MOOSE_SPAWNABLE), SpawnGroup.CREATURE, 8, 2, 3, 8211498, 4276545, Items.MAGMA_CUBE_SPAWN_EGG));
    }

    public static final EntityModelLayer MOOSE_MODEL_LAYER = new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "moose"), "main");

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(EntityRegistry.MOOSE, MooseEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MOOSE_MODEL_LAYER, MooseEntityModel::getTexturedModelData);
    }

    public static <L extends MobEntity, T extends EntityType<L>> void registerMobEntity(T entityType, Identifier id, EntitySettings<L, T> settings) {
        Registry.register(Registry.ENTITY_TYPE, id, entityType);
        settings.register();
    }

    public static record EntitySettings<L extends MobEntity, T extends EntityType<L>>(T entityType, DefaultAttributeContainer.Builder builder, SpawnRestriction.Location location, Heightmap.Type heightmap, SpawnRestriction.SpawnPredicate<L> predicate, Predicate<BiomeSelectionContext> biomeSelector, SpawnGroup group, int weight, int minGroupSize, int maxGroupSize, int primaryEggColor, int secondaryEggColor, Item after) {
        public void register() {
            FabricDefaultAttributeRegistry.register(entityType, builder);
            SpawnRestriction.register(entityType, location, heightmap, predicate);
            BiomeModifications.addSpawn(biomeSelector, group, entityType, weight, minGroupSize, maxGroupSize);
            Registry.register(Registry.ITEM, new Identifier(GreatBigWorld.NAMESPACE, Registry.ENTITY_TYPE.getId(entityType).getPath() + "_spawn_egg"), new SpawnEggItem(entityType, primaryEggColor, secondaryEggColor, new FabricItemSettings().group(ItemGroup.MISC)) {
                @Override
                public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
                    ItemUtil.appendStackInGroup(stacks, getDefaultStack(), after);
                }
            });
        }
    }
}
