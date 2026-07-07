package com.github.starcatcher21.stargazer.block.clases.eyes;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Eyes extends FlowerBedBlock {
    public Eyes(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return super.getShape(state, world, pos, context);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(MoonBlocks.MOON_ROCK_NYLIUM) || floor.is(MoonBlocks.MOON_ROCK);
    }

    @Override
    public Function<BlockState, VoxelShape> getShapeCalculator(EnumProperty<Direction> directionProperty, IntegerProperty segmentAmountProperty) {
        Map<Direction, VoxelShape> map = Shapes.rotateHorizontal(Block.box(0.0, 0.0, 0.0, 8.0, this.getShapeHeight(), 8.0));
        return state -> {
            VoxelShape voxelShape = Shapes.empty();
            int i = state.getValue(segmentAmountProperty);
            Direction direction = i > 1 ? (Direction)state.getValue(directionProperty).getOpposite() : (Direction)state.getValue(directionProperty).getClockWise();
            for (int j = 0; j < i; ++j) {
                voxelShape = Shapes.or(voxelShape, (VoxelShape)map.get(direction.getCounterClockWise()));
                direction = direction.getCounterClockWise();
            }
            return voxelShape.singleEncompassing();
        };
    }
}
