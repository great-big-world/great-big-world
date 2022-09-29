package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class GBWModelLayers {
    public static EntityModelLayer createBoat(GBWBoatEntity.GBWType type) {
        return new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "boat/" + type.getName()), "main");
    }

    public static EntityModelLayer createChestBoat(GBWBoatEntity.GBWType type) {
        return new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, "chest_boat/" + type.getName()), "main");
    }
}
