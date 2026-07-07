package com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit;

import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, GeodeFruitStage.start).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Map<Direction, VoxelShape> S4MAP = Shapes.rotateAll(stage4Shape);
        if (state.getValue(STAGE).equals(GeodeFruitStage.start)) {
            return stage1Shape;
        } else if (state.getValue(STAGE).equals(GeodeFruitStage.middle)) {
            return stage2Shape;
        } else if (state.getValue(STAGE).equals(GeodeFruitStage.ending)) {
            return stage3Shape;
        } else {
            return S4MAP.get(state.getValue(FACING));
        }
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

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (direction == Direction.UP && !this.canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
        builder.add(FACING);
    }
}
