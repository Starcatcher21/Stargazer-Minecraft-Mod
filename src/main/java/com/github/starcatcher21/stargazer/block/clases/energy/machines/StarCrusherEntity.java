package com.github.starcatcher21.stargazer.block.clases.energy.machines;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.energy.SimpleModEnergyStorage;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarCrusherRecipeInput;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
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
import javax.annotation.Nullable;

public class StarCrusherEntity extends BlockEntity implements Container {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private int progress = 0;
    private int maxProgress = 100;
    private static final long ENERGY_PER_TICK = 5;

    public StarCrusherEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_CRUSHER.get(), pos, state);
    }

    public final SimpleModEnergyStorage energyStorage =
            new SimpleModEnergyStorage(10000, 100, 0, this::setChanged);

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
    public static void tick(Level world, BlockPos pos, BlockState state, StarCrusherEntity sge) {
        if (world.isClientSide()) return;

        if (sge.hasRecipe() && sge.hasEnoughEnergy()) {
            sge.energyStorage.setAmount(sge.energyStorage.getAmount() - ENERGY_PER_TICK);
            sge.increaseCraftingProgress();
            setChanged(world, pos, state);

            if (sge.hasCraftingFinished()) {
                sge.craftItem();
                sge.resetProgress();
            }
        } else {
            sge.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 100;
    }

    /* ---------------- crafting --------------------------------------- */
    private void craftItem() {
        Optional<RecipeHolder<StarCrusherRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().craft(
                new StarCrusherRecipeInput(List.of(inventory.get(INPUT_SLOT))),
                this.level.registryAccess());

        this.removeItem(INPUT_SLOT, 1);
        this.setItem(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getItem(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<StarCrusherRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        ItemStack output = recipe.get().value().craft(
                new StarCrusherRecipeInput(List.of(inventory.get(INPUT_SLOT))),
                this.level.registryAccess());

        return canInsertAmountIntoOutputSlot(output.getCount())
                && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<StarCrusherRecipe>> getCurrentRecipe() {
        if (!(this.getLevel() instanceof ServerLevel serverLevel)) {
            return Optional.empty();
        }
        StarCrusherRecipeInput input =
                new StarCrusherRecipeInput(List.of(inventory.get(INPUT_SLOT)));

        //? if >= 26.2 {
        /*return serverLevel.recipeAccess().getRecipeFor(
            RecipeTypes.STAR_CRUSHER.get(), input, serverLevel);
        *///?} else {
        return serverLevel.getRecipeManager().getRecipeFor(
                RecipeTypes.STAR_CRUSHER.get(), input, serverLevel);
        //?}
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
        return slot == INPUT_SLOT && stack.is(Items.SPYGLASS);
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
        setChanged();
    }

    private boolean hasEnoughEnergy() {
        return this.energyStorage.getAmount() >= ENERGY_PER_TICK;
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
                case 1 -> { /* capacity is read-only */ }
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
