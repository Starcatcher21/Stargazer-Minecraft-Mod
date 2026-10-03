package com.github.starcatcher21.stargazer.block.clases.star.barrier;

import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;

import java.util.EnumSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StarBarrierBlockEntityRenderer<T extends StarBarrierBlockEntity> implements BlockEntityRenderer<T, StarBarrierBlockEntityRenderState> {
    public StarBarrierBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // outside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(
            EnumSet<Direction> directions, Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3, Direction side
    ) {
        if (directions.contains(side)) {
            renderNormal(pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }

    }

    private void renderNormal(Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3) {
        consumer.addVertex(pose, x0, y0, z0).setColor(255, 255, 255, 255).setUv(0, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y0, z1).setColor(255, 255, 255, 255).setUv(1, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y1, z2).setColor(255, 255, 255, 255).setUv(1, 1).setLight(15728880);
        consumer.addVertex(pose, x0, y1, z3).setColor(255, 255, 255, 255).setUv(0, 1).setLight(15728880);
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.STAR_BARRIER;
    }

    @Override
    public StarBarrierBlockEntityRenderState createRenderState() {
        return new StarBarrierBlockEntityRenderState();
    }

    @Override
    public void submit(StarBarrierBlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        queue.submitCustomGeometry(
                matrices,
                this.getLayer(),
                (matricesEntry, vertexConsumer) -> this.renderSides(state.sides, matricesEntry.pose(), vertexConsumer)
        );
    }
    @Override
    public void extractRenderState(
            T entity,
            StarBarrierBlockEntityRenderState cosmicBlockEntityRenderState,
            float f,
            Vec3 vec3d,
            ModelFeatureRenderer.CrumblingOverlay crumblingOverlayCommand
    ) {
        BlockEntityRenderer.super.extractRenderState(entity, cosmicBlockEntityRenderState, f, vec3d, crumblingOverlayCommand);
        cosmicBlockEntityRenderState.sides.clear();
        Level world = entity.getLevel();

        for (Direction direction : Direction.values()) {
            try {
                if (world.getBlockState(entity.getBlockPos().relative(direction, 1)).propagatesSkylightDown()) {
                    cosmicBlockEntityRenderState.sides.add(direction);
                }
            } catch (Exception e) {}
        }
    }
}
