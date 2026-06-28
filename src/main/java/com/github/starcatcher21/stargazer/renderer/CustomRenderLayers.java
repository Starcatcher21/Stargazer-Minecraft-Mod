package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlock;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeaves;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;

public class CustomRenderLayers {
    public static final RenderLayer COSMIC = RenderLayer.of(
            "cosmic",
            RenderSetup.builder(CustomRederPipelines.COSMIC)
                    .texture("Sampler0", CosmicBlock.TEXTURE)
                    .texture("Sampler1", CosmicBlock.TEXTURE)
                    .build()
    );
    public static final RenderLayer STAR_LEAVES = RenderLayer.of("star_leaves", RenderSetup.builder(CustomRederPipelines.STAR_LEAVES).texture("star", StarLeaves.TEXTURE).build());
    public static final RenderLayer STAR_BARRIER = RenderLayer.of("star_barrier", RenderSetup.builder(CustomRederPipelines.STAR_BARRIER).texture("starbarrier", StarBarrierBlock.TEXTURE).build());
    public static final RenderLayer NEGATIVE = RenderLayer.of("negative", RenderSetup.builder(CustomRederPipelines.NEGATIVE).build());
    public static final RenderLayer NO_RED = RenderLayer.of("no_red", RenderSetup.builder(CustomRederPipelines.NO_RED).build());
    public static final RenderLayer NO_GREEN = RenderLayer.of("no_green", RenderSetup.builder(CustomRederPipelines.NO_GREEN).build());
    public static final RenderLayer NO_BLUE = RenderLayer.of("no_blue", RenderSetup.builder(CustomRederPipelines.NO_BLUE).build());
}
