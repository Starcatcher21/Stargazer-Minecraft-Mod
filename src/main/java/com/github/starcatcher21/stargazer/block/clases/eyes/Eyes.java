package com.github.starcatcher21.stargazer.block.clases.eyes;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Map;
import java.util.function.Function;

public class Eyes extends FlowerbedBlock {
    public Eyes(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(MoonBlocks.MOON_ROCK_NYLIUM) || floor.isOf(MoonBlocks.MOON_ROCK);
    }

    @Override
    public Function<BlockState, VoxelShape> createShapeFunction(EnumProperty<Direction> directionProperty, IntProperty segmentAmountProperty) {
        Map<Direction, VoxelShape> map = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, this.getHeight(), 8.0));
        return state -> {
            VoxelShape voxelShape = VoxelShapes.empty();
            int i = state.get(segmentAmountProperty);
            Direction direction = i > 1 ? (Direction)state.get(directionProperty).getOpposite() : (Direction)state.get(directionProperty).rotateYClockwise();
            for (int j = 0; j < i; ++j) {
                voxelShape = VoxelShapes.union(voxelShape, (VoxelShape)map.get(direction.rotateYCounterclockwise()));
                direction = direction.rotateYCounterclockwise();
            }
            return voxelShape.asCuboid();
        };
    }
}
