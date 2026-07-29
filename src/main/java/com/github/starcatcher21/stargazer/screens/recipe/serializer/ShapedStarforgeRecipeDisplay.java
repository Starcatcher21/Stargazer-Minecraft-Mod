package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record ShapedStarforgeRecipeDisplay(List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<ShapedStarforgeRecipeDisplay> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            SlotDisplay.CODEC.listOf().fieldOf("ingredients").forGetter(ShapedStarforgeRecipeDisplay::ingredients),
            SlotDisplay.CODEC.fieldOf("result").forGetter(ShapedStarforgeRecipeDisplay::result),
            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(ShapedStarforgeRecipeDisplay::craftingStation))
            .apply(instance, ShapedStarforgeRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarforgeRecipeDisplay> STREAM_CODEC;
    public static final RecipeDisplay.Type<ShapedStarforgeRecipeDisplay> SERIALIZER;

    public ShapedStarforgeRecipeDisplay {
        if (ingredients.size() != 14) {
            throw new IllegalArgumentException("Invalid shaped recipe display contents");
        }
    }

    public RecipeDisplay.Type<ShapedStarforgeRecipeDisplay> type() {
        return SERIALIZER;
    }

    public boolean isEnabled(FeatureFlagSet features) {
        return this.ingredients.stream().allMatch((ingredient) -> ingredient.isEnabled(features));
    }

    static {
        STREAM_CODEC = StreamCodec.composite(
                SlotDisplay.STREAM_CODEC.apply(ByteBufCodecs.list()),
                ShapedStarforgeRecipeDisplay::ingredients,
                SlotDisplay.STREAM_CODEC,
                ShapedStarforgeRecipeDisplay::result,
                SlotDisplay.STREAM_CODEC,
                ShapedStarforgeRecipeDisplay::craftingStation,
                ShapedStarforgeRecipeDisplay::new
        );
        SERIALIZER = new RecipeDisplay.Type(CODEC, STREAM_CODEC);
    }
}