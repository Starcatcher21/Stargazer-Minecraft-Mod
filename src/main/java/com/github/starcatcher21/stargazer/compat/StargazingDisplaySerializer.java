package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.starlib.mechanics.star.FallingObjectsList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
//? if < 26.2 {
/*import net.minecraft.nbt.CompoundTag;
*///? }
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class StargazingDisplaySerializer implements DisplaySerializer<StargazingDisplay> {
    public final MapCodec<StargazingDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    FallingObjectsList.CODEC.fieldOf("objects").forGetter(display -> display.getList()))
            .apply(instance, StargazingDisplay::new)
    );

    public final StreamCodec<RegistryFriendlyByteBuf, StargazingDisplay> PACKET_CODEC = StreamCodec.composite(
            FallingObjectsList.PACKET_CODEC,
            StargazingDisplay::getList,
            StargazingDisplay::new
    );
    //? if >= 26.2 {
    @Override
    public MapCodec<StargazingDisplay> codec() {
        return CODEC;
    }

    public StreamCodec<RegistryFriendlyByteBuf, StargazingDisplay> streamCodec() {
        return PACKET_CODEC;
    }
    //? } else {
    /*@Override
    public CompoundTag save(CompoundTag tag, StargazingDisplay display) {
        tag.put("objects", LegacyDisplayCodec.encode(FallingObjectsList.CODEC, display.getList()));
        return tag;
    }

    @Override
    public StargazingDisplay read(CompoundTag tag) {
        return new StargazingDisplay(LegacyDisplayCodec.decode(FallingObjectsList.CODEC, tag.get("objects")));
    }
    *///? }
}
