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

public interface MoonWelderRecipe
        extends Recipe<MoonWelderRecipeInput> {
    @Override
    default public RecipeType<MoonWelderRecipe> getType() {
        return RecipeTypes.MOON_WELDER;
    }

    @Override
    public RecipeSerializer<? extends MoonWelderRecipe> getSerializer();

    default public DefaultedList<ItemStack> getRecipeRemainders(MoonWelderRecipeInput input) {
        return MoonWelderRecipe.collectRecipeRemainders(input);
    }

    public static DefaultedList<ItemStack> collectRecipeRemainders(MoonWelderRecipeInput input) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getStackInSlot(i).getItem();
            defaultedList.set(i, item.getRecipeRemainder());
        }
        return defaultedList;
    }

    ItemStack craft(MoonWelderRecipeInput craftingRecipeInput, DynamicRegistryManager registryManager);

    boolean matches(MoonWelderRecipeInput recipeInput, World world);

    int getMoonPhase();

    default public MoonWelderRecipeInput.Positioned createPositionedRecipeInput() {
        return MoonWelderRecipeInput.createPositioned(2, 1, this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();
}
