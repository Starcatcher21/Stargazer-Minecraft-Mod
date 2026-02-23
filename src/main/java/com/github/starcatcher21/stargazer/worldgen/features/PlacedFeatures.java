package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class PlacedFeatures {
    public static final RegistryKey<PlacedFeature> PRISMATIC_ORE = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(Stargazer.MOD_ID, "prismatic_ore")
    );

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.all(), // Or use BiomeSelectors.includeByKey(Biomes.PLAINS)
                GenerationStep.Feature.UNDERGROUND_ORES, // The Step
                PRISMATIC_ORE // The Registry Key
        );
    }
}
