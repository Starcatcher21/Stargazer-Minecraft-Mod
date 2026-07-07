package com.github.starcatcher21.stargazer.screens.recipe;

import java.util.List;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class StarCrusherRecipeInput
        implements RecipeInput {
    public static final StarCrusherRecipeInput EMPTY = new StarCrusherRecipeInput(List.of());
    private final List<ItemStack> stacks;
    private final StackedItemContents matcher = new StackedItemContents();
    private final int stackCount;

    public StarCrusherRecipeInput(List<ItemStack> stacks) {
        this.stacks = stacks;
        int i = 0;
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty()) continue;
            ++i;
            this.matcher.accountStack(itemStack, 1);
        }
        this.stackCount = i;
    }

    public static StarCrusherRecipeInput.Positioned createPositioned( List<ItemStack> stacks) {
        return new StarCrusherRecipeInput.Positioned(new StarCrusherRecipeInput( stacks), 1, 1);
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.stacks.get(slot);
    }

    public ItemStack getStackInSlot(int x, int y) {
        return this.stacks.get(x + y);
    }

    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stackCount == 0;
    }

    public StackedItemContents getRecipeMatcher() {
        return this.matcher;
    }

    public List<ItemStack> getStacks() {
        return this.stacks;
    }

    public int getStackCount() {
        return this.stackCount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof StarCrusherRecipeInput) {
            StarCrusherRecipeInput craftingRecipeInput = (StarCrusherRecipeInput) o;
            return this.stackCount == craftingRecipeInput.stackCount && ItemStack.listMatches(this.stacks, craftingRecipeInput.stacks);
        }
        return false;
    }

    public int hashCode() {
        int i = ItemStack.hashStackList(this.stacks);
        i = 31 * i + 1;
        i = 31 * i + 1;
        return i;
    }


    public record Positioned(StarCrusherRecipeInput input, int left, int top) {
        public static final StarCrusherRecipeInput.Positioned EMPTY = new StarCrusherRecipeInput.Positioned(StarCrusherRecipeInput.EMPTY, 0, 0);
    }
}
