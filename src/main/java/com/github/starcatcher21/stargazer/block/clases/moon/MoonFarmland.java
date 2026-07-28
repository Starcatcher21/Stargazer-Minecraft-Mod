package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MoonFarmland extends Block {
    public static final BooleanProperty MOISTURE = BooleanProperty.create("moisture");
    public MoonFarmland(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(MOISTURE, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (direction == Direction.UP && !state.canSurvive(world, pos)) {
            tickView.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.above());
        return !blockState.isSolidRender() || blockState.getBlock() instanceof FenceGateBlock || blockState.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.column(16.0, 0.0, 15.0);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getShape(state, world, pos, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        if (!this.defaultBlockState().canSurvive(ctx.getLevel(), ctx.getClickedPos())) {
            return MoonBlocks.MOON_ROCK.defaultBlockState();
        }
        return super.getStateForPlacement(ctx);
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(world, pos)) {
            MoonFarmland.setToRock(null, state, world, pos);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        Boolean i = state.getValue(MOISTURE);
        if (MoonFarmland.isSprinklerNearby(world, pos)) {
            world.setBlock(pos, (BlockState) state.setValue(MOISTURE, true), Block.UPDATE_CLIENTS);
        } else if (i) {
            world.setBlock(pos, (BlockState) state.setValue(MOISTURE, false), Block.UPDATE_CLIENTS);
        } else {
            MoonFarmland.setToRock(null, state, world, pos);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public static void setToRock(@Nullable Entity entity, BlockState state, Level world, BlockPos pos) {
        BlockState blockState = FarmlandBlock.pushEntitiesUp(state, MoonBlocks.MOON_ROCK.defaultBlockState(), world, pos);
        world.setBlockAndUpdate(pos, blockState);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState));
    }

    public static boolean isSprinklerNearby(LevelReader world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, 1, -1), pos.offset(1, 1, 1))) {
            if (!world.getBlockState(blockPos).getBlock().equals(ModBlock.SPRINKLER)) continue;
            return true;
        }
        return false;
    }
}
