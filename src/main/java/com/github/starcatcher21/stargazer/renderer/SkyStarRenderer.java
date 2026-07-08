package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MappableRingBuffer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public final class SkyStarRenderer {
    private static final SkyStarRenderer INSTANCE = new SkyStarRenderer();

    private static final Identifier STAR_TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/stargazer_star_sheet.png");

    private static final int STAR_COUNT = 64;
    private static final float STAR_RADIUS = 48.0F;

    private static final int STAR_ATLAS_COLUMNS = 8;
    private static final int STAR_ATLAS_ROWS = 5;
    private static final int STAR_SPRITE_COUNT = 39;

    private static final ByteBufferBuilder ALLOCATOR = new ByteBufferBuilder(RenderType.SMALL_BUFFER_SIZE);
    private static final Vector4f COLOR_MODULATOR = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
    private static final Vector3f MODEL_OFFSET = new Vector3f();
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();

    private static final VertexFormat STAR_FORMAT = DefaultVertexFormat.POSITION_TEX_COLOR;
    private static final VertexFormat.Mode STAR_MODE = VertexFormat.Mode.QUADS;

    private BufferBuilder buffer;
    private MappableRingBuffer vertexBuffer;
    private StarRenderState renderState = StarRenderState.DISABLED;

    public static void init() {
        WorldRenderEvents.END_EXTRACTION.register(INSTANCE::extract);
        WorldRenderEvents.END_MAIN.register(INSTANCE::renderAndDraw);
    }

    private void extract(WorldExtractionContext context) {
        Level level = Minecraft.getInstance().level;
        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) {
            this.renderState = StarRenderState.DISABLED;
            return;
        }

        this.renderState = new StarRenderState(true, level.getGameTime());
    }

    private void renderAndDraw(WorldRenderContext context) {
        if (!this.renderState.enabled()) {
            return;
        }

        renderStars(context);
        if (this.buffer != null) {
            drawDepthPinnedStars(Minecraft.getInstance(), CustomRederPipelines.POSITION_TEX_COLOR_DEPTH_PINNED_STARS, STAR_TEXTURE);
        }
    }

    private void renderStars(WorldRenderContext context) {
        PoseStack matrices = context.matrices();
        if (matrices == null) {
            return;
        }

        Vec3 camera = Minecraft.getInstance().gameRenderer.getMainCamera().position();

        matrices.pushPose();
        matrices.translate(-camera.x, -camera.y, -camera.z);

        if (this.buffer == null) {
            this.buffer = new BufferBuilder(ALLOCATOR, STAR_MODE, STAR_FORMAT);
        }

        buildStars(this.buffer, matrices.last().pose(), camera, this.renderState.gameTime());
        matrices.popPose();
    }

    private static void buildStars(BufferBuilder buffer, Matrix4fc matrix, Vec3 camera, long gameTime) {
        for (int i = 0; i < STAR_COUNT; i++) {
            Vec3 baseDirection = fibonacciSphere(i, STAR_COUNT);

            if (baseDirection.y < -0.35D) {
                continue;
            }

            double lifetime = 150.0D + random01(i, 101) * 190.0D;
            double age = positiveFraction(gameTime / lifetime + random01(i, 103));
            float fade = fadeEnvelope(age);
            if (fade <= 0.015F) {
                continue;
            }

            Vec3 guideUp = Math.abs(baseDirection.y) > 0.94D
                    ? new Vec3(1.0D, 0.0D, 0.0D)
                    : new Vec3(0.0D, 1.0D, 0.0D);

            Vec3 tangentA = guideUp.cross(baseDirection).normalize();
            Vec3 tangentB = baseDirection.cross(tangentA).normalize();

            double motionAngle = random01(i, 107) * Math.PI * 2.0D;
            Vec3 motionDirection = tangentA.scale(Math.cos(motionAngle)).add(tangentB.scale(Math.sin(motionAngle))).normalize();

            double travel = (age * 2.0D - 1.0D) * (0.24D + random01(i, 109) * 0.58D);
            Vec3 direction = baseDirection.add(motionDirection.scale(travel)).normalize();
            Vec3 center = camera.add(direction.scale(STAR_RADIUS));

            Vec3 starGuideUp = Math.abs(direction.y) > 0.94D
                    ? new Vec3(1.0D, 0.0D, 0.0D)
                    : new Vec3(0.0D, 1.0D, 0.0D);
            Vec3 right = starGuideUp.cross(direction).normalize();
            Vec3 up = direction.cross(right).normalize();

            float size = (1.8F + random01(i, 3) * 4.2F) * (0.72F + fade * 0.28F);
            int atlasTile = Math.floorMod(i * 7 + (int) Math.floor(random01(i, 113) * STAR_SPRITE_COUNT), STAR_SPRITE_COUNT);

            float brightness = 0.92F + random01(i, 211) * 0.22F;
            float alpha = fade * (0.62F + random01(i, 23) * 0.32F);
            double rotation = random01(i, 307) * Math.PI * 2.0D;

            addStarQuad(buffer, matrix, center, right.scale(size), up.scale(size), rotation, atlasTile, brightness, brightness, brightness, alpha);
        }
    }

    private static void addStarQuad(
            BufferBuilder buffer,
            Matrix4fc matrix,
            Vec3 center,
            Vec3 right,
            Vec3 up,
            double rotation,
            int atlasTile,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        double sin = Math.sin(rotation);
        double cos = Math.cos(rotation);

        Vec3 rotatedRight = right.scale(cos).add(up.scale(sin));
        Vec3 rotatedUp = up.scale(cos).subtract(right.scale(sin));

        Vec3 p0 = center.subtract(rotatedRight).subtract(rotatedUp);
        Vec3 p1 = center.add(rotatedRight).subtract(rotatedUp);
        Vec3 p2 = center.add(rotatedRight).add(rotatedUp);
        Vec3 p3 = center.subtract(rotatedRight).add(rotatedUp);

        int column = atlasTile % STAR_ATLAS_COLUMNS;
        int row = atlasTile / STAR_ATLAS_COLUMNS;

        float tileWidth = 1.0F / STAR_ATLAS_COLUMNS;
        float tileHeight = 1.0F / STAR_ATLAS_ROWS;

        float pad = 0.0025F;
        float u0 = column * tileWidth + pad;
        float u1 = (column + 1) * tileWidth - pad;
        float v0 = row * tileHeight + pad;
        float v1 = (row + 1) * tileHeight - pad;

        buffer.addVertex(matrix, (float) p0.x, (float) p0.y, (float) p0.z).setUv(u0, v1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, (float) p1.x, (float) p1.y, (float) p1.z).setUv(u1, v1).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, (float) p2.x, (float) p2.y, (float) p2.z).setUv(u1, v0).setColor(red, green, blue, alpha);
        buffer.addVertex(matrix, (float) p3.x, (float) p3.y, (float) p3.z).setUv(u0, v0).setColor(red, green, blue, alpha);
    }

    private void drawDepthPinnedStars(Minecraft client, RenderPipeline pipeline, Identifier textureId) {
        MeshData builtBuffer = this.buffer.buildOrThrow();
        MeshData.DrawState drawParameters = builtBuffer.drawState();
        VertexFormat format = drawParameters.format();

        GpuBuffer vertices = upload(drawParameters, format, builtBuffer);
        draw(client, pipeline, textureId, builtBuffer, drawParameters, vertices);

        this.vertexBuffer.rotate();
        this.buffer = null;
    }

    private GpuBuffer upload(MeshData.DrawState drawParameters, VertexFormat format, MeshData builtBuffer) {
        int vertexBufferSize = drawParameters.vertexCount() * format.getVertexSize();

        if (this.vertexBuffer == null || this.vertexBuffer.size() < vertexBufferSize) {
            if (this.vertexBuffer != null) {
                this.vertexBuffer.close();
            }

            this.vertexBuffer = new MappableRingBuffer(
                    () -> Stargazer.MOD_ID + " depth pinned star vertices",
                    GpuBuffer.USAGE_VERTEX | GpuBuffer.USAGE_MAP_WRITE,
                    vertexBufferSize
            );
        }

        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        try (GpuBuffer.MappedView mappedView = commandEncoder.mapBuffer(this.vertexBuffer.currentBuffer().slice(0, builtBuffer.vertexBuffer().remaining()), false, true)) {
            MemoryUtil.memCopy(builtBuffer.vertexBuffer(), mappedView.data());
        }

        return this.vertexBuffer.currentBuffer();
    }

    private static void draw(
            Minecraft client,
            RenderPipeline pipeline,
            Identifier textureId,
            MeshData builtBuffer,
            MeshData.DrawState drawParameters,
            GpuBuffer vertices
    ) {
        RenderSystem.AutoStorageIndexBuffer shapeIndexBuffer = RenderSystem.getSequentialBuffer(STAR_MODE);

        GpuBuffer indices = shapeIndexBuffer.getBuffer(drawParameters.indexCount());

        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms().writeTransform(
                RenderSystem.getModelViewMatrix(),
                COLOR_MODULATOR,
                MODEL_OFFSET,
                TEXTURE_MATRIX
        );

        AbstractTexture texture = client.getTextureManager().getTexture(textureId);
        GpuTextureView colorTarget = client.getMainRenderTarget().getColorTextureView();
        GpuTextureView depthTarget = client.getMainRenderTarget().getDepthTextureView();

        try (RenderPass renderPass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(
                        () -> Stargazer.MOD_ID + " depth-pinned stars",
                        colorTarget,
                        OptionalInt.empty(),
                        depthTarget,
                        OptionalDouble.empty()
                )) {
            renderPass.setPipeline(pipeline);
            RenderSystem.bindDefaultUniforms(renderPass);

            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.bindTexture("Sampler0", texture.getTextureView(), texture.getSampler());

            renderPass.setVertexBuffer(0, vertices);
            renderPass.setIndexBuffer(indices, shapeIndexBuffer.type());
            renderPass.drawIndexed(0, 0, drawParameters.indexCount(), 1);
        }

        builtBuffer.close();
    }

    private static Vec3 fibonacciSphere(int index, int count) {
        double y = 1.0D - 2.0D * ((index + 0.5D) / count);
        double radius = Math.sqrt(1.0D - y * y);
        double theta = index * 2.399963229728653D;

        return new Vec3(Math.cos(theta) * radius, y, Math.sin(theta) * radius);
    }

    private static float fadeEnvelope(double age) {
        float fadeIn = smoothstep(0.04D, 0.18D, age);
        float fadeOut = 1.0F - smoothstep(0.64D, 1.0D, age);
        return fadeIn * fadeOut;
    }

    private static float smoothstep(double edge0, double edge1, double value) {
        double t = clamp((value - edge0) / (edge1 - edge0), 0.0D, 1.0D);
        return (float) (t * t * (3.0D - 2.0D * t));
    }

    private static double positiveFraction(double value) {
        return value - Math.floor(value);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static float random01(int index, int salt) {
        int value = index * 374761393 + salt * 668265263;
        value = (value ^ (value >>> 13)) * 1274126177;
        value = value ^ (value >>> 16);
        return (value & 0x00FFFFFF) / (float) 0x01000000;
    }

    private record StarRenderState(boolean enabled, long gameTime) {
        private static final StarRenderState DISABLED = new StarRenderState(false, 0L);
    }
}
