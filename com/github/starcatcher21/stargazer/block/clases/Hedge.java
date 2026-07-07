package com.github.starcatcher21.stargazer.block.clases;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Hedge extends Block {
    public Block leaves;
    public Hedge(Block leaves, Settings settings) {
        super(settings);
        this.leaves = leaves;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 1, 0.75);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (random.nextInt(7) != 0) return;
        Block top1 = world.getBlockState(pos.up()).getBlock();
        Block top2 = world.getBlockState(pos.up().up()).getBlock();
        if (top1.equals(Blocks.AIR)) {
            if (leaves.getDefaultState().getProperties().contains(Properties.DISTANCE_1_7)) {
                world.setBlockState(pos.up(), leaves.getDefaultState().with(Properties.DISTANCE_1_7, 1));
            } else {
                world.setBlockState(pos.up(), leaves.getDefaultState());
            }
        }
        if (top1.equals(leaves) && top2.equals(Blocks.AIR)) {
            if (leaves.getDefaultState().getProperties().contains(Properties.DISTANCE_1_7)) {
                world.setBlockState(pos.up().up(), leaves.getDefaultState().with(Properties.DISTANCE_1_7, 1));
            } else {
                world.setBlockState(pos.up().up(), leaves.getDefaultState());
            }
        }
    }
}
