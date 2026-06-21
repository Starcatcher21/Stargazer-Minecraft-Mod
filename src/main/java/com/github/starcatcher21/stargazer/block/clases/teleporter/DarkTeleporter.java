package com.github.starcatcher21.stargazer.block.clases.teleporter;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DarkTeleporter extends Block {
    public static VoxelShape MIDDLESHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 0, 1, 1, 0.0625),
            VoxelShapes.cuboid(0, 0, 0.9375, 1, 1, 1),
            VoxelShapes.cuboid(0, 0, 0.0625, 0.0625, 1, 0.9375),
            VoxelShapes.cuboid(0.9375, 0, 0.0625, 1, 1, 0.9375),
            VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375),
            VoxelShapes.cuboid(0.0625, 0.875, 0.0625, 0.9375, 0.9375, 0.9375),
            VoxelShapes.cuboid(0.0625, 0.9375, 0.8125, 0.1875, 1, 0.9375),
            VoxelShapes.cuboid(0.0625, 0.9375, 0.0625, 0.1875, 1, 0.1875),
            VoxelShapes.cuboid(0.8125, 0.9375, 0.0625, 0.9375, 1, 0.1875),
            VoxelShapes.cuboid(0.8125, 0.9375, 0.8125, 0.9375, 1, 0.9375)
    );
    public static VoxelShape CORNERSHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 0, 1, 0.625, 1),
            VoxelShapes.cuboid(0.25, 0.625, 0, 1, 0.9375, 0.75)
    );
    public static VoxelShape SIDESHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 0, 1, 0.3125, 1),
            VoxelShapes.cuboid(0, 0.3125, 0.3125, 1, 0.625, 1),
            VoxelShapes.cuboid(0, 0.625, 0.625, 1, 0.9375, 1)
    );

    public static final EnumProperty<DarkTeleporterState> STATE = EnumProperty.of("state", DarkTeleporterState.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public DarkTeleporter(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState().with(STATE, DarkTeleporterState.middle).with(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(STATE).equals(DarkTeleporterState.middle)) {
            for (int i = 0; i < 4; i++) {
                double d = pos.up().getX() + 0.5;
                double e = pos.up().getY();
                double f = pos.up().getZ() + 0.5;
                double g = (random.nextFloat() - 0.5) / 50.0f;
                double h = (random.nextFloat() / 50.0f);
                double j = (random.nextFloat() - 0.5) / 50.0f;

                world.addParticleClient(Particles.STAR, d, e, f, g, h, j);
            }
        }
    }


    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(STATE).equals(DarkTeleporterState.middle)) {
            breakPortal(world, pos);
        }
        if (state.get(STATE).equals(DarkTeleporterState.north)) {
            BlockState state2 = world.getBlockState(pos.south());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.south());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.south)) {
            BlockState state2 = world.getBlockState(pos.north());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.north());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.west)) {
            BlockState state2 = world.getBlockState(pos.east());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.east());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.east)) {
            BlockState state2 = world.getBlockState(pos.west());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.west());
            }

        }
        if (state.get(STATE).equals(DarkTeleporterState.north_west)) {
            BlockState state2 = world.getBlockState(pos.south().east());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.south().east());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.north_east)) {
            BlockState state2 = world.getBlockState(pos.south().west());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.south().west());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.south_west)) {
            BlockState state2 = world.getBlockState(pos.north().east());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.north().east());
            }
        }
        if (state.get(STATE).equals(DarkTeleporterState.south_east)) {
            BlockState state2 = world.getBlockState(pos.north().west());
            if (state2.contains(STATE) && state2.get(STATE).equals(DarkTeleporterState.middle)) {
                breakPortal(world, pos.north().west());
            }
        }
        return super.onBreak(world, pos, world.getBlockState(pos), player);
    }


    public void breakPortal(WorldAccess world, BlockPos root) {
        world.setBlockState(root, Blocks.OBSIDIAN.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.north(), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.NORTH.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.south(), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.SOUTH.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.west(), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.WEST.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.east(), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.EAST.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.north().west(), Chess.BLACK_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.north().east(), Chess.BLACK_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.south().west(), Chess.BLACK_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.south().east(), Chess.BLACK_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return state;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(STATE).equals(DarkTeleporterState.middle)) {
            if (world instanceof ServerWorld sw) {
                TeleportTarget target = createTeleportTarget(sw, player, pos);
                player.teleportTo(target);
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            if (state.get(STATE).equals(DarkTeleporterState.middle)) {
                if (world instanceof ServerWorld sw) {
                    TeleportTarget target = createTeleportTarget(sw, entity, pos);
                    TeleportTarget newTarget = target.withPosition(target.position().offset(Direction.NORTH, 1));
                    entity.teleportTo(newTarget);
                }
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Nullable
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        RegistryKey<World> registryKey = world.getRegistryKey() == CustomWorlds.WANDER ? World.OVERWORLD : CustomWorlds.WANDER;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        }
        WorldBorder worldBorder = serverWorld.getWorldBorder();
        return this.getOrCreateExitPortalTarget(registryKey, serverWorld, entity, pos, worldBorder);
    }

    public List<BlockPos> getPortalPos(BlockPos pos, ServerWorld world, WorldBorder worldBorder) {
        PointOfInterestStorage pointOfInterestStorage = world.getPointOfInterestStorage();
        ChunkPos chunkPos = world.getChunk(pos).getPos();
        pointOfInterestStorage.preloadChunks(world, pos, 16);
        return pointOfInterestStorage.getInChunk(poiType -> poiType.matchesKey(Registries.POINT_OF_INTEREST_TYPE.getKey(PointOfIntrests.DARK_TELEPORTER).get()), chunkPos, PointOfInterestStorage.OccupationStatus.ANY)
                .map(PointOfInterest::getPos)
                .filter(worldBorder::contains)
                .filter(blockPos -> world.getBlockState(blockPos).contains(STATE))
                .filter(blockPos -> world.getBlockState(blockPos).get(STATE).equals(DarkTeleporterState.middle))
                .toList();
    }

    private TeleportTarget getOrCreateExitPortalTarget(RegistryKey<World> worldKey, ServerWorld world, Entity entity2, BlockPos pos, WorldBorder worldBorder) {
        TeleportTarget.PostDimensionTransition postDimensionTransition;
        BlockLocating.Rectangle rectangle;
        List<BlockPos> optional = getPortalPos(pos, world, worldBorder);
        if (!optional.isEmpty()) {
            BlockPos blockPos = optional.getFirst();
            rectangle = new BlockLocating.Rectangle(blockPos, 1, 3);
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(entity -> entity.addPortalChunkTicketAt(pos));
        } else {
            if (worldKey.equals(CustomWorlds.COSMIC) && pos.getY() < 100) {
                rectangle = createPortal(worldKey, world, new BlockPos(pos.getX(), 100, pos.getZ())).get();
            } else {
                rectangle = createPortal(worldKey, world, new BlockPos(pos.getX(), 60, pos.getZ())).get();
            }
            postDimensionTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET);
        }
        return new TeleportTarget(world, rectangle.lowerLeft.up().toCenterPos(), Vec3d.ZERO, entity2.getYaw(), entity2.getPitch(), PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT), postDimensionTransition);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return collision(state,world,pos,context);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return collision(state,world,pos,context);
    }

    protected VoxelShape collision(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Map<Direction, VoxelShape> SIDEMAP = VoxelShapes.createFacingShapeMap(SIDESHAPE);
        Map<Direction, VoxelShape> CORNERMAP = VoxelShapes.createFacingShapeMap(CORNERSHAPE);
        if (state.get(STATE).equals(DarkTeleporterState.north)) {
            return SIDEMAP.get(Direction.NORTH);
        }
        if (state.get(STATE).equals(DarkTeleporterState.south)) {
            return SIDEMAP.get(Direction.SOUTH);
        }
        if (state.get(STATE).equals(DarkTeleporterState.east)) {
            return SIDEMAP.get(Direction.EAST);
        }
        if (state.get(STATE).equals(DarkTeleporterState.west)) {
            return SIDEMAP.get(Direction.WEST);
        }
        if (state.get(STATE).equals(DarkTeleporterState.north_east)) {
            return CORNERMAP.get(Direction.NORTH);
        }
        if (state.get(STATE).equals(DarkTeleporterState.north_west)) {
            return CORNERMAP.get(Direction.WEST);
        }
        if (state.get(STATE).equals(DarkTeleporterState.south_east)) {
            return CORNERMAP.get(Direction.EAST);
        }
        if (state.get(STATE).equals(DarkTeleporterState.south_west)) {
            return CORNERMAP.get(Direction.SOUTH);
        }
        return MIDDLESHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState blockState = this.getDefaultState()
                .with(STATE, DarkTeleporterState.middle)
                .with(WATERLOGGED, Boolean.valueOf(fluidState.getFluid() == Fluids.WATER));
        return blockState;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        portalPlace(world, pos, false, false);
    }


    public static void portalPlace(World world, BlockPos root, Boolean isAir, Boolean isPlatform) {
        world.setBlockState(root, ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.middle));
        world.setBlockState(root.north(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.north));
        world.setBlockState(root.south(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.south));
        world.setBlockState(root.west(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.west));
        world.setBlockState(root.east(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.east));
        world.setBlockState(root.north().west(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.north_west));
        world.setBlockState(root.north().east(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.north_east));
        world.setBlockState(root.south().west(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.south_west));
        world.setBlockState(root.south().east(), ModBlock.DARK_TELEPORTER.getDefaultState().with(DarkTeleporter.STATE, DarkTeleporterState.south_east));

        if (isAir) {
            world.setBlockState(root.up(), Blocks.AIR.getDefaultState());
            world.setBlockState(root.north().up(), Blocks.AIR.getDefaultState());
            world.setBlockState(root.south().up(), Blocks.AIR.getDefaultState());
            world.setBlockState(root.west().up(), Blocks.AIR.getDefaultState());
            world.setBlockState(root.east().up(), Blocks.AIR.getDefaultState());
            world.setBlockState(root.north().east().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.south().west().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.west().north().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.east().south().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.north().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.south().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.west().up(2), Blocks.AIR.getDefaultState());
            world.setBlockState(root.east().up(2), Blocks.AIR.getDefaultState());
        }
        if (isPlatform) {
            checkAndPlace(world, root.down(), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(1).north(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(2).north(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(-1).north(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(-2).north(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(1).south(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(2).south(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(-1).south(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(-2).south(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(1).west(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(2).west(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(-1).west(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(-2).west(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(1).east(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(2).east(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(-1).east(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(-2).east(4), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(1).east(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(2).east(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(1).east(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(1).west(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(2).west(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(1).west(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(1).south(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(2).south(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(1).south(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(1).north(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(2).north(1), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(1).north(2), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().north(3).east(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().south(3).west(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().east(3).south(3), Blocks.BLACKSTONE.getDefaultState());
            checkAndPlace(world, root.down().west(3).north(3), Blocks.BLACKSTONE.getDefaultState());
        }
    }

    public static boolean checkAndPlace(World world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(pos, state);
            return true;
        }
        return false;
    }

    public Optional<BlockLocating.Rectangle> createPortal(RegistryKey<World> worldKey, World world, BlockPos pos) {
        int n;
        int m;
        int l;
        double d = -1.0;
        BlockPos blockPos = null;
        double e = -1.0;
        BlockPos blockPos2 = null;
        WorldBorder worldBorder = world.getWorldBorder();
        int i = Math.min(world.getTopYInclusive(), world.getBottomY() + world.getHeight() - 1);
        boolean j = true;
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (BlockPos.Mutable mutable2 : BlockPos.iterateInSquare(pos, 0, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, world.getTopY(Heightmap.Type.MOTION_BLOCKING, mutable2.getX(), mutable2.getZ()));
            if (!worldBorder.contains(mutable2)) continue;
            for (l = k; l >= world.getBottomY(); --l) {
                mutable2.setY(l);
                if (!this.isBlockStateValid(world, mutable2)) continue;
                m = l;
                while (l > world.getBottomY() && this.isBlockStateValid(world, mutable2.move(Direction.DOWN))) {
                    --l;
                }
                if (l + 4 > i || (n = m - l) > 0 && n < 3) continue;
                mutable2.setY(l);
                if (!isValidPortalPos(world, mutable2)) continue;
                double f = pos.getSquaredDistance(mutable2);
                if (isValidPortalPos(world, mutable2) && isValidPortalPos(world, mutable2) && (d == -1.0 || d > f)) {
                    d = f;
                    blockPos = mutable2.toImmutable();
                }
                if (d != -1.0 || e != -1.0 && !(e > f)) continue;
                e = f;
                blockPos2 = mutable2.toImmutable();
            }
        }
        if (d == -1.0 && e != -1.0) {
            blockPos = blockPos2;
            d = e;
        }
        if (d == -1.0) {
            int p = i - 9;
            int o;
            if (worldKey.equals(CustomWorlds.COSMIC)) {
                o = Math.max(world.getBottomY() - -1, 120);
            } else {
                o = Math.max(world.getBottomY() - -1, 70);
            }
            if (p < o) {
                return Optional.empty();
            }
            blockPos = new BlockPos(pos.getX(), MathHelper.clamp(pos.getY(), o, p), pos.getZ()).toImmutable();
            blockPos = worldBorder.clampFloored(blockPos);
        }
        portalPlace(world, blockPos, true, world.getBlockState(blockPos.down()).isAir());
        return Optional.of(new BlockLocating.Rectangle(blockPos.toImmutable(), 1, 3));
    }

    private boolean isBlockStateValid(World world, BlockPos.Mutable pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isReplaceable() && blockState.getFluidState().isEmpty();
    }

    private boolean isValidPortalPos(World world, BlockPos pos) {
        for (int x = -1; x > 1; x++) {
            for (int z = -1; z > 1; z++) {
                for (int y = 0; y > 2; y++) {
                    if (!world.getBlockState(pos.north(x).east(z).up(y)).isAir()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}