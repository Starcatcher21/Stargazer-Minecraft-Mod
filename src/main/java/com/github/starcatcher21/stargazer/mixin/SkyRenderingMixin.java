package com.github.starcatcher21.stargazer.mixin;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.renderer.SkyDimensionChecks;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.OptionalDouble;

//? if >= 26.2 {
/*import com.github.starcatcher21.stargazer.renderer.CustomRederPipelines;
import com.mojang.blaze3d.PrimitiveTopology;
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
import net.minecraft.client.renderer.MappableRingBuffer;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
*///?} else {
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.LevelRenderer;
import org.joml.Matrix4f;
//?}

//? if >= 26.2 {
/*@Mixin(SkyRenderer.class)
 *///?} else {
@Mixin(LevelRenderer.class)
//?}
public abstract class SkyRenderingMixin {

    //? if >= 26.2 {
    /*@Unique
    private static final ByteBufferBuilder SKY_ALLOCATOR =
        new ByteBufferBuilder(RenderType.SMALL_BUFFER_SIZE);

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
    *///?}

    /* ------------------------------------------------------------------ */
    /* 26.2+ : inject into SkyRenderer.renderEndSky / renderSun            */
    /* ------------------------------------------------------------------ */
    //? if >= 26.2 {
    /*@Inject(method = "renderEndSky", at = @At("HEAD"), cancellable = true)
    private void renderStargazerSoftSky(CallbackInfo ci) {
        Level level = Minecraft.getInstance().level;
        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) return;
        renderScreenSpaceSky();
        ci.cancel();
    }

    @Inject(method = "renderSun", at = @At("HEAD"), cancellable = true)
    private void renderStargazerSun(CallbackInfo ci) {
        if (Minecraft.getInstance().level == null) return;
        if (SkyDimensionChecks.isStargazerSkyDimension(Minecraft.getInstance().level)) {
            ci.cancel();
        }
    }

    @Unique
    private void renderScreenSpaceSky() {
        MeshData meshData = buildSkyQuad();
        MeshData.DrawState drawState = meshData.drawState();
        GpuBufferSlice vertices = uploadSkyQuad(meshData, drawState.format());

        RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer =
            RenderSystem.getSequentialBuffer(PrimitiveTopology.QUADS);
        GpuBuffer indices = shapeIndexBuffer.getBuffer(drawState.indexCount());

        Minecraft client = Minecraft.getInstance();
        GpuTextureView colorTarget =
            client.gameRenderer.mainRenderTarget().getColorTextureView();
        GpuTextureView depthTarget =
            client.gameRenderer.mainRenderTarget().getDepthTextureView();

        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(
            RenderSystem.getModelViewMatrixCopy(), COLOR_MODULATOR, MODEL_OFFSET, TEXTURE_MATRIX);

        try (RenderPass renderPass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(
                    () -> Stargazer.MOD_ID + " soft sky",
                    colorTarget, Optional.empty(), depthTarget, OptionalDouble.empty())) {
            renderPass.setPipeline(CustomRederPipelines.POSITION_SOFT_SKY);
            RenderSystem.bindDefaultUniforms(renderPass);
            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.setVertexBuffer(0, vertices);
            renderPass.setIndexBuffer(indices, shapeIndexBuffer.type());
            renderPass.drawIndexed(0, 0, drawState.indexCount(), 0, 1);
        } catch (Exception e) {
            Stargazer.LOGGER.error("Exception in sky render: ", e);
        }

        meshData.close();
        this.skyVertexBuffer.rotate();
    }

    @Unique
    private static MeshData buildSkyQuad() {
        BufferBuilder buffer = new BufferBuilder(
            SKY_ALLOCATOR, PrimitiveTopology.QUADS, DefaultVertexFormat.POSITION);
        float distance = 100.0F;
        buffer.addVertex(IDENTITY, -distance, -distance, distance)
              .setUv(0.0F, 0.0F).setColor(-14145496);
        buffer.addVertex(IDENTITY, distance, -distance, distance)
              .setUv(0.0F, 16.0F).setColor(-14145496);
        buffer.addVertex(IDENTITY, distance, distance, distance)
              .setUv(16.0F, 16.0F).setColor(-14145496);
        buffer.addVertex(IDENTITY, -distance, distance, distance)
              .setUv(16.0F, 0.0F).setColor(-14145496);
        return buffer.buildOrThrow();
    }

    @Unique
    private GpuBufferSlice uploadSkyQuad(MeshData meshData, VertexFormat format) {
        int vertexBufferSize =
            meshData.drawState().vertexCount() * format.getVertexSize();
        if (this.skyVertexBuffer == null || this.skyVertexBuffer.size() < vertexBufferSize) {
            if (this.skyVertexBuffer != null) this.skyVertexBuffer.close();
            this.skyVertexBuffer = new MappableRingBuffer(
                () -> Stargazer.MOD_ID + " soft sky vertices",
                GpuBuffer.USAGE_VERTEX | GpuBuffer.USAGE_MAP_WRITE | GpuBuffer.USAGE_COPY_DST,
                vertexBufferSize);
        }
        GpuBuffer currentBuf = this.skyVertexBuffer.currentBuffer();
        GpuBufferSlice slice = currentBuf.slice(0, meshData.vertexBuffer().remaining());
        CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
        encoder.writeToBuffer(slice, meshData.vertexBuffer());
        return slice;
    }
    *///?} else {

    /* ------------------------------------------------------------------ */
    /* 1.21.1 : inject into LevelRenderer.renderSky internal sky path     */
    /* ------------------------------------------------------------------ */

    // 1.21.1 LevelRenderer.renderSky(PoseStack, Matrix4f, float, Camera, boolean, Runnable)
    // The end-sky branch is inside this method. We inject at HEAD and cancel if
    // our dimension check passes, then perform our own soft-sky render.

    @Inject(
            method = "renderSky",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderStargazerSoftSky1_21_1(Matrix4f matrix4f2, Matrix4f matrix4f3, float g, Camera arg, boolean bl, Runnable runnable, CallbackInfo ci) {
        Level level = Minecraft.getInstance().level;
        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) return;

        renderScreenSpaceSky1_21_1();
        ci.cancel();
    }

    @Unique
    private void renderScreenSpaceSky1_21_1() {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.QUADS,
                DefaultVertexFormat.POSITION);
        Matrix4f identity = new Matrix4f();
        float distance = 100.0F;

        buffer.addVertex(identity, -distance, -distance, distance)
                .setColor(-14145496);
        buffer.addVertex(identity,  distance, -distance, distance)
                .setColor(-14145496);
        buffer.addVertex(identity,  distance,  distance, distance)
                .setColor(-14145496);
        buffer.addVertex(identity, -distance,  distance, distance)
                .setColor(-14145496);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);

        BufferUploader.drawWithShader(buffer.buildOrThrow());

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }
    //?}
}
