package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.*;
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
import net.minecraft.world.level.block.Blocks;

public class ShapedStarCrusherRecipe
        implements StarCrusherRecipe {
    final StarCrusherShapedRecipe raw;
    final ItemStackTemplate result;
    final String group;
    final boolean showNotification;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw, ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw, ItemStackTemplate result) {
        this(group, raw, result, true);
    }

    @Override
    public RecipeSerializer<? extends StarCrusherRecipe> getSerializer() {
        return RecipeTypes.STAR_CRUSHER_SERIALIZER;
    }

    @Override
    public String group() {
        return this.group;
    }

    public ItemStack craft(StarCrusherRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        return this.result.create();
    }

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

    @Override
    public ItemStack assemble(StarCrusherRecipeInput input) {
        return this.result.create();
    }

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return List.of(Optional.of(this.raw.getItem1()));
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(Optional.of(this.raw.getItem1())));
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
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }

    public static final MapCodec<ShapedStarCrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
            StarCrusherShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
    ).apply(instance, (group, raw, result) -> new ShapedStarCrusherRecipe(group, raw, result)));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarCrusherRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, recipe -> recipe.group,
            StarCrusherShapedRecipe.PACKET_CODEC, recipe -> recipe.raw,
            ItemStackTemplate.STREAM_CODEC, recipe -> recipe.result,
            ShapedStarCrusherRecipe::new
    );

    public static final RecipeSerializer<ShapedStarCrusherRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
