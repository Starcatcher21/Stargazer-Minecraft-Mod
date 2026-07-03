package com.github.starcatcher21.stargazer.item.classes;

import com.github.starcatcher21.stargazer.screens.StarBookScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class StarBook extends Item {

    public StarBook(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {

            NamedScreenHandlerFactory factory = new SimpleNamedScreenHandlerFactory(
                    (syncId, playerInventory, player) -> new StarBookScreenHandler(syncId, playerInventory),
                    Text.literal("")
            );

            user.openHandledScreen(factory);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return ActionResult.SUCCESS;
    }
}
