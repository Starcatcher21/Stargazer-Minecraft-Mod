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
//        BlockRenderLayerMap.putBlock(ModBlock.GRAVE, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.BORDER_BLOCK, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_PLANKS_DOOR, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.STAR_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.DARKNESS_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.STAR_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.CELESTIAL_STAR_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYE_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_GRASS, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.TALL_MOON_GRASS, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(ModBlock.BONE_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.GEODE_FRUIT, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Crops.DRAGON_CARROT_BLOCK, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Crops.BROODY_BLOCK, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_FERN, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYE_FERN, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Crops.GIANT_DRAGON_CARROT, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.PURPLE_MUSHROOM, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.FORGET_ME_NOW, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_FORGET_ME_NOW, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_STAR_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_CURVE_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_MOON_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_PURPLE_MUSHROOM, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_STAR_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.POTTED_DARKNESS_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(StarBlocks.STAR_PLANKS_DOOR, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.DARKNESS_PLANKS_DOOR, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(ModBlock.BONEFLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(ModBlock.POTTED_BONEFLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.RED_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.PURPLE_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.BLUE_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.YELLOW_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.POTTED_RED_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.POTTED_YELLOW_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.POTTED_BLUE_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Nebulas.POTTED_PURPLE_TENTACLE_FLOWER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(ModBlock.MOON_WELDER, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_PLANKS_DOOR, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.ROSE_OF_PAIN, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.POTTED_ROSE_OF_PAIN, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Crops.EYE_BALLS_BLOCK, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.SPRUNGUS, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_SPRUNGUS, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.YERI_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.YERI_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_YERI_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.BLUE_GRASS, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.FULL_MOON_LEAVES, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.FULL_MOON_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_FULL_MOON_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.POINTY, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_POINTY, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.SPIRO_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_SPIRO_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.GRADI, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Darkness.POTTED_GRADI, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Wander.POTTED_TRUNN_SAPLING, ChunkSectionLayer.CUTOUT);
//        BlockRenderLayerMap.putBlock(Wander.TRUNN_SAPLING, ChunkSectionLayer.CUTOUT);
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