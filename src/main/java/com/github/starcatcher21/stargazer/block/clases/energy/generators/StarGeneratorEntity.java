package com.github.starcatcher21.stargazer.block.clases.energy.generators;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class StarGeneratorEntity extends BlockEntity implements Container {

	private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;

	private int progress = 0;
	private int maxProgress = 100;


	public StarGeneratorEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.STAR_GENERATOR, pos, state);
	}

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000, 0, 100) {
		@Override
		protected void onFinalCommit() {
			setChanged();
		}
	};

	@Override
	protected void loadAdditional(ValueInput nbt) {
		super.loadAdditional(nbt);
		ContainerHelper.loadAllItems(nbt, this.inventory);
		energyStorage.amount = nbt.getLongOr("energy", 0);
		progress = nbt.getIntOr("progress", 0);
		maxProgress = nbt.getIntOr("max_progress", 0);
	}

	@Override
	protected void saveAdditional(ValueOutput nbt) {
		super.saveAdditional(nbt);
		ContainerHelper.saveAllItems(nbt, this.inventory, true); // Saves item inventory to NBT
		nbt.putLong("energy", energyStorage.amount);
		nbt.putInt("progress", progress);
		nbt.putInt("max_progress", maxProgress);
	}

	public static void tick(Level world, BlockPos pos, BlockState state, StarGeneratorEntity sge) {
		if (world.isClientSide()) return;

		if(sge.hasRecipe()) {
			sge.increaseCraftingProgress();
			setChanged(world, pos, state);

			if(sge.hasCraftingFinished()) {
				sge.craftItem();
				sge.resetProgress();
			}
		} else {
			sge.resetProgress();
		}

		SimpleEnergyStorage source = sge.energyStorage;
		if (source.amount <= 0) return;

		for (Direction direction : Direction.values()) {
			EnergyStorage target = EnergyStorage.SIDED.find(world, pos.relative(direction), direction.getOpposite());
			if (target != null) {
				try (Transaction transaction = Transaction.openOuter()) {
					long extracted = source.extract(Long.MAX_VALUE, transaction);
					if (extracted > 0) {
						long inserted = target.insert(extracted, transaction);
						if (inserted > 0) {
							transaction.commit();
						}
					}
				}
			}
		}
	}
	private void resetProgress() {
		this.progress = 0;
		this.maxProgress = 100;
	}

	private void craftItem() {
		ItemStack output = new ItemStack(ModItems.SUPERNOVA, 1);

		this.removeItem(INPUT_SLOT, 1);
		this.setItem(OUTPUT_SLOT, new ItemStack(output.getItem(),
				this.getItem(OUTPUT_SLOT).getCount() + output.getCount()));
		this.energyStorage.amount = this.energyStorage.amount + 100 != this.energyStorage.capacity ? this.energyStorage.amount+100 : this.energyStorage.capacity;
	}

	private boolean hasCraftingFinished() {
		return this.progress >= this.maxProgress;
	}

	private void increaseCraftingProgress() {
		this.progress++;
	}

	private boolean hasRecipe() {
		ItemStack output = new ItemStack(ModItems.SUPERNOVA, 1);

		return this.getItem(INPUT_SLOT).is(CustomTags.STAR) &&
				canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output) && this.energyStorage.amount != this.energyStorage.capacity;
	}

	private boolean canInsertItemIntoOutputSlot(ItemStack output) {
		return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getItem() == output.getItem();
	}

	private boolean canInsertAmountIntoOutputSlot(int count) {
		int maxCount = this.getItem(OUTPUT_SLOT).isEmpty() ? 64 : this.getItem(OUTPUT_SLOT).getMaxStackSize();
		int currentCount = this.getItem(OUTPUT_SLOT).getCount();

		return maxCount >= currentCount + count;
	}

	@Override
	public int getContainerSize() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : this.inventory) {
			if (!itemStack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.inventory.get(slot);
	}

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

	@Override
	public boolean canTakeItem(Container hopperInventory, int slot, ItemStack stack) {
		return slot == OUTPUT_SLOT;
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
		setChanged();
	}

	public final ContainerData propertyDelegate = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
				case 0: return (int) energyStorage.amount;
				case 1: return (int) energyStorage.capacity;
				case 2: return (int) progress;
				case 3: return (int) maxProgress;
				default: return 0;
			}
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0: energyStorage.amount = value; break;
				case 1: break;
				case 2: progress = value; break;
				case 3: maxProgress = value; break;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}