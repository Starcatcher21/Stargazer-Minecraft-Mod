package com.github.starcatcher21.stargazer.mechanics.dash;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class DashServer {
    public static void handleDashRequest(ServerPlayer player) {
        // Apply dash movement on the server side to keep client and server position in sync
        Vec3 lookAngle = player.getLookAngle();
        player.setDeltaMovement(lookAngle.scale(DashClient.DASH_SPEED));
        player.setSprinting(true);

        // Mark player as dashing on server
        Dash.setDashing(player.getUUID());
    }
}
