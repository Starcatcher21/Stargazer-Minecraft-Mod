package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.*;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.List;
import java.util.Optional;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
//? if >= 26.2 {
/*import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.jspecify.annotations.Nullable;
*///?}
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ShapedStarCrusherRecipe implements StarCrusherRecipe {
    final StarCrusherShapedRecipe raw;
    final String group;
    final boolean showNotification;

    //? if >= 26.2 {
    /*final ItemStackTemplate result;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw,
                                   ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw,
                                   ItemStackTemplate result) {
        this(group, raw, result, true);
    }
    *///?} else {
    final ItemStack result;

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw,
                                   ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw,
                                   ItemStack result) {
        this(group, raw, result, true);
    }
    //?}

    @Override
    public RecipeSerializer<? extends StarCrusherRecipe> getSerializer() {
        return RecipeTypes.STAR_CRUSHER_SERIALIZER.get();
    }

    //? if >= 26.2 {
    /*@Override
    *///? }
    public String group() {
        return this.group;
    }

    //? if >= 26.2 {
    /*public ItemStack craft(StarCrusherRecipeInput craftingRecipeInput,
                           RegistryAccess registryManager) {
        return this.result.create();
    }
    *///?} else {
    public ItemStack craft(StarCrusherRecipeInput craftingRecipeInput,
                           RegistryAccess registryManager) {
        return this.result.copy();     // 1.21.1: ItemStack is already concrete
    }
    //?}

    @Override
    public boolean matches(StarCrusherRecipeInput recipeInput, Level world) {
        Ingredient item1 = this.raw.getItem1();
        ItemStack stack;
        try {
            stack = recipeInput.getStacks().getFirst();
        } catch (Exception e) {
            stack = new ItemStack(Blocks.AIR.asItem());
        }
        return Ingredient.testOptionalIngredient(Optional.of(item1), stack);
    }

    //? if >= 26.2 {
    /*@Override
    public ItemStack assemble(StarCrusherRecipeInput input) {
        return this.result.create();
    }
    *///?} else {
    @Override
    public ItemStack assemble(StarCrusherRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider arg) {
        return this.result.copy();
    }
    //?}

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    //? if >= 26.2 {
    /*@VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return List.of(Optional.of(this.raw.getItem1()));
    }
    *///? } else {
    @VisibleForTesting
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(this.raw.getItem1());
    }
    //? }

    //? if >= 26.2 {
    /*@Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(
                    List.of(Optional.of(this.raw.getItem1())));
        }
        return this.ingredientPlacement;
    }
    *///?}

    //? if >= 26.2 {
    /*public ItemStack getResult() {
        return this.result.create();
    }
    *///?} else {
    public ItemStack getResult() {
        return this.result.copy();
    }
    //?}

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    //? if >= 26.2 {
    /*@Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }
    *///?}

    /* -------------------- codecs ------------------------------------- */

    //? if >= 26.2 {
    /*public static final MapCodec<ShapedStarCrusherRecipe> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                    StarCrusherShapedRecipe.CODEC.forGetter(r -> r.raw),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result)
            ).apply(instance, ShapedStarCrusherRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarCrusherRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,  r -> r.group,
                    StarCrusherShapedRecipe.PACKET_CODEC, r -> r.raw,
                    ItemStackTemplate.STREAM_CODEC, r -> r.result,
                    ShapedStarCrusherRecipe::new
            );

    public static final RecipeSerializer<ShapedStarCrusherRecipe> SERIALIZER =
            new RecipeSerializer<>(CODEC, STREAM_CODEC);
    *///?} else {
    public static final MapCodec<ShapedStarCrusherRecipe> CODEC =
        RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
            StarCrusherShapedRecipe.CODEC.forGetter(r -> r.raw),
            ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result)
        ).apply(instance, ShapedStarCrusherRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarCrusherRecipe> STREAM_CODEC =
        StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,  r -> r.group,
            StarCrusherShapedRecipe.PACKET_CODEC, r -> r.raw,
            ItemStack.STREAM_CODEC,     r -> r.result,
            ShapedStarCrusherRecipe::new
        );

    // 1.21.1 RecipeSerializer has a different construction path.
    public static final RecipeSerializer<ShapedStarCrusherRecipe> SERIALIZER =
        new RecipeSerializer<>(CODEC, STREAM_CODEC);
    //?}
}
