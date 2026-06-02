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

public record ShapedMoonWelderRecipeDisplay(List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<ShapedMoonWelderRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.listOf().fieldOf("ingredients").forGetter(ShapedMoonWelderRecipeDisplay::ingredients),
            SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedMoonWelderRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedMoonWelderRecipeDisplay::craftingStation))
            .apply(instance, ShapedMoonWelderRecipeDisplay::new));
    public static final PacketCodec<RegistryByteBuf, ShapedMoonWelderRecipeDisplay> PACKET_CODEC;
    public static final Serializer<ShapedMoonWelderRecipeDisplay> SERIALIZER;

    public ShapedMoonWelderRecipeDisplay {
        if (ingredients.size() != 14) {
            throw new IllegalArgumentException("Invalid shaped recipe display contents");
        }
    }

    public Serializer<ShapedMoonWelderRecipeDisplay> serializer() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureSet features) {
        return this.ingredients.stream().allMatch((ingredient) -> ingredient.isEnabled(features));
    }

    static {
        PACKET_CODEC = PacketCodec.tuple(
                SlotDisplay.PACKET_CODEC.collect(PacketCodecs.toList()),
                ShapedMoonWelderRecipeDisplay::ingredients,
                SlotDisplay.PACKET_CODEC,
                ShapedMoonWelderRecipeDisplay::result,
                SlotDisplay.PACKET_CODEC,
                ShapedMoonWelderRecipeDisplay::craftingStation,
                ShapedMoonWelderRecipeDisplay::new
        );
        SERIALIZER = new Serializer(CODEC, PACKET_CODEC);
    }
}