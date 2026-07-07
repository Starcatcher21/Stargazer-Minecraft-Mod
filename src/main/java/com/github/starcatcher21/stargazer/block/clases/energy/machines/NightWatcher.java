package com.github.starcatcher21.stargazer.block.clases.energy.machines;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.clases.energy.generators.StarGeneratorEntity;
import com.github.starcatcher21.stargazer.screens.NightWatcherScreenHandler;
import com.github.starcatcher21.stargazer.screens.StarGeneratorScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class NightWatcher extends BaseEntityBlock {
    public static final MapCodec<NightWatcher> CODEC = Block.simpleCodec(NightWatcher::new);
    public static final Component TITLE = Component.translatable("container.nightwatcher");
    public NightWatcher(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NightWatcherEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return  world.isClientSide() ? null : createTickerHelper(type, BlockTypes.NIGHT_WATCHER, NightWatcherEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
            player.openMenu(state.getMenuProvider(world, pos));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof NightWatcherEntity sge) {
            return new SimpleMenuProvider((syncId, inventory, player) -> new NightWatcherScreenHandler(syncId, inventory, sge, sge.propertyDelegate), TITLE);
        } else {
            return new SimpleMenuProvider((syncId, inventory, player) -> new NightWatcherScreenHandler(syncId, inventory, pos), TITLE);
        }
    }

}
