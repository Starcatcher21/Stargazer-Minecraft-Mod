package com.github.starcatcher21.stargazer.block.clases.energy.machines;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGenerator;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectDayState;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Collections;
import java.util.Random;

public class NightWatcherEntity extends BlockEntity implements Container {

	private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
	private static final int INPUT_SLOT = 0;

	private int progress = 0;
	private int maxProgress = 100;
	private static final long ENERGY_PER_TICK = 5;


	public NightWatcherEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.NIGHT_WATCHER, pos, state);
	}

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000, 100, 100) {
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

	public static void tick(Level world, BlockPos pos, BlockState state, NightWatcherEntity sge) {
		if (world.isClientSide()) return;

		if (sge.hasRecipe() && sge.hasEnoughEnergy()) {

			sge.energyStorage.amount -= ENERGY_PER_TICK;

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

	private void craftItem() {
		Random random = new Random();
		for (FallingObjectsList list : FallingObjectsList.list2) {
			if (this.level.dimension().equals(list.world)) {
				if (list.dayState.equals(FallingObjectDayState.Day) && this.level.isDarkOutside()) {
					continue;
				}
				if (list.dayState.equals(FallingObjectDayState.Night) && this.level.isBrightOutside()) {
					continue;
				}
				Collections.shuffle(list.weightedList);
				FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));
				star.spawn(Minecraft.getInstance(), this.getBlockPos(), this.level);
			}
		}
	}

	private boolean hasCraftingFinished() {
		return this.progress >= this.maxProgress;
	}

	private void increaseCraftingProgress() {
		this.progress++;
	}

	private boolean hasRecipe() {
		for (FallingObjectsList list : FallingObjectsList.list2) {
			if (this.level.dimension().equals(list.world)) {
				if (list.dayState.equals(FallingObjectDayState.Day) && this.level.isDarkOutside()) {
					continue;
				}
				if (list.dayState.equals(FallingObjectDayState.Night) && this.level.isBrightOutside()) {
					continue;
				}
				return this.getItem(INPUT_SLOT).is(Items.SPYGLASS);
			}
		}
		return false;
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
		return slot == INPUT_SLOT && stack.is(Items.SPYGLASS);
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
		setChanged();
	}

	private boolean hasEnoughEnergy() {
		return this.energyStorage.amount >= ENERGY_PER_TICK;
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