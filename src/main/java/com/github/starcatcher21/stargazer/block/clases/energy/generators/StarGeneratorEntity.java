package com.github.starcatcher21.stargazer.block.clases.energy.generators;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class StarGeneratorEntity extends BlockEntity implements Inventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
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
			markDirty();
		}
	};

	@Override
	protected void readData(ReadView nbt) {
		super.readData(nbt);
		Inventories.readData(nbt, this.inventory);
		energyStorage.amount = nbt.getLong("energy", 0);
		progress = nbt.getInt("progress", 0);
		maxProgress = nbt.getInt("max_progress", 0);
	}

	@Override
	protected void writeData(WriteView nbt) {
		super.writeData(nbt);
		Inventories.writeData(nbt, this.inventory, true); // Saves item inventory to NBT
		nbt.putLong("energy", energyStorage.amount);
		nbt.putInt("progress", progress);
		nbt.putInt("max_progress", maxProgress);
	}

	public static void tick(World world, BlockPos pos, BlockState state, StarGeneratorEntity sge) {
		if (world.isClient()) return;

		if(sge.hasRecipe()) {
			sge.increaseCraftingProgress();
			markDirty(world, pos, state);

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
			EnergyStorage target = EnergyStorage.SIDED.find(world, pos.offset(direction), direction.getOpposite());
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

		this.removeStack(INPUT_SLOT, 1);
		this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
				this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
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

		return this.getStack(INPUT_SLOT).isIn(CustomTags.STAR) &&
				canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output) && this.energyStorage.amount != this.energyStorage.capacity;
	}

	private boolean canInsertItemIntoOutputSlot(ItemStack output) {
		return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
	}

	private boolean canInsertAmountIntoOutputSlot(int count) {
		int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
		int currentCount = this.getStack(OUTPUT_SLOT).getCount();

		return maxCount >= currentCount + count;
	}

	@Override
	public int size() {
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
	public ItemStack getStack(int slot) {
		return this.inventory.get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
		if (!result.isEmpty()) markDirty();
		return result;
	}

	@Override
	public ItemStack removeStack(int slot) {
		return Inventories.removeStack(this.inventory, slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
		if (stack.getCount() > getMaxCountPerStack()) {
			stack.setCount(getMaxCountPerStack());
		}
		markDirty();
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return Inventory.canPlayerUse(this, player);
	}

	@Override
	public boolean isValid(int slot, ItemStack stack) {
		return slot == INPUT_SLOT && stack.isIn(CustomTags.STAR);
	}

	@Override
	public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
		return slot == OUTPUT_SLOT;
	}

	@Override
	public void clear() {
		this.inventory.clear();
		markDirty();
	}

	public final PropertyDelegate propertyDelegate = new PropertyDelegate() {
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
		public int size() {
			return 4;
		}
	};

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}
}