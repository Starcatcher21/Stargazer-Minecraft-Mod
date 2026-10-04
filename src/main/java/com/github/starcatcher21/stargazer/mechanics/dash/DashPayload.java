package com.github.starcatcher21.stargazer.mechanics.dash;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DashPayload() implements CustomPacketPayload {
    public static final Type<DashPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "request_dash"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DashPayload> STREAM_CODEC =
            StreamCodec.unit(new DashPayload());

    @Override
    public Type<DashPayload> type() {
        return TYPE;
    }
}