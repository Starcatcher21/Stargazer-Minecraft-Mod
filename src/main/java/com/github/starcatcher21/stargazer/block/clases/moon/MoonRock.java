package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if < 26.2 {
import net.minecraft.world.ItemInteractionResult;
//?}
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class MoonRock extends Block {
    public MoonRock(Properties settings) {
        super(settings);
    }

    //? if >= 26.2 {
    /*@Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    *///? } else {
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    //?}
        if (stack.is(ItemTags.HOES)) {
            world.playSound((Entity)player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (!world.isClientSide()) {
                if (player != null) {
                    //? if >= 26.2 {
                    /*stack.hurtAndBreak(1, player, hand);
                     *///?} else {
                    stack.hurtAndBreak(1, player, player.getUsedItemHand() == InteractionHand.MAIN_HAND
                            ? net.minecraft.world.entity.EquipmentSlot.MAINHAND
                            : net.minecraft.world.entity.EquipmentSlot.OFFHAND);
                    //?}
                }
            }
            world.setBlockAndUpdate(pos, MoonBlocks.MOON_FARMLAND.get().defaultBlockState());
            //? if >= 26.2 {
            /*return InteractionResult.SUCCESS;
             *///?} else {
            return ItemInteractionResult.SUCCESS;
            //?}
        }
        //? if >= 26.2 {
        /*return InteractionResult.PASS;
         *///?} else {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        //?}
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.randomTick(state, world, pos, random);
        //? if >= 26.2 {
        /*if (!world.getBlockState(pos.above()).propagatesSkylightDown()) {
         *///?} else {
        if (!world.getBlockState(pos.above()).propagatesSkylightDown(world, pos.above())) {
        //?}
            world.setBlockAndUpdate(pos, MoonBlocks.MOON_ROCK.get().defaultBlockState());
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
        }
    }
}
