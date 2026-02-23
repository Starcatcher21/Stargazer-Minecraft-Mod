package com.github.starcatcher21.stargazer.block.clases.star.cosmic;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CosmicBlockEntity extends BlockEntity {
    public CosmicBlockEntity(BlockPos pos, BlockState state) {
        super(BlockTypes.COSMIC_BLOCK, pos, state);
    }
}
