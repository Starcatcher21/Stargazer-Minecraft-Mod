package com.github.starcatcher21.stargazer.block.clases.crystalizer;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.recipe.CrystalizerRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.CrystalizerRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//? if >= 26.2 {
/*import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

*///?} else {
//?}

public class CrystalizerEntity extends BlockEntity {
    private static final int CRAFTING_TIME = 500;

    public NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    public int progress;

    public CrystalizerEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.CRYSTALIZER.get(), pos, state);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void sync() {
        setChanged();
        level.sendBlockUpdated(
                worldPosition,
                getBlockState(),
                getBlockState(),
                Block.UPDATE_ALL
        );
    }

    //? if >= 26.2 {
    /*@Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items.clear();
        ContainerHelper.loadAllItems(input, this.items);
        this.progress = input.getIntOr("progress", CRAFTING_TIME);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items);
        output.putInt("progress", progress);
    }
    *///?} else {
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, items, registries);

        this.progress = tag.getInt("progress");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, items, registries);
        tag.putInt("progress", progress);
    }
    //?}

    public void setProgress(int progress) {
        this.progress = progress;
        sync();
    }

    public void setInput(ItemStack value) {
        items.set(0, value.copy());
        sync();
    }
    public void setInput2(ItemStack value) {
        items.set(1, value.copy());
        sync();
    }
    public void setOutput(ItemStack value) {
        items.set(2, value.copy());
        sync();
    }
    public ItemStack getInput() {
        return items.getFirst();
    }
    public ItemStack getInput2() {
        return items.get(1);
    }
    public ItemStack getOutput() {
        return items.getLast();
    }
    public int getProgress() {
        return this.progress;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrystalizerEntity be) {
        if (level instanceof ServerLevel) {
            serverTick(level, pos, state, be);
        } else {
            clientTick(level, pos, state, be);
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, CrystalizerEntity be) {
        if (be.getProgress() == 1) {
            level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
        } else if (be.getProgress() < CRAFTING_TIME/2) {
            level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
        } else if (be.getProgress() < CRAFTING_TIME) {
            level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CrystalizerEntity be) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        ItemStack in1 = be.getInput();
        ItemStack in2 = be.getInput2();

        if (in1.isEmpty() || in2.isEmpty()) {
            if (be.getProgress() != CRAFTING_TIME) {
                be.setProgress(CRAFTING_TIME);
            }
            return;
        }

        CrystalizerRecipeInput input = new CrystalizerRecipeInput(2, 1, List.of(in1, in2));
        var recipeOpt = serverLevel
                //? if >= 26.2 {
                /*.recipeAccess()
                *///? } else {
                .getRecipeManager()
                //? }
                .getRecipeFor(RecipeTypes.CRYSTALIZER.get(), input, serverLevel);

        if (recipeOpt.isEmpty()) {
            if (be.getProgress() != CRAFTING_TIME) {
                be.setProgress(CRAFTING_TIME);
            }
            return;
        }

        CrystalizerRecipe recipe = recipeOpt.get().value();
        ItemStack result = recipe.getResult();
        ItemStack output = be.getOutput();

        if (!output.isEmpty()) {
            if (!ItemStack.isSameItemSameComponents(output, result)
                    || output.getCount() + result.getCount() > output.getMaxStackSize()) {
                if (be.getProgress() != CRAFTING_TIME) be.setProgress(CRAFTING_TIME);
                return;
            }
        }

        be.progress--;
        if (be.getProgress() <= 0) {
            be.setProgress(CRAFTING_TIME);
            be.setInput(ItemStack.EMPTY);
            be.setInput2(ItemStack.EMPTY);

            if (output.isEmpty()) {
                be.setOutput(result.copy());
            }

            be.sync();
        } else {
            be.sync();
        }
    }
}
