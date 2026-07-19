package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.biome.modification.BuiltInResourceKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;

import static com.github.starcatcher21.stargazer.CustomTags.COPPER_DUST;
import static com.github.starcatcher21.stargazer.CustomTags.STARDUST;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(ConventionalItemTags.DUSTS)
                .add(ModItems.IRON_DUST)
                .add(ModItems.COPPER_DUST)
                .add(ModItems.GOLD_DUST)
                .add(ModItems.STARDUST);
        valueLookupBuilder(CustomTags.COPPER_DUST).add(ModItems.COPPER_DUST);
        valueLookupBuilder(CustomTags.IRON_DUST).add(ModItems.IRON_DUST);
        valueLookupBuilder(CustomTags.GOLD_DUST).add(ModItems.GOLD_DUST);
        valueLookupBuilder(ConventionalItemTags.BUCKETS)
                .add(ModItems.DREAM_BUCKET);
        valueLookupBuilder(ConventionalItemTags.CROPS)
                .add(Crops.BROODY, Crops.DRAGON_CARROT, Crops.EYE_BALLS);
        valueLookupBuilder(CustomTags.COSMIC)
                .add(StarBlocks.COSMIC_BLOCK.asItem());
        valueLookupBuilder(CustomTags.CHESS_BRICK)
                .add(ModItems.WHITE_BRICK, ModItems.BLACK_BRICK);
        valueLookupBuilder(net.minecraft.tags.ItemTags.LOGS)
                .add(MoonBlocks.MOON_LOG.asItem())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem())
                .add(MoonBlocks.CURVE_LOG.asItem())
                .add(EyeBloodBlocks.EYE_LOG.asItem())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.asItem())
                .add(StarBlocks.STAR_LOG.asItem())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem())
                .add(Darkness.LOG_OF_DARKNESS.asItem())
                .add(Nebulas.BLUE_NEBULA_LOG.asItem())
                .add(Nebulas.RED_NEBULA_LOG.asItem())
                .add(Nebulas.PURPLE_NEBULA_LOG.asItem())
                .add(Nebulas.YELLOW_NEBULA_LOG.asItem())
                .add(RedOrbBlocks.YERI_LOG.asItem())
                .add(Wander.TRUNN_LOG.asItem())
                .add(MoonBlocks.FULL_MOON_LOG.asItem())
                .add(RedOrbBlocks.SPIRO_LOG.asItem())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem());
        valueLookupBuilder(ItemTags.SAPLINGS)
                .add(MoonBlocks.MOON_SAPLING.asItem())
                .add(StarBlocks.STAR_SAPLING.asItem())
                .add(Darkness.DARKNESS_SAPLING.asItem())
                .add(MoonBlocks.CURVE_SAPLING.asItem())
                .add(MoonBlocks.FULL_MOON_SAPLING.asItem())
                .add(RedOrbBlocks.SPIRO_SAPLING.asItem())
                .add(RedOrbBlocks.YERI_SAPLING.asItem());
        valueLookupBuilder(STARDUST)
                .add(ModItems.STARDUST);
        valueLookupBuilder(CustomTags.STAR)
                .add(Items.NETHER_STAR)
                .add(ModItems.PURPLE_STAR)
                .add(ModItems.RED_STAR)
                .add(ModItems.BLUE_STAR)
                .add(ModItems.YELLOW_STAR);
       valueLookupBuilder(CustomTags.PURPLE_STAR)
                .add(ModItems.PURPLE_STAR);
       valueLookupBuilder(CustomTags.RED_STAR)
                .add(ModItems.RED_STAR);
       valueLookupBuilder(CustomTags.BLUE_STAR)
                .add(ModItems.BLUE_STAR);
       valueLookupBuilder(CustomTags.YELLOW_STAR)
                .add(ModItems.YELLOW_STAR);
        valueLookupBuilder(CustomTags.MOON_LOG)
                .add(MoonBlocks.MOON_LOG.asItem())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem());
        valueLookupBuilder(CustomTags.STAR_LOG)
                .add(StarBlocks.STAR_LOG.asItem())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem());
        valueLookupBuilder(CustomTags.CURVE_LOG)
                .add(MoonBlocks.CURVE_LOG.asItem())
                .add(MoonBlocks.STRIPPED_CURVE_LOG.asItem());
        valueLookupBuilder(CustomTags.DARKNESS_LOG)
                .add(Darkness.LOG_OF_DARKNESS.asItem())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem());
        valueLookupBuilder(CustomTags.EYE_LOG)
                .add(EyeBloodBlocks.EYE_LOG.asItem())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.asItem());
        valueLookupBuilder(net.minecraft.tags.ItemTags.PLANKS)
                .add(MoonBlocks.RED_MOON_PLANKS.asItem())
                .add(MoonBlocks.BLUE_MOON_PLANKS.asItem())
                .add(MoonBlocks.PURPLE_MOON_PLANKS.asItem())
                .add(MoonBlocks.YELLOW_MOON_PLANKS.asItem())
                .add(StarBlocks.STAR_PLANKS.asItem())
                .add(MoonBlocks.CURVE_PLANKS.asItem())
                .add(Darkness.DARKNESS_PLANKS.asItem())
                .add(Nebulas.BLUE_NEBULA_PLANKS.asItem())
                .add(Nebulas.PURPLE_NEBULA_PLANKS.asItem())
                .add(Nebulas.RED_NEBULA_PLANKS.asItem())
                .add(Nebulas.YELLOW_NEBULA_PLANKS.asItem())
                .add(RedOrbBlocks.YERI_PLANKS.asItem())
                .add(MoonBlocks.MOON_PLANKS.asItem());
        valueLookupBuilder(net.minecraft.tags.ItemTags.STONE_TOOL_MATERIALS)
                .add(MoonBlocks.MOON_ROCK.asItem());
        valueLookupBuilder(CustomTags.STAR_FLOWER)
                .add(StarBlocks.STAR_FLOWER.asItem());
        valueLookupBuilder(CustomTags.ECTOPLASM)
                .add(ModItems.ECTOPLASM)
                .add(ModItems.COOLER_ECTOPLASM);
    }
}
