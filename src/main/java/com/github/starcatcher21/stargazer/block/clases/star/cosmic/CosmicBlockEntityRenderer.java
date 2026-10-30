package com.github.starcatcher21.stargazer.block.clases.star.cosmic;

import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//? }
//? if >= 26.2 {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
//?} else {
/*import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
*///?}
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import java.util.EnumSet;

//? if fabric {
@Environment(value = EnvType.CLIENT)
 //? }
//? if >= 26.2 {
public class CosmicBlockEntityRenderer<T extends CosmicBlockEntity>
        implements BlockEntityRenderer<T, CosmicBlockEntityRenderState> {

    public CosmicBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // outside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
        // inside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.UP);
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
        return CustomRenderLayers.COSMIC;
    }

    @Override
    public CosmicBlockEntityRenderState createRenderState() {
        return new CosmicBlockEntityRenderState();
    }

    @Override
    public void submit(CosmicBlockEntityRenderState state, PoseStack matrices,
                       SubmitNodeCollector queue, CameraRenderState cameraState) {
        queue.submitCustomGeometry(matrices, this.getLayer(),
            (matricesEntry, vertexConsumer) ->
                this.renderSides(state.sides, matricesEntry.pose(), vertexConsumer));
    }

    @Override
    public void extractRenderState(T entity, CosmicBlockEntityRenderState state,
                                   float f, Vec3 vec3d,
                                   ModelFeatureRenderer.CrumblingOverlay overlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, f, vec3d, overlay);
        state.sides.clear();
        Level world = entity.getLevel();
        if (world == null) return;

        for (Direction direction : Direction.values()) {
            if (world.getBlockState(entity.getBlockPos().relative(direction, 1)).isAir()) {
                state.sides.add(direction);
            }
        }
    }
}
//?} else {
/*public class CosmicBlockEntityRenderer implements BlockEntityRenderer<CosmicBlockEntity> {

    public CosmicBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // outside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
        // inside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.UP);
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
        return CustomRenderLayers.COSMIC;
    }

    @Override
    public void render(CosmicBlockEntity entity, float tickDelta,
                       PoseStack matrices, MultiBufferSource vertexConsumers,
                       int light, int overlay) {
        Level world = entity.getLevel();
        if (world == null) return;

        EnumSet<Direction> sides = EnumSet.noneOf(Direction.class);
        for (Direction direction : Direction.values()) {
            try {
                if (world.getBlockState(entity.getBlockPos().relative(direction, 1)).isAir()) {
                    sides.add(direction);
                }
            } catch (Exception ignored) {}
        }
        if (sides.isEmpty()) return;

        VertexConsumer consumer = vertexConsumers.getBuffer(this.getLayer());
        this.renderSides(sides, matrices.last().pose(), consumer);
    }
}
*///?}
