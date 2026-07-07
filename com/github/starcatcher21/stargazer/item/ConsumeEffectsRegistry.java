package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.item.ConsumeEffects.StarGazeConsume;
import com.mojang.serialization.MapCodec;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ConsumeEffectsRegistry {
    public static final ConsumeEffect.Type<StarGazeConsume> STARGAZE = register(
            "stargaze", StarGazeConsume.CODEC, StarGazeConsume.PACKET_CODEC
    );

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String id, MapCodec<T> codec, PacketCodec<RegistryByteBuf, T> packetCodec) {
        return Registry.register(Registries.CONSUME_EFFECT_TYPE, id, new ConsumeEffect.Type<>(codec, packetCodec));
    }
    public static void init() {}
}
