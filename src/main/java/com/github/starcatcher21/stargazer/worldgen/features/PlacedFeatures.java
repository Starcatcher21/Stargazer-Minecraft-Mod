package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> PRISMATIC_ORE = ResourceKey.create(
            Registries.PLACED_FEATURE,
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "prismatic_ore")
    );

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.MOON), // Or use BiomeSelectors.includeByKey(Biomes.PLAINS)
                GenerationStep.Decoration.UNDERGROUND_ORES, // The Step
                PRISMATIC_ORE // The Registry Key
        );
    }
}
