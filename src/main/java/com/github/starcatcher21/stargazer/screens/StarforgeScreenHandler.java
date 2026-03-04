package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipeInputs.StarforgeInventory;
import com.github.starcatcher21.stargazer.screens.slots.StarforgeResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class StarforgeScreenHandler
        extends ScreenHandler {
    public static final int INPUT_SLOTS_START = 0;
    public static final int INPUT_SLOTS_END = 13;

    public static final int OUTPUT_SLOT = 14;

    public static final int PLAYER_INVENTORY_START = 15;
    public static final int PLAYER_HOTBAR_START = 15;
    public static final int PLAYER_HOTBAR_END = 23;
    public static final int PLAYER_MAIN_INVENTORY_START = 24;
    public static final int PLAYER_MAIN_INVENTORY_END = 50;
    public static final int PLAYER_INVENTORY_END = 50;

    private final ScreenHandlerContext context;
    private final PlayerEntity player;
    private boolean filling;

    protected final StarforgeRecipeInventory craftingInventory;
    protected final CraftingResultInventory craftingResultInventory = new CraftingResultInventory();

    public StarforgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public StarforgeScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlerTypes.STARFORGE_HANDLER, syncId);
        this.craftingInventory = new StarforgeInventory(this, 14);
        this.context = context;
        this.player = playerInventory.player;
        this.addResultSlot(this.player, 124, 35);
        this.addInputSlots(30, 32);
        this.addPlayerSlots(playerInventory, 8, 119);
    }

    protected static void updateResult(ScreenHandler handler, ServerWorld world, PlayerEntity player, StarforgeRecipeInventory craftingInventory, CraftingResultInventory resultInventory, @Nullable RecipeEntry<?> recipe) {
        StarforgeRecipeInput craftingRecipeInput = craftingInventory.createRecipeInput();
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
        ItemStack itemStack = ItemStack.EMPTY;
        Optional<RecipeEntry<StarforgeRecipe>> optional = world.getRecipeManager().getFirstMatch
                (RecipeTypes.STARFORGE, craftingRecipeInput, (World)world);
        if (optional.isPresent()) {
            ItemStack itemStack2;
            RecipeEntry<StarforgeRecipe> recipeEntry = optional.get();
            StarforgeRecipe craftingRecipe = recipeEntry.value();
            if (resultInventory.shouldCraftRecipe(serverPlayerEntity, recipeEntry) && (itemStack2 = craftingRecipe.craft(craftingRecipeInput, world.getRegistryManager())).isItemEnabled(world.getEnabledFeatures())) {
                itemStack = itemStack2;
            }
        }
        resultInventory.setStack(15, itemStack);
        handler.setReceivedStack(15, itemStack);
        serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 15, itemStack));
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
        return this.addSlot(new StarforgeResultSlot(player, this.craftingInventory, this.craftingResultInventory, 15, x, y));
    }

    protected void addInputSlots(int x, int y) {
        this.addSlot(new Slot(this.craftingInventory, 0, x + 18, y - 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 1, x, y - 1));
        this.addSlot(new Slot(this.craftingInventory, 2, x + 18, y - 1));
        this.addSlot(new Slot(this.craftingInventory, 3, x + 2 * 18, y - 1));
        this.addSlot(new Slot(this.craftingInventory,  4, x - 18, y + 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 5, x, y + 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 6, x + 18, y + 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 7, x + 2 * 18, y + 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 8, x + 3 * 18, y + 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 9, x, y + 2 * 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 10, x + 18, y + 2 * 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 11, x + 2 * 18, y + 2 * 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 12, x + 3 * 18, y + 3 * 18 - 1));
        this.addSlot(new Slot(this.craftingInventory, 13, x - 18, y + 3 * 18 - 1));
    }

    public void onInputSlotFillStart() {
        this.filling = true;
    }

    public void onInputSlotFillFinish(ServerWorld world, RecipeEntry<StarforgeRecipe> recipe) {
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
        return canUse(this.context, player, MoonBlocks.STAR_FORGE);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);

        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();

            // 1. Logic for the Output Slot
            if (slot == OUTPUT_SLOT) {
                // Attempt to move from output to player inventory
                if (!this.insertItem(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            }
            // 2. Logic for Player Inventory (Main + Hotbar)
            else if (slot >= PLAYER_INVENTORY_START && slot <= PLAYER_INVENTORY_END) {
                // First, try to move into the Input Slots
                if (!this.insertItem(itemStack2, INPUT_SLOTS_START, INPUT_SLOTS_END + 1, false)) {
                    // If input is full, swap between Hotbar and Main Inventory
                    if (slot < PLAYER_HOTBAR_END + 1) { // Is in Hotbar
                        if (!this.insertItem(itemStack2, PLAYER_MAIN_INVENTORY_START, PLAYER_MAIN_INVENTORY_END + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else { // Is in Main Inventory
                        if (!this.insertItem(itemStack2, PLAYER_HOTBAR_START, PLAYER_HOTBAR_END + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
            // 3. Logic for Container/Input Slots
            else if (slot >= INPUT_SLOTS_START && slot <= INPUT_SLOTS_END) {
                // Move from container back to player inventory
                if (!this.insertItem(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // Catch-all for any other slots (e.g., unexpected custom slots)
            else {
                if (!this.insertItem(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            // --- Post-Transfer Cleanup ---
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

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.craftingResultInventory && super.canInsertIntoSlot(stack, slot);
    }

    public Slot getOutputSlot() {
       return this.slots.get(15);
    }

    public List<Slot> getInputSlots() {
        return this.slots.subList(0, 14);
    }

    protected PlayerEntity getPlayer() {
        return this.player;
    }
}
