package com.github.starcatcher21.stargazer.mixin;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeFeatureRenderer.class)
public abstract class CapeMixin {
    @Shadow
    @Final
    private BipedEntityModel<PlayerEntityRenderState> model;

    @Shadow
    protected abstract boolean hasCustomModelForLayer(ItemStack stack, EquipmentModel.LayerType layerType);

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/PlayerEntityRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void captureAndModify(
            MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int i, PlayerEntityRenderState playerEntityRenderState, float f, float g, CallbackInfo ci         // 1. Captured directly from the method arguments
    ) {
        if (!playerEntityRenderState.invisible && playerEntityRenderState.capeVisible) {
            SkinTextures skinTextures = playerEntityRenderState.skinTextures;
            if (skinTextures.cape() != null) {
                if (!this.hasCustomModelForLayer(playerEntityRenderState.equippedChestStack, EquipmentModel.LayerType.WINGS)) {
                    matrixStack.push();
                    if (this.hasCustomModelForLayer(playerEntityRenderState.equippedChestStack, EquipmentModel.LayerType.HUMANOID)) {
                        matrixStack.translate(0.0F, -0.053125F, 0.06875F);
                    }

                    orderedRenderCommandQueue.submitModel(
                            this.model,
                            playerEntityRenderState,
                            matrixStack,
                            RenderLayers.entityTranslucent(skinTextures.cape().texturePath()),
                            i,
                            OverlayTexture.DEFAULT_UV,
                            playerEntityRenderState.outlineColor,
                            null
                    );
                    matrixStack.pop();
                }
            }
            ci.cancel();
        }
    }
}
