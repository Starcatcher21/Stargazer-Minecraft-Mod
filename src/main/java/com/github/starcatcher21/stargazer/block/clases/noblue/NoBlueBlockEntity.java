package com.github.starcatcher21.stargazer.block.clases.noblue;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NoBlueBlockEntity extends BlockEntity {
    public NoBlueBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NOBLUE_BLOCK, pos, state);
    }
}
