package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.stats.ModStats;
import com.github.starcatcher21.starlib.mechanics.star.FallingObject;
import com.github.starcatcher21.starlib.mechanics.star.FallingObjectDayState;
import com.github.starcatcher21.starlib.mechanics.star.FallingObjectsList;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.LightLayer;

import java.util.Collections;
import java.util.Random;

public class StargazeServer {
    public static void handleStarCatchRequest(ServerPlayer player) {
        ServerLevel level = player.level();

        for (FallingObjectsList list : FallingObjectsList.list2) {
            if (level.dimension().equals(list.world)) {

                if (list.dayState.equals(FallingObjectDayState.Day) && !level.isBrightOutside()) {
                    continue;
                }
                if (list.dayState.equals(FallingObjectDayState.Night) && level.isBrightOutside()) {
                    continue;
                }

                if (level.getBrightness(LightLayer.SKY, player.blockPosition()) >= list.lightLevel) {

                    Random random = new Random();
                    if (random.nextInt(100) < 10) {
                        if (list.weightedList.isEmpty()) continue;

                        Collections.shuffle(list.weightedList);
                        FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));

                        // Trigger server advancement & stat updates safely
                        Criterias.starcatching.trigger(player, star.item.value());
                        star.spawn(level, player);
                        player.awardStat(ModStats.STAR_CATCHED);
                    }
                }
            }
        }
    }
}
