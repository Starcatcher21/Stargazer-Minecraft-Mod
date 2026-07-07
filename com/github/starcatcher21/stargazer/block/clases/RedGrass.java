package com.github.starcatcher21.stargazer.block.clases;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class RedGrass
        extends PlantBlock
        implements Fertilizable {
    public static final MapCodec<RedGrass> CODEC = RedGrass.createCodec(RedGrass::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(12.0, 0.0, 13.0);

    public MapCodec<RedGrass> getCodec() {
        return CODEC;
    }

    public RedGrass(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return CustomRedSapling.PLACE.contains(floor.getBlock());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
    }

}

