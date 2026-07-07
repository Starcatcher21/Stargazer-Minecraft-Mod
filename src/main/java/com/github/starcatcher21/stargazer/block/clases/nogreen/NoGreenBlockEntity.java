package com.github.starcatcher21.stargazer.block.clases.nogreen;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NoGreenBlockEntity extends BlockEntity {
    public NoGreenBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NOGREEN_BLOCK, pos, state);
    }
}
