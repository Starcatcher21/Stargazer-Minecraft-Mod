// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
import com.github.starcatcher21.stargazer.screens.recipe.StarCrusherRecipeInput;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class StarCrusherShapedRecipe {
    public static final MapCodec<StarCrusherShapedRecipe> CODEC = Data.CODEC.flatXmap(
            StarCrusherShapedRecipe::fromData,
            recipe -> recipe.data.map(DataResult::success).orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe")));
    public static final PacketCodec<RegistryByteBuf, StarCrusherShapedRecipe> PACKET_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, recipe -> recipe.item1,
            StarCrusherShapedRecipe::create);
    private static final Logger log = LoggerFactory.getLogger(StarCrusherShapedRecipe.class);
    private final Optional<Data> data;
    private final Ingredient item1;


    public StarCrusherShapedRecipe(Ingredient item1, Optional<Data> data) {
        this.item1 = item1;
        this.data = data;
    }

    public static StarCrusherShapedRecipe create(Ingredient item1) {
        Data data = new Data(item1);
        return StarCrusherShapedRecipe.fromData(data).getOrThrow();
    }


    private static DataResult<StarCrusherShapedRecipe> fromData(Data data) {
        return DataResult.success(new StarCrusherShapedRecipe(data.item1, Optional.of(data)));
    }

    public boolean matches(StarCrusherRecipeInput input, World world) {
        Ingredient item1 = this.item1;
        ItemStack stack;
        try {
            stack = input.getStackInSlot(0);
        } catch (Exception e) {
            stack = new ItemStack(Blocks.AIR.asItem());
        }
        return Ingredient.matches(Optional.of(item1), stack);
    }

    public Ingredient getItem1() {
        return this.item1;
    }

    public record Data(Ingredient item1) {
        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("item1").forGetter(Data::item1)
        ).apply(instance, Data::new));
    }

}
