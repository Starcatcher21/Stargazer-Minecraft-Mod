package com.github.starcatcher21.stargazer.block.clases.nored;

import com.github.starcatcher21.stargazer.block.ModBlock;
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
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NoRedBlockEntityRenderer<T extends NoRedBlockEntity> implements BlockEntityRenderer<T, NoRedBlockEntityRenderState> {
    public NoRedBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // outside
//        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
//        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
//        this.renderSide(entity, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
//        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
//        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
//        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
        // inside
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.UP);
    }

    private void renderSide(
            EnumSet<Direction> directions, Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3, Direction side
    ) {
        if (directions.contains(side)) {
            renderNormal(side, pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }

    }

    private void renderNormal(Direction side, Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3) {
        if (side == Direction.EAST || side == Direction.WEST) {
            x0 = sizeDown(x0);
            x1 = sizeDown(x1);
        }
        if (side == Direction.DOWN || side == Direction.UP) {
            y0 = sizeDown(y0);
            y1 = sizeDown(y1);
        }
        if (side == Direction.NORTH || side == Direction.SOUTH) {
            z0 = sizeDown(z0);
            z1 = sizeDown(z1);
            z2 = sizeDown(z2);
            z3 = sizeDown(z3);
        }
        consumer.addVertex(pose, x0, y0, z0);
        consumer.addVertex(pose, x1, y0, z1);
        consumer.addVertex(pose, x1, y1, z2);
        consumer.addVertex(pose, x0, y1, z3);
    }

    private float sizeDown(float x) {
        if (x == 0) {
            return x + 0.001F;
        } else {
            return x - 0.001F;
        }
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.NO_RED;
    }

    @Override
    public NoRedBlockEntityRenderState createRenderState() {
        return new NoRedBlockEntityRenderState();
    }

    @Override
    public void submit(NoRedBlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        queue.submitCustomGeometry(
                matrices,
                this.getLayer(),
                (matricesEntry, vertexConsumer) -> this.renderSides(state.sides, matricesEntry.pose(), vertexConsumer)
        );
    }
    @Override
    public void extractRenderState(
            T entity,
            NoRedBlockEntityRenderState cosmicBlockEntityRenderState,
            float f,
            Vec3 vec3d,
            ModelFeatureRenderer.CrumblingOverlay crumblingOverlayCommand
    ) {
        BlockEntityRenderer.super.extractRenderState(entity, cosmicBlockEntityRenderState, f, vec3d, crumblingOverlayCommand);
        cosmicBlockEntityRenderState.sides.clear();
        Level world = entity.getLevel();

        for (Direction direction : Direction.values()) {
            try {
                if (!world.getBlockState(entity.getBlockPos().relative(direction, 1)).getBlock().equals(ModBlock.NORED_BLOCK)) {
                    cosmicBlockEntityRenderState.sides.add(direction);
                }
            } catch (Exception e) {}
        }
    }
}
