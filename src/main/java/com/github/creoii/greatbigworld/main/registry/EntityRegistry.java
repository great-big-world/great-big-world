package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.render.BearEntityRenderer;
import com.github.creoii.greatbigworld.client.render.FallingConcretePowderEntityRenderer;
import com.github.creoii.greatbigworld.entity.BearEntity;
import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    //region Colormatic
    public static final EntityType<FallingConcretePowderEntity> FALLING_CONCRETE = FabricEntityTypeBuilder.<FallingConcretePowderEntity>create(SpawnGroup.MISC, FallingConcretePowderEntity::new).forceTrackedVelocityUpdates(true).dimensions(new EntityDimensions(.98f, .98f, true)).trackRangeChunks(10).trackedUpdateRate(20).build();
    //endregion

    //region Wonders of the Wild
    public static final EntityType<BearEntity> BEAR = FabricEntityTypeBuilder.<BearEntity>create(SpawnGroup.CREATURE, BearEntity::new).dimensions(new EntityDimensions(1.4f, 1.4f, true)).trackRangeChunks(10).build();
    //endregion

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.MOD_ID, "falling_concrete"), FALLING_CONCRETE);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.MOD_ID, "bear"), BEAR);
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BEAR, BearEntity.createBearAttributes());
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(FALLING_CONCRETE, FallingConcretePowderEntityRenderer::new);
        EntityRendererRegistry.register(BEAR, BearEntityRenderer::new);
    }
}
