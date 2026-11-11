package com.github.starcatcher21.stargazer.screens.recipe;

import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

//? if >= 26.2 {
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.level.MoonPhase;

//?} else {
//?}

public class CrystalizerRecipeInput implements RecipeInput {

    public static final CrystalizerRecipeInput EMPTY =
            new CrystalizerRecipeInput(2, 1, List.of());

    private final int width;
    private final int height;
    private final List<ItemStack> stacks;
    private final int stackCount;

    //? if >= 26.2 {
    private final StackedItemContents matcher = new StackedItemContents();
     //?} else {
    /*private final StackedContents matcher =
            new StackedContents();
    *///?}

    public CrystalizerRecipeInput(int width, int height, List<ItemStack> stacks) {
        this.width = width;
        this.height = height;
        this.stacks = stacks;
        int i = 0;
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty()) continue;
            ++i;
            //? if >= 26.2 {
            this.matcher.accountStack(itemStack, 1);
             //?} else {
            /*this.matcher.accountStack(itemStack);
            *///?}
        }
        this.stackCount = i;
    }

    public static CrystalizerRecipeInput create(int width, int height,
                                                List<ItemStack> stacks) {
        return CrystalizerRecipeInput.createPositioned(width, height, stacks).input();
    }

    public static Positioned createPositioned(int width, int height,
                                              List<ItemStack> stacks) {
        return new Positioned(new CrystalizerRecipeInput(3, 5, stacks), 3, 5);
    }

    @Override
    public ItemStack getItem(int slot) {
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

    //? if >= 26.2 {
    public StackedItemContents getRecipeMatcher() {
        return this.matcher;
    }
    //?} else {
    /*// 1.21.1 equivalent — same purpose, different type.
    public StackedContents getRecipeMatcher() {
        return this.matcher;
    }
    *///?}

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

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof CrystalizerRecipeInput other) {
            return this.width  == other.width
                    && this.height == other.height
                    && this.stackCount == other.stackCount
                    && ItemStack.listMatches(this.stacks, other.stacks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        //? if >= 26.2 {
        int i = ItemStack.hashStackList(this.stacks);
        //?} else {
        /*int i = 1;
        for (ItemStack stack : this.stacks) {
            i = 31 * i + ItemStack.hashItemAndComponents(stack);
        }
        *///?}
        i = 31 * i + this.width;
        i = 31 * i + this.height;
        return i;
    }

    public record Positioned(CrystalizerRecipeInput input, int left, int top) {
        public static final Positioned EMPTY =
                new Positioned(CrystalizerRecipeInput.EMPTY, 0, 0);
    }
}
