package com.github.starcatcher21.stargazer.block.clases.nored;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NoRedBlockEntity extends BlockEntity {
    public NoRedBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NORED_BLOCK, pos, state);
    }
}
