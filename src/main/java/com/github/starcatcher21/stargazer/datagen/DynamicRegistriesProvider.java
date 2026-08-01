package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.starlib.RegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistriesProvider extends FabricDynamicRegistryProvider {
    public DynamicRegistriesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(RegistryKeys.FALLING_OBJECTS));
    }

    @Override
    public String getName() {
        return "Stargazer Dynamic Registries";
    }
}
