package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class ModPOITagProvider extends FabricTagProvider.FabricValueLookupTagProvider {
    public ModPOITagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registriesFuture, block -> ((Block) block).builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(MoonBlocks.STAR_FORGE);
    }
}
