package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Patterns>> STAR_PATTERN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_pattern"));
    public static final EntityDataSerializer<StarPatternsComponent> PATTERN_COMPONENT = EntityDataSerializer.forValueType(StarPatternsComponent.PACKET_CODEC);

    public static final ResourceKey<Registry<CobbleGen>> COBBLE_GEN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cobblegen"));

    public static final ResourceKey<Registry<FallingObject>> FALLING_OBJECTS = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling/objects"));

    public static final ResourceKey<Registry<FallingObjectsList>> FALLING_OBJECTS_LIST = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling/list"));


    public static void init() {
        DynamicRegistries.<FallingObject>register(
                FALLING_OBJECTS,
                FallingObject.CODEC
        );
        DynamicRegistries.<Patterns>register(
                STAR_PATTERN,
                Patterns.CODEC
        );
        DynamicRegistries.<CobbleGen>register(
                COBBLE_GEN,
                CobbleGen.CODEC
        );
        DynamicRegistries.<FallingObjectsList>register(
                FALLING_OBJECTS_LIST,
                FallingObjectsList.CODEC
        );
        FabricEntityDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns_component"), PATTERN_COMPONENT);
    }
}