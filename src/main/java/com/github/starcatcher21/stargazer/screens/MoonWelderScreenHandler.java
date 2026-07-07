package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInventory;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipeInputs.MoonWelderInventory;
import com.github.starcatcher21.stargazer.screens.slots.MoonWelderResultSlot;
import net.minecraft.world.attribute.EnvironmentAttributes;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class MoonWelderScreenHandler
        extends AbstractContainerMenu {

    private final ContainerLevelAccess context;
    private final Player player;
    private boolean filling;

    protected final MoonWelderRecipeInventory craftingInventory;
    protected final ResultContainer craftingResultInventory = new ResultContainer();

    public MoonWelderScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public MoonWelderScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(ScreenHandlerTypes.MOON_WELDER_HANDLER, syncId);
        this.craftingInventory = new MoonWelderInventory(this, 3);
        this.context = context;
        this.player = playerInventory.player;
        this.addResultSlot(this.player, 112, 40);
        this.addInputSlots(36, 40);
        this.addStandardInventorySlots(playerInventory, 8, 95);
    }

    protected static void updateResult(AbstractContainerMenu handler, ServerLevel world, Player player, MoonWelderRecipeInventory craftingInventory, ResultContainer resultInventory, @Nullable RecipeHolder<?> recipe) {
        MoonWelderRecipeInput craftingRecipeInput = craftingInventory.createRecipeInput();
        ServerPlayer serverPlayerEntity = (ServerPlayer)player;
        ItemStack itemStack = ItemStack.EMPTY;
        Optional<RecipeHolder<MoonWelderRecipe>> optional = world.recipeAccess().getRecipeFor
                (RecipeTypes.MOON_WELDER, craftingRecipeInput, (Level)world);
        if (optional.isPresent()) {
            ItemStack itemStack2;
            RecipeHolder<MoonWelderRecipe> recipeEntry = optional.get();
            MoonWelderRecipe craftingRecipe = recipeEntry.value();
            if (resultInventory.setRecipeUsed(serverPlayerEntity, recipeEntry) && (itemStack2 = craftingRecipe.assemble(craftingRecipeInput, world.registryAccess())).isItemEnabled(world.enabledFeatures())) {
                if (craftingRecipe.getMoonPhase() > 7 && world.isBrightOutside() && world.canSeeSky(player.blockPosition())) {
                    itemStack = itemStack2;
                }
                EnvironmentAttributeSystem envAccess = world.environmentAttributes();
                int moonPhase = envAccess.getDimensionValue(EnvironmentAttributes.MOON_PHASE).index();
                if (world.isDarkOutside() && craftingRecipe.getMoonPhase() == moonPhase && world.canSeeSky(player.blockPosition())) {
                    itemStack = itemStack2;
                }
            }
        }
        resultInventory.setItem(2, itemStack);
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
        return this.addSlot(new MoonWelderResultSlot(player, this.craftingInventory, this.craftingResultInventory, 2, x, y));
    }

    protected void addInputSlots(int x, int y) {
        this.addSlot(new Slot(this.craftingInventory, 0, x, y -1));
        this.addSlot(new Slot(this.craftingInventory, 1, x + 35, y -1));
    }

    public void onInputSlotFillStart() {
        this.filling = true;
    }

    public void onInputSlotFillFinish(ServerLevel world, RecipeHolder<MoonWelderRecipe> recipe) {
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
        return stillValid(this.context, player, ModBlock.MOON_WELDER);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            itemStack = itemStack2.copy();
            ItemStack itemStack3 = this.getInputSlots().get(0).getItem();
            ItemStack itemStack4 = this.getInputSlots().get(1).getItem();
            if (slot == 2) {
                if (!this.moveItemStackTo(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickCraft(itemStack2, itemStack);
            } else if (slot == 0 || slot == 1 ? !this.moveItemStackTo(itemStack2, 3, 39, false) : (itemStack3.isEmpty() || itemStack4.isEmpty() ? !this.moveItemStackTo(itemStack2, 0, 3, false) : (slot >= 3 && slot < 30 ? !this.moveItemStackTo(itemStack2, 30, 39, false) : slot >= 30 && slot < 39 && !this.moveItemStackTo(itemStack2, 3, 30, false)))) {
                return ItemStack.EMPTY;
            }
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
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.craftingResultInventory && super.canTakeItemForPickAll(stack, slot);
    }

    public Slot getOutputSlot() {
       return this.slots.get(2);
    }

    public List<Slot> getInputSlots() {
        return this.slots.subList(0, 2);
    }

    protected Player getPlayer() {
        return this.player;
    }
}
