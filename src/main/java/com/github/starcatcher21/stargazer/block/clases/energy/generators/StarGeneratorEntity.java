package com.github.starcatcher21.stargazer.block.clases.energy.generators;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.energy.EnergyHooks;
import com.github.starcatcher21.stargazer.energy.SimpleModEnergyStorage;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///?} else {
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
//?}
import org.jetbrains.annotations.Nullable;

public class StarGeneratorEntity extends BlockEntity implements Container {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private int progress = 0;
    private int maxProgress = 100;

    public StarGeneratorEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_GENERATOR.get(), pos, state);
    }

    public final SimpleModEnergyStorage energyStorage =
            new SimpleModEnergyStorage(10000, 0, 100, this::setChanged);

    /* ---------------- serialization ---------------------------------- */
    //? if >= 26.2 {
    /*@Override
    protected void loadAdditional(ValueInput nbt) {
        super.loadAdditional(nbt);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        energyStorage.setAmount(nbt.getLongOr("energy", 0));
        progress    = nbt.getIntOr("progress", 0);
        maxProgress = nbt.getIntOr("max_progress", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory, true);
        nbt.putLong("energy", energyStorage.getAmount());
        nbt.putInt("progress", progress);
        nbt.putInt("max_progress", maxProgress);
    }
    *///?} else {
    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        ContainerHelper.loadAllItems(nbt, this.inventory, registries);
        // CompoundTag.getLong/getInt return 0 when the key is absent,
        // matching getLongOr/getIntOr(..., 0).
        energyStorage.setAmount(nbt.getLong("energy"));
        progress    = nbt.getInt("progress");
        maxProgress = nbt.getInt("max_progress");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        ContainerHelper.saveAllItems(nbt, this.inventory, true, registries);
        nbt.putLong("energy", energyStorage.getAmount());
        nbt.putInt("progress", progress);
        nbt.putInt("max_progress", maxProgress);
    }
    //?}

    /* ---------------- tick ------------------------------------------- */
    public static void tick(Level world, BlockPos pos, BlockState state, StarGeneratorEntity sge) {
        if (world.isClientSide()) return;

        if (sge.hasRecipe()) {
            sge.increaseCraftingProgress();
            setChanged(world, pos, state);

            if (sge.hasCraftingFinished()) {
                sge.craftItem();
                sge.resetProgress();
            }
        } else {
            sge.resetProgress();
        }

        SimpleModEnergyStorage source = sge.energyStorage;
        if (source.getAmount() <= 0) return;

        for (Direction direction : Direction.values()) {
            EnergyHooks.pushEnergy(world, pos, direction, sge.energyStorage, Long.MAX_VALUE);
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 100;
    }

    private void craftItem() {
        ItemStack output = new ItemStack(ModItems.SUPERNOVA.get(), 1);

        this.removeItem(INPUT_SLOT, 1);
        this.setItem(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getItem(OUTPUT_SLOT).getCount() + output.getCount()));

        long amount = this.energyStorage.getAmount() + 100;
        this.energyStorage.setAmount(
                amount != this.energyStorage.getCapacity()
                        ? amount
                        : this.energyStorage.getCapacity());
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        ItemStack output = new ItemStack(ModItems.SUPERNOVA.get(), 1);

        return this.getItem(INPUT_SLOT).is(CustomTags.STAR)
                && canInsertAmountIntoOutputSlot(output.getCount())
                && canInsertItemIntoOutputSlot(output)
                && this.energyStorage.getAmount() != this.energyStorage.getCapacity();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getItem(OUTPUT_SLOT).isEmpty()
                || this.getItem(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getItem(OUTPUT_SLOT).isEmpty()
                ? 64
                : this.getItem(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = this.getItem(OUTPUT_SLOT).getCount();
        return maxCount >= currentCount + count;
    }

    /* ---------------- Container impl --------------------------------- */
    @Override public int getContainerSize() { return this.inventory.size(); }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty()) return false;
        }
        return true;
    }

    @Override public ItemStack getItem(int slot) { return this.inventory.get(slot); }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(this.inventory, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot == INPUT_SLOT && stack.is(CustomTags.STAR);
    }

    //? if >= 26.2 {
    /*@Override
    public boolean canTakeItem(Container hopperInventory, int slot, ItemStack stack) {
        return slot == OUTPUT_SLOT;
    }
    *///?} else {
    // 1.21.1 Container does not declare canTakeItem; hopper extraction uses
    // canPlaceItem + slot rules instead. If you need the same behaviour,
    // override canPlaceItem on the hopper side or use a separate ItemHandler.
    //?}

    @Override
    public void clearContent() {
        this.inventory.clear();
        setChanged();
    }

    public final ContainerData propertyDelegate = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> (int) energyStorage.getAmount();
                case 1 -> (int) energyStorage.getCapacity();
                case 2 -> progress;
                case 3 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStorage.setAmount(value);
                case 2 -> progress = value;
                case 3 -> maxProgress = value;
            }
        }

        @Override public int getCount() { return 4; }
    };

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
