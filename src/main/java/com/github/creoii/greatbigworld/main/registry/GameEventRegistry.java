package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.fabricmc.fabric.api.registry.SculkSensorFrequencyRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;

public class GameEventRegistry implements Register {
    public static GameEvent SHED_ANTLERS;

    @Override
    public void register() {
        SHED_ANTLERS = registerGameEvent(new Identifier(GreatBigWorld.NAMESPACE, "shed_antlers"), 16, 4);
    }

    private static GameEvent registerGameEvent(Identifier id, int range) {
        return Registry.register(Registries.GAME_EVENT, id, new GameEvent(id.getPath(), range));
    }

    private static GameEvent registerGameEvent(Identifier id, int range, int frequency) {
        GameEvent gameEvent = registerGameEvent(id, range);
        SculkSensorFrequencyRegistry.register(gameEvent, frequency);
        return gameEvent;
    }
}
