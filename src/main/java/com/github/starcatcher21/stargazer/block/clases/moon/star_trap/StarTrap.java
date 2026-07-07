package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.block.clases.CustomSapling;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
    @Override
    protected MapCodec<? extends StarTrap> codec() {
        return simpleCodec(StarTrap::new);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (direction.equals(Direction.DOWN)) {
            if (!this.canSurvive(state, world, pos)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return state;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild || pos.equals(player.blockPosition())) {
            return InteractionResult.PASS;
        }
        if (state.getValue(ACTIVE)) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(false);
            }
            return InteractionResult.SUCCESS;
        } else if (player.hasInfiniteMaterials()) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState state2 = world.getBlockState(pos.below());
        return CustomSapling.PLACE.contains(state2.getBlock());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.1, 0.0, 0.1, 0.9, 0.15, 0.9);
    }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        if (entity instanceof Player pe) {
            if (!pe.getAbilities().mayBuild && pe.gameMode() != GameType.ADVENTURE) {
                return;
            }
            if (pe.hasInfiniteMaterials()) {
                return;
            }
        }
        if (!state.getValue(ACTIVE) && entity instanceof LivingEntity le) {
            world.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            if (world instanceof ServerLevel sw) {
                DamageSource damageSource = new DamageSource(
                        world.registryAccess()
                                .lookupOrThrow(Registries.DAMAGE_TYPE)
                                .get(DamageTypeRegistry.STAR_TRAP.identifier()).get()
                );
                le.hurtServer(sw, damageSource, 6.0f);
                if (le instanceof ServerPlayer spe) {
                    Criterias.starTrap.trigger(spe);
                }
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarTrapEntity(pos, state);
    }

    public StarTrap(Properties settings) {
        super(settings.replaceable());
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

}
