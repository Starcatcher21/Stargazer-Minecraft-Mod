package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class MoonWelderDisplaySerializer implements DisplaySerializer<MoonWelderDisplay> {
    public final MapCodec<MoonWelderDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedMoonWelderRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, MoonWelderDisplay::new)
    );

    public final StreamCodec<RegistryFriendlyByteBuf, MoonWelderDisplay> PACKET_CODEC = StreamCodec.composite(
            ShapedMoonWelderRecipe.STREAM_CODEC,
            MoonWelderDisplay::recipe,
            MoonWelderDisplay::new
    );
    //? if >= 26.2 {
    @Override
    public MapCodec<MoonWelderDisplay> codec() {
        return CODEC;
    }

    public StreamCodec<RegistryFriendlyByteBuf, MoonWelderDisplay> streamCodec() {
        return PACKET_CODEC;
    }
    //? } else {
    /*@Override
    public CompoundTag save(CompoundTag tag, MoonWelderDisplay display) {
        tag.put("recipe", LegacyDisplayCodec.encode(ShapedMoonWelderRecipe.CODEC.codec(), display.recipe()));
        return tag;
    }

    @Override
    public MoonWelderDisplay read(CompoundTag tag) {
        return new MoonWelderDisplay(LegacyDisplayCodec.decode(ShapedMoonWelderRecipe.CODEC.codec(), tag.get("recipe")));
    }
    *///? }
}
