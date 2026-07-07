package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class GraveEntity extends BlockEntity {
	public Optional<String> TYPE = Optional.of("item");
	public DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

	public GraveEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.GRAVE, pos, state);
	}

	@Override
	protected void readData(ReadView nbt) {
		super.readData(nbt);
		TYPE = nbt.getOptionalString("type");
		Inventories.readData(nbt, items);

		if (world != null) {
			world.updateListeners(pos, getCachedState(), getCachedState(), 0);
		}
	}

	@Override
	protected void writeData(WriteView nbt) {
		super.writeData(nbt);
		nbt.putString("type", TYPE.orElse(null));
		Inventories.writeData(nbt, items);
	}

	public Optional<String> getNbtType() {
		return TYPE;
	}

	public void setNbtType(Optional<String> value) {
		TYPE = value;
		markDirty();
	}

	public DefaultedList<ItemStack> getNbtItems() {
		return items;
	}

	public void setNbtItems(DefaultedList<ItemStack> value) {
		items = value;
		markDirty();
	}
}