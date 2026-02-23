package com.github.starcatcher21.stargazer.block.clases.negative;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NegativeBlockEntity extends BlockEntity {
    public NegativeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NEGATIVE_BLOCK, pos, state);
    }
}
