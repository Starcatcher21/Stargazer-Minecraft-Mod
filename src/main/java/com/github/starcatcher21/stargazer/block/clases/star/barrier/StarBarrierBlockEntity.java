package com.github.starcatcher21.stargazer.block.clases.star.barrier;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StarBarrierBlockEntity extends BlockEntity {
    public StarBarrierBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_BARRIER_BLOCK, pos, state);
    }
}
