package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.entity.brain.MooseBrain;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SensorRegistry implements Register {
    public static final SensorType<TemptationsSensor> MOOSE_TEMPTATIONS = new SensorType<TemptationsSensor>(() -> {
        return new TemptationsSensor(MooseBrain.getTemptItems());
    });

    @Override
    public void register() {
        Registry.register(Registry.SENSOR_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "moose_temptations"), MOOSE_TEMPTATIONS);
    }
}
