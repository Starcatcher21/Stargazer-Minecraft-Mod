package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.dash.DashPayload;
import com.github.starcatcher21.stargazer.mechanics.dash.DashServer;
import com.github.starcatcher21.stargazer.mechanics.star.StargazePayload;
import com.github.starcatcher21.stargazer.mechanics.star.StargazeServer;
import dev.architectury.networking.NetworkManager;
import net.minecraft.server.level.ServerPlayer;

public class StargazerNetworking {
    public static void registerPackets() {
        // Register serverbound packets using Architectury's NetworkManager
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, StargazePayload.TYPE, StargazePayload.STREAM_CODEC, (payload, context) -> {
            context.queue(() -> {
                StargazeServer.handleStarCatchRequest((ServerPlayer) context.getPlayer());
            });
        });

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, DashPayload.TYPE, DashPayload.STREAM_CODEC, (payload, context) -> {
            context.queue(() -> {
                DashServer.handleDashRequest((ServerPlayer) context.getPlayer());
            });
        });

        Stargazer.LOGGER.info("Stargazer packets registered");
    }
}