package com.github.starcatcher21.stargazer.mechanics.dash;

import com.github.starcatcher21.stargazer.Keybinds;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;

public class DashClient {

    public static final double DASH_SPEED = 1.0;
    private static final double HYPER_H_SPEED = 3.0;
    private static final double HYPER_V_SPEED = 0.5;
    private static final double SUPER_H_SPEED = 0.8;
    private static final double SUPER_V_SPEED = 1.0;
    private static final double BOUNCE_H_SPEED = 0.8;
    private static final double BOUNCE_V_SPEED = 1.5;

    private static boolean isOnCooldown = false;

    public static int groundCooldown = 2;
    private static int dashCooldown = 2;
    private static int dashes = 0;

    private static Vec3 dashDirection = null;
    private static double dashXRot = 0.0;

    private static boolean willHyper = false;
    private static boolean willSuper = false;
    private static boolean willBounce = false;
    private static Direction bounceDirection = null;

    public static void tick() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        if ((canRefresh(player) && groundCooldown == 0) || player.hasInfiniteMaterials()) {
            groundCooldown = 6;
            refresh(player);
        }

        groundCooldown--;
        if (groundCooldown < 0) groundCooldown = 0;

        // Hyper
        if (willHyper) {
            willHyper = false;
            Vec3 hyperMotion = new Vec3(
                    player.getLookAngle().x * HYPER_H_SPEED,
                    -HYPER_V_SPEED * 4,
                    player.getLookAngle().z * HYPER_H_SPEED
            );
            player.setDeltaMovement(hyperMotion);
            dashCooldown = 2;
        }
        // Super
        if (willSuper) {
            willSuper = false;
            Vec3 superMotion = new Vec3(
                    player.getLookAngle().x * SUPER_H_SPEED,
                    -SUPER_V_SPEED * 4,
                    player.getLookAngle().z * SUPER_H_SPEED
            );
            player.setDeltaMovement(superMotion);
            dashCooldown = 2;
        }
        // Wall Bounce
        if (willBounce) {
            willBounce = false;
            if (bounceDirection != null) {
                player.setDeltaMovement(bounceDirection.getUnitVec3().x * BOUNCE_H_SPEED, BOUNCE_V_SPEED, bounceDirection.getUnitVec3().z * BOUNCE_H_SPEED);
            } else {
                player.setDeltaMovement(0, BOUNCE_V_SPEED, 0);
            }
            dashCooldown = 2;
        }

        // During Dash
        Dash:
        if (dashCooldown > 0) {
            dashCooldown--;

            // End Dash
            if (dashDirection != null) {
                if (dashCooldown == 0) {
                    Dash.removeDashing(player.getUUID());
                    break Dash;
                }

                player.setDeltaMovement(dashDirection.scale(DASH_SPEED));

                Detect:
                if (Minecraft.getInstance().options.keyJump.isDown()) {
                    if (player.onGround()) {
                        if (dashXRot > 60.0 && dashXRot < 90.0) willHyper = true;
                        if (dashXRot > 15.0 && dashXRot < 60.0) willSuper = true;
                    } else if (dashXRot < -60.0 && player.horizontalCollision) {
                        bounceDirection = player.getMotionDirection().getOpposite();
                        willBounce = true;
                    }
                }
            }
        }

        isOnCooldown = dashCooldown > 0 || dashes == 0;

        // Trigger Dash & Send Packet to Server
        if (Keybinds.DASH_KEY.isDown() && !isOnCooldown()) {
            Dash.setDashing(player.getUUID());

            // Send packet to server
            ClientPlayNetworking.send(new DashPayload());

            // Set counter to duration of dash
            dashCooldown = 5;
            // Decrement the dash counter
            dashes--;

            dashXRot = player.getYRot();
            dashDirection = player.getLookAngle();
            player.setSprinting(true);
        }
    }

    public static void reset() {
        dashes = 0;
        isOnCooldown = false;
        dashCooldown = 0;
    }

    public static void refresh(LocalPlayer player) {
        dashes = (int) player.getAttributeValue(StargazerAttributes.DASH_LEVEL);
    }

    public static void refresh(LocalPlayer player, int amount) {
        dashes = amount;
    }

    public static boolean canRefresh(LocalPlayer player) {
        return player.onGround() || player.getInBlockState().getBlock() instanceof LiquidBlock;
    }

    public static boolean isOnCooldown() {
        return isOnCooldown;
    }
}