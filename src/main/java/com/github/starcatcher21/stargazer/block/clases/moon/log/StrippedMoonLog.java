package com.github.starcatcher21.stargazer.block.clases.moon.log;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class StrippedMoonLog extends FacingBlock {
    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return null;
    }

    public StrippedMoonLog(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(Properties.AXIS, Direction.Axis.Y));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.AXIS);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.AXIS, ctx.getSide().getAxis());
    }
}
