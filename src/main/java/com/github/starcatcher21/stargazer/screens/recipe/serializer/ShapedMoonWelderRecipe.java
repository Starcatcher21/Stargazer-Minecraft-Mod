package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStackTemplate;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ShapedMoonWelderRecipe
        implements MoonWelderRecipe {
    final RawMoonWelderShapedRecipe raw;
    final ItemStackTemplate result;
    final String group;
    final boolean showNotification;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw, ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw, ItemStackTemplate result) {
        this(group, raw, result, true);
    }

    @Override
    public RecipeSerializer<? extends MoonWelderRecipe> getSerializer() {
        return RecipeTypes.MOON_WELDER_SERIALIZER;
    }

    @Override
    public String group() {
        return this.group;
    }

    @Override
    public ItemStack assemble(MoonWelderRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        return this.result.create();
    }

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return List.of(Optional.of(this.raw.getItem1()), Optional.of(this.raw.getItem2()));
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(Optional.of(this.raw.getItem1()),Optional.of(this.raw.getItem2())));
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

    public boolean matches(MoonWelderRecipeInput craftingRecipeInput, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput, world);
    }

    @Override
    public ItemStack assemble(MoonWelderRecipeInput recipeInput) {
        return this.result.create();
    }

    @Override
    public int getMoonPhase() {
        return this.raw.getMoonPhase();
    }

    public ItemStack craft(MoonWelderRecipeInput craftingRecipeInput, HolderLookup.Provider wrapperLookup) {
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

    public static final MapCodec<ShapedMoonWelderRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
            RawMoonWelderShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
    ).apply(instance, (group, raw, result) -> new ShapedMoonWelderRecipe(group, raw, result)));
    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedMoonWelderRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, recipe -> recipe.group,
            RawMoonWelderShapedRecipe.PACKET_CODEC, recipe -> recipe.raw,
            ItemStackTemplate.STREAM_CODEC, recipe -> recipe.result,
            ShapedMoonWelderRecipe::new
    );

    public static final RecipeSerializer<ShapedMoonWelderRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
