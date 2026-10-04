package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.ByteBufCodecs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
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
*///? }
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ShapedMoonWelderRecipe implements MoonWelderRecipe {

    final RawMoonWelderShapedRecipe raw;
    final String group;
    final boolean showNotification;

    /* ---------------- 26.2+ ------------------------------------------ */
    //? if >= 26.2 {
    /*final ItemStackTemplate result;
    @Nullable private PlacementInfo ingredientPlacement;

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw,
                                  ItemStackTemplate result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw,
                                  ItemStackTemplate result) {
        this(group, raw, result, true);
    }
    *///?} else {
    /* ---------------- 1.21.1 ----------------------------------------- */
    final ItemStack result;

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw,
                                  ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw,
                                  ItemStack result) {
        this(group, raw, result, true);
    }
    //?}

    @Override
    public RecipeSerializer<? extends MoonWelderRecipe> getSerializer() {
        return RecipeTypes.MOON_WELDER_SERIALIZER.get();
    }


    //? if >= 26.2 {
    /*@Override
    *///? }
    public String group() {
        return this.group;
    }

    /* ---------------- assemble --------------------------------------- */
    @Override
    public ItemStack assemble(MoonWelderRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        //? if >= 26.2 {
        /*return this.result.create();
        *///? } else {
        return this.result.copy();
        //? }
    }

    //? if >= 26.2 {
    /*@Override
    public ItemStack assemble(MoonWelderRecipeInput recipeInput) {
        return this.result.create();
    }
    *///?} else {
    @Override
    public ItemStack assemble(MoonWelderRecipeInput craftingRecipeInput,
                              HolderLookup.Provider registryManager) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }
    //?}

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    /* ---------------- ingredients ------------------------------------ */
    //? if >= 26.2 {
    /*@VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return List.of(Optional.of(this.raw.getItem1()),
                       Optional.of(this.raw.getItem2()));
    }
    *///?} else {
    // 1.21.1's Recipe interface declares NonNullList<Ingredient> getIngredients(),
    // so we cannot name our optional-list method getIngredients() on this branch.
    @VisibleForTesting
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
    //?}

    /* ---------------- placement (26.2 only) -------------------------- */
    //? if >= 26.2 {
    /*@Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(
                List.of(Optional.of(this.raw.getItem1()),
                        Optional.of(this.raw.getItem2())));
        }
        return this.ingredientPlacement;
    }
    *///?}

    /* ---------------- result ----------------------------------------- */
    //? if >= 26.2 {
    /*public ItemStack getResult() {
        return this.result.create();
    }
    *///?} else {
    public ItemStack getResult() {
        return this.result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result.copy();
    }
    //?}

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public boolean matches(MoonWelderRecipeInput craftingRecipeInput, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput, world);
    }

    @Override
    public int getMoonPhase() {
        return this.raw.getMoonPhase();
    }

    public ItemStack craft(MoonWelderRecipeInput craftingRecipeInput,
                           HolderLookup.Provider wrapperLookup) {
        //? if >= 26.2 {
        /*return this.result.create();
         *///?} else {
        return this.result.copy();
        //?}
    }

    public int getWidth() {
        return this.raw.getWidth();
    }

    public int getHeight() {
        return this.raw.getHeight();
    }

    //? if >= 26.2 {
    /*@Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }
    *///?}

    /* ---------------- codecs ----------------------------------------- */
    //? if >= 26.2 {
    /*public static final MapCodec<ShapedMoonWelderRecipe> CODEC =
        RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
            RawMoonWelderShapedRecipe.CODEC.forGetter(r -> r.raw),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.result)
        ).apply(instance, ShapedMoonWelderRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedMoonWelderRecipe> STREAM_CODEC =
        StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,            r -> r.group,
            RawMoonWelderShapedRecipe.PACKET_CODEC, r -> r.raw,
            ItemStackTemplate.STREAM_CODEC,       r -> r.result,
            ShapedMoonWelderRecipe::new
        );
    *///?} else {
    public static final MapCodec<ShapedMoonWelderRecipe> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                    RawMoonWelderShapedRecipe.CODEC.forGetter(r -> r.raw),
                    ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result)
            ).apply(instance, ShapedMoonWelderRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedMoonWelderRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,            r -> r.group,
                    RawMoonWelderShapedRecipe.PACKET_CODEC, r -> r.raw,
                    ItemStack.STREAM_CODEC,               r -> r.result,
                    ShapedMoonWelderRecipe::new
            );
    //?}

    public static final RecipeSerializer<ShapedMoonWelderRecipe> SERIALIZER =
            new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
