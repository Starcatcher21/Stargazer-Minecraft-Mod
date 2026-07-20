package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import static net.minecraft.client.renderer.RenderPipelines.*;

public class CustomRederPipelines {
    // SNIPPETS
    // BLOCKS
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_AURORA_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_translucent"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_aurora"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_LEAVES_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_leaves"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
//            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_STAR_BARIER_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            ).withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
//            .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_NEGATIVE_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
		    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color"))
		    .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
//            .withBlend(BlendFunction.INVERT)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_RED_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_red"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
//            .withBlend(BlendFunction.INVERT)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_GREEN_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_green"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
//            .withBlend(BlendFunction.INVERT)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_BLUE_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_blue"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
//            .withBlend(BlendFunction.INVERT)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();

    // PIPELINES
    // SKY
    public static final RenderPipeline POSITION_SOFT_SKY = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET)
                    .withLocation("pipeline/soft_sky")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
//                    .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
//                    .withDepthWrite(false)
                    .withCull(false)
                    .build()
    );

    public static final RenderPipeline POSITION_TEX_COLOR_DEPTH_PINNED_STARS = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET)
                    .withLocation("pipeline/depth_pinned_stars")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withShaderDefine("Sampler0")
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
//                    .withBlend(BlendFunction.TRANSLUCENT)
//                    .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
//                    .withDepthWrite(false)
                    .withCull(false)
                    .build()
    );
    // BLOCKS
    public static final RenderPipeline COSMIC = register(
            RenderPipeline.builder(RENDERTYPE_COSMIC_SNIPPET)
                    .withLocation("pipeline/cosmic")
                    .withShaderDefine("STAR_LAYERS", 16).build()
    );
    public static final RenderPipeline STAR_LEAVES = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_STAR_LEAVES_SNIPPET)
                    .withLocation("pipeline/star_leaves")
                    .withShaderDefine("STAR_LAYERS", 16).build());
    public static final RenderPipeline STAR_BARRIER = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_STAR_BARIER_SNIPPET).withLocation("pipeline/star_barrier").build());
    public static final RenderPipeline AURORA = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_AURORA_SNIPPET).withLocation("pipeline/aurora").build());
    public static final RenderPipeline NEGATIVE = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NEGATIVE_SNIPPET).withLocation("pipeline/negative").build());

    public static final RenderPipeline NO_RED = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_RED_SNIPPET).withLocation("pipeline/no_red").build());

    public static final RenderPipeline NO_GREEN = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_GREEN_SNIPPET).withLocation("pipeline/no_green").build());

    public static final RenderPipeline NO_BLUE = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_BLUE_SNIPPET).withLocation("pipeline/no_blue").build());
}
