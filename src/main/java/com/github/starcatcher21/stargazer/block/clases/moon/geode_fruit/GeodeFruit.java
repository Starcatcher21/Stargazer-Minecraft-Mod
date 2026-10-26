package com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit;

import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
//? if >= 26.2 {
/*import net.minecraft.world.level.ScheduledTickAccess;
 *///?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GeodeFruit extends Block {
    public static final EnumProperty<GeodeFruitStage> STAGE = EnumProperty.create("stage", GeodeFruitStage.class);
    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, List.of(
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST
    ));

    public static VoxelShape stage1Shape = Shapes.or(
            Shapes.box(0.4375, 0.875, 0.4375, 0.5625, 1, 0.5625)
    );
    public static VoxelShape stage2Shape = Shapes.or(
            Shapes.box(0.4375, 0.75, 0.4375, 0.5625, 1, 0.5625)
    );
    public static VoxelShape stage3Shape = Shapes.or(
            Shapes.box(0.375, 0.625, 0.375, 0.625, 1, 0.625)
    );
    public static VoxelShape stage4Shape = Shapes.or(
            Shapes.box(0.36875, 0.84375, 0.375, 0.53125, 0.90625, 0.625),
            Shapes.box(0.34375, 0.78125, 0.375, 0.625, 0.84375, 0.625),
            Shapes.box(0.31875, 0.71875, 0.375, 0.6125, 0.78125, 0.625),
            Shapes.box(0.2875, 0.65625, 0.375, 0.5875, 0.71875, 0.625),
            Shapes.box(0.26250000000000007, 0.59375, 0.375, 0.5625000000000001, 0.65625, 0.625),
            Shapes.box(0.25000000000000006, 0.53125, 0.375, 0.5312500000000002, 0.59375, 0.625),
            Shapes.box(0.33125000000000004, 0.46875, 0.375, 0.5062500000000002, 0.53125, 0.625)
    );

    public GeodeFruit(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(STAGE, GeodeFruitStage.start)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(STAGE).equals(GeodeFruitStage.start)) {
            return stage1Shape;
        } else if (state.getValue(STAGE).equals(GeodeFruitStage.middle)) {
            return stage2Shape;
        } else if (state.getValue(STAGE).equals(GeodeFruitStage.ending)) {
            return stage3Shape;
        } else {
            return switch (state.getValue(FACING)) {
                case EAST  -> rotateY(stage4Shape, 1);
                case SOUTH -> rotateY(stage4Shape, 2);
                case WEST  -> rotateY(stage4Shape, 3);
                default    -> stage4Shape; // NORTH
            };
        }
    }

    private static VoxelShape rotateY(VoxelShape shape, int turns) {
        final VoxelShape[] result = {Shapes.empty()};
        // forAllBoxes gives (minX, minY, minZ, maxX, maxY, maxZ)
        shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> {
            double nx1 = x1, nz1 = z1, nx2 = x2, nz2 = z2;
            for (int i = 0; i < turns; i++) {
                // 90° Y rotation: (x, z) -> (1 - z, x)
                double ox1 = nx1, oz1 = nz1, ox2 = nx2, oz2 = nz2;
                nx1 = 1 - oz2;   // new minX from old maxZ
                nz1 = ox1;       // new minZ from old minX
                nx2 = 1 - oz1;   // new maxX from old minZ
                nz2 = ox2;       // new maxZ from old maxX
            }
            result[0] = Shapes.box(
                    Math.min(nx1, nx2), y1, Math.min(nz1, nz2),
                    Math.max(nx1, nx2), y2, Math.max(nz1, nz2)
            );
        });
        return result[0];
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.getValue(STAGE).equals(GeodeFruitStage.grown) && random.nextInt(25) == 0) {
            GeodeFruitStage statge = nextStage(state.getValue(STAGE));
            world.setBlockAndUpdate(pos, state.setValue(STAGE, statge));
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && !state.getValue(STAGE).equals(GeodeFruitStage.grown);
    }

    public static GeodeFruitStage nextStage(GeodeFruitStage cur) {
        GeodeFruitStage nex;
        if (cur.equals(GeodeFruitStage.start)) {
            nex = GeodeFruitStage.middle;
        } else if (cur.equals(GeodeFruitStage.middle)) {
            nex = GeodeFruitStage.ending;
        } else {
            nex = GeodeFruitStage.grown;
        }
        return nex;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isFaceSturdy(world, blockPos, Direction.DOWN) || blockState.is(BlockTags.LEAVES);
    }

    /* ---------------- updateShape (signature differs) ---------------- */
    //? if >= 26.2 {
    /*@Override
    protected BlockState updateShape(BlockState state, LevelReader world,
                                     ScheduledTickAccess tickView, BlockPos pos,
                                     Direction direction, BlockPos neighborPos,
                                     BlockState neighborState, RandomSource random) {
        if (direction == Direction.UP && !this.canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }
    *///?} else {
    @Override
    protected BlockState updateShape(BlockState state, Direction direction,
                                     BlockState neighborState, LevelAccessor world,
                                     BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }
    //?}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
        builder.add(FACING);
    }
}
