package com.github.starcatcher21.stargazer.datagen.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.worldgen.features.amertylst.AmertylstConfig;
import com.github.starcatcher21.starlib.worldgen.features.trees.TreeConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
//? if >= 26.1.2 {
import net.minecraft.util.random.WeightedList;
//? } else {
/*import net.minecraft.util.random.SimpleWeightedRandomList;
*///? }
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.List;

public class ConfiguredFeaturesProvider {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GEM_ORE = key("gem_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FUZZY_TREES = key("fuzzy_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_TRAP = key("star_trap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRICY = key("petricy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBRELLA_TREES = key("umbrella_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRON_ORE = key("iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_ORE = key("crystal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YERI_TREES = key("yeri_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_NEBULA_TREES = key("yellow_nebula_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TRUNN_TREES = key("trunn_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_MOON_GRASS = key("tall_moon_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_TREES = key("star_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_FLOWERS = key("star_flowers");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRO_TREES = key("spiro_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHORT_MOON_GRASS = key("short_moon_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_NEBULA_TREES = key("red_nebula_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_SHROOM = key("purple_shroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_NEBULA_TREES = key("purple_nebula_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRISMATIC_ORE = key("prismatic_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEBULA_TREES = key("nebula_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_TREES = key("moon_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_GRASS = key("moon_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_ROCKS = key("green_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRADI = key("gradi");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLASS_TREES = key("glass_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FULL_MOON_TREES = key("full_moon_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FORGET_ME_NOW = key("forget_me_now");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EYES = key("eyes");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EYE_GRASS = key("eye_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EYE_BIRCH_TREES = key("eye_birch_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARKNESS_TREES = key("darkness_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_GRASS = key("dark_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CURVE_TREES = key("curve_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESS_TREES = key("chess_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUBBLES = key("bubbles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONEFLOWERS = key("boneflowers");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_TREES = key("bone_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_NEBULA_TREES = key("blue_nebula_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_GRASS = key("blue_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_PILLARS = key("blackstone_pillars");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_SPIKE_BIG = key("amethyst_spike_big");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_SPIKE_0 = key("amethyst_spike0");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_SPIKE = key("amethyst_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_CLUSTERS = key("amethyst_clusters");

    private static final List<BlockState> AMETHYST_MAIN_BLOCKS = List.of(
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.AMETHYST_BLOCK.defaultBlockState(),
            Blocks.BUDDING_AMETHYST.defaultBlockState()
    );

    private static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name));
    }

    private static Feature<?> feature(String name) {
        return BuiltInRegistries.FEATURE.get(id(name))
                //? if >= 26.1.2 {
                .get().value()
                //? }
                ;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                 ResourceKey<ConfiguredFeature<?, ?>> key,
                                 Feature feature,
                                 FeatureConfiguration config) {
        context.register(key, new ConfiguredFeature(feature, config));
    }

    private static void simpleBlock(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                    ResourceKey<ConfiguredFeature<?, ?>> key,
                                    BlockState state) {
        context.register(key, new ConfiguredFeature<>(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(SimpleStateProvider.simple(state))
        ));
    }

    private static void weightedBlock(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                      ResourceKey<ConfiguredFeature<?, ?>> key,
                                      BlockState... states) {
        //? if < 26.1.2 {
        /*var builder = SimpleWeightedRandomList.<BlockState>builder();
        for (var state : states) {
            builder.add(state, 1);
        }
        *///? }
        context.register(key, new ConfiguredFeature<>(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(
                        //? if >= 26.1.2 {
                        WeightedList.of(states)
                        //? } else {
                        /*builder.build()
                        *///? }
                ))
        ));
    }

    private static void noneFeature(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                    ResourceKey<ConfiguredFeature<?, ?>> key,
                                    String featureName) {
        register(context, key, feature(featureName), NoneFeatureConfiguration.INSTANCE);
    }

    private static void spikes(BootstrapContext<ConfiguredFeature<?, ?>> context,
                               ResourceKey<ConfiguredFeature<?, ?>> key,
                               int minHeight, int maxHeight,
                               List<BlockState> stoneBlocks,
                               List<BlockState> mainBlocks) {
        register(context, key, feature("spikes"),
                new AmertylstConfig(minHeight, maxHeight, stoneBlocks, mainBlocks));
    }

    private static void registerCustomGrowOn(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                             ResourceKey<ConfiguredFeature<?, ?>> key,
                                             String featureName,
                                             boolean blacklist,
                                             List<String> names,
                                             List<BlockState> growOn) {
        register(context, key, feature(featureName), new TreeConfig(blacklist, names, growOn));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        registerCustomGrowOn(context, GEM_ORE, "gem_ore", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, FUZZY_TREES, "fuzzy_trees", true, List.of(), List.of(MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState()));

        weightedBlock(context, STAR_TRAP, MoonBlocks.STAR_TRAP.get().defaultBlockState());
        simpleBlock(context, PETRICY, RedOrbBlocks.PETRICY.get().defaultBlockState());

        registerCustomGrowOn(context, UMBRELLA_TREES, "umbrella_trees", true, List.of(), List.of(Wander.BORIL.get().defaultBlockState()));
        registerCustomGrowOn(context, IRON_ORE, "iron_ore", true, List.of(), List.of(MoonBlocks.MOON_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, CRYSTAL_ORE, "crystal_ore", true, List.of(), List.of(MoonBlocks.MOON_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, YERI_TREES, "yeri_trees", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, YELLOW_NEBULA_TREES, "nebula_trees", false, List.of("yellow"), List.of(Blocks.AIR.defaultBlockState()));
        registerCustomGrowOn(context, TRUNN_TREES, "trunn_trees", true, List.of(), List.of(Wander.PUROIL.get().defaultBlockState(), Wander.BORIL.get().defaultBlockState()));

        simpleBlock(context, TALL_MOON_GRASS, MoonBlocks.TALL_MOON_GRASS.get().defaultBlockState());

        registerCustomGrowOn(context, STAR_TREES, "star_trees", true, List.of(), List.of(MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState()));

        weightedBlock(context, STAR_FLOWERS,
                MoonBlocks.MOON_GRASS.get().defaultBlockState(),
                StarBlocks.STAR_FLOWER.get().defaultBlockState(),
                MoonBlocks.SPRUNGUS.get().defaultBlockState());

        registerCustomGrowOn(context, SPIRO_TREES, "spiro_trees", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));

        simpleBlock(context, SHORT_MOON_GRASS, MoonBlocks.MOON_GRASS.get().defaultBlockState());

        registerCustomGrowOn(context, RED_NEBULA_TREES, "nebula_trees", false, List.of("red"), List.of(Blocks.AIR.defaultBlockState()));

        registerCustomGrowOn(context, PURPLE_SHROOM, "purple_shroom", true, List.of(), List.of(
                MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState(),
                MoonBlocks.MOON_ROCK.get().defaultBlockState()));

        registerCustomGrowOn(context, PURPLE_NEBULA_TREES, "nebula_trees", false, List.of("purple"), List.of(Blocks.AIR.defaultBlockState()));
        registerCustomGrowOn(context, PRISMATIC_ORE, "prismatic_ore", true, List.of(), List.of(MoonBlocks.MOON_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, NEBULA_TREES, "nebula_trees", true, List.of(), List.of(Blocks.AIR.defaultBlockState()));
        registerCustomGrowOn(context, MOON_TREES, "moon_trees", true, List.of(), List.of(MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState()));

        weightedBlock(context, MOON_GRASS,
                MoonBlocks.MOON_GRASS.get().defaultBlockState(),
                MoonBlocks.MOON_FERN.get().defaultBlockState());

        registerCustomGrowOn(context, GREEN_ROCKS, "green_rocks", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));

        noneFeature(context, GRADI, "gradi");

        registerCustomGrowOn(context, GLASS_TREES, "glass_trees", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));
        registerCustomGrowOn(context, FULL_MOON_TREES, "full_moon_trees", true, List.of(), List.of(MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState()));

        noneFeature(context, FORGET_ME_NOW, "forget_me_now");
        noneFeature(context, EYES, "eyes");

        weightedBlock(context, EYE_GRASS,
                MoonBlocks.MOON_GRASS.get().defaultBlockState(),
                EyeBloodBlocks.EYE_FERN.get().defaultBlockState(),
                MoonBlocks.TALL_MOON_GRASS.get().defaultBlockState(),
                EyeBloodBlocks.EYE_FERN.get().defaultBlockState());

        registerCustomGrowOn(context, EYE_BIRCH_TREES, "eye_birch_trees", true, List.of(), List.of(
                MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState(),
                Blocks.GRASS_BLOCK.defaultBlockState()));

        registerCustomGrowOn(context, DARKNESS_TREES, "darkness_trees", true, List.of(), List.of(Darkness.DYLIUM.get().defaultBlockState()));

        weightedBlock(context, DARK_GRASS,
                MoonBlocks.MOON_GRASS.get().defaultBlockState(),
                Darkness.ROSE_OF_PAIN.get().defaultBlockState());

        registerCustomGrowOn(context, CURVE_TREES, "curve_trees", true, List.of(), List.of(MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState()));

        registerCustomGrowOn(context, CHESS_TREES, "chess_trees", true, List.of(), List.of(
                Chess.WHITE_BRICKS.get().defaultBlockState(),
                Chess.BLACK_CHESSBOARD.get().defaultBlockState()));

        registerCustomGrowOn(context, BUBBLES, "bubbles", true, List.of(), List.of(Blocks.AIR.defaultBlockState()));

        weightedBlock(context, BONEFLOWERS,
                MoonBlocks.MOON_GRASS.get().defaultBlockState(),
                ModBlock.BONEFLOWER.get().defaultBlockState());

        registerCustomGrowOn(context, BONE_TREES, "bone_trees", true, List.of(), List.of(
                MoonBlocks.MOON_ROCK_NYLIUM.get().defaultBlockState(),
                MoonBlocks.MOON_ROCK.get().defaultBlockState()));

        registerCustomGrowOn(context, BLUE_NEBULA_TREES, "nebula_trees", false, List.of("blue"), List.of(Blocks.AIR.defaultBlockState()));

        weightedBlock(context, BLUE_GRASS,
                RedOrbBlocks.BLUE_GRASS.get().defaultBlockState(),
                RedOrbBlocks.POINTY.get().defaultBlockState());

        registerCustomGrowOn(context, BLACKSTONE_PILLARS, "blackstone_pillars", true, List.of(), List.of(RedOrbBlocks.RED_ROCK.get().defaultBlockState()));

        spikes(context, AMETHYST_SPIKE_BIG, 0, 10,
                List.of(ModBlock.INFESTED_CALCITE.get().defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.CALCITE.defaultBlockState()),
                AMETHYST_MAIN_BLOCKS);
        spikes(context, AMETHYST_SPIKE_0, 0, 7,
                List.of(ModBlock.INFESTED_CALCITE.get().defaultBlockState(), Blocks.CALCITE.defaultBlockState()),
                AMETHYST_MAIN_BLOCKS);
        spikes(context, AMETHYST_SPIKE, 1, 7,
                List.of(Blocks.AMETHYST_BLOCK.defaultBlockState(), ModBlock.INFESTED_CALCITE.get().defaultBlockState(), Blocks.CALCITE.defaultBlockState()),
                AMETHYST_MAIN_BLOCKS);

        simpleBlock(context, AMETHYST_CLUSTERS, Blocks.AMETHYST_CLUSTER.defaultBlockState());
    }

    public static void init() {}
}
