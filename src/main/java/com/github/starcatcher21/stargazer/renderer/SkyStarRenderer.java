package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.CustomWorlds;
import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.StagedVertexBuffer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.Optional;
import java.util.OptionalDouble;

public final class SkyStarRenderer {
    private static final SkyStarRenderer INSTANCE = new SkyStarRenderer();

    private static final Identifier STAR_TEXTURE = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/stargazer_star_sheet.png");
    private static final Identifier STAR_TEXTURE2 = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/stargazer_star2_sheet.png");
    private static final Identifier STAR_TEXTURE3 = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/environment/wander_sky2.png");

    private static final int DEFAULT_STAR_COUNT = 128;
    private static final float DEFAULT_STAR_RADIUS = 96.0F;
    private static final int WANDER_STAR_COUNT = 256;
    private static final float WANDER_STAR_RADIUS = 48.0F;
    private static final float RED_ORB_STAR_RADIUS = 192.0F;

    private static final int STAR_ATLAS_COLUMNS = 8;
    private static final int STAR_ATLAS_ROWS = 5;
    private static final int STAR_SPRITE_COUNT = 39;

    private static final Vector4f COLOR_MODULATOR = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
    private static final Vector3f MODEL_OFFSET = new Vector3f();
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();

    private StarRenderState renderState = StarRenderState.DISABLED;

    private static final StagedVertexBuffer STAGED_BUFFER = new StagedVertexBuffer(
            () -> Stargazer.MOD_ID + " Sky Stars Buffer",
            RenderType.SMALL_BUFFER_SIZE
    );

    public static void init() {
        LevelExtractionEvents.END_EXTRACTION.register(INSTANCE::extract);
        LevelRenderEvents.END_MAIN.register(INSTANCE::renderAndDraw);
    }

    // --- EXTRACTION PHASE ---
    private void extract(LevelExtractionContext context) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null || !SkyDimensionChecks.isStargazerSkyDimension(level)) {
            this.renderState = StarRenderState.DISABLED;
            return;
        }

        Optional<ResourceKey<DimensionType>> dimensionType = level.dimensionTypeRegistration().unwrapKey();
        if (dimensionType.isEmpty()) {
            this.renderState = StarRenderState.DISABLED;
            return;
        }

        StarSettings settings = StarSettings.forDimension(dimensionType.get());
        if (settings == null) {
            this.renderState = StarRenderState.DISABLED;
            return;
        }

        Vec3 cameraPos = context.levelState().cameraRenderState.pos;

        this.renderState = new StarRenderState(
                true,
                level.getGameTime(),
                cameraPos,
                settings
        );
    }

    // --- DRAWING PHASE ---
    private void renderAndDraw(LevelRenderContext context) {
        if (!this.renderState.enabled() || this.renderState.settings() == null) {
            return;
        }

        RenderPipeline pipeline = CustomRederPipelines.POSITION_TEX_COLOR_DEPTH_PINNED_STARS;
        VertexFormat formatBinding = pipeline.getVertexFormatBinding(0);
        assert formatBinding != null;

        PrimitiveTopology primitive = pipeline.getPrimitiveTopology();
        StagedVertexBuffer.Draw draw = STAGED_BUFFER.appendDraw(
                formatBinding,
                primitive,
                primitive == PrimitiveTopology.QUADS ? RenderSystem.getProjectionType().vertexSorting() : null
        );

        this.renderStars(context, draw);
        STAGED_BUFFER.upload();

        StagedVertexBuffer.ExecuteInfo info = STAGED_BUFFER.getExecuteInfo(draw);
        if (info != null) {
            draw(Minecraft.getInstance(), info, pipeline, this.renderState.settings().texture());
        }

        STAGED_BUFFER.endFrame();
    }

    private void renderStars(LevelRenderContext context, StagedVertexBuffer.Draw draw) {
        PoseStack matrices = context.poseStack();
        Vec3 camera = this.renderState.cameraPos();
        StarSettings settings = this.renderState.settings();

        matrices.pushPose();
        matrices.translate(-camera.x, -camera.y, -camera.z);

        VertexConsumer builder = STAGED_BUFFER.getVertexBuilder(draw);
        buildStars(
                builder,
                matrices.last().pose(),
                camera,
                this.renderState.gameTime(),
                settings.starCount(),
                settings.starRadius()
        );

        matrices.popPose();
    }

    private static void buildStars(
            VertexConsumer buffer,
            Matrix4fc matrix,
            Vec3 camera,
            long gameTime,
            int starCount,
            float starRadius
    ) {
        for (int i = 0; i < starCount; i++) {
            Vec3 baseDirection = fibonacciSphere(i, starCount);

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
            Vec3 center = camera.add(direction.scale(starRadius));

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
            VertexConsumer buffer,
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

    private static void draw(Minecraft client, StagedVertexBuffer.ExecuteInfo info, RenderPipeline pipeline, Identifier textureId) {
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
                .writeTransform(RenderSystem.getModelViewMatrixCopy(), COLOR_MODULATOR, MODEL_OFFSET, TEXTURE_MATRIX);

        AbstractTexture texture = client.getTextureManager().getTexture(textureId);
        RenderTarget mainTarget = client.gameRenderer.mainRenderTarget();
        GpuTextureView colorTexture = mainTarget.getColorTextureView();
        assert colorTexture != null;

        try (RenderPass renderPass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(
                        () -> Stargazer.MOD_ID + " depth-pinned stars",
                        colorTexture,
                        Optional.empty(),
                        mainTarget.getDepthTextureView(),
                        OptionalDouble.empty()
                )) {
            renderPass.setPipeline(pipeline);
            RenderSystem.bindDefaultUniforms(renderPass);

            renderPass.setUniform("DynamicTransforms", dynamicTransforms);
            renderPass.bindTexture("Sampler0", texture.getTextureView(), texture.getSampler());

            renderPass.setVertexBuffer(0, info.vertexBuffer().slice());
            renderPass.setIndexBuffer(info.indexBuffer(), info.indexType());
            renderPass.drawIndexed(info.indexCount(), 1, info.firstIndex(), info.baseVertex(), 0);
        }
    }

    public static void close() {
        STAGED_BUFFER.close();
    }

    // --- MATH & UTILS ---
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

    // --- RECORDS ---
    private record StarSettings(Identifier texture, int starCount, float starRadius) {
        @Nullable
        private static StarSettings forDimension(ResourceKey<DimensionType> dimensionType) {
            if (dimensionType.equals(CustomWorlds.COSMIC_TYPE)) {
                return new StarSettings(STAR_TEXTURE, DEFAULT_STAR_COUNT, DEFAULT_STAR_RADIUS);
            }
            if (dimensionType.equals(CustomWorlds.RED_ORB_TYPE)) {
                return new StarSettings(STAR_TEXTURE2, DEFAULT_STAR_COUNT, RED_ORB_STAR_RADIUS);
            }
            if (dimensionType.equals(CustomWorlds.WANDER_TYPE)) {
                return new StarSettings(STAR_TEXTURE3, WANDER_STAR_COUNT, WANDER_STAR_RADIUS);
            }
            return null;
        }
    }

    private record StarRenderState(
            boolean enabled,
            long gameTime,
            Vec3 cameraPos,
            @Nullable StarSettings settings
    ) {
        private static final StarRenderState DISABLED = new StarRenderState(false, 0L, Vec3.ZERO, null);
    }}
