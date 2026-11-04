package com.github.starcatcher21.stargazer.block.clases.crystalizer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.Star;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

//? if >= 26.2 {
/*import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

*///? }
//? if < 26.2 {
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
//? }

public class CrystalizerRenderer implements BlockEntityRenderer<CrystalizerEntity
        //? if >= 26.2
        //, CrystalizerRenderState
        > {

    //? if < 26.2 {
    private final BlockRenderDispatcher blockRenderer;
    //? }
    private static final float ROTATION_SPEED = 0.03f;

    public CrystalizerRenderer(BlockEntityRendererProvider.Context ctx) {
        //? if < 26.2 {
        this.blockRenderer = Minecraft.getInstance().getBlockRenderer();
         //? }
    }

    //? if >= 26.2 {
    /*@Override
    public CrystalizerRenderState createRenderState() {
        return new CrystalizerRenderState();
    }

    @Override
    public void extractRenderState(CrystalizerEntity entity,
                                   CrystalizerRenderState state,
                                   float partialTick,
                                   Vec3 cameraPos,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, cameraPos, crumblingOverlay);
        state.state = entity.getBlockState();
        state.items = entity.items;
        Level level = entity.getLevel();
        float time = (level != null ? level.getGameTime() : 0L) + partialTick;
        state.rotation = time * ROTATION_SPEED;
    }

    @Override
    public void submit(CrystalizerRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
        ItemStackRenderState itemStackRenderState2 = new ItemStackRenderState();
        ItemStackRenderState itemStackRenderState3 = new ItemStackRenderState();
        var itemModelResolver = Minecraft.getInstance().getItemModelResolver();
        itemModelResolver.appendItemLayers(itemStackRenderState, state.items.get(0), ItemDisplayContext.GROUND, null, null, 0);
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.75f, 0.5f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.YP.rotation(state.rotation));
        itemStackRenderState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
        itemModelResolver.appendItemLayers(itemStackRenderState2, state.items.get(1), ItemDisplayContext.GROUND, null, null, 0);
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.45f, 0.5f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        itemStackRenderState2.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
        itemModelResolver.appendItemLayers(itemStackRenderState3, state.items.get(2), ItemDisplayContext.GROUND, null, null, 0);
        poseStack.pushPose();
        poseStack.translate(0.5f, 1.0f, 0.5f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.mulPose(Axis.YP.rotation(state.rotation));
        poseStack.mulPose(Axis.ZP.rotation(state.rotation));
        itemStackRenderState3.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
    *///? }

    //? if < 26.2 {
    @Override
    public void render(CrystalizerEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        ItemStack stack = entity.getInput();
        ItemStack stack2 = entity.getInput2();
        ItemStack stack3 = entity.getOutput();

        Level level = entity.getLevel();
        float time = (level != null ? level.getGameTime() : 0L) + partialTick;
        float rotation = time * ROTATION_SPEED;

        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        int overlay = OverlayTexture.NO_OVERLAY;

        var model = this.blockRenderer.getBlockModel(entity.getBlockState());

        poseStack.pushPose();
        this.blockRenderer.getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.SOLID),
                entity.getBlockState(),
                model,
                1.0f, 1.0f, 1.0f,
                packedLight,
                packedOverlay
        );
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.75f, 0.5f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.YP.rotation(rotation));
        itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND,
                packedLight, overlay, poseStack, bufferSource, level, 0);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(0.45f, 0.45f, 0.35f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        itemRenderer.renderStatic(stack2, ItemDisplayContext.GROUND,
                packedLight, overlay, poseStack, bufferSource, level, 0);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.translate(0.45f, 1.0f, 0.35f);
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.mulPose(Axis.YP.rotation(rotation));
        poseStack.mulPose(Axis.ZP.rotation(rotation));
        itemRenderer.renderStatic(stack3, ItemDisplayContext.GROUND,
                packedLight, overlay, poseStack, bufferSource, level, 0);
        poseStack.popPose();
    }
    //? }
}
