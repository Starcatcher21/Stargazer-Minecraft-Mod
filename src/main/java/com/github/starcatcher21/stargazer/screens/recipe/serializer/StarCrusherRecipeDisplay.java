package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record StarCrusherRecipeDisplay(SlotDisplay item1, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<StarCrusherRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.fieldOf("item1").forGetter(StarCrusherRecipeDisplay::item1),
            SlotDisplay.CODEC.fieldOf("result").forGetter(StarCrusherRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(StarCrusherRecipeDisplay::craftingStation))
            .apply(instance, StarCrusherRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, StarCrusherRecipeDisplay> STREAM_CODEC;
    public static final Type<StarCrusherRecipeDisplay> SERIALIZER;

    public StarCrusherRecipeDisplay {
    }

    public Type<StarCrusherRecipeDisplay> type() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureFlagSet features) {
        return true;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(
                SlotDisplay.STREAM_CODEC, // <-- Fixed here
                StarCrusherRecipeDisplay::item1,
                SlotDisplay.STREAM_CODEC,
                StarCrusherRecipeDisplay::result,
                SlotDisplay.STREAM_CODEC,
                StarCrusherRecipeDisplay::craftingStation,
                StarCrusherRecipeDisplay::new
        );
        SERIALIZER = new Type(CODEC, STREAM_CODEC);
    }
}