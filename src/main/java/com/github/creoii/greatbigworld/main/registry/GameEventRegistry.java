package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;

public class GameEventRegistry implements Register {
    public static GameEvent SHED_ANTLERS;
    public static GameEvent NAUTILUS_OXIDIZE;

    @Override
    public void register() {
        SHED_ANTLERS = registerGameEvent(new Identifier(GreatBigWorld.NAMESPACE, "shed_antlers"), 16);
        NAUTILUS_OXIDIZE = registerGameEvent(new Identifier(GreatBigWorld.NAMESPACE, "nautilus_oxidize"), 16);
    }

    private static GameEvent registerGameEvent(Identifier id, int range) {
        return Registry.register(Registries.GAME_EVENT, id, new GameEvent(id.getPath(), range));
    }
}
