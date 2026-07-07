package com.github.starcatcher21.stargazer.block.clases.star.cosmic;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CosmicBlockEntity extends BlockEntity {
    public CosmicBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.COSMIC_BLOCK, pos, state);
    }
}
