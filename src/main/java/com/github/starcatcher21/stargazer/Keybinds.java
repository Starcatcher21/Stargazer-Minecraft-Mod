package com.github.starcatcher21.stargazer;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Keybinds {
    public static final KeyMapping.Category STARGAZER = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargazer"));
    public static final KeyMapping DASH_KEY = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "Dash",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_X,
                    STARGAZER
            )
    );
    public static void init() {}
}
