package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.HashMap;
import java.util.Map;

public class RedCable extends BaseEntityBlock {
    public static final MapCodec<RedCable> CODEC = Block.simpleCodec(RedCable::new);

    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final BooleanProperty UP = PipeBlock.UP;
    public static final BooleanProperty DOWN = PipeBlock.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES = PipeBlock.PROPERTY_BY_DIRECTION;

    private static final VoxelShape CORE = Block.box(6, 6, 6, 10, 10, 10);
    private static final Map<Direction, VoxelShape> DIRECTION_SHAPES = Util.make(new HashMap<>(), map -> {
        map.put(Direction.NORTH, Block.box(6, 6, 0, 10, 10, 6));
        map.put(Direction.SOUTH, Block.box(6, 6, 10, 10, 10, 16));
        map.put(Direction.WEST, Block.box(0, 6, 6, 6, 10, 10));
        map.put(Direction.EAST, Block.box(10, 6, 6, 16, 10, 10));
        map.put(Direction.DOWN, Block.box(6, 0, 6, 10, 6, 10));
        map.put(Direction.UP, Block.box(6, 10, 6, 10, 16, 10));
    });

    private final VoxelShape[] SHAPE_CACHE;

    public RedCable(Properties settings) {
        super(settings);
        this.SHAPE_CACHE = this.createShapeCache();

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false)
                .setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    private VoxelShape[] createShapeCache() {
        VoxelShape[] shapes = new VoxelShape[64];
        for (int i = 0; i < 64; i++) {
            VoxelShape shape = CORE;
            for (Direction direction : Direction.values()) {
                if ((i & (1 << direction.get3DDataValue())) != 0) {
                    shape = Shapes.or(shape, DIRECTION_SHAPES.get(direction));
                }
            }
            shapes[i] = shape.optimize();
        }
        return shapes;
    }

    private boolean shouldConnectTo(BlockGetter world, BlockPos currentPos, Direction direction) {
        BlockState state = world.getBlockState(currentPos);

        if (state.is(this)) {
            return true;
        }

        if (world instanceof Level actualWorld) {
            EnergyStorage storage = EnergyStorage.SIDED.find(actualWorld, currentPos, direction.getOpposite());

            return storage != null;
        }

        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockGetter world = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        BlockState state = this.defaultBlockState();

        for (Direction direction : Direction.values()) {
            boolean canConnect = this.shouldConnectTo(world, pos.relative(direction), direction);
            state = state.setValue(FACING_PROPERTIES.get(direction), canConnect);
        }
        return state;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        boolean canConnect = this.shouldConnectTo(world, neighborPos, direction);
        return state.setValue(FACING_PROPERTIES.get(direction), canConnect);
    }

    private int getShapeIndex(BlockState state) {
        int index = 0;
        for (Direction direction : Direction.values()) {
            if (state.getValue(FACING_PROPERTIES.get(direction))) {
                index |= (1 << direction.get3DDataValue());
            }
        }
        return index;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.SHAPE_CACHE[this.getShapeIndex(state)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.SHAPE_CACHE[this.getShapeIndex(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RedCableEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        // This hooks up the tick method we wrote above
        return createTickerHelper(type, BlockTypes.RED_CABLE, RedCableEntity::tick);
    }
}