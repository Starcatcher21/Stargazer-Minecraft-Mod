package com.github.starcatcher21.stargazer.datagen.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.Darkness;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.block.register.Wander;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
//? if >= 26.1.2 {
import net.minecraft.util.random.WeightedList;
//? } else {
/*import net.minecraft.util.random.SimpleWeightedRandomList;
*///? }
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;

public class PlacedFeaturesProvider {

    public static final ResourceKey<PlacedFeature> GEM_ORE = key("gem_ore");
    public static final ResourceKey<PlacedFeature> PETRICY = key("petricy");
    public static final ResourceKey<PlacedFeature> PETRICY_TOP = key("petricy_top");
    public static final ResourceKey<PlacedFeature> UMBRELLA_TREES = key("umbrella_trees");
    public static final ResourceKey<PlacedFeature> UMBRELLA_TREES_TOP = key("umbrella_trees_top");
    public static final ResourceKey<PlacedFeature> IRON_ORE = key("iron_ore");
    public static final ResourceKey<PlacedFeature> CRYSTAL_ORE = key("crystal_ore");
    public static final ResourceKey<PlacedFeature> PRISMATIC_ORE = key("prismatic_ore");
    public static final ResourceKey<PlacedFeature> YERI_TREES = key("yeri_trees");
    public static final ResourceKey<PlacedFeature> YERI_TREES_TOP = key("yeri_trees_top");
    public static final ResourceKey<PlacedFeature> TRUNN_TREES = key("trunn_trees");
    public static final ResourceKey<PlacedFeature> TALL_MOON_GRASS = key("tall_moon_grass");
    public static final ResourceKey<PlacedFeature> TALL_MOON_GRASS_TOP = key("tall_moon_grass_top");
    public static final ResourceKey<PlacedFeature> SHORT_MOON_GRASS = key("short_moon_grass");
    public static final ResourceKey<PlacedFeature> SHORT_MOON_GRASS_TOP = key("short_moon_grass_top");
    public static final ResourceKey<PlacedFeature> STAR_TREES = key("star_trees");
    public static final ResourceKey<PlacedFeature> STAR_FLOWERS = key("star_flowers");
    public static final ResourceKey<PlacedFeature> STAR_FLOWERS_TOP = key("star_flowers_top");
    public static final ResourceKey<PlacedFeature> STAR_TRAP = key("star_trap");
    public static final ResourceKey<PlacedFeature> STAR_TRAP_TOP = key("star_trap_top");
    public static final ResourceKey<PlacedFeature> SPIRO_TREES = key("spiro_trees");
    public static final ResourceKey<PlacedFeature> SPIRO_TREES_TOP = key("spiro_trees_top");
    public static final ResourceKey<PlacedFeature> PURPLE_SHROOM = key("purple_shroom");
    public static final ResourceKey<PlacedFeature> PURPLE_SHROOM_TOP = key("purple_shroom_top");
    public static final ResourceKey<PlacedFeature> PURPLE_SHROOM_RARE = key("purple_shroom_rare");
    public static final ResourceKey<PlacedFeature> PURPLE_SHROOM_RARE_TOP = key("purple_shroom_rare_top");
    public static final ResourceKey<PlacedFeature> NEBULA_TREES = key("nebula_trees");
    public static final ResourceKey<PlacedFeature> MOON_TREES = key("moon_trees");
    public static final ResourceKey<PlacedFeature> MOON_TREES_TOP = key("moon_trees_top");
    public static final ResourceKey<PlacedFeature> MOON_GRASS = key("moon_grass");
    public static final ResourceKey<PlacedFeature> MOON_GRASS_TOP = key("moon_grass_top");
    public static final ResourceKey<PlacedFeature> MOON_GRASS_BONE = key("moon_grass_bone");
    public static final ResourceKey<PlacedFeature> GREEN_ROCKS = key("green_rocks");
    public static final ResourceKey<PlacedFeature> GREEN_ROCKS_TOP = key("green_rocks_top");
    public static final ResourceKey<PlacedFeature> GRADI = key("gradi");
    public static final ResourceKey<PlacedFeature> GLASS_TREES = key("glass_trees");
    public static final ResourceKey<PlacedFeature> GLASS_TREES_TOP = key("glass_trees_top");
    public static final ResourceKey<PlacedFeature> FORGET_ME_NOW = key("forget_me_now");
    public static final ResourceKey<PlacedFeature> EYES = key("eyes");
    public static final ResourceKey<PlacedFeature> EYE_GRASS = key("eye_grass");
    public static final ResourceKey<PlacedFeature> EYE_GRASS_TOP = key("eye_grass_top");
    public static final ResourceKey<PlacedFeature> EYE_BIRCH_TREES = key("eye_birch_trees");
    public static final ResourceKey<PlacedFeature> EYE_BIRCH_TREES_TOP = key("eye_birch_trees_top");
    public static final ResourceKey<PlacedFeature> DARKNESS_TREES = key("darkness_trees");
    public static final ResourceKey<PlacedFeature> DARKNESS_TREES_TOP = key("darkness_trees_top");
    public static final ResourceKey<PlacedFeature> DARK_GRASS = key("dark_grass");
    public static final ResourceKey<PlacedFeature> DARK_GRASS_TOP = key("dark_grass_top");
    public static final ResourceKey<PlacedFeature> CURVE_TREES = key("curve_trees");
    public static final ResourceKey<PlacedFeature> CURVE_TREES_TOP = key("curve_trees_top");
    public static final ResourceKey<PlacedFeature> CHESS_TREES = key("chess_trees");
    public static final ResourceKey<PlacedFeature> BUBBLES = key("bubbles");

