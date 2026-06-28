package com.github.starcatcher21.stargazer.block.clases.star.barrier;

import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Matrix4f;

import java.util.EnumSet;

public class StarBarrierBlockEntityRenderer<T extends StarBarrierBlockEntity> implements BlockEntityRenderer<T, StarBarrierBlockEntityRenderState> {
    public StarBarrierBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
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
        consumer.vertex(pose, x0, y0, z0);
        consumer.vertex(pose, x1, y0, z1);
        consumer.vertex(pose, x1, y1, z2);
        consumer.vertex(pose, x0, y1, z3);
    }

    protected RenderLayer getLayer() {
        return CustomRenderLayers.STAR_BARRIER;
    }

    @Override
    public StarBarrierBlockEntityRenderState createRenderState() {
        return new StarBarrierBlockEntityRenderState();
    }

    @Override
    public void render(StarBarrierBlockEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        queue.submitCustom(
                matrices,
                this.getLayer(),
                (matricesEntry, vertexConsumer) -> this.renderSides(state.sides, matricesEntry.getPositionMatrix(), vertexConsumer)
        );
    }
    public void updateRenderState(
            T entity,
            StarBarrierBlockEntityRenderState cosmicBlockEntityRenderState,
            float f,
            Vec3d vec3d,
            ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand
    ) {
        BlockEntityRenderer.super.updateRenderState(entity, cosmicBlockEntityRenderState, f, vec3d, crumblingOverlayCommand);
        cosmicBlockEntityRenderState.sides.clear();
        World world = entity.getWorld();

        for (Direction direction : Direction.values()) {
            try {
                if (world.getBlockState(entity.getPos().offset(direction, 1)).isTransparent()) {
                    cosmicBlockEntityRenderState.sides.add(direction);
                }
            } catch (Exception e) {}
        }
    }
}
