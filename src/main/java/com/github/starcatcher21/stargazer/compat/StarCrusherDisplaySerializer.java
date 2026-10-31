package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
//? if < 26.2 {
import net.minecraft.nbt.CompoundTag;
//? }
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class StarCrusherDisplaySerializer implements DisplaySerializer<StarCrusherDisplay> {
    public final MapCodec<StarCrusherDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedStarCrusherRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, StarCrusherDisplay::new)
    );

    public final StreamCodec<RegistryFriendlyByteBuf, StarCrusherDisplay> PACKET_CODEC = StreamCodec.composite(
            ShapedStarCrusherRecipe.STREAM_CODEC,
            StarCrusherDisplay::recipe,
            StarCrusherDisplay::new
    );
    //? if >= 26.2 {
    /*@Override
    public MapCodec<StarCrusherDisplay> codec() {
        return CODEC;
    }

    public StreamCodec<RegistryFriendlyByteBuf, StarCrusherDisplay> streamCodec() {
        return PACKET_CODEC;
    }
    *///? } else {
    @Override
    public CompoundTag save(CompoundTag tag, StarCrusherDisplay display) {
        tag.put("recipe", LegacyDisplayCodec.encode(ShapedStarCrusherRecipe.CODEC.codec(), display.recipe()));
        return tag;
    }

    @Override
    public StarCrusherDisplay read(CompoundTag tag) {
        return new StarCrusherDisplay(LegacyDisplayCodec.decode(ShapedStarCrusherRecipe.CODEC.codec(), tag.get("recipe")));
    }
    //? }
}
