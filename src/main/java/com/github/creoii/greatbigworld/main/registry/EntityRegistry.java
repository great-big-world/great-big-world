package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.client.render.GBWBoatEntityRenderer;
import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.entity.GBWChestBoatEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry implements Register {
    public static final EntityType<GBWBoatEntity> BOAT = FabricEntityTypeBuilder.<GBWBoatEntity>create(SpawnGroup.MISC, GBWBoatEntity::new).dimensions(new EntityDimensions(1.375f, .5625f, true)).trackRangeChunks(10).build();
    public static final EntityType<GBWChestBoatEntity> CHEST_BOAT = FabricEntityTypeBuilder.<GBWChestBoatEntity>create(SpawnGroup.MISC, GBWChestBoatEntity::new).dimensions(new EntityDimensions(1.375f, .5625f, true)).trackRangeChunks(10).build();

    @Override
    public void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "boat"), BOAT);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "chest_boat"), CHEST_BOAT);
    }

    @Override
    public void registerClient() {
        EntityRendererRegistry.register(BOAT, context -> new GBWBoatEntityRenderer(context, false));
    }
}
