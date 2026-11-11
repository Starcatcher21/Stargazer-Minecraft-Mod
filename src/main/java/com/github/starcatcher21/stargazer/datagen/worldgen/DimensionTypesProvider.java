package com.github.starcatcher21.stargazer.datagen.worldgen;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
//? if >= 26.1.2 {
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.CardinalLighting;
//? }

import java.util.Optional;
import java.util.OptionalLong;

public class DimensionTypesProvider {

    // --- Registry Keys ---
    public static final ResourceKey<DimensionType> WANDER  = key("wander");
    public static final ResourceKey<DimensionType> RED_ORB = key("red_orb");
    public static final ResourceKey<DimensionType> COSMIC  = key("cosmic");

    private static ResourceKey<DimensionType> key(String name) {
        return ResourceKey.create(Registries.DIMENSION_TYPE,
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }

    /**
     * Shared baseline for all three custom dimensions.
     *
     * <p>They only differ in {@code ambient_light} and whether time is pinned.
     * Every other field matches the JSON files:
     * <pre>
     *   has_ender_dragon_fight = false
     *   has_ceiling           = true
     *   has_skylight          = true
     *   height / logical_height = 384
     *   min_y                 = -64
     *   coordinate_scale      = 1.0
     *   infiniburn            = #minecraft:infiniburn_end
     *   monster_spawn_light_level = uniform(0, 7)
     *   monster_spawn_block_light_limit = 0
     *   bed_rule              = (never, never, explodes)
     *   sun_angle / moon_angle = 180
     * </pre>
     */
    private static DimensionType dim(BootstrapContext<DimensionType> context, float ambientLight, boolean hasFixedTime) {
        //? if >= 26.1.2 {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        return new DimensionType(
                hasFixedTime,
                true,
                true,
                false,
                1.0,
                -64,
                384,
                384,
                blocks.getOrThrow(BlockTags.INFINIBURN_END),
                ambientLight,
                new DimensionType.MonsterSettings(
                        UniformInt.of(0, 7),0
                ),
                DimensionType.Skybox.END,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY,
                HolderSet.empty(),
                Optional.empty()
        );
        //? } else {
        /*return new DimensionType(
                hasFixedTime ? OptionalLong.of(6000L) : OptionalLong.empty(),
                true,   // hasSkylight
                true,   // hasCeiling
                false,  // ultraWarm
                false,  // natural
                1.0,    // coordinateScale
                false,  // bedWorks        -> explodes
                false,  // respawnAnchorWorks
                -64,    // minY
                384,    // height
                384,    // logicalHeight
                BlockTags.INFINIBURN_END,
                Identifier.withDefaultNamespace("the_end"), // effects
                ambientLight,
                new DimensionType.MonsterSettings(
                        false,                  // piglinSafe
                        false,                  // hasRaids
                        UniformInt.of(0, 7),    // monster_spawn_light_level
                        0                       // monster_spawn_block_light_limit
                )
        );
        *///? }
    }

    public static void bootstrap(BootstrapContext<DimensionType> context) {
        context.register(WANDER,  dim(context, 0.8f, false));
        context.register(RED_ORB, dim(context, 0.2f, false));
        context.register(COSMIC,  dim(context, 0.4f, true));
    }

    public static void init() {}
}
