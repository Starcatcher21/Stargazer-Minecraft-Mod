package com.github.starcatcher21.stargazer.block.clases.moon.star_trap;

import com.github.starcatcher21.stargazer.block.clases.moon.MoonGrass;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class StarTrap extends BlockWithEntity {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    @Override
    protected MapCodec<? extends StarTrap> getCodec() {
        return createCodec(StarTrap::new);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction.equals(Direction.DOWN)) {
            if (!this.canPlaceAt(state, world, pos)) {
                return Blocks.AIR.getDefaultState();
            }
        }
        return state;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld || pos.equals(player.getBlockPos())) {
            return ActionResult.PASS;
        }
        if (state.get(ACTIVE)) {
            world.setBlockState(pos, state.with(ACTIVE, false));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(false);
            }
            return ActionResult.SUCCESS;
        } else if (player.isInCreativeMode()) {
            world.setBlockState(pos, state.with(ACTIVE, true));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState state2 = world.getBlockState(pos.down());
        return MoonGrass.PLACE.contains(state2.getBlock());
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.1, 0.0, 0.1, 0.9, 0.15, 0.9);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (entity instanceof PlayerEntity pe) {
            if (!pe.getAbilities().allowModifyWorld && pe.getGameMode() != GameMode.ADVENTURE) {
                return;
            }
            if (pe.isInCreativeMode()) {
                return;
            }
        }
        if (!state.get(ACTIVE) && entity instanceof LivingEntity le) {
            world.setBlockState(pos, state.with(ACTIVE, true));
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StarTrapEntity ste) {
                ste.setActive(true);
            }
            if (world instanceof ServerWorld sw) {
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                .getEntry(DamageTypeRegistry.STAR_TRAP.getValue()).get()
                );
                le.damage(sw, damageSource, 6.0f);
                if (le instanceof ServerPlayerEntity spe) {
                    Criterias.starTrap.trigger(spe);
                }
            }
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StarTrapEntity(pos, state);
    }

    public StarTrap(Settings settings) {
        super(settings.replaceable());
        this.setDefaultState(this.getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

}
