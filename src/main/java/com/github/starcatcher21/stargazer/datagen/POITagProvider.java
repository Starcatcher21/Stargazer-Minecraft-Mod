/*? if fabric || neoforge {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
//?} else if < 26.2 {
/*import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
*///?}
//? if neoforge && >= 26.2 {
/*import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.tags.TagAppender;
*///?}
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.tags.PoiTypeTags;

import java.util.concurrent.CompletableFuture;

public class POITagProvider
//? if fabric {
        extends FabricTagsProvider {

    public POITagProvider(FabricPackOutput output, CompletableFuture registryLookupFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }
//?} else if < 26.2 {
        /*extends IntrinsicHolderTagsProvider<PoiType> {

    public POITagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, ExistingFileHelper existingFileHelper) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture, poi -> BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(poi).orElseThrow(), Stargazer.MOD_ID, existingFileHelper);
    }

    private TagAppender<PoiType> builder(net.minecraft.tags.TagKey<PoiType> tag) {
        return tag(tag);
    }
*///?} else {
        /*extends TagsProvider<PoiType> {
    public POITagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture, Stargazer.MOD_ID);
    }

    private TagAppender<PoiType> builder(net.minecraft.tags.TagKey<PoiType> tag) {
        return tag(tag);
    }
*///?}

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(PointOfIntrests.STAR_FORGE_KEY);
    }
}
//? }
