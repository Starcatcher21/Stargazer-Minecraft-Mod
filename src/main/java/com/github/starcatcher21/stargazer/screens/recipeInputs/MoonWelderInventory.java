package com.github.starcatcher21.stargazer.screens.recipeInputs;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class MoonWelderInventory
        implements MoonWelderRecipeInventory {
    private final DefaultedList<ItemStack> stacks;
    private final ScreenHandler handler;

    public MoonWelderInventory(ScreenHandler handler, int size) {
        this(handler, DefaultedList.ofSize(size, ItemStack.EMPTY));
    }

    private MoonWelderInventory(ScreenHandler handler, DefaultedList<ItemStack> stacks) {
        this.stacks = stacks;
        this.handler = handler;
    }

    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.stacks) {
            if (itemStack.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= this.size()) {
            return ItemStack.EMPTY;
        }
        return this.stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.stacks, slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);
        if (!itemStack.isEmpty()) {
            this.handler.onContentChanged(this);
        }
        return itemStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.stacks.set(slot, stack);
        this.handler.onContentChanged(this);
    }

    @Override
    public void markDirty() {
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.copyOf(this.stacks);
    }

    public MoonWelderRecipeInput createMoonRecipeInput() {
        return createPositionedRecipeInput().input();
    }

    public StarforgeRecipeInput.Positioned createStarPositionedRecipeInput() {
        return StarforgeRecipeInput.createPositioned(this.getWidth(), this.getHeight(), this.getHeldStacks());
    }

    @Override
    public void provideRecipeInputs(RecipeFinder finder) {
        for (ItemStack itemStack : this.stacks) {
            finder.addInputIfUsable(itemStack);
        }
    }
}
