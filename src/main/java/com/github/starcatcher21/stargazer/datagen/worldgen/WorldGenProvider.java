package com.github.starcatcher21.stargazer.datagen.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
*///? } else if neoforge {
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
//? }

public class WorldGenProvider extends
        //? if neoforge {
        DatapackBuiltinEntriesProvider
        //? } else {
        /*FabricDynamicRegistryProvider
        *///? }
{
    //? if neoforge {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, BiomesProvider::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeaturesProvider::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeaturesProvider::bootstrap)
            .add(Registries.DIMENSION_TYPE, DimensionTypesProvider::bootstrap);

    public static RegistrySetBuilder getBuilder() {
        return BUILDER;
    }

    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Stargazer.MOD_ID));
    }
    //? } else {
    /*public WorldGenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        ConfiguredFeaturesProvider.init();
        PlacedFeaturesProvider.init();
        BiomesProvider.init();
        DimensionTypesProvider.init();
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
    }
    *///? }

    @Override
    public String getName() {
        return "World Gen Provider: " + Stargazer.MOD_ID;
    }
}
