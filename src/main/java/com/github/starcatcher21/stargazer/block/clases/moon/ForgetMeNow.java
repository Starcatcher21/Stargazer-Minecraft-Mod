package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
//? if >= 26.2 {
/*import net.minecraft.world.level.block.FlowerBedBlock;
 *///?} else {
import com.github.starcatcher21.stargazer.block.older1211.FlowerBedBlock;
//?}
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ForgetMeNow extends FlowerBedBlock {
    public ForgetMeNow(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return super.getShape(state, world, pos, context);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return CustomSapling.PLACE.stream().map(Supplier::get).toList().contains(floor.getBlock());
    }

    /**
     * Manual replacement for Shapes.rotateHorizontal(VoxelShape).
     * Returns a map of the four horizontal directions to the rotated shape.
     * Rotation is (x, z) -> (1 - z, x) for each 90-degree turn.
     */
    private static Map<Direction, VoxelShape> rotateHorizontal(VoxelShape shape) {
        Map<Direction, VoxelShape> map = new HashMap<>(4);
        map.put(Direction.NORTH, shape);
        map.put(Direction.EAST,  rotateY(shape, 1));
        map.put(Direction.SOUTH, rotateY(shape, 2));
        map.put(Direction.WEST,  rotateY(shape, 3));
        return map;
    }

    private static VoxelShape rotateY(VoxelShape shape, int turns) {
        VoxelShape result = Shapes.empty();
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> {
            double nx1 = x1, nz1 = z1, nx2 = x2, nz2 = z2;
            for (int i = 0; i < turns; i++) {
                double ox1 = nx1, oz1 = nz1, ox2 = nx2, oz2 = nz2;
                nx1 = 1 - oz2;
                nz1 = ox1;
                nx2 = 1 - oz1;
                nz2 = ox2;
            }
            // `result` is effectively final per lambda invocation; accumulate below.
            // (Can't assign to outer local inside lambda, so use a single-element array.)
        });
        // The lambda above can't mutate `result`; redo with an array holder.
        VoxelShape[] holder = {Shapes.empty()};
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> {
            double nx1 = x1, nz1 = z1, nx2 = x2, nz2 = z2;
            for (int i = 0; i < turns; i++) {
                double ox1 = nx1, oz1 = nz1, ox2 = nx2, oz2 = nz2;
                nx1 = 1 - oz2;
                nz1 = ox1;
                nx2 = 1 - oz1;
                nz2 = ox2;
            }
            holder[0] = Shapes.or(holder[0], Shapes.box(
                    Math.min(nx1, nx2), y1, Math.min(nz1, nz2),
                    Math.max(nx1, nx2), y2, Math.max(nz1, nz2)
            ));
        });
        return holder[0];
    }

    @Override
    public Function<BlockState, VoxelShape> getShapeCalculator(EnumProperty<Direction> directionProperty,
                                                               IntegerProperty segmentAmountProperty) {
        Map<Direction, VoxelShape> map =
                rotateHorizontal(Block.box(0.0, 0.0, 0.0, 8.0, this.getShapeHeight(), 8.0));
        return state -> {
            VoxelShape voxelShape = Shapes.empty();
            int i = state.getValue(segmentAmountProperty);
            Direction direction = i > 1
                    ? state.getValue(directionProperty).getOpposite()
                    : state.getValue(directionProperty).getClockWise();
            for (int j = 0; j < i; ++j) {
                voxelShape = Shapes.or(voxelShape, map.get(direction.getCounterClockWise()));
                direction = direction.getCounterClockWise();
            }
            // 1.21.1 has no VoxelShape.singleEncompassing(); the OR-ed shape
            // is already a valid compound shape for rendering.
            return voxelShape;
        };
    }
}
