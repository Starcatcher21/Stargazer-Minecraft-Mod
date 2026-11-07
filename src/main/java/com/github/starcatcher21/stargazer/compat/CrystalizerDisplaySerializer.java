package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedCrystalizerRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
//? if < 26.2 {
import net.minecraft.nbt.CompoundTag;
//? }
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class CrystalizerDisplaySerializer implements DisplaySerializer<CrystalizerDisplay> {
    public final MapCodec<CrystalizerDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedCrystalizerRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, CrystalizerDisplay::new)
    );

    public final StreamCodec<RegistryFriendlyByteBuf, CrystalizerDisplay> PACKET_CODEC = StreamCodec.composite(
            ShapedCrystalizerRecipe.STREAM_CODEC,
            CrystalizerDisplay::recipe,
            CrystalizerDisplay::new
    );
    //? if >= 26.2 {
    /*@Override
    public MapCodec<CrystalizerDisplay> codec() {
        return CODEC;
    }

    public StreamCodec<RegistryFriendlyByteBuf, CrystalizerDisplay> streamCodec() {
        return PACKET_CODEC;
    }
    *///? } else {
    @Override
    public CompoundTag save(CompoundTag tag, CrystalizerDisplay display) {
        tag.put("recipe", LegacyDisplayCodec.encode(ShapedCrystalizerRecipe.CODEC.codec(), display.recipe()));
        return tag;
    }

    @Override
    public CrystalizerDisplay read(CompoundTag tag) {
        return new CrystalizerDisplay(
                LegacyDisplayCodec.decode(ShapedCrystalizerRecipe.CODEC.codec(), tag.get("recipe")));
    }
    //? }
}
