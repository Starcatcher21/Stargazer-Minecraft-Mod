package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public class StarforgeDisplaySerializer implements DisplaySerializer<StarforgeDisplay> {
    public final MapCodec<StarforgeDisplay> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ShapedStarforgeRecipe.CODEC.fieldOf("recipe").forGetter(display -> display.recipe()))
            .apply(instance, StarforgeDisplay::new)
    );

    public final PacketCodec<RegistryByteBuf, StarforgeDisplay> PACKET_CODEC = PacketCodec.tuple(
            ShapedStarforgeRecipe.PACKET_CODEC,
            StarforgeDisplay::recipe,
            StarforgeDisplay::new
    );
    @Override
    public MapCodec<StarforgeDisplay> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, StarforgeDisplay> streamCodec() {
        return PACKET_CODEC;
    }
}
