package com.github.starcatcher21.stargazer;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    //? if >= 26.2 {
    /*public static final KeyMapping DASH_KEY = new KeyMapping(
            "key.stargazer.dash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            new KeyMapping.Category(net.minecraft.resources.Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargazer"))
    );
    *///? } else {
    public static final KeyMapping DASH_KEY = new KeyMapping(
            "key.stargazer.dash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            "key.categories.stargazer"
    );
    //?}

    public static void init() {
        // Common initialization logic if needed
    }
}