package com.github.starcatcher21.stargazer.mechanics.dash;

import com.github.starcatcher21.stargazer.Keybinds;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

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

    private static Vec3d dashDirection = null;
    private static double dashXRot = 0.0;

    private static boolean willHyper = false;
    private static boolean willSuper = false;
    private static boolean willBounce = false;
    private static Direction bounceDirection = null;

    public static void tick() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if ((canRefresh(player) && groundCooldown == 0) || player.isInCreativeMode()) {
            groundCooldown = 4;
            refresh(player);
        }

        groundCooldown--;
        if (groundCooldown < 0) groundCooldown = 0;

        // Hyper
        if (willHyper) {
            willHyper = false;
            Vec3d hyperMotion = new Vec3d(
                    player.getRotationVector().x * HYPER_H_SPEED,
                    -HYPER_V_SPEED*4,
                    player.getRotationVector().z * HYPER_H_SPEED
            );
            player.setVelocity(hyperMotion);
            dashCooldown = 2;
        }
        // Super
        if (willSuper) {
            willSuper = false;
            Vec3d superMotion = new Vec3d(
                    player.getRotationVector().x * SUPER_H_SPEED,
                    -SUPER_V_SPEED*4,
                    player.getRotationVector().z * SUPER_H_SPEED
            );
            player.setVelocity(superMotion);
            dashCooldown = 2;
        }
        // Wall Bounce
        if (willBounce) {
            willBounce = false;
            if (bounceDirection != null) {
                player.setVelocity(bounceDirection.getDoubleVector().x * BOUNCE_H_SPEED, BOUNCE_V_SPEED, bounceDirection.getDoubleVector().z * BOUNCE_H_SPEED);
            } else {
                player.setVelocity(0, BOUNCE_V_SPEED, 0);
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
                    Dash.removeDashing(player.getUuid());
                    break Dash;
                }

                player.setVelocity(dashDirection.multiply(DASH_SPEED));

                Detect:
                if (MinecraftClient.getInstance().options.jumpKey.isPressed()) {
                    if (player.isOnGround()) {
                        if (dashXRot > 60.0 && dashXRot < 90.0) willHyper = true;
                        if (dashXRot > 15.0 && dashXRot < 60.0) willSuper = true;
                    } else if (dashXRot < -60.0 && player.horizontalCollision) {
                        // change required distance from wall here
                        bounceDirection = player.getMovementDirection().getOpposite();
                        willBounce = true;
                    }
                }
            }
        }

        isOnCooldown = dashCooldown > 0 || dashes == 0;

        // Here is when the dash happens
        if (Keybinds.DASH_KEY.isPressed() && !isOnCooldown()) {
            Dash.setDashing(player.getUuid());

            // Set counter to duration of dash
            dashCooldown = 5;
            // Decrement the dash counter
            dashes--;

            dashXRot = player.getYaw();
            dashDirection = player.getRotationVector();
            player.setSprinting(true);
        }
    }

    public static void reset() {
        dashes = 0;
        isOnCooldown = false;
        dashCooldown = 0;
    }

    public static void refresh(ClientPlayerEntity player) {
        dashes = (int) player.getAttributeValue(StargazerAttributes.DASH_LEVEL);
    }
    public static void refresh(ClientPlayerEntity player, int amount) {
        dashes = amount;
    }

    public static boolean canRefresh(ClientPlayerEntity player) {
        return player.isOnGround() || player.getBlockStateAtPos().getBlock() instanceof FluidBlock;
    }

    public static boolean isOnCooldown() {
        return isOnCooldown;
    }
}