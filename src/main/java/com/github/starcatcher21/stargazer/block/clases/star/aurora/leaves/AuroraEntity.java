package com.github.starcatcher21.stargazer.block.clases.star.aurora.leaves;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AuroraEntity extends BlockEntity {
    public AuroraEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.AURORA, pos, state);
    }
}
