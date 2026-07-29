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

public interface MoonWelderRecipe
        extends Recipe<MoonWelderRecipeInput> {
    @Override
    default public RecipeType<MoonWelderRecipe> getType() {
        return RecipeTypes.MOON_WELDER;
    }

    @Override
    public RecipeSerializer<? extends MoonWelderRecipe> getSerializer();

    default public NonNullList<ItemStack> getRecipeRemainders(MoonWelderRecipeInput input) {
        return MoonWelderRecipe.collectRecipeRemainders(input);
    }

    public static NonNullList<ItemStack> collectRecipeRemainders(MoonWelderRecipeInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = input.getItem(i).getItem();
            try {
                defaultedList.set(i, item.getCraftingRemainder().create());
            } catch (Exception ignored) {}
        }
        return defaultedList;
    }

    ItemStack assemble(MoonWelderRecipeInput craftingRecipeInput, RegistryAccess registryManager);

    boolean matches(MoonWelderRecipeInput recipeInput, Level world);

    int getMoonPhase();

    default public MoonWelderRecipeInput.Positioned createPositionedRecipeInput() {
        return MoonWelderRecipeInput.createPositioned(2, 1, this.getHeldStacks(), this.getMoonPhase());
    }

    List<ItemStack> getHeldStacks();
}
