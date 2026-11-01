//? if fabric {
package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import com.github.starcatcher21.stargazer.worldgen.features.PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricPlacedFeatures {
    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.MOON),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                PlacedFeatures.PRISMATIC_ORE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.MOON),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                PlacedFeatures.CRYSTAL_ORE
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.MOON),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                PlacedFeatures.IRON_ORE
        );
    }
}
//? }
