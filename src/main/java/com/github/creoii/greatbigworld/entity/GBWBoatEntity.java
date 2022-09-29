package com.github.creoii.greatbigworld.entity;

import com.github.creoii.greatbigworld.main.registry.BlockRegistry;
import com.github.creoii.greatbigworld.main.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class GBWBoatEntity extends BoatEntity {
    private static final TrackedData<Integer> GBW_BOAT_TYPE = DataTracker.registerData(GBWBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public GBWBoatEntity(EntityType<? extends GBWBoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public GBWBoatEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(GBW_BOAT_TYPE, GBWType.MAHOGANY.ordinal());
    }

    public Item asItem() {
        return switch (getGBWBoatType()) {
            default -> ItemRegistry.MAHOGANY_BOAT;
            case DRIED_BAMBOO -> ItemRegistry.DRIED_BAMBOO_BOAT;
            case ASPEN -> ItemRegistry.ASPEN_BOAT;
        };
    }

    public void setGBWBoatType(GBWType type) {
        dataTracker.set(GBW_BOAT_TYPE, type.ordinal());
    }

    public GBWType getGBWBoatType() {
        return GBWType.getType(dataTracker.get(GBW_BOAT_TYPE));
    }

    public enum GBWType {
        MAHOGANY(BlockRegistry.MAHOGANY.planks(), "mahogany"),
        DRIED_BAMBOO(BlockRegistry.DRIED_BAMBOO.planks(), "dried_bamboo"),
        ASPEN(BlockRegistry.ASPEN.planks(), "aspen");

        private final String name;
        private final Block baseBlock;

        GBWType(Block baseBlock, String name) {
            this.name = name;
            this.baseBlock = baseBlock;
        }

        public String getName() {
            return this.name;
        }

        public Block getBaseBlock() {
            return this.baseBlock;
        }

        public String toString() {
            return this.name;
        }

        public static GBWType getType(int type) {
            GBWType[] types = values();
            if (type < 0 || type >= types.length) type = 0;

            return types[type];
        }

        public static GBWType getType(String name) {
            GBWType[] types = values();

            for (GBWType type : types) {
                if (type.getName().equals(name)) return type;
            }

            return types[0];
        }
    }
}
