package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
//? if >= 26.2 {
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.ScheduledTickAccess;
//?}
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class StarTrap extends BaseEntityBlock {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public StarTrap(Properties settings) {
        super(settings.replaceable());
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    protected MapCodec<? extends StarTrap> codec() {
        return simpleCodec(StarTrap::new);
    }


    /* ---------------- updateShape (signature differs) ---------------- */
    //? if >= 26.2 {
    @Override
    protected BlockState updateShape(BlockState state, LevelReader world,
                                     ScheduledTickAccess tickView, BlockPos pos,
                                     Direction direction, BlockPos neighborPos,
                                     BlockState neighborState, RandomSource random) {
        if (direction.equals(Direction.DOWN) && !this.canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }
    //?} else {
    /*@Override
    protected BlockState updateShape(BlockState state, Direction direction,
                                     BlockState neighborState, LevelAccessor world,
                                     BlockPos pos, BlockPos neighborPos) {
        if (direction.equals(Direction.DOWN) && !this.canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    *///?}

    /* ---------------- interaction (use vs useWithoutItem) ----------- */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hitResult) {
        return handleUse(state, world, pos, player);
    }

    /** Shared body for the interaction hook on both versions. */
    private InteractionResult handleUse(BlockState state, Level world, BlockPos pos, Player player) {
        if (!player.getAbilities().mayBuild || pos.equals(player.blockPosition())) {
            return InteractionResult.PASS;
        }
        //? if >= 26.2 {
        boolean creative = player.hasInfiniteMaterials();
         //?} else {
        /*boolean creative = player.getAbilities().instabuild;
        *///?}
        if (state.getValue(ACTIVE)) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));
            if (world.getBlockEntity(pos) instanceof StarTrapEntity ste) {
                ste.setActive(false);
            }
            return InteractionResult.SUCCESS;
        } else if (creative) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            if (world.getBlockEntity(pos) instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState state2 = world.getBlockState(pos.below());
        return state2.is(MoonBlocks.MOON_ROCK_NYLIUM.get()) || state2.is(Blocks.GRASS_BLOCK);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.1, 0.0, 0.1, 0.9, 0.15, 0.9);
    }

    /* ---------------- entityInside (signature differs) --------------- */
    //? if >= 26.2 {
    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity,
                                InsideBlockEffectApplier handler, boolean bl) {
        handleEntityInside(state, world, pos, entity);
        super.entityInside(state, world, pos, entity, handler, bl);
    }
    //?} else {
    /*@Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        handleEntityInside(state, world, pos, entity);
        super.entityInside(state, world, pos, entity);
    }
    *///?}

    /** Shared body for entityInside on both versions. */
    private void handleEntityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity instanceof Player pe) {
            if (!pe.getAbilities().mayBuild) {
                return;
            }
            //? if >= 26.2 {
            if (pe.hasInfiniteMaterials()) return;
             //?} else {
            /*if (pe.getAbilities().instabuild) return;
            *///?}
        }
        if (!state.getValue(ACTIVE) && entity instanceof LivingEntity le) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            if (world.getBlockEntity(pos) instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            if (world instanceof ServerLevel sw) {
                DamageSource damageSource = new DamageSource(
                        world.registryAccess()
                                .lookupOrThrow(Registries.DAMAGE_TYPE)
                                .get(DamageTypeRegistry.STAR_TRAP).get()
                );
                //? if >= 26.2 {
                le.hurtServer(sw, damageSource, 6.0f);
                 //?} else {
                /*le.hurt(damageSource, 6.0f);
                *///?}
                if (le instanceof ServerPlayer spe) {
                    //? if >= 26.2 {
                    Criterias.STAR_TRAP.listen(crit -> crit.trigger(spe));
                    //? } else {
                    /*Criterias.STAR_TRAP.trigger(spe);
                    *///? }
                }
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarTrapEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }
}
