package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;

public class ScreenHandlerTypes {
    public static final DeferredRegister<MenuType<?>> SCREEN_HANDLERS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<StarforgeScreenHandler>> STARFORGE_HANDLER =
            SCREEN_HANDLERS.register("starforge_handler", () -> new MenuType<>(StarforgeScreenHandler::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<MoonWelderScreenHandler>> MOON_WELDER_HANDLER =
            SCREEN_HANDLERS.register("moon_welder_handler", () -> new MenuType<>(MoonWelderScreenHandler::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<StarBookScreenHandler>> STARBOOK_HANDLER =
            SCREEN_HANDLERS.register("starbook_handler", () -> new MenuType<>(StarBookScreenHandler::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<StarGeneratorScreenHandler>> STARGENERATOR_HANDLER =
            SCREEN_HANDLERS.register("stargenerator_handler", () -> new MenuType<>(StarGeneratorScreenHandler::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<NightWatcherScreenHandler>> NIGHTWATCHER_HANDLER =
            SCREEN_HANDLERS.register("nightwatcher_handler", () -> new MenuType<>(NightWatcherScreenHandler::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<StarCrusherScreenHandler>> STARCRUSHER_HANDLER =
            SCREEN_HANDLERS.register("starcrusher_handler", () -> new MenuType<>(StarCrusherScreenHandler::new, FeatureFlagSet.of()));

    public static void init() {
        SCREEN_HANDLERS.register();
    }
}
