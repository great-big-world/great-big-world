package com.github.creoii.greatbigworld.client;

import com.github.creoii.greatbigworld.entity.GBWBoatEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class GBWModelLayers {
    public static EntityModelLayer createBoat(GBWBoatEntity.GBWType type, boolean chest) {
        return new EntityModelLayer(new Identifier(GreatBigWorld.NAMESPACE, chest ? "chest_" : "" + "boat/" + type.getName()), "main");
    }
}
