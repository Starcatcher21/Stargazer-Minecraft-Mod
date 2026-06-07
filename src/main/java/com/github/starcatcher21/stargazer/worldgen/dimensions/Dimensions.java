package com.github.starcatcher21.stargazer.worldgen.dimensions;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class Dimensions {
    public static final RegistryKey<World> REG_COSMIC_WORLD = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID,"cosmic"));
    public static final RegistryKey<World> REG_CHESS_WORLD = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID,"chess"));
    public static final RegistryKey<World> REG_RED_ORB_WORLD = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID,"red_orb"));
}
