package com.github.starcatcher21.stargazer.fabric.datagen;

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
                .add(ModItems.PURPLE_STAR.get().builtInRegistryHolder().key())
                .add(ModItems.RED_STAR.get().builtInRegistryHolder().key())
                .add(ModItems.BLUE_STAR.get().builtInRegistryHolder().key())
                .add(ModItems.END_STAR.get().builtInRegistryHolder().key())
                .add(ModItems.YELLOW_STAR.get().builtInRegistryHolder().key());
        builder(ConventionalItemTags.DUSTS)
                .add(ModItems.IRON_DUST.get().builtInRegistryHolder().key())
                .add(ModItems.COPPER_DUST.get().builtInRegistryHolder().key())
                .add(ModItems.GOLD_DUST.get().builtInRegistryHolder().key())
                .add(ModItems.FULL_MOON_DUST.get().builtInRegistryHolder().key())
                .add(ModItems.STARDUST.get().builtInRegistryHolder().key());
        builder(CustomTags.COPPER_DUST).add(ModItems.COPPER_DUST.get().builtInRegistryHolder().key());
        builder(CustomTags.IRON_DUST).add(ModItems.IRON_DUST.get().builtInRegistryHolder().key());
        builder(CustomTags.GOLD_DUST).add(ModItems.GOLD_DUST.get().builtInRegistryHolder().key());
        builder(ConventionalItemTags.CROPS)
                .add(Crops.BROODY.get().builtInRegistryHolder().key(), Crops.DRAGON_CARROT.get().builtInRegistryHolder().key(), Crops.EYE_BALLS.get().builtInRegistryHolder().key());
        builder(CustomTags.COSMIC)
                .add(StarBlocks.COSMIC_BLOCK.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.CHESS_BRICK)
                .add(ModItems.WHITE_BRICK.get().builtInRegistryHolder().key(), ModItems.BLACK_BRICK.get().builtInRegistryHolder().key());
        builder(ItemTags.LOGS)
                .add(MoonBlocks.MOON_LOG.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_MOON_LOG.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_LOG.get().asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.EYE_LOG.get().asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.get().asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_LOG.get().asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STRIPPED_STAR_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Darkness.LOG_OF_DARKNESS.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.BLUE_NEBULA_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.RED_NEBULA_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.PURPLE_NEBULA_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.YELLOW_NEBULA_LOG.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Wander.TRUNN_LOG.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.FULL_MOON_LOG.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.SPIRO_LOG.get().asItem().builtInRegistryHolder().key())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.get().asItem().builtInRegistryHolder().key());
        builder(ItemTags.SAPLINGS)
                .add(MoonBlocks.MOON_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(Wander.UMBRELLA_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(Darkness.DARKNESS_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.FULL_MOON_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.SPIRO_SAPLING.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_SAPLING.get().asItem().builtInRegistryHolder().key());
        builder(STARDUST)
                .add(ModItems.STARDUST.get().builtInRegistryHolder().key());
       builder(CustomTags.PURPLE_STAR)
                .add(ModItems.PURPLE_STAR.get().builtInRegistryHolder().key());
       builder(CustomTags.RED_STAR)
                .add(ModItems.RED_STAR.get().builtInRegistryHolder().key());
       builder(CustomTags.BLUE_STAR)
                .add(ModItems.BLUE_STAR.get().builtInRegistryHolder().key());
       builder(CustomTags.YELLOW_STAR)
                .add(ModItems.YELLOW_STAR.get().builtInRegistryHolder().key());
        builder(CustomTags.MOON_LOG)
                .add(MoonBlocks.MOON_LOG.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_MOON_LOG.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.STAR_LOG)
                .add(StarBlocks.STAR_LOG.get().asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STRIPPED_STAR_LOG.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.CURVE_LOG)
                .add(MoonBlocks.CURVE_LOG.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.STRIPPED_CURVE_LOG.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.DARKNESS_LOG)
                .add(Darkness.LOG_OF_DARKNESS.get().asItem().builtInRegistryHolder().key())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.EYE_LOG)
                .add(EyeBloodBlocks.EYE_LOG.get().asItem().builtInRegistryHolder().key())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.get().asItem().builtInRegistryHolder().key());
        builder(ItemTags.PLANKS)
                .add(MoonBlocks.RED_MOON_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.BLUE_MOON_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.PURPLE_MOON_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.YELLOW_MOON_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(StarBlocks.STAR_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.CURVE_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(Darkness.DARKNESS_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.BLUE_NEBULA_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.PURPLE_NEBULA_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.RED_NEBULA_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(Nebulas.YELLOW_NEBULA_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.YERI_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.MOON_PLANKS.get().asItem().builtInRegistryHolder().key());
        builder(ItemTags.STONE_TOOL_MATERIALS)
                .add(MoonBlocks.MOON_ROCK.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.STAR_FLOWER)
                .add(StarBlocks.STAR_FLOWER.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.ECTOPLASM)
                .add(ModItems.ECTOPLASM.get().builtInRegistryHolder().key())
                .add(ModItems.COOLER_ECTOPLASM.get().builtInRegistryHolder().key());
        builder(ItemTags.HEAD_ARMOR)
                .add(ModItems.MOON_HELMET.get().builtInRegistryHolder().key())
                .add(ModItems.JESTER_HELMET.get().builtInRegistryHolder().key());
        builder(ItemTags.CHEST_ARMOR)
                .add(ModItems.MOON_CHESTPLATE.get().builtInRegistryHolder().key())
                .add(ModItems.JESTER_CHESTPLATE.get().builtInRegistryHolder().key());
        builder(ItemTags.FOOT_ARMOR)
                .add(ModItems.MOON_BOOTS.get().builtInRegistryHolder().key())
                .add(ModItems.JESTER_BOOTS.get().builtInRegistryHolder().key());
        builder(ItemTags.LEG_ARMOR)
                .add(ModItems.MOON_LEGGINS.get().builtInRegistryHolder().key())
                .add(ModItems.JESTER_LEGGINS.get().builtInRegistryHolder().key());
        builder(CustomTags.REPAIR_MOON)
                .add(ModItems.CRYSTAL_MOON.get().builtInRegistryHolder().key());
    }
}
