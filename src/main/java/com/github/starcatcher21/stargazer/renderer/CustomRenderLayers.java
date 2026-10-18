package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.block.clases.star.aurora.Aurora;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlock;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlock;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeaves;

//? if >= 26.2 {
/*import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
*///?} else {
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
//?}

public class CustomRenderLayers {

    //? if >= 26.2 {
    /*public static final RenderType COSMIC = RenderType.create(
            "cosmic",
            RenderSetup.builder(CustomRederPipelines.COSMIC)
                    .withTexture("Sampler0", CosmicBlock.TEXTURE)
                    .withTexture("Sampler1", CosmicBlock.TEXTURE)
                    .createRenderSetup()
    );
    public static final RenderType STAR_LEAVES = RenderType.create(
            "star_leaves",
            RenderSetup.builder(CustomRederPipelines.STAR_LEAVES)
                    .withTexture("Sampler0", StarLeaves.TEXTURE)
                    .createRenderSetup()
    );
    public static final RenderType STAR_BARRIER = RenderType.create(
            "star_barrier",
            RenderSetup.builder(CustomRederPipelines.STAR_BARRIER)
                    .withTexture("Sampler0", StarBarrierBlock.TEXTURE)
                    .createRenderSetup()
    );
    public static final RenderType NEGATIVE = RenderType.create(
            "negative",
            RenderSetup.builder(CustomRederPipelines.NEGATIVE)
                    .useLightmap()
                    .createRenderSetup()
    );
    public static final RenderType NO_RED = RenderType.create(
            "no_red",
            RenderSetup.builder(CustomRederPipelines.NO_RED)
                    .useLightmap()
                    .createRenderSetup()
    );
    public static final RenderType NO_GREEN = RenderType.create(
            "no_green",
            RenderSetup.builder(CustomRederPipelines.NO_GREEN)
                    .useLightmap()
                    .createRenderSetup()
    );
    public static final RenderType NO_BLUE = RenderType.create(
            "no_blue",
            RenderSetup.builder(CustomRederPipelines.NO_BLUE)
                    .useLightmap()
                    .createRenderSetup()
    );
    public static final RenderType AURORA = RenderType.create(
            "aurora",
            RenderSetup.builder(CustomRederPipelines.AURORA)
                    .withTexture("Sampler0", Aurora.TEXTURE)
                    .useLightmap()
                    .createRenderSetup()
    );
    *///?} else {
    /* ---------------- 1.21.1 legacy RenderType API ------------------ */

    public static final RenderType COSMIC = RenderType.create(
            "cosmic",
            DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getCosmicShader))
                    .setTextureState(new RenderStateShard.TextureStateShard(
                            CosmicBlock.TEXTURE, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType STAR_LEAVES = RenderType.create(
            "star_leaves",
            DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getStarLeavesShader))
                    .setTextureState(new RenderStateShard.TextureStateShard(
                            StarLeaves.TEXTURE, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType STAR_BARRIER = RenderType.create(
            "star_barrier",
            DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getStarBarrierShader))
                    .setTextureState(new RenderStateShard.TextureStateShard(
                            StarBarrierBlock.TEXTURE, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType NEGATIVE = RenderType.create(
            "negative",
            DefaultVertexFormat.POSITION_COLOR_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getNegativeShader))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType NO_RED = RenderType.create(
            "no_red",
            DefaultVertexFormat.POSITION_COLOR_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getNoRedShader))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType NO_GREEN = RenderType.create(
            "no_green",
            DefaultVertexFormat.POSITION_COLOR_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getNoGreenShader))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType NO_BLUE = RenderType.create(
            "no_blue",
            DefaultVertexFormat.POSITION_COLOR_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getNoBlueShader))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );

    public static final RenderType AURORA = RenderType.create(
            "aurora",
            DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            CustomRederPipelines::getAuroraShader))
                    .setTextureState(new RenderStateShard.TextureStateShard(
                            Aurora.TEXTURE, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .createCompositeState(false)
    );
    //?}
}
