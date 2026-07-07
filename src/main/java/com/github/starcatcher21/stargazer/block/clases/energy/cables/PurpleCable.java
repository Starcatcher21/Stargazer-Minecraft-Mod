package com.github.starcatcher21.stargazer.block.clases.energy.cables;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.HashMap;
import java.util.Map;

public class PurpleCable extends BlockWithEntity {
    public static final MapCodec<PurpleCable> CODEC = Block.createCodec(PurpleCable::new);

    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES;

    private static final VoxelShape CORE = Block.createCuboidShape(6, 6, 6, 10, 10, 10);
    private static final Map<Direction, VoxelShape> DIRECTION_SHAPES = Util.make(new HashMap<>(), map -> {
        map.put(Direction.NORTH, Block.createCuboidShape(6, 6, 0, 10, 10, 6));
        map.put(Direction.SOUTH, Block.createCuboidShape(6, 6, 10, 10, 10, 16));
        map.put(Direction.WEST,  Block.createCuboidShape(0, 6, 6, 6, 10, 10));
        map.put(Direction.EAST,  Block.createCuboidShape(10, 6, 6, 16, 10, 10));
        map.put(Direction.DOWN,  Block.createCuboidShape(6, 0, 6, 10, 6, 10));
        map.put(Direction.UP,    Block.createCuboidShape(6, 10, 6, 10, 16, 10));
    });

    private final VoxelShape[] SHAPE_CACHE;

    public PurpleCable(Settings settings) {
        super(settings);
        this.SHAPE_CACHE = this.createShapeCache();

        this.setDefaultState(this.stateManager.getDefaultState()
                .with(NORTH, false).with(EAST, false).with(SOUTH, false)
                .with(WEST, false).with(UP, false).with(DOWN, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    private VoxelShape[] createShapeCache() {
        VoxelShape[] shapes = new VoxelShape[64];
        for (int i = 0; i < 64; i++) {
            VoxelShape shape = CORE;
            for (Direction direction : Direction.values()) {
                if ((i & (1 << direction.getIndex())) != 0) {
                    shape = VoxelShapes.union(shape, DIRECTION_SHAPES.get(direction));
                }
            }
            shapes[i] = shape.simplify();
        }
        return shapes;
    }

    private boolean shouldConnectTo(BlockView world, BlockPos currentPos, Direction direction) {
        BlockState state = world.getBlockState(currentPos);

        if (state.isOf(this)) {
            return true;
        }

        if (world instanceof World actualWorld) {
            EnergyStorage storage = EnergyStorage.SIDED.find(actualWorld, currentPos, direction.getOpposite());

            return storage != null;
        }

        return false;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = this.getDefaultState();

        for (Direction direction : Direction.values()) {
            boolean canConnect = this.shouldConnectTo(world, pos.offset(direction), direction);
            state = state.with(FACING_PROPERTIES.get(direction), canConnect);
        }
        return state;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        boolean canConnect = this.shouldConnectTo(world, neighborPos, direction);
        return state.with(FACING_PROPERTIES.get(direction), canConnect);
    }

    private int getShapeIndex(BlockState state) {
        int index = 0;
        for (Direction direction : Direction.values()) {
            if (state.get(FACING_PROPERTIES.get(direction))) {
                index |= (1 << direction.getIndex());
            }
        }
        return index;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.SHAPE_CACHE[this.getShapeIndex(state)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.SHAPE_CACHE[this.getShapeIndex(state)];
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PurpleCableEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // This hooks up the tick method we wrote above
        return validateTicker(type, BlockTypes.PURPLE_CABLE, PurpleCableEntity::tick);
    }
}