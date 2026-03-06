package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.block.register.Darkness;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                createShaped(RecipeCategory.FOOD, ModItems.GRAVICE, 1)
                        .pattern("#")
                        .pattern("#")
                        .pattern("I")
                        .input('#', Items.GRAVEL)
                        .input('I', Items.STICK)
                        .group("ice")
                        .criterion(hasItem(Items.GRAVEL), conditionsFromItem(ModItems.GRAVICE))
                        .offerTo(exporter);
                // Moon Tree
                createShapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS, 4)
                        .input(CustomTags.MOON_LOG)
                        .group("planks")
                        .criterion("has_log", this.conditionsFromTag(CustomTags.MOON_LOG))
                        .offerTo(this.exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.RED_MOON_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.RED_MOON_PLANKS))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.BLUE_MOON_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .offerTo(exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.YELLOW_MOON_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.MOON_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.RED_MOON_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.RED_MOON_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.BLUE_MOON_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.MOON_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.RED_MOON_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.RED_MOON_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.BLUE_MOON_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.MOON_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.RED_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.MOON_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.PURPLE_MOON_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.PURPLE_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.RED_MOON_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.RED_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.BLUE_MOON_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.BLUE_MOON_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.YELLOW_MOON_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.YELLOW_MOON_PLANKS))
                        .offerTo(exporter);
                createDoorRecipe(MoonBlocks.MOON_PLANKS_DOOR, Ingredient.ofItem(MoonBlocks.MOON_PLANKS))
                        .group("wooden_door")
                        .criterion("wood", conditionsFromItem(MoonBlocks.MOON_PLANKS))
                        .offerTo(exporter);
                // Star Tree
                createShapeless(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS, 4)
                        .input(CustomTags.STAR_LOG)
                        .group("planks")
                        .criterion("has_log", this.conditionsFromTag(CustomTags.STAR_LOG))
                        .offerTo(this.exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS_SLAB, Ingredient.ofItem(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(StarBlocks.STAR_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(StarBlocks.STAR_PLANKS_STAIRS, Ingredient.ofItem(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(StarBlocks.STAR_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(StarBlocks.STAR_PLANKS_BUTTON, Ingredient.ofItem(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(StarBlocks.STAR_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(StarBlocks.STAR_PLANKS_FENCE, Ingredient.ofItem(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(StarBlocks.STAR_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(StarBlocks.STAR_PLANKS_FENCE_GATE, Ingredient.ofItem(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(StarBlocks.STAR_PLANKS))
                        .offerTo(exporter);
                // Curve Tree
                createShapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS, 4)
                        .input(CustomTags.CURVE_LOG)
                        .group("planks")
                        .criterion("has_log", this.conditionsFromTag(CustomTags.CURVE_LOG))
                        .offerTo(this.exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS_SLAB, Ingredient.ofItem(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.CURVE_PLANKS_STAIRS, Ingredient.ofItem(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(MoonBlocks.CURVE_PLANKS_BUTTON, Ingredient.ofItem(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(MoonBlocks.CURVE_PLANKS_FENCE, Ingredient.ofItem(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(MoonBlocks.CURVE_PLANKS_FENCE_GATE, Ingredient.ofItem(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);

                // Darkness
                createShapeless(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS, 4)
                        .input(CustomTags.DARKNESS_LOG)
                        .group("planks")
                        .criterion("has_log", this.conditionsFromTag(CustomTags.DARKNESS_LOG))
                        .offerTo(this.exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS_SLAB, Ingredient.ofItem(Darkness.DARKNESS_PLANKS))
                        .group("wooden_slab")
                        .criterion("wood", conditionsFromItem(Darkness.DARKNESS_PLANKS))
                        .offerTo(exporter);
                createStairsRecipe(Darkness.DARKNESS_PLANKS_STAIRS, Ingredient.ofItem(Darkness.DARKNESS_PLANKS))
                        .group("wooden_stairs")
                        .criterion("wood", conditionsFromItem(Darkness.DARKNESS_PLANKS))
                        .offerTo(exporter);
                createButtonRecipe(Darkness.DARKNESS_PLANKS_BUTTON, Ingredient.ofItem(Darkness.DARKNESS_PLANKS))
                        .group("wooden_button")
                        .criterion("wood", conditionsFromItem(Darkness.DARKNESS_PLANKS))
                        .offerTo(exporter);
                createFenceRecipe(Darkness.DARKNESS_PLANKS_FENCE, Ingredient.ofItem(Darkness.DARKNESS_PLANKS))
                        .group("wooden_fence")
                        .criterion("wood", conditionsFromItem(MoonBlocks.CURVE_PLANKS))
                        .offerTo(exporter);
                createFenceGateRecipe(Darkness.DARKNESS_PLANKS_FENCE_GATE, Ingredient.ofItem(Darkness.DARKNESS_PLANKS))
                        .group("wooden_fence_gate")
                        .criterion("wood", conditionsFromItem(Darkness.DARKNESS_PLANKS))
                        .offerTo(exporter);

                // Moon Rock
                createShaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS, 4)
                        .input('#', MoonBlocks.POLISHED_MOON_ROCK)
                        .pattern("##")
                        .pattern("##")
                        .criterion(hasItem(MoonBlocks.MOON_ROCK), this.conditionsFromItem(MoonBlocks.MOON_ROCK))
                        .offerTo(this.exporter);
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS_SLAB, Ingredient.ofItem(MoonBlocks.MOON_ROCK_BRICKS))
                        .criterion("rock", conditionsFromItem(MoonBlocks.MOON_ROCK_BRICKS))
                        .offerTo(exporter);
                createStairsRecipe(MoonBlocks.MOON_ROCK_BRICKS_STAIRS, Ingredient.ofItem(MoonBlocks.MOON_ROCK))
                        .criterion("rock", conditionsFromItem(MoonBlocks.MOON_ROCK_BRICKS))
                        .offerTo(exporter);
                createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_MOON_ROCK, Ingredient.ofItem(MoonBlocks.MOON_ROCK))
                        .criterion("rock", conditionsFromItem(MoonBlocks.MOON_ROCK))
                        .offerTo(exporter);
                createCutCopperRecipe(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK, Ingredient.ofItem(MoonBlocks.BLACK_MOON_ROCK))
                        .criterion("rock", conditionsFromItem(MoonBlocks.BLACK_MOON_ROCK))
                        .offerTo(exporter);
                createShaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE, 8)
                        .pattern("pb")
                        .pattern("bp")
                        .input('b', MoonBlocks.BLACK_MOON_ROCK)
                        .input('p', ModItems.PURPLE_STAR)
                        .criterion("rock", conditionsFromItem(MoonBlocks.BLACK_MOON_ROCK))
                        .offerTo(exporter);
                createShaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_TILES, 8)
                        .pattern("wb")
                        .pattern("bw")
                        .input('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK)
                        .input('w', MoonBlocks.POLISHED_MOON_ROCK)
                        .criterion("rock", conditionsFromItem(MoonBlocks.BLACK_MOON_ROCK))
                        .offerTo(exporter);
                createShaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_ROCK_TILES, 8)
                        .pattern("wb")
                        .pattern("bw")
                        .input('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE)
                        .input('w', MoonBlocks.POLISHED_MOON_ROCK)
                        .criterion("rock", conditionsFromItem(MoonBlocks.BLACK_MOON_ROCK))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, MoonBlocks.STAR_FORGE, 1)
                        .pattern("ss")
                        .pattern("##")
                        .pattern("##")
                        .input('#', MoonBlocks.MOON_ROCK)
                        .input('s', CustomTags.STAR)
                        .group("starforge")
                        .criterion(hasItem(MoonBlocks.MOON_ROCK), conditionsFromTag(CustomTags.STAR))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "stargazer";
    }
}

