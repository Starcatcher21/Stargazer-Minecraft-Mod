/*? if fabric {*/
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new net.minecraft.data.recipes.RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                HolderGetter<Item> items = wrapperLookup.lookupOrThrow(Registries.ITEM);

                shapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS.get(), 4)
                        .requires(CustomTags.MOON_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.MOON_LOG))
                        .save(this.output);
                shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, 4)
                        .requires(CustomTags.EYE_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.EYE_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.RED_MOON_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.RED_MOON_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS.get()))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.BLUE_MOON_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .save(output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.MOON_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.RED_MOON_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.RED_MOON_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.BLUE_MOON_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.MOON_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.RED_MOON_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.RED_MOON_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.BLUE_MOON_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.MOON_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.RED_MOON_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.MOON_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.PURPLE_MOON_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.PURPLE_MOON_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.RED_MOON_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.RED_MOON_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.RED_MOON_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.BLUE_MOON_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.BLUE_MOON_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.YELLOW_MOON_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.YELLOW_MOON_PLANKS.get()))
                        .save(output);
                doorBuilder(MoonBlocks.MOON_PLANKS_DOOR.get(), Ingredient.of(MoonBlocks.MOON_PLANKS.get()))
                        .group("wooden_door")
                        .unlockedBy("wood", has(MoonBlocks.MOON_PLANKS.get()))
                        .save(output);
                doorBuilder(StarBlocks.STAR_PLANKS_DOOR.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_door")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);
                doorBuilder(Darkness.DARKNESS_PLANKS_DOOR.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_door")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);
                doorBuilder(MoonBlocks.CURVE_PLANKS_DOOR.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_door")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);

                // Star Tree
                shapeless(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS.get(), 4)
                        .requires(CustomTags.STAR_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.STAR_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, StarBlocks.STAR_PLANKS_SLAB.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);
                stairBuilder(StarBlocks.STAR_PLANKS_STAIRS.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);
                buttonBuilder(StarBlocks.STAR_PLANKS_BUTTON.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);
                fenceBuilder(StarBlocks.STAR_PLANKS_FENCE.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(StarBlocks.STAR_PLANKS_FENCE_GATE.get(), Ingredient.of(StarBlocks.STAR_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(StarBlocks.STAR_PLANKS.get()))
                        .save(output);

                // Curve Tree
                shapeless(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS.get(), 4)
                        .requires(CustomTags.CURVE_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.CURVE_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CURVE_PLANKS_SLAB.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.CURVE_PLANKS_STAIRS.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);
                buttonBuilder(MoonBlocks.CURVE_PLANKS_BUTTON.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);
                fenceBuilder(MoonBlocks.CURVE_PLANKS_FENCE.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(MoonBlocks.CURVE_PLANKS_FENCE_GATE.get(), Ingredient.of(MoonBlocks.CURVE_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(MoonBlocks.CURVE_PLANKS.get()))
                        .save(output);

                // Darkness
                shapeless(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS.get(), 4)
                        .requires(CustomTags.DARKNESS_LOG)
                        .group("planks")
                        .unlockedBy("has_log", this.has(CustomTags.DARKNESS_LOG))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Darkness.DARKNESS_PLANKS_SLAB.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);
                stairBuilder(Darkness.DARKNESS_PLANKS_STAIRS.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);
                buttonBuilder(Darkness.DARKNESS_PLANKS_BUTTON.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);
                fenceBuilder(Darkness.DARKNESS_PLANKS_FENCE.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(Darkness.DARKNESS_PLANKS_FENCE_GATE.get(), Ingredient.of(Darkness.DARKNESS_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Darkness.DARKNESS_PLANKS.get()))
                        .save(output);

                // Nebulas
                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.BLUE_NEBULA_PLANKS.get(), 4)
                        .requires(Nebulas.BLUE_NEBULA_LOG.get())
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.BLUE_NEBULA_LOG.get()))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.BLUE_NEBULA_PLANKS_SLAB.get(), Ingredient.of(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .save(output);
                stairBuilder(Nebulas.BLUE_NEBULA_PLANKS_STAIRS.get(), Ingredient.of(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .save(output);
                buttonBuilder(Nebulas.BLUE_NEBULA_PLANKS_BUTTON.get(), Ingredient.of(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .save(output);
                fenceBuilder(Nebulas.BLUE_NEBULA_PLANKS_FENCE.get(), Ingredient.of(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE.get(), Ingredient.of(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.BLUE_NEBULA_PLANKS.get()))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.RED_NEBULA_PLANKS.get(), 4)
                        .requires(Nebulas.RED_NEBULA_LOG.get())
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.RED_NEBULA_LOG.get()))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.RED_NEBULA_PLANKS_SLAB.get(), Ingredient.of(Nebulas.RED_NEBULA_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS.get()))
                        .save(output);
                stairBuilder(Nebulas.RED_NEBULA_PLANKS_STAIRS.get(), Ingredient.of(Nebulas.RED_NEBULA_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS.get()))
                        .save(output);
                buttonBuilder(Nebulas.RED_NEBULA_PLANKS_BUTTON.get(), Ingredient.of(Nebulas.RED_NEBULA_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS.get()))
                        .save(output);
                fenceBuilder(Nebulas.RED_NEBULA_PLANKS_FENCE.get(), Ingredient.of(Nebulas.RED_NEBULA_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE.get(), Ingredient.of(Nebulas.RED_NEBULA_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.RED_NEBULA_PLANKS.get()))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.PURPLE_NEBULA_PLANKS.get(), 4)
                        .requires(Nebulas.PURPLE_NEBULA_LOG.get())
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.PURPLE_NEBULA_LOG.get()))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get(), Ingredient.of(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .save(output);
                stairBuilder(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS.get(), Ingredient.of(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .save(output);
                buttonBuilder(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON.get(), Ingredient.of(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .save(output);
                fenceBuilder(Nebulas.PURPLE_NEBULA_PLANKS_FENCE.get(), Ingredient.of(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE.get(), Ingredient.of(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.PURPLE_NEBULA_PLANKS.get()))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Nebulas.YELLOW_NEBULA_PLANKS.get(), 4)
                        .requires(Nebulas.YELLOW_NEBULA_LOG.get())
                        .group("planks")
                        .unlockedBy("has_log", this.has(Nebulas.YELLOW_NEBULA_LOG.get()))
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get(), Ingredient.of(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .save(output);
                stairBuilder(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS.get(), Ingredient.of(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .save(output);
                buttonBuilder(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON.get(), Ingredient.of(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .save(output);
                fenceBuilder(Nebulas.YELLOW_NEBULA_PLANKS_FENCE.get(), Ingredient.of(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE.get(), Ingredient.of(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(Nebulas.YELLOW_NEBULA_PLANKS.get()))
                        .save(output);

                // Moon Rock
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS.get(), 4)
                        .define('#', MoonBlocks.POLISHED_MOON_ROCK.get())
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(MoonBlocks.MOON_ROCK.get()), this.has(MoonBlocks.MOON_ROCK.get()))
                        .save(this.output);

                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK.get(), 4)
                        .define('#', MoonBlocks.SUN_ENRICHED_MOON_ROCK.get())
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get()), this.has(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get()))
                        .save(this.output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PRISMATIC_SHARD_BLOCK.get(), 1)
                        .define('#', Ingredient.of(ModItems.PRISMATIC_SHARD.get())) // Fixed with .get()
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(getHasName(ModItems.PRISMATIC_SHARD.get()), this.has(ModItems.PRISMATIC_SHARD.get())) // Fixed with .get()
                        .save(this.output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.CRYSTAL_MOON_BLOCK.get(), 1)
                        .define('#', Ingredient.of(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(getHasName(ModItems.CRYSTAL_MOON.get()), this.has(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.CRYSTAL_MOON.get(), 9) // Fixed with .get()
                        .requires(MoonBlocks.CRYSTAL_MOON_BLOCK.get())
                        .unlockedBy(getHasName(MoonBlocks.CRYSTAL_MOON_BLOCK.get()), this.has(MoonBlocks.CRYSTAL_MOON_BLOCK.get()))
                        .save(this.output);
                shaped(RecipeCategory.COMBAT, ModItems.MOON_CHESTPLATE.get(), 1) // Fixed with .get()
                        .define('#', Ingredient.of(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy(getHasName(ModItems.CRYSTAL_MOON.get()), this.has(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .save(this.output);
                shaped(RecipeCategory.COMBAT, ModItems.MOON_BOOTS.get(), 1) // Fixed with .get()
                        .define('#', Ingredient.of(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .pattern("# #")
                        .pattern("# #")
                        .unlockedBy(getHasName(ModItems.CRYSTAL_MOON.get()), this.has(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .save(this.output);
                shaped(RecipeCategory.COMBAT, ModItems.MOON_LEGGINS.get(), 1) // Fixed with .get()
                        .define('#', Ingredient.of(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .unlockedBy(getHasName(ModItems.CRYSTAL_MOON.get()), this.has(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .save(this.output);
                shaped(RecipeCategory.COMBAT, ModItems.MOON_HELMET.get(), 1) // Fixed with .get()
                        .define('#', Ingredient.of(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .pattern("###")
                        .pattern("# #")
                        .unlockedBy(getHasName(ModItems.CRYSTAL_MOON.get()), this.has(ModItems.CRYSTAL_MOON.get())) // Fixed with .get()
                        .save(this.output);

                shapeless(RecipeCategory.MISC, ModItems.RED_STAR.get()) // Fixed with .get()
                        .requires(Nebulas.RED_TENTACLE_FLOWER.get())
                        .unlockedBy(getHasName(Nebulas.RED_TENTACLE_FLOWER.get()), this.has(Nebulas.RED_TENTACLE_FLOWER.get()))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.BLUE_STAR.get()) // Fixed with .get()
                        .requires(Nebulas.BLUE_TENTACLE_FLOWER.get())
                        .unlockedBy(getHasName(Nebulas.BLUE_TENTACLE_FLOWER.get()), this.has(Nebulas.BLUE_TENTACLE_FLOWER.get()))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.PURPLE_STAR.get()) // Fixed with .get()
                        .requires(Nebulas.PURPLE_TENTACLE_FLOWER.get())
                        .unlockedBy(getHasName(Nebulas.PURPLE_TENTACLE_FLOWER.get()), this.has(Nebulas.PURPLE_TENTACLE_FLOWER.get()))
                        .save(this.output);
                shapeless(RecipeCategory.MISC, ModItems.YELLOW_STAR.get()) // Fixed with .get()
                        .requires(Nebulas.YELLOW_TENTACLE_FLOWER.get())
                        .unlockedBy(getHasName(Nebulas.YELLOW_TENTACLE_FLOWER.get()), this.has(Nebulas.YELLOW_TENTACLE_FLOWER.get()))
                        .save(this.output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_BRICKS_SLAB.get(), Ingredient.of(MoonBlocks.MOON_ROCK_BRICKS.get()))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK_BRICKS.get()))
                        .save(output);
                stairBuilder(MoonBlocks.MOON_ROCK_BRICKS_STAIRS.get(), Ingredient.of(MoonBlocks.MOON_ROCK_BRICKS.get()))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK_BRICKS.get()))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_MOON_ROCK.get(), Ingredient.of(MoonBlocks.MOON_ROCK.get()))
                        .unlockedBy("rock", has(MoonBlocks.MOON_ROCK.get()))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK.get(), Ingredient.of(MoonBlocks.BLACK_MOON_ROCK.get()))
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK.get()))
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, Chess.BLACK_BRICKS.get(), Ingredient.of(ModItems.BLACK_BRICK.get())) // Fixed with .get()
                        .unlockedBy("rock", has(ModItems.BLACK_BRICK.get())) // Fixed with .get()
                        .save(output);
                cutBuilder(RecipeCategory.BUILDING_BLOCKS, Chess.WHITE_BRICKS.get(), Ingredient.of(ModItems.WHITE_BRICK.get())) // Fixed with .get()
                        .unlockedBy("rock", has(ModItems.WHITE_BRICK.get())) // Fixed with .get()
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get(), 8)
                        .pattern("pb")
                        .pattern("bp")
                        .define('b', MoonBlocks.BLACK_MOON_ROCK.get())
                        .define('p', ModItems.PURPLE_STAR.get()) // Fixed with .get()
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK.get()))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.MOON_ROCK_TILES.get(), 8)
                        .pattern("wb")
                        .pattern("bw")
                        .define('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK.get())
                        .define('w', MoonBlocks.POLISHED_MOON_ROCK.get())
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK.get()))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.PURPLE_MOON_ROCK_TILES.get(), 8)
                        .pattern("wb")
                        .pattern("bw")
                        .define('b', MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get())
                        .define('w', MoonBlocks.POLISHED_MOON_ROCK.get())
                        .unlockedBy("rock", has(MoonBlocks.BLACK_MOON_ROCK.get()))
                        .save(output);
                shaped(RecipeCategory.MISC, MoonBlocks.STAR_FORGE.get(), 1)
                        .pattern("ss")
                        .pattern("##")
                        .pattern("##")
                        .define('#', MoonBlocks.MOON_ROCK.get())
                        .define('s', CustomTags.STAR)
                        .group("starforge")
                        .unlockedBy(getHasName(MoonBlocks.MOON_ROCK.get()), has(CustomTags.STAR))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.POLISHED_RED_ROCK.get(), 4)
                        .define('#', RedOrbBlocks.RED_ROCK.get())
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(RedOrbBlocks.RED_ROCK.get()), this.has(RedOrbBlocks.RED_ROCK.get()))
                        .save(this.output);
                shaped(RecipeCategory.BUILDING_BLOCKS, MoonBlocks.COMET_BLOCK.get(), 1)
                        .define('#', ModItems.COMET_FRAGMENT.get()) // Fixed with .get()
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy(getHasName(ModItems.COMET_FRAGMENT.get()), this.has(ModItems.COMET_FRAGMENT.get())) // Fixed with .get()
                        .save(this.output);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.RED_ROCK_SLAB.get(), Ingredient.of(RedOrbBlocks.RED_ROCK.get()))
                        .unlockedBy("rock", has(RedOrbBlocks.RED_ROCK.get()))
                        .save(output);
                stairBuilder(RedOrbBlocks.RED_ROCK_STAIRS.get(), Ingredient.of(RedOrbBlocks.RED_ROCK.get()))
                        .unlockedBy("rock", has(RedOrbBlocks.RED_ROCK.get()))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.YERI_PLANKS.get(), 4)
                        .requires(RedOrbBlocks.YERI_LOG.get())
                        .group("planks")
                        .unlockedBy("has_log", this.has(RedOrbBlocks.YERI_LOG.get()))
                        .save(this.output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, RedOrbBlocks.YERI_PLANKS_SLAB.get(), Ingredient.of(RedOrbBlocks.YERI_PLANKS.get()))
                        .group("wooden_slab")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS.get()))
                        .save(output);
                stairBuilder(RedOrbBlocks.YERI_PLANKS_STAIRS.get(), Ingredient.of(RedOrbBlocks.YERI_PLANKS.get()))
                        .group("wooden_stairs")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS.get()))
                        .save(output);
                buttonBuilder(RedOrbBlocks.YERI_PLANKS_BUTTON.get(), Ingredient.of(RedOrbBlocks.YERI_PLANKS.get()))
                        .group("wooden_button")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS.get()))
                        .save(output);
                fenceBuilder(RedOrbBlocks.YERI_PLANKS_FENCE.get(), Ingredient.of(RedOrbBlocks.YERI_PLANKS.get()))
                        .group("wooden_fence")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS.get()))
                        .save(output);
                fenceGateBuilder(RedOrbBlocks.YERI_PLANKS_FENCE_GATE.get(), Ingredient.of(RedOrbBlocks.YERI_PLANKS.get()))
                        .group("wooden_fence_gate")
                        .unlockedBy("wood", has(RedOrbBlocks.YERI_PLANKS.get()))
                        .save(output);

                oreSmelting(Collections.singletonList((ItemLike) ModItems.COPPER_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
                oreSmelting(Collections.singletonList((ItemLike) ModItems.IRON_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
                oreSmelting(Collections.singletonList((ItemLike) ModItems.GOLD_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 0.7f, 200, "gold_ingot"); // Fixed with .get()
                oreBlasting(Collections.singletonList((ItemLike) ModItems.COPPER_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot"); // Fixed with .get()
                oreBlasting(Collections.singletonList((ItemLike) ModItems.IRON_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot"); // Fixed with .get()
                oreBlasting(Collections.singletonList((ItemLike) ModItems.GOLD_DUST.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GOLD_INGOT, 0.7f, 100, "gold_ingot"); // Fixed with .get()
            }
        };
    }

    @Override
    public String getName() {
        return "stargazer";
    }
}
//? }
