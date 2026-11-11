package com.github.starcatcher21.stargazer.datagen.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
//? if >= 26.1.2 {
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.attribute.*;
//? }
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BiomesProvider {

    // --- Registry Keys ---
    public static final ResourceKey<Biome> YERI_FOREST = key("yeri_forest");
    public static final ResourceKey<Biome> SPIRO_FOREST = key("spiro_forest");
    public static final ResourceKey<Biome> REDNESS = key("redness");
    public static final ResourceKey<Biome> GREEN_ROCK_VALLEY = key("green_rock_valley");
    public static final ResourceKey<Biome> GLASS_FOREST = key("glass_forest");
    public static final ResourceKey<Biome> BLACKSTONE_PILLARS = key("blackstone_pillars");
    public static final ResourceKey<Biome> TRUNN_FOREST = key("trunn_forest");
    public static final ResourceKey<Biome> AMETHYST_SPIKES = key("amethyst_spikes");
    public static final ResourceKey<Biome> BONE_FOREST = key("bone_forest");
    public static final ResourceKey<Biome> BUBBLE_FIELD = key("bubble_field");
    public static final ResourceKey<Biome> EYE_BIRCH_FOREST = key("eye_birch_forest");
    public static final ResourceKey<Biome> MOON_FIELD = key("moon_field");
    public static final ResourceKey<Biome> MOON_FOREST = key("moon_forest");
    public static final ResourceKey<Biome> MOON_VOID = key("moon_void");
    public static final ResourceKey<Biome> MOON_WASTE = key("moon_waste");
    public static final ResourceKey<Biome> PURPLE_SHROOM_FOREST = key("purple_shroom_forest");
    public static final ResourceKey<Biome> BLACK = key("black");
    public static final ResourceKey<Biome> WHITE = key("white");
    public static final ResourceKey<Biome> UMBRELLA_FOREST = key("umbrella_forest");
    public static final ResourceKey<Biome> DARKNESS_FOREST = key("darkness_forest");

    // --- color palette ---
    private static final int WATER = 10076657;
    private static final int FOG = 10076657;
    private static final int SKY = 10076657;
    private static final int GRASS = 14453469;
    private static final int FOLIAGE = 12607947;

    // --- background music ---
    private static final Music COSMIC = new Music(
            Holder.direct(SoundEvent.createVariableRangeEvent(
                    Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cosmic.music"))),
            0, 0, false);

    // --- shared vegetation templates ---
    private static final List<ResourceKey<PlacedFeature>> BLUE_GRASS_PETRICY = List.of(
            PlacedFeaturesProvider.BLUE_GRASS, PlacedFeaturesProvider.BLUE_GRASS_TOP,
            PlacedFeaturesProvider.PETRICY, PlacedFeaturesProvider.PETRICY_TOP);

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }

    // --- effect presets ---

    /** fog=0 / sky=0 / water=10076657 / water_fog=329011 (most common). */
    private static BiomeSpecialEffects dark() {
        return new BiomeSpecialEffects.Builder()
                //? if < 26.1.2 {
                /*.fogColor(FOG)
                .waterFogColor(FOG)
                .skyColor(SKY)
                *///? }
                .waterColor(WATER)
                .build();
    }

    /** sky=6184036 / fog=589824 / water/water_fog=10076657/589824 + grass/foliage. */
    private static BiomeSpecialEffects amethyst() {
        return new BiomeSpecialEffects.Builder()
                //? if < 26.1.2 {
                /*.fogColor(FOG)
                .waterFogColor(FOG)
                .skyColor(SKY)
                *///? }
                .waterColor(WATER)
                .grassColorOverride(GRASS).foliageColorOverride(FOLIAGE)
                .build();
    }

    private static BiomeSpecialEffects flat() {
        return new BiomeSpecialEffects.Builder()
                //? if < 26.1.2 {
                /*.fogColor(FOG)
                .waterFogColor(FOG)
                .skyColor(SKY)
                *///? }
                .waterColor(WATER)
                .grassColorOverride(GRASS).foliageColorOverride(FOLIAGE)
                .build();
    }

    private static BiomeSpecialEffects vanilla() {
        return new BiomeSpecialEffects.Builder()
                //? if < 26.1.2 {
                /*.fogColor(FOG)
                .waterFogColor(FOG)
                .skyColor(SKY)
                *///? }
                .waterColor(4159204)
                .build();
    }

    // --- generation helper ---

    private static BiomeGenerationSettings gen(BootstrapContext<Biome> ctx,
                                                List<ResourceKey<PlacedFeature>> vegetation) {
        HolderGetter<PlacedFeature> pf = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);
        BiomeGenerationSettings.Builder b = new BiomeGenerationSettings.Builder(pf, carvers);
        for (ResourceKey<PlacedFeature> f : vegetation) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, f);
        }
        return b.build();
    }

    // --- spawner helper ---

    private record Spawn(EntityType<?> type, int weight, int min, int max) {}

    private static MobSpawnSettings spawners(Spawn... data) {
        MobSpawnSettings.Builder b = new MobSpawnSettings.Builder();
        for (Spawn s : data) {
            b.addSpawn(s.type.getCategory(),
                    //? if >= 26.1.2 {
                    s.weight,
                    //? }
                    new MobSpawnSettings.SpawnerData(s.type,
                            //? if < 26.1.2 {
                            /*s.weight,
                            *///? }
                            s.min, s.max
                    ));
        }
        return b.build();
    }

    // --- biome factory ---

    private static Biome biome(BiomeSpecialEffects effects, float temp, float downfall,
                                BiomeGenerationSettings gen, MobSpawnSettings mobs) {
        return biome(effects, temp, downfall, gen, mobs, Optional.of(COSMIC), 0.1f);
    }

    private static Biome biome(BiomeSpecialEffects effects, float temp, float downfall,
                                BiomeGenerationSettings gen, MobSpawnSettings mobs,
                                Optional<Music> music) {
        return biome(effects, temp, downfall, gen, mobs, music, 0.1f);
    }

    //? if <1.21.5 {
    /*private static Biome biome(BiomeSpecialEffects effects, float temp, float downfall,
                               BiomeGenerationSettings gen, MobSpawnSettings mobs,
                               Optional<Music> music, float creatureSpawnProbability) {
        Biome.BiomeBuilder b = new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .downfall(downfall)
                .specialEffects(effects)
                .generationSettings(gen)
                .mobSpawnSettings(mobs);
        music.ifPresent(m -> {
        });
        return b.build();
    }
    *///?} else {
    private static Biome biome(BiomeSpecialEffects effects, float temp, float downfall,
                               BiomeGenerationSettings gen, MobSpawnSettings mobs,
                               Optional<Music> music, float creatureSpawnProbability) {
        Biome.BiomeBuilder b = new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temp)
                .downfall(downfall)
                .specialEffects(effects)
                .generationSettings(gen)
                .mobSpawnSettings(mobs);
        music.ifPresent(m -> b.setAttribute(
                EnvironmentAttributes.BACKGROUND_MUSIC,
                new BackgroundMusic(
                                new Music(m.sound(), m.minDelay(), m.maxDelay(), m.replaceCurrentMusic())
                )));
        return b.build();
    }
    //?}

    private static List<ResourceKey<PlacedFeature>> treeWithGrass(
            ResourceKey<PlacedFeature> tree, ResourceKey<PlacedFeature> treeTop) {
        List<ResourceKey<PlacedFeature>> r = new ArrayList<>(6);
        r.add(tree);
        r.add(treeTop);
        r.addAll(BLUE_GRASS_PETRICY);
        return r;
    }

    @SafeVarargs
    private static List<ResourceKey<PlacedFeature>> F(ResourceKey<PlacedFeature>... keys) {
        return List.of(keys);
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        EntityType<?> scruby = EntityRegistry.SCRUBY_ENTITY.get();
        EntityType<?> amethystTurtle = EntityRegistry.AMETHYST_TURTLE_ENTITY.get();
        EntityType<?> eyeBat = EntityRegistry.EYE_BAT_ENTITY.get();
        EntityType<?> ghost = EntityRegistry.GHOST_ENTITY.get();
        EntityType<?> blackRook = EntityRegistry.BLACK_ROOK_ENTITY.get();
        EntityType<?> rook = EntityRegistry.ROOK_ENTITY.get();
        EntityType<?> blackFox = EntityRegistry.BLACK_FOX_ENTITY.get();

        context.register(YERI_FOREST, biome(
                dark(), 0.8f, 0.4f,
                gen(context, treeWithGrass(
                        PlacedFeaturesProvider.YERI_TREES,
                        PlacedFeaturesProvider.YERI_TREES_TOP)),
                spawners(new Spawn(scruby, 1, 5, 10))));

        context.register(SPIRO_FOREST, biome(
                dark(), 0.8f, 0.4f,
                gen(context, treeWithGrass(
                        PlacedFeaturesProvider.SPIRO_TREES,
                        PlacedFeaturesProvider.SPIRO_TREES_TOP)),
                spawners(new Spawn(scruby, 1, 5, 10))));

        context.register(REDNESS, biome(
                dark(), 0.8f, 0.4f,
                gen(context, BLUE_GRASS_PETRICY),
                spawners()));

        context.register(GREEN_ROCK_VALLEY, biome(
                dark(), 0.8f, 0.4f,
                gen(context, treeWithGrass(
                        PlacedFeaturesProvider.GREEN_ROCKS,
                        PlacedFeaturesProvider.GREEN_ROCKS_TOP)),
                spawners()));

        context.register(GLASS_FOREST, biome(
                dark(), 0.8f, 0.4f,
                gen(context, treeWithGrass(
                        PlacedFeaturesProvider.GLASS_TREES,
                        PlacedFeaturesProvider.GLASS_TREES_TOP)),
                spawners()));

        context.register(BLACKSTONE_PILLARS, biome(
                dark(), 0.8f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.BLACKSTONE_PILLARS,
                        PlacedFeaturesProvider.BLACKSTONE_PILLARS_TOP,
                        PlacedFeaturesProvider.PETRICY,
                        PlacedFeaturesProvider.PETRICY_TOP)),
                spawners(new Spawn(scruby, 1, 1, 9))));

        // ===================== flat() biomes (no lava springs) =====================
        context.register(TRUNN_FOREST, biome(
                flat(), 0.7f, 0.4f,
                gen(context, F(PlacedFeaturesProvider.TRUNN_TREES)),
                spawners()));

        context.register(BUBBLE_FIELD, biome(
                flat(), 0.7f, 0.4f,
                gen(context, F(PlacedFeaturesProvider.BUBBLES)),
                spawners()));

        context.register(UMBRELLA_FOREST, biome(
                flat(), 0.4f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.UMBRELLA_TREES,
                        PlacedFeaturesProvider.UMBRELLA_TREES_TOP)),
                spawners()));

        // ===================== amethyst() biomes =====================
        context.register(AMETHYST_SPIKES, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.AMETHYST_CLUSTERS,
                        PlacedFeaturesProvider.AMETHYST_CLUSTERS_TOP,
                        PlacedFeaturesProvider.AMETHYST_CLUSTERS_BOTTOM,
                        PlacedFeaturesProvider.AMETHYST_SPIKE,
                        PlacedFeaturesProvider.AMETHYST_SPIKE_TOP,
                        PlacedFeaturesProvider.AMETHYST_SPIKE_BOTTOM,
                        PlacedFeaturesProvider.AMETHYST_SPIKE_BIG,
                        PlacedFeaturesProvider.AMETHYST_SPIKE_BIG_TOP,
                        PlacedFeaturesProvider.AMETHYST_SPIKE_BIG_BOTTOM)),
                spawners(new Spawn(amethystTurtle, 1, 4, 6))));

        context.register(BONE_FOREST, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.BONE_TREES,
                        PlacedFeaturesProvider.BONE_TREES_TOP,
                        PlacedFeaturesProvider.BONEFLOWERS,
                        PlacedFeaturesProvider.BONEFLOWERS_TOP)),
                spawners(new Spawn(
                        //? if >= 26.1.2 {
                        EntityTypes.SKELETON
                        //? } else {
                        /*EntityType.SKELETON
                        *///? }
                        , 50, 1, 2))));

        context.register(EYE_BIRCH_FOREST, biome(
                amethyst(), 0.6f, 0.6f,
                gen(context, F(
                        PlacedFeaturesProvider.EYE_BIRCH_TREES,
                        PlacedFeaturesProvider.EYE_BIRCH_TREES_TOP,
                        PlacedFeaturesProvider.STAR_FLOWERS,
                        PlacedFeaturesProvider.STAR_FLOWERS_TOP,
                        PlacedFeaturesProvider.EYE_GRASS,
                        PlacedFeaturesProvider.EYE_GRASS_TOP,
                        PlacedFeaturesProvider.EYES)),
                spawners(new Spawn(eyeBat, 1, 5, 10))));

        context.register(MOON_FIELD, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.STAR_FLOWERS,
                        PlacedFeaturesProvider.STAR_FLOWERS_TOP,
                        PlacedFeaturesProvider.MOON_GRASS,
                        PlacedFeaturesProvider.MOON_GRASS_TOP,
                        PlacedFeaturesProvider.STAR_TRAP,
                        PlacedFeaturesProvider.STAR_TRAP_TOP,
                        PlacedFeaturesProvider.FORGET_ME_NOW)),
                spawners(new Spawn(ghost, 10, 1, 3))));

        context.register(MOON_FOREST, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.MOON_TREES,
                        PlacedFeaturesProvider.MOON_TREES_TOP,
                        PlacedFeaturesProvider.CURVE_TREES,
                        PlacedFeaturesProvider.CURVE_TREES_TOP,
                        PlacedFeaturesProvider.STAR_FLOWERS,
                        PlacedFeaturesProvider.STAR_FLOWERS_TOP,
                        PlacedFeaturesProvider.PURPLE_SHROOM_RARE,
                        PlacedFeaturesProvider.PURPLE_SHROOM_RARE_TOP,
                        PlacedFeaturesProvider.MOON_GRASS,
                        PlacedFeaturesProvider.MOON_GRASS_TOP,
                        PlacedFeaturesProvider.STAR_TRAP,
                        PlacedFeaturesProvider.STAR_TRAP_TOP,
                        PlacedFeaturesProvider.FORGET_ME_NOW)),
                spawners(new Spawn(ghost, 10, 1, 3))));

        context.register(MOON_VOID, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, List.of()),
                spawners(new Spawn(ghost, 10, 1, 3))));

        context.register(MOON_WASTE, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, List.of()),
                spawners(new Spawn(ghost, 10, 1, 3))));

        context.register(PURPLE_SHROOM_FOREST, biome(
                amethyst(), 0.7f, 0.4f,
                gen(context, F(
                        PlacedFeaturesProvider.PURPLE_SHROOM,
                        PlacedFeaturesProvider.PURPLE_SHROOM_TOP,
                        PlacedFeaturesProvider.MOON_GRASS,
                        PlacedFeaturesProvider.MOON_GRASS_TOP,
                        PlacedFeaturesProvider.STAR_TRAP,
                        PlacedFeaturesProvider.STAR_TRAP_TOP)),
                spawners()));

        context.register(DARKNESS_FOREST, biome(
                amethyst(), -0.9f, 0.5f,
                gen(context, F(
                        PlacedFeaturesProvider.DARKNESS_TREES,
                        PlacedFeaturesProvider.DARKNESS_TREES_TOP,
                        PlacedFeaturesProvider.DARK_GRASS,
                        PlacedFeaturesProvider.DARK_GRASS_TOP,
                        PlacedFeaturesProvider.GRADI)),
                spawners(new Spawn(blackFox, 1, 5, 10)),
                Optional.of(COSMIC), 0.3f));

        // ===================== vanilla() biomes (black/white — no music) =====================
        context.register(BLACK, biome(
                vanilla(), 0.8f, 0.4f,
                gen(context, F(PlacedFeaturesProvider.CHESS_TREES)),
                spawners(new Spawn(blackRook, 1, 1, 2)),
                Optional.empty()));

        context.register(WHITE, biome(
                vanilla(), 0.8f, 0.4f,
                gen(context, F(PlacedFeaturesProvider.CHESS_TREES)),
                spawners(new Spawn(rook, 1, 1, 2)),
                Optional.empty()));
    }
    public static void init() {}
}
