package com.github.starcatcher21.stargazer.renderer;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.resources.Identifier;

//? if >= 26.2 {
import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.pipeline.*;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
//?} else {
/*import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import java.io.IOException;
*///?}

//? if neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
//? if >= 26.2 {
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;
 //?} else {
/^import net.neoforged.neoforge.client.event.RegisterShadersEvent;
^///?}
*///? }

//? if >= 26.2 {
import static net.minecraft.client.renderer.RenderPipelines.*;
 //? }

//? if neoforge {
/*@EventBusSubscriber(modid = Stargazer.MOD_ID, value = Dist.CLIENT)
*///? }
public class CustomRederPipelines {

    // ================================================================
    // 26.2+ — RenderPipeline snippets and registrations
    // ================================================================
    //? if >= 26.2 {
    public static final RenderPipeline.Snippet RENDERTYPE_COSMIC_SNIPPET = RenderPipeline.builder(GLOBALS_SNIPPET)
            .withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
            .withBindGroupLayout(BindGroupLayouts.FOG)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withVertexBinding(0, DefaultVertexFormat.POSITION)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_AURORA_SNIPPET = RenderPipeline.builder(GLOBALS_SNIPPET)
            .withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
            .withBindGroupLayout(BindGroupLayouts.FOG)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_translucent"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_aurora"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_LIGHTMAP_COLOR)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_STAR_LEAVES_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cosmic"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_leaves"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_STAR_BARIER_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_star_barrier"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NEGATIVE_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_RED_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_red"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_GREEN_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_green"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline.Snippet RENDERTYPE_NO_BLUE_SNIPPET = RenderPipeline.builder(MATRICES_FOG_SNIPPET)
            .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/rendertype_cube"))
            .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/position_tex_color_blue"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0_SAMPLER2)
            .withColorTargetState(new ColorTargetState(BlendFunction.INVERT))
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withVertexBinding(0, DefaultVertexFormat.POSITION_COLOR)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .buildSnippet();

    public static final RenderPipeline POSITION_SOFT_SKY = register(
            RenderPipeline.builder()
                    .withLocation("pipeline/soft_sky")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/soft_sky"))
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
                    .withVertexBinding(0, DefaultVertexFormat.POSITION)
                    .withDepthStencilState(new DepthStencilState(CompareOp.NEVER_PASS, false))
                    .withCull(false)
                    .build());

    public static final RenderPipeline POSITION_TEX_COLOR_DEPTH_PINNED_STARS = register(
            RenderPipeline.builder(MATRICES_FOG_SNIPPET)
                    .withLocation("pipeline/depth_pinned_stars")
                    .withVertexShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withFragmentShader(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "core/sky_stars"))
                    .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
                    .withPrimitiveTopology(PrimitiveTopology.QUADS)
                    .withVertexBinding(0, DefaultVertexFormat.POSITION_TEX_COLOR)
                    .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
                    .withDepthStencilState(new DepthStencilState(CompareOp.GREATER_THAN, false))
                    .withCull(false)
                    .build());

    public static final RenderPipeline COSMIC = register(
            RenderPipeline.builder(RENDERTYPE_COSMIC_SNIPPET).withLocation("pipeline/cosmic").build());
    public static final RenderPipeline STAR_LEAVES = register(
            RenderPipeline.builder(RENDERTYPE_STAR_LEAVES_SNIPPET).withLocation("pipeline/star_leaves").build());
    public static final RenderPipeline STAR_BARRIER = register(
            RenderPipeline.builder(RENDERTYPE_STAR_BARIER_SNIPPET).withLocation("pipeline/star_barrier").build());
    public static final RenderPipeline AURORA = register(
            RenderPipeline.builder(RENDERTYPE_AURORA_SNIPPET).withLocation("pipeline/aurora").build());
    public static final RenderPipeline NEGATIVE = register(
            RenderPipeline.builder(RENDERTYPE_NEGATIVE_SNIPPET).withLocation("pipeline/negative").build());
    public static final RenderPipeline NO_RED = register(
            RenderPipeline.builder(RENDERTYPE_NO_RED_SNIPPET).withLocation("pipeline/no_red").build());
    public static final RenderPipeline NO_GREEN = register(
            RenderPipeline.builder(RENDERTYPE_NO_GREEN_SNIPPET).withLocation("pipeline/no_green").build());
    public static final RenderPipeline NO_BLUE = register(
            RenderPipeline.builder(RENDERTYPE_NO_BLUE_SNIPPET).withLocation("pipeline/no_blue").build());

    private static RenderPipeline register(final RenderPipeline pipeline) {
        //? if fabric {
        PIPELINES_BY_LOCATION.put(pipeline.getLocation(), pipeline);
        return pipeline;
        //?} else {
        /*return pipeline;
        *///?}
    }
    //?}

    // ================================================================
    // 1.21.1 — ShaderInstance holders, populated by the loader event
    // ================================================================
    //? if < 26.2 {
    /*public static ShaderInstance COSMIC;
    public static ShaderInstance STAR_LEAVES;
    public static ShaderInstance STAR_BARRIER;
    public static ShaderInstance AURORA;
    public static ShaderInstance NEGATIVE;
    public static ShaderInstance NO_RED;
    public static ShaderInstance NO_GREEN;
    public static ShaderInstance NO_BLUE;
    public static ShaderInstance SKY_STARS;

    public static ShaderInstance getCosmicShader()       { return COSMIC; }
    public static ShaderInstance getStarLeavesShader()   { return STAR_LEAVES; }
    public static ShaderInstance getStarBarrierShader()  { return STAR_BARRIER; }
    public static ShaderInstance getAuroraShader()       { return AURORA; }
    public static ShaderInstance getNegativeShader()     { return NEGATIVE; }
    public static ShaderInstance getNoRedShader()        { return NO_RED; }
    public static ShaderInstance getNoGreenShader()      { return NO_GREEN; }
    public static ShaderInstance getNoBlueShader()       { return NO_BLUE; }
    public static ShaderInstance getSkyStarsShader()     { return SKY_STARS; }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
    }
    *///?}

    // ================================================================
    // NeoForge registration
    // ================================================================
    //? if neoforge {
    /*//? if >= 26.2 {
    @SubscribeEvent
    public static void onRegisterRenderPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(COSMIC);
        event.registerPipeline(STAR_LEAVES);
        event.registerPipeline(STAR_BARRIER);
        event.registerPipeline(AURORA);
        event.registerPipeline(NEGATIVE);
        event.registerPipeline(NO_RED);
        event.registerPipeline(NO_GREEN);
        event.registerPipeline(NO_BLUE);
        event.registerPipeline(POSITION_SOFT_SKY);
        event.registerPipeline(POSITION_TEX_COLOR_DEPTH_PINNED_STARS);
    }
    //?} else {
    /^@SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("cosmic"),
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP), shader -> COSMIC = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("star_leaves"),
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP), shader -> STAR_LEAVES = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("star_barrier"),
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP), shader -> STAR_BARRIER = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("aurora"),
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP), shader -> AURORA = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("negative"),
                DefaultVertexFormat.POSITION_COLOR_LIGHTMAP), shader -> NEGATIVE = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("no_red"),
                DefaultVertexFormat.POSITION_COLOR_LIGHTMAP), shader -> NO_RED = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("no_green"),
                DefaultVertexFormat.POSITION_COLOR_LIGHTMAP), shader -> NO_GREEN = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("no_blue"),
                DefaultVertexFormat.POSITION_COLOR_LIGHTMAP), shader -> NO_BLUE = shader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), id("sky_stars"),
                DefaultVertexFormat.POSITION_TEX_COLOR), shader -> SKY_STARS = shader);
    }
    ^///?}
    *///?}
}
