package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public class StargazingDisplaySerializer implements DisplaySerializer<StargazingDisplay> {
    public final MapCodec<StargazingDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    FallingObjectsList.CODEC.fieldOf("objects").forGetter(display -> display.getList()))
            .apply(instance, StargazingDisplay::new)
    );

    public final PacketCodec<RegistryByteBuf, StargazingDisplay> PACKET_CODEC = PacketCodec.tuple(
            FallingObjectsList.PACKET_CODEC,
            StargazingDisplay::getList,
            StargazingDisplay::new
    );
    @Override
    public MapCodec<StargazingDisplay> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, StargazingDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
