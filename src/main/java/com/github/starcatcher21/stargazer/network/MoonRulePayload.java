package com.github.starcatcher21.stargazer.network;

import com.github.starcatcher21.stargazer.Stargazer;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

public record MoonRulePayload(boolean moonRuleEnabled) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<MoonRulePayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_rule"));

    public static final StreamCodec<ByteBuf, MoonRulePayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.BOOL, MoonRulePayload::moonRuleEnabled,
                    MoonRulePayload::new
            );

    @Override
    public CustomPacketPayload.Type<MoonRulePayload> type() {
        return TYPE;
    }
}
