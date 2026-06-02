package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipeInputs.MoonWelderInventory;
import com.github.starcatcher21.stargazer.screens.slots.MoonWelderResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MoonWelderScreenHandler
        extends ScreenHandler {

    private final ScreenHandlerContext context;
    private final PlayerEntity player;
    private boolean filling;

    protected final MoonWelderRecipeInventory craftingInventory;
    protected final CraftingResultInventory craftingResultInventory = new CraftingResultInventory();

    public MoonWelderScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public MoonWelderScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlerTypes.MOON_WELDER_HANDLER, syncId);
        this.craftingInventory = new MoonWelderInventory(this, 3);
        this.context = context;
        this.player = playerInventory.player;
        this.addResultSlot(this.player, 112, 40);
        this.addInputSlots(36, 40);
        this.addPlayerSlots(playerInventory, 8, 95);
    }

    protected static void updateResult(ScreenHandler handler, ServerWorld world, PlayerEntity player, MoonWelderRecipeInventory craftingInventory, CraftingResultInventory resultInventory, @Nullable RecipeEntry<?> recipe) {
        MoonWelderRecipeInput craftingRecipeInput = craftingInventory.createRecipeInput();
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
        ItemStack itemStack = ItemStack.EMPTY;
        Optional<RecipeEntry<MoonWelderRecipe>> optional = world.getRecipeManager().getFirstMatch
                (RecipeTypes.MOON_WELDER, craftingRecipeInput, (World)world);
        if (optional.isPresent()) {
            ItemStack itemStack2;
            RecipeEntry<MoonWelderRecipe> recipeEntry = optional.get();
            MoonWelderRecipe craftingRecipe = recipeEntry.value();
            if (resultInventory.shouldCraftRecipe(serverPlayerEntity, recipeEntry) && (itemStack2 = craftingRecipe.craft(craftingRecipeInput, world.getRegistryManager())).isItemEnabled(world.getEnabledFeatures())) {
                if (craftingRecipe.getMoonPhase() > 7 && world.isDay() && world.isSkyVisible(player.getBlockPos())) {
                    itemStack = itemStack2;
                }
                if (world.isNight() && craftingRecipe.getMoonPhase() == world.getMoonPhase() && world.isSkyVisible(player.getBlockPos())) {
                    itemStack = itemStack2;
                }
            }
        }
        resultInventory.setStack(2, itemStack);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        if (!this.filling) {
            this.context.run((world, pos) -> {
                if (world instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)world;
                    updateResult(this, serverWorld, this.player, this.craftingInventory, this.craftingResultInventory, null);
                }
            });
        }
    }

    protected Slot addResultSlot(PlayerEntity player, int x, int y) {
        return this.addSlot(new MoonWelderResultSlot(player, this.craftingInventory, this.craftingResultInventory, 2, x, y));
    }

    protected void addInputSlots(int x, int y) {
        this.addSlot(new Slot(this.craftingInventory, 0, x, y -1));
        this.addSlot(new Slot(this.craftingInventory, 1, x + 35, y -1));
    }

    public void onInputSlotFillStart() {
        this.filling = true;
    }

    public void onInputSlotFillFinish(ServerWorld world, RecipeEntry<MoonWelderRecipe> recipe) {
        this.filling = false;
        updateResult(this, world, this.player, this.craftingInventory, this.craftingResultInventory, recipe);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.craftingInventory));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlock.MOON_WELDER);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            ItemStack itemStack3 = this.getInputSlots().get(0).getStack();
            ItemStack itemStack4 = this.getInputSlots().get(1).getStack();
            if (slot == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot == 0 || slot == 1 ? !this.insertItem(itemStack2, 3, 39, false) : (itemStack3.isEmpty() || itemStack4.isEmpty() ? !this.insertItem(itemStack2, 0, 3, false) : (slot >= 3 && slot < 30 ? !this.insertItem(itemStack2, 30, 39, false) : slot >= 30 && slot < 39 && !this.insertItem(itemStack2, 3, 30, false)))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot2.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }
    protected boolean isValidIngredient(ItemStack stack) {
        return true;
    }

    public int getResultSlotIndex() {
        return 2;
    }

    private int getPlayerInventoryStartIndex() {
        return this.getResultSlotIndex() + 1;
    }

    private int getPlayerInventoryEndIndex() {
        return this.getPlayerInventoryStartIndex() + 27;
    }

    private int getPlayerHotbarStartIndex() {
        return this.getPlayerInventoryEndIndex();
    }

    private int getPlayerHotbarEndIndex() {
        return this.getPlayerHotbarStartIndex() + 9;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.craftingResultInventory && super.canInsertIntoSlot(stack, slot);
    }

    public Slot getOutputSlot() {
       return this.slots.get(2);
    }

    public List<Slot> getInputSlots() {
        return this.slots.subList(0, 2);
    }

    protected PlayerEntity getPlayer() {
        return this.player;
    }
}
