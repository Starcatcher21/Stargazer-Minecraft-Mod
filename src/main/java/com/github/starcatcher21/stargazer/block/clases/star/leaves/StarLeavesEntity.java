package com.github.starcatcher21.stargazer.block.clases.star.leaves;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class StarLeavesEntity extends BlockEntity {
    public StarLeavesEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.STAR_LEAVES, pos, state);
    }
}
