package com.github.starcatcher21.stargazer.mechanics.star;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StargazePayload() implements CustomPacketPayload {
    public static final Type<StargazePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "request_star_catch"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StargazePayload> STREAM_CODEC =
            StreamCodec.unit(new StargazePayload());

    @Override
    public Type<StargazePayload> type() {
        return TYPE;
    }
}
