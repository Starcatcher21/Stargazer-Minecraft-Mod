package com.github.starcatcher21.stargazer.screens.recipe;

import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public interface StarforgeRecipe
        extends Recipe<StarforgeRecipeInput> {
    @Override
    default public RecipeType<StarforgeRecipe> getType() {
        return RecipeTypes.STARFORGE;
    }

    @Override
    public RecipeSerializer<? extends StarforgeRecipe> getSerializer();

    default public NonNullList<ItemStack> getRecipeRemainders(StarforgeRecipeInput input) {
        return StarforgeRecipe.collectRecipeRemainders(input);
    }

    public static NonNullList<ItemStack> collectRecipeRemainders(StarforgeRecipeInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getItem(i).getItem();
            defaultedList.set(i, item.getCraftingRemainder().create());
        }
        return defaultedList;
    }

    ItemStack craft(StarforgeRecipeInput craftingRecipeInput, RegistryAccess registryManager);

    boolean matches(StarforgeRecipeInput recipeInput, Level world);

    default public StarforgeRecipeInput.Positioned createPositionedRecipeInput() {
        return StarforgeRecipeInput.createPositioned(5, 5, this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();
}
