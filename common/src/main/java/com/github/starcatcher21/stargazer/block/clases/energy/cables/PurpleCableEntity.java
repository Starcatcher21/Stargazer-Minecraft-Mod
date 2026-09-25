package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class PurpleCableEntity extends BaseCableEntity {
	public PurpleCableEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.PURPLE_CABLE.get(), pos, state, 20000, 20000, 20000);
	}

	@Override
	protected void loadAdditional(ValueInput nbt) {
		super.loadAdditional(nbt);
		energyStorage.setAmount(nbt.getLongOr("energy", 0));
	}

	@Override
	protected void saveAdditional(ValueOutput nbt) {
		super.saveAdditional(nbt);
		nbt.putLong("energy", energyStorage.getAmount());
	}
}