package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PointOfInterestTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModPOITagProvider extends FabricTagProvider.FabricValueLookupTagProvider {
    public ModPOITagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registriesFuture, block -> ((Block) block).getRegistryEntry().registryKey());
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
                .add(MoonBlocks.STAR_FORGE);
    }
}
