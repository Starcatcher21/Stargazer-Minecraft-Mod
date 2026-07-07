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

public interface StarCrusherRecipe
        extends Recipe<StarCrusherRecipeInput> {
    @Override
    default public RecipeType<StarCrusherRecipe> getType() {
        return RecipeTypes.STAR_CRUSHER;
    }

    @Override
    public RecipeSerializer<? extends StarCrusherRecipe> getSerializer();

    default public DefaultedList<ItemStack> getRecipeRemainders(StarforgeRecipeInput input) {
        return StarCrusherRecipe.collectRecipeRemainders(input);
    }

    public static DefaultedList<ItemStack> collectRecipeRemainders(StarforgeRecipeInput input) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getStackInSlot(i).getItem();
            defaultedList.set(i, item.getRecipeRemainder());
        }
        return defaultedList;
    }

    ItemStack craft(StarCrusherRecipeInput craftingRecipeInput, DynamicRegistryManager registryManager);

    boolean matches(StarCrusherRecipeInput recipeInput, World world);

    default public StarCrusherRecipeInput.Positioned createPositionedRecipeInput() {
        return StarCrusherRecipeInput.createPositioned(this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();
}
