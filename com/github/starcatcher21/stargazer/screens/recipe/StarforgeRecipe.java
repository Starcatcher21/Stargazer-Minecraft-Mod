package com.github.starcatcher21.stargazer.screens.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public interface StarforgeRecipe
        extends Recipe<StarforgeRecipeInput> {
    @Override
    default public RecipeType<StarforgeRecipe> getType() {
        return RecipeTypes.STARFORGE;
    }

    @Override
    public RecipeSerializer<? extends StarforgeRecipe> getSerializer();

    default public DefaultedList<ItemStack> getRecipeRemainders(StarforgeRecipeInput input) {
        return StarforgeRecipe.collectRecipeRemainders(input);
    }

    public static DefaultedList<ItemStack> collectRecipeRemainders(StarforgeRecipeInput input) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getStackInSlot(i).getItem();
            defaultedList.set(i, item.getRecipeRemainder());
        }
        return defaultedList;
    }

    ItemStack craft(StarforgeRecipeInput craftingRecipeInput, DynamicRegistryManager registryManager);

    boolean matches(StarforgeRecipeInput recipeInput, World world);

    default public StarforgeRecipeInput.Positioned createPositionedRecipeInput() {
        return StarforgeRecipeInput.createPositioned(5, 5, this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();
}
