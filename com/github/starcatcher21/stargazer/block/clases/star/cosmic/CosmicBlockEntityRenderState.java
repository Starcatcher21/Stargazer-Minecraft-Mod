package com.github.starcatcher21.stargazer.block.clases.star.cosmic;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.util.math.Direction;

import java.util.EnumSet;

public class CosmicBlockEntityRenderState extends BlockEntityRenderState {
    public EnumSet<Direction> sides = EnumSet.noneOf(Direction.class);
}
