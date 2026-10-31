package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.dash.DashPayload;
import com.github.starcatcher21.stargazer.mechanics.dash.DashServer;
import com.github.starcatcher21.stargazer.mechanics.star.StargazePayload;
import com.github.starcatcher21.stargazer.mechanics.star.StargazeServer;
import com.github.starcatcher21.stargazer.network.MoonRulePayload;
import dev.architectury.networking.NetworkManager;
import net.minecraft.server.level.ServerPlayer;

public class StargazerNetworking {
    private static boolean cachedMoonRule = true;

    public static boolean isMoonRuleEnabled() {
        return cachedMoonRule;
    }

    public static void registerPackets() {
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

        NetworkManager.registerReceiver(
                NetworkManager.Side.S2C,
                MoonRulePayload.TYPE,
                MoonRulePayload.STREAM_CODEC,
                StargazerNetworking::handleMoonRule
        );

        Stargazer.LOGGER.info("Stargazer packets registered");
    }
    private static void handleMoonRule(MoonRulePayload payload, NetworkManager.PacketContext context) {
        context.queue(() -> cachedMoonRule = payload.moonRuleEnabled());
    }
}
