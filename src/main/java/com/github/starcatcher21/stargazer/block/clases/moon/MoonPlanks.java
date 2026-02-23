package com.github.starcatcher21.stargazer.block.clases.moon;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

import static com.github.starcatcher21.stargazer.block.register.MoonBlocks.COLORED_PLANKS;

public class MoonPlanks extends Block {
    public MoonPlanks(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof PotionItem && !state.getBlock().equals(MoonBlocks.MOON_PLANKS)) {
            if (stack.getItem().getDefaultStack().getName().equals(Text.translatable("item.minecraft.potion.effect.water"))) {
                change(world, stack, MoonBlocks.MOON_PLANKS.getDefaultState(), pos, player);
                return ActionResult.SUCCESS;
            }
        }
        for (Map.Entry<Item, BlockState> entry : COLORED_PLANKS.entrySet()) {
            if (stack.getItem().equals(entry.getKey()) && !state.equals(entry.getValue())) {
                change(world, stack, entry.getValue(), pos, player);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
    public static void change(World world, ItemStack stack, BlockState state, BlockPos pos, PlayerEntity player) {
        if (player instanceof ServerPlayerEntity) {
            Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, stack);
        }
        world.setBlockState(pos, state, Block.NOTIFY_ALL_AND_REDRAW);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));
        if (player != null) {
            if (!player.isInCreativeMode()) {
                stack.decrement(1);
                if (stack.getItem().equals(Potions.WATER)) {
                    player.giveOrDropStack(new ItemStack(Items.GLASS_BOTTLE, 1));
                }
            }
        }
    }
}
