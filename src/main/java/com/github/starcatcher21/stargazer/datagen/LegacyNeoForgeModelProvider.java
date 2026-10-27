//? if neoforge && <= 1.21.1 {
package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.energy.cables.YellowCable;
import com.github.starcatcher21.stargazer.block.clases.moon.plants.MoonCrop;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.item.WishingStars;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Set;

/**
 * 1.21.1 NeoForge port of ModelProvider.
 * <p>
 * Uses the pre-26.2 API ({@code BlockStateProvider}, {@code ModelBuilder},
 * {@code VariantBlockStateBuilder}, {@code MultiPartBlockStateBuilder}).
 * <p>
 * Every {@code instanceof} guard here is a safety net: if a custom block
 * class does not extend the matching vanilla base class, the datagen
 * falls back to a cube / column model instead of throwing
 * {@link ClassCastException}. In-game behaviour of such a block will not
 * be that of a stair/slab/door/pillar anyway — the proper fix is to make
 * the custom class extend the vanilla base class.
 */
public final class LegacyNeoForgeModelProvider extends BlockStateProvider {
    private static final ExistingFileHelper.ResourceType TEXTURE_RESOURCE =
            new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");

    private static final Set<String> SOURCE_BLOCKSTATES = Set.of(
            "grave", "negative_block", "no_red_block", "no_green_block", "no_blue_block",
            "infested_calcite", "copper_teleporter", "dark_teleporter", "end_teleporter",
            "red_teleporter", "sprinkler", "moon_welder", "moon_farmland", "moon_rock_nylium",
            "moon_rock_tiles", "purple_moon_rock_tiles", "star_forge",
            "star_stone", "geode_fruit_block", "star_trap", "forget_me_now",
            "potted_forget_me_now", "cosmic_block", "star_barrier_block", "border_block",
            "star_leaves", "aurora", "star_display", "eye_log", "eye_jar", "eye_fern",
            "eyes", "gradi", "potted_gradi", "chessboard"
    );

    private final ExistingFileHelper existingFileHelper;

    public LegacyNeoForgeModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Stargazer.MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    // ---------------------------------------------------------------------
    // Entry point
    // ---------------------------------------------------------------------

