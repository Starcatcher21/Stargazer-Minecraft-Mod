package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
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

    @Shadow private SkyRendering skyRendering;
    @Shadow private CloudRenderer cloudRenderer;

    @Shadow
    @Final
    private DefaultFramebufferSet framebufferSet;

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
            } else if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "red_orb"))) {
                int proc = Runtime.getRuntime().availableProcessors();
                BufferBuilderStorage bufferBuilders = new BufferBuilderStorage(proc);
                MatrixStack matrixStack = new MatrixStack();
                float tickProgress = client.getRenderTickCounter().getTickProgress(false);
                float h = client.world.getSkyAngle(tickProgress);
                float i = 1.0f - client.world.getRainGradient(tickProgress);
                float j = client.world.getStarBrightness(tickProgress) * i;
                int l = client.world.getMoonPhase();
                int m = 0x000000;
                float n = ColorHelper.getRedFloat(m);
                float o = ColorHelper.getGreenFloat(m);
                float p = ColorHelper.getBlueFloat(m);
                this.skyRendering.renderTopSky(n, o, p);
                VertexConsumerProvider.Immediate immediate = bufferBuilders.getEntityVertexConsumers();
                renderCelestialBodies(matrixStack, immediate, h, l, i, j);
                immediate.draw();
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
        if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "red_orb"))) {
            FramePass framePass = frameGraphBuilder.createPass("clouds");
            if (this.framebufferSet.cloudsFramebuffer != null) {
                this.framebufferSet.cloudsFramebuffer = framePass.transfer(this.framebufferSet.cloudsFramebuffer);
            } else {
                this.framebufferSet.mainFramebuffer = framePass.transfer(this.framebufferSet.mainFramebuffer);
            }
            framePass.setRenderer(() -> {
                this.cloudRenderer.renderClouds(0xD9E4FF, mode, cloudHeight+50, cameraPos, f);
                this.cloudRenderer.renderClouds(0xD9E4FF, mode, cloudHeight+100, cameraPos.add(300.0, 0.0,300.0), f+50);
            });
            ci.cancel();
        }
    }

    public void renderCelestialBodies(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, float rot, int phase, float alpha, float starBrightness) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rot * 360.0f));
        renderBrokenMoon(phase, alpha, vertexConsumers, matrices);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));
        renderMoon(phase, alpha, vertexConsumers, matrices);
        vertexConsumers.draw();
        matrices.pop();
    }

    private static final Identifier BROKEN_MOON_PHASES_TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/environment/red_orb_moon_phases.png");
    private static final Identifier MOON_PHASES_TEXTURE = Identifier.ofVanilla("textures/environment/moon_phases.png");

    private void renderBrokenMoon(int phase, float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices) {
        float f = 20.0f;
        int i = phase % 4;
        int j = phase / 4 % 2;
        float g = (float)(i + 0) / 4.0f;
        float h = (float)(j + 0) / 2.0f;
        float k = (float)(i + 1) / 4.0f;
        float l = (float)(j + 1) / 2.0f;
        float m = 100.0f;
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCelestial(BROKEN_MOON_PHASES_TEXTURE));
        int n = ColorHelper.getWhite(alpha);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        vertexConsumer.vertex(matrix4f, -20.0f, -100.0f, 20.0f).texture(k, l).color(n);
        vertexConsumer.vertex(matrix4f, 20.0f, -100.0f, 20.0f).texture(g, l).color(n);
        vertexConsumer.vertex(matrix4f, 20.0f, -100.0f, -20.0f).texture(g, h).color(n);
        vertexConsumer.vertex(matrix4f, -20.0f, -100.0f, -20.0f).texture(k, h).color(n);
    }

    private void renderMoon(int phase, float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices) {
        float f = 20.0f;
        int i = phase % 4;
        int j = phase / 4 % 2;
        float g = (float)(i + 0) / 4.0f;
        float h = (float)(j + 0) / 2.0f;
        float k = (float)(i + 1) / 4.0f;
        float l = (float)(j + 1) / 2.0f;
        float m = 100.0f;
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCelestial(MOON_PHASES_TEXTURE));
        int n = ColorHelper.getWhite(alpha);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        vertexConsumer.vertex(matrix4f, -20.0f, -100.0f, 20.0f).texture(k, l).color(n);
        vertexConsumer.vertex(matrix4f, 20.0f, -100.0f, 20.0f).texture(g, l).color(n);
        vertexConsumer.vertex(matrix4f, 20.0f, -100.0f, -20.0f).texture(g, h).color(n);
        vertexConsumer.vertex(matrix4f, -20.0f, -100.0f, -20.0f).texture(k, h).color(n);
    }
}
