// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record ShapedMoonWelderRecipeDisplay(SlotDisplay item1, SlotDisplay item2, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<ShapedMoonWelderRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.fieldOf("item1").forGetter(ShapedMoonWelderRecipeDisplay::item1),
            SlotDisplay.CODEC.fieldOf("item2").forGetter(ShapedMoonWelderRecipeDisplay::item2),
            SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedMoonWelderRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedMoonWelderRecipeDisplay::craftingStation))
            .apply(instance, ShapedMoonWelderRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedMoonWelderRecipeDisplay> STREAM_CODEC;
    public static final Type<ShapedMoonWelderRecipeDisplay> SERIALIZER;

    public ShapedMoonWelderRecipeDisplay {
    }

    public Type<ShapedMoonWelderRecipeDisplay> type() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureFlagSet features) {
        return true;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(
                SlotDisplay.STREAM_CODEC, // <-- Fixed here
                ShapedMoonWelderRecipeDisplay::item1,
                SlotDisplay.STREAM_CODEC,
                ShapedMoonWelderRecipeDisplay::item2,
                SlotDisplay.STREAM_CODEC,
                ShapedMoonWelderRecipeDisplay::result,
                SlotDisplay.STREAM_CODEC,
                ShapedMoonWelderRecipeDisplay::craftingStation,
                ShapedMoonWelderRecipeDisplay::new
        );
        SERIALIZER = new Type(CODEC, STREAM_CODEC);
    }
}