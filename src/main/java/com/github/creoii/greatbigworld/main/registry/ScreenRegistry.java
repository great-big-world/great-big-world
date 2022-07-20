package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.block.screen.SawmillScreenHandler;
import com.github.creoii.greatbigworld.client.screen.SawmillScreen;
import com.github.creoii.greatbigworld.main.GreatBigWorld;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ScreenRegistry {
    public static final ScreenHandlerType<SawmillScreenHandler> SAWMILL = new ScreenHandlerType<SawmillScreenHandler>(SawmillScreenHandler::new);

    public static void register() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        HandledScreens.register(SAWMILL, SawmillScreen::new);
    }
}
