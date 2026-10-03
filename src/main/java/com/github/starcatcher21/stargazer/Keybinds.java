package com.github.starcatcher21.stargazer;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    // Define the category string (or use translation key directly depending on your MC version)
    public static final KeyMapping DASH_KEY = new KeyMapping(
            "key.stargazer.dash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            new KeyMapping.Category(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargazer"))
    );

    public static void init() {
        // Common initialization logic if needed
    }
}