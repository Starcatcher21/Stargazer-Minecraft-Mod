package com.github.starcatcher21.stargazer.fabric;

import com.github.starcatcher21.stargazer.RegistryKeys;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.nbt.Patterns;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.resources.Identifier;

public class RegistryKeysImpl {
    public static void init() {
        DynamicRegistries.register(
                RegistryKeys.STAR_PATTERN,
                Patterns.CODEC
        );
        FabricEntityDataRegistry.register(
                Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "patterns_component"),
                RegistryKeys.PATTERN_COMPONENT
        );
    }
}