package com.github.starcatcher21.stargazer.mechanics.dash;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DashPayload(int unused) implements CustomPacketPayload {

    public static final Identifier ID =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "request_dash");

    public static final StreamCodec<RegistryFriendlyByteBuf, DashPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, DashPayload::unused,
                    DashPayload::new);

    public static final CustomPacketPayload.Type<DashPayload> TYPE =
            new CustomPacketPayload.Type<>(ID);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // Convenience no-arg factory for the client send site.
    public static DashPayload empty() {
        return new DashPayload(0);
    }
}
