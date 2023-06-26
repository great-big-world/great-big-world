package com.github.creoii.greatbigworld.main.registry;

import com.github.creoii.greatbigworld.main.GreatBigWorld;
import com.github.creoii.greatbigworld.main.util.Register;
import com.github.creoii.greatbigworld.screen.KilnScreenHandler;
import com.github.creoii.greatbigworld.screen.SawmillScreenHandler;
import com.github.creoii.greatbigworld.screen.client.KilnScreen;
import com.github.creoii.greatbigworld.screen.client.SawmillScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class GBWScreens implements Register {
    public static final ScreenHandlerType<KilnScreenHandler> KILN = new ScreenHandlerType<>(KilnScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static final ScreenHandlerType<SawmillScreenHandler> SAWMILL = new ScreenHandlerType<>(SawmillScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    @Override
    public void register() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(GreatBigWorld.NAMESPACE, "kiln"), KILN);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
    }

    @Override
    public void registerClient() {
        HandledScreens.register(KILN, KilnScreen::new);
        HandledScreens.register(SAWMILL, SawmillScreen::new);
    }
}
