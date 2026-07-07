package com.github.starcatcher21.stargazer;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static final KeyMapping.Category STARGAZER = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargazer"));
    public static final KeyMapping DASH_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "Dash",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_X,
                    STARGAZER
            )
    );
    public static void init() {}
}
