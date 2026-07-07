package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class MoonFarmland extends Block {
    public static final BooleanProperty MOISTURE = BooleanProperty.of("moisture");
    public MoonFarmland(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(MOISTURE, false));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            tickView.scheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return !blockState.isSolid() || blockState.getBlock() instanceof FenceGateBlock || blockState.getBlock() instanceof PistonExtensionBlock;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createColumnShape(16.0, 0.0, 15.0);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
            return MoonBlocks.MOON_ROCK.getDefaultState();
        }
        return super.getPlacementState(ctx);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            MoonFarmland.setToRock(null, state, world, pos);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Boolean i = state.get(MOISTURE);
        if (MoonFarmland.isSprinklerNearby(world, pos)) {
            world.setBlockState(pos, (BlockState) state.with(MOISTURE, true), Block.NOTIFY_LISTENERS);
        } else if (i) {
            world.setBlockState(pos, (BlockState) state.with(MOISTURE, false), Block.NOTIFY_LISTENERS);
        } else {
            MoonFarmland.setToRock(null, state, world, pos);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public static void setToRock(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
        BlockState blockState = FarmlandBlock.pushEntitiesUpBeforeBlockChange(state, MoonBlocks.MOON_ROCK.getDefaultState(), world, pos);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity, blockState));
    }

    public static boolean isSprinklerNearby(WorldView world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, 1, -1), pos.add(1, 1, 1))) {
            if (!world.getBlockState(blockPos).getBlock().equals(ModBlock.SPRINKLER)) continue;
            return true;
        }
        return false;
    }
}
