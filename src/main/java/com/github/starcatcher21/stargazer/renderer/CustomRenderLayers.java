package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.block.clases.star.aurora.leaves.Aurora;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlock;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeaves;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;

public class CustomRenderLayers {
    public static final RenderType COSMIC = RenderType.create(
            "cosmic",
            RenderSetup.builder(CustomRederPipelines.COSMIC)
                    .withTexture("Sampler0", CosmicBlock.TEXTURE)
                    .withTexture("Sampler1", CosmicBlock.TEXTURE)
                    .createRenderSetup()
    );
    public static final RenderType STAR_LEAVES = RenderType.create("star_leaves", RenderSetup.builder(CustomRederPipelines.STAR_LEAVES).withTexture("star", StarLeaves.TEXTURE).createRenderSetup());
    public static final RenderType STAR_BARRIER = RenderType.create("star_barrier", RenderSetup.builder(CustomRederPipelines.STAR_BARRIER).withTexture("starbarrier", StarBarrierBlock.TEXTURE).createRenderSetup());
    public static final RenderType NEGATIVE = RenderType.create("negative", RenderSetup.builder(CustomRederPipelines.NEGATIVE).createRenderSetup());
    public static final RenderType NO_RED = RenderType.create("no_red", RenderSetup.builder(CustomRederPipelines.NO_RED).createRenderSetup());
    public static final RenderType NO_GREEN = RenderType.create("no_green", RenderSetup.builder(CustomRederPipelines.NO_GREEN).createRenderSetup());
    public static final RenderType NO_BLUE = RenderType.create("no_blue", RenderSetup.builder(CustomRederPipelines.NO_BLUE).createRenderSetup());
    public static final RenderType AURORA = RenderType.create("aurora", RenderSetup.builder(CustomRederPipelines.AURORA).withTexture("aurora", Aurora.TEXTURE).createRenderSetup());
}
