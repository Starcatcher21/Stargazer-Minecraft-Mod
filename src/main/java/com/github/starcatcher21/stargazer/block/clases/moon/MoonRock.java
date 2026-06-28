package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class MoonRock extends Block {
    public MoonRock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isIn(ItemTags.HOES)) {
            world.playSound((Entity)player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isClient()) {
                if (player != null) {
                    stack.damage(1, player, hand);
                }
            }
            world.setBlockState(pos, MoonBlocks.MOON_FARMLAND.getDefaultState());
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (!world.getBlockState(pos.up()).isTransparent()) {
            world.setBlockState(pos, MoonBlocks.MOON_ROCK.getDefaultState());
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
        }
    }
}
