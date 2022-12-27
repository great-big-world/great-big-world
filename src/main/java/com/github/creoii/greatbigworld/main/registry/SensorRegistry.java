package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.entity.MooseEntity;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SensorRegistry implements Register {
    public static final SensorType<TemptationsSensor> MOOSE_TEMPTATIONS = new SensorType<>(() -> new TemptationsSensor(MooseEntity.getTemptationsItems()));

    @Override
    public void register() {
        Registry.register(Registries.SENSOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "moose_temptations"), MOOSE_TEMPTATIONS);
    }
}
