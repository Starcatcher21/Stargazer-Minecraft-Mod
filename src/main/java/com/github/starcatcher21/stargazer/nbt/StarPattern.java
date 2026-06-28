package com.github.starcatcher21.stargazer.nbt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

public record StarPattern(Identifier assetId, String translationKey) {
    public static final Codec<StarPattern> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("asset_id").forGetter(StarPattern::assetId), Codec.STRING.fieldOf("translation_key").forGetter(StarPattern::translationKey)
                    )
                    .apply(instance, StarPattern::new)
    );
    public static final PacketCodec<RegistryByteBuf, StarPattern> PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC, StarPattern::assetId, PacketCodecs.STRING, StarPattern::translationKey, StarPattern::new
    );

    public static void init() {}
}
