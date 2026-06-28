package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

import static net.minecraft.client.gl.RenderPipelines.*;

public class CustomRederPipelines {
    // SNIPPETS
    // BLOCKS
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_LEAVES_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_star_leaves"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SKY_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/cosmic_position_color"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/cosmic_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_RED_SKY_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/red_position_color"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/red_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_WANDER_SKY_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/wander_position_color"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/wander_position_color"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_BARIER_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            ).withVertexShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withSampler("Sampler0")
            .withSampler("Sampler1")
            .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_NEGATIVE_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader("core/position_tex_color")
		    .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/position_tex_color"))
		    .withSampler("Sampler0")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_RED_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET
            )
            .withVertexShader("core/position_tex_color")
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/position_tex_color_red"))
            .withSampler("Sampler0")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_GREEN_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader("core/position_tex_color")
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/position_tex_color_green"))
            .withSampler("Sampler0")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_BLUE_SNIPPET = RenderPipeline.builder(
                    TRANSFORMS_AND_PROJECTION_SNIPPET, FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader("core/position_tex_color")
            .withFragmentShader(Identifier.of(Stargazer.MOD_ID, "core/position_tex_color_blue"))
            .withSampler("Sampler0")
            .withBlend(BlendFunction.INVERT)
            .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS)
            .buildSnippet();

    // PIPELINES
    // SKY
    public static final RenderPipeline POSITION_TEX_COLOR_COSMIC_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_COSMIC_SKY_SNIPPET)
                    .withLocation("pipeline/cosmic_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
                    .withShaderDefine("STAR_LAYERS", 16).build()
    );

    public static final RenderPipeline POSITION_TEX_COLOR_RED_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_RED_SKY_SNIPPET)
                    .withLocation("pipeline/red_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
                    .build()
    );
    public static final RenderPipeline POSITION_TEX_COLOR_WANDER_SKY = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_WANDER_SKY_SNIPPET)
                    .withLocation("pipeline/wander_sky")
                    .withBlend(BlendFunction.TRANSLUCENT).withDepthWrite(false)
                    .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
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
    public static final RenderPipeline NEGATIVE = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NEGATIVE_SNIPPET).withLocation("pipeline/negative").build());

    public static final RenderPipeline NO_RED = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_RED_SNIPPET).withLocation("pipeline/no_red").build());

    public static final RenderPipeline NO_GREEN = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_GREEN_SNIPPET).withLocation("pipeline/no_green").build());

    public static final RenderPipeline NO_BLUE = RenderPipelines.register(
            RenderPipeline.builder(RENDERTYPE_NO_BLUE_SNIPPET).withLocation("pipeline/no_blue").build());
}
