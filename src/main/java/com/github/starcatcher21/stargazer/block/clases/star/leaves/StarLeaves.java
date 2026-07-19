package com.github.starcatcher21.stargazer.block.clases.star.leaves;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.OptionalInt;

public class StarLeaves extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<StarLeaves> CODEC = simpleCodec(StarLeaves::new);
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/block/star_leaves.png");
    protected final float leafParticleChance = 0.5F;
    public static final int MAX_DISTANCE = 7;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final int field_31112 = 1;

    @Override
    public MapCodec<? extends StarLeaves> codec() {
        return CODEC;
    }

    public StarLeaves(Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false))
        );
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(DISTANCE) == 7 && !(Boolean)state.getValue(PERSISTENT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (this.shouldDecay(state)) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    protected boolean shouldDecay(BlockState state) {
        return !(Boolean)state.getValue(PERSISTENT) && (Integer)state.getValue(DISTANCE) == 7;
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        world.setBlock(pos, updateDistanceFromLogs(state, world, pos), Block.UPDATE_ALL);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader world,
            ScheduledTickAccess tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource random
    ) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || (Integer)state.getValue(DISTANCE) != i) {
            tickView.scheduleTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, LevelAccessor world, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            mutable.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceFromLog(BlockState state) {
        return getOptionalDistanceFromLog(state).orElse(7);
    }

    public static OptionalInt getOptionalDistanceFromLog(BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return state.hasProperty(DISTANCE) ? OptionalInt.of((Integer)state.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (world.isRainingAt(pos.above())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.below();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.canOcclude() || !blockState.isFaceSturdy(world, blockPos, Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(world, pos, random, ParticleTypes.DRIPPING_WATER);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        BlockState blockState = this.defaultBlockState()
                .setValue(PERSISTENT, Boolean.valueOf(true))
                .setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
        return updateDistanceFromLogs(blockState, ctx.getLevel(), ctx.getClickedPos());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarLeavesEntity(pos, state);
    }
}
