package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                // Moon Tree
                shapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS, 4)
                        .requires(CustomTags.MOON_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.MOON_LOG))
                        .save(this.output);
                shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, 4)
                        .requires(CustomTags.EYE_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.EYE_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS_SLAB, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_PLANKS_SLAB, Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.RED_MOON_PLANKS_SLAB, Ingredient.of(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.BLUE_MOON_PLANKS_SLAB, Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.YELLOW_MOON_PLANKS_SLAB, Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.MOON_PLANKS_STAIRS, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS, Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.RED_MOON_PLANKS_STAIRS, Ingredient.of(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.BLUE_MOON_PLANKS_STAIRS, Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS, Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.MOON_PLANKS_BUTTON, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON, Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.RED_MOON_PLANKS_BUTTON, Ingredient.of(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.BLUE_MOON_PLANKS_BUTTON, Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON, Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.MOON_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.MOON_PLANKS_FENCE, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.PURPLE_MOON_PLANKS_FENCE, Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.RED_MOON_PLANKS_FENCE, Ingredient.of(MoonBlocks.RED_MOON_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.BLUE_MOON_PLANKS_FENCE, Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.YELLOW_MOON_PLANKS_FENCE, Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS))
                        .save(output);
                doorBuilder(MoonBlocks.MOON_PLANKS_DOOR, Ingredient.of(MoonBlocks.MOON_PLANKS))
                        .group("wooden_door")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS))
                        .save(output);
                doorBuilder(StarBlocks.STAR_PLANKS_DOOR, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_door")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                doorBuilder(Darkness.DARKNESS_PLANKS_DOOR, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_door")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS))
                        .save(output);
                doorBuilder(MoonBlocks.CURVE_PLANKS_DOOR, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_door")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                // Star Tree
                shapeless(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS, 4)
                        .requires(CustomTags.STAR_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.STAR_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS_SLAB, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                stairBuilder(StarBlocks.STAR_PLANKS_STAIRS, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                buttonBuilder(StarBlocks.STAR_PLANKS_BUTTON, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                fenceBuilder(StarBlocks.STAR_PLANKS_FENCE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                fenceGateBuilder(StarBlocks.STAR_PLANKS_FENCE_GATE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS))
                        .save(output);
                // Curve Tree
                shapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS, 4)
                        .requires(CustomTags.CURVE_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.CURVE_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS_SLAB, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                stairBuilder(MoonBlocks.CURVE_PLANKS_STAIRS, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                buttonBuilder(MoonBlocks.CURVE_PLANKS_BUTTON, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                fenceBuilder(MoonBlocks.CURVE_PLANKS_FENCE, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                fenceGateBuilder(MoonBlocks.CURVE_PLANKS_FENCE_GATE, Ingredient.of(MoonBlocks.CURVE_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);

                // Darkness
                shapeless(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS, 4)
                        .requires(CustomTags.DARKNESS_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.DARKNESS_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS_SLAB, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS))
                        .save(output);
                stairBuilder(Darkness.DARKNESS_PLANKS_STAIRS, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS))
                        .save(output);
                buttonBuilder(Darkness.DARKNESS_PLANKS_BUTTON, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS))
                        .save(output);
                fenceBuilder(Darkness.DARKNESS_PLANKS_FENCE, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS))
                        .save(output);
                fenceGateBuilder(Darkness.DARKNESS_PLANKS_FENCE_GATE, Ingredient.of(Darkness.DARKNESS_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS))
                        .save(output);

                // Nebulas
                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.BLUE_NEBULA_PLANKS, 4)
                        .requires(Nebulas.BLUE_NEBULA_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.BLUE_NEBULA_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.BLUE_NEBULA_PLANKS_SLAB, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS))
                        .save(output);
                stairBuilder(Nebulas.BLUE_NEBULA_PLANKS_STAIRS, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS))
                        .save(output);
                buttonBuilder(Nebulas.BLUE_NEBULA_PLANKS_BUTTON, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS))
                        .save(output);
                fenceBuilder(Nebulas.BLUE_NEBULA_PLANKS_FENCE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS))
                        .save(output);
                fenceGateBuilder(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.RED_NEBULA_PLANKS, 4)
                        .requires(Nebulas.RED_NEBULA_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.RED_NEBULA_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.RED_NEBULA_PLANKS_SLAB, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS))
                        .save(output);
                stairBuilder(Nebulas.RED_NEBULA_PLANKS_STAIRS, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS))
                        .save(output);
                buttonBuilder(Nebulas.RED_NEBULA_PLANKS_BUTTON, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS))
                        .save(output);
                fenceBuilder(Nebulas.RED_NEBULA_PLANKS_FENCE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS))
                        .save(output);
                fenceGateBuilder(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.PURPLE_NEBULA_PLANKS, 4)
                        .requires(Nebulas.PURPLE_NEBULA_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.PURPLE_NEBULA_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.PURPLE_NEBULA_PLANKS_SLAB, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS))
                        .save(output);
                stairBuilder(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS))
                        .save(output);
                buttonBuilder(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS))
                        .save(output);
                fenceBuilder(Nebulas.PURPLE_NEBULA_PLANKS_FENCE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS))
                        .save(output);
                fenceGateBuilder(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.YELLOW_NEBULA_PLANKS, 4)
                        .requires(Nebulas.YELLOW_NEBULA_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.YELLOW_NEBULA_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.YELLOW_NEBULA_PLANKS_SLAB, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS))
                        .save(output);
                stairBuilder(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS))
                        .save(output);
                buttonBuilder(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS))
                        .save(output);
                fenceBuilder(Nebulas.YELLOW_NEBULA_PLANKS_FENCE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS))
                        .save(output);
                fenceGateBuilder(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE, Ingredient.of(StarBlocks.STAR_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS))
                        .save(output);

                // Moon Rock
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS, 4)
                        .define('#', MoonBlocks.POLISHED_MOON_ROCK)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(MoonBlocks.MOON_ROCK), this.has(MoonBlocks.MOON_ROCK))
                        .save(this.output);

                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK, 4)
                        .define('#', MoonBlocks.SUN_ENRICHED_MOON_ROCK)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(MoonBlocks.SUN_ENRICHED_MOON_ROCK), this.has(MoonBlocks.SUN_ENRICHED_MOON_ROCK))
                        .save(this.output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PRISMATIC_SHARD_BLOCK, 1)
                        .define('#', Ingredient.of(ModItems.PRISMATIC_SHARD))
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(getHasName(ModItems.PRISMATIC_SHARD), this.has(ModItems.PRISMATIC_SHARD))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.RED_STAR)
                        .requires(Nebulas.RED_TENTACLE_FLOWER)
                        .unlockedBy(getHasName(Nebulas.RED_TENTACLE_FLOWER), this.has(Nebulas.RED_TENTACLE_FLOWER))
                        .save(this.output);

                shapeless(RecipeCategory.MISC, ModItems.BLUE_STAR)
                        .requires(Nebulas.BLUE_TENTACLE_FLOWER)
                        .unlockedBy(getHasName(Nebulas.BLUE_TENTACLE_FLOWER), this.has(Nebulas.RED_TENTACLE_FLOWER))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.PURPLE_STAR)
                        .requires(Nebulas.PURPLE_TENTACLE_FLOWER)
                        .unlockedBy(getHasName(Nebulas.PURPLE_TENTACLE_FLOWER), this.has(Nebulas.RED_TENTACLE_FLOWER))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.YELLOW_STAR)
                        .requires(Nebulas.YELLOW_TENTACLE_FLOWER)
                        .unlockedBy(getHasName(Nebulas.YELLOW_TENTACLE_FLOWER), this.has(Nebulas.RED_TENTACLE_FLOWER))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS_SLAB, Ingredient.of(MoonBlocks.MOON_ROCK_BRICKS))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK_BRICKS))
                        .save(output);
                stairBuilder(MoonBlocks.MOON_ROCK_BRICKS_STAIRS, Ingredient.of(MoonBlocks.MOON_ROCK_BRICKS))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK_BRICKS))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_MOON_ROCK, Ingredient.of(MoonBlocks.MOON_ROCK))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK, Ingredient.of(MoonBlocks.BLACK_MOON_ROCK))
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, Chess.BLACK_BRICKS, Ingredient.of(ModItems.BLACK_BRICK))
                        .unlockedBy("rock", has(ModItems.BLACK_BRICK))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, Chess.WHITE_BRICKS, Ingredient.of(ModItems.WHITE_BRICK))
                        .unlockedBy("rock", has(ModItems.WHITE_BRICK))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE, 8)
                        .pattern("pb")
                        .pattern("bp")
                        .define('b', MoonBlocks.BLACK_MOON_ROCK)
                        .define('p', ModItems.PURPLE_STAR)
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_TILES, 8)
                        .pattern("wb")
                        .pattern("bw")
                        .define('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK)
                        .define('w', MoonBlocks.POLISHED_MOON_ROCK)
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_ROCK_TILES, 8)
                        .pattern("wb")
                        .pattern("bw")
                        .define('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE)
                        .define('w', MoonBlocks.POLISHED_MOON_ROCK)
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK))
                        .save(output);
                shaped(RecipeCategory.MISC, MoonBlocks.STAR_FORGE, 1)
                        .pattern("ss")
                        .pattern("##")
                        .pattern("##")
                        .define('#', MoonBlocks.MOON_ROCK)
                        .define('s', CustomTags.STAR)
                        .group("starforge")
                        .unlockedBy(getHasName(MoonBlocks.MOON_ROCK), has(CustomTags.STAR))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.POLISHED_RED_ROCK, 4)
                        .define('#', RedOrbBlocks.RED_ROCK)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(RedOrbBlocks.RED_ROCK), this.has(RedOrbBlocks.RED_ROCK))
                        .save(this.output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.COMET_BLOCK, 1)
                        .define('#', ModItems.COMET_FRAGMENT)
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(ModItems.COMET_FRAGMENT), this.has(ModItems.COMET_FRAGMENT))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.RED_ROCK_SLAB, Ingredient.of(RedOrbBlocks.RED_ROCK))
                        .unlockedBy("rock", has(RedOrbBlocks.RED_ROCK))
                        .save(output);
                stairBuilder(RedOrbBlocks.RED_ROCK_STAIRS, Ingredient.of(RedOrbBlocks.RED_ROCK))
                        .unlockedBy("rock", has(RedOrbBlocks.RED_ROCK))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.YERI_PLANKS, 4)
                        .requires(RedOrbBlocks.YERI_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(RedOrbBlocks.YERI_LOG))
                        .save(this.output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.YERI_PLANKS_SLAB, Ingredient.of(RedOrbBlocks.YERI_PLANKS))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS))
                        .save(output);
                stairBuilder(RedOrbBlocks.YERI_PLANKS_STAIRS, Ingredient.of(RedOrbBlocks.YERI_PLANKS))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS))
                        .save(output);
                buttonBuilder(RedOrbBlocks.YERI_PLANKS_BUTTON, Ingredient.of(RedOrbBlocks.YERI_PLANKS))
                        .group("wooden_button")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS))
                        .save(output);
                fenceBuilder(RedOrbBlocks.YERI_PLANKS_FENCE, Ingredient.of(RedOrbBlocks.YERI_PLANKS))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS))
                        .save(output);
                fenceGateBuilder(RedOrbBlocks.YERI_PLANKS_FENCE_GATE, Ingredient.of(RedOrbBlocks.YERI_PLANKS))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS))
                        .save(output);
                oreSmelting(Collections.singletonList((ItemLike) ModItems.COPPER_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
                oreSmelting(Collections.singletonList((ItemLike) ModItems.IRON_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
                oreSmelting(Collections.singletonList((ItemLike) ModItems.GOLD_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 0.7f, 200, "gold_ingot");
                oreBlasting(Collections.singletonList((ItemLike) ModItems.COPPER_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
                oreBlasting(Collections.singletonList((ItemLike) ModItems.IRON_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");
                oreBlasting(Collections.singletonList((ItemLike)ModItems.GOLD_DUST), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 0.7f, 100, "gold_ingot");
            }
        };
    }

    @Override
    public String getName() {
        return "stargazer";
    }
}

