package com.github.starcatcher21.stargazer.block.clases.star.barrier;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.star.border.BorderBlock;
import com.github.starcatcher21.stargazer.renderer.CustomRenderLayers;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class StarBarrierBlockEntityRenderer<T extends StarBarrierBlockEntity> implements BlockEntityRenderer<T> {
    public StarBarrierBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(T entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        try {
            BlockState state = entity.getWorld().getBlockState(entity.getPos());
            if (!(state.getBlock() instanceof AirBlock)) {
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                this.renderSides(entity, matrix4f, vertexConsumers.getBuffer(this.getLayer()));
            }
        } catch (Exception e) {
            Stargazer.LOGGER.error(e.toString());
        }
    }

    private void renderSides(T entity, Matrix4f matrix, VertexConsumer vertexConsumer) {
        // outside
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(entity, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(
            T entity, Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3, Direction side
    ) {
        switch (shoudRender(entity, side)) {
            case "normal" -> renderNormal(pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3);
            case "small" -> renderSamll(pose, consumer, x0, x1, y0, y1, z0, z1, z2, z3, side);
        }

    }

    private void renderNormal(Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3) {
        consumer.vertex(pose, x0, y0, z0);
        consumer.vertex(pose, x1, y0, z1);
        consumer.vertex(pose, x1, y1, z2);
        consumer.vertex(pose, x0, y1, z3);
    }

    private void renderSamll(Matrix4f pose, VertexConsumer consumer, float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3, Direction side) {
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
        consumer.vertex(pose, x0, y0, z0);
        consumer.vertex(pose, x1, y0, z1);
        consumer.vertex(pose, x1, y1, z2);
        consumer.vertex(pose, x0, y1, z3);
    }

    private float sizeDown(float x) {
        if (x == 0) {
            return x + 0.001F;
        } else {
            return x - 0.001F;
        }
    }

    private String shoudRender(T entity, Direction dir) {
        String ret = "false";
        switch (dir) {
            case WEST -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().west(1)).getBlock());
            case EAST -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().east(1)).getBlock());
            case UP -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().up(1)).getBlock());
            case DOWN -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().down(1)).getBlock());
            case NORTH -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().north(1)).getBlock());
            case SOUTH -> ret = rend(entity, entity.getWorld().getBlockState(entity.getPos().south(1)).getBlock());
        }
        return ret;
    }
    private String rend(T entity, Block block) {
        if (!(block instanceof StarBarrierBlock)) {
            if (block instanceof AirBlock || block instanceof BorderBlock) {
                return "normal";
            } else {
                return "false";
            }
        } else {
            return "false";
        }
    }

    protected RenderLayer getLayer() {
        return CustomRenderLayers.STAR_BARRIER;
    }

}
