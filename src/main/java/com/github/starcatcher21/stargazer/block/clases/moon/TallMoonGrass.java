package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class TallMoonGrass
        extends TallPlantBlock {
    public static final MapCodec<TallMoonGrass> CODEC = TallMoonGrass.createCodec(TallMoonGrass::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(12.0, 0.0, 13.0);
    public static final ImmutableList<Block> PLACE = ImmutableList.of(
            MoonBlocks.MOON_ROCK_NYLIUM,
            MoonBlocks.MOON_ROCK
    );

    public MapCodec<TallMoonGrass> getCodec() {
        return CODEC;
    }

    public TallMoonGrass(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}

