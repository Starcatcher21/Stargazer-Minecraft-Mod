package com.github.starcatcher21.stargazer.block.clases.nogreen;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NoGreenBlockEntity extends BlockEntity {
    public NoGreenBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NOGREEN_BLOCK, pos, state);
    }
}
