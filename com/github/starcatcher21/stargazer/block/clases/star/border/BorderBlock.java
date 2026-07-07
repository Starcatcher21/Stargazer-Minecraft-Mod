package com.github.starcatcher21.stargazer.block.clases.star.border;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class BorderBlock extends BlockWithEntity {
    @Override
    protected MapCodec<? extends BorderBlock> getCodec() {
        return createCodec(BorderBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BorderBlockEntity(pos, state);
    }

    public BorderBlock(Settings settings) {
        super(settings);
    }
}
