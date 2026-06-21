package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.screens.MoonWelderScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Map;

public class MoonWelder extends Block {
    public VoxelShape shape = VoxelShapes.union(
            VoxelShapes.cuboid(0.125, 0, 0.3125, 0.875, 0.25, 0.6875),
            VoxelShapes.cuboid(0.25, 0.25, 0.375, 0.75, 0.625, 0.625),
            VoxelShapes.cuboid(0.125, 0.625, 0.3125, 0.8125, 0.75, 0.6875),
            VoxelShapes.cuboid(0.8125, 0.625, 0.375, 0.9375, 0.75, 0.625),
            VoxelShapes.cuboid(0, 0.625, 0.375, 0.125, 0.75, 0.625),
            VoxelShapes.cuboid(0.9375, 0.625, 0.4375, 1, 0.75, 0.5625),
            VoxelShapes.cuboid(-0.0625, 0.625, 0.4375, 0, 0.75, 0.5625)
            );
    public static final MapCodec<MoonWelder> CODEC = createCodec(MoonWelder::new);
    public static final Text TITLE = Text.translatable("container.moon_welder");

    @Override
    public MapCodec<? extends MoonWelder> getCodec() {
        return CODEC;
    }

    public MoonWelder(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new MoonWelderScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), TITLE);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        Map<Direction, VoxelShape> S4MAP = VoxelShapes.createFacingShapeMap(shape);
        return S4MAP.get(dir);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing());
    }
}
