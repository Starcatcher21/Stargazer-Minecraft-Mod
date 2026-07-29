package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ShapedStarforgeRecipe
        implements StarforgeRecipe {
    final RawStarforgeShapedRecipe raw;
    final ItemStackTemplate result;
    final String group;
    final boolean showNotification;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStackTemplate result) {
        this(group, raw, result, true);
    }

    @Override
    public RecipeSerializer<? extends StarforgeRecipe> getSerializer() {
        return RecipeTypes.STARFORGE_SERIALIZER;
    }

    @Override
    public String group() {
        return this.group;
    }

    @Override
    public ItemStack craft(StarforgeRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        return this.result.create();
    }

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return this.raw.getIngredients();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(this.raw.getIngredients());
        }
        return this.ingredientPlacement;
    }

    public ItemStack getResult() {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public boolean matches(StarforgeRecipeInput craftingRecipeInput, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput);
    }

    @Override
    public ItemStack assemble(StarforgeRecipeInput input) {
        return this.result.create();
    }

    public int getWidth() {
        return this.raw.getWidth();
    }

    public int getHeight() {
        return this.raw.getHeight();
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }

    public static final MapCodec<ShapedStarforgeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
            RawStarforgeShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
    ).apply(instance, ShapedStarforgeRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarforgeRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, recipe -> recipe.group,
            RawStarforgeShapedRecipe.PACKET_CODEC, recipe -> recipe.raw,
            ItemStackTemplate.STREAM_CODEC, recipe -> recipe.result,
            ShapedStarforgeRecipe::new
    );

    public static final RecipeSerializer<ShapedStarforgeRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
