package com.github.starcatcher21.stargazer.screens.recipeInputs;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class MoonWelderInventory
        implements MoonWelderRecipeInventory {
    private final NonNullList<ItemStack> stacks;
    private final AbstractContainerMenu handler;

    public MoonWelderInventory(AbstractContainerMenu handler, int size) {
        this(handler, NonNullList.withSize(size, ItemStack.EMPTY));
    }

    private MoonWelderInventory(AbstractContainerMenu handler, NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
        this.handler = handler;
    }

    @Override
    public int getContainerSize() {
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
    public ItemStack getItem(int slot) {
        if (slot >= this.getContainerSize()) {
            return ItemStack.EMPTY;
        }
        return this.stacks.get(slot);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.stacks, slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemStack = ContainerHelper.removeItem(this.stacks, slot, amount);
        if (!itemStack.isEmpty()) {
            this.handler.slotsChanged(this);
        }
        return itemStack;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.stacks.set(slot, stack);
        this.handler.slotsChanged(this);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
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
    public void fillStackedContents(StackedItemContents finder) {
        for (ItemStack itemStack : this.stacks) {
            finder.accountSimpleStack(itemStack);
        }
    }
}
