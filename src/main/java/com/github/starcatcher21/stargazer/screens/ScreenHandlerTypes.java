package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlerTypes {
    public static final ScreenHandlerType<StarforgeScreenHandler> STARFORGE_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Stargazer.MOD_ID, "starforge_handler"), new ScreenHandlerType<>(StarforgeScreenHandler::new, FeatureSet.empty()));

    public static void init() {}
}
