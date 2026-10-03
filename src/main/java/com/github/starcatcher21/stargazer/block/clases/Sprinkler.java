package com.github.starcatcher21.stargazer.block.clases;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Sprinkler extends Block {
    public Sprinkler(Properties settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Shapes.box(0.4375, 0, 0.4375, 0.5625, 0.375, 0.5625),
                Shapes.box(0.125, 0.375, 0.4375, 0.875, 0.5, 0.5625),
                Shapes.box(0.875, 0.375, 0.375, 0.9375, 0.5, 0.625),
                Shapes.box(0.0625, 0.375, 0.375, 0.125, 0.5, 0.625),
                Shapes.box(0.4375, 0.375, 0.125, 0.5625, 0.5, 0.4375),
                Shapes.box(0.4375, 0.375, 0.5625, 0.5625, 0.5, 0.875),
                Shapes.box(0.375, 0.375, 0.0625, 0.625, 0.5, 0.125),
                Shapes.box(0.375, 0.375, 0.875, 0.625, 0.5, 0.9375)
        );
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getShape(state, world, pos, context);
    }
}
