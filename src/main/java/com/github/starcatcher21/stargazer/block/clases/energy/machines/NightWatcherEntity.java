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
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

import java.util.Collections;
import java.util.Random;

public class NightWatcherEntity extends BlockEntity implements Inventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
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

	public static void tick(World world, BlockPos pos, BlockState state, NightWatcherEntity sge) {
		if (world.isClient()) return;

		if (sge.hasRecipe() && sge.hasEnoughEnergy()) {

			sge.energyStorage.amount -= ENERGY_PER_TICK;

			sge.increaseCraftingProgress();
			markDirty(world, pos, state);

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
			if (this.world.getRegistryKey().equals(list.world)) {
				if (list.dayState.equals(FallingObjectDayState.Day) && this.world.isNight()) {
					continue;
				}
				if (list.dayState.equals(FallingObjectDayState.Night) && this.world.isDay()) {
					continue;
				}
				Collections.shuffle(list.weightedList);
				FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));
				star.spawn(MinecraftClient.getInstance(), this.getPos(), this.world);
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
			if (this.world.getRegistryKey().equals(list.world)) {
				if (list.dayState.equals(FallingObjectDayState.Day) && this.world.isNight()) {
					continue;
				}
				if (list.dayState.equals(FallingObjectDayState.Night) && this.world.isDay()) {
					continue;
				}
				return this.getStack(INPUT_SLOT).isOf(Items.SPYGLASS);
			}
		}
		return false;
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
		return slot == INPUT_SLOT && stack.isOf(Items.SPYGLASS);
	}

	@Override
	public void clear() {
		this.inventory.clear();
		markDirty();
	}

	private boolean hasEnoughEnergy() {
		return this.energyStorage.amount >= ENERGY_PER_TICK;
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