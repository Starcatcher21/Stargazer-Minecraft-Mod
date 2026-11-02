package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///?} else {
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
//?}

public class GraveEntity extends BlockEntity {
    public NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public GraveEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.GRAVE.get(), pos, state);
    }

    //? if >= 26.2 {
    /*@Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);

        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }
    *///?} else {
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ContainerHelper.loadAllItems(tag, items, registries);

        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, items, registries);
    }
    //?}

    public NonNullList<ItemStack> getNbtItems() {
        return items;
    }

    public void setNbtItems(NonNullList<ItemStack> value) {
        items = value;
        setChanged();
    }
}
