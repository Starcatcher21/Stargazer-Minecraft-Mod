package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import java.util.Map;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import static com.github.starcatcher21.stargazer.block.register.MoonBlocks.COLORED_PLANKS;

public class MoonPlanks extends Block {
    public MoonPlanks(Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof PotionItem && !state.getBlock().equals(MoonBlocks.MOON_PLANKS)) {
            if (stack.getItem().getDefaultInstance().getHoverName().equals(Component.translatable("item.minecraft.potion.effect.water"))) {
                change(world, stack, MoonBlocks.MOON_PLANKS.defaultBlockState(), pos, player);
                return InteractionResult.SUCCESS;
            }
        }
        for (Map.Entry<Item, BlockState> entry : COLORED_PLANKS.entrySet()) {
            if (stack.getItem().equals(entry.getKey()) && !state.equals(entry.getValue())) {
                change(world, stack, entry.getValue(), pos, player);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
    public static void change(Level world, ItemStack stack, BlockState state, BlockPos pos, Player player) {
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, stack);
        }
        world.setBlock(pos, state, Block.UPDATE_ALL_IMMEDIATE);
        world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
        if (player != null) {
            if (!player.hasInfiniteMaterials()) {
                stack.shrink(1);
                if (stack.getItem().equals(Potions.WATER)) {
                    player.handleExtraItemsCreatedOnUse(new ItemStack(Items.GLASS_BOTTLE, 1));
                }
            }
        }
    }
}
