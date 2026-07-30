package com.github.starcatcher21.stargazer.mechanics.dash;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DashPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<DashPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "request_dash"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DashPayload> STREAM_CODEC =
            StreamCodec.unit(new DashPayload());

    @Override
    public CustomPacketPayload.Type<DashPayload> type() {
        return TYPE;
    }
}