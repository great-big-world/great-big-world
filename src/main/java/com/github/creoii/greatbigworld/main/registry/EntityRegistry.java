package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.render.BearEntityRenderer;
import com.github.creoii.greatbigworld.client.render.ButterflyEntityRenderer;
import com.github.creoii.greatbigworld.client.render.FallingConcretePowderEntityRenderer;
import com.github.creoii.greatbigworld.entity.BearEntity;
import com.github.creoii.greatbigworld.entity.ButterflyEntity;
import com.github.creoii.greatbigworld.entity.DaggerEntity;
import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class EntityRegistry {
    //region Colormatic
    public static final EntityType<FallingConcretePowderEntity> FALLING_CONCRETE = FabricEntityTypeBuilder.<FallingConcretePowderEntity>create(SpawnGroup.MISC, FallingConcretePowderEntity::new).forceTrackedVelocityUpdates(true).dimensions(new EntityDimensions(.98f, .98f, true)).trackRangeChunks(10).trackedUpdateRate(20).build();
    public static final EntityType<ButterflyEntity> BUTTERFLY = FabricEntityTypeBuilder.<ButterflyEntity>create(SpawnGroup.AMBIENT, ButterflyEntity::new).dimensions(new EntityDimensions(.4f, .2f, true)).trackRangeChunks(10).build();
    //endregion

    //region Cloak And Dagger
    public static final EntityType<DaggerEntity> DAGGER = FabricEntityTypeBuilder.<DaggerEntity>create(SpawnGroup.MISC, DaggerEntity::new).dimensions(new EntityDimensions(.25f, .25f, true)).trackRangeChunks(4).trackedUpdateRate(10).build();
    //endregion

    //region Wonders of the Wild
    public static final EntityType<BearEntity> BEAR = FabricEntityTypeBuilder.<BearEntity>create(SpawnGroup.CREATURE, BearEntity::new).dimensions(new EntityDimensions(1.4f, 1.4f, true)).trackRangeChunks(10).build();
    //endregion

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "falling_concrete"), FALLING_CONCRETE);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "butterfly"), BUTTERFLY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "dagger"), DAGGER);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "bear"), BEAR);
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BEAR, BearEntity.createBearAttributes());
        FabricDefaultAttributeRegistry.register(BUTTERFLY, ButterflyEntity.createButterflyAttributes());

        SpawnRestriction.register(BUTTERFLY, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ButterflyEntity::canSpawn);

    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(FALLING_CONCRETE, FallingConcretePowderEntityRenderer::new);
        EntityRendererRegistry.register(BUTTERFLY, ButterflyEntityRenderer::new);
        EntityRendererRegistry.register(DAGGER, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(BEAR, BearEntityRenderer::new);
    }
}
