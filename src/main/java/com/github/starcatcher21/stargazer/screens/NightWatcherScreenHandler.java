package com.github.starcatcher21.stargazer.screens;

import com.github.starcatcher21.stargazer.CustomTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NightWatcherScreenHandler extends AbstractContainerMenu {

    private final Player player;
    private final ContainerData propertyDelegate;
    private final Container blockInventory;

    public NightWatcherScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(1), new SimpleContainerData(4));
    }

    public NightWatcherScreenHandler(int syncId, Inventory inventory, BlockPos pos) {
        this(syncId, inventory,
                inventory.player.level().getBlockEntity(pos) instanceof Container inv ? inv : new SimpleContainer(1),
                new SimpleContainerData(4));
    }

    public NightWatcherScreenHandler(int syncId, Inventory playerInventory,
                                     Container inventory, ContainerData arrayPropertyDelegate) {
        super(ScreenHandlerTypes.NIGHTWATCHER_HANDLER, syncId);

        this.player = playerInventory.player;
        this.blockInventory = inventory;
        this.propertyDelegate = arrayPropertyDelegate;

        if (this.blockInventory != null) {
            this.blockInventory.startOpen(playerInventory.player);
        }

        this.addSlot(new Slot(blockInventory, 0, 48, 13) {
             @Override
             public boolean mayPlace(ItemStack stack) {
                 return stack.is(Items.SPYGLASS);
             }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 119 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 119 + 58));
        }

        this.addDataSlots(propertyDelegate);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();

            if (invSlot < this.blockInventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.blockInventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else {
                boolean inserted = false;

                if (this.blockInventory.getContainerSize() > 0) {
                    inserted = this.moveItemStackTo(originalStack, 0, 1, false);
                }

                if (!inserted && this.blockInventory.getContainerSize() > 2) {
                    inserted = this.moveItemStackTo(originalStack, 2, this.blockInventory.getContainerSize(), false);
                }

                if (!inserted) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockInventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.blockInventory.stopOpen(player);
    }

    public int getEnergy() {
        return this.propertyDelegate.get(0);
    }

    public int getMaxEnergy() {
        return this.propertyDelegate.get(1);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(2) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.propertyDelegate.get(2);
        int maxProgress = this.propertyDelegate.get(3); // Max Progress
        int arrowPixelSize = 56; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public float getEnergyPercent() {
        int energy = this.getEnergy();
        int maxEnergy = this.getMaxEnergy();

        if (maxEnergy == 0 || energy == 0) {
            return 0.0F;
        }
        return Mth.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }
}