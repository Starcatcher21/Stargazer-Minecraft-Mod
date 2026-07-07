package com.github.starcatcher21.stargazer.block.clases;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Hedge extends Block {
    public Block leaves;
    public Hedge(Block leaves, Properties settings) {
        super(settings);
        this.leaves = leaves;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.25, 0, 0.25, 0.75, 1, 0.75);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.randomTick(state, world, pos, random);
        if (random.nextInt(7) != 0) return;
        Block top1 = world.getBlockState(pos.above()).getBlock();
        Block top2 = world.getBlockState(pos.above().above()).getBlock();
        if (top1.equals(Blocks.AIR)) {
            if (leaves.defaultBlockState().getProperties().contains(BlockStateProperties.DISTANCE)) {
                world.setBlockAndUpdate(pos.above(), leaves.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1));
            } else {
                world.setBlockAndUpdate(pos.above(), leaves.defaultBlockState());
            }
        }
        if (top1.equals(leaves) && top2.equals(Blocks.AIR)) {
            if (leaves.defaultBlockState().getProperties().contains(BlockStateProperties.DISTANCE)) {
                world.setBlockAndUpdate(pos.above().above(), leaves.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1));
            } else {
                world.setBlockAndUpdate(pos.above().above(), leaves.defaultBlockState());
            }
        }
    }
}
