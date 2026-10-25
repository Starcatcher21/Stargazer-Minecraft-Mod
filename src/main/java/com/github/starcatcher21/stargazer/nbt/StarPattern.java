// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.nbt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public record StarPattern(Identifier assetId, String translationKey) {
    public static final Codec<StarPattern> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("asset_id").forGetter(StarPattern::assetId), Codec.STRING.fieldOf("translation_key").forGetter(StarPattern::translationKey)
                    )
                    .apply(instance, StarPattern::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, StarPattern> PACKET_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, StarPattern::assetId, ByteBufCodecs.STRING_UTF8, StarPattern::translationKey, StarPattern::new
    );

    public static void init() {}
}
