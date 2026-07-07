package com.github.starcatcher21.stargazer.mechanics.star;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.StringIdentifiable;

public enum FallingObjectDayState implements StringIdentifiable {
    Day("day"),
    Night("night"),
    Both("both");

    private final String ID;

    public static final Codec<FallingObjectDayState> CODEC = StringIdentifiable.createCodec(() -> FallingObjectDayState.values());
    public static final PacketCodec<RegistryByteBuf, FallingObjectDayState> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, FallingObjectDayState::asString, FallingObjectDayState::newDayState
    );

    FallingObjectDayState(String id) {
        ID = id;
    }

    public static FallingObjectDayState newDayState(String id) {
        if (id.equals("day")) {
            return FallingObjectDayState.Day;
        } else if (id.equals("night")) {
            return FallingObjectDayState.Night;
        } else {
            return FallingObjectDayState.Both;
        }
    }

    @Override
    public String asString() {
        return this.ID;
    }

    private String getId() {
        return this.ID;
    }
}
