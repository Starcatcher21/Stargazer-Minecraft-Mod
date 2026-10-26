package com.github.starcatcher21.stargazer.screens.recipe;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

//? if >= 26.2 {
/*import net.minecraft.world.entity.player.StackedItemContents;
*///?} else {
import net.minecraft.world.entity.player.StackedContents;
 //?}

public class StarCrusherRecipeInput implements RecipeInput {

    //? if >= 26.2 {
    /*public static final StarCrusherRecipeInput EMPTY =
            new StarCrusherRecipeInput(List.of());
    *///?} else {
    public static final StarCrusherRecipeInput EMPTY =
        new StarCrusherRecipeInput(List.of());
    //?}

    private final List<ItemStack> stacks;
    private final int stackCount;

    //? if >= 26.2 {
    /*private final StackedItemContents matcher = new StackedItemContents();
    *///?} else {
    // 1.21.1 has no StackedItemContents class. Use StackedContents instead.
    private final StackedContents matcher = new StackedContents();
    //?}

    public StarCrusherRecipeInput(List<ItemStack> stacks) {
        this.stacks = stacks;
        int i = 0;
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty()) continue;
            ++i;
            //? if >= 26.2 {
            /*this.matcher.accountStack(itemStack, 1);
            *///?} else {
            this.matcher.accountStack(itemStack);
            //?}
        }
        this.stackCount = i;
    }

    public static Positioned createPositioned(List<ItemStack> stacks) {
        return new Positioned(new StarCrusherRecipeInput(stacks), 1, 1);
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

    //? if >= 26.2 {
    /*public StackedItemContents getRecipeMatcher() {
        return this.matcher;
    }
    *///?}

    public List<ItemStack> getStacks() {
        return this.stacks;
    }

    public int getStackCount() {
        return this.stackCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof StarCrusherRecipeInput craftingRecipeInput) {
            return this.stackCount == craftingRecipeInput.stackCount
                    && ItemStack.listMatches(this.stacks, craftingRecipeInput.stacks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        //? if >= 26.2 {
        /*int i = ItemStack.hashStackList(this.stacks);
        *///?} else {
        // 1.21.1: hashStackList does not exist; combine per-stack hashes.
        int i = 1;
        for (ItemStack stack : this.stacks) {
            i = 31 * i + ItemStack.hashItemAndComponents(stack);
        }
        //?}
        i = 31 * i + 1;
        i = 31 * i + 1;
        return i;
    }

    public record Positioned(StarCrusherRecipeInput input, int left, int top) {
        public static final Positioned EMPTY =
                new Positioned(StarCrusherRecipeInput.EMPTY, 0, 0);
    }
}
