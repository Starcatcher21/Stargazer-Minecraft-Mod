package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {

    public static final Identifier GHOST_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "ghost");
    public static final ResourceKey<EntityType<?>> GHOST_KEY = ResourceKey.create(Registries.ENTITY_TYPE, GHOST_ID);

    public static final EntityType<Ghost> GHOST_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            GHOST_ID,
            EntityType.Builder.of(Ghost::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.65f)
                    .fireImmune()
                    .build(GHOST_KEY)
    );

    public static final Identifier AMETHYST_TURTLE_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "amethyst_turtle");
    public static final ResourceKey<EntityType<?>> AMETHYST_TURTLE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, AMETHYST_TURTLE_ID);

    public static final EntityType<AmethystTurtle> AMETHYST_TURTLE_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            AMETHYST_TURTLE_ID,
            EntityType.Builder.of(AmethystTurtle::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.35f)
                    .fireImmune()
                    .build(AMETHYST_TURTLE_KEY)
    );

    public static final Identifier EYE_BAT_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "eye_bat");
    public static final ResourceKey<EntityType<?>> EYE_BAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE, EYE_BAT_ID);

    public static final EntityType<EyeBat> EYE_BAT_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            EYE_BAT_ID,
            EntityType.Builder.of(EyeBat::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.65f)
                    .build(EYE_BAT_KEY)
    );

    public static final Identifier STAR_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star");
    public static final ResourceKey<EntityType<?>> STAR_KEY = ResourceKey.create(Registries.ENTITY_TYPE, STAR_ID);

    public static final EntityType<Star> STAR_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            STAR_ID,
            EntityType.Builder.of(Star::new, MobCategory.MISC)
                    .sized(1.25f, 0.25f)
                    .fireImmune()
                    .build(STAR_KEY)
    );

    public static final Identifier THROWABLE_STAR_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "throwable_star");
    public static final ResourceKey<EntityType<?>> THROWABLE_STAR_KEY = ResourceKey.create(Registries.ENTITY_TYPE, THROWABLE_STAR_ID);

    public static final EntityType<ThrowableStarEntity> THROWABLE_STAR_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            THROWABLE_STAR_ID,
            EntityType.Builder.<ThrowableStarEntity>of(ThrowableStarEntity::new, MobCategory.MISC)
                    .sized(1.25f, 0.25f)
                    .fireImmune()
                    .build(THROWABLE_STAR_KEY)
    );

    public static final Identifier ROOK_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "rook");
    public static final ResourceKey<EntityType<?>> ROOK_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ROOK_ID);

    public static final EntityType<Rook> ROOK_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ROOK_ID,
            EntityType.Builder.of(Rook::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(ROOK_KEY)
    );
    public static final Identifier BLACK_ROOK_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_rook");
    public static final ResourceKey<EntityType<?>> BLACK_ROOK_KEY = ResourceKey.create(Registries.ENTITY_TYPE, BLACK_ROOK_ID);

    public static final EntityType<BlackRook> BLACK_ROOK_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            BLACK_ROOK_ID,
            EntityType.Builder.of(BlackRook::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(BLACK_ROOK_KEY)
    );
    public static final Identifier SCRUBY_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "scruby");
    public static final ResourceKey<EntityType<?>> SCRUBY_KEY = ResourceKey.create(Registries.ENTITY_TYPE, SCRUBY_ID);

    public static final EntityType<Scruby> SCRUBY_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            SCRUBY_ID,
            EntityType.Builder.of(Scruby::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.5f)
                    .build(SCRUBY_KEY)
    );

    public static final Identifier BLACK_FOX_ID = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_fox");
    public static final ResourceKey<EntityType<?>> BLACK_FOX_KEY = ResourceKey.create(Registries.ENTITY_TYPE, BLACK_FOX_ID);

    public static final EntityType<BlackFox> BLACK_FOX_ENTITY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            BLACK_FOX_ID,
            EntityType.Builder.of(BlackFox::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.5f)
                    .build(BLACK_FOX_KEY)
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(GHOST_ENTITY, Ghost.createFlyingCreatureAttributes());
        FabricDefaultAttributeRegistry.register(AMETHYST_TURTLE_ENTITY, AmethystTurtle.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(EYE_BAT_ENTITY, EyeBat.createFlyingCreatureAttributes());
        FabricDefaultAttributeRegistry.register(ROOK_ENTITY, Rook.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(BLACK_ROOK_ENTITY, BlackRook.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(SCRUBY_ENTITY, Scruby.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(BLACK_FOX_ENTITY, BlackFox.createCreatureAttributes());
    }
}
