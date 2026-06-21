package com.github.starcatcher21.stargazer.screens.slots;

import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.UnknownNullability;

public class MoonWelderResultSlot
        extends Slot {
    private final MoonWelderRecipeInventory input;
    private final PlayerEntity player;
    private int amount;

    public MoonWelderResultSlot(PlayerEntity player, MoonWelderRecipeInventory input, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
        this.input = input;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack takeStack(int amount) {
        if (this.hasStack()) {
            this.amount += Math.min(amount, this.getStack().getCount());
        }
        return super.takeStack(amount);
    }

    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        this.amount += amount;
        this.onCrafted(stack);
    }

    @Override
    protected void onTake(int amount) {
        this.amount += amount;
    }

    @Override
    protected void onCrafted(ItemStack stack) {
//        Inventory inventory;
        if (this.amount > 0) {
            stack.onCraftByPlayer(this.player, this.amount);
        }
        this.amount = 0;
    }

    private static DefaultedList<ItemStack> copyInput(@UnknownNullability MoonWelderRecipeInput input) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            defaultedList.set(i, input.getStackInSlot(i));
        }
        return defaultedList;
    }

    private DefaultedList<ItemStack> getRecipeRemainders(MoonWelderRecipeInput input, World world) {
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            return serverWorld.getRecipeManager().getFirstMatch(RecipeTypes.MOON_WELDER, input, serverWorld).map(recipe -> ((MoonWelderRecipe)recipe.value()).getRecipeRemainders(input)).orElseGet(() -> MoonWelderResultSlot.copyInput(input));
        }
        return MoonWelderRecipe.collectRecipeRemainders(input);
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);

        MoonWelderRecipeInput craftingRecipeInput = this.input.createRecipeInput();

        World world = player.getEntityWorld();
        DefaultedList<ItemStack> recipeRemainders = this.getRecipeRemainders(craftingRecipeInput, world);

        for (int i = 0; i < this.input.size(); ++i) {
            ItemStack inputStack = this.input.getStack(i);
            if (!inputStack.isEmpty()) {
                this.input.removeStack(i, 1);

                ItemStack remainderStack = recipeRemainders.get(i);
                if (!remainderStack.isEmpty()) {
                    if (inputStack.isEmpty()) {
                        this.input.setStack(i, remainderStack);
                    } else if (ItemStack.areItemsAndComponentsEqual(inputStack, remainderStack)) {
                        remainderStack.increment(inputStack.getCount());
                        this.input.setStack(i, remainderStack);
                    } else {
                        if (!player.getInventory().insertStack(remainderStack)) {
                            player.dropItem(remainderStack, false);
                        }
                    }
                }
            }
        }
        if (player instanceof ServerPlayerEntity spe) {
            Criterias.moonWeld.trigger(spe, stack.getItem());
        }
    }

    @Override
    public boolean disablesDynamicDisplay() {
        return true;
    }
}
