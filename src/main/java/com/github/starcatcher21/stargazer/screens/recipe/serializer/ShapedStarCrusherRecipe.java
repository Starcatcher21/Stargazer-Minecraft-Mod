package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.*;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
    final ItemStack result;
    final String group;
    final boolean showNotification;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw, ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarCrusherRecipe(String group, StarCrusherShapedRecipe raw, ItemStack result) {
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
        return this.result.copy();
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
    public ItemStack assemble(StarCrusherRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
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
        return this.result;
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }

    public static class Serializer
            implements RecipeSerializer<ShapedStarCrusherRecipe> {
        public static final MapCodec<ShapedStarCrusherRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                StarCrusherShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
        ).apply(instance, (group, raw, result) -> new ShapedStarCrusherRecipe(group, raw, result)));
        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarCrusherRecipe> PACKET_CODEC = StreamCodec.of(ShapedStarCrusherRecipe.Serializer::write, ShapedStarCrusherRecipe.Serializer::read);

        @Override
        public MapCodec<ShapedStarCrusherRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedStarCrusherRecipe> streamCodec() {
            return PACKET_CODEC;
        }

        private static ShapedStarCrusherRecipe read(RegistryFriendlyByteBuf buf) {
            String string = buf.readUtf();
            StarCrusherShapedRecipe rawShapedRecipe = (StarCrusherShapedRecipe)StarCrusherShapedRecipe.PACKET_CODEC.decode(buf);
            ItemStack itemStack = (ItemStack)ItemStack.STREAM_CODEC.decode(buf);
            boolean bl = buf.readBoolean();
            return new ShapedStarCrusherRecipe(string, rawShapedRecipe, itemStack, bl);
        }

        private static void write(RegistryFriendlyByteBuf buf, ShapedStarCrusherRecipe recipe) {
            buf.writeUtf(recipe.group);
            StarCrusherShapedRecipe.PACKET_CODEC.encode(buf, recipe.raw);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
            buf.writeBoolean(recipe.showNotification);
        }
    }
}
