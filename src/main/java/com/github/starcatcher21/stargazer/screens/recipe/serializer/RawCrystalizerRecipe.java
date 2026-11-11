package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.CrystalizerRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public class RawCrystalizerRecipe {

    public static final MapCodec<RawCrystalizerRecipe> CODEC = Data.CODEC.flatXmap(
            RawCrystalizerRecipe::fromData,
            recipe -> recipe.data.map(DataResult::success)
                    .orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe")));

    public static final StreamCodec<RegistryFriendlyByteBuf, RawCrystalizerRecipe> PACKET_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, recipe -> recipe.width,
                    ByteBufCodecs.VAR_INT, recipe -> recipe.height,
                    Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.item1,
                    Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.item2,
                    RawCrystalizerRecipe::create);

    private final int width;
    private final int height;
    private final Optional<Data> data;
    private final Ingredient item1;
    private final Ingredient item2;

    public RawCrystalizerRecipe(int width, int height,
                                Ingredient item1, Ingredient item2,
                                Optional<Data> data) {
        this.width = width;
        this.height = height;
        this.item1 = item1;
        this.item2 = item2;
        this.data = data;
    }

    private static RawCrystalizerRecipe create(Integer width, Integer height,
                                               Ingredient item1, Ingredient item2) {
        return new RawCrystalizerRecipe(width, height, item1, item2, Optional.empty());
    }

    public static RawCrystalizerRecipe create(Ingredient item1, Ingredient item2) {
        Data data = new Data(item1, item2);
        return RawCrystalizerRecipe.fromData(data).getOrThrow();
    }

    private static DataResult<RawCrystalizerRecipe> fromData(Data data) {
        return DataResult.success(new RawCrystalizerRecipe(
                2, 1, data.item1, data.item2, Optional.of(data)));
    }

    public boolean matches(CrystalizerRecipeInput input, Level world) {
        Ingredient item1 = this.item1;
        Ingredient item2 = this.item2;

        ItemStack stack;
        ItemStack stack2;
        try { stack  = input.getItem(0); }
        catch (Exception e) { stack = new ItemStack(Blocks.AIR.asItem()); }
        try { stack2 = input.getItem(1); }
        catch (Exception e) { stack2 = new ItemStack(Blocks.AIR.asItem()); }

        //? if >= 26.2 {
        return Ingredient.testOptionalIngredient(Optional.of(item1), stack)
                && Ingredient.testOptionalIngredient(Optional.of(item2), stack2);
        //?} else {
        /*return testOptionalIngredient(Optional.of(item1), stack)
                && testOptionalIngredient(Optional.of(item2), stack2);
        *///?}
    }

    private static boolean testOptionalIngredient(Optional<Ingredient> optional, ItemStack stack) {
        return optional.map(ingredient -> ingredient.test(stack)).orElse(true);
    }

    public int getWidth()  { return this.width; }
    public int getHeight() { return this.height; }
    public Ingredient getItem1() { return this.item1; }
    public Ingredient getItem2() { return this.item2; }

    public record Data(Ingredient item1, Ingredient item2) {
        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("item1").forGetter(Data::item1),
                Ingredient.CODEC.fieldOf("item2").forGetter(Data::item2)
        ).apply(instance, Data::new));
    }
}
