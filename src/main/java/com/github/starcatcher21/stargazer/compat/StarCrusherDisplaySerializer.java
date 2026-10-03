package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
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
    @Override
    public MapCodec<StarCrusherDisplay> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, StarCrusherDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
