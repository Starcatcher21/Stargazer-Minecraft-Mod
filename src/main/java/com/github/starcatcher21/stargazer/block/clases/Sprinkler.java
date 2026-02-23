package com.github.starcatcher21.stargazer.block.clases;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Sprinkler extends Block {
    public Sprinkler(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.4375, 0, 0.4375, 0.5625, 0.375, 0.5625),
                VoxelShapes.cuboid(0.125, 0.375, 0.4375, 0.875, 0.5, 0.5625),
                VoxelShapes.cuboid(0.875, 0.375, 0.375, 0.9375, 0.5, 0.625),
                VoxelShapes.cuboid(0.0625, 0.375, 0.375, 0.125, 0.5, 0.625),
                VoxelShapes.cuboid(0.4375, 0.375, 0.125, 0.5625, 0.5, 0.4375),
                VoxelShapes.cuboid(0.4375, 0.375, 0.5625, 0.5625, 0.5, 0.875),
                VoxelShapes.cuboid(0.375, 0.375, 0.0625, 0.625, 0.5, 0.125),
                VoxelShapes.cuboid(0.375, 0.375, 0.875, 0.625, 0.5, 0.9375)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }
}
