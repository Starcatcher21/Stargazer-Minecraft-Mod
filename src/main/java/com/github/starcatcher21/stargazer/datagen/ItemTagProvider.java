/*? if fabric || neoforge {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
*///?} else if < 26.2 {
import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
//?}
//? if neoforge && >= 26.2 {
/*import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.tags.TagAppender;
*///?}
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

import static com.github.starcatcher21.stargazer.CustomTags.STARDUST;

public class ItemTagProvider
//? if fabric {
        /*extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }
*///?} else if < 26.2 {
        extends IntrinsicHolderTagsProvider<Item> {

    public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper) {
        super(output, Registries.ITEM, completableFuture, item -> item.builtInRegistryHolder().key(), Stargazer.MOD_ID, existingFileHelper);
    }

    private TagAppender<Item> builder(net.minecraft.tags.TagKey<Item> tag) {
        return tag(tag);
    }
//?} else {
        /*extends TagsProvider<Item> {

    public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, Registries.ITEM, completableFuture, Stargazer.MOD_ID);
    }

    private TagAppender<Item> builder(net.minecraft.tags.TagKey<Item> tag) {
        return tag(tag);
    }
*///?}

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(CustomTags.STAR)
                .add(Items.NETHER_STAR.builtInRegistryHolder().key())
                .add(ModItems.PURPLE_STAR.getKey())
                .add(ModItems.RED_STAR.getKey())
                .add(ModItems.BLUE_STAR.getKey())
                .add(ModItems.END_STAR.getKey())
                .add(ModItems.YELLOW_STAR.getKey());
        builder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dusts")))
                .add(ModItems.IRON_DUST.getKey())
                .add(ModItems.COPPER_DUST.getKey())
                .add(ModItems.GOLD_DUST.getKey())
                .add(ModItems.FULL_MOON_DUST.getKey())
                .add(ModItems.AMETHYST_DUST.getKey())
                .add(ModItems.PURE_AMETHYST_DUST.getKey())
                .add(ModItems.STARDUST.getKey());
        builder(CustomTags.REPAIR_AMETHYST)
                .add(ModItems.PURE_AMETHYST_DUST.getKey());
        builder(CustomTags.COPPER_DUST).add(ModItems.COPPER_DUST.getKey());
        builder(CustomTags.IRON_DUST).add(ModItems.IRON_DUST.getKey());
        builder(CustomTags.GOLD_DUST).add(ModItems.GOLD_DUST.getKey());
        builder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "crops")))
                .add(Crops.BROODY.getKey(), Crops.DRAGON_CARROT.getKey(), Crops.EYE_BALLS.get().builtInRegistryHolder().key());
        builder(CustomTags.COSMIC)
                .add(StarBlocks.COSMIC_BLOCK.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.CHESS_BRICK)
                .add(ModItems.WHITE_BRICK.getKey(), ModItems.BLACK_BRICK.getKey());
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
                .add(ModItems.STARDUST.getKey());
       builder(CustomTags.PURPLE_STAR)
                .add(ModItems.PURPLE_STAR.getKey());
       builder(CustomTags.RED_STAR)
                .add(ModItems.RED_STAR.getKey());
       builder(CustomTags.BLUE_STAR)
                .add(ModItems.BLUE_STAR.getKey());
       builder(CustomTags.YELLOW_STAR)
                .add(ModItems.YELLOW_STAR.getKey());
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
                .add(Wander.TRUNN_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(RedOrbBlocks.SPIRO_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.FUZZY_PLANKS.get().asItem().builtInRegistryHolder().key())
                .add(MoonBlocks.MOON_PLANKS.get().asItem().builtInRegistryHolder().key());
        builder(ItemTags.STONE_TOOL_MATERIALS)
                .add(MoonBlocks.MOON_ROCK.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.STAR_FLOWER)
                .add(StarBlocks.STAR_FLOWER.get().asItem().builtInRegistryHolder().key());
        builder(CustomTags.ECTOPLASM)
                .add(ModItems.ECTOPLASM.getKey())
                .add(ModItems.COOLER_ECTOPLASM.getKey());
        builder(ItemTags.HEAD_ARMOR)
                .add(ModItems.MOON_HELMET.getKey())
                .add(ModItems.AMETHYST_HELMET.getKey())
                .add(ModItems.JESTER_HELMET.getKey());
        builder(ItemTags.CHEST_ARMOR)
                .add(ModItems.MOON_CHESTPLATE.getKey())
                .add(ModItems.AMETHYST_CHESTPLATE.getKey())
                .add(ModItems.JESTER_CHESTPLATE.getKey());
        builder(ItemTags.FOOT_ARMOR)
                .add(ModItems.MOON_BOOTS.getKey())
                .add(ModItems.AMETHYST_BOOTS.getKey())
                .add(ModItems.JESTER_BOOTS.getKey());
        builder(ItemTags.LEG_ARMOR)
                .add(ModItems.MOON_LEGGINS.getKey())
                .add(ModItems.AMETHYST_LEGGINS.getKey())
                .add(ModItems.JESTER_LEGGINS.getKey());
        builder(CustomTags.REPAIR_MOON)
                .add(ModItems.CRYSTAL_MOON.getKey());
        builder(CustomTags.GEM)
                .add(ModItems.RED_GEM.getKey())
                .add(ModItems.GREEN_GEM.getKey())
                .add(ModItems.CRYSTAL_MOON.getKey())
                .add(ModItems.PURPLE_GEM.getKey());
        builder(ItemTags.SWORDS)
                .add(ModItems.GRASS_SWORD.getKey())
                .add(ModItems.PURE_GOLD_SWORD.getKey())
                .add(ModItems.DEMONIC_SWORD.getKey());
    }
}
//? }
