//? if neoforge {
package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.handled.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Stargazer.MOD_ID)
public final class ClientModEvents {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ScreenHandlerTypes.STARFORGE_HANDLER.get(), StarforgeHandled::new);
        event.register(ScreenHandlerTypes.MOON_WELDER_HANDLER.get(), MoonWelderHandled::new);
        event.register(ScreenHandlerTypes.NIGHTWATCHER_HANDLER.get(), NightWatcherHandled::new);
        event.register(ScreenHandlerTypes.STARGENERATOR_HANDLER.get(), StargeneratorHandled::new);
        event.register(ScreenHandlerTypes.STARBOOK_HANDLER.get(), StarBookHandled::new);
        event.register(ScreenHandlerTypes.STARCRUSHER_HANDLER.get(), StarCrusherHandled::new);
    }
}
//?}
