package com.github.starcatcher21.stargazer;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomWorlds {
    // world
    public static final RegistryKey<World> COSMIC = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID, "cosmic"));
}
