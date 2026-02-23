package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow @Final private MinecraftClient client;

    // Shadowing the SkyRendering object if it's a field in GameRenderer.
    // If it's a local variable or created within renderSky, the injection strategy would be different.
    @Shadow private SkyRendering skyRendering;
    @Shadow private CloudRenderer cloudRenderer;
    @Shadow private WeatherRendering weatherRendering;

    @Redirect(
            method = "renderSky(Lnet/minecraft/client/render/FrameGraphBuilder;Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/render/Fog;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/FramePass;setRenderer(Ljava/lang/Runnable;)V"
            )
    )
    private void redirectFramePassSetRenderer(FramePass framePass, Runnable originalRunnable) {
        framePass.setRenderer(() -> {
            World world = client.world;
            if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "cosmic"))) {
                this.skyRendering.renderEndSky();
                Camera camera = client.gameRenderer.getCamera();
                Fog fog = BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_TERRAIN, new Vector4f(), 0, false, 0);
                RenderSystem.setShaderFog(fog);
            } else {
                originalRunnable.run();
            }
        });
    }
    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void injectAtRenderCloudsStart(
            FrameGraphBuilder frameGraphBuilder,
            CloudRenderMode mode,
            Vec3d cameraPos,
            float f,
            int color,
            float cloudHeight,
            CallbackInfo ci
    ) {
        World world = client.world;
        if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "cosmic"))) {
            ci.cancel();
        }
    }
//    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
//    private void InjectAtWeather(
//            FrameGraphBuilder frameGraphBuilder,
//            Vec3d cameraPos,
//            float tickProgress,
//            Fog fog,
//            CallbackInfo ci) {
//        World world = client.world;
//        if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "cosmic"))) {
//            ci.cancel();
//        }
//    }
}
