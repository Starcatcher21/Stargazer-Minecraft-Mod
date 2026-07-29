package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.RawStarforgeShapedRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipeDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StarforgeDisplay extends BasicDisplay {
    private List<EntryIngredient> in;
    @Nullable
    private List<Optional<Ingredient>> place;
    @Nullable
    private ShapedStarforgeRecipe recipe;
    private EntryIngredient out;
    public StarforgeDisplay(ShapedStarforgeRecipe recipe) {
        this(recipe.placementInfo(), Collections.singletonList(EntryIngredients.of(recipe.getResult())), recipe.getIngredients());
        this.recipe = recipe;
    }

    public StarforgeDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    public StarforgeDisplay(PlacementInfo placement, List<EntryIngredient> outputs, List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.ingredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.ingredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }

    public StarforgeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    public StarforgeDisplay(ShapedStarforgeRecipeDisplay shapedStarforgeRecipeDisplay, Optional<RecipeDisplayId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }

    public StarforgeDisplay(Recipe<?> recipe) {
        this((ShapedStarforgeRecipe) recipe);
    }

    private static ShapedStarforgeRecipe emptyRecipe() {
        return new ShapedStarforgeRecipe("", new RawStarforgeShapedRecipe(0,0, List.of(Optional.empty()), Optional.empty()), ItemStackTemplate.fromStack(Blocks.AIR.asItem().getDefaultInstance()));
    }

    public static ShapedStarforgeRecipe getRecipe(Optional<RecipeDisplayId> networkRecipeId) {
        if (networkRecipeId.isPresent() && Minecraft.getInstance().getSingleplayerServer() != null) {
            RecipeManager.ServerDisplayInfo recip = Minecraft.getInstance().getSingleplayerServer().getRecipeManager().getRecipeFromDisplay(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedStarforgeRecipe ssr) {
                return ssr;
            }
        }
        return emptyRecipe();
    }

    public static ShapedStarforgeRecipe getRecipe(Identifier id) {
        if (Minecraft.getInstance().getSingleplayerServer() != null) {
            Optional<RecipeHolder<?>> recip = Minecraft.getInstance().getSingleplayerServer().getRecipeManager().byKey(ResourceKey.create(Registries.RECIPE, id));
            if (recip.isPresent() && recip.get().value() instanceof ShapedStarforgeRecipe ssr) {
                return ssr;
            }
        }
        return emptyRecipe();
    }

    public static StarforgeDisplay of(RecipeHolder<? extends Recipe<?>> holder) {
        Recipe<?> recipe = holder.value();
        if (recipe instanceof ShapedStarforgeRecipe ssr) {
            return new StarforgeDisplay(ssr);
        }

        return new StarforgeDisplay(emptyRecipe());
    }

    List<EntryIngredient> getIngedientsList() {

        List<EntryIngredient> list = new ArrayList<>(14);

        List<EntryIngredient> inputEntries = getInputEntries();

        for (int i = 0; i < 14; i++) {
            EntryIngredient stacks;
            try {
                stacks = inputEntries.get(i);
                list.add(stacks);
            } catch (Exception ignored) {}
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return StarforgeCategory.STARFORGE;
    }

    public EntryIngredient result() {
        return this.out;
    }

    public List<Optional<Ingredient>> placement() {
        return this.place;
    }

    public List<EntryIngredient> ingredients() {
        return this.in;
    }
    public ShapedStarforgeRecipe recipe() {
        return this.recipe;
    }


    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.STARFORGE;
    }
}
