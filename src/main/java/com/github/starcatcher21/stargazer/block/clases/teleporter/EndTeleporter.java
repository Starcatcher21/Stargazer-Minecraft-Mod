package com.github.starcatcher21.stargazer.block.clases.teleporter;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.particle.Particles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public class EndTeleporter extends Block {
    public static VoxelShape MIDDLESHAPE = Shapes.or(
            Shapes.box(0, 0, 0, 1, 1, 0.0625),
            Shapes.box(0, 0, 0.9375, 1, 1, 1),
            Shapes.box(0, 0, 0.0625, 0.0625, 1, 0.9375),
            Shapes.box(0.9375, 0, 0.0625, 1, 1, 0.9375),
            Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375),
            Shapes.box(0.0625, 0.875, 0.0625, 0.9375, 0.9375, 0.9375),
            Shapes.box(0.0625, 0.9375, 0.8125, 0.1875, 1, 0.9375),
            Shapes.box(0.0625, 0.9375, 0.0625, 0.1875, 1, 0.1875),
            Shapes.box(0.8125, 0.9375, 0.0625, 0.9375, 1, 0.1875),
            Shapes.box(0.8125, 0.9375, 0.8125, 0.9375, 1, 0.9375)
    );
    public static VoxelShape CORNERSHAPE = Shapes.or(
            Shapes.box(0, 0, 0, 1, 0.625, 1),
            Shapes.box(0.25, 0.625, 0, 1, 0.9375, 0.75)
    );
    public static VoxelShape SIDESHAPE = Shapes.or(
            Shapes.box(0, 0, 0, 1, 0.3125, 1),
            Shapes.box(0, 0.3125, 0.3125, 1, 0.625, 1),
            Shapes.box(0, 0.625, 0.625, 1, 0.9375, 1)
    );

    public static final EnumProperty<EndTeleporterState> STATE = EnumProperty.create("state", EndTeleporterState.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public EndTeleporter(Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(STATE, EndTeleporterState.middle).setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (state.getValue(STATE).equals(EndTeleporterState.middle)) {
            for (int i = 0; i < 4; i++) {
                double d = pos.above().getX() + 0.5;
                double e = pos.above().getY();
                double f = pos.above().getZ() + 0.5;
                double g = (random.nextFloat() - 0.5) / 50.0f;
                double h = (random.nextFloat() / 50.0f);
                double j = (random.nextFloat() - 0.5) / 50.0f;

                world.addParticle(Particles.STAR, d, e, f, g, h, j);
            }
        }
    }


    @Override
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (state.getValue(STATE).equals(EndTeleporterState.middle)) {
            breakPortal(world, pos);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.north)) {
            BlockState state2 = world.getBlockState(pos.south());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south)) {
            BlockState state2 = world.getBlockState(pos.north());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.west)) {
            BlockState state2 = world.getBlockState(pos.east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.east());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.east)) {
            BlockState state2 = world.getBlockState(pos.west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.west());
            }

        }
        if (state.getValue(STATE).equals(EndTeleporterState.north_west)) {
            BlockState state2 = world.getBlockState(pos.south().east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south().east());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.north_east)) {
            BlockState state2 = world.getBlockState(pos.south().west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.south().west());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south_west)) {
            BlockState state2 = world.getBlockState(pos.north().east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north().east());
            }
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south_east)) {
            BlockState state2 = world.getBlockState(pos.north().west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(EndTeleporterState.middle)) {
                breakPortal(world, pos.north().west());
            }
        }
        return super.playerWillDestroy(world, pos, world.getBlockState(pos), player);
    }


    public void breakPortal(LevelAccessor world, BlockPos root) {
        world.setBlock(root, Blocks.END_STONE_BRICKS.defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north(), Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.south(), Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.west(), Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.east(), Blocks.END_STONE_BRICK_STAIRS.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.north().west(), Chess.WHITE_BRICKS.defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north().east(), Chess.WHITE_BRICKS.defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().west(), Chess.WHITE_BRICKS.defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().east(), Chess.WHITE_BRICKS.defaultBlockState(), Block.UPDATE_ALL);
    }
    @Override
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        EndTeleporterState cts = state.getValue(STATE);
        if (cts.equals(EndTeleporterState.middle)) {
            return Blocks.END_STONE_BRICKS.asItem().getDefaultInstance();
        }
        if (cts.equals(EndTeleporterState.north) || cts.equals(EndTeleporterState.south) || cts.equals(EndTeleporterState.west) || cts.equals(EndTeleporterState.east)) {
            return Blocks.END_STONE_BRICK_STAIRS.asItem().getDefaultInstance();
        }
        return Chess.WHITE_BRICKS.asItem().getDefaultInstance();
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
        if (state.getValue(STATE).equals(EndTeleporterState.middle)) {
            if (world instanceof ServerLevel sw) {
                TeleportTransition target = createTeleportTarget(sw, player, pos);
                player.teleport(target);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof Player)) {
            if (state.getValue(STATE).equals(EndTeleporterState.middle)) {
                if (world instanceof ServerLevel sw) {
                    TeleportTransition target = createTeleportTarget(sw, entity, pos);
                    TeleportTransition newTarget = target.withPosition(target.position().relative(Direction.NORTH, 1));
                    entity.teleport(newTarget);
                }
            }
        }
        super.stepOn(world, pos, state, entity);
    }

    @Nullable
    public TeleportTransition createTeleportTarget(ServerLevel world, Entity entity, BlockPos pos) {
        LevelData.RespawnData spawnPoint = world.getRespawnData();
        ResourceKey<Level> registryKey = world.dimension();
        boolean bl = registryKey == Level.END;
        ResourceKey<Level> registryKey2 = bl ? spawnPoint.dimension() : Level.END;
        BlockPos blockPos = bl ? spawnPoint.pos() : ServerLevel.END_SPAWN_POINT;
        ServerLevel serverWorld = world.getServer().getLevel(registryKey2);
        if (serverWorld == null) {
            return null;
        } else {
            Vec3 vec3d = blockPos.getBottomCenter();
            float f;
            float g;
            Set<Relative> set;
            if (!bl) {
                EndPlatformFeature.createEndPlatform(serverWorld, BlockPos.containing(vec3d).below(), true);
                f = Direction.WEST.toYRot();
                g = 0.0F;
                set = Relative.union(Relative.DELTA, Set.of(Relative.X_ROT));
                if (entity instanceof ServerPlayer) {
                    vec3d = vec3d.subtract(0.0, 1.0, 0.0);
                }
            } else {
                f = spawnPoint.yaw();
                g = spawnPoint.pitch();
                set = Relative.union(Relative.DELTA, Relative.ROTATION);
                if (entity instanceof ServerPlayer serverPlayerEntity) {
                    return serverPlayerEntity.findRespawnPositionAndUseSpawnBlock(false, TeleportTransition.DO_NOTHING);
                }

                vec3d = entity.adjustSpawnLocation(serverWorld, blockPos).getBottomCenter();
            }

            return new TeleportTransition(
                    serverWorld, vec3d, Vec3.ZERO, f, g, set, TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET)
            );
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return collision(state,world,pos,context);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return collision(state,world,pos,context);
    }

    protected VoxelShape collision(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Map<Direction, VoxelShape> SIDEMAP = Shapes.rotateAll(SIDESHAPE);
        Map<Direction, VoxelShape> CORNERMAP = Shapes.rotateAll(CORNERSHAPE);
        if (state.getValue(STATE).equals(EndTeleporterState.north)) {
            return SIDEMAP.get(Direction.NORTH);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south)) {
            return SIDEMAP.get(Direction.SOUTH);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.east)) {
            return SIDEMAP.get(Direction.EAST);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.west)) {
            return SIDEMAP.get(Direction.WEST);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.north_east)) {
            return CORNERMAP.get(Direction.NORTH);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.north_west)) {
            return CORNERMAP.get(Direction.WEST);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south_east)) {
            return CORNERMAP.get(Direction.EAST);
        }
        if (state.getValue(STATE).equals(EndTeleporterState.south_west)) {
            return CORNERMAP.get(Direction.SOUTH);
        }
        return MIDDLESHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        BlockState blockState = this.defaultBlockState()
                .setValue(STATE, EndTeleporterState.middle)
                .setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
        return blockState;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        portalPlace(world, pos, false, false);
    }


    public static void portalPlace(Level world, BlockPos root, Boolean isAir, Boolean isPlatform) {
        world.setBlockAndUpdate(root, ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.middle));
        world.setBlockAndUpdate(root.north(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.north));
        world.setBlockAndUpdate(root.south(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.south));
        world.setBlockAndUpdate(root.west(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.west));
        world.setBlockAndUpdate(root.east(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.east));
        world.setBlockAndUpdate(root.north().west(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.north_west));
        world.setBlockAndUpdate(root.north().east(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.north_east));
        world.setBlockAndUpdate(root.south().west(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.south_west));
        world.setBlockAndUpdate(root.south().east(), ModBlock.END_TELEPORTER.defaultBlockState().setValue(EndTeleporter.STATE, EndTeleporterState.south_east));

        if (isAir) {
            world.setBlockAndUpdate(root.above(), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.north().above(), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.south().above(), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.west().above(), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.east().above(), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.north().east().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.south().west().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.west().north().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.east().south().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.north().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.south().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.west().above(2), Blocks.AIR.defaultBlockState());
            world.setBlockAndUpdate(root.east().above(2), Blocks.AIR.defaultBlockState());
        }
        if (isPlatform) {
            checkAndPlace(world, root.below(), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(2).north(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(-1).north(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(-2).north(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(2).south(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(-1).south(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(-2).south(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(2).west(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(-1).west(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(-2).west(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(2).east(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(-1).east(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(-2).east(4), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(2).east(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(2).west(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(2).south(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(2).north(1), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(2), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().north(3).east(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().south(3).west(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().east(3).south(3), Blocks.BLACKSTONE.defaultBlockState());
            checkAndPlace(world, root.below().west(3).north(3), Blocks.BLACKSTONE.defaultBlockState());
        }
    }

    public static boolean checkAndPlace(Level world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir()) {
            world.setBlockAndUpdate(pos, state);
            return true;
        }
        return false;
    }
}