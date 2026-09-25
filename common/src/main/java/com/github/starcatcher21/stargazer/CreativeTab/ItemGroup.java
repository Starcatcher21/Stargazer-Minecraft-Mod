package com.github.starcatcher21.stargazer.CreativeTab;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.item.WishingStars;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemGroup {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Stargazer.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> STAR_GROUP = TABS.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_group"), () -> CreativeTabRegistry.create(
            builder -> builder
                    .icon(() -> new ItemStack(ModItems.YELLOW_STAR.get()))
                    .title(Component.translatable("itemGroup.Stargazer"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.STAR_BOOK.get());
                        // Blocks
                        output.accept(ModItems.STAR_HAMMER.get());
                        output.accept(ModBlock.GRAVE.get());
                        output.accept(ModBlock.NEGATIVE_BLOCK.get());
                        output.accept(ModBlock.NORED_BLOCK.get());
                        output.accept(ModBlock.NOGREEN_BLOCK.get());
                        output.accept(ModBlock.NOBLUE_BLOCK.get());
                        output.accept(ModBlock.INFESTED_CALCITE.get());
                        output.accept(ModBlock.BONE_LEAVES.get());
                        output.accept(ModBlock.SPRINKLER.get());
                        output.accept(ModBlock.MOON_WELDER.get());
                        // Crops
                        output.accept(ModItems.SEED_PACKET.get());
                        output.accept(Crops.DRAGON_CARROT.get());
                        output.accept(Crops.BROODY.get());
                        output.accept(Crops.EYE_BALLS.get());
                        // Fish
                        output.accept(ModItems.GUMMY_FISH.get());
                        output.accept(ModItems.GUMMY_WORM.get());
                        output.accept(ModItems.COSMO_FISH.get());
                        output.accept(ModItems.ENDER_FISH.get());
                        output.accept(ModItems.GOLDEN_CRUCIAN.get());
                        output.accept(ModItems.LUCKY_COMET.get());
                        output.accept(ModItems.COMET_FRAGMENT.get());
                        output.accept(MoonBlocks.COMET_BLOCK.get());
                        output.accept(ModItems.AURORA_FRAGMENT.get());
                        output.accept(StarBlocks.AURORA.get());
                        output.accept(ModItems.MOON_COOKIE.get());
                        output.accept(ModItems.STAR_COOKIE.get());
                        // Star Blocks
                        output.accept(StarBlocks.COSMIC_BLOCK.get());
                        output.accept(StarBlocks.STAR_BARRIER_BLOCK.get());
                        output.accept(StarBlocks.BORDER_BLOCK.get());
                        output.accept(StarBlocks.STAR_LOG.get());
                        output.accept(StarBlocks.STRIPPED_STAR_LOG.get());
                        output.accept(StarBlocks.STAR_PLANKS.get());
                        output.accept(StarBlocks.STAR_PLANKS_DOOR.get());
                        output.accept(StarBlocks.STAR_PLANKS_SLAB.get());
                        output.accept(StarBlocks.STAR_PLANKS_STAIRS.get());
                        output.accept(StarBlocks.STAR_PLANKS_BUTTON.get());
                        output.accept(StarBlocks.STAR_PLANKS_FENCE.get());
                        output.accept(StarBlocks.STAR_PLANKS_FENCE_GATE.get());
                        output.accept(StarBlocks.STAR_LEAVES.get());
                        output.accept(StarBlocks.STAR_SAPLING.get());
                        // Moon Rocks
                        output.accept(MoonBlocks.MOON_ROCK.get());
                        output.accept(MoonBlocks.MOON_ROCK_IRON_ORE.get());
                        output.accept(MoonBlocks.POLISHED_MOON_ROCK.get());
                        output.accept(MoonBlocks.MOON_ROCK_NYLIUM.get());
                        output.accept(Darkness.DYLIUM.get());
                        output.accept(Darkness.ROSE_OF_PAIN.get());
                        output.accept(MoonBlocks.BLACK_MOON_ROCK.get());
                        output.accept(MoonBlocks.POLISHED_BLACK_MOON_ROCK.get());
                        output.accept(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get());
                        output.accept(MoonBlocks.MOON_ROCK_TILES.get());
                        output.accept(MoonBlocks.PURPLE_MOON_ROCK_TILES.get());
                        output.accept(MoonBlocks.MOON_ROCK_BRICKS.get());
                        output.accept(MoonBlocks.MOON_ROCK_BRICKS_SLAB.get());
                        output.accept(MoonBlocks.MOON_ROCK_BRICKS_STAIRS.get());
                        output.accept(MoonBlocks.CRACKED_MOON_ROCK_BRICKS.get());
                        output.accept(MoonBlocks.CHISELED_MOON_ROCK_BRICKS.get());
                        output.accept(MoonBlocks.STAR_FORGE.get());
                        output.accept(MoonBlocks.STAR_STONE.get());
                        output.accept(MoonBlocks.MOON_ROCK_CRYSTALS.get());
                        output.accept(ModItems.CRYSTAL_MOON.get());
                        output.accept(MoonBlocks.CRYSTAL_MOON_BLOCK.get());
                        output.accept(ModItems.MOON_HELMET.get());
                        output.accept(ModItems.MOON_CHESTPLATE.get());
                        output.accept(ModItems.MOON_LEGGINS.get());
                        output.accept(ModItems.MOON_BOOTS.get());
                        output.accept(MoonBlocks.PRISMATIC_ORE.get());
                        output.accept(ModItems.PRISMATIC_SHARD.get());
                        output.accept(MoonBlocks.PRISMATIC_SHARD_BLOCK.get());
                        output.accept(ModItems.PRISMATIC_INGOT.get());
                        output.accept(ModItems.MOON_GLASS_SHARD.get());
                        output.accept(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get());
                        output.accept(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK.get());
                        output.accept(ModItems.SUN_ENRICHED_YELLOW_STAR.get());
                        output.accept(WishingStars.WISHING_STAR.get());
                        output.accept(WishingStars.WHITE_WISHING_STAR.get());
                        output.accept(WishingStars.LIGHT_GRAY_WISHING_STAR.get());
                        output.accept(WishingStars.GRAY_WISHING_STAR.get());
                        output.accept(WishingStars.BLACK_WISHING_STAR.get());
                        output.accept(WishingStars.BROWN_WISHING_STAR.get());
                        output.accept(WishingStars.RED_WISHING_STAR.get());
                        output.accept(WishingStars.ORANGE_WISHING_STAR.get());
                        output.accept(WishingStars.LIME_WISHING_STAR.get());
                        output.accept(WishingStars.GREEN_WISHING_STAR.get());
                        output.accept(WishingStars.CYAN_WISHING_STAR.get());
                        output.accept(WishingStars.LIGHT_BLUE_WISHING_STAR.get());
                        output.accept(WishingStars.BLUE_WISHING_STAR.get());
                        output.accept(WishingStars.PURPLE_WISHING_STAR.get());
                        output.accept(WishingStars.MAGENTA_WISHING_STAR.get());
                        output.accept(WishingStars.PINK_WISHING_STAR.get());
                        output.accept(ModItems.DREAM_STAR.get());
                        output.accept(ModItems.WINGED_STAR.get());
                        output.accept(ModItems.THROWABLE_STAR.get());
                        output.accept(StarBlocks.STAR_DISPLAY.get());
                        // Moon Trees
                        output.accept(MoonBlocks.MOON_LOG.get());
                        output.accept(MoonBlocks.STRIPPED_MOON_LOG.get());
                        output.accept(MoonBlocks.MOON_LEAVES.get());
                        output.accept(MoonBlocks.MOON_SAPLING.get());
                        output.accept(MoonBlocks.FULL_MOON_LOG.get());
                        output.accept(MoonBlocks.FULL_MOON_LEAVES.get());
                        output.accept(MoonBlocks.FULL_MOON_CORE.get());
                        output.accept(MoonBlocks.FULL_MOON_SAPLING.get());
                        output.accept(MoonBlocks.MOON_PLANKS.get());
                        output.accept(MoonBlocks.MOON_PLANKS_DOOR.get());
                        output.accept(MoonBlocks.MOON_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.MOON_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.MOON_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.MOON_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.MOON_PLANKS_FENCE_GATE.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE.get());
                        // curve tree
                        output.accept(MoonBlocks.CURVE_LOG.get());
                        output.accept(MoonBlocks.STRIPPED_CURVE_LOG.get());
                        output.accept(MoonBlocks.CURVE_LEAVES.get());
                        output.accept(MoonBlocks.CURVE_SAPLING.get());
                        output.accept(MoonBlocks.CURVE_PLANKS.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_DOOR.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_SLAB.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_STAIRS.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_BUTTON.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_FENCE.get());
                        output.accept(MoonBlocks.CURVE_PLANKS_FENCE_GATE.get());
                        // mushroom
                        output.accept(MoonBlocks.PURPLE_MUSHROOM.get());
                        output.accept(MoonBlocks.PURPLE_MUSHROOM_BLOCK.get());
                        // Eye
                        output.accept(EyeBloodBlocks.EYE_LOG.get());
                        output.accept(EyeBloodBlocks.STRIPPED_EYE_LOG.get());
                        output.accept(EyeBloodBlocks.EYE_LEAVES.get());
                        output.accept(EyeBloodBlocks.EYE_JAR.get());
                        output.accept(ModItems.DEAD_EYE_BAT.get());
                        output.accept(ModItems.LIVING_EYE.get());
                        // Darkness
                        output.accept(Darkness.LOG_OF_DARKNESS.get());
                        output.accept(Darkness.STRIPPED_LOG_OF_DARKNESS.get());
                        output.accept(Darkness.DARKNESS_LEAVES.get());
                        output.accept(Darkness.DARKNESS_SAPLING.get());
                        output.accept(Darkness.DARKNESS_PLANKS.get());
                        output.accept(Darkness.DARKNESS_PLANKS_SLAB.get());
                        output.accept(Darkness.DARKNESS_PLANKS_STAIRS.get());
                        output.accept(Darkness.DARKNESS_PLANKS_BUTTON.get());
                        output.accept(Darkness.DARKNESS_PLANKS_FENCE.get());
                        output.accept(Darkness.DARKNESS_PLANKS_FENCE_GATE.get());
                        // Plants
                        output.accept(StarBlocks.STAR_FLOWER.get());
                        output.accept(StarBlocks.CELESTIAL_STAR_FLOWER.get());
                        output.accept(MoonBlocks.MOON_GRASS.get());
                        output.accept(MoonBlocks.TALL_MOON_GRASS.get());
                        output.accept(MoonBlocks.STAR_TRAP.get());
                        output.accept(MoonBlocks.MOON_FERN.get());
                        output.accept(MoonBlocks.FORGET_ME_NOW.get());
                        output.accept(Darkness.GRADI.get());
                        output.accept(EyeBloodBlocks.EYE_FERN.get());
                        output.accept(EyeBloodBlocks.EYES.get());
                        output.accept(Nebulas.PURPLE_TENTACLE_FLOWER.get());
                        output.accept(Nebulas.BLUE_TENTACLE_FLOWER.get());
                        output.accept(Nebulas.RED_TENTACLE_FLOWER.get());
                        output.accept(Nebulas.YELLOW_TENTACLE_FLOWER.get());
                        output.accept(MoonBlocks.SPRUNGUS.get());
                        // Items
                        output.accept(ModItems.STARDUST.get());
                        output.accept(ModItems.PURPLE_STAR.get());
                        output.accept(StarBlocks.PURPLE_STAR_BLOCK.get());
                        output.accept(Nebulas.PURPLE_NEBULA_LOG.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS_FENCE.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get());
                        output.accept(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS.get());
                        output.accept(Nebulas.PURPLE_NEBULA_LEAVES.get());
                        output.accept(Nebulas.PURPLE_NEBULA_REGROW_CORE.get());
                        output.accept(ModItems.BLUE_STAR.get());
                        output.accept(StarBlocks.BLUE_STAR_BLOCK.get());
                        output.accept(Nebulas.BLUE_NEBULA_LOG.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS_BUTTON.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS_FENCE.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS_SLAB.get());
                        output.accept(Nebulas.BLUE_NEBULA_PLANKS_STAIRS.get());
                        output.accept(Nebulas.BLUE_NEBULA_LEAVES.get());
                        output.accept(Nebulas.BLUE_NEBULA_REGROW_CORE.get());
                        output.accept(ModItems.RED_STAR.get());
                        output.accept(StarBlocks.RED_STAR_BLOCK.get());
                        output.accept(Nebulas.RED_NEBULA_LOG.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS_BUTTON.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS_FENCE.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS_SLAB.get());
                        output.accept(Nebulas.RED_NEBULA_PLANKS_STAIRS.get());
                        output.accept(Nebulas.RED_NEBULA_LEAVES.get());
                        output.accept(Nebulas.RED_NEBULA_REGROW_CORE.get());
                        output.accept(ModItems.END_STAR.get());
                        output.accept(ModItems.YELLOW_STAR.get());
                        output.accept(StarBlocks.YELLOW_STAR_BLOCK.get());
                        output.accept(Nebulas.YELLOW_NEBULA_LOG.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS_FENCE.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get());
                        output.accept(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS.get());
                        output.accept(Nebulas.YELLOW_NEBULA_LEAVES.get());
                        output.accept(Nebulas.YELLOW_NEBULA_REGROW_CORE.get());
                        output.accept(ModItems.LODESTAR.get());
                        output.accept(ModItems.GEODE_FRUIT.get());
                        output.accept(ModItems.COOKED_GEODE_FRUIT.get());
                        output.accept(ModItems.FULL_COOKED_GEODE_FRUIT.get());
                        output.accept(ModItems.BLACK_COOKED_GEODE_FRUIT.get());
                        output.accept(ModItems.ECTOPLASM.get());
                        output.accept(ModItems.COOLER_ECTOPLASM.get());
                        output.accept(ModItems.STAR_BANNER_PATTERN.get());
                        // SpawnEggs
                        output.accept(ModItems.GHOST_SPAWN_EGG.get());
                        output.accept(ModItems.AMETHYST_TURTLE_SPAWN_EGG.get());
                        output.accept(ModItems.EYE_BAT_SPAWN_EGG.get());
                        output.accept(ModItems.ROOK_SPAWN_EGG.get());
                        output.accept(ModItems.BLACK_ROOK_SPAWN_EGG.get());
                        output.accept(ModItems.SCRUBY_SPAWN_EGG.get());
                        output.accept(ModItems.BLACK_FOX_SPAWN_EGG.get());
                        // Chess
                        output.accept(Chess.BLACK_CHESSBOARD.get());
                        output.accept(Chess.WHITE_CHESSBOARD.get());
                        output.accept(Chess.CHESSBOARD.get());
                        output.accept(ModItems.WHITE_BRICK.get());
                        output.accept(ModItems.BLACK_BRICK.get());
                        output.accept(Chess.WHITE_BRICKS.get());
                        output.accept(Chess.BLACK_BRICKS.get());
                        // Hedge
                        output.accept(Hedges.OAK_HEDGE.get());
                        output.accept(Hedges.BIRCH_HEDGE.get());
                        output.accept(Hedges.ACACIA_HEDGE.get());
                        output.accept(Hedges.CHERRY_HEDGE.get());
                        output.accept(Hedges.DARK_OAK_HEDGE.get());
                        output.accept(Hedges.JUNGLE_HEDGE.get());
                        output.accept(Hedges.MANGROVE_HEDGE.get());
                        output.accept(Hedges.PALE_HEDGE.get());
                        output.accept(Hedges.SPRUCE_HEDGE.get());
                        output.accept(Hedges.MOON_HEDGE.get());
                        output.accept(Hedges.CURVE_HEDGE.get());
                        output.accept(Hedges.STAR_HEDGE.get());
                        output.accept(Hedges.DARKNESS_HEDGE.get());
                        output.accept(Hedges.YERI_HEDGE.get());
                        output.accept(Hedges.SPIRO_HEDGE.get());
                        output.accept(Hedges.TRUNN_HEDGE.get());
                        // Red Orb
                        output.accept(RedOrbBlocks.RED_ROCK.get());
                        output.accept(RedOrbBlocks.RED_ROCK_SLAB.get());
                        output.accept(RedOrbBlocks.RED_ROCK_STAIRS.get());
                        output.accept(RedOrbBlocks.POLISHED_RED_ROCK.get());
                        output.accept(RedOrbBlocks.YERI_LOG.get());
                        output.accept(RedOrbBlocks.YERI_LEAVES.get());
                        output.accept(RedOrbBlocks.YERI_SAPLING.get());
                        output.accept(ModItems.RED_ORB_PLATFORM_BASE.get());
                        output.accept(RedOrbBlocks.RED_ORB_PLATFORM.get());
                        output.accept(RedOrbBlocks.GREEN_ROCK.get());
                        output.accept(RedOrbBlocks.BLUE_GRASS.get());
                        output.accept(RedOrbBlocks.POINTY.get());
                        output.accept(RedOrbBlocks.PETRICY.get());
                        output.accept(RedOrbBlocks.SPIRO_LOG.get());
                        output.accept(RedOrbBlocks.SPIRO_LEAVES.get());
                        output.accept(RedOrbBlocks.SPIRO_SAPLING.get());
                        output.accept(RedOrbBlocks.GLASS_LOG.get());
                        // Wander
                        output.accept(ModItems.DARKSTAR.get());
                        output.accept(Wander.BORIL.get());
                        output.accept(Wander.PUROIL.get());
                        output.accept(Wander.TRUNN_LOG.get());
                        output.accept(Wander.TRUNN_LEAVES.get());
                        output.accept(Wander.TRUNN_SAPLING.get());
                        output.accept(ModItems.DREAM_BUCKET.get());
                        // Energy
                        output.accept(Energy.STARMACHINE_BLOCK.get());
                        output.accept(Energy.STARGENERATOR.get());
                        output.accept(Energy.YELLOW_CABLE.get());
                        output.accept(Energy.BLUE_CABLE.get());
                        output.accept(Energy.RED_CABLE.get());
                        output.accept(Energy.PURPLE_CABLE.get());
                        output.accept(Energy.NIGHT_WATCHER.get());
                        output.accept(Energy.STAR_CRUSHER.get());
                        output.accept(ModItems.COPPER_DUST.get());
                        output.accept(ModItems.IRON_DUST.get());
                        output.accept(ModItems.GOLD_DUST.get());
                        output.accept(ModItems.SUPERNOVA.get());

                        output.accept(ModItems.JESTER_HELMET.get());
                        output.accept(ModItems.JESTER_CHESTPLATE.get());
                        output.accept(ModItems.JESTER_LEGGINS.get());
                        output.accept(ModItems.JESTER_BOOTS.get());
                        // Masks
                        output.accept(ModItems.MASK_OF_LUNA.get());
                    })
    ));

    public static void init() {
        // Architectury's CreativeTabRegistry handles internal registration automatically.
        // Keep this method empty or call it during mod initialization so the class loads.
        Stargazer.LOGGER.info("Stargazer Creative Mode Tabs initialized");
        TABS.register();
    }
}