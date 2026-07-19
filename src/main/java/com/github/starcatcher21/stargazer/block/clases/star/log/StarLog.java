package com.github.starcatcher21.stargazer.block.clases.star.log;

import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class StarLog extends DirectionalBlock {
    public static final MapCodec<StarLog> CODEC = simpleCodec(StarLog::new);

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (stack.tags().toList().contains(ItemTags.AXES)) {
            BlockState newState = StarBlocks.STRIPPED_STAR_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, state.getValue(BlockStateProperties.AXIS));
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
            }
            world.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            if (player != null) {
                if (!player.hasInfiniteMaterials() && stack.isDamageableItem()) {
                    stack.hurtWithoutBreaking(1, player);
                }
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    public StarLog(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(BlockStateProperties.AXIS, ctx.getClickedFace().getAxis());
    }
}
