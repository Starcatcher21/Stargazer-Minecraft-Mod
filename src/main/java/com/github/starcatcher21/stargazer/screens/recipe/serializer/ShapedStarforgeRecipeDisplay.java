package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;

public record ShapedStarforgeRecipeDisplay(List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<ShapedStarforgeRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.listOf().fieldOf("ingredients").forGetter(ShapedStarforgeRecipeDisplay::ingredients),
            SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedStarforgeRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedStarforgeRecipeDisplay::craftingStation))
            .apply(instance, ShapedStarforgeRecipeDisplay::new));
    public static final PacketCodec<RegistryByteBuf, ShapedStarforgeRecipeDisplay> PACKET_CODEC;
    public static final RecipeDisplay.Serializer<ShapedStarforgeRecipeDisplay> SERIALIZER;

    public ShapedStarforgeRecipeDisplay {
        if (ingredients.size() != 14) {
            throw new IllegalArgumentException("Invalid shaped recipe display contents");
        }
    }

    public RecipeDisplay.Serializer<ShapedStarforgeRecipeDisplay> serializer() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureSet features) {
        return this.ingredients.stream().allMatch((ingredient) -> ingredient.isEnabled(features));
    }

    static {
        PACKET_CODEC = PacketCodec.tuple(
                SlotDisplay.PACKET_CODEC.collect(PacketCodecs.toList()),
                ShapedStarforgeRecipeDisplay::ingredients,
                SlotDisplay.PACKET_CODEC,
                ShapedStarforgeRecipeDisplay::result,
                SlotDisplay.PACKET_CODEC,
                ShapedStarforgeRecipeDisplay::craftingStation,
                ShapedStarforgeRecipeDisplay::new
        );
        SERIALIZER = new RecipeDisplay.Serializer(CODEC, PACKET_CODEC);
    }
}