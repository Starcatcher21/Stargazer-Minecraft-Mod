package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.StargazerDataLoader;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Random;

public class Stargaze {
    private static int timer = 0;
    public static int range = 12;
    public static void tick(MinecraftClient client) {
        for (FallingObjectsList list : StargazerDataLoader.getFallingObjectsListData().values()) {
            if (client.world.getRegistryKey().equals(list.world)) {
                if (list.dayState.equals(FallingObjectDayState.Day)) {
                    if (!client.getServer().getOverworld().isDay()) {
                        continue;
                    }
                }
                if (list.dayState.equals(FallingObjectDayState.Night)) {
                    if (client.getServer().getOverworld().isDay()) {
                        continue;
                    }
                }

                PlayerEntity player = client.player;
                World world = client.getServer().getWorld(list.world);
                if (timer != 0 ) {
                    timer -= 1;
                    return;
                }
                if (world.getLightLevel(LightType.SKY, player.getBlockPos()) == list.lightLevel) {
                    if (player.getPitch() < -60.0 && player.isUsingSpyglass()) {
                        Random random = new Random();
                        int randomNumb = random.nextInt(100);
                        if (randomNumb < 10) {
                            timer = 20;
                            Collections.shuffle(list.weightedList);
                            FallingObject star = list.weightedList.get(random.nextInt(list.weightedList.size()));
                            ServerPlayerEntity spe = client.getServer().getPlayerManager().getPlayer(player.getUuid());
                            Criterias.starcatching.trigger(spe);
                            star.spawn(client, player);
                        }
                    }
                }

            }
        }
    }
}
