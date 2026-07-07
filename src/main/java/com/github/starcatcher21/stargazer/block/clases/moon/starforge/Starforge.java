package com.github.starcatcher21.stargazer.block.clases.moon.starforge;

import com.github.starcatcher21.stargazer.screens.StarforgeScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Starforge extends Block {
    public static final MapCodec<Starforge> CODEC = simpleCodec(Starforge::new);
    public static final Component TITLE = Component.translatable("container.starforge");

    @Override
    public MapCodec<? extends Starforge> codec() {
        return CODEC;
    }

    public Starforge(BlockBehaviour.Properties settings) {
        super(settings);
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
        return new SimpleMenuProvider((syncId, inventory, player) -> new StarforgeScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), TITLE);
    }
}
