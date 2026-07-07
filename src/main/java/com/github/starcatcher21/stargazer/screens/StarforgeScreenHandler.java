package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipeInputs.StarforgeInventory;
import com.github.starcatcher21.stargazer.screens.slots.StarforgeResultSlot;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class StarforgeScreenHandler
        extends AbstractContainerMenu {
    public static final int INPUT_SLOTS_START = 0;
    public static final int INPUT_SLOTS_END = 13;

    public static final int OUTPUT_SLOT = 14;

    public static final int PLAYER_INVENTORY_START = 15;
    public static final int PLAYER_HOTBAR_START = 15;
    public static final int PLAYER_HOTBAR_END = 23;
    public static final int PLAYER_MAIN_INVENTORY_START = 24;
    public static final int PLAYER_MAIN_INVENTORY_END = 50;
    public static final int PLAYER_INVENTORY_END = 50;

    private final ContainerLevelAccess context;
    private final Player player;
    private boolean filling;

    protected final StarforgeRecipeInventory craftingInventory;
    protected final ResultContainer craftingResultInventory = new ResultContainer();

    public StarforgeScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public StarforgeScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(ScreenHandlerTypes.STARFORGE_HANDLER, syncId);
        this.craftingInventory = new StarforgeInventory(this, 14);
        this.context = context;
        this.player = playerInventory.player;
        this.addResultSlot(this.player, 124, 35);
        this.addInputSlots(30, 32);
        this.addStandardInventorySlots(playerInventory, 8, 119);
    }

    protected static void updateResult(AbstractContainerMenu handler, ServerLevel world, Player player, StarforgeRecipeInventory craftingInventory, ResultContainer resultInventory, @Nullable RecipeHolder<?> recipe) {
        StarforgeRecipeInput craftingRecipeInput = craftingInventory.createRecipeInput();
        ServerPlayer serverPlayerEntity = (ServerPlayer)player;
        ItemStack itemStack = ItemStack.EMPTY;
        Optional<RecipeHolder<StarforgeRecipe>> optional = world.recipeAccess().getRecipeFor
                (RecipeTypes.STARFORGE, craftingRecipeInput, (Level)world);
        if (optional.isPresent()) {
            ItemStack itemStack2;
            RecipeHolder<StarforgeRecipe> recipeEntry = optional.get();
            StarforgeRecipe craftingRecipe = recipeEntry.value();
            if (resultInventory.setRecipeUsed(serverPlayerEntity, recipeEntry) && (itemStack2 = craftingRecipe.craft(craftingRecipeInput, world.registryAccess())).isItemEnabled(world.enabledFeatures())) {
                itemStack = itemStack2;
            }
        }
        resultInventory.setItem(15, itemStack);
        handler.setRemoteSlot(15, itemStack);
        serverPlayerEntity.connection.send(new ClientboundContainerSetSlotPacket(handler.containerId, handler.incrementStateId(), 15, itemStack));
    }

    @Override
    public void slotsChanged(Container inventory) {
        if (!this.filling) {
            this.context.execute((world, pos) -> {
                if (world instanceof ServerLevel) {
                    ServerLevel serverWorld = (ServerLevel)world;
                    updateResult(this, serverWorld, this.player, this.craftingInventory, this.craftingResultInventory, null);
                }
            });
        }
    }

    protected Slot addResultSlot(Player player, int x, int y) {
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

    public void onInputSlotFillFinish(ServerLevel world, RecipeHolder<StarforgeRecipe> recipe) {
        this.filling = false;
        updateResult(this, world, this.player, this.craftingInventory, this.craftingResultInventory, recipe);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.context.execute((world, pos) -> this.clearContainer(player, this.craftingInventory));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.context, player, MoonBlocks.STAR_FORGE);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);

        if (slot2 != null && slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            itemStack = itemStack2.copy();

            // 1. Logic for the Output Slot
            if (slot == OUTPUT_SLOT) {
                // Attempt to move from output to player inventory
                if (!this.moveItemStackTo(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickCraft(itemStack2, itemStack);
            }
            // 2. Logic for Player Inventory (Main + Hotbar)
            else if (slot >= PLAYER_INVENTORY_START && slot <= PLAYER_INVENTORY_END) {
                // First, try to move into the Input Slots
                if (!this.moveItemStackTo(itemStack2, INPUT_SLOTS_START, INPUT_SLOTS_END + 1, false)) {
                    // If input is full, swap between Hotbar and Main Inventory
                    if (slot < PLAYER_HOTBAR_END + 1) { // Is in Hotbar
                        if (!this.moveItemStackTo(itemStack2, PLAYER_MAIN_INVENTORY_START, PLAYER_MAIN_INVENTORY_END + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else { // Is in Main Inventory
                        if (!this.moveItemStackTo(itemStack2, PLAYER_HOTBAR_START, PLAYER_HOTBAR_END + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
            // 3. Logic for Container/Input Slots
            else if (slot >= INPUT_SLOTS_START && slot <= INPUT_SLOTS_END) {
                // Move from container back to player inventory
                if (!this.moveItemStackTo(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // Catch-all for any other slots (e.g., unexpected custom slots)
            else {
                if (!this.moveItemStackTo(itemStack2, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            // --- Post-Transfer Cleanup ---
            if (itemStack2.isEmpty()) {
                slot2.setByPlayer(ItemStack.EMPTY);
            } else {
                slot2.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.craftingResultInventory && super.canTakeItemForPickAll(stack, slot);
    }

    public Slot getOutputSlot() {
       return this.slots.get(15);
    }

    public List<Slot> getInputSlots() {
        return this.slots.subList(0, 14);
    }

    protected Player getPlayer() {
        return this.player;
    }
}
