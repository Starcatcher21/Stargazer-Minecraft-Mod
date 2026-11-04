package com.github.starcatcher21.stargazer.screens.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public interface CrystalizerRecipe
        extends Recipe<CrystalizerRecipeInput> {
    @Override
    default public RecipeType<CrystalizerRecipe> getType() {
        return RecipeTypes.CRYSTALIZER.get();
    }

    @Override
    public RecipeSerializer<? extends CrystalizerRecipe> getSerializer();

    default public NonNullList<ItemStack> getRecipeRemainders(CrystalizerRecipeInput input) {
        return CrystalizerRecipe.collectRecipeRemainders(input);
    }

    public static NonNullList<ItemStack> collectRecipeRemainders(CrystalizerRecipeInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getItem(i).getItem();
            //? if >= 26.2 {
            /*defaultedList.set(i, item.getCraftingRemainder().create());
            *///?} else {
            defaultedList.set(i, input.getItem(i).getCraftingRemainingItem());
             //?}
        }
        return defaultedList;
    }

    ItemStack craft(CrystalizerRecipeInput craftingRecipeInput, RegistryAccess registryManager);

    boolean matches(CrystalizerRecipeInput recipeInput, Level world);

    default public StarCrusherRecipeInput.Positioned createPositionedRecipeInput() {
        return StarCrusherRecipeInput.createPositioned(this.getHeldStacks());
    }

    List<ItemStack> getHeldStacks();

    ItemStack getResult();

    //? if >= 26.2 {
    
    /*List<Optional<Ingredient>> getIngredients();
    *///?}

}
