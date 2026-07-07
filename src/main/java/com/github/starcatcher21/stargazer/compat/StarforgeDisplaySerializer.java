package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class StarforgeDisplaySerializer implements DisplaySerializer<StarforgeDisplay> {
    public final MapCodec<StarforgeDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedStarforgeRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, StarforgeDisplay::new)
    );

    public final StreamCodec<RegistryFriendlyByteBuf, StarforgeDisplay> PACKET_CODEC = StreamCodec.composite(
            ShapedStarforgeRecipe.STREAM_CODEC,
            StarforgeDisplay::recipe,
            StarforgeDisplay::new
    );
    @Override
    public MapCodec<StarforgeDisplay> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, StarforgeDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
