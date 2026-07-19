package com.github.starcatcher21.stargazer.block.clases.teleporter;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.github.starcatcher21.stargazer.particle.Particles;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
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
import java.util.Map;
import java.util.Optional;

public class CopperTeleporter extends Block {
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
    public static VoxelShape CORNERTOPSHAPE = Shapes.or(
            Shapes.box(0.6875, -0.0625, 0.1875, 0.8125, 0.875, 0.3125),
            Shapes.box(0.5, 0.875, 0, 1, 1, 0.5)
    );

    public static final EnumProperty<CopperTeleporterState> STATE = EnumProperty.create("state", CopperTeleporterState.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CopperTeleporter(Properties settings) {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(STATE, CopperTeleporterState.middle).setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (state.getValue(STATE).equals(CopperTeleporterState.middle)) {
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
        if (state.getValue(STATE).equals(CopperTeleporterState.middle)) {
            breakPortal(world, pos);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.north)) {
            BlockState state2 = world.getBlockState(pos.south());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.south());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south)) {
            BlockState state2 = world.getBlockState(pos.north());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.north());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.west)) {
            BlockState state2 = world.getBlockState(pos.east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.east());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.east)) {
            BlockState state2 = world.getBlockState(pos.west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.west());
            }

        }
        if (state.getValue(STATE).equals(CopperTeleporterState.north_west)) {
            BlockState state2 = world.getBlockState(pos.south().east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.south().east());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.north_east)) {
            BlockState state2 = world.getBlockState(pos.south().west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.south().west());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south_west)) {
            BlockState state2 = world.getBlockState(pos.north().east());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.north().east());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south_east)) {
            BlockState state2 = world.getBlockState(pos.north().west());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.north().west());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.ne_up)) {
            BlockState state2 = world.getBlockState(pos.south().west().below());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.south().west().below());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.nw_up)) {
            BlockState state2 = world.getBlockState(pos.south().east().below());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.south().east().below());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.se_up)) {
            BlockState state2 = world.getBlockState(pos.north().west().below());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.north().west().below());
            }
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.sw_up)) {
            BlockState state2 = world.getBlockState(pos.north().east().below());
            if (state2.hasProperty(STATE) && state2.getValue(STATE).equals(CopperTeleporterState.middle)) {
                breakPortal(world, pos.north().east().below());
            }
        }
        return super.playerWillDestroy(world, pos, world.getBlockState(pos), player);
    }


    public void breakPortal(LevelAccessor world, BlockPos root) {
        world.setBlock(root, Blocks.CUT_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north(), Blocks.CUT_COPPER_STAIRS.weathering().unaffected().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.south(), Blocks.CUT_COPPER_STAIRS.weathering().unaffected().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.west(), Blocks.CUT_COPPER_STAIRS.weathering().unaffected().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.east(), Blocks.CUT_COPPER_STAIRS.weathering().unaffected().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST.getOpposite()), Block.UPDATE_ALL);
        world.setBlock(root.north().west(), Blocks.CHISELED_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north().east(), Blocks.CHISELED_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().west(), Blocks.CHISELED_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().east(), Blocks.CHISELED_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north().west().above(), Blocks.CUT_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.north().east().above(), Blocks.CUT_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().west().above(), Blocks.CUT_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
        world.setBlock(root.south().east().above(), Blocks.CUT_COPPER.weathering().unaffected().defaultBlockState(), Block.UPDATE_ALL);
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
        if (state.getValue(STATE).equals(CopperTeleporterState.middle)) {
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
            if (state.getValue(STATE).equals(CopperTeleporterState.middle)) {
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
        ResourceKey<Level> registryKey = world.dimension() == CustomWorlds.COSMIC ? Level.OVERWORLD : CustomWorlds.COSMIC;
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
        return pointOfInterestStorage.getInChunk(poiType -> poiType.is(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(PointOfIntrests.COPPER_TELEPORTER).get()), chunkPos, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .filter(blockPos -> world.getBlockState(blockPos).hasProperty(STATE))
                .filter(blockPos -> world.getBlockState(blockPos).getValue(STATE).equals(CopperTeleporterState.middle))
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
        return new TeleportTransition(world, Vec3.atCenterOf(rectangle.minCorner.above()), Vec3.ZERO, entity2.getYRot(), entity2.getXRot(), Relative.union(Relative.DELTA, Relative.ROTATION), postDimensionTransition);
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
    protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        CopperTeleporterState cts = state.getValue(STATE);
        if (cts.equals(CopperTeleporterState.middle) || cts.equals(CopperTeleporterState.ne_up) || cts.equals(CopperTeleporterState.nw_up) || cts.equals(CopperTeleporterState.se_up) || cts.equals(CopperTeleporterState.sw_up)) {
            return Blocks.CUT_COPPER.weathering().unaffected().asItem().getDefaultInstance();
        }
        if (cts.equals(CopperTeleporterState.north) || cts.equals(CopperTeleporterState.south) || cts.equals(CopperTeleporterState.west) || cts.equals(CopperTeleporterState.east)) {
            return Blocks.CUT_COPPER_STAIRS.weathering().unaffected().asItem().getDefaultInstance();
        }
        return Blocks.CHISELED_COPPER.weathering().unaffected().asItem().getDefaultInstance();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return collision(state,world,pos,context);
    }

    protected VoxelShape collision(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Map<Direction, VoxelShape> SIDEMAP = Shapes.rotateAll(SIDESHAPE);
        Map<Direction, VoxelShape> CORNERMAP = Shapes.rotateAll(CORNERSHAPE);
        Map<Direction, VoxelShape> CORNERTOPMAP = Shapes.rotateAll(CORNERTOPSHAPE);
        if (state.getValue(STATE).equals(CopperTeleporterState.north)) {
            return SIDEMAP.get(Direction.NORTH);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south)) {
            return SIDEMAP.get(Direction.SOUTH);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.east)) {
            return SIDEMAP.get(Direction.EAST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.west)) {
            return SIDEMAP.get(Direction.WEST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.north_east)) {
            return CORNERMAP.get(Direction.NORTH);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.north_west)) {
            return CORNERMAP.get(Direction.WEST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south_east)) {
            return CORNERMAP.get(Direction.EAST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.south_west)) {
            return CORNERMAP.get(Direction.SOUTH);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.ne_up)) {
            return CORNERTOPMAP.get(Direction.NORTH);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.nw_up)) {
            return CORNERTOPMAP.get(Direction.WEST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.se_up)) {
            return CORNERTOPMAP.get(Direction.EAST);
        }
        if (state.getValue(STATE).equals(CopperTeleporterState.sw_up)) {
            return CORNERTOPMAP.get(Direction.SOUTH);
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
                .setValue(STATE, CopperTeleporterState.middle)
                .setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
        return blockState;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        portalPlace(world, pos, false, false);
    }


    public static void portalPlace(Level world, BlockPos root, Boolean isAir, Boolean isPlatform) {
        world.setBlockAndUpdate(root, ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.middle));
        world.setBlockAndUpdate(root.north(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.north));
        world.setBlockAndUpdate(root.south(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.south));
        world.setBlockAndUpdate(root.west(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.west));
        world.setBlockAndUpdate(root.east(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.east));
        world.setBlockAndUpdate(root.north().west(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.north_west));
        world.setBlockAndUpdate(root.north().east(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.north_east));
        world.setBlockAndUpdate(root.south().west(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.south_west));
        world.setBlockAndUpdate(root.south().east(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.south_east));
        world.setBlockAndUpdate(root.north().west().above(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.nw_up));
        world.setBlockAndUpdate(root.north().east().above(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.ne_up));
        world.setBlockAndUpdate(root.south().west().above(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.sw_up));
        world.setBlockAndUpdate(root.south().east().above(), ModBlock.COPPER_TELEPORTER.defaultBlockState().setValue(CopperTeleporter.STATE, CopperTeleporterState.se_up));

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
            checkAndPlace(world, root.below(), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(2).north(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(-1).north(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(-2).north(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(2).south(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(-1).south(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(-2).south(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(2).west(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(-1).west(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(-2).west(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(2).east(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(-1).east(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(-2).east(4), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(2).east(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(1).east(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(2).west(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(1).west(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(2).south(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(1).south(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(2).north(1), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(1).north(2), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().north(3).east(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().south(3).west(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().east(3).south(3), MoonBlocks.MOON_ROCK.defaultBlockState());
            checkAndPlace(world, root.below().west(3).north(3), MoonBlocks.MOON_ROCK.defaultBlockState());
        }
    }

    public static boolean checkAndPlace(Level world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).isAir()) {
            world.setBlockAndUpdate(pos, state);
            return true;
        }
        return false;
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
            int o;
            if (worldKey.equals(CustomWorlds.COSMIC)) {
                o = Math.max(world.getMinY() - -1, 120);
            } else {
                o = Math.max(world.getMinY() - -1, 70);
            }
            if (p < o) {
                return Optional.empty();
            }
            blockPos = new BlockPos(pos.getX(), Mth.clamp(pos.getY(), o, p), pos.getZ()).immutable();
            blockPos = worldBorder.clampToBounds(blockPos);
        }
        portalPlace(world, blockPos, true, world.getBlockState(blockPos.below()).isAir());
        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 1, 3));
    }

    private boolean isBlockStateValid(Level world, BlockPos.MutableBlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean isValidPortalPos(Level world, BlockPos pos) {
        for (int x = -1; x > 1; x++) {
            for (int z = -1; z > 1; z++) {
                for (int y = 0; y > 2; y++) {
                    if (!world.getBlockState(pos.north(x).east(z).above(y)).isAir()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}