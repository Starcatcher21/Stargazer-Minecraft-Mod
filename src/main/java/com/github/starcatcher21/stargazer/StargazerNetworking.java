package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.dash.DashPayload;
import com.github.starcatcher21.stargazer.mechanics.dash.DashServer;
import com.github.starcatcher21.stargazer.mechanics.star.StargazePayload;
import com.github.starcatcher21.stargazer.mechanics.star.StargazeServer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class StargazerNetworking {
    public static void registerPackets() {
        PayloadTypeRegistry.serverboundPlay().register(StargazePayload.TYPE, StargazePayload.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(DashPayload.TYPE, DashPayload.STREAM_CODEC);
        Stargazer.LOGGER.info("Stargazer packets registered");

        ServerPlayNetworking.registerGlobalReceiver(StargazePayload.TYPE, ((payload, context) -> {
            StargazeServer.handleStarCatchRequest(context.player());
        }));

        ServerPlayNetworking.registerGlobalReceiver(DashPayload.TYPE, ((payload, context) -> {
            DashServer.handleDashRequest(context.player());
        }));
    }
}
