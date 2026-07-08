package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.CustomRederPipelines;
import com.github.starcatcher21.stargazer.renderer.SkyDimensionChecks;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MappableRingBuffer;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.OptionalDouble;
import java.util.OptionalInt;

@Mixin(SkyRenderer.class)
public abstract class SkyRenderingMixin {
    @Unique
    private static final ByteBufferBuilder SKY_ALLOCATOR = new ByteBufferBuilder(RenderType.SMALL_BUFFER_SIZE);

    @Unique
    private static final Matrix4f IDENTITY = new Matrix4f();

    @Unique
    private static final Vector4f COLOR_MODULATOR = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);

    @Unique
    private static final Vector3f MODEL_OFFSET = new Vector3f();

    @Unique
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();

    @Unique
    private MappableRingBuffer skyVertexBuffer;

    @Inject(method = "renderEndSky", at = @At("HEAD"), cancellable = true)
    private void renderStargazerSoftSky(CallbackInfo ci) {
        Level level = Minecraft.getInstance().level;

        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) {
            return;
        }

        renderScreenSpaceSky();
        ci.cancel();
    }

    @Unique
    private void renderScreenSpaceSky() {
        MeshData meshData = buildSkyQuad();
        MeshData.DrawState drawState = meshData.drawState();
        GpuBuffer vertices = uploadSkyQuad(meshData, drawState.format());

        RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
        GpuBuffer indices = shapeIndexBuffer.getBuffer(drawState.indexCount());

        Minecraft client = Minecraft.getInstance();
        GpuTextureView colorTarget = client.getMainRenderTarget().getColorTextureView();
        GpuTextureView depthTarget = client.getMainRenderTarget().getDepthTextureView();

        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(
                new Matrix4f(),
                COLOR_MODULATOR,
                MODEL_OFFSET,
                TEXTURE_MATRIX
        );

        try (RenderPass renderPass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(
                        () -> Stargazer.MOD_ID + " soft sky",
                        colorTarget,
                        OptionalInt.empty(),
                        depthTarget,
                        OptionalDouble.empty()
                )) {
            renderPass.setPipeline(CustomRederPipelines.POSITION_SOFT_SKY);
            RenderSystem.bindDefaultUniforms(renderPass);

            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.setVertexBuffer(0, vertices);
            renderPass.setIndexBuffer(indices, shapeIndexBuffer.type());
            renderPass.drawIndexed(0, 0, drawState.indexCount(), 1);
        }

        meshData.close();
        this.skyVertexBuffer.rotate();
    }

    @Unique
    private static MeshData buildSkyQuad() {
        BufferBuilder buffer = new BufferBuilder(SKY_ALLOCATOR, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        buffer.addVertex(IDENTITY, -1.0F, -1.0F, 1.0F);
        buffer.addVertex(IDENTITY, 1.0F, -1.0F, 1.0F);
        buffer.addVertex(IDENTITY, 1.0F, 1.0F, 1.0F);
        buffer.addVertex(IDENTITY, -1.0F, 1.0F, 1.0F);

        return buffer.buildOrThrow();
    }

    @Unique
    private GpuBuffer uploadSkyQuad(MeshData meshData, VertexFormat format) {
        int vertexBufferSize = meshData.drawState().vertexCount() * format.getVertexSize();

        if (this.skyVertexBuffer == null || this.skyVertexBuffer.size() < vertexBufferSize) {
            if (this.skyVertexBuffer != null) {
                this.skyVertexBuffer.close();
            }

            this.skyVertexBuffer = new MappableRingBuffer(
                    () -> Stargazer.MOD_ID + " soft sky vertices",
                    GpuBuffer.USAGE_VERTEX | GpuBuffer.USAGE_MAP_WRITE,
                    vertexBufferSize
            );
        }

        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        try (GpuBuffer.MappedView mappedView = commandEncoder.mapBuffer(this.skyVertexBuffer.currentBuffer().slice(0, meshData.vertexBuffer().remaining()), false, true)) {
            MemoryUtil.memCopy(meshData.vertexBuffer(), mappedView.data());
        }

        return this.skyVertexBuffer.currentBuffer();
    }
}
