package com.github.starcatcher21.stargazer;

import com.github.starcatcher21.stargazer.nbt.Patterns;
import com.github.starcatcher21.stargazer.nbt.StarPatternsComponent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Patterns>> STAR_PATTERN = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_pattern"));
    public static final EntityDataSerializer<StarPatternsComponent> PATTERN_COMPONENT = EntityDataSerializer.forValueType(StarPatternsComponent.PACKET_CODEC);

    @ExpectPlatform
    public static void init() {
        throw new AssertionError();
    }
}