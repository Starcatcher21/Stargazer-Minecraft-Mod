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
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class StarforgeDisplay extends BasicDisplay {
    private List<EntryIngredient> in;
    @Nullable
    private List<Optional<Ingredient>> place;
    @Nullable
    private ShapedStarforgeRecipe recipe;
    private EntryIngredient out;
    public StarforgeDisplay(ShapedStarforgeRecipe recipe) {
        this(recipe.getIngredientPlacement(), Collections.singletonList(EntryIngredients.of(recipe.getResult())), recipe.getIngredients());
        this.recipe = recipe;
    }

    public StarforgeDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    public StarforgeDisplay(IngredientPlacement placement, List<EntryIngredient> outputs, List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.getIngredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.getIngredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }

    public StarforgeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    public StarforgeDisplay(ShapedStarforgeRecipeDisplay shapedStarforgeRecipeDisplay, Optional<NetworkRecipeId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }

    public StarforgeDisplay(Recipe<?> recipe) {
        this((ShapedStarforgeRecipe) recipe);
    }

    public static ShapedStarforgeRecipe getRecipe(Optional<NetworkRecipeId> networkRecipeId) {
        if (networkRecipeId.isPresent() && MinecraftClient.getInstance().getServer() != null) {
            ServerRecipeManager.ServerRecipe recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedStarforgeRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedStarforgeRecipe("", new RawStarforgeShapedRecipe(0,0, List.of(Optional.empty()), Optional.empty()), ItemStack.EMPTY);
    }

    public static ShapedStarforgeRecipe getRecipe(Identifier id) {
        if (MinecraftClient.getInstance().getServer() != null) {
            Optional<RecipeEntry<?>> recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(RegistryKey.of(RegistryKeys.RECIPE, id));
            if (recip.isPresent() && recip.get().value() instanceof ShapedStarforgeRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedStarforgeRecipe("", new RawStarforgeShapedRecipe(0,0, List.of(Optional.empty()), Optional.empty()), ItemStack.EMPTY);
    }

    @Nullable
    public static StarforgeDisplay of(RecipeEntry<? extends Recipe<?>> holder) {
        Recipe<?> recipe = holder.value();
        if (recipe instanceof ShapedStarforgeRecipe ssr) {
            return new StarforgeDisplay(ssr);
        }

        return null;
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
