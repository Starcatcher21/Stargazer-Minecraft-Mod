package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.stats.ModStats;
import java.util.Collections;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class Stargaze {
    private static int timer = 0;
    public static int range = 12;
    public static void tick(Minecraft client) {
        for (FallingObjectsList list : FallingObjectsList.list2) {
            if (client.level.dimension().equals(list.world)) {
                if (list.dayState.equals(FallingObjectDayState.Day)) {
                    if (!client.getSingleplayerServer().overworld().isBrightOutside()) {
                        continue;
                    }
                }
                if (list.dayState.equals(FallingObjectDayState.Night)) {
                    if (client.getSingleplayerServer().overworld().isBrightOutside()) {
                        continue;
                    }
                }

                Player player = client.player;
                Level world = client.getSingleplayerServer().getLevel(list.world);
                if (timer != 0 ) {
                    timer -= 1;
                    if (timer < 0) {
                        timer = 0;
                    }
                    return;
                }
                if (world.getBrightness(LightLayer.SKY, player.blockPosition()) == list.lightLevel) {
                    if (player.getXRot() < -60.0 && player.isScoping()) {
                        Random random = new Random();
                        int randomNumb = random.nextInt(100);
                        if (randomNumb < 10) {
                            timer = 20;
                            Collections.shuffle(list.weightedList);
                            FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));
                            ServerPlayer spe = client.getSingleplayerServer().getPlayerList().getPlayer(player.getUUID());
                            Criterias.starcatching.trigger(spe, star.item.value());
                            star.spawn(world, spe);
                            spe.awardStat(ModStats.STAR_CATCHED);
                        }
                    }
                }

            }
        }
    }
}
