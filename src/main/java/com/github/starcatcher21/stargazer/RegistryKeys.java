package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Patterns>> STAR_PATTERN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_pattern"));
    public static final EntityDataSerializer<Patterns> PATTERN = EntityDataSerializer.forValueType(Patterns.PACKET_CODEC);
    public static final EntityDataSerializer<StarPatternsComponent> PATTERN_COMPONENT = EntityDataSerializer.forValueType(StarPatternsComponent.PACKET_CODEC);

    public static final ResourceKey<Registry<CobbleGen>> COBBLE_GEN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cobblegen"));
    public static final EntityDataSerializer<CobbleGen> COBBLE_GEN_DATA = EntityDataSerializer.forValueType(CobbleGen.PACKET_CODEC);

    public static final ResourceKey<Registry<FallingObject>> FALLING_OBJECTS = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling/objects"));
    public static final EntityDataSerializer<FallingObject> FALLING_OBJECTS_DATA = EntityDataSerializer.forValueType(FallingObject.PACKET_CODEC);

    public static final ResourceKey<Registry<FallingObjectsList>> FALLING_OBJECTS_LIST = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling/list"));
    public static final EntityDataSerializer<FallingObjectsList> FALLING_OBJECTS_LIST_DATA = EntityDataSerializer.forValueType(FallingObjectsList.PACKET_CODEC);


    public static void init() {
        DynamicRegistries.<Patterns>register(
                STAR_PATTERN,
                Patterns.CODEC
        );
        DynamicRegistries.<CobbleGen>register(
                COBBLE_GEN,
                CobbleGen.CODEC
        );
        DynamicRegistries.<FallingObject>register(
                FALLING_OBJECTS,
                FallingObject.CODEC
        );
        DynamicRegistries.<FallingObjectsList>register(
                FALLING_OBJECTS_LIST,
                FallingObjectsList.CODEC
        );
        FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns"), PATTERN);
        FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "cobble_gen"), COBBLE_GEN_DATA);
        FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling_objects"), FALLING_OBJECTS_DATA);
        FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "falling_objects_list"), FALLING_OBJECTS_LIST_DATA);
        FabricTrackedDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns_component"), PATTERN_COMPONENT);
    }
}