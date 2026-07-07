package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public class StarCrusherDisplaySerializer implements DisplaySerializer<StarCrusherDisplay> {
    public final MapCodec<StarCrusherDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedStarCrusherRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, StarCrusherDisplay::new)
    );

    public final PacketCodec<RegistryByteBuf, StarCrusherDisplay> PACKET_CODEC = PacketCodec.tuple(
            ShapedStarCrusherRecipe.PACKET_CODEC,
            StarCrusherDisplay::recipe,
            StarCrusherDisplay::new
    );
    @Override
    public MapCodec<StarCrusherDisplay> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, StarCrusherDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
