package com.github.starcatcher21.stargazer.screens.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public class MoonWelderRecipeInput
        implements RecipeInput {
    public static final MoonWelderRecipeInput EMPTY = new MoonWelderRecipeInput(2, 1, List.of());
    private final int width;
    private final int height;
    private final List<ItemStack> stacks;
    private final RecipeFinder matcher = new RecipeFinder();
    private final int stackCount;

    private MoonWelderRecipeInput(int width, int height, List<ItemStack> stacks) {
        this.width = width;
        this.height = height;
        this.stacks = stacks;
        int i = 0;
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty()) continue;
            ++i;
            this.matcher.addInput(itemStack, 1);
        }
        this.stackCount = i;
    }

    public static MoonWelderRecipeInput create(int width, int height, List<ItemStack> stacks) {
        return MoonWelderRecipeInput.createPositioned(width, height, stacks).input();
    }

    public static MoonWelderRecipeInput.Positioned createPositioned(int width, int height, List<ItemStack> stacks) {
        return new MoonWelderRecipeInput.Positioned(new MoonWelderRecipeInput(3, 5, stacks), 3, 5);
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.stacks.get(slot);
    }

    public ItemStack getStackInSlot(int x, int y) {
        return this.stacks.get(x + y * this.width);
    }

    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stackCount == 0;
    }

    public RecipeFinder getRecipeMatcher() {
        return this.matcher;
    }

    public List<ItemStack> getStacks() {
        return this.stacks;
    }

    public int getStackCount() {
        return this.stackCount;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof MoonWelderRecipeInput) {
            MoonWelderRecipeInput craftingRecipeInput = (MoonWelderRecipeInput) o;
            return this.width == craftingRecipeInput.width && this.height == craftingRecipeInput.height && this.stackCount == craftingRecipeInput.stackCount && ItemStack.stacksEqual(this.stacks, craftingRecipeInput.stacks);
        }
        return false;
    }

    public int hashCode() {
        int i = ItemStack.listHashCode(this.stacks);
        i = 31 * i + this.width;
        i = 31 * i + this.height;
        return i;
    }


    public record Positioned(MoonWelderRecipeInput input, int left, int top) {
        public static final MoonWelderRecipeInput.Positioned EMPTY = new MoonWelderRecipeInput.Positioned(MoonWelderRecipeInput.EMPTY, 0, 0);
    }
}
