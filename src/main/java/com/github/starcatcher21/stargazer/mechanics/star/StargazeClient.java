package com.github.starcatcher21.stargazer.mechanics.star;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public class StargazeClient {
    private static int timer = 0;

    public static void clientTick(Minecraft client) {
        if (client.player == null) return;

        if (timer > 0) {
            timer--;
            return;
        }

        if (client.player.getXRot() < -60.0 && client.player.isScoping()) {
            ClientPlayNetworking.send(new StargazePayload());
            timer = 20;
        }
    }
}
