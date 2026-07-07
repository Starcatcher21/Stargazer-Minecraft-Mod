package com.github.starcatcher21.stargazer.block.clases.nored;

import java.util.EnumSet;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class NoRedBlockEntityRenderState extends BlockEntityRenderState {
    public EnumSet<Direction> sides = EnumSet.noneOf(Direction.class);
}