    // --- batch 3 ---
    public static final ResourceKey<PlacedFeature> BONEFLOWERS = key("boneflowers");
    public static final ResourceKey<PlacedFeature> BONEFLOWERS_TOP = key("boneflowers_top");
    public static final ResourceKey<PlacedFeature> BONE_TREES = key("bone_trees");
    public static final ResourceKey<PlacedFeature> BONE_TREES_TOP = key("bone_trees_top");
    public static final ResourceKey<PlacedFeature> BLUE_GRASS = key("blue_grass");
    public static final ResourceKey<PlacedFeature> BLUE_GRASS_TOP = key("blue_grass_top");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_PILLARS = key("blackstone_pillars");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_PILLARS_TOP = key("blackstone_pillars_top");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE = key("amethyst_spike");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE_TOP = key("amethyst_spike_top");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE_BOTTOM = key("amethyst_spike_bottom");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE0 = key("amethyst_spike0");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE0_TOP = key("amethyst_spike0_top");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE0_BOTTOM = key("amethyst_spike0_bottom");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE_BIG = key("amethyst_spike_big");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE_BIG_TOP = key("amethyst_spike_big_top");
    public static final ResourceKey<PlacedFeature> AMETHYST_SPIKE_BIG_BOTTOM = key("amethyst_spike_big_bottom");
    public static final ResourceKey<PlacedFeature> AMETHYST_CLUSTERS = key("amethyst_clusters");
    public static final ResourceKey<PlacedFeature> AMETHYST_CLUSTERS_TOP = key("amethyst_clusters_top");
    public static final ResourceKey<PlacedFeature> AMETHYST_CLUSTERS_BOTTOM = key("amethyst_clusters_bottom");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }

    private static void place(BootstrapContext<PlacedFeature> context,
                              ResourceKey<PlacedFeature> key,
                              ResourceKey<ConfiguredFeature<?, ?>> config,
                              List<PlacementModifier> mods) {
        context.register(key, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(config), mods));
    }

    /** Registers both the {@code name} and {@code name_top} placed features from one call. */
    private static void pair(BootstrapContext<PlacedFeature> context, String name,
                             ResourceKey<ConfiguredFeature<?, ?>> config,
                             List<PlacementModifier> top, List<PlacementModifier> regular) {
        place(context, key(name + "_top"), config, top);
        place(context, key(name), config, regular);
    }

    /** Registers {@code name_top}, {@code name_bottom} and {@code name} from one call. */
    private static void triple(BootstrapContext<PlacedFeature> context, String name,
                               ResourceKey<ConfiguredFeature<?, ?>> config,
                               List<PlacementModifier> top, List<PlacementModifier> bottom,
                               List<PlacementModifier> regular) {
        place(context, key(name + "_top"), config, top);
        place(context, key(name + "_bottom"), config, bottom);
        place(context, key(name), config, regular);
    }

    /** count → in_square → WORLD_SURFACE → trapezoid range → [filter] → [biome]. */
    private static List<PlacementModifier> trap(int count, int min, int max, int plateau,
                                                BlockPredicateFilter filter, boolean biome) {
        return trap(count, min, max, plateau, false, Heightmap.Types.WORLD_SURFACE, filter, biome);
    }

    /** Same, but with the square-first ordering (used by many ground-cover features). */
    private static List<PlacementModifier> trap(int count, int min, int max, int plateau,
                                                boolean squareFirst,
                                                BlockPredicateFilter filter, boolean biome) {
        return trap(count, min, max, plateau, squareFirst, Heightmap.Types.WORLD_SURFACE, filter, biome);
    }

    /** Full form: allows overriding the heightmap type (needed for {@code amethyst_spike0}). */
    private static List<PlacementModifier> trap(int count, int min, int max, int plateau,
                                                boolean squareFirst, Heightmap.Types hm,
                                                BlockPredicateFilter filter, boolean biome) {
        List<PlacementModifier> mods = new ArrayList<>(6);
        if (squareFirst) mods.add(InSquarePlacement.spread());
        mods.add(CountPlacement.of(count));
        if (!squareFirst) mods.add(InSquarePlacement.spread());
        mods.add(HeightmapPlacement.onHeightmap(hm));
        mods.add(HeightRangePlacement.of(TrapezoidHeight.of(
                VerticalAnchor.absolute(min), VerticalAnchor.absolute(max), plateau)));
        if (filter != null) mods.add(filter);
        if (biome) mods.add(BiomeFilter.biome());
        return mods;
    }

    /** Same as {@link #trap} but without the {@code height_range} step. */
    private static List<PlacementModifier> trapNoRange(int count, boolean squareFirst,
                                                       BlockPredicateFilter filter, boolean biome) {
        List<PlacementModifier> mods = new ArrayList<>(5);
        if (squareFirst) mods.add(InSquarePlacement.spread());
        mods.add(CountPlacement.of(count));
        if (!squareFirst) mods.add(InSquarePlacement.spread());
        mods.add(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE));
        if (filter != null) mods.add(filter);
        if (biome) mods.add(BiomeFilter.biome());
        return mods;
    }

    /** {@code count_on_every_layer} + biome (blue_grass). */
    private static List<PlacementModifier> everyLayer(int count) {
        return List.of(CountOnEveryLayerPlacement.of(count), BiomeFilter.biome());
    }

    /** {@code block_predicate_filter} matching the block directly underneath. */
    private static BlockPredicateFilter below(Block... blocks) {
        return BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), blocks));
    }

    /** petricy's environment-scan pattern. */
    private static List<PlacementModifier> petricy(int count, HeightRangePlacement height) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                height,
                EnvironmentScanPlacement.scanningFor(Direction.UP,
                        BlockPredicate.solid(),
                        BlockPredicate.matchesTag(BlockTags.AIR), 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                BiomeFilter.biome());
    }

    /** Weighted count → in_square → water-depth 0 → OCEAN_FLOOR → biome (star_trees / nebula_trees). */
    private static List<PlacementModifier> weightedTrees() {
        return List.of(
                CountPlacement.of(new WeightedListInt(
                        //? if >= 26.1.2 {
                        WeightedList.<IntProvider>builder()
                        //? } else {
                        /*SimpleWeightedRandomList.<IntProvider>builder()
                        *///? }
                            .add(ConstantInt.of(10), 9)
                            .add(ConstantInt.of(9), 1)
                            .build()
                        )),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome());
    }

    /** in_square → biome → count → heightmap → height_range → filter (purple_shroom_rare* order). */
    private static List<PlacementModifier> biomeFirstTrap(int count, int min, int max, int plateau,
                                                          BlockPredicateFilter filter) {
        return List.of(
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                CountPlacement.of(count),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                HeightRangePlacement.of(TrapezoidHeight.of(
                        VerticalAnchor.absolute(min), VerticalAnchor.absolute(max), plateau)),
                filter);
    }

    /** heightmap → predicate → rarity(3) — chess_trees has no count/in_square. */
    private static List<PlacementModifier> chessTrees(BlockPredicateFilter filter) {
        return List.of(
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                filter,
                RarityFilter.onAverageOnceEvery(3));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        BlockPredicateFilter belowMoonGrass =
                below(MoonBlocks.MOON_ROCK_NYLIUM.get(), Blocks.GRASS_BLOCK);
        BlockPredicateFilter belowMoon =
                below(MoonBlocks.MOON_ROCK_NYLIUM.get());
        BlockPredicateFilter belowRedRock =
                below(RedOrbBlocks.RED_ROCK.get());
        BlockPredicateFilter belowBoril =
                below(Wander.BORIL.get());
        BlockPredicateFilter belowDylium =
                below(Darkness.DYLIUM.get());
        BlockPredicateFilter belowDyliumMoonGrass =
                below(Darkness.DYLIUM.get(), MoonBlocks.MOON_ROCK_NYLIUM.get(), Blocks.GRASS_BLOCK);
        BlockPredicateFilter belowMoonRock =
                below(MoonBlocks.MOON_ROCK.get());
        BlockPredicateFilter belowMoonRockNyliumGrass =
                below(MoonBlocks.MOON_ROCK.get(), MoonBlocks.MOON_ROCK_NYLIUM.get(), Blocks.GRASS_BLOCK);
        BlockPredicateFilter belowCalcite =
                below(ModBlock.INFESTED_CALCITE.get());
        BlockPredicateFilter belowAmethystCalcite =
                below(Blocks.AMETHYST_BLOCK, ModBlock.INFESTED_CALCITE.get());

        // --- Ores / simple features (no filter, no biome) ---
        place(context, GEM_ORE, ConfiguredFeaturesProvider.GEM_ORE,
                trap(6, 40, 90, 60, null, false));
        place(context, IRON_ORE, ConfiguredFeaturesProvider.IRON_ORE,
                trap(3, 70, 160, 140, null, false));
        place(context, CRYSTAL_ORE, ConfiguredFeaturesProvider.CRYSTAL_ORE,
                trap(2, 30, 130, 130, null, false));
        place(context, PRISMATIC_ORE, ConfiguredFeaturesProvider.PRISMATIC_ORE,
                trap(2, 30, 100, 130, null, false));
        place(context, BUBBLES, ConfiguredFeaturesProvider.BUBBLES,
                trap(8, -64, 200, 130, null, false));

        // --- petricy ---
        place(context, PETRICY_TOP, ConfiguredFeaturesProvider.PETRICY,
                petricy(25, HeightRangePlacement.of(TrapezoidHeight.of(
                        VerticalAnchor.absolute(100), VerticalAnchor.absolute(150), 130))));
        place(context, PETRICY, ConfiguredFeaturesProvider.PETRICY,
                petricy(25, HeightRangePlacement.uniform(
                        VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256))));

        // --- Trees on boril ---
        pair(context, "umbrella_trees", ConfiguredFeaturesProvider.UMBRELLA_TREES,
                trap(256, 100, 200, 130, belowBoril, true),
                trap(256, -64, 200, 130, belowBoril, true));
        place(context, TRUNN_TREES, ConfiguredFeaturesProvider.TRUNN_TREES,
                trap(228, -64, 200, 130, belowBoril, true));

        // --- Trees / pillars on red_rock ---
        pair(context, "yeri_trees", ConfiguredFeaturesProvider.YERI_TREES,
                trap(256, 100, 200, 130, belowRedRock, true),
                trap(228, 30, 100, 130, belowRedRock, true));
        pair(context, "spiro_trees", ConfiguredFeaturesProvider.SPIRO_TREES,
                trap(256, 100, 200, 130, belowRedRock, true),
                trap(228, 30, 100, 130, belowRedRock, true));
        pair(context, "glass_trees", ConfiguredFeaturesProvider.GLASS_TREES,
                trap(256, 100, 200, 130, belowRedRock, true),
                trap(228, 30, 100, 130, belowRedRock, true));
        pair(context, "green_rocks", ConfiguredFeaturesProvider.GREEN_ROCKS,
                trap(228, 100, 250, 130, belowRedRock, true),
                trap(228, 30, 100, 130, belowRedRock, true));
        pair(context, "blackstone_pillars", ConfiguredFeaturesProvider.BLACKSTONE_PILLARS,
                trap(256, 100, 200, 130, belowRedRock, true),
                trap(228, 30, 100, 130, belowRedRock, true));

        // --- Trees / shroom on moon_rock_nylium ---
        pair(context, "moon_trees", ConfiguredFeaturesProvider.MOON_TREES,
                trap(256, 100, 150, 130, belowMoon, true),
                trap(228, 30, 100, 130, belowMoon, true));
        pair(context, "eye_birch_trees", ConfiguredFeaturesProvider.EYE_BIRCH_TREES,
                trap(228, 100, 150, 130, belowMoon, true),
                trap(228, 30, 100, 130, belowMoon, true));
        pair(context, "curve_trees", ConfiguredFeaturesProvider.CURVE_TREES,
                trap(228, 100, 150, 130, belowMoon, true),
                trap(228, 30, 100, 130, belowMoon, true));
        pair(context, "purple_shroom", ConfiguredFeaturesProvider.PURPLE_SHROOM,
                trap(180, 100, 150, 130, belowMoon, true),
                trap(180, 30, 100, 130, belowMoon, true));

        // --- Trees on moon_rock ---
        pair(context, "bone_trees", ConfiguredFeaturesProvider.BONE_TREES,
                trap(156, 100, 150, 130, belowMoonRock, true),
                trap(156, 30, 100, 130, belowMoonRock, true));

        // --- Trees on dylium ---
        pair(context, "darkness_trees", ConfiguredFeaturesProvider.DARKNESS_TREES,
                trap(256, 100, 150, 130, belowDylium, true),
                trap(228, 30, 100, 130, belowDylium, true));

        // --- square-first ground cover on moon_rock_nylium + grass (no biome) ---
        pair(context, "star_flowers", ConfiguredFeaturesProvider.STAR_FLOWERS,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));
        pair(context, "tall_moon_grass", ConfiguredFeaturesProvider.TALL_MOON_GRASS,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));
        pair(context, "star_trap", ConfiguredFeaturesProvider.STAR_TRAP,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));
        pair(context, "short_moon_grass", ConfiguredFeaturesProvider.SHORT_MOON_GRASS,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));
        pair(context, "moon_grass", ConfiguredFeaturesProvider.MOON_GRASS,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));
        pair(context, "eye_grass", ConfiguredFeaturesProvider.EYE_GRASS,
                trap(180, 100, 150, 130, true, belowMoonGrass, false),
                trap(180, 30, 100, 130, true, belowMoonGrass, false));

        // --- square-first ground cover on moon_rock | moon_rock_nylium | grass (no biome) ---
        pair(context, "boneflowers", ConfiguredFeaturesProvider.BONEFLOWERS,
                trapNoRange(120, true, belowMoonRockNyliumGrass, false),
                trapNoRange(180, true, belowMoonRockNyliumGrass, false));

        // --- square-first ground cover on dylium + moon_rock_nylium + grass (no biome) ---
        pair(context, "dark_grass", ConfiguredFeaturesProvider.DARK_GRASS,
                trap(180, 100, 150, 130, true, belowDyliumMoonGrass, false),
                trap(180, 30, 100, 130, true, belowDyliumMoonGrass, false));

        // --- moon_grass_bone: no height range, no biome, square-first ---
        place(context, MOON_GRASS_BONE, ConfiguredFeaturesProvider.MOON_GRASS,
                trapNoRange(3, true, belowMoonGrass, false));

        // --- no-height-range on mixed surface with biome ---
        place(context, GRADI, ConfiguredFeaturesProvider.GRADI,
                trapNoRange(4, false, belowDyliumMoonGrass, true));
        place(context, FORGET_ME_NOW, ConfiguredFeaturesProvider.FORGET_ME_NOW,
                trapNoRange(4, false, belowMoonGrass, true));
        place(context, EYES, ConfiguredFeaturesProvider.EYES,
                trapNoRange(4, false, belowMoonGrass, true));

        // --- purple_shroom_rare: biome-before-count ordering from JSON ---
        place(context, PURPLE_SHROOM_RARE_TOP, ConfiguredFeaturesProvider.PURPLE_SHROOM,
                biomeFirstTrap(30, 100, 150, 130, belowMoon));
        place(context, PURPLE_SHROOM_RARE, ConfiguredFeaturesProvider.PURPLE_SHROOM,
                biomeFirstTrap(30, 30, 100, 130, belowMoon));

        // --- weighted-count trees (OCEAN_FLOOR heightmap) ---
        place(context, STAR_TREES, ConfiguredFeaturesProvider.STAR_TREES, weightedTrees());
        place(context, NEBULA_TREES, ConfiguredFeaturesProvider.NEBULA_TREES, weightedTrees());

        // --- chess_trees: heightmap + predicate + rarity ---
        place(context, CHESS_TREES, ConfiguredFeaturesProvider.CHESS_TREES,
                chessTrees(below(Chess.BLACK_CHESSBOARD.get(), Chess.WHITE_CHESSBOARD.get())));

        // --- blue_grass: count_on_every_layer + biome (top and regular identical) ---
        List<PlacementModifier> blueGrass = everyLayer(8);
        pair(context, "blue_grass", ConfiguredFeaturesProvider.BLUE_GRASS, blueGrass, blueGrass);

        // --- Amethyst spikes (top / bottom / regular, square-first, no biome) ---
        triple(context, "amethyst_spike", ConfiguredFeaturesProvider.AMETHYST_SPIKE,
                trap(60, 100, 150, 130, true, belowCalcite, false),
                trap(60, 0, 30, 130, true, belowCalcite, false),
                trap(60, 30, 100, 130, true, belowCalcite, false));

        triple(context, "amethyst_spike0", ConfiguredFeaturesProvider.AMETHYST_SPIKE_0,
                trap(60, 100, 150, 130, true, belowCalcite, false),
                trap(60, 0, 30, 130, true, belowCalcite, false),
                trap(60, 30, 100, 130, true, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, belowCalcite, false));

        triple(context, "amethyst_spike_big", ConfiguredFeaturesProvider.AMETHYST_SPIKE_BIG,
                trap(132, 100, 150, 130, true, belowCalcite, false),
                trap(132, 0, 30, 130, true, belowCalcite, false),
                trap(132, 30, 100, 130, true, belowCalcite, false));

        // --- Amethyst clusters (on amethyst_block | infested_calcite, square-first, no biome) ---
        triple(context, "amethyst_clusters", ConfiguredFeaturesProvider.AMETHYST_CLUSTERS,
                trap(180, 100, 150, 130, true, belowAmethystCalcite, false),
                trap(180, 0, 30, 130, true, belowAmethystCalcite, false),
                trap(180, 30, 100, 130, true, belowAmethystCalcite, false));
    }
    public static void init() {}
}
