package com.github.starcatcher21.stargazer.block.clases.negative;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.util.math.Direction;

import java.util.EnumSet;

public class NegativeBlockEntityRenderState extends BlockEntityRenderState {
    public EnumSet<Direction> sides = EnumSet.noneOf(Direction.class);
}
