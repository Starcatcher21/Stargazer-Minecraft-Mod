package com.github.starcatcher21.stargazer;

import com.geckolib.renderer.GeoBlockRenderer;
import com.github.starcatcher21.stargazer.block.BlockTypes;
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
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.entity.renderers.*;
import com.github.starcatcher21.stargazer.mechanics.dash.DashClient;
import com.github.starcatcher21.stargazer.mechanics.star.StargazeClient;
import com.github.starcatcher21.stargazer.particle.ParticlesClient;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.handled.*;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.gui.MenuScreenRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;

public class StargazerClient {
    public static void initClient() {
        // Block rendering
        Stargazer.LOGGER.info("Loading Block Rendering");
        BlockTypes.COSMIC_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, CosmicBlockEntityRenderer::new));
        BlockTypes.STAR_BARRIER_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, StarBarrierBlockEntityRenderer::new));
        BlockTypes.NEGATIVE_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, NegativeBlockEntityRenderer::new));
        BlockTypes.AURORA.listen(type -> BlockEntityRendererRegistry.register(type, AuroraEntityRenderer::new));
        BlockTypes.NORED_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, NoRedBlockEntityRenderer::new));
        BlockTypes.NOGREEN_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, NoGreenBlockEntityRenderer::new));
        BlockTypes.NOBLUE_BLOCK.listen(type -> BlockEntityRendererRegistry.register(type, NoBlueBlockEntityRenderer::new));
        BlockTypes.STAR_LEAVES.listen(type -> BlockEntityRendererRegistry.register(type, StarLeavesEntityRenderer::new));

        Stargazer.LOGGER.info("Loading GeckoLib Block Rendering");

        BlockTypes.STAR_TRAP.listen(type -> BlockEntityRendererRegistry.register(
                type,
                context -> new GeoBlockRenderer<>(context, new StarTrapModel())
        ));

        BlockTypes.EYE_JAR.listen(type -> BlockEntityRendererRegistry.register(
                type,
                context -> new GeoBlockRenderer<>(context, new EyeJarModel())
        ));

        BlockTypes.STAR_DISPLAY.listen(type -> BlockEntityRendererRegistry.register(
                type,
                context -> new StarDisplayRenderer<>(context, new StarDisplayModel())
        ));

        // Particles
        ParticlesClient.init();

        // Keybinds
        Keybinds.init();

        // Entity Renderers
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
        ScreenHandlerTypes.STARFORGE_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, StarforgeHandled::new));
        ScreenHandlerTypes.MOON_WELDER_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, MoonWelderHandled::new));
        ScreenHandlerTypes.NIGHTWATCHER_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, NightWatcherHandled::new));
        ScreenHandlerTypes.STARGENERATOR_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, StargeneratorHandled::new));
        ScreenHandlerTypes.STARBOOK_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, StarBookHandled::new));
        ScreenHandlerTypes.STARCRUSHER_HANDLER.listen(screen -> MenuScreenRegistry.registerScreenFactory(screen, StarCrusherHandled::new));

        // Tick Events
        Stargazer.LOGGER.info("Loading End Client Tick Events");
        ClientTickEvent.CLIENT_POST.register(client -> {
            if (client != null && client.level != null && client.player != null) {
                // Run dash logic directly without illegal server-side gameRule lookups
                DashClient.tick();
                StargazeClient.clientTick(client);
            }
        });
    }
}