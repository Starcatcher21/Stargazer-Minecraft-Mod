package com.github.starcatcher21.stargazer.screens.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeInputProvider;

import java.util.List;

public interface MoonWelderRecipeInventory
        extends Inventory,
        RecipeInputProvider {
    /**
     * {@return the width of the recipe grid}
     */
    public int getWidth();

    /**
     * {@return the height of the recipe grid}
     */
    public int getHeight();

    /**
     * {@return the stacks held by the inventory}
     */
    public List<ItemStack> getHeldStacks();

    default public MoonWelderRecipeInput createRecipeInput() {
        return this.createPositionedRecipeInput().input();
    }

    default public MoonWelderRecipeInput.Positioned createPositionedRecipeInput() {
        return MoonWelderRecipeInput.createPositioned(this.getWidth(), this.getHeight(), this.getHeldStacks());
    }
}

