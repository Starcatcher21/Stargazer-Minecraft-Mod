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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(SkyRenderer.class)
public abstract class SkyRenderingMixin {
    private static final Identifier COSMIC = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/cosmic_sky.png");
    private static final Identifier RED = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/red_sky.png");
    private static final Identifier WANDER = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/wander_sky.png");

    @Shadow @Final private GpuBuffer endSkyBuffer;

    @Shadow
    protected abstract AbstractTexture getTexture(TextureManager textureManager, Identifier texture);


    @Inject(method = "renderEndSky", at = @At("HEAD"), cancellable = true)
    private void renderCosmicSky(CallbackInfo ci) {
        Level world = Minecraft.getInstance().level;
        if (world != null) {
            Optional<ResourceKey<DimensionType>> dim = world.dimensionTypeRegistration().unwrapKey();
            if (dim.isPresent() && dim.get().equals(CustomWorlds.COSMIC_TYPE)) {
                RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getBuffer(36);
                GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
                GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                Minecraft mc = Minecraft.getInstance();
                AbstractTexture abt = this.getTexture(mc.getTextureManager(), COSMIC);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "cosmic", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_COSMIC_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.type());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
            if (dim.isPresent() && dim.get().equals(CustomWorlds.RED_ORB_TYPE)) {
                RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getBuffer(36);
                GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
                GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                Minecraft mc = Minecraft.getInstance();
                AbstractTexture abt = this.getTexture(mc.getTextureManager(), RED);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "red_orb", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_RED_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.type());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
            if (dim.isPresent() && dim.get().equals(CustomWorlds.WANDER_TYPE)) {
                RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
                GpuBuffer gpuBuffer = shapeIndexBuffer.getBuffer(36);
                GpuTextureView gpuTextureView = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
                GpuTextureView gpuTextureView2 = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
                GpuBufferSlice gpuBufferSlice = RenderSystem.getDynamicUniforms()
                        .writeTransform(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), new Vector3f(), new Matrix4f());
                Minecraft mc = Minecraft.getInstance();
                AbstractTexture abt = this.getTexture(mc.getTextureManager(), WANDER);
                try (RenderPass renderPass = RenderSystem.getDevice()
                        .createCommandEncoder()
                        .createRenderPass(() -> "wander", gpuTextureView, OptionalInt.empty(), gpuTextureView2, OptionalDouble.empty())) {
                    renderPass.setPipeline(CustomRederPipelines.POSITION_TEX_COLOR_WANDER_SKY);
                    RenderSystem.bindDefaultUniforms(renderPass);
                    renderPass.setUniform("DynamicTransforms", gpuBufferSlice);
                    renderPass.bindTexture("Sampler0", abt.getTextureView(), abt.getSampler());
                    renderPass.setUniform("GameTime", gpuBufferSlice);
                    renderPass.setVertexBuffer(0, this.endSkyBuffer);
                    renderPass.setIndexBuffer(gpuBuffer, shapeIndexBuffer.type());
                    renderPass.drawIndexed(0, 0, 36, 1);
                }
                ci.cancel();
            }
        }
    }
}
