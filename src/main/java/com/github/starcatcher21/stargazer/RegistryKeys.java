package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Patterns>> STAR_PATTERN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_pattern"));
    public static final EntityDataSerializer<StarPatternsComponent> PATTERN_COMPONENT = EntityDataSerializer.forValueType(StarPatternsComponent.PACKET_CODEC);


    public static void init() {
        DynamicRegistries.<Patterns>register(
                STAR_PATTERN,
                Patterns.CODEC
        );
        FabricEntityDataRegistry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns_component"), PATTERN_COMPONENT);
    }
}