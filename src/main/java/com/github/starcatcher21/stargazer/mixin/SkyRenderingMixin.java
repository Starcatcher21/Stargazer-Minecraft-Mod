package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.CustomRederPipelines;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.TriState;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.OptionalDouble;
import java.util.OptionalInt;

@Mixin(SkyRendering.class)
public class SkyRenderingMixin {
    private static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/environment/cosmic_sky.png");

    @Shadow @Final private GpuBuffer endSkyVertexBuffer;

    @Inject(method = "renderEndSky", at = @At("HEAD"), cancellable = true)
    private void renderCosmicSky(CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world.getDimension().effects().equals(Identifier.of(Stargazer.MOD_ID, "cosmic"))) {
            TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
            AbstractTexture abstractTexture = textureManager.getTexture(TEXTURE);
            abstractTexture.setFilter(TriState.FALSE, false);
            RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
            GpuBuffer gpuBuffer = shapeIndexBuffer.getIndexBuffer(/*36*/ 0);
//            GpuBuffer gpuBuffer = RenderSystem.getQuadVertexBuffer();
            GpuTexture gpuTexture = MinecraftClient.getInstance().getFramebuffer().getColorAttachment();
            GpuTexture gpuTexture2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachment();
            try (RenderPass renderPass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(gpuTexture, OptionalInt.empty(), gpuTexture2, OptionalDouble.empty());) {
                renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_COSMIC_SKY);
                renderPass.bindSampler("Sampler0", abstractTexture.getGlTexture());
                renderPass.setVertexBuffer(0, this.endSkyVertexBuffer);
                renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.getIndexType());
                renderPass.drawIndexed(0, 36);
            }
            ci.cancel();
        }
    }
}
