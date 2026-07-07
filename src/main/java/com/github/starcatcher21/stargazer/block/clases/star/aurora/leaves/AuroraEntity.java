package com.github.starcatcher21.stargazer.block.clases.star.aurora.leaves;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AuroraEntity extends BlockEntity {
    public AuroraEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.AURORA, pos, state);
    }
}
