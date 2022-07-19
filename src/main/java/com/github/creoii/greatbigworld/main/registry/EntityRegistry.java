package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.render.*;
import com.github.creoii.greatbigworld.entity.*;
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
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class EntityRegistry {
    //region Change the World
    public static final EntityType<HyenaEntity> HYENA = FabricEntityTypeBuilder.<HyenaEntity>create(SpawnGroup.CREATURE, HyenaEntity::new).dimensions(new EntityDimensions(.6f, .85f, true)).trackRangeChunks(10).build();
    public static final EntityType<ZebraEntity> ZEBRA = FabricEntityTypeBuilder.<ZebraEntity>create(SpawnGroup.CREATURE, ZebraEntity::new).dimensions(new EntityDimensions(1.4f, 1.6f, true)).trackRangeChunks(10).build();
    public static final EntityType<DeerEntity> DEER = FabricEntityTypeBuilder.<DeerEntity>create(SpawnGroup.CREATURE, DeerEntity::new).dimensions(new EntityDimensions(1.2f, 1.4f, true)).trackRangeChunks(10).disableSummon().build();
    //endregion

    //region Colormatic
    public static final EntityType<FallingConcretePowderEntity> FALLING_CONCRETE = FabricEntityTypeBuilder.<FallingConcretePowderEntity>create(SpawnGroup.MISC, FallingConcretePowderEntity::new).forceTrackedVelocityUpdates(true).dimensions(new EntityDimensions(.98f, .98f, true)).trackRangeChunks(10).trackedUpdateRate(20).build();
    public static final EntityType<ButterflyEntity> BUTTERFLY = FabricEntityTypeBuilder.<ButterflyEntity>create(SpawnGroup.AMBIENT, ButterflyEntity::new).dimensions(new EntityDimensions(.4f, .2f, true)).trackRangeChunks(10).build();
    //endregion

    //region Cloak And Dagger
    public static final EntityType<DaggerEntity> DAGGER = FabricEntityTypeBuilder.<DaggerEntity>create(SpawnGroup.MISC, DaggerEntity::new).dimensions(new EntityDimensions(.25f, .25f, true)).trackRangeChunks(4).trackedUpdateRate(10).build();
    //endregion

    //region Venture n' Voyage
    public static final EntityType<WrappedEntity> WRAPPED = FabricEntityTypeBuilder.<WrappedEntity>create(SpawnGroup.MONSTER, WrappedEntity::new).dimensions(new EntityDimensions(.65f, 1f, true)).trackRangeChunks(10).build();
    //endregion

    //region Wonders of the Wild
    //public static final EntityType<BearEntity> BEAR = FabricEntityTypeBuilder.<BearEntity>create(SpawnGroup.CREATURE, BearEntity::new).dimensions(new EntityDimensions(1.4f, 1.4f, true)).trackRangeChunks(10).build();
    //endregion

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "hyena"), HYENA);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "zebra"), ZEBRA);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "deer"), DEER);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "falling_concrete"), FALLING_CONCRETE);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "butterfly"), BUTTERFLY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "dagger"), DAGGER);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "wrapped"), WRAPPED);
        //Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "bear"), BEAR);
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(HYENA, HyenaEntity.createHyenaAttributes());
        FabricDefaultAttributeRegistry.register(ZEBRA, ZebraEntity.createZebraAttributes());
        FabricDefaultAttributeRegistry.register(DEER, DeerEntity.createDeerAttributes());
        FabricDefaultAttributeRegistry.register(BUTTERFLY, ButterflyEntity.createButterflyAttributes());
        FabricDefaultAttributeRegistry.register(WRAPPED, WrappedEntity.createWrappedAttributes());
        //FabricDefaultAttributeRegistry.register(BEAR, BearEntity.createBearAttributes());

        SpawnRestriction.register(HYENA, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> true);
        SpawnRestriction.register(ZEBRA, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> true);
        SpawnRestriction.register(DEER, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> true);
        SpawnRestriction.register(WRAPPED, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
        SpawnRestriction.register(BUTTERFLY, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ButterflyEntity::canSpawn);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(HYENA, HyenaEntityRenderer::new);
        EntityRendererRegistry.register(ZEBRA, ZebraEntityRenderer::new);
        EntityRendererRegistry.register(WRAPPED, WrappedEntityRenderer::new);
        EntityRendererRegistry.register(FALLING_CONCRETE, FallingConcretePowderEntityRenderer::new);
        EntityRendererRegistry.register(BUTTERFLY, ButterflyEntityRenderer::new);
        EntityRendererRegistry.register(DAGGER, FlyingItemEntityRenderer::new);
        //EntityRendererRegistry.register(BEAR, BearEntityRenderer::new);
    }
}
