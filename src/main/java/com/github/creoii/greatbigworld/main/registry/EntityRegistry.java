package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.render.FallingConcretePowderRenderer;
import com.github.creoii.greatbigworld.entity.FallingConcretePowderEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
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

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.MOD_ID, "falling_concrete"), FALLING_CONCRETE);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(FALLING_CONCRETE, FallingConcretePowderRenderer::new);
    }
}
