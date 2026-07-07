package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public class MoonWelderDisplaySerializer implements DisplaySerializer<MoonWelderDisplay> {
    public final MapCodec<MoonWelderDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedMoonWelderRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, MoonWelderDisplay::new)
    );

    public final PacketCodec<RegistryByteBuf, MoonWelderDisplay> PACKET_CODEC = PacketCodec.tuple(
            ShapedMoonWelderRecipe.PACKET_CODEC,
            MoonWelderDisplay::recipe,
            MoonWelderDisplay::new
    );
    @Override
    public MapCodec<MoonWelderDisplay> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, MoonWelderDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
