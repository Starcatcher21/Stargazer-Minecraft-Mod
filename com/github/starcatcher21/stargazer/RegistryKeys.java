package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.mechanics.Generators.CobbleGen;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class RegistryKeys {
    public static final RegistryKey<Registry<Patterns>> STAR_PATTERN = RegistryKey.ofRegistry(Identifier.of(Stargazer.MOD_ID, "star_pattern"));
    public static final TrackedDataHandler<Patterns> PATTERN = TrackedDataHandler.create(Patterns.PACKET_CODEC);
    public static final TrackedDataHandler<StarPatternsComponent> PATTERN_COMPONENT = TrackedDataHandler.create(StarPatternsComponent.PACKET_CODEC);

    public static final RegistryKey<Registry<CobbleGen>> COBBLE_GEN = RegistryKey.ofRegistry(Identifier.of(Stargazer.MOD_ID, "cobblegen"));
    public static final TrackedDataHandler<CobbleGen> COBBLE_GEN_DATA = TrackedDataHandler.create(CobbleGen.PACKET_CODEC);

    public static final RegistryKey<Registry<FallingObject>> FALLING_OBJECTS = RegistryKey.ofRegistry(Identifier.of(Stargazer.MOD_ID, "falling/objects"));
    public static final TrackedDataHandler<FallingObject> FALLING_OBJECTS_DATA = TrackedDataHandler.create(FallingObject.PACKET_CODEC);

    public static final RegistryKey<Registry<FallingObjectsList>> FALLING_OBJECTS_LIST = RegistryKey.ofRegistry(Identifier.of(Stargazer.MOD_ID, "falling/list"));
    public static final TrackedDataHandler<FallingObjectsList> FALLING_OBJECTS_LIST_DATA = TrackedDataHandler.create(FallingObjectsList.PACKET_CODEC);


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
        FabricTrackedDataRegistry.register(Identifier.of(Stargazer.MOD_ID, "patterns"), PATTERN);
        FabricTrackedDataRegistry.register(Identifier.of(Stargazer.MOD_ID, "cobble_gen"), COBBLE_GEN_DATA);
        FabricTrackedDataRegistry.register(Identifier.of(Stargazer.MOD_ID, "falling_objects"), FALLING_OBJECTS_DATA);
        FabricTrackedDataRegistry.register(Identifier.of(Stargazer.MOD_ID, "falling_objects_list"), FALLING_OBJECTS_LIST_DATA);
        FabricTrackedDataRegistry.register(Identifier.of(Stargazer.MOD_ID, "patterns_component"), PATTERN_COMPONENT);
    }
}