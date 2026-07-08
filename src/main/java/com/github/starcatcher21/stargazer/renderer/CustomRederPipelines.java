package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import static net.minecraft.client.renderer.RenderPipelines.*;

public class CustomRederPipelines {
    // SNIPPETS
    // BLOCKS
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_AURORA_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_translucent"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_aurora"))
            .withSampler("Sampler0")
            .withSampler("Sampler2")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_LEAVES_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_leaves"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SKY_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/cosmic_position_color"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/cosmic_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_RED_SKY_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/red_position_color"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/red_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_WANDER_SKY_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/wander_position_color"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/wander_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_BARIER_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            ).withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS).buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_NEGATIVE_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
		    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color"))
		    .withSampler("Sampler0")
            .withSampler("Sampler2")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_RED_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_red"))
            .withSampler("Sampler0")
            .withSampler("Sampler2")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_GREEN_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_green"))
            .withSampler("Sampler0")
            .withSampler("Sampler2")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_BLUE_SNIPPET = RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_blue"))
            .withSampler("Sampler0")
            .withSampler("Sampler2")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
            .buildSnippet();

    // PIPELINES
    // SKY
    public static final RenderPipeline POSITION_TEX_COLOR_COSMIC_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_COSMIC_SKY_SNIPPET)
                    .withLocation("pipeline/cosmic_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
                    .withShaderDefine("STAR_LAYERS", 16).build()
    );

    public static final RenderPipeline POSITION_TEX_COLOR_RED_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_RED_SKY_SNIPPET)
                    .withLocation("pipeline/red_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
                    .build()
    );
    public static final RenderPipeline POSITION_TEX_COLOR_WANDER_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_WANDER_SKY_SNIPPET)
                    .withLocation("pipeline/wander_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
                    .build()
    );

    public static final RenderPipeline POSITION_SOFT_SKY = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_PROJECTION_SNIPPET, GLOBALS_SNIPPET)
                    .withLocation("pipeline/soft_sky")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
                    .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
                    .withDepthWrite(false)
                    .withCull(false)
                    .build()
    );

    public static final RenderPipeline POSITION_TEX_COLOR_DEPTH_PINNED_STARS = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_PROJECTION_SNIPPET, GLOBALS_SNIPPET)
                    .withLocation("pipeline/depth_pinned_stars")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withSampler("Sampler0")
                    .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
                    .withBlend(BlendFunction.TRANSLUCENT)
                    .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
                    .withDepthWrite(false)
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
