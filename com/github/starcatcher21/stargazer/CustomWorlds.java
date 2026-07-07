package com.github.starcatcher21.stargazer;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class CustomWorlds {
    // world
    public static final RegistryKey<World> COSMIC = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID, "cosmic"));
    public static final RegistryKey<World> CHESS = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID, "chess"));
    public static final RegistryKey<World> RED_ORB = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID, "red_orb"));
    public static final RegistryKey<World> WANDER = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Stargazer.MOD_ID, "wander"));
    public static final RegistryKey<DimensionType> COSMIC_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(Stargazer.MOD_ID, "cosmic"));
    public static final RegistryKey<DimensionType> CHESS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(Stargazer.MOD_ID, "chess"));
    public static final RegistryKey<DimensionType> RED_ORB_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(Stargazer.MOD_ID, "red_orb"));
    public static final RegistryKey<DimensionType> WANDER_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(Stargazer.MOD_ID, "wander"));
}
