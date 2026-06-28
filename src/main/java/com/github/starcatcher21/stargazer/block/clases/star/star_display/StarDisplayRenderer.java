package com.github.starcatcher21.stargazer.block.clases.star.star_display;

import com.github.starcatcher21.stargazer.entity.DataTickets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

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
    public void render(R state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        super.render(state, matrices, queue, cameraState);

        ItemStack stack = state.getGeckolibData(DataTickets.INVENTORY);
        if (stack == null || stack.isEmpty()) {
            return;
        }

        ItemModelManager modelManager = MinecraftClient.getInstance().getItemModelManager();
        ItemRenderState itemRenderState = new ItemRenderState();

        modelManager.update(itemRenderState, stack, ItemDisplayContext.GROUND, null, null, 0);

        matrices.push();
        matrices.translate(0.5f, 0.5f, 0.5f);
        matrices.scale(1.0f, 1.0f, 1.0f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.getGeckolibData(DataTickets.ROTATION)));

        int overlay = OverlayTexture.DEFAULT_UV;
        itemRenderState.render(matrices, queue, state.lightmapCoordinates, overlay, 0);
        matrices.pop();


        if (stack.getCount() > 1) {
            String countText = String.valueOf(stack.getCount());
            OrderedText orderedText = Text.of(countText).asOrderedText();

            float textWidth = MinecraftClient.getInstance().textRenderer.getWidth(orderedText);
            float textHeight = 8.0f;

            matrices.push();
            matrices.translate(0.5f, 0.3f, 0.5f);
            matrices.scale(1 / 24f, 1 / 24f, 1 / 24f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.getGeckolibData(DataTickets.ROTATION)));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            queue.submitText(
                    matrices,
                    -textWidth / 2f,
                    -textHeight / 2f,
                    Text.of(countText).asOrderedText(),
                    false,
                    TextRenderer.TextLayerType.NORMAL,
                    state.lightmapCoordinates,
                    0xFFFFFFFF,
                    0x00000000,
                    0x00000000
            );
            matrices.pop();
        }
    }
}
