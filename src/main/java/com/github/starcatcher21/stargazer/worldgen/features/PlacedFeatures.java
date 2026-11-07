package com.github.starcatcher21.stargazer.worldgen.features;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.worldgen.BiomeTags;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
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
    public static final ResourceKey<PlacedFeature> GEM_ORE = ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "gem_ore")
    );

    public static void init() {
        BiomeModifications.addProperties(
                biomeContext -> biomeContext.hasTag(BiomeTags.MOON),
                (biomeContext, mutable) ->  {
                    mutable.getGenerationProperties().addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            PRISMATIC_ORE
                    );
                    mutable.getGenerationProperties().addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            IRON_ORE
                    );
                    mutable.getGenerationProperties().addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            CRYSTAL_ORE
                    );
                }
        );
        BiomeModifications.addProperties(
                biomeContext -> biomeContext.hasTag(BiomeTags.REDNESS),
                (biomeContext, mutable) ->  {
                    mutable.getGenerationProperties().addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            GEM_ORE
                    );
                }
        );
    }
}
