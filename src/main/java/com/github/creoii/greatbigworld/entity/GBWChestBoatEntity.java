package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.entity.GBWBoatEntity.GBWType;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class GBWChestBoatEntity extends ChestBoatEntity {
    private static final TrackedData<Integer> GBW_BOAT_TYPE = DataTracker.registerData(GBWChestBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public GBWChestBoatEntity(EntityType<? extends GBWChestBoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public GBWChestBoatEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(GBW_BOAT_TYPE, GBWType.MAHOGANY.ordinal());
    }

    public Item asItem() {
        return switch (getGBWBoatType()) {
            default -> ItemRegistry.MAHOGANY_CHEST_BOAT;
            case DRIED_BAMBOO -> ItemRegistry.DRIED_BAMBOO_CHEST_BOAT;
            case ASPEN -> ItemRegistry.ASPEN_CHEST_BOAT;
        };
    }

    public void setGBWBoatType(GBWType type) {
        dataTracker.set(GBW_BOAT_TYPE, type.ordinal());
    }

    public GBWType getGBWBoatType() {
        return GBWType.getType(dataTracker.get(GBW_BOAT_TYPE));
    }
}
