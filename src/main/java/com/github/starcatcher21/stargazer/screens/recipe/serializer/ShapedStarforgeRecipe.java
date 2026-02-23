package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ShapedStarforgeRecipe
        implements StarforgeRecipe {
    final RawStarforgeShapedRecipe raw;
    final ItemStack result;
    final String group;
    final boolean showNotification;
    @Nullable
    private IngredientPlacement ingredientPlacement;

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
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack craft(StarforgeRecipeInput craftingRecipeInput, DynamicRegistryManager registryManager) {
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
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(this.raw.getIngredients());
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
    public boolean matches(StarforgeRecipeInput craftingRecipeInput, World world) {
        if (world.isClient()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput);
    }

    @Override
    public ItemStack craft(StarforgeRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        return this.result.copy();
    }

    public int getWidth() {
        return this.raw.getWidth();
    }

    public int getHeight() {
        return this.raw.getHeight();
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }

    public static class Serializer
            implements RecipeSerializer<ShapedStarforgeRecipe> {
        public static final MapCodec<ShapedStarforgeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                RawStarforgeShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
        ).apply(instance, (group, raw, result) -> new ShapedStarforgeRecipe(group, raw, result)));
        public static final PacketCodec<RegistryByteBuf, ShapedStarforgeRecipe> PACKET_CODEC = PacketCodec.ofStatic(ShapedStarforgeRecipe.Serializer::write, ShapedStarforgeRecipe.Serializer::read);

        @Override
        public MapCodec<ShapedStarforgeRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ShapedStarforgeRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static ShapedStarforgeRecipe read(RegistryByteBuf buf) {
            String string = buf.readString();
            RawStarforgeShapedRecipe rawShapedRecipe = (RawStarforgeShapedRecipe)RawStarforgeShapedRecipe.PACKET_CODEC.decode(buf);
            ItemStack itemStack = (ItemStack)ItemStack.PACKET_CODEC.decode(buf);
            boolean bl = buf.readBoolean();
            return new ShapedStarforgeRecipe(string, rawShapedRecipe, itemStack, bl);
        }

        private static void write(RegistryByteBuf buf, ShapedStarforgeRecipe recipe) {
            buf.writeString(recipe.group);
            RawStarforgeShapedRecipe.PACKET_CODEC.encode(buf, recipe.raw);
            ItemStack.PACKET_CODEC.encode(buf, recipe.result);
            buf.writeBoolean(recipe.showNotification);
        }
    }
}
