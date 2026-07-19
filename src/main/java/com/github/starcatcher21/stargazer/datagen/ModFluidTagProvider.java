package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagsProvider.FluidTagsProvider {
    public ModFluidTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Fluid> DREAM = of("dream");

    private static TagKey<Fluid> of(String id) {
        return TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
    }
}
