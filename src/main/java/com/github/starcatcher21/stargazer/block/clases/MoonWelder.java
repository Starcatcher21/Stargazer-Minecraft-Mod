package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import com.mojang.serialization.MapCodec;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MoonWelder extends Block {
    public VoxelShape shape = Shapes.or(
            Shapes.box(0.125, 0, 0.3125, 0.875, 0.25, 0.6875),
            Shapes.box(0.25, 0.25, 0.375, 0.75, 0.625, 0.625),
            Shapes.box(0.125, 0.625, 0.3125, 0.8125, 0.75, 0.6875),
            Shapes.box(0.8125, 0.625, 0.375, 0.9375, 0.75, 0.625),
            Shapes.box(0, 0.625, 0.375, 0.125, 0.75, 0.625),
            Shapes.box(0.9375, 0.625, 0.4375, 1, 0.75, 0.5625),
            Shapes.box(-0.0625, 0.625, 0.4375, 0, 0.75, 0.5625)
            );
    public static final MapCodec<MoonWelder> CODEC = simpleCodec(MoonWelder::new);
    public static final Component TITLE = Component.translatable("container.moon_welder");

    @Override
    public MapCodec<? extends MoonWelder> codec() {
        return CODEC;
    }

    public MoonWelder(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
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
        return new SimpleMenuProvider((syncId, inventory, player) -> new MoonWelderScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), TITLE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        Map<Direction, VoxelShape> S4MAP = Shapes.rotateAll(shape);
        return S4MAP.get(dir);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection());
    }
}
