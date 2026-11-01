/*? if fabric {*/
package com.github.starcatcher21.stargazer.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class WorldGenerator extends FabricDynamicRegistryProvider {
    public WorldGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.BIOME));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.CONFIGURED_CARVER));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.DIMENSION_TYPE));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.NOISE));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.DENSITY_FUNCTION));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.NOISE_SETTINGS));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.STRUCTURE));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.STRUCTURE_SET));
        entries.addAll(wrapperLookup.lookupOrThrow(Registries.TEMPLATE_POOL));
    }

    @Override
    public String getName() {
        return "World Gen";
    }
}
//? }
