package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.entity.DataTickets;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;
import com.geckolib.model.GeoModel;
import com.geckolib.renderer.GeoBlockRenderer;
import com.geckolib.renderer.base.GeoRenderState;

public class StarDisplayRenderer<R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<StarDisplayEntity, R> {
    public StarDisplayRenderer(GeoModel<StarDisplayEntity> model) {
            super(model);
    }

    @Override
    public void addRenderData(StarDisplayEntity animatable, @Nullable Void relatedObject, R renderState, float partialTick) {
        renderState.addGeckolibData(DataTickets.INVENTORY, animatable.getItem());
        renderState.addGeckolibData(DataTickets.ROTATION, animatable.getRotation());
    }

    @Override
    public void submit(R state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        super.submit(state, matrices, queue, cameraState);

        ItemStack stack = state.getGeckolibData(DataTickets.INVENTORY);
        if (stack == null || stack.isEmpty()) {
            return;
        }

        ItemModelResolver modelManager = Minecraft.getInstance().getItemModelResolver();
        ItemStackRenderState itemRenderState = new ItemStackRenderState();

        modelManager.appendItemLayers(itemRenderState, stack, ItemDisplayContext.GROUND, null, null, 0);

        matrices.pushPose();
        matrices.translate(0.5f, 0.5f, 0.5f);
        matrices.scale(1.0f, 1.0f, 1.0f);
        matrices.mulPose(Axis.YP.rotationDegrees(state.getGeckolibData(DataTickets.ROTATION)));

        int overlay = OverlayTexture.NO_OVERLAY;
        itemRenderState.submit(matrices, queue, state.lightCoords, overlay, 0);
        matrices.popPose();


        ItemModelResolver modelManager2 = Minecraft.getInstance().getItemModelResolver();
        ItemStackRenderState itemRenderState2 = new ItemStackRenderState();
        modelManager2.appendItemLayers(itemRenderState2, Blocks.GLASS.asItem().getDefaultInstance(), ItemDisplayContext.GROUND, null, null, 0);

        matrices.pushPose();
        matrices.translate(0.5f, 0.35f, 0.5f);
        matrices.scale(2f, 2f, 2f);
        matrices.mulPose(Axis.YP.rotationDegrees(state.getGeckolibData(DataTickets.ROTATION) * -1));

        itemRenderState2.submit(matrices, queue, state.lightCoords, overlay, 0);
        matrices.popPose();


        if (stack.getCount() > 1) {
            String countText = String.valueOf(stack.getCount());
            FormattedCharSequence orderedText = Component.nullToEmpty(countText).getVisualOrderText();

            float textWidth = Minecraft.getInstance().font.width(orderedText);
            float textHeight = 8.0f;

            matrices.pushPose();
            matrices.translate(0.5f, 0.3f, 0.5f);
            matrices.scale(1 / 24f, 1 / 24f, 1 / 24f);
            matrices.mulPose(Axis.YP.rotationDegrees(state.getGeckolibData(DataTickets.ROTATION)));
            matrices.mulPose(Axis.XP.rotationDegrees(90));
            queue.submitText(
                    matrices,
                    -textWidth / 2f,
                    -textHeight / 2f,
                    Component.nullToEmpty(countText).getVisualOrderText(),
                    false,
                    Font.DisplayMode.NORMAL,
                    state.lightCoords,
                    0xFFFFFFFF,
                    0x00000000,
                    0x00000000
            );
            matrices.popPose();
        }
    }
}
