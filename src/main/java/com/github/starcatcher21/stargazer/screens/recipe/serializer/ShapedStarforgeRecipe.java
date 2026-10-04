package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.ByteBufCodecs;
//? if >= 26.2 {

/*import net.minecraft.world.item.ItemStackTemplate;

*///? }
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
    //? if >= 26.2 {
    
    /*final ItemStackTemplate result;
    
    *///? } else {
    final ItemStack result;
    //? }
    final String group;
    final boolean showNotification;
    //? if >= 26.2 {
    /*@Nullable
    private PlacementInfo ingredientPlacement;
    *///? }

    //? if >= 26.2 {
    
    /*public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStackTemplate result, boolean showNotification) {
    
    *///? } else {
    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStack result, boolean showNotification) {
        //? }
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    //? if >= 26.2 {
    
    /*public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStackTemplate result) {
    
    *///? } else {
    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStack result) {
        //? }
        this(group, raw, result, true);
    }

    @Override
    public RecipeSerializer<? extends StarforgeRecipe> getSerializer() {
        return RecipeTypes.STARFORGE_SERIALIZER.get();
    }

    //? if >= 26.2 {
    /*@Override
    *///? }
    public String group() {
        return this.group;
    }

    @Override
    public ItemStack craft(StarforgeRecipeInput craftingRecipeInput, RegistryAccess registryManager) {
        //? if >= 26.2 {
        
        /*return this.result.create();
        
        *///? } else {
        return this.result.copy();
        //? }
    }

    @Override
    public List<ItemStack> getHeldStacks() {
        return List.of();
    }

    //? if >= 26.2 {
    /*@VisibleForTesting
    public List<Optional<Ingredient>> getIngredients() {
        return this.raw.getIngredients();
    }
    *///? } else {
    @VisibleForTesting
    public NonNullList<Ingredient> getIngredients() {
        return this.raw.getIngredients();
    }
    //? }

    //? if >= 26.2 {
    /*@Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(this.raw.getIngredients());
        }
        return this.ingredientPlacement;
    }
    *///? }


    public ItemStack getResult() {
        //? if >= 26.2 {
        
        /*return this.result.create();
        
        *///? } else {
        return this.result.copy();
        //? }
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


    //? if 26.2 {
    //? } else {
    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider arg) {
        return this.getResult();
    }
    //? }

    @Override
    public ItemStack assemble(StarforgeRecipeInput input
            //? if < 26.2 {
            , HolderLookup.Provider holderLookupProvider
            //? }
    ) {
        //? if >= 26.2 {
        
        /*return this.result.create();
        
        *///? } else {
        return this.result.copy();
        //? }
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
    *///? }

    public static final MapCodec<ShapedStarforgeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
            RawStarforgeShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
            //? if >= 26.2 {
            
            /*ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            
            *///? } else {
            ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
            //? }
    ).apply(instance, ShapedStarforgeRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarforgeRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, recipe -> recipe.group,
            RawStarforgeShapedRecipe.PACKET_CODEC, recipe -> recipe.raw,
            //? if >= 26.2 {
            
            /*ItemStackTemplate.STREAM_CODEC, recipe -> recipe.result,
            
            *///? } else {
            ItemStack.STREAM_CODEC, recipe -> recipe.result,
            //? }
            ShapedStarforgeRecipe::new
    );

    public static final RecipeSerializer<ShapedStarforgeRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);
}
