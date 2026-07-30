package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

import static com.github.starcatcher21.stargazer.CustomTags.STARDUST;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(CustomTags.STAR)
                .add(ItemIds.NETHER_STAR)
                .add(ModItems.PURPLE_STAR.builtInRegistryHolder().key())
                .add(ModItems.RED_STAR.builtInRegistryHolder().key())
                .add(ModItems.BLUE_STAR.builtInRegistryHolder().key())
                .add(ModItems.END_STAR.builtInRegistryHolder().key())
                .add(ModItems.YELLOW_STAR.builtInRegistryHolder().key());
        builder(ConventionalItemTags.DUSTS)
                .add(ModItems.IRON_DUST.builtInRegistryHolder().key())
                .add(ModItems.COPPER_DUST.builtInRegistryHolder().key())
                .add(ModItems.GOLD_DUST.builtInRegistryHolder().key())
                .add(ModItems.STARDUST.builtInRegistryHolder().key());
        builder(CustomTags.COPPER_DUST).add(ModItems.COPPER_DUST.builtInRegistryHolder().key());
        builder(CustomTags.IRON_DUST).add(ModItems.IRON_DUST.builtInRegistryHolder().key());
        builder(CustomTags.GOLD_DUST).add(ModItems.GOLD_DUST.builtInRegistryHolder().key());
        builder(ConventionalItemTags.BUCKETS)
                .add(ModItems.DREAM_BUCKET.builtInRegistryHolder().key());
        builder(ConventionalItemTags.CROPS)
                .add(Crops.BROODY.builtInRegistryHolder().key(), Crops.DRAGON_CARROT.builtInRegistryHolder().key(), Crops.EYE_BALLS.builtInRegistryHolder().key());
        builder(CustomTags.COSMIC)
                .add(StarBlocks.COSMIC_BLOCK.asItem().builtInRegistryHolder().key());
        builder(CustomTags.CHESS_BRICK)
                .add(ModItems.WHITE_BRICK.builtInRegistryHolder().key(), ModItems.BLACK_BRICK.builtInRegistryHolder().key());
        builder(net.minecraft.tags.ItemTags.LOGS)
                .add(MoonBlocks.MOON_LOG.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_LOG.asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.EYE_LOG.asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_LOG.asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem().builtInRegistryHolder().key())
                .add(Darkness.LOG_OF_DARKNESS.asItem().builtInRegistryHolder().key())
                .add(Nebulas.BLUE_NEBULA_LOG.asItem().builtInRegistryHolder().key())
                .add(Nebulas.RED_NEBULA_LOG.asItem().builtInRegistryHolder().key())
                .add(Nebulas.PURPLE_NEBULA_LOG.asItem().builtInRegistryHolder().key())
                .add(Nebulas.YELLOW_NEBULA_LOG.asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_LOG.asItem().builtInRegistryHolder().key())
                .add(Wander.TRUNN_LOG.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.FULL_MOON_LOG.asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.SPIRO_LOG.asItem().builtInRegistryHolder().key())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem().builtInRegistryHolder().key());
        builder(ItemTags.SAPLINGS)
                .add(MoonBlocks.MOON_SAPLING.asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_SAPLING.asItem().builtInRegistryHolder().key())
                .add(Darkness.DARKNESS_SAPLING.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_SAPLING.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.FULL_MOON_SAPLING.asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.SPIRO_SAPLING.asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_SAPLING.asItem().builtInRegistryHolder().key());
        builder(STARDUST)
                .add(ModItems.STARDUST.builtInRegistryHolder().key());
       builder(CustomTags.PURPLE_STAR)
                .add(ModItems.PURPLE_STAR.builtInRegistryHolder().key());
       builder(CustomTags.RED_STAR)
                .add(ModItems.RED_STAR.builtInRegistryHolder().key());
       builder(CustomTags.BLUE_STAR)
                .add(ModItems.BLUE_STAR.builtInRegistryHolder().key());
       builder(CustomTags.YELLOW_STAR)
                .add(ModItems.YELLOW_STAR.builtInRegistryHolder().key());
        builder(CustomTags.MOON_LOG)
                .add(MoonBlocks.MOON_LOG.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem().builtInRegistryHolder().key());
        builder(CustomTags.STAR_LOG)
                .add(StarBlocks.STAR_LOG.asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem().builtInRegistryHolder().key());
        builder(CustomTags.CURVE_LOG)
                .add(MoonBlocks.CURVE_LOG.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_CURVE_LOG.asItem().builtInRegistryHolder().key());
        builder(CustomTags.DARKNESS_LOG)
                .add(Darkness.LOG_OF_DARKNESS.asItem().builtInRegistryHolder().key())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem().builtInRegistryHolder().key());
        builder(CustomTags.EYE_LOG)
                .add(EyeBloodBlocks.EYE_LOG.asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.asItem().builtInRegistryHolder().key());
        builder(net.minecraft.tags.ItemTags.PLANKS)
                .add(MoonBlocks.RED_MOON_PLANKS.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.BLUE_MOON_PLANKS.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.PURPLE_MOON_PLANKS.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.YELLOW_MOON_PLANKS.asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_PLANKS.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_PLANKS.asItem().builtInRegistryHolder().key())
                .add(Darkness.DARKNESS_PLANKS.asItem().builtInRegistryHolder().key())
                .add(Nebulas.BLUE_NEBULA_PLANKS.asItem().builtInRegistryHolder().key())
                .add(Nebulas.PURPLE_NEBULA_PLANKS.asItem().builtInRegistryHolder().key())
                .add(Nebulas.RED_NEBULA_PLANKS.asItem().builtInRegistryHolder().key())
                .add(Nebulas.YELLOW_NEBULA_PLANKS.asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_PLANKS.asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.MOON_PLANKS.asItem().builtInRegistryHolder().key());
        builder(net.minecraft.tags.ItemTags.STONE_TOOL_MATERIALS)
                .add(MoonBlocks.MOON_ROCK.asItem().builtInRegistryHolder().key());
        builder(CustomTags.STAR_FLOWER)
                .add(StarBlocks.STAR_FLOWER.asItem().builtInRegistryHolder().key());
        builder(CustomTags.ECTOPLASM)
                .add(ModItems.ECTOPLASM.builtInRegistryHolder().key())
                .add(ModItems.COOLER_ECTOPLASM.builtInRegistryHolder().key());
    }
}
