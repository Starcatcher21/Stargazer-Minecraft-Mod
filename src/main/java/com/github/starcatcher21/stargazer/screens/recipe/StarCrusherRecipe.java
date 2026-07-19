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

public interface StarCrusherRecipe
        extends Recipe<StarCrusherRecipeInput> {
    @Override
    default public RecipeType<StarCrusherRecipe> getType() {
        return RecipeTypes.STAR_CRUSHER;
    }

    @Override
    public RecipeSerializer<? extends StarCrusherRecipe> getSerializer();

    default public NonNullList<ItemStack> getRecipeRemainders(StarforgeRecipeInput input) {
        return StarCrusherRecipe.collectRecipeRemainders(input);
    }

    public static NonNullList<ItemStack> collectRecipeRemainders(StarforgeRecipeInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getItem(i).getItem();
            defaultedList.set(i, item.getCraftingRemainder().create());
        }
        return defaultedList;
    }

    ItemStack craft(StarCrusherRecipeInput craftingRecipeInput, RegistryAccess registryManager);

    boolean matches(StarCrusherRecipeInput recipeInput, Level world);

    default public StarCrusherRecipeInput.Positioned createPositionedRecipeInput() {
        return StarCrusherRecipeInput.createPositioned(this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();
}
