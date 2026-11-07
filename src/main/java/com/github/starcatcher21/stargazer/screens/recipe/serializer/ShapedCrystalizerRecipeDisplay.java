//? if >= 26.2 {
/*package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record ShapedCrystalizerRecipeDisplay(
        SlotDisplay item1,
        SlotDisplay item2,
        SlotDisplay result,
        SlotDisplay craftingStation
) implements RecipeDisplay {

    public static final MapCodec<ShapedCrystalizerRecipeDisplay> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    SlotDisplay.CODEC.fieldOf("item1").forGetter(ShapedCrystalizerRecipeDisplay::item1),
                    SlotDisplay.CODEC.fieldOf("item2").forGetter(ShapedCrystalizerRecipeDisplay::item2),
                    SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedCrystalizerRecipeDisplay::result),
                    SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedCrystalizerRecipeDisplay::craftingStation)
            ).apply(instance, ShapedCrystalizerRecipeDisplay::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedCrystalizerRecipeDisplay> STREAM_CODEC;
    public static final Type<ShapedCrystalizerRecipeDisplay> SERIALIZER;

    public ShapedCrystalizerRecipeDisplay {
    }

    @Override
    public Type<ShapedCrystalizerRecipeDisplay> type() {
        return SERIALIZER;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet features) {
        return true;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(
                SlotDisplay.STREAM_CODEC, ShapedCrystalizerRecipeDisplay::item1,
                SlotDisplay.STREAM_CODEC, ShapedCrystalizerRecipeDisplay::item2,
                SlotDisplay.STREAM_CODEC, ShapedCrystalizerRecipeDisplay::result,
                SlotDisplay.STREAM_CODEC, ShapedCrystalizerRecipeDisplay::craftingStation,
                ShapedCrystalizerRecipeDisplay::new
        );
        SERIALIZER = new Type(CODEC, STREAM_CODEC);
    }
}
*///? }
