package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.Darkness;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.item.ModItems;
import dev.architectury.platform.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static com.github.starcatcher21.stargazer.CustomTags.STARDUST;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(CustomTags.COSMIC)
                .add(StarBlocks.COSMIC_BLOCK.asItem());
        getOrCreateTagBuilder(net.minecraft.registry.tag.ItemTags.LOGS)
                .add(MoonBlocks.MOON_LOG.asItem())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem())
                .add(MoonBlocks.CURVE_LOG.asItem())
                .add(EyeBloodBlocks.EYE_LOG.asItem())
                .add(EyeBloodBlocks.STRIPPED_EYE_LOG.asItem())
                .add(StarBlocks.STAR_LOG.asItem())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem())
                .add(Darkness.LOG_OF_DARKNESS.asItem())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem());
        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(MoonBlocks.MOON_SAPLING.asItem())
                .add(StarBlocks.STAR_SAPLING.asItem())
                .add(MoonBlocks.CURVE_SAPLING.asItem());
        getOrCreateTagBuilder(STARDUST)
                .add(ModItems.STARDUST);
        getOrCreateTagBuilder(CustomTags.ICECREAM)
                .add(ModItems.GRAVICE);
        getOrCreateTagBuilder(CustomTags.STAR)
                .add(Items.NETHER_STAR)
                .add(ModItems.PURPLE_STAR)
                .add(ModItems.RED_STAR)
                .add(ModItems.BLUE_STAR)
                .add(ModItems.YELLOW_STAR);
       getOrCreateTagBuilder(CustomTags.PURPLE_STAR)
                .add(ModItems.PURPLE_STAR);
       getOrCreateTagBuilder(CustomTags.RED_STAR)
                .add(ModItems.RED_STAR);
       getOrCreateTagBuilder(CustomTags.BLUE_STAR)
                .add(ModItems.BLUE_STAR);
       getOrCreateTagBuilder(CustomTags.YELLOW_STAR)
                .add(ModItems.YELLOW_STAR);
        getOrCreateTagBuilder(CustomTags.MOON_LOG)
                .add(MoonBlocks.MOON_LOG.asItem())
                .add(MoonBlocks.STRIPPED_MOON_LOG.asItem());
        getOrCreateTagBuilder(CustomTags.STAR_LOG)
                .add(StarBlocks.STAR_LOG.asItem())
                .add(StarBlocks.STRIPPED_STAR_LOG.asItem());
        getOrCreateTagBuilder(CustomTags.CURVE_LOG)
                .add(MoonBlocks.CURVE_LOG.asItem())
                .add(MoonBlocks.STRIPPED_CURVE_LOG.asItem());
        getOrCreateTagBuilder(CustomTags.DARKNESS_LOG)
                .add(Darkness.LOG_OF_DARKNESS.asItem())
                .add(Darkness.STRIPPED_LOG_OF_DARKNESS.asItem());
        getOrCreateTagBuilder(net.minecraft.registry.tag.ItemTags.PLANKS)
                .add(MoonBlocks.RED_MOON_PLANKS.asItem())
                .add(MoonBlocks.BLUE_MOON_PLANKS.asItem())
                .add(MoonBlocks.PURPLE_MOON_PLANKS.asItem())
                .add(MoonBlocks.YELLOW_MOON_PLANKS.asItem())
                .add(StarBlocks.STAR_PLANKS.asItem())
                .add(MoonBlocks.CURVE_PLANKS.asItem())
                .add(MoonBlocks.MOON_PLANKS.asItem());
        getOrCreateTagBuilder(net.minecraft.registry.tag.ItemTags.STONE_TOOL_MATERIALS)
                .add(MoonBlocks.MOON_ROCK.asItem());
        getOrCreateTagBuilder(CustomTags.STAR_FLOWER)
                .add(StarBlocks.STAR_FLOWER.asItem());
        getOrCreateTagBuilder(CustomTags.ECTOPLASM)
                .add(ModItems.ECTOPLASM)
                .add(ModItems.COOLER_ECTOPLASM);
    }
}
