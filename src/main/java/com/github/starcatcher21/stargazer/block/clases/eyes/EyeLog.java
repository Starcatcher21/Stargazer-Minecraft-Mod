package com.github.starcatcher21.stargazer.block.clases.eyes;

import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EyeLog extends FacingBlock {
    protected Block STRIP;
    protected String VARIANT;
    @Override
    protected MapCodec<? extends FacingBlock> getCodec() {
        return null;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.streamTags().toList().contains(ItemTags.AXES)) {
            BlockState newState = STRIP.getDefaultState().with(Properties.AXIS, state.get(Properties.AXIS));
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, stack);
            }
            world.setBlockState(pos, newState, Block.NOTIFY_ALL_AND_REDRAW);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            if (player != null) {
                if (!player.isInCreativeMode() && stack.isDamageable()) {
                    stack.damage(1, player);
                }
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(Properties.EYE)) {
            world.setBlockState(pos, EyeBloodBlocks.EYE_LOG.getDefaultState().with(Properties.EYE, false).with(Properties.AXIS, state.get(Properties.AXIS)));
        } else {
            world.setBlockState(pos, EyeBloodBlocks.EYE_LOG.getDefaultState().with(Properties.EYE, true).with(Properties.AXIS, state.get(Properties.AXIS)));
        }
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    public EyeLog(Block strip, Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(Properties.AXIS, Direction.Axis.Y));
        this.setDefaultState(this.getDefaultState().with(Properties.EYE, false));
        STRIP = strip;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.AXIS, Properties.EYE);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.EYE, false).with(Properties.AXIS, ctx.getSide().getAxis());
    }
}