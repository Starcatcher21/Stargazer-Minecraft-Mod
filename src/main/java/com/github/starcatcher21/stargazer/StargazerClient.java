package com.github.starcatcher21.stargazer;

import com.geckolib.renderer.GeoBlockRenderer;
import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.ModFluids;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJarModel;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrapModel;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.noblue.NoBlueBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.nogreen.NoGreenBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.nored.NoRedBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.star.aurora.AuroraEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.star.barrier.StarBarrierBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.star.cosmic.CosmicBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.star.leaves.StarLeavesEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.star.star_display.StarDisplayModel;
import com.github.starcatcher21.stargazer.block.clases.star.star_display.StarDisplayRenderer;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.entity.renderers.*;
import com.github.starcatcher21.stargazer.mechanics.PlayerCosmicGrav;
import com.github.starcatcher21.stargazer.mechanics.PlayerRedOrbGrav;
import com.github.starcatcher21.stargazer.mechanics.dash.DashClient;
import com.github.starcatcher21.stargazer.mechanics.star.Stargaze;
import com.github.starcatcher21.stargazer.particle.ParticlesClient;
import com.github.starcatcher21.stargazer.renderer.SkyStarRenderer;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.handled.*;
import com.github.starcatcher21.stargazer.worldgen.dimensions.Dimensions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.client.rendering.BlockEntityRendererRegistryImpl;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class StargazerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block rendering
        Stargazer.LOGGER.info("Loading Block Rendering");
        BlockEntityRenderers.register(BlockTypes.COSMIC_BLOCK, CosmicBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.STAR_BARRIER_BLOCK, StarBarrierBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.NEGATIVE_BLOCK, NegativeBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.AURORA, AuroraEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.NORED_BLOCK, NoRedBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.NOGREEN_BLOCK, NoGreenBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.NOBLUE_BLOCK, NoBlueBlockEntityRenderer::new);
        BlockEntityRenderers.register(BlockTypes.STAR_LEAVES, StarLeavesEntityRenderer::new);

        BlockEntityRendererRegistryImpl.register(BlockTypes.STAR_TRAP, (context) -> new GeoBlockRenderer<>(context, new StarTrapModel()));
        BlockEntityRendererRegistryImpl.register(BlockTypes.EYE_JAR, (context) -> new GeoBlockRenderer<>(context, new EyeJarModel()));
        BlockEntityRendererRegistryImpl.register(BlockTypes.STAR_DISPLAY, (context) -> new StarDisplayRenderer(context, new StarDisplayModel()));

        // Custom dimension sky stars
        SkyStarRenderer.init();

        // Particles
        ParticlesClient.init();
        Keybinds.init();

        // Fluids
//        BlockRenderLayerMap.putBlock(Fluids.DREAM, ChunkSectionLayer.TRANSLUCENT);
        FluidRenderingRegistry.register(
                ModFluids.DREAM,
                ModFluids.DREAM_FLOWING,
                new FluidModel.Unbaked(
                        new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_still")),
                        new Material(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "block/dream_flow")),
                        null,
                        null
                )
        );

        // Entity
        EntityRendererRegistry.register(EntityRegistry.GHOST_ENTITY, GhostRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.AMETHYST_TURTLE_ENTITY, AmethystTurtleRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.EYE_BAT_ENTITY, EyeBatRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.STAR_ENTITY, StarRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.THROWABLE_STAR_ENTITY, ThrownItemRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ROOK_ENTITY, RookRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.BLACK_ROOK_ENTITY, BlackRookRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.SCRUBY_ENTITY, ScrubyRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.BLACK_FOX_ENTITY, BlackFoxRenderer::new);

        // Screens
        MenuScreens.register(ScreenHandlerTypes.STARFORGE_HANDLER, StarforgeHandled::new);
        MenuScreens.register(ScreenHandlerTypes.MOON_WELDER_HANDLER, MoonWelderHandled::new);
        MenuScreens.register(ScreenHandlerTypes.STARBOOK_HANDLER, StarBookHandled::new);
        MenuScreens.register(ScreenHandlerTypes.STARGENERATOR_HANDLER, StargeneratorHandled::new);
        MenuScreens.register(ScreenHandlerTypes.NIGHTWATCHER_HANDLER, NightWatcherHandled::new);
        MenuScreens.register(ScreenHandlerTypes.STARCRUSHER_HANDLER, StarCrusherHandled::new);

        // Tick Events
        Stargazer.LOGGER.info("Loading End Client Tick Events");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                if (client != null && client.level != null && client.player != null) {
                    if (client.getSingleplayerServer().findRespawnDimension().getGameRules().get(GameRules.DASH)) {
                        DashClient.tick();
                    }
                    PlayerCosmicGrav.tick(client);
                    PlayerRedOrbGrav.tick(client);
                    Stargaze.tick(client);
                    if (client.player.getControlledVehicle() != null) {
                        if (!client.player.getControlledVehicle().isAlive()) {
                            if (Dimensions.REG_COSMIC_WORLD.identifier().equals(client.level.dimension().identifier())) {
                                client.player.getAttribute(StargazerAttributes.DASH_LEVEL).addTransientModifier(PlayerCosmicGrav.dash_modifier);
                            }
                        }
                        if (client.player.getControlledVehicle().isAlive()) {
                            if (Dimensions.REG_COSMIC_WORLD.identifier().equals(client.level.dimension().identifier())) {
                                if (!client.player.getActiveEffects().contains(StatusEffects.COSMO)) {
                                    client.player.getAttribute(StargazerAttributes.DASH_LEVEL).removeModifier(PlayerCosmicGrav.dash_modifier);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Stargazer.LOGGER.error("Can't load clinet settings");

            }
        });
    }
}