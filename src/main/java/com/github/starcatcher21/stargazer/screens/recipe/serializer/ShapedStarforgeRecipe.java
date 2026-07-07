package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
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

public class ShapedStarforgeRecipe
        implements StarforgeRecipe {
    final RawStarforgeShapedRecipe raw;
    final ItemStack result;
    final String group;
    final boolean showNotification;
    @Nullable
    private PlacementInfo ingredientPlacement;

    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedStarforgeRecipe(String group, RawStarforgeShapedRecipe raw, ItemStack result) {
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
        return this.result.copy();
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
        return this.result;
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
    public ItemStack assemble(StarforgeRecipeInput craftingRecipeInput, HolderLookup.Provider wrapperLookup) {
        return this.result.copy();
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

    public static class Serializer
            implements RecipeSerializer<ShapedStarforgeRecipe> {
        public static final MapCodec<ShapedStarforgeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                RawStarforgeShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
        ).apply(instance, (group, raw, result) -> new ShapedStarforgeRecipe(group, raw, result)));
        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedStarforgeRecipe> PACKET_CODEC = StreamCodec.of(ShapedStarforgeRecipe.Serializer::write, ShapedStarforgeRecipe.Serializer::read);

        @Override
        public MapCodec<ShapedStarforgeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedStarforgeRecipe> streamCodec() {
            return PACKET_CODEC;
        }

        private static ShapedStarforgeRecipe read(RegistryFriendlyByteBuf buf) {
            String string = buf.readUtf();
            RawStarforgeShapedRecipe rawShapedRecipe = (RawStarforgeShapedRecipe)RawStarforgeShapedRecipe.PACKET_CODEC.decode(buf);
            ItemStack itemStack = (ItemStack)ItemStack.STREAM_CODEC.decode(buf);
            boolean bl = buf.readBoolean();
            return new ShapedStarforgeRecipe(string, rawShapedRecipe, itemStack, bl);
        }

        private static void write(RegistryFriendlyByteBuf buf, ShapedStarforgeRecipe recipe) {
            buf.writeUtf(recipe.group);
            RawStarforgeShapedRecipe.PACKET_CODEC.encode(buf, recipe.raw);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
            buf.writeBoolean(recipe.showNotification);
        }
    }
}
