package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;

public class ScreenHandlerTypes {
    public static final MenuType<StarforgeScreenHandler> STARFORGE_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starforge_handler"), new MenuType<>(StarforgeScreenHandler::new, FeatureFlagSet.of()));
    public static final MenuType<MoonWelderScreenHandler> MOON_WELDER_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_welder_handler"), new MenuType<>(MoonWelderScreenHandler::new, FeatureFlagSet.of()));
    public static final MenuType<StarBookScreenHandler> STARBOOK_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starbook_handler"), new MenuType<>(StarBookScreenHandler::new, FeatureFlagSet.of()));
    public static final MenuType<StarGeneratorScreenHandler> STARGENERATOR_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargenerator_handler"), new MenuType<>(StarGeneratorScreenHandler::new, FeatureFlagSet.of()));
    public static final MenuType<NightWatcherScreenHandler> NIGHTWATCHER_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "nightwatcher_handler"), new MenuType<>(NightWatcherScreenHandler::new, FeatureFlagSet.of()));
    public static final MenuType<StarCrusherScreenHandler> STARCRUSHER_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starcrusher_handler"), new MenuType<>(StarCrusherScreenHandler::new, FeatureFlagSet.of()));

    public static void init() {}
}
