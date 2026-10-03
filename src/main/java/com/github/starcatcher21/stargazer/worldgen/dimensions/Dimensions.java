package com.github.starcatcher21.stargazer.worldgen.dimensions;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class Dimensions {
    public static final ResourceKey<Level> REG_COSMIC_WORLD = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID,"cosmic"));
    public static final ResourceKey<Level> REG_CHESS_WORLD = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID,"chess"));
    public static final ResourceKey<Level> REG_RED_ORB_WORLD = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID,"red_orb"));
}
