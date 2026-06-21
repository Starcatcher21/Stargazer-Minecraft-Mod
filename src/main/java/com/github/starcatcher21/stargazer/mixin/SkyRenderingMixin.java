package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.CustomRederPipelines;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Mixin(SkyRendering.class)
public abstract class SkyRenderingMixin {
    private static final Identifier COSMIC = Identifier.of(Stargazer.MOD_ID, "textures/environment/cosmic_sky.png");
    private static final Identifier RED = Identifier.of(Stargazer.MOD_ID, "textures/environment/red_sky.png");
    private static final Identifier WANDER = Identifier.of(Stargazer.MOD_ID, "textures/environment/wander_sky.png");

    @Shadow @Final private GpuBuffer endSkyVertexBuffer;

    @Shadow
    protected abstract AbstractTexture bindTexture(TextureManager textureManager, Identifier texture);


    @Inject(method = "renderEndSky", at = @At("HEAD"), cancellable = true)
    private void renderCosmicSky(CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world != null) {
            Optional<RegistryKey<DimensionType>> dim = world.getDimensionEntry().getKey();
            if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
                RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getIndexBuffer(36);
                GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
                GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .write(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                MinecraftClient mc = MinecraftClient.getInstance();
                AbstractTexture abt = this.bindTexture(mc.getTextureManager(), COSMIC);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "cosmic", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_COSMIC_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getGlTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyVertexBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.getIndexType());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
            if (dim.isPresent() && dim.get().equals(CustomWorlds.RED_ORB_TYPE)) {
                RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getIndexBuffer(36);
                GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
                GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .write(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                MinecraftClient mc = MinecraftClient.getInstance();
                AbstractTexture abt = this.bindTexture(mc.getTextureManager(), RED);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "red_orb", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_RED_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getGlTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyVertexBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.getIndexType());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
            if (dim.isPresent() && dim.get().equals(CustomWorlds.WANDER_TYPE)) {
                RenderSystem.ShapeIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getIndexBuffer(36);
                GpuTextureView gpuTextureView = MinecraftClient.getInstance().getFramebuffer().getColorAttachmentView();
                GpuTextureView gpuTextureView2 = MinecraftClient.getInstance().getFramebuffer().getDepthAttachmentView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .write(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                MinecraftClient mc = MinecraftClient.getInstance();
                AbstractTexture abt = this.bindTexture(mc.getTextureManager(), WANDER);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "wander", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_WANDER_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getGlTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyVertexBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.getIndexType());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
        }
    }
}
