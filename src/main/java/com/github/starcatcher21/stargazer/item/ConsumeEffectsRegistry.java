package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.item.ConsumeEffects.StarGazeConsume;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class ConsumeEffectsRegistry {
    public static final ConsumeEffect.Type<StarGazeConsume> STARGAZE = register(
            "stargaze", StarGazeConsume.CODEC, StarGazeConsume.PACKET_CODEC
    );

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String id, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> packetCodec) {
        return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, id, new ConsumeEffect.Type<>(codec, packetCodec));
    }
    public static void init() {}
}
