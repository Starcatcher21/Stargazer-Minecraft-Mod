package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.*;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

//? if >= 26.2 {
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.jetbrains.annotations.Nullable;

//? }

public class ShapedCrystalizerRecipe implements CrystalizerRecipe {

    final RawCrystalizerRecipe raw;
    final String group;
    final boolean showNotification;

    /* ---------------- 26.2+ ------------------------------------------ */
    //? if >= 26.2 {
    final ItemStackTemplate result;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedCrystalizerRecipe(String group, RawCrystalizerRecipe raw,
                                  ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedCrystalizerRecipe(String group, RawCrystalizerRecipe raw,
                                  ItemStackTemplate result) {
        this(group, raw, result, true);
    }
    //?} else {
    /*/^ ---------------- 1.21.1 ----------------------------------------- ^/
    final ItemStack result;

    public ShapedCrystalizerRecipe(String group, RawCrystalizerRecipe raw,
                                   ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedCrystalizerRecipe(String group, RawCrystalizerRecipe raw,
                                   ItemStack result) {
        this(group, raw, result, true);
    }
    *///?}

    @Override
    public RecipeSerializer<? extends CrystalizerRecipe> getSerializer() {
        return RecipeTypes.CRYSTALIZER_SERIALIZER.get();
    }


    //? if >= 26.2 {
    @Override
    //? }
    public String group() {
        return this.group;
    }

    /* ---------------- assemble --------------------------------------- */
    @Override
    public ItemStack craft(CrystalizerRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        //? if >= 26.2 {
        return this.result.create();
        //? } else {
        /*return this.result.copy();
        *///? }
    }

    //? if >= 26.2 {
    @Override
    public ItemStack assemble(CrystalizerRecipeInput recipeInput) {
        return this.result.create();
    }
    //?} else {
    /*@Override
    public ItemStack assemble(CrystalizerRecipeInput craftingRecipeInput,
                              HolderLookup.Provider registryManager) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }
    *///?}

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    /* ---------------- ingredients ------------------------------------ */
    //? if >= 26.2 {
    @VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return List.of(Optional.of(this.raw.getItem1()),
                       Optional.of(this.raw.getItem2()));
    }
    //?} else {
    /*@VisibleForTesting
    public List<Optional<Ingredient>> getOptionalIngredients() {
        return List.of(Optional.of(this.raw.getItem1()),
                Optional.of(this.raw.getItem2()));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.raw.getItem1());
        list.add(this.raw.getItem2());
        return list;
    }
    *///?}

    /* ---------------- placement (26.2 only) -------------------------- */
    //? if >= 26.2 {
    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(
                List.of(Optional.of(this.raw.getItem1()),
                        Optional.of(this.raw.getItem2())));
        }
        return this.ingredientPlacement;
    }
    //?}

    /* ---------------- result ----------------------------------------- */
    //? if >= 26.2 {
    public ItemStack getResult() {
        return this.result.create();
    }
    //?} else {
    /*public ItemStack getResult() {
        return this.result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result.copy();
    }
    *///?}

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public boolean matches(CrystalizerRecipeInput craftingRecipeInput, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput, world);
    }

    public ItemStack craft(CrystalizerRecipeInput craftingRecipeInput,
                           HolderLookup.Provider wrapperLookup) {
        //? if >= 26.2 {
        return this.result.create();
         //?} else {
        /*return this.result.copy();
        *///?}
    }

    public int getWidth() {
        return this.raw.getWidth();
    }

    public int getHeight() {
        return this.raw.getHeight();
    }

    //? if >= 26.2 {
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }
    //?}

    /* ---------------- codecs ----------------------------------------- */
    //? if >= 26.2 {
    public static final MapCodec<ShapedCrystalizerRecipe> CODEC =
        RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
            RawCrystalizerRecipe.CODEC.forGetter(r -> r.raw),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result)
        ).apply(instance, ShapedCrystalizerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedCrystalizerRecipe> STREAM_CODEC =
        StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,            r -> r.group,
            RawCrystalizerRecipe.PACKET_CODEC, r -> r.raw,
            ItemStackTemplate.STREAM_CODEC,       r -> r.result,
            ShapedCrystalizerRecipe::new
        );
    //?} else {
    /*public static final MapCodec<ShapedCrystalizerRecipe> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                    RawCrystalizerRecipe.CODEC.forGetter(r -> r.raw),
                    ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result)
            ).apply(instance, ShapedCrystalizerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedCrystalizerRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,            r -> r.group,
                    RawCrystalizerRecipe.PACKET_CODEC, r -> r.raw,
                    ItemStack.STREAM_CODEC,               r -> r.result,
                    ShapedCrystalizerRecipe::new
            );
    *///?}

    //? if >= 26.2 {
    public static final RecipeSerializer<ShapedCrystalizerRecipe> SERIALIZER =
            new RecipeSerializer<>(CODEC, STREAM_CODEC);
    //?} else {
    /*public static final RecipeSerializer<ShapedCrystalizerRecipe> SERIALIZER = new RecipeSerializer<>() {
        @Override
        public MapCodec<ShapedCrystalizerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedCrystalizerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    };
    *///?}
}
