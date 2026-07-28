package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.RegistryKeys;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class ModPOITagProvider extends FabricTagsProvider {

    public ModPOITagProvider(FabricPackOutput output, CompletableFuture registryLookupFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(MoonBlocks.STAR_FORGE.properties().blockId());
    }
}
