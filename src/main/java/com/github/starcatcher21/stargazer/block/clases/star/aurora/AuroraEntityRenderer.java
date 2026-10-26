package com.github.starcatcher21.stargazer.block.clases.star.aurora;

import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.*;

import java.util.EnumSet;
//? if >= 26.2 {
/*import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
*///?} else {
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
//?}
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

//? if >= 26.2 {
/*public class AuroraEntityRenderer<T extends AuroraEntity>
        implements BlockEntityRenderer<T, AuroraBlockEntityRenderState> {

    public AuroraEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(EnumSet<Direction> set, Matrix4f pose, VertexConsumer consumer,
                            float x0, float x1, float y0, float y1,
                            float z0, float z1, float z2, float z3, Direction side) {
        if (set.contains(side)) {
            renderNormal(pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }
    }

    private void renderNormal(Matrix4f pose, VertexConsumer consumer,
                              float x0, float x1, float y0, float y1,
                              float z0, float z1, float z2, float z3) {
        consumer.addVertex(pose, x0, y0, z0).setColor(255, 255, 255, 255).setUv(0, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y0, z1).setColor(255, 255, 255, 255).setUv(1, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y1, z2).setColor(255, 255, 255, 255).setUv(1, 1).setLight(15728880);
        consumer.addVertex(pose, x0, y1, z3).setColor(255, 255, 255, 255).setUv(0, 1).setLight(15728880);
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.AURORA;
    }

    @Override
    public AuroraBlockEntityRenderState createRenderState() {
        return new AuroraBlockEntityRenderState();
    }

    @Override
    public void submit(AuroraBlockEntityRenderState state, PoseStack matrices,
                       SubmitNodeCollector queue, CameraRenderState cameraState) {
        queue.submitCustomGeometry(matrices, this.getLayer(),
            (pose, vertexConsumer) ->
                this.renderSides(state.sides, pose.pose(), vertexConsumer));
    }

    @Override
    public void extractRenderState(T entity, AuroraBlockEntityRenderState state,
                                   float f, Vec3 vec3d,
                                   ModelFeatureRenderer.CrumblingOverlay overlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, f, vec3d, overlay);
        state.sides.clear();
        for (Direction direction : Direction.values()) {
            state.sides.add(direction);
        }
    }
}
*///?} else {
public class AuroraEntityRenderer implements BlockEntityRenderer<AuroraEntity> {

    public AuroraEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(EnumSet<Direction> set, Matrix4f pose, VertexConsumer consumer,
                            float x0, float x1, float y0, float y1,
                            float z0, float z1, float z2, float z3, Direction side) {
        if (set.contains(side)) {
            renderNormal(pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }
    }

    private void renderNormal(Matrix4f pose, VertexConsumer consumer,
                              float x0, float x1, float y0, float y1,
                              float z0, float z1, float z2, float z3) {
        consumer.addVertex(pose, x0, y0, z0).setColor(255, 255, 255, 255).setUv(0, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y0, z1).setColor(255, 255, 255, 255).setUv(1, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y1, z2).setColor(255, 255, 255, 255).setUv(1, 1).setLight(15728880);
        consumer.addVertex(pose, x0, y1, z3).setColor(255, 255, 255, 255).setUv(0, 1).setLight(15728880);
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.AURORA;
    }

    @Override
    public void render(AuroraEntity entity, float tickDelta,
                       PoseStack matrices, MultiBufferSource vertexConsumers,
                       int light, int overlay) {
        // Behaviour matches the original: every direction is always drawn.
        EnumSet<Direction> sides = EnumSet.allOf(Direction.class);

        VertexConsumer consumer = vertexConsumers.getBuffer(this.getLayer());
        this.renderSides(sides, matrices.last().pose(), consumer);
    }
}
//?}
