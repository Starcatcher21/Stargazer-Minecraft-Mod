package com.github.starcatcher21.stargazer.block.clases;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class Chessboard extends Block {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public Chessboard(Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE)
        );
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

        return state;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world instanceof ServerLevel sw) {
            TeleportTransition target = createTeleportTarget(sw, player, pos);
            player.teleport(target);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof Player)) {
            if (world instanceof ServerLevel sw) {
                TeleportTransition target = createTeleportTarget(sw, entity, pos);
                TeleportTransition newTarget = target.withPosition(target.position().relative(Direction.NORTH, 1));
                entity.teleport(newTarget);
            }
        }
        super.stepOn(world, pos, state, entity);
    }

    @Nullable
    public TeleportTransition createTeleportTarget(ServerLevel world, Entity entity, BlockPos pos) {
        ResourceKey<Level> registryKey = world.dimension() == CustomWorlds.CHESS ? Level.OVERWORLD : CustomWorlds.CHESS;
        ServerLevel serverWorld = world.getServer().getLevel(registryKey);
        if (serverWorld == null) {
            return null;
        }
        WorldBorder worldBorder = serverWorld.getWorldBorder();
        return this.getOrCreateExitPortalTarget(registryKey, serverWorld, entity, pos, worldBorder);
    }

    public List<BlockPos> getPortalPos(BlockPos pos, ServerLevel world, WorldBorder worldBorder) {
        PoiManager pointOfInterestStorage = world.getPoiManager();
        ChunkPos chunkPos = world.getChunk(pos).getPos();
        pointOfInterestStorage.ensureLoadedAndValid(world, pos, 16);
        return pointOfInterestStorage.getInChunk(poiType -> poiType.is(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(PointOfIntrests.CHESS_TELEPORTER).get()), chunkPos, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .toList();
    }

    private TeleportTransition getOrCreateExitPortalTarget(ResourceKey<Level> worldKey, ServerLevel world, Entity entity2, BlockPos pos, WorldBorder worldBorder) {
        TeleportTransition.PostTeleportTransition postDimensionTransition;
        BlockUtil.FoundRectangle rectangle;
        List<BlockPos> optional = getPortalPos(pos, world, worldBorder);
        if (!optional.isEmpty()) {
            BlockPos blockPos = optional.getFirst();
            rectangle = new BlockUtil.FoundRectangle(blockPos, 1, 3);
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(entity -> entity.placePortalTicket(pos));
        } else {
            if (worldKey.equals(CustomWorlds.COSMIC) && pos.getY() < 100) {
                rectangle = createPortal(worldKey, world, new BlockPos(pos.getX(), 100, pos.getZ())).get();
            } else {
                rectangle = createPortal(worldKey, world, new BlockPos(pos.getX(), 60, pos.getZ())).get();
            }
            postDimensionTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }
        return new TeleportTransition(world, rectangle.minCorner.above().getCenter(), Vec3.ZERO, entity2.getYRot(), entity2.getXRot(), Relative.union(Relative.DELTA, Relative.ROTATION), postDimensionTransition);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.box(0, 0, 0, 1, 0.125, 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        BlockState blockState = this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
        return blockState;
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(ResourceKey<Level> worldKey, Level world, BlockPos pos) {
        int n;
        int m;
        int l;
        double d = -1.0;
        BlockPos blockPos = null;
        double e = -1.0;
        BlockPos blockPos2 = null;
        WorldBorder worldBorder = world.getWorldBorder();
        int i = Math.min(world.getMaxY(), world.getMinY() + world.getHeight() - 1);
        boolean j = true;
        BlockPos.MutableBlockPos mutable = pos.mutable();
        for (BlockPos.MutableBlockPos mutable2 : BlockPos.spiralAround(pos, 0, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, world.getHeight(Heightmap.Types.MOTION_BLOCKING, mutable2.getX(), mutable2.getZ()));
            if (!worldBorder.isWithinBounds(mutable2)) continue;
            for (l = k; l >= world.getMinY(); --l) {
                mutable2.setY(l);
                if (!this.isBlockStateValid(world, mutable2)) continue;
                m = l;
                while (l > world.getMinY() && this.isBlockStateValid(world, mutable2.move(Direction.DOWN))) {
                    --l;
                }
                if (l + 4 > i || (n = m - l) > 0 && n < 3) continue;
                mutable2.setY(l);
                if (!isValidPortalPos(world, mutable2)) continue;
                double f = pos.distSqr(mutable2);
                if (isValidPortalPos(world, mutable2) && isValidPortalPos(world, mutable2) && (d == -1.0 || d > f)) {
                    d = f;
                    blockPos = mutable2.immutable();
                }
                if (d != -1.0 || e != -1.0 && !(e > f)) continue;
                e = f;
                blockPos2 = mutable2.immutable();
            }
        }
        if (d == -1.0 && e != -1.0) {
            blockPos = blockPos2;
            d = e;
        }
        if (d == -1.0) {
            int p = i - 9;
            int o = Math.max(world.getMinY() - -1, 70);
            if (p < o) {
                return Optional.empty();
            }
            blockPos = new BlockPos(pos.getX(), Mth.clamp(pos.getY(), o, p), pos.getZ()).immutable();
            blockPos = worldBorder.clampToBounds(blockPos);
        }
        if (!world.getBlockState(blockPos.below()).equals(this.defaultBlockState())) {
            world.setBlockAndUpdate(blockPos, this.defaultBlockState());
        }
        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 1, 2));
    }

    private boolean isBlockStateValid(Level world, BlockPos.MutableBlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean isValidPortalPos(Level world, BlockPos pos) {
        return world.getBlockState(pos.above()).isAir();
    }
}