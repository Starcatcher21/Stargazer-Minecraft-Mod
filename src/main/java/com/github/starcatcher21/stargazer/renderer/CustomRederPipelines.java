package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.pipeline.*;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
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
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_AURORA_SNIPPET = RenderPipeline.builder(
                        GLOBALS_SNIPPET
            )
            .withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
            .withBindGroupLayout(BindGroupLayouts.FOG)
            .withBindGroupLayout(BindGroupLayouts.LIGHTMAP_INFO)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_translucent"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_aurora"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_STAR_LEAVES_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_leaves"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(new DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_STAR_BARIER_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            ).withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .buildSnippet();
    public static final RenderPipeline.Snippet RENDERTYPE_NEGATIVE_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
		    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color"))
		    .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_RED_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_red"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_GREEN_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_green"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler2")
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_BLUE_SNIPPET = RenderPipeline.builder(
                    MATRICES_FOG_SNIPPET, GLOBALS_SNIPPET
            )
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_blue"))
            .withShaderDefine("Sampler0")
            .withShaderDefine("Sampler1")
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .buildSnippet();

    // PIPELINES
    // SKY
    public static final RenderPipeline POSITION_SOFT_SKY = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_FOG_SNIPPET)
                    .withLocation("pipeline/soft_sky")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
                    .withVertexBinding(0, DefaultVertexFormat.POSITION)
                    .withDepthStencilState(new DepthStencilState(CompareOp.ALWAYS_PASS, false))
                    .withCull(false)
                    .build()
    );

    public static final RenderPipeline POSITION_TEX_COLOR_DEPTH_PINNED_STARS = RenderPipelines.register(
            RenderPipeline.builder(MATRICES_FOG_SNIPPET)
                    .withLocation("pipeline/depth_pinned_stars")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withShaderDefine("Sampler0")
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
                    .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR)
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withDepthStencilState(new DepthStencilState(CompareOp.ALWAYS_PASS, false))
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
