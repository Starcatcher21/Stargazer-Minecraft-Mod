//? if <= 1.21.1 {
package com.github.starcatcher21.stargazer.block.older1211;

import java.util.function.Function;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface SegmentableBlock {
    int MIN_SEGMENT = 1;
    int MAX_SEGMENT = 4;
    IntegerProperty AMOUNT = IntegerProperty.create("segment_amount", 1, 4);;

    default Function<BlockState, VoxelShape> getShapeCalculator(EnumProperty<Direction> facing, IntegerProperty amount) {
        VoxelShape shapes = Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)8.0F, this.getShapeHeight(), (double)8.0F);
        return (state) -> {
            VoxelShape shape = Shapes.empty();
            Direction direction = (Direction)state.getValue(facing);
            int count = (Integer)state.getValue(amount);

            for(int i = 0; i < count; ++i) {
                shape = Shapes.or(shape, shapes);
                direction = direction.getCounterClockWise();
            }

            return shape.singleEncompassing();
        };
    }

    default IntegerProperty getSegmentAmountProperty() {
        return AMOUNT;
    }

    default double getShapeHeight() {
        return (double)1.0F;
    }

    default boolean canBeReplaced(BlockState state, BlockPlaceContext context, IntegerProperty segment) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(state.getBlock().asItem()) && (Integer)state.getValue(segment) < 4;
    }

    default BlockState getStateForPlacement(BlockPlaceContext context, Block block, IntegerProperty segment, EnumProperty<Direction> facing) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        return state.is(block) ? (BlockState)state.setValue(segment, Math.min(4, (Integer)state.getValue(segment) + 1)) : (BlockState)block.defaultBlockState().setValue(facing, context.getHorizontalDirection().getOpposite());
    }
}
//? }
