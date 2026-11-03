package com.github.starcatcher21.stargazer.block.clases.grave;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.model.Model;

//? if >= 26.2 {
/*import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
*///? }
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

//? if < 26.2 {
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
//? }

public class GraveRenderer implements BlockEntityRenderer<GraveEntity
        //? if >= 26.2
        //, GraveRenderState
        > {

    //? if < 26.2 {
    private final BlockRenderDispatcher blockRenderer;
    //? }

    public GraveRenderer(BlockEntityRendererProvider.Context ctx) {
        //? if < 26.2 {
        this.blockRenderer = Minecraft.getInstance().getBlockRenderer();
         //? }
    }

    //? if >= 26.2 {
    /*@Override
    public GraveRenderState createRenderState() {
        return new GraveRenderState();
    }

    @Override
    public void extractRenderState(GraveEntity entity,
                                   GraveRenderState state,
                                   float partialTick,
                                   Vec3 cameraPos,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, cameraPos, crumblingOverlay);
        state.graveState = entity.getBlockState();
        state.dirtState = entity.getLevel().getBlockState(entity.getBlockPos().below());
        state.activated = state.graveState.getValue(Grave.ACTIVATED);
    }

    @Override
    public void submit(GraveRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {
        if (!state.activated) {
            submitDirtModels(poseStack, collector, state.dirtState, state.lightCoords, state.lightCoords);
        }
    }

    private void submitDirtModels(PoseStack poseStack, SubmitNodeCollector collector,
                                  BlockState dirtState, int packedLight, int ovelayCoords) {
        final float scale = 0.25f;
        final float yOffset = -0.35f;

        float[][] positions = {
                {0.5f, 0.5f}, {1.5f, 0.25f}, {2.5f, 0.5f}, {2.75f, 1.5f},
                {2.5f, 2.5f}, {1.5f, 2.75f}, {0.5f, 2.5f}, {0.25f, 1.5f}
        };

        for (float[] pos : positions) {
            poseStack.pushPose();
            poseStack.scale(scale, scale, scale);
            poseStack.translate(pos[0], yOffset, pos[1]);
            //? if neoforge {
            BlockModelResolver modelManager = Minecraft.getInstance().getBlockModelResolver();
            //? } else {
            /^BlockModelResolver modelManager = new BlockModelResolver(Minecraft.getInstance().modelManager);
            ^///? }
            BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
            modelManager.update(blockModelRenderState, dirtState, BlockDisplayContext.create());

            blockModelRenderState.submit(poseStack, collector, packedLight, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }
    *///? }

    //? if < 26.2 {
    @Override
    public void render(GraveEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState graveState = entity.getBlockState();
        BlockState dirtState = entity.getLevel().getBlockState(entity.getBlockPos().below());

        BakedModel model = this.blockRenderer.getBlockModel(graveState);

        this.blockRenderer.getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.SOLID),
                graveState,
                model,
                1.0f, 1.0f, 1.0f,
                packedLight,
                packedOverlay
        );

        BakedModel dirtModel;
        if (dirtState.getBlock().equals(Blocks.GRASS_BLOCK)) {
            dirtModel = this.blockRenderer.getBlockModel(Blocks.DIRT.defaultBlockState());
        } else {
            dirtModel = this.blockRenderer.getBlockModel(dirtState);
        }
        BakedModel errorModel = this.blockRenderer.getBlockModel(Blocks.DIRT.defaultBlockState());
        if (!graveState.getValue(Grave.ACTIVATED)) {
            try {
                renderDirtModel(graveState, poseStack, bufferSource, dirtModel, packedLight, packedOverlay);
            } catch (Exception e) {
                poseStack.popPose();
                renderDirtModel(graveState, poseStack, bufferSource, errorModel, packedLight, packedOverlay);
            }
        }
    }

    private void rend(BlockState graveState, PoseStack poseStack, MultiBufferSource bufferSource,
                      BakedModel dirtModel, int packedLight, int packedOverlay) {
        this.blockRenderer.getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.CUTOUT),
                graveState,
                dirtModel,
                1.0f, 1.0f, 1.0f,
                packedLight,
                packedOverlay
        );
    }

    private void renderDirtModel(BlockState graveState, PoseStack poseStack, MultiBufferSource bufferSource,
                                 BakedModel dirtModel, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0.5, -0.35, 0.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(1.5, -0.35, 0.25);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(2.5, -0.35, 0.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(2.75, -0.35, 1.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(2.5, -0.35, 2.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(1.5, -0.35, 2.75);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0.5, -0.35, 2.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0.25, -0.35, 1.5);
        rend(graveState,poseStack,bufferSource, dirtModel, packedLight, packedOverlay);
        poseStack.popPose();
    }
    //? }
}
