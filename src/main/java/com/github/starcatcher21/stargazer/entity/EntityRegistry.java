package com.github.starcatcher21.stargazer.entity;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Stargazer.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<Ghost>> GHOST_ENTITY = ENTITY_TYPES.register("ghost", () ->
            EntityType.Builder.of(Ghost::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.65f)
                    .fireImmune()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "ghost")))
    );

    public static final RegistrySupplier<EntityType<AmethystTurtle>> AMETHYST_TURTLE_ENTITY = ENTITY_TYPES.register("amethyst_turtle", () ->
            EntityType.Builder.of(AmethystTurtle::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.35f)
                    .fireImmune()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "amethyst_turtle")))
    );

    public static final RegistrySupplier<EntityType<EyeBat>> EYE_BAT_ENTITY = ENTITY_TYPES.register("eye_bat", () ->
            EntityType.Builder.of(EyeBat::new, MobCategory.CREATURE)
                    .sized(0.65f, 0.65f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "eye_bat")))
    );

    public static final RegistrySupplier<EntityType<Star>> STAR_ENTITY = ENTITY_TYPES.register("star", () ->
            EntityType.Builder.of(Star::new, MobCategory.MISC)
                    .sized(1.25f, 0.25f)
                    .fireImmune()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star")))
    );

    public static final RegistrySupplier<EntityType<ThrowableStarEntity>> THROWABLE_STAR_ENTITY = ENTITY_TYPES.register("throwable_star", () ->
            EntityType.Builder.<ThrowableStarEntity>of(ThrowableStarEntity::new, MobCategory.MISC)
                    .sized(1.25f, 0.25f)
                    .fireImmune()
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "throwable_star")))
    );

    public static final RegistrySupplier<EntityType<Rook>> ROOK_ENTITY = ENTITY_TYPES.register("rook", () ->
            EntityType.Builder.of(Rook::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "rook")))
    );

    public static final RegistrySupplier<EntityType<BlackRook>> BLACK_ROOK_ENTITY = ENTITY_TYPES.register("black_rook", () ->
            EntityType.Builder.of(BlackRook::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_rook")))
    );

    public static final RegistrySupplier<EntityType<Scruby>> SCRUBY_ENTITY = ENTITY_TYPES.register("scruby", () ->
            EntityType.Builder.of(Scruby::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.5f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "scruby")))
    );

    public static final RegistrySupplier<EntityType<BlackFox>> BLACK_FOX_ENTITY = ENTITY_TYPES.register("black_fox", () ->
            EntityType.Builder.of(BlackFox::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.5f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "black_fox")))
    );

    public static void init() {
        ENTITY_TYPES.register();
        GHOST_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, Ghost::createFlyingCreatureAttributes));
        AMETHYST_TURTLE_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, AmethystTurtle::createCreatureAttributes));
        EYE_BAT_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, EyeBat::createFlyingCreatureAttributes));
        ROOK_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, Rook::createCreatureAttributes));
        BLACK_ROOK_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, BlackRook::createCreatureAttributes));
        SCRUBY_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, Scruby::createCreatureAttributes));
        BLACK_FOX_ENTITY.listen(entity -> EntityAttributeRegistry.register(() -> entity, BlackFox::createCreatureAttributes));
    }
}