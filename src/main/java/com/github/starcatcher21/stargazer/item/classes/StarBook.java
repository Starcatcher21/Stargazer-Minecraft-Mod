package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class StarBook extends Item {

    public StarBook(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {

            MenuProvider factory = new SimpleMenuProvider(
                    (syncId, playerInventory, player) -> new StarBookScreenHandler(syncId, playerInventory),
                    Component.literal("")
            );

            user.openMenu(factory);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }
}
