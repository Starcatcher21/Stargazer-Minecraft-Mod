package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.resource.featuretoggle.FeatureSet;

public record ShapedMoonWelderRecipeDisplay(SlotDisplay item1, SlotDisplay item2, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<ShapedMoonWelderRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.fieldOf("item1").forGetter(ShapedMoonWelderRecipeDisplay::item1),
            SlotDisplay.CODEC.fieldOf("item2").forGetter(ShapedMoonWelderRecipeDisplay::item2),
            SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedMoonWelderRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedMoonWelderRecipeDisplay::craftingStation))
            .apply(instance, ShapedMoonWelderRecipeDisplay::new));
    public static final PacketCodec<RegistryByteBuf, ShapedMoonWelderRecipeDisplay> PACKET_CODEC;
    public static final Serializer<ShapedMoonWelderRecipeDisplay> SERIALIZER;

    public ShapedMoonWelderRecipeDisplay {
    }

    public Serializer<ShapedMoonWelderRecipeDisplay> serializer() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureSet features) {
        return true;
    }

    static {
        PACKET_CODEC = PacketCodec.tuple(
                SlotDisplay.PACKET_CODEC, // <-- Fixed here
                ShapedMoonWelderRecipeDisplay::item1,
                SlotDisplay.PACKET_CODEC,
                ShapedMoonWelderRecipeDisplay::item2,
                SlotDisplay.PACKET_CODEC,
                ShapedMoonWelderRecipeDisplay::result,
                SlotDisplay.PACKET_CODEC,
                ShapedMoonWelderRecipeDisplay::craftingStation,
                ShapedMoonWelderRecipeDisplay::new
        );
        SERIALIZER = new Serializer(CODEC, PACKET_CODEC);
    }
}