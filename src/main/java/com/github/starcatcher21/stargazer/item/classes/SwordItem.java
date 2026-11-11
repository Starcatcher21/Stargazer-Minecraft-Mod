package com.github.starcatcher21.stargazer.item.classes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
//? if < 26.2 && neoforge {
/*import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
*///? }

import java.util.List;

public class SwordItem extends Item {

    public SwordItem(Properties properties) {
        super(properties.component(DataComponents.TOOL, createToolProperties()));
    }

    public static Tool createToolProperties() {
        //? if >= 26.1.2 {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        //? }
        return new Tool(
                List.of(
                        Tool.Rule.minesAndDrops(
                                //? if < 26.1.2 {
                                /*List.of(Blocks.COBWEB),
                                *///? } else {
                                HolderSet.direct(new Holder[]{Blocks.COBWEB.builtInRegistryHolder()}),
                                //? }
                                15.0F),
                        Tool.Rule.overrideSpeed(
                                //? if < 26.1.2 {
                                /*BlockTags.SWORD_EFFICIENT,
                                *///? } else {
                                registrationLookup.getOrThrow(BlockTags.SWORD_EFFICIENT),
                                //? }
                                1.5F)
                        //? if >= 26.1.2 {
                        , Tool.Rule.overrideSpeed(
                                registrationLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES),
                                Float.MAX_VALUE)
                        //? }
                ),
                1.0F,
                2
                //? if >= 26.1.2 {
                , false
                //? }
        );
    }
    //? if < 26.1.2 {
    /*public boolean canAttackBlock(BlockState arg, Level arg2, BlockPos arg3, Player arg4) {
        return !arg4.isCreative();
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
    }
    *///? }
}
