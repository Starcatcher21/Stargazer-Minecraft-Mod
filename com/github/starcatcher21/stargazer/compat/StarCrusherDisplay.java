package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.*;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StarCrusherDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable
    private List<Optional<Ingredient>> place;
    @Nullable
    private ShapedStarCrusherRecipe recipe;
    private final EntryIngredient out;
    public StarCrusherDisplay(ShapedStarCrusherRecipe recipe) {
        this(recipe.getIngredientPlacement(), Collections.singletonList(EntryIngredients.of(recipe.getResult())), recipe.getIngredients());
        this.recipe = recipe;
    }

    public StarCrusherDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    public StarCrusherDisplay(IngredientPlacement placement, List<EntryIngredient> outputs, List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.getIngredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.getIngredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }

    public StarCrusherDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    public StarCrusherDisplay(StarCrusherRecipeDisplay shapedMoonWelderRecipeDisplay, Optional<NetworkRecipeId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }

    public StarCrusherDisplay(Recipe<?> recipe) {
        this((ShapedStarCrusherRecipe) recipe);
    }

    public static ShapedStarCrusherRecipe getRecipe(Optional<NetworkRecipeId> networkRecipeId) {
        if (networkRecipeId.isPresent() && MinecraftClient.getInstance().getServer() != null) {
            ServerRecipeManager.ServerRecipe recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedStarCrusherRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedStarCrusherRecipe("", new StarCrusherShapedRecipe(Ingredient.ofItem(Blocks.AIR.asItem()), Optional.empty()), ItemStack.EMPTY);
    }

    public static ShapedStarCrusherRecipe getRecipe(Identifier id) {
        if (MinecraftClient.getInstance().getServer() != null) {
            Optional<RecipeEntry<?>> recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(RegistryKey.of(RegistryKeys.RECIPE, id));
            if (recip.isPresent() && recip.get().value() instanceof ShapedStarCrusherRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedStarCrusherRecipe("", new StarCrusherShapedRecipe(Ingredient.ofItem(Blocks.AIR.asItem()), Optional.empty()), ItemStack.EMPTY);
    }

    @Nullable
    public static StarCrusherDisplay of(RecipeEntry<? extends Recipe<?>> holder) {
        Recipe<?> recipe = holder.value();
        if (recipe instanceof ShapedStarCrusherRecipe ssr) {
            return new StarCrusherDisplay(ssr);
        }

        return null;
    }

    List<EntryIngredient> getIngedientsList() {

        List<EntryIngredient> list = new ArrayList<>(14);

        List<EntryIngredient> inputEntries = getInputEntries();

        for (int i = 0; i < 2; i++) {
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
        return StarCrusherCategory.STARFORGE;
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
    public ShapedStarCrusherRecipe recipe() {
        return this.recipe;
    }


    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.STAR_CRUSHER;
    }
}
