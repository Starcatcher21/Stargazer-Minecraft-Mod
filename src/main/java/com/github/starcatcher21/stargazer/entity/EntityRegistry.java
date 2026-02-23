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
                    .makeFireImmune()
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

    public static void init() {
        FabricDefaultAttributeRegistry.register(GHOST_ENTITY, Ghost.createFlyingCreatureAttributes());
        FabricDefaultAttributeRegistry.register(AMETHYST_TURTLE_ENTITY, AmethystTurtle.createCreatureAttributes());
        FabricDefaultAttributeRegistry.register(EYE_BAT_ENTITY, EyeBat.createFlyingCreatureAttributes());
    }
}
