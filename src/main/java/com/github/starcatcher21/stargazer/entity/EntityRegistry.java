package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Stargazer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EntityRegistry {

    public static final Identifier GHOST_ID = Identifier.of(Stargazer.MOD_ID, "ghost");
    public static final RegistryKey<EntityType<?>> GHOST_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, GHOST_ID);

    public static final EntityType<Ghost> GHOST_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            GHOST_ID,
            EntityType.Builder.create(Ghost::new, SpawnGroup.CREATURE)
                    .dimensions(0.65f, 0.65f)
                    .makeFireImmune()
                    .build(GHOST_KEY)
    );

    public static final Identifier AMETHYST_TURTLE_ID = Identifier.of(Stargazer.MOD_ID, "amethyst_turtle");
    public static final RegistryKey<EntityType<?>> AMETHYST_TURTLE_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, AMETHYST_TURTLE_ID);

    public static final EntityType<AmethystTurtle> AMETHYST_TURTLE_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            AMETHYST_TURTLE_ID,
            EntityType.Builder.create(AmethystTurtle::new, SpawnGroup.CREATURE)
                    .dimensions(0.65f, 0.35f)
                    .makeFireImmune()
                    .build(AMETHYST_TURTLE_KEY)
    );

    public static final Identifier EYE_BAT_ID = Identifier.of(Stargazer.MOD_ID, "eye_bat");
    public static final RegistryKey<EntityType<?>> EYE_BAT_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, EYE_BAT_ID);

    public static final EntityType<EyeBat> EYE_BAT_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            EYE_BAT_ID,
            EntityType.Builder.create(EyeBat::new, SpawnGroup.CREATURE)
                    .dimensions(0.65f, 0.65f)
                    .build(EYE_BAT_KEY)
    );

    public static final Identifier STAR_ID = Identifier.of(Stargazer.MOD_ID, "star");
    public static final RegistryKey<EntityType<?>> STAR_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, STAR_ID);

    public static final EntityType<Star> STAR_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            STAR_ID,
            EntityType.Builder.create(Star::new, SpawnGroup.MISC)
                    .dimensions(1.25f, 0.25f)
                    .makeFireImmune()
                    .build(STAR_KEY)
    );

    public static final Identifier THROWABLE_STAR_ID = Identifier.of(Stargazer.MOD_ID, "throwable_star");
    public static final RegistryKey<EntityType<?>> THROWABLE_STAR_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, THROWABLE_STAR_ID);

    public static final EntityType<ThrowableStarEntity> THROWABLE_STAR_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            THROWABLE_STAR_ID,
            EntityType.Builder.<ThrowableStarEntity>create(ThrowableStarEntity::new, SpawnGroup.MISC)
                    .dimensions(1.25f, 0.25f)
                    .makeFireImmune()
                    .build(THROWABLE_STAR_KEY)
    );

    public static final Identifier ROOK_ID = Identifier.of(Stargazer.MOD_ID, "rook");
    public static final RegistryKey<EntityType<?>> ROOK_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, ROOK_ID);

    public static final EntityType<Rook> ROOK_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            ROOK_ID,
            EntityType.Builder.create(Rook::new, SpawnGroup.CREATURE)
                    .dimensions(1.0f, 1.0f)
                    .build(ROOK_KEY)
    );
    public static final Identifier BLACK_ROOK_ID = Identifier.of(Stargazer.MOD_ID, "black_rook");
    public static final RegistryKey<EntityType<?>> BLACK_ROOK_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, BLACK_ROOK_ID);

    public static final EntityType<BlackRook> BLACK_ROOK_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            BLACK_ROOK_ID,
            EntityType.Builder.create(BlackRook::new, SpawnGroup.CREATURE)
                    .dimensions(1.0f, 1.0f)
                    .build(BLACK_ROOK_KEY)
    );
    public static final Identifier SCRUBY_ID = Identifier.of(Stargazer.MOD_ID, "scruby");
    public static final RegistryKey<EntityType<?>> SCRUBY_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, SCRUBY_ID);

    public static final EntityType<Scruby> SCRUBY_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            SCRUBY_ID,
            EntityType.Builder.create(Scruby::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.5f)
                    .build(SCRUBY_KEY)
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(GHOST_ENTITY, Ghost.createFlyingCreatureAttributes());
        FabricDefaultAttributeRegistry.register(AMETHYST_TURTLE_ENTITY, AmethystTurtle.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(EYE_BAT_ENTITY, EyeBat.createFlyingCreatureAttributes());
        FabricDefaultAttributeRegistry.register(ROOK_ENTITY, Rook.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(BLACK_ROOK_ENTITY, BlackRook.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(SCRUBY_ENTITY, Scruby.createCreatureAttributes());
    }
}
