package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.event.GameEvent;

public class GBWGameEvents implements Register {
    public static GameEvent SHED_ANTLERS;

    @Override
    public void register() {
        SHED_ANTLERS = registerGameEvent(new Identifier(GreatBigWorld.NAMESPACE, "shed_antlers"), 16);
    }

    private static GameEvent registerGameEvent(Identifier id, int range) {
        return Registry.register(Registry.GAME_EVENT, id, new GameEvent(id.getPath(), range));
    }
}