    @Override
    protected void registerStatesAndModels() {
        // ---------------- Simple cubes ----------------
        cubeWithItem(ModBlock.BONE_LEAVES.get());
        cubeWithItem(MoonBlocks.CHISELED_MOON_ROCK_BRICKS.get());
        cubeWithItem(MoonBlocks.CRACKED_MOON_ROCK_BRICKS.get());
        cubeWithItem(MoonBlocks.MOON_LEAVES.get());
        cubeWithItem(MoonBlocks.FULL_MOON_LEAVES.get());
        cubeWithItem(MoonBlocks.FULL_MOON_CORE.get());
        cubeWithItem(MoonBlocks.CURVE_LEAVES.get());
        cubeWithItem(Darkness.DARKNESS_LEAVES.get());
        cubeWithItem(Wander.TRUNN_LEAVES.get());
        cubeWithItem(Wander.UMBRELLA_LEAVES.get());
        cubeWithItem(EyeBloodBlocks.EYE_LEAVES.get());
        cubeWithItem(Nebulas.BLUE_NEBULA_LEAVES.get());
        cubeWithItem(Nebulas.PURPLE_NEBULA_LEAVES.get());
        cubeWithItem(Nebulas.RED_NEBULA_LEAVES.get());
        cubeWithItem(Nebulas.YELLOW_NEBULA_LEAVES.get());
        cubeWithItem(Nebulas.BLUE_NEBULA_CORE.get());
        cubeWithItem(Nebulas.PURPLE_NEBULA_CORE.get());
        cubeWithItem(Nebulas.RED_NEBULA_CORE.get());
        cubeWithItem(Nebulas.YELLOW_NEBULA_CORE.get());
        cubeWithItem(MoonBlocks.SUN_ENRICHED_MOON_ROCK.get());
        cubeWithItem(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK.get());
        cubeWithItem(Wander.PUROIL.get());
        cubeWithItem(Chess.BLACK_CHESSBOARD.get());
        cubeWithItem(Chess.WHITE_CHESSBOARD.get());
        cubeWithItem(Chess.BLACK_BRICKS.get());
        cubeWithItem(Chess.WHITE_BRICKS.get());
        cubeWithItem(StarBlocks.RED_STAR_BLOCK.get());
        cubeWithItem(StarBlocks.BLUE_STAR_BLOCK.get());
        cubeWithItem(StarBlocks.YELLOW_STAR_BLOCK.get());
        cubeWithItem(StarBlocks.PURPLE_STAR_BLOCK.get());
        cubeWithItem(RedOrbBlocks.POLISHED_RED_ROCK.get());
        cubeWithItem(RedOrbBlocks.YERI_LEAVES.get());
        cubeWithItem(RedOrbBlocks.GREEN_ROCK.get());
        cubeWithItem(RedOrbBlocks.SPIRO_LEAVES.get());
        cubeWithItem(MoonBlocks.COMET_BLOCK.get());
        cubeWithItem(Energy.STARGENERATOR.get());
        cubeWithItem(Energy.STARMACHINE_BLOCK.get());
        cubeWithItem(Energy.NIGHT_WATCHER.get());
        cubeWithItem(Energy.STAR_CRUSHER.get());
        cubeWithItem(MoonBlocks.MOON_ROCK_CRYSTALS.get());
        cubeWithItem(MoonBlocks.CRYSTAL_MOON_BLOCK.get());
        cubeWithItem(MoonBlocks.MOON_ROCK_IRON_ORE.get());
        cubeWithItem(ModBlock.AMETHYST_DUST_BLOCK.get());
        cubeWithItem(ModBlock.PURE_AMETHYST_DUST_BLOCK.get());
        cubeWithItem(MoonBlocks.MOON_ROCK.get());
        cubeWithItem(MoonBlocks.POLISHED_MOON_ROCK.get());
        cubeWithItem(MoonBlocks.BLACK_MOON_ROCK.get());
        cubeWithItem(MoonBlocks.POLISHED_BLACK_MOON_ROCK.get());
        cubeWithItem(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE.get());
        cubeWithItem(MoonBlocks.PRISMATIC_ORE.get());
        cubeWithItem(MoonBlocks.PRISMATIC_SHARD_BLOCK.get());
        cubeWithItem(ModBlock.AMETHYST_BRICKS.get());

        // ---------------- Item models for source blockstates ----------------
        blockItem(EyeBloodBlocks.EYE_LOG.get(), "closed_eye_log");
        blockItem(EyeBloodBlocks.EYES.get(), "eyes1");
        blockItem(Chess.CHESSBOARD.get(), "chess");

        // ---------------- Wood families ----------------
        planks(MoonBlocks.MOON_PLANKS.get(), MoonBlocks.MOON_PLANKS_STAIRS.get(),
                MoonBlocks.MOON_PLANKS_SLAB.get(), MoonBlocks.MOON_PLANKS_FENCE.get(),
                MoonBlocks.MOON_PLANKS_FENCE_GATE.get(), MoonBlocks.MOON_PLANKS_BUTTON.get());
        planks(MoonBlocks.YELLOW_MOON_PLANKS.get(), MoonBlocks.YELLOW_MOON_PLANKS_STAIRS.get(),
                MoonBlocks.YELLOW_MOON_PLANKS_SLAB.get(), MoonBlocks.YELLOW_MOON_PLANKS_FENCE.get(),
                MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE.get(), MoonBlocks.YELLOW_MOON_PLANKS_BUTTON.get());
        planks(MoonBlocks.BLUE_MOON_PLANKS.get(), MoonBlocks.BLUE_MOON_PLANKS_STAIRS.get(),
                MoonBlocks.BLUE_MOON_PLANKS_SLAB.get(), MoonBlocks.BLUE_MOON_PLANKS_FENCE.get(),
                MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE.get(), MoonBlocks.BLUE_MOON_PLANKS_BUTTON.get());
        planks(MoonBlocks.RED_MOON_PLANKS.get(), MoonBlocks.RED_MOON_PLANKS_STAIRS.get(),
                MoonBlocks.RED_MOON_PLANKS_SLAB.get(), MoonBlocks.RED_MOON_PLANKS_FENCE.get(),
                MoonBlocks.RED_MOON_PLANKS_FENCE_GATE.get(), MoonBlocks.RED_MOON_PLANKS_BUTTON.get());
        planks(MoonBlocks.PURPLE_MOON_PLANKS.get(), MoonBlocks.PURPLE_MOON_PLANKS_STAIRS.get(),
                MoonBlocks.PURPLE_MOON_PLANKS_SLAB.get(), MoonBlocks.PURPLE_MOON_PLANKS_FENCE.get(),
                MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE.get(), MoonBlocks.PURPLE_MOON_PLANKS_BUTTON.get());
        planks(StarBlocks.STAR_PLANKS.get(), StarBlocks.STAR_PLANKS_STAIRS.get(),
                StarBlocks.STAR_PLANKS_SLAB.get(), StarBlocks.STAR_PLANKS_FENCE.get(),
                StarBlocks.STAR_PLANKS_FENCE_GATE.get(), StarBlocks.STAR_PLANKS_BUTTON.get());
        planks(MoonBlocks.CURVE_PLANKS.get(), MoonBlocks.CURVE_PLANKS_STAIRS.get(),
                MoonBlocks.CURVE_PLANKS_SLAB.get(), MoonBlocks.CURVE_PLANKS_FENCE.get(),
                MoonBlocks.CURVE_PLANKS_FENCE_GATE.get(), MoonBlocks.CURVE_PLANKS_BUTTON.get());
        planks(Darkness.DARKNESS_PLANKS.get(), Darkness.DARKNESS_PLANKS_STAIRS.get(),
                Darkness.DARKNESS_PLANKS_SLAB.get(), Darkness.DARKNESS_PLANKS_FENCE.get(),
                Darkness.DARKNESS_PLANKS_FENCE_GATE.get(), Darkness.DARKNESS_PLANKS_BUTTON.get());
        planks(Nebulas.BLUE_NEBULA_PLANKS.get(), Nebulas.BLUE_NEBULA_PLANKS_STAIRS.get(),
                Nebulas.BLUE_NEBULA_PLANKS_SLAB.get(), Nebulas.BLUE_NEBULA_PLANKS_FENCE.get(),
                Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE.get(), Nebulas.BLUE_NEBULA_PLANKS_BUTTON.get());
        planks(Nebulas.PURPLE_NEBULA_PLANKS.get(), Nebulas.PURPLE_NEBULA_PLANKS_STAIRS.get(),
                Nebulas.PURPLE_NEBULA_PLANKS_SLAB.get(), Nebulas.PURPLE_NEBULA_PLANKS_FENCE.get(),
                Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE.get(), Nebulas.PURPLE_NEBULA_PLANKS_BUTTON.get());
        planks(Nebulas.RED_NEBULA_PLANKS.get(), Nebulas.RED_NEBULA_PLANKS_STAIRS.get(),
                Nebulas.RED_NEBULA_PLANKS_SLAB.get(), Nebulas.RED_NEBULA_PLANKS_FENCE.get(),
                Nebulas.RED_NEBULA_PLANKS_FENCE_GATE.get(), Nebulas.RED_NEBULA_PLANKS_BUTTON.get());
        planks(Nebulas.YELLOW_NEBULA_PLANKS.get(), Nebulas.YELLOW_NEBULA_PLANKS_STAIRS.get(),
                Nebulas.YELLOW_NEBULA_PLANKS_SLAB.get(), Nebulas.YELLOW_NEBULA_PLANKS_FENCE.get(),
                Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE.get(), Nebulas.YELLOW_NEBULA_PLANKS_BUTTON.get());
        planks(RedOrbBlocks.YERI_PLANKS.get(), RedOrbBlocks.YERI_PLANKS_STAIRS.get(),
                RedOrbBlocks.YERI_PLANKS_SLAB.get(), RedOrbBlocks.YERI_PLANKS_FENCE.get(),
                RedOrbBlocks.YERI_PLANKS_FENCE_GATE.get(), RedOrbBlocks.YERI_PLANKS_BUTTON.get());
        planks(RedOrbBlocks.SPIRO_PLANKS.get(), RedOrbBlocks.SPIRO_PLANKS_STAIRS.get(),
                RedOrbBlocks.SPIRO_PLANKS_SLAB.get(), RedOrbBlocks.SPIRO_PLANKS_FENCE.get(),
                RedOrbBlocks.SPIRO_PLANKS_FENCE_GATE.get(), RedOrbBlocks.SPIRO_PLANKS_BUTTON.get());
        planks(Wander.TRUNN_PLANKS.get(), Wander.TRUNN_PLANKS_STAIRS.get(),
                Wander.TRUNN_PLANKS_SLAB.get(), Wander.TRUNN_PLANKS_FENCE.get(),
                Wander.TRUNN_PLANKS_FENCE_GATE.get(), Wander.TRUNN_PLANKS_BUTTON.get());

        // Stone family with slab + stairs
        stoneFamily(MoonBlocks.MOON_ROCK_BRICKS.get(),
                MoonBlocks.MOON_ROCK_BRICKS_STAIRS.get(), MoonBlocks.MOON_ROCK_BRICKS_SLAB.get());
        stoneFamily(RedOrbBlocks.RED_ROCK.get(),
                RedOrbBlocks.RED_ROCK_STAIRS.get(), RedOrbBlocks.RED_ROCK_SLAB.get());

        // ---------------- Doors ----------------
        door(StarBlocks.STAR_PLANKS_DOOR.get());
        door(RedOrbBlocks.YERI_PLANKS_DOOR.get());
        door(MoonBlocks.CURVE_PLANKS_DOOR.get());
        door(Darkness.DARKNESS_PLANKS_DOOR.get());
        door(MoonBlocks.MOON_PLANKS_DOOR.get());

        // ---------------- Pillars / logs ----------------
        pillar(MoonBlocks.MOON_LOG.get());
        pillar(MoonBlocks.FULL_MOON_LOG.get());
        pillar(MoonBlocks.STRIPPED_MOON_LOG.get());
        pillar(MoonBlocks.CURVE_LOG.get());
        pillar(MoonBlocks.STRIPPED_CURVE_LOG.get());
        pillar(StarBlocks.STAR_LOG.get());
        pillar(StarBlocks.STRIPPED_STAR_LOG.get());
        pillar(Darkness.LOG_OF_DARKNESS.get());
        pillar(Darkness.STRIPPED_LOG_OF_DARKNESS.get());
        pillar(EyeBloodBlocks.STRIPPED_EYE_LOG.get());
        pillar(Nebulas.BLUE_NEBULA_LOG.get());
        pillar(Nebulas.RED_NEBULA_LOG.get());
        pillar(Nebulas.PURPLE_NEBULA_LOG.get());
        pillar(Nebulas.YELLOW_NEBULA_LOG.get());
        pillar(Wander.TRUNN_LOG.get());
        pillar(Wander.UMBRELLA_LOG.get());
        pillar(RedOrbBlocks.YERI_LOG.get());
        pillar(RedOrbBlocks.SPIRO_LOG.get());
        pillar(RedOrbBlocks.GLASS_LOG.get());

        // ---------------- Cross plants ----------------
        crossWithItem(MoonBlocks.MOON_GRASS.get());
        crossWithItem(MoonBlocks.MOON_FERN.get());
        crossWithItem(RedOrbBlocks.BLUE_GRASS.get());
        cross(RedOrbBlocks.PETRICY.get());

        // ---------------- Double plant ----------------
        doublePlant(MoonBlocks.TALL_MOON_GRASS.get());

        // ---------------- Mushroom block ----------------
        mushroomBlock(MoonBlocks.PURPLE_MUSHROOM_BLOCK.get());

        // ---------------- Crops ----------------
        crop(Crops.DRAGON_CARROT_BLOCK.get(), 0, 1, 2, 3, 4, 5, 6, 7);
        crop(Crops.BROODY_BLOCK.get(), 0, 1, 2, 3, 4, 5, 6, 7);
        crop(Crops.EYE_BALLS_BLOCK.get(), 0, 1, 2, 3, 4, 5, 6, 7);

        // ---------------- Pot plants ----------------
        potPlant(MoonBlocks.PURPLE_MUSHROOM.get(), MoonBlocks.POTTED_PURPLE_MUSHROOM.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(MoonBlocks.MOON_SAPLING.get(), MoonBlocks.POTTED_MOON_SAPLING.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(MoonBlocks.CURVE_SAPLING.get(), MoonBlocks.POTTED_CURVE_SAPLING.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(StarBlocks.STAR_SAPLING.get(), StarBlocks.POTTED_STAR_SAPLING.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Darkness.DARKNESS_SAPLING.get(), Darkness.POTTED_DARKNESS_SAPLING.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(RedOrbBlocks.YERI_SAPLING.get(), RedOrbBlocks.POTTED_YERI_SAPLING.get(), RedOrbBlocks.RED_ROCK.get());
        potPlant(MoonBlocks.FULL_MOON_SAPLING.get(), MoonBlocks.POTTED_FULL_MOON_SAPLING.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(RedOrbBlocks.SPIRO_SAPLING.get(), RedOrbBlocks.POTTED_SPIRO_SAPLING.get(), RedOrbBlocks.RED_ROCK.get());
        potPlant(Wander.TRUNN_SAPLING.get(), Wander.POTTED_TRUNN_SAPLING.get(), Wander.PUROIL.get());
        potPlant(Wander.UMBRELLA_SAPLING.get(), Wander.POTTED_UMBRELLA_SAPLING.get(), Wander.PUROIL.get());
        potPlant(StarBlocks.STAR_FLOWER.get(), StarBlocks.POTTED_STAR_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(StarBlocks.CELESTIAL_STAR_FLOWER.get(), StarBlocks.POTTED_CELESTIAL_STAR_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(ModBlock.BONEFLOWER.get(), ModBlock.POTTED_BONEFLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Nebulas.RED_TENTACLE_FLOWER.get(), Nebulas.POTTED_RED_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Nebulas.YELLOW_TENTACLE_FLOWER.get(), Nebulas.POTTED_YELLOW_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Nebulas.BLUE_TENTACLE_FLOWER.get(), Nebulas.POTTED_BLUE_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Nebulas.PURPLE_TENTACLE_FLOWER.get(), Nebulas.POTTED_PURPLE_TENTACLE_FLOWER.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(Darkness.ROSE_OF_PAIN.get(), Darkness.POTTED_ROSE_OF_PAIN.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(MoonBlocks.SPRUNGUS.get(), MoonBlocks.POTTED_SPRUNGUS.get(), MoonBlocks.MOON_ROCK.get());
        potPlant(RedOrbBlocks.POINTY.get(), RedOrbBlocks.POTTED_POINTY.get(), RedOrbBlocks.RED_ROCK.get());

        // ---------------- Bottom-top blocks ----------------
        cubeBottomTop(Darkness.DYLIUM.get(), "dylium_side", "moon_rock", "dylium_top");
        cubeBottomTop(Wander.BORIL.get(), "boril_side", "puroil", "boril_top");

        // ---------------- Hedges ----------------
        hedge(Hedges.ACACIA_HEDGE.get(), Blocks.ACACIA_LOG);
        hedge(Hedges.BIRCH_HEDGE.get(), Blocks.BIRCH_LOG);
        hedge(Hedges.CHERRY_HEDGE.get(), Blocks.CHERRY_LOG);
        hedge(Hedges.DARK_OAK_HEDGE.get(), Blocks.DARK_OAK_LOG);
        hedge(Hedges.JUNGLE_HEDGE.get(), Blocks.JUNGLE_LOG);
        hedge(Hedges.MANGROVE_HEDGE.get(), Blocks.MANGROVE_LOG);
        hedge(Hedges.OAK_HEDGE.get(), Blocks.OAK_LOG);
        hedge(Hedges.SPRUCE_HEDGE.get(), Blocks.SPRUCE_LOG);
        hedgeSide(Hedges.CURVE_HEDGE.get(), MoonBlocks.CURVE_LOG.get());
        hedgeSide(Hedges.DARKNESS_HEDGE.get(), Darkness.LOG_OF_DARKNESS.get());
        hedgeSide(Hedges.MOON_HEDGE.get(), MoonBlocks.MOON_LOG.get());
        hedgeSide(Hedges.FULL_MOON_HEDGE.get(), MoonBlocks.FULL_MOON_LOG.get());
        hedgeSide(Hedges.STAR_HEDGE.get(), StarBlocks.STAR_LOG.get());
        hedgeSide(Hedges.YERI_HEDGE.get(), RedOrbBlocks.YERI_LOG.get());
        hedgeSide(Hedges.SPIRO_HEDGE.get(), RedOrbBlocks.SPIRO_LOG.get());
        hedgeSide(Hedges.TRUNN_HEDGE.get(), Wander.TRUNN_LOG.get());
        hedgeSide(Hedges.UMBRELLA_HEDGE.get(), Wander.UMBRELLA_LOG.get());

        // ---------------- Cables ----------------
        cable(Energy.YELLOW_CABLE.get());
        cable(Energy.BLUE_CABLE.get());
        cable(Energy.RED_CABLE.get());
        cable(Energy.PURPLE_CABLE.get());

        // ---------------- Flat item models for blocks ----------------
        blockGeneratedItem(StarBlocks.STAR_FLOWER.get());
        blockGeneratedItem(StarBlocks.CELESTIAL_STAR_FLOWER.get());
        blockGeneratedItem(ModBlock.BONEFLOWER.get());
        blockGeneratedItem(RedOrbBlocks.POINTY.get());
        blockGeneratedItem(RedOrbBlocks.PETRICY.get());
        blockGeneratedItem(Nebulas.RED_TENTACLE_FLOWER.get());
        blockGeneratedItem(Nebulas.BLUE_TENTACLE_FLOWER.get());
        blockGeneratedItem(Nebulas.PURPLE_TENTACLE_FLOWER.get());
        blockGeneratedItem(Nebulas.YELLOW_TENTACLE_FLOWER.get());
        blockGeneratedItem(MoonBlocks.PURPLE_MUSHROOM.get());
        blockGeneratedItem(MoonBlocks.MOON_SAPLING.get());
        blockGeneratedItem(Wander.UMBRELLA_SAPLING.get());
        blockGeneratedItem(Wander.TRUNN_SAPLING.get());
        blockGeneratedItem(MoonBlocks.CURVE_SAPLING.get());
        blockGeneratedItem(StarBlocks.STAR_SAPLING.get());
        blockGeneratedItem(RedOrbBlocks.YERI_SAPLING.get());
        blockGeneratedItem(RedOrbBlocks.SPIRO_SAPLING.get());
        blockGeneratedItem(Darkness.DARKNESS_SAPLING.get());
        blockGeneratedItem(MoonBlocks.FULL_MOON_SAPLING.get());
        blockGeneratedItem(Darkness.ROSE_OF_PAIN.get());
        blockGeneratedItem(MoonBlocks.SPRUNGUS.get());

        // ---------------- Item models (flat items) ----------------
        flatItem(ModItems.LODESTAR.get());
        flatItem(ModItems.FULL_MOON_DUST.get());
        flatItem(ModItems.PURE_AMETHYST_DUST.get());
        flatItem(ModItems.AMETHYST_DUST.get());
        flatItem(ModItems.PURE_AMETHYST_INGOT.get());
        flatItem(ModItems.IRON_DUST.get());
        flatItem(ModItems.GOLD_DUST.get());
        flatItem(ModItems.COPPER_DUST.get());
        flatItem(ModItems.SUPERNOVA.get());
        flatItem(ModItems.DARKSTAR.get());
        flatItem(ModItems.STARDUST.get());
        flatItem(ModItems.GEODE_FRUIT.get());
        flatItem(ModItems.COOKED_GEODE_FRUIT.get());
        flatItem(ModItems.FULL_COOKED_GEODE_FRUIT.get());
        flatItem(ModItems.BLACK_COOKED_GEODE_FRUIT.get());
        flatItem(ModItems.BLUE_STAR.get());
        flatItem(ModItems.RED_STAR.get());
        flatItem(ModItems.YELLOW_STAR.get());
        flatItem(ModItems.END_STAR.get());
        flatItem(ModItems.PURPLE_STAR.get());
        flatItem(ModItems.DREAM_STAR.get());
        flatItem(ModItems.MOON_GLASS_SHARD.get());
        flatItem(ModItems.PRISMATIC_INGOT.get());
        flatItem(WishingStars.WISHING_STAR.get());
        flatItem(WishingStars.WHITE_WISHING_STAR.get());
        flatItem(WishingStars.LIGHT_GRAY_WISHING_STAR.get());
        flatItem(WishingStars.GRAY_WISHING_STAR.get());
        flatItem(WishingStars.BLACK_WISHING_STAR.get());
        flatItem(WishingStars.BROWN_WISHING_STAR.get());
        flatItem(WishingStars.RED_WISHING_STAR.get());
        flatItem(WishingStars.ORANGE_WISHING_STAR.get());
        flatItem(WishingStars.LIME_WISHING_STAR.get());
        flatItem(WishingStars.GREEN_WISHING_STAR.get());
        flatItem(WishingStars.CYAN_WISHING_STAR.get());
        flatItem(WishingStars.LIGHT_BLUE_WISHING_STAR.get());
        flatItem(WishingStars.BLUE_WISHING_STAR.get());
        flatItem(WishingStars.PURPLE_WISHING_STAR.get());
        flatItem(WishingStars.MAGENTA_WISHING_STAR.get());
        flatItem(WishingStars.PINK_WISHING_STAR.get());
        flatItem(ModItems.THROWABLE_STAR.get());
        flatItem(ModItems.STAR_BANNER_PATTERN.get());
        flatItem(ModItems.STAR_HAMMER.get());
        flatItem(ModItems.SUN_ENRICHED_YELLOW_STAR.get());
        flatItem(ModItems.WINGED_STAR.get());
        flatItem(ModItems.GUMMY_FISH.get());
        flatItem(ModItems.GUMMY_WORM.get());
        flatItem(ModItems.COSMO_FISH.get());
        flatItem(ModItems.ENDER_FISH.get());
        flatItem(ModItems.GOLDEN_CRUCIAN.get());
        flatItem(ModItems.LUCKY_COMET.get());
        flatItem(ModItems.COMET_FRAGMENT.get());
        flatItem(ModItems.AURORA_FRAGMENT.get());
        flatItem(ModItems.STAR_BOOK.get());
        flatItem(MoonBlocks.TALL_MOON_GRASS.get().asItem());
        flatItem(ModItems.MOON_COOKIE.get());
        flatItem(ModItems.STAR_COOKIE.get());
        flatItem(ModItems.MASK_OF_LUNA.get());
        flatItem(ModItems.JESTER_BOOTS.get());
        flatItem(ModItems.JESTER_CHESTPLATE.get());
        flatItem(ModItems.JESTER_LEGGINS.get());
        flatItem(ModItems.JESTER_HELMET.get());
        flatItem(ModItems.CRYSTAL_MOON.get());
        flatItem(ModItems.MOON_BOOTS.get());
        flatItem(ModItems.MOON_CHESTPLATE.get());
        flatItem(ModItems.MOON_LEGGINS.get());
        flatItem(ModItems.MOON_HELMET.get());
        flatItem(ModItems.AMETHYST_BOOTS.get());
        flatItem(ModItems.AMETHYST_CHESTPLATE.get());
        flatItem(ModItems.AMETHYST_LEGGINS.get());
        flatItem(ModItems.AMETHYST_HELMET.get());
        flatItem(ModItems.SEED_PACKET.get());
        flatItem(Crops.DRAGON_CARROT.get());
        flatItem(Crops.BROODY.get());
        flatItem(Crops.EYE_BALLS.get());
        flatItem(ModItems.DEAD_EYE_BAT.get());
        flatItem(ModItems.LIVING_EYE.get());
        flatItem(ModItems.PRISMATIC_SHARD.get());
        flatItem(ModItems.ECTOPLASM.get());
        flatItem(ModItems.COOLER_ECTOPLASM.get());
        flatItem(ModItems.BLACK_BRICK.get());
        flatItem(ModItems.WHITE_BRICK.get());
        flatItem(StarBlocks.STAR_DISPLAY.get().asItem());
        // Spawn eggs
        flatItem(ModItems.GHOST_SPAWN_EGG.get());
        flatItem(ModItems.AMETHYST_TURTLE_SPAWN_EGG.get());
        flatItem(ModItems.EYE_BAT_SPAWN_EGG.get());
        flatItem(ModItems.SCRUBY_SPAWN_EGG.get());
        flatItem(ModItems.ROOK_SPAWN_EGG.get());
        flatItem(ModItems.BLACK_ROOK_SPAWN_EGG.get());
        flatItem(ModItems.BLACK_FOX_SPAWN_EGG.get());
        flatItem(ModItems.SPACE_JAM.get());
    }

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    private static String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private ResourceLocation blockTex(Block block) {
        return modLoc("block/" + name(block));
    }

    private ResourceLocation blockTex(String path) {
        return modLoc("block/" + path);
    }

    /** Simple cube + item (matches modern {@code createTrivialCube}). */
    private void cubeWithItem(Block block) {
        if (SOURCE_BLOCKSTATES.contains(name(block))) return;
        ModelFile model = models().cubeAll(name(block), blockTex(block));
        simpleBlockWithItem(block, model);
    }

    /**
     * Wood-family: planks + stairs + slab + fence + gate + button.
     * Every child is guarded with {@code instanceof}: if a custom block
     * class doesn't extend the matching vanilla base class, it falls back
     * to a plain cube rendered with the planks texture.
     */
    private void planks(Block planks, Block stairs, Block slab, Block fence, Block gate, Block button) {
        ResourceLocation tex = blockTex(planks);

        // Plan
        simpleBlockWithItem(planks, models().cubeAll(name(planks), tex));

        // Stairs
        if (stairs instanceof StairBlock s) {
            stairsBlock(s, tex);
            itemModels().withExistingParent(name(stairs), modLoc("block/" + name(stairs)));
        } else {
            simpleBlockWithItem(stairs, models().cubeAll(name(stairs), tex));
        }

        // Slab
        if (slab instanceof SlabBlock s) {
            slabBlock(s, tex, tex);
            itemModels().withExistingParent(name(slab), modLoc("block/" + name(slab)));
        } else {
            simpleBlockWithItem(slab, models().cubeAll(name(slab), tex));
        }

        // Fence
        if (fence instanceof FenceBlock f) {
            fenceBlock(f, tex);
            itemModels().withExistingParent(name(fence), mcLoc("block/fence_inventory"))
                    .texture("texture", tex);
        } else {
            simpleBlockWithItem(fence, models().cubeAll(name(fence), tex));
        }

        // Fence gate
        if (gate instanceof FenceGateBlock g) {
            fenceGateBlock(g, tex);
            itemModels().withExistingParent(name(gate), modLoc("block/" + name(gate)));
        } else {
            simpleBlockWithItem(gate, models().cubeAll(name(gate), tex));
        }

        // Button
        if (button instanceof ButtonBlock b) {
            buttonBlock(b, tex);
            itemModels().withExistingParent(name(button), mcLoc("block/button_inventory"))
                    .texture("texture", tex);
        } else {
            simpleBlockWithItem(button, models().cubeAll(name(button), tex));
        }
    }

    /** Stone family with just slab + stairs. */
    private void stoneFamily(Block base, Block stairs, Block slab) {
        ResourceLocation tex = blockTex(base);
        simpleBlockWithItem(base, models().cubeAll(name(base), tex));
        if (stairs instanceof StairBlock s) {
            stairsBlock(s, tex);
            itemModels().withExistingParent(name(stairs), modLoc("block/" + name(stairs)));
        } else {
            simpleBlockWithItem(stairs, models().cubeAll(name(stairs), tex));
        }
        if (slab instanceof SlabBlock s) {
            slabBlock(s, tex, tex);
            itemModels().withExistingParent(name(slab), modLoc("block/" + name(slab)));
        } else {
            simpleBlockWithItem(slab, models().cubeAll(name(slab), tex));
        }
    }

    /**
     * Axis-aligned pillar (log).
     * <p>
     * Always emits {@code axis=x/y/z} variants when the block has
     * {@link BlockStateProperties#AXIS}. {@code instanceof RotatedPillarBlock}
     * is not required — custom logs already define {@code AXIS} themselves.
     */
    private void pillar(Block block) {
        if (SOURCE_BLOCKSTATES.contains(name(block))) return;

        ResourceLocation side = blockTex(name(block) + "_side");
        ResourceLocation top = blockTex(name(block) + "_top");
        if (!existingFileHelper.exists(top, TEXTURE_RESOURCE)) {
            top = side;
        }

        ModelFile vertical = models().cubeColumn(name(block), side, top);
        ModelFile horizontal = models().cubeColumnHorizontal(name(block) + "_horizontal", side, top);

        if (block.defaultBlockState().hasProperty(BlockStateProperties.AXIS)) {
            getVariantBuilder(block)
                    .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y)
                    .modelForState().modelFile(vertical).addModel()
                    .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z)
                    .modelForState().modelFile(horizontal).rotationX(90).addModel()
                    .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X)
                    .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        } else if (block instanceof RotatedPillarBlock rp) {
            axisBlock(rp, vertical, horizontal);
        } else {
            simpleBlock(block, vertical);
        }
        itemModels().withExistingParent(name(block), modLoc("block/" + name(block)));
    }

    /**
     * Door with matching bottom/top textures following vanilla naming:
     * {@code {name}_bottom} / {@code {name}_top}.
     * <p>
     * Falls back to the planks texture if those files don't exist, and to
     * a plain cube if the block isn't a {@link DoorBlock}.
     */
    private void door(Block block) {
        String n = name(block);

        // Resolve textures independently
        ResourceLocation bottom = blockTex(n + "_bottom");
        if (!existingFileHelper.exists(bottom, TEXTURE_RESOURCE)) {
            String planksName = n.endsWith("_door") ? n.substring(0, n.length() - 5) : n;
            bottom = blockTex(planksName);
        }

        ResourceLocation top = blockTex(n + "_top");
        if (!existingFileHelper.exists(top, TEXTURE_RESOURCE)) {
            top = bottom;
        }

        if (block instanceof DoorBlock d) {
            // This is the crucial change
            doorBlockWithRenderType(d, n, bottom, top, "cutout");

            // Item model logic (unchanged)
            ResourceLocation itemTex = modLoc("item/" + n);
            if (existingFileHelper.exists(itemTex, TEXTURE_RESOURCE)) {
                itemModels().withExistingParent(n, "item/generated")
                        .texture("layer0", itemTex);
            } else {
                itemModels().withExistingParent(n, modLoc("block/" + n + "_bottom_left"));
            }
        } else {
            simpleBlockWithItem(block, models().cubeAll(n, bottom));
        }
    }

    /** Cross-model plant without an item model. */
    private void cross(Block block) {
        ModelFile model = models().cross(name(block), blockTex(block)).renderType("cutout");
        simpleBlock(block, model);
    }

    /** Cross-model plant that also emits a flat item model. */
    private void crossWithItem(Block block) {
        cross(block);
        itemModels().withExistingParent(name(block), "item/generated")
                .texture("layer0", blockTex(block));
    }

    /** Two-part vertical plant (tall grass). */
    private void doublePlant(Block block) {
        ModelFile lower = models().cross(name(block) + "_bottom", blockTex(block)).renderType("cutout");
        ModelFile upper = models().cross(name(block) + "_top", blockTex(block)).renderType("cutout");
        getVariantBuilder(block)
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(lower).addModel()
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(upper).addModel();
    }

    /** Mushroom block – cube_all fallback. */
    private void mushroomBlock(Block block) {
        simpleBlockWithItem(block, models().cubeAll(name(block), blockTex(block)));
    }

    /** Crop block with per-stage cross models. */
    private void crop(Block block, int... stages) {
        var builder = getVariantBuilder(block);
        for (int stage : stages) {
            ModelFile model = models()
                    .crop(name(block) + "_stage" + stage, blockTex(name(block) + "_stage" + stage))
                    .renderType("cutout");
            builder.partialState().with(MoonCrop.AGE, stage)
                    .modelForState().modelFile(model).addModel();
        }
    }

    /** Flower-pot plant: cross block + custom pot model + flat item model. */
    private void potPlant(Block plant, Block pot, Block dirt) {
        cross(plant);
        ModelFile potModel = models()
                .withExistingParent(name(pot), modLoc("block/potted_plant_custom"))
                .texture("plant", blockTex(plant))
                .texture("dirt", blockTex(dirt));
        simpleBlock(pot, potModel);
        itemModels().withExistingParent(name(plant), "item/generated")
                .texture("layer0", blockTex(plant));
    }

    /** Bottom-top cube (dylium / boril). */
    private void cubeBottomTop(Block block, String side, String bottom, String top) {
        ModelFile model = models().cubeBottomTop(
                name(block), blockTex(side), blockTex(bottom), blockTex(top));
        simpleBlockWithItem(block, model);
    }

    /** Hedge using {@code block/hedge} template. */
    private void hedge(Block hedge, Block log) {
        ResourceLocation logKey = BuiltInRegistries.BLOCK.getKey(log);
        String ns = logKey.getNamespace();
        ResourceLocation topTex = ns.equals(Stargazer.MOD_ID)
                ? modLoc("block/" + logKey.getPath() + "_top")
                : ResourceLocation.fromNamespaceAndPath(ns, "block/" + logKey.getPath() + "_top");
        ResourceLocation sideTex = ns.equals(Stargazer.MOD_ID)
                ? modLoc("block/" + logKey.getPath())
                : ResourceLocation.fromNamespaceAndPath(ns, "block/" + logKey.getPath());
        if (!existingFileHelper.exists(topTex, TEXTURE_RESOURCE)) {
            topTex = sideTex;
        }
        ModelFile model = models()
                .withExistingParent(name(hedge), modLoc("block/hedge"))
                .texture("top", topTex)
                .texture("side", sideTex);
        simpleBlockWithItem(hedge, model);
    }

    /** Hedge variant that expects a distinct {@code _side} texture on the log. */
    private void hedgeSide(Block hedge, Block log) {
        ResourceLocation side = blockTex(name(log) + "_side");
        ResourceLocation top = blockTex(name(log) + "_top");
        if (!existingFileHelper.exists(top, TEXTURE_RESOURCE)) {
            top = blockTex(log);
        }
        if (!existingFileHelper.exists(side, TEXTURE_RESOURCE)) {
            side = blockTex(log);
        }
        ModelFile model = models()
                .withExistingParent(name(hedge), modLoc("block/hedge"))
                .texture("top", top)
                .texture("side", side);
        simpleBlockWithItem(hedge, model);
    }

    /** Energy cable via multipart. */
    private void cable(Block cable) {
        ResourceLocation tex = blockTex(cable);
        ModelFile core = models()
                .withExistingParent(name(cable), modLoc("block/cable_core_template"))
                .texture("all", tex).texture("particle", tex);
        ModelFile side = models()
                .withExistingParent(name(cable) + "_side", modLoc("block/cable_side_template"))
                .texture("all", tex).texture("particle", tex);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(cable);
        builder.part().modelFile(core).addModel();

        builder.part().modelFile(side).uvLock(false).rotationY(0).addModel()
                .condition(YellowCable.NORTH, true);
        builder.part().modelFile(side).uvLock(false).rotationY(90).addModel()
                .condition(YellowCable.EAST, true);
        builder.part().modelFile(side).uvLock(false).rotationY(180).addModel()
                .condition(YellowCable.SOUTH, true);
        builder.part().modelFile(side).uvLock(false).rotationY(270).addModel()
                .condition(YellowCable.WEST, true);
        builder.part().modelFile(side).uvLock(false).rotationX(270).addModel()
                .condition(YellowCable.UP, true);
        builder.part().modelFile(side).uvLock(false).rotationX(90).addModel()
                .condition(YellowCable.DOWN, true);

        itemModels().withExistingParent(name(cable), modLoc("block/" + name(cable))).renderType("solid");
    }

    /** Flat item model referencing {@code item/{name}}. */
    private void flatItem(Item item) {
        itemModels().basicItem(item);
    }

    /** Flat item model using the block's texture (matches modern {@code blockGeneratedItem}). */
    private void blockGeneratedItem(Block block) {
        itemModels().withExistingParent(name(block), "item/generated")
                .texture("layer0", blockTex(block));
    }

    /** Item model using an existing or generated block model as its parent. */
    private void blockItem(Block block) {
        blockItem(block, name(block));
    }

    /** Item model using a specific block model, useful for source blockstates. */
    private void blockItem(Block block, String modelName) {
        itemModels().withExistingParent(name(block), modLoc("block/" + modelName)).renderType("soild");
    }
}
//? }
