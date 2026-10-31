package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? } else {
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
//? }

public class BlueCableEntity extends BaseCableEntity {
	public BlueCableEntity(BlockPos pos, BlockState state) {
		super(BlockTypes.BLUE_CABLE.get(), pos, state, 5000, 5000, 5000);
	}

    //? if >= 26.2 {
    /*@Override
    protected void loadAdditional(ValueInput nbt) {
        super.loadAdditional(nbt);
        energyStorage.setAmount(nbt.getLongOr("energy", 0));
    }

    @Override
    protected void saveAdditional(ValueOutput nbt) {
        super.saveAdditional(nbt);
        nbt.putLong("energy", energyStorage.getAmount());
    }
    *///?} else {
    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        // CompoundTag.getLong returns 0 when the key is absent,
        // matching getLongOr("energy", 0).
        energyStorage.setAmount(nbt.getLong("energy"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putLong("energy", energyStorage.getAmount());
    }
    //?}
}
