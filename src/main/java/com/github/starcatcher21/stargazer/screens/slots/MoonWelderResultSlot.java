package com.github.starcatcher21.stargazer.screens.slots;

import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.UnknownNullability;

public class MoonWelderResultSlot
        extends Slot {
    private final MoonWelderRecipeInventory input;
    private final Player player;
    private int amount;

    public MoonWelderResultSlot(Player player, MoonWelderRecipeInventory input, Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
        this.input = input;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.amount += Math.min(amount, this.getItem().getCount());
        }
        return super.remove(amount);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        this.amount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void onSwapCraft(int amount) {
        this.amount += amount;
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
//        Inventory inventory;
        if (this.amount > 0) {
            stack.onCraftedBy(this.player, this.amount);
        }
        this.amount = 0;
    }

    private static NonNullList<ItemStack> copyInput(@UnknownNullability MoonWelderRecipeInput input) {
        NonNullList<ItemStack> defaultedList = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            defaultedList.set(i, input.getItem(i));
        }
        return defaultedList;
    }

    private NonNullList<ItemStack> getRecipeRemainders(MoonWelderRecipeInput input, Level world) {
        if (world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel)world;
            return serverWorld.recipeAccess().getRecipeFor(RecipeTypes.MOON_WELDER, input, serverWorld).map(recipe -> ((MoonWelderRecipe)recipe.value()).getRecipeRemainders(input)).orElseGet(() -> MoonWelderResultSlot.copyInput(input));
        }
        return MoonWelderRecipe.collectRecipeRemainders(input);
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);

        MoonWelderRecipeInput craftingRecipeInput = this.input.createRecipeInput();

        Level world = player.level();
        NonNullList<ItemStack> recipeRemainders = this.getRecipeRemainders(craftingRecipeInput, world);

        for (int i = 0; i < this.input.getContainerSize(); ++i) {
            ItemStack inputStack = this.input.getItem(i);
            if (!inputStack.isEmpty()) {
                this.input.removeItem(i, 1);

                ItemStack remainderStack = recipeRemainders.get(i);
                if (!remainderStack.isEmpty()) {
                    if (inputStack.isEmpty()) {
                        this.input.setItem(i, remainderStack);
                    } else if (ItemStack.isSameItemSameComponents(inputStack, remainderStack)) {
                        remainderStack.grow(inputStack.getCount());
                        this.input.setItem(i, remainderStack);
                    } else {
                        if (!player.getInventory().add(remainderStack)) {
                            player.drop(remainderStack, false);
                        }
                    }
                }
            }
        }
        if (player instanceof ServerPlayer spe) {
            Criterias.moonWeld.trigger(spe, stack.getItem());
        }
    }

    @Override
    public boolean isFake() {
        return true;
    }
}
