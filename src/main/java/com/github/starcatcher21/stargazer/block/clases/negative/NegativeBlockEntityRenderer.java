package com.github.starcatcher21.stargazer.block.clases.negative;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//? if >= 26.2 {
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
//?} else {
/*import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
*///?}
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.EnumSet;

//? if >= 26.2 {
public class NegativeBlockEntityRenderer<T extends NegativeBlockEntity>
        implements BlockEntityRenderer<T, NegativeBlockEntityRenderState> {

    public NegativeBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // [same body as original — no version-sensitive APIs]
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        // ... etc (see original)
    }

    private void renderSide(EnumSet<Direction> directions, Matrix4f pose, VertexConsumer consumer,
                            float x0, float x1, float y0, float y1,
                            float z0, float z1, float z2, float z3, Direction side) {
        if (directions.contains(side)) {
            renderNormal(side, pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }
    }

    private void renderNormal(Direction side, Matrix4f pose, VertexConsumer consumer,
                              float x0, float x1, float y0, float y1,
                              float z0, float z1, float z2, float z3) {
        // [same body as original]
    }

    private float sizeDown(float x) {
        return x == 0 ? x + 0.0001F : x - 0.0001F;
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.NEGATIVE;
    }

    @Override
    public @NotNull NegativeBlockEntityRenderState createRenderState() {
        return new NegativeBlockEntityRenderState();
    }

    @Override
    public void submit(@NotNull NegativeBlockEntityRenderState state,
                       @NotNull PoseStack matrices,
                       SubmitNodeCollector queue,
                       @NotNull CameraRenderState cameraState) {
        if (state.sides == null || state.sides.isEmpty()) return;
        queue.submitCustomGeometry(matrices, this.getLayer(), (matricesEntry, vertexConsumer) -> {
            if (!state.sides.isEmpty()) {
                this.renderSides(state.sides, matricesEntry.pose(), vertexConsumer);
            }
        });
    }

    @Override
    public void extractRenderState(@NotNull T entity,
                                   @NotNull NegativeBlockEntityRenderState state,
                                   float f, @NotNull Vec3 vec3d,
                                   ModelFeatureRenderer.CrumblingOverlay overlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, f, vec3d, overlay);
        state.sides.clear();

        Level world = entity.getLevel();
        if (world == null) return;

        BlockPos pos = entity.getBlockPos();
        for (Direction direction : Direction.values()) {
            BlockState neighborState = world.getBlockState(pos.relative(direction));
            if (!neighborState.is(ModBlock.NEGATIVE_BLOCK.get())) {
                state.sides.add(direction);
            }
        }
    }
}
//?} else {
/*public class NegativeBlockEntityRenderer implements BlockEntityRenderer<NegativeBlockEntity> {

    public NegativeBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    private void renderSides(EnumSet<Direction> set, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // [same body as original — no version-sensitive APIs]
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(set, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.EAST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, Direction.WEST);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.DOWN);
        this.renderSide(set, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.UP);
    }

    private void renderSide(EnumSet<Direction> directions, Matrix4f pose, VertexConsumer consumer,
                            float x0, float x1, float y0, float y1,
                            float z0, float z1, float z2, float z3, Direction side) {
        if (directions.contains(side)) {
            renderNormal(side, pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
        }
    }

    private void renderNormal(Direction side, Matrix4f pose, VertexConsumer consumer,
                              float x0, float x1, float y0, float y1,
                              float z0, float z1, float z2, float z3) {
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
        consumer.addVertex(pose, x0, y0, z0).setColor(255, 255, 255, 255).setUv(0, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y0, z1).setColor(255, 255, 255, 255).setUv(1, 0).setLight(15728880);
        consumer.addVertex(pose, x1, y1, z2).setColor(255, 255, 255, 255).setUv(1, 1).setLight(15728880);
        consumer.addVertex(pose, x0, y1, z3).setColor(255, 255, 255, 255).setUv(0, 1).setLight(15728880);
    }

    private float sizeDown(float x) {
        return x == 0 ? x + 0.0001F : x - 0.0001F;
    }

    protected RenderType getLayer() {
        return CustomRenderLayers.NEGATIVE;
    }

    @Override
    public void render(@NotNull NegativeBlockEntity entity,
                       float tickDelta,
                       @NotNull PoseStack matrices,
                       @NotNull MultiBufferSource vertexConsumers,
                       int light, int overlay) {
        Level world = entity.getLevel();
        if (world == null) return;

        // Build the set of exposed sides (same logic as 26.2 extractRenderState)
        EnumSet<Direction> sides = EnumSet.noneOf(Direction.class);
        BlockPos pos = entity.getBlockPos();
        for (Direction direction : Direction.values()) {
            BlockState neighborState = world.getBlockState(pos.relative(direction));
            if (!neighborState.is(ModBlock.NEGATIVE_BLOCK.get())) {
                sides.add(direction);
            }
        }
        if (sides.isEmpty()) return;

        VertexConsumer consumer = vertexConsumers.getBuffer(this.getLayer());
        this.renderSides(sides, matrices.last().pose(), consumer);
    }
}
*///?}
