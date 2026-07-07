package com.github.starcatcher21.stargazer.block.clases.negative;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NegativeBlockEntity extends BlockEntity {
    public NegativeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NEGATIVE_BLOCK, pos, state);
    }
}
