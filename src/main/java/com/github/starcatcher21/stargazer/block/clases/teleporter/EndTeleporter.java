package com.github.starcatcher21.stargazer.block.clases.teleporter;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
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
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.EndPlatformFeature;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public class EndTeleporter extends Block {
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

    public static final EnumProperty<EndTeleporterState> STATE = EnumProperty.of("state", EndTeleporterState.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public EndTeleporter(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState().with(STATE, EndTeleporterState.middle).with(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(STATE).equals(EndTeleporterState.middle)) {
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
        if (state.get(STATE).equals(EndTeleporterState.middle)) {
            breakPortal(world, pos);
        }
        if (state.get(STATE).equals(EndTeleporterState.north)) {
            BlockState state2 = world.getBlockState(pos.south());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.south)) {
            BlockState state2 = world.getBlockState(pos.north());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.west)) {
            BlockState state2 = world.getBlockState(pos.east());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.east());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.east)) {
            BlockState state2 = world.getBlockState(pos.west());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.west());
            }

        }
        if (state.get(STATE).equals(EndTeleporterState.north_west)) {
            BlockState state2 = world.getBlockState(pos.south().east());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south().east());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.north_east)) {
            BlockState state2 = world.getBlockState(pos.south().west());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south().west());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.south_west)) {
            BlockState state2 = world.getBlockState(pos.north().east());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north().east());
            }
        }
        if (state.get(STATE).equals(EndTeleporterState.south_east)) {
            BlockState state2 = world.getBlockState(pos.north().west());
            if (state2.contains(STATE) && state2.get(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north().west());
            }
        }
        return super.onBreak(world, pos, world.getBlockState(pos), player);
    }


    public void breakPortal(WorldAccess world, BlockPos root) {
        world.setBlockState(root, Blocks.END_STONE_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.north(), Blocks.END_STONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.NORTH.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.south(), Blocks.END_STONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.SOUTH.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.west(), Blocks.END_STONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.WEST.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.east(), Blocks.END_STONE_BRICK_STAIRS.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.EAST.getOpposite()), Block.NOTIFY_ALL);
        world.setBlockState(root.north().west(), Chess.WHITE_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.north().east(), Chess.WHITE_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.south().west(), Chess.WHITE_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
        world.setBlockState(root.south().east(), Chess.WHITE_BRICKS.getDefaultState(), Block.NOTIFY_ALL);
    }
    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        EndTeleporterState cts = state.get(STATE);
        if (cts.equals(EndTeleporterState.middle)) {
            return Blocks.END_STONE_BRICKS.asItem().getDefaultStack();
        }
        if (cts.equals(EndTeleporterState.north) || cts.equals(EndTeleporterState.south) || cts.equals(EndTeleporterState.west) || cts.equals(EndTeleporterState.east)) {
            return Blocks.END_STONE_BRICK_STAIRS.asItem().getDefaultStack();
        }
        return Chess.WHITE_BRICKS.asItem().getDefaultStack();
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
        if (state.get(STATE).equals(EndTeleporterState.middle)) {
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
            if (state.get(STATE).equals(EndTeleporterState.middle)) {
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
        WorldProperties.SpawnPoint spawnPoint = world.getSpawnPoint();
        RegistryKey<World> registryKey = world.getRegistryKey();
        boolean bl = registryKey == World.END;
        RegistryKey<World> registryKey2 = bl ? spawnPoint.getDimension() : World.END;
        BlockPos blockPos = bl ? spawnPoint.getPos() : ServerWorld.END_SPAWN_POS;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey2);
        if (serverWorld == null) {
            return null;
        } else {
            Vec3d vec3d = blockPos.toBottomCenterPos();
            float f;
            float g;
            Set<PositionFlag> set;
            if (!bl) {
                EndPlatformFeature.generate(serverWorld, BlockPos.ofFloored(vec3d).down(), true);
                f = Direction.WEST.getPositiveHorizontalDegrees();
                g = 0.0F;
                set = PositionFlag.combine(PositionFlag.DELTA, Set.of(PositionFlag.X_ROT));
                if (entity instanceof ServerPlayerEntity) {
                    vec3d = vec3d.subtract(0.0, 1.0, 0.0);
                }
            } else {
                f = spawnPoint.yaw();
                g = spawnPoint.pitch();
                set = PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT);
                if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                    return serverPlayerEntity.getRespawnTarget(false, TeleportTarget.NO_OP);
                }

                vec3d = entity.getWorldSpawnPos(serverWorld, blockPos).toBottomCenterPos();
            }

            return new TeleportTarget(
                    serverWorld, vec3d, Vec3d.ZERO, f, g, set, TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET)
            );
        }
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
        if (state.get(STATE).equals(EndTeleporterState.north)) {
            return SIDEMAP.get(Direction.NORTH);
        }
        if (state.get(STATE).equals(EndTeleporterState.south)) {
            return SIDEMAP.get(Direction.SOUTH);
        }
        if (state.get(STATE).equals(EndTeleporterState.east)) {
            return SIDEMAP.get(Direction.EAST);
        }
        if (state.get(STATE).equals(EndTeleporterState.west)) {
            return SIDEMAP.get(Direction.WEST);
        }
        if (state.get(STATE).equals(EndTeleporterState.north_east)) {
            return CORNERMAP.get(Direction.NORTH);
        }
        if (state.get(STATE).equals(EndTeleporterState.north_west)) {
            return CORNERMAP.get(Direction.WEST);
        }
        if (state.get(STATE).equals(EndTeleporterState.south_east)) {
            return CORNERMAP.get(Direction.EAST);
        }
        if (state.get(STATE).equals(EndTeleporterState.south_west)) {
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
                .with(STATE, EndTeleporterState.middle)
                .with(WATERLOGGED, Boolean.valueOf(fluidState.getFluid() == Fluids.WATER));
        return blockState;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        portalPlace(world, pos, false, false);
    }


    public static void portalPlace(World world, BlockPos root, Boolean isAir, Boolean isPlatform) {
        world.setBlockState(root, ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.middle));
        world.setBlockState(root.north(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.north));
        world.setBlockState(root.south(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.south));
        world.setBlockState(root.west(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.west));
        world.setBlockState(root.east(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.east));
        world.setBlockState(root.north().west(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.north_west));
        world.setBlockState(root.north().east(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.north_east));
        world.setBlockState(root.south().west(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.south_west));
        world.setBlockState(root.south().east(), ModBlock.END_TELEPORTER.getDefaultState().with(EndTeleporter.STATE, EndTeleporterState.south_east));

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
}