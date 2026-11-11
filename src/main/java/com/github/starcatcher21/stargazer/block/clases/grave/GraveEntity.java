package com.github.starcatcher21.stargazer.block.clases.grave;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >= 26.2 {
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
//?} else {
/*import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
*///?}

public class GraveEntity extends BlockEntity {
    public NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public Identifier lootTable;

    public GraveEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.GRAVE.get(), pos, state);
    }

    public boolean hasLootTable() {
        return this.lootTable != null;
    }

    //? if >= 26.2 {
    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);

        var optional = input.getString("LootTable");
        if (optional.isPresent()) {
            this.lootTable = Identifier.tryParse(optional.get());
        } else {
            this.lootTable = null;
        }

        if (level != null) {
            if (this.lootTable != null && !getBlockState().getValue(Grave.ACTIVATED)) {
                level.setBlock(worldPosition, getBlockState().setValue(Grave.ACTIVATED, true), Block.UPDATE_ALL);
                level.sendBlockUpdated(worldPosition, getBlockState().setValue(Grave.ACTIVATED, true), getBlockState().setValue(Grave.ACTIVATED, true), Block.UPDATE_ALL);
            } else {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        if (this.lootTable != null) {
            output.putString("LootTable", this.lootTable.toString());
        }
    }
    //?} else {
    /*@Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ContainerHelper.loadAllItems(tag, items, registries);
        if (tag.contains("LootTable", 8)) { // 8 = string tag
            this.lootTable = Identifier.tryParse(tag.getString("LootTable"));
        }

        if (level != null) {
            if (this.lootTable != null && !getBlockState().getValue(Grave.ACTIVATED)) {
                level.setBlock(worldPosition, getBlockState().setValue(Grave.ACTIVATED, true), Block.UPDATE_ALL);
                level.sendBlockUpdated(worldPosition, getBlockState().setValue(Grave.ACTIVATED, true), getBlockState().setValue(Grave.ACTIVATED, true), Block.UPDATE_ALL);
            } else {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 0);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, items, registries);
        if (this.lootTable != null) {
            tag.putString("LootTable", this.lootTable.toString());
        }
    }
    *///?}

    public NonNullList<ItemStack> getNbtItems() {
        return items;
    }

    public void setNbtItems(NonNullList<ItemStack> value) {
        items = value;
        setChanged();
    }

    public void generateLootIfNeeded(Level level) {
        if (this.lootTable != null && level.getServer() != null) {
            LootTable table = level.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, this.lootTable));
            if (table != null && !table.equals(LootTable.EMPTY)) {
                LootParams params = new LootParams.Builder((ServerLevel) level)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.worldPosition))
                        .withParameter(LootContextParams.BLOCK_ENTITY, this) // optional, but useful for context
                        .create(LootContextParamSets.CHEST); // or BLOCK

                // Generate items and place them into the first slot (or expand the list if needed)
                this.items = NonNullList.withSize(1, ItemStack.EMPTY);
                table.getRandomItems(params, stack -> {
                    if (this.items.get(0).isEmpty()) {
                        this.items.set(0, stack);
                    } else {
                        // If you want to support multiple items, you'd need a larger list.
                        // For simplicity, we'll just drop the extra items later.
                    }
                });
                this.setChanged();
            }
        }
    }
}
