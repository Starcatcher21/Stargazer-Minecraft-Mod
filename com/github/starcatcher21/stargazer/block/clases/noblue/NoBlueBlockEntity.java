package com.github.starcatcher21.stargazer.block.clases.noblue;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NoBlueBlockEntity extends BlockEntity {
    public NoBlueBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NOBLUE_BLOCK, pos, state);
    }
}
