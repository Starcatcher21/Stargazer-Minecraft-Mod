// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.resource.featuretoggle.FeatureSet;

public record StarCrusherRecipeDisplay(SlotDisplay item1, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<StarCrusherRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.fieldOf("item1").forGetter(StarCrusherRecipeDisplay::item1),
            SlotDisplay.CODEC.fieldOf("result").forGetter(StarCrusherRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(StarCrusherRecipeDisplay::craftingStation))
            .apply(instance, StarCrusherRecipeDisplay::new));
    public static final PacketCodec<RegistryByteBuf, StarCrusherRecipeDisplay> PACKET_CODEC;
    public static final Serializer<StarCrusherRecipeDisplay> SERIALIZER;

    public StarCrusherRecipeDisplay {
    }

    public Serializer<StarCrusherRecipeDisplay> serializer() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureSet features) {
        return true;
    }

    static {
        PACKET_CODEC = PacketCodec.tuple(
                SlotDisplay.PACKET_CODEC, // <-- Fixed here
                StarCrusherRecipeDisplay::item1,
                SlotDisplay.PACKET_CODEC,
                StarCrusherRecipeDisplay::result,
                SlotDisplay.PACKET_CODEC,
                StarCrusherRecipeDisplay::craftingStation,
                StarCrusherRecipeDisplay::new
        );
        SERIALIZER = new Serializer(CODEC, PACKET_CODEC);
    }
}