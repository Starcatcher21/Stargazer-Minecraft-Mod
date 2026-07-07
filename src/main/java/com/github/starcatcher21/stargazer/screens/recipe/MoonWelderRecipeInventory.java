package com.github.starcatcher21.stargazer.screens.recipe;

import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;

public interface MoonWelderRecipeInventory
        extends Container,
        StackedContentsCompatible {
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

