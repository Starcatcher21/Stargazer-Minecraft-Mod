package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> PRISMATIC_ORE = ResourceKey.create(
            Registries.PLACED_FEATURE,
          ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "prismatic_ore")
    );
    public static final ResourceKey<PlacedFeature> CRYSTAL_ORE = ResourceKey.create(
            Registries.PLACED_FEATURE,
          ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "crystal_ore")
    );
    public static final ResourceKey<PlacedFeature> IRON_ORE = ResourceKey.create(
            Registries.PLACED_FEATURE,
          ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "iron_ore")
    );

    public static void init() {
        // Common initialization hook if needed
    }
}