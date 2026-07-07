package com.github.starcatcher21.stargazer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class CustomWorlds {
    // world
    public static final ResourceKey<Level> COSMIC = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic"));
    public static final ResourceKey<Level> CHESS = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "chess"));
    public static final ResourceKey<Level> RED_ORB = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb"));
    public static final ResourceKey<Level> WANDER = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "wander"));
    public static final ResourceKey<DimensionType> COSMIC_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic"));
    public static final ResourceKey<DimensionType> CHESS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "chess"));
    public static final ResourceKey<DimensionType> RED_ORB_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "red_orb"));
    public static final ResourceKey<DimensionType> WANDER_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "wander"));
}
