package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.stats.ModStats;
import com.github.starcatcher21.starlib.mechanics.star.FallingObject;
import com.github.starcatcher21.starlib.mechanics.star.FallingObjectDayState;
import com.github.starcatcher21.starlib.mechanics.star.FallingObjectsList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.LightLayer;

import java.util.Collections;
import java.util.Random;

public class StargazeServer {
    public static void handleStarCatchRequest(ServerPlayer player) {
        //? if >= 26.2 {
        ServerLevel level = player.level();
         //?} else {
        /*ServerLevel level = player.serverLevel();
        *///?}

        for (FallingObjectsList list : FallingObjectsList.list2) {
            if (!level.dimension().equals(list.world)) continue;

            //? if >= 26.2 {
            boolean bright = level.isBrightOutside();
             //?} else {
            /*boolean bright = !level.isNight();
            *///?}

            if (list.dayState == FallingObjectDayState.Day && !bright) continue;
            if (list.dayState == FallingObjectDayState.Night && bright) continue;

            if (level.getBrightness(LightLayer.SKY, player.blockPosition()) >= list.lightLevel) {
                Random random = new Random();
                if (random.nextInt(100) < 10) {
                    if (list.weightedList.isEmpty()) continue;

                    Collections.shuffle(list.weightedList);
                    FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));

                    //? if >= 26.2 {
                    Criterias.STARCATCHING.get().trigger(player, star.item.value());
                     //?} else {
                    /*Criterias.STARCATCHING.trigger(player, star.item.value());
                    *///?}
                    star.spawn(level, player);
                    ModStats.STAR_CATCHED.listen(player::awardStat);
                }
            }
        }
    }
}
