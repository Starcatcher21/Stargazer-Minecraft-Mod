package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class GraveEntity extends BlockEntity {
	public Optional<String> TYPE = Optional.of("item");
	public NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

	public GraveEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.GRAVE, pos, state);
	}

	@Override
	protected void loadAdditional(ValueInput nbt) {
		super.loadAdditional(nbt);
		TYPE = nbt.getString("type");
		ContainerHelper.loadAllItems(nbt, items);

		if (level != null) {
			level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
		}
	}

	@Override
	protected void saveAdditional(ValueOutput nbt) {
		super.saveAdditional(nbt);
		nbt.putString("type", TYPE.orElse(null));
		ContainerHelper.saveAllItems(nbt, items);
	}

	public Optional<String> getNbtType() {
		return TYPE;
	}

	public void setNbtType(Optional<String> value) {
		TYPE = value;
		setChanged();
	}

	public NonNullList<ItemStack> getNbtItems() {
		return items;
	}

	public void setNbtItems(NonNullList<ItemStack> value) {
		items = value;
		setChanged();
	}
}