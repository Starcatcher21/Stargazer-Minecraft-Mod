package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ShapedMoonWelderRecipe
        implements MoonWelderRecipe {
    final RawMoonWelderShapedRecipe raw;
    final ItemStack result;
    final String group;
    final boolean showNotification;
    @Nullable
    private IngredientPlacement ingredientPlacement;

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw, ItemStack result, boolean showNotification) {
        this.group = group;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    public ShapedMoonWelderRecipe(String group, RawMoonWelderShapedRecipe raw, ItemStack result) {
        this(group, raw, result, true);
    }

    @Override
    public RecipeSerializer<? extends MoonWelderRecipe> getSerializer() {
        return RecipeTypes.MOON_WELDER_SERIALIZER;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public ItemStack craft(MoonWelderRecipeInput craftingRecipeInput, DynamicRegistryManager registryManager) {
        return this.result.copy();
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
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(Optional.of(this.raw.getItem1()),Optional.of(this.raw.getItem2())));
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

    public boolean matches(MoonWelderRecipeInput craftingRecipeInput, World world) {
        if (world.isClient()) {
            return false;
        }
        return this.raw.matches(craftingRecipeInput, world);
    }

    @Override
    public int getMoonPhase() {
        return this.raw.getMoonPhase();
    }

    public ItemStack craft(MoonWelderRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
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
            implements RecipeSerializer<ShapedMoonWelderRecipe> {
        public static final MapCodec<ShapedMoonWelderRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                RawMoonWelderShapedRecipe.CODEC.forGetter(recipe -> recipe.raw),
                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
        ).apply(instance, (group, raw, result) -> new ShapedMoonWelderRecipe(group, raw, result)));
        public static final PacketCodec<RegistryByteBuf, ShapedMoonWelderRecipe> PACKET_CODEC = PacketCodec.ofStatic(ShapedMoonWelderRecipe.Serializer::write, ShapedMoonWelderRecipe.Serializer::read);

        @Override
        public MapCodec<ShapedMoonWelderRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ShapedMoonWelderRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static ShapedMoonWelderRecipe read(RegistryByteBuf buf) {
            String string = buf.readString();
            RawMoonWelderShapedRecipe rawShapedRecipe = (RawMoonWelderShapedRecipe)RawMoonWelderShapedRecipe.PACKET_CODEC.decode(buf);
            ItemStack itemStack = (ItemStack)ItemStack.PACKET_CODEC.decode(buf);
            boolean bl = buf.readBoolean();
            return new ShapedMoonWelderRecipe(string, rawShapedRecipe, itemStack, bl);
        }

        private static void write(RegistryByteBuf buf, ShapedMoonWelderRecipe recipe) {
            buf.writeString(recipe.group);
            RawMoonWelderShapedRecipe.PACKET_CODEC.encode(buf, recipe.raw);
            ItemStack.PACKET_CODEC.encode(buf, recipe.result);
            buf.writeBoolean(recipe.showNotification);
        }
    }
}
