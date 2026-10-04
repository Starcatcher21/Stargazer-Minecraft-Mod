package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.renderer.SkyDimensionChecks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.Level;

//? if >= 26.2 {
/*import com.mojang.blaze3d.framegraph.FramePass;
import net.minecraft.client.renderer.SkyRenderer;
*///?} else {
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
//?}

@Mixin(LevelRenderer.class)
public class WorldRendererMixin {

    // ====================================================================
    // 26.2 branch — redirect the sky pass's FramePass.executes(Runnable)
    // ====================================================================
    //? if >= 26.2 {
    /*@Shadow private SkyRenderer skyRenderer;

    @Redirect(
            method = "addSkyPass",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/framegraph/FramePass;executes(Ljava/lang/Runnable;)V"
            )
    )
    private void redirectFramePassSetRenderer(FramePass framePass, Runnable originalRunnable) {
        framePass.executes(() -> {
            Level world = Minecraft.getInstance().level;
            if (world != null && SkyDimensionChecks.isStargazerSkyDimension(world)) {
                this.skyRenderer.renderEndSky();
            } else {
                originalRunnable.run();
            }
        });
    }
    *///?}

    // ====================================================================
    // 1.21.1 branch — inject into LevelRenderer.renderSky and cancel
    // ====================================================================
    //? if < 26.2 {
    @Inject(
            method = "renderSky(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FLnet/minecraft/client/Camera;ZLjava/lang/Runnable;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectSoftSky1_21_1(Matrix4f frustumMatrix,
                                     Matrix4f projectionMatrix,
                                     float partialTick,
                                     Camera camera,
                                     boolean thickFog,
                                     Runnable fogCallback,
                                     CallbackInfo ci) {
        Level world = Minecraft.getInstance().level;
        if (world == null || !SkyDimensionChecks.isStargazerSkyDimension(world)) {
            return;
        }

        renderCustomSky1_21_1();
        ci.cancel();
    }

    @Unique
    private static void renderCustomSky1_21_1() {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(
                VertexFormat.Mode.QUADS,
                DefaultVertexFormat.POSITION_COLOR
        );

        Matrix4f matrix = new Matrix4f();
        float distance = 100.0F;
        int color = -14145496; // 0xFF282828

        buffer.addVertex(matrix, -distance, -distance, distance).setColor(color);
        buffer.addVertex(matrix,  distance, -distance, distance).setColor(color);
        buffer.addVertex(matrix,  distance,  distance, distance).setColor(color);
        buffer.addVertex(matrix, -distance,  distance, distance).setColor(color);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);

        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        BufferUploader.drawWithShader(buffer.buildOrThrow());

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
    //?}
}
