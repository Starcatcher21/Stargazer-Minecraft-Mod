/*? if fabric || neoforge {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
*///?} else if < 26.2 {
import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
//?}
//? if neoforge && >= 26.2 {
/*import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
*///?}
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;

public class FluidTagProvider
//? if fabric {
        /*extends FabricTagsProvider.FluidTagsProvider {
    public FluidTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
*///?} else if < 26.2 {
        extends IntrinsicHolderTagsProvider<Fluid> {
    public FluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, ExistingFileHelper existingFileHelper) {
        super(output, Registries.FLUID, registriesFuture, fluid -> fluid.builtInRegistryHolder().key(), Stargazer.MOD_ID, existingFileHelper);
    }
//?} else {
        /*extends TagsProvider<Fluid> {
    public FluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.FLUID, registriesFuture, Stargazer.MOD_ID);
    }
*///?}

    public static final TagKey<Fluid> DREAM = of("dream");

    private static TagKey<Fluid> of(String id) {
        return TagKey.create(Registries.FLUID,ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, id));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
    }
}
//? }
