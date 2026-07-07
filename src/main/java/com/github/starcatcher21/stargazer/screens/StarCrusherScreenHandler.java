package com.github.starcatcher21.stargazer.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class StarCrusherScreenHandler extends ScreenHandler {

    private final PlayerEntity player;
    private final PropertyDelegate propertyDelegate;
    private final Inventory blockInventory;

    public StarCrusherScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(4));
    }

    public StarCrusherScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory,
                inventory.player.getEntityWorld().getBlockEntity(pos) instanceof Inventory inv ? inv : new SimpleInventory(2),
                new ArrayPropertyDelegate(4));
    }

    public StarCrusherScreenHandler(int syncId, PlayerInventory playerInventory,
                                    Inventory inventory, PropertyDelegate arrayPropertyDelegate) {
        super(ScreenHandlerTypes.STARCRUSHER_HANDLER, syncId);

        this.player = playerInventory.player;
        this.blockInventory = inventory;
        this.propertyDelegate = arrayPropertyDelegate;

        if (this.blockInventory != null) {
            this.blockInventory.onOpen(playerInventory.player);
        }

        this.addSlot(new Slot(blockInventory, 0, 48, 13));
        this.addSlot(new Slot(blockInventory, 1, 48, 80) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
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

        this.addProperties(propertyDelegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot < this.blockInventory.size()) {
                if (!this.insertItem(originalStack, this.blockInventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else {
                boolean inserted = false;

                if (this.blockInventory.size() > 0) {
                    inserted = this.insertItem(originalStack, 0, 1, false);
                }

                if (!inserted && this.blockInventory.size() > 2) {
                    inserted = this.insertItem(originalStack, 2, this.blockInventory.size(), false);
                }

                if (!inserted) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.blockInventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.blockInventory.onClose(player);
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
        int arrowPixelSize = 47; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public float getEnergyPercent() {
        int energy = this.getEnergy();
        int maxEnergy = this.getMaxEnergy();

        if (maxEnergy == 0 || energy == 0) {
            return 0.0F;
        }
        return MathHelper.clamp((float) energy / (float) maxEnergy, 0.0F, 1.0F);
    }
}