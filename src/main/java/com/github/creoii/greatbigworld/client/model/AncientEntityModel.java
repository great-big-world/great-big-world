package com.github.creoii.greatbigworld.client.model;

import com.github.creoii.greatbigworld.entity.AncientEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AbstractZombieModel;

public class AncientEntityModel<T extends AncientEntity> extends AbstractZombieModel<T> {
    public AncientEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public boolean isAttacking(AncientEntity entity) {
        return entity.isAttacking();
    }
}
