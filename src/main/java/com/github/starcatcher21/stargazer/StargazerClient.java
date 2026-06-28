package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.block.BlockTypes;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.ModFluids;
import com.github.starcatcher21.stargazer.block.clases.eyes.eyejar.EyeJarModel;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrapModel;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.noblue.NoBlueBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.nogreen.NoGreenBlockEntityRenderer;
import com.github.starcatcher21.stargazer.block.clases.nored.NoRedBlockEntityRenderer;
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
import com.github.starcatcher21.stargazer.particle.Particles;
import com.github.starcatcher21.stargazer.screens.ScreenHandlerTypes;
import com.github.starcatcher21.stargazer.screens.handled.MoonWelderHandled;
import com.github.starcatcher21.stargazer.screens.handled.StarforgeHandled;
import com.github.starcatcher21.stargazer.worldgen.dimensions.Dimensions;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

@Environment(EnvType.CLIENT)
public class StargazerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block rendering
        Stargazer.LOGGER.info("Loading Block Rendering");
        BlockRenderLayerMap.putBlock(ModBlock.GRAVE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.BORDER_BLOCK, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_PLANKS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.STAR_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.DARKNESS_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.STAR_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.CELESTIAL_STAR_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYE_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_GRASS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.TALL_MOON_GRASS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.BONE_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.GEODE_FRUIT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Crops.DRAGON_CARROT_BLOCK, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Crops.BROODY_BLOCK, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.MOON_FERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYE_FERN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(EyeBloodBlocks.EYES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Crops.GIANT_DRAGON_CARROT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.PURPLE_MUSHROOM, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.FORGET_ME_NOW, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_FORGET_ME_NOW, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_STAR_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_CELESTIAL_STAR_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_CURVE_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_MOON_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_PURPLE_MUSHROOM, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.POTTED_STAR_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.POTTED_DARKNESS_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(StarBlocks.STAR_PLANKS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.DARKNESS_PLANKS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.BONEFLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.POTTED_BONEFLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.RED_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.PURPLE_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.BLUE_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.YELLOW_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.POTTED_RED_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.POTTED_YELLOW_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.POTTED_BLUE_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Nebulas.POTTED_PURPLE_TENTACLE_FLOWER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.MOON_WELDER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.CURVE_PLANKS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.ROSE_OF_PAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.POTTED_ROSE_OF_PAIN, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Crops.EYE_BALLS_BLOCK, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.SPRUNGUS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_SPRUNGUS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.YERI_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.YERI_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_YERI_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.BLUE_GRASS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.FULL_MOON_LEAVES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.FULL_MOON_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(MoonBlocks.POTTED_FULL_MOON_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.POINTY, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_POINTY, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.SPIRO_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RedOrbBlocks.POTTED_SPIRO_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.GRADI, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Darkness.POTTED_GRADI, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Wander.POTTED_TRUNN_SAPLING, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Wander.TRUNN_SAPLING, BlockRenderLayer.CUTOUT);
        BlockEntityRendererFactories.register(BlockTypes.COSMIC_BLOCK, CosmicBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.STAR_BARRIER_BLOCK, StarBarrierBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.NEGATIVE_BLOCK, NegativeBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.NORED_BLOCK, NoRedBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.NOGREEN_BLOCK, NoGreenBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.NOBLUE_BLOCK, NoBlueBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockTypes.STAR_LEAVES, StarLeavesEntityRenderer::new);

        BlockEntityRendererRegistry.register(BlockTypes.STAR_TRAP, (context) -> new GeoBlockRenderer<>(new StarTrapModel()));
        BlockEntityRendererRegistry.register(BlockTypes.EYE_JAR, (context) -> new GeoBlockRenderer<>(new EyeJarModel()));
        BlockEntityRendererRegistry.register(BlockTypes.STAR_DISPLAY, (context) -> new StarDisplayRenderer(new StarDisplayModel()));

        // Particles
        Particles.clientInit();

        // Fluids
        BlockRenderLayerMap.putBlock(Fluids.DREAM, BlockRenderLayer.TRANSLUCENT);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.DREAM,
                ModFluids.DREAM_FLOWING,
                new SimpleFluidRenderHandler(
                        // Source texture
                        Identifier.of(Stargazer.MOD_ID, "block/dream_still"),
                        // Flowing texture
                        Identifier.of(Stargazer.MOD_ID, "block/dream_flow")
                )
        );

        // Entity
        EntityRendererRegistry.register(EntityRegistry.GHOST_ENTITY, GhostRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.AMETHYST_TURTLE_ENTITY, AmethystTurtleRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.EYE_BAT_ENTITY, EyeBatRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.STAR_ENTITY, StarRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.THROWABLE_STAR_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ROOK_ENTITY, RookRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.BLACK_ROOK_ENTITY, BlackRookRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.SCRUBY_ENTITY, ScrubyRenderer::new);

        // Screens
        HandledScreens.register(ScreenHandlerTypes.STARFORGE_HANDLER, StarforgeHandled::new);
        HandledScreens.register(ScreenHandlerTypes.MOON_WELDER_HANDLER, MoonWelderHandled::new);

        // Tick Events
        Stargazer.LOGGER.info("Loading End Client Tick Events");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                if (client != null && client.world != null && client.player != null) {
                    if (client.getServer().getSpawnWorld().getGameRules().getValue(GameRules.DASH)) {
                        DashClient.tick();
                    }
                    PlayerCosmicGrav.tick(client);
                    PlayerRedOrbGrav.tick(client);
                    Stargaze.tick(client);
                    if (client.player.getControllingVehicle() != null) {
                        if (!client.player.getControllingVehicle().isLiving()) {
                            if (Dimensions.REG_COSMIC_WORLD.getValue().equals(client.world.getRegistryKey().getValue())) {
                                client.player.getAttributeInstance(StargazerAttributes.DASH_LEVEL).addTemporaryModifier(PlayerCosmicGrav.dash_modifier);
                            }
                        }
                        if (client.player.getControllingVehicle().isLiving()) {
                            if (Dimensions.REG_COSMIC_WORLD.getValue().equals(client.world.getRegistryKey().getValue())) {
                                if (!client.player.getStatusEffects().contains(StatusEffects.COSMO)) {
                                    client.player.getAttributeInstance(StargazerAttributes.DASH_LEVEL).removeModifier(PlayerCosmicGrav.dash_modifier);
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
