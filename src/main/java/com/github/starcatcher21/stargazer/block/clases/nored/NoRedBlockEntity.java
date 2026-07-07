package com.github.starcatcher21.stargazer.block.clases.nored;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NoRedBlockEntity extends BlockEntity {
    public NoRedBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.NORED_BLOCK, pos, state);
    }
}
