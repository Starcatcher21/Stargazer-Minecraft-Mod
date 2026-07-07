package com.github.starcatcher21.stargazer.block.clases.star.border;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BorderBlock extends BaseEntityBlock {
    @Override
    protected MapCodec<? extends BorderBlock> codec() {
        return simpleCodec(BorderBlock::new);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BorderBlockEntity(pos, state);
    }

    public BorderBlock(Properties settings) {
        super(settings);
    }
}
