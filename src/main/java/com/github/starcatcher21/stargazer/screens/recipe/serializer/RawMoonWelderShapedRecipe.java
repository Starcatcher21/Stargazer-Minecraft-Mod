package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipeInput;
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

public class RawMoonWelderShapedRecipe {
    public static final MapCodec<RawMoonWelderShapedRecipe> CODEC = Data.CODEC.flatXmap(
            RawMoonWelderShapedRecipe::fromData,
            recipe -> recipe.data.map(DataResult::success).orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe")));
    public static final PacketCodec<RegistryByteBuf, RawMoonWelderShapedRecipe> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, recipe -> recipe.width,
            PacketCodecs.VAR_INT, recipe -> recipe.height,
            Ingredient.PACKET_CODEC, recipe -> recipe.item1,
            Ingredient.PACKET_CODEC, recipe -> recipe.item2,
            PacketCodecs.INTEGER, recipe -> recipe.moonPhase,
            RawMoonWelderShapedRecipe::create);
    private static final Logger log = LoggerFactory.getLogger(RawMoonWelderShapedRecipe.class);
    private final int width;
    private final int height;
    private final Optional<Data> data;
    private final Ingredient item1;
    private final Ingredient item2;
    private final int moonPhase;


    public RawMoonWelderShapedRecipe(int width, int height, Ingredient item1, Ingredient item2, int moonPhase, Optional<Data> data) {
        this.width = width;
        this.height = height;
        this.item1 = item1;
        this.item2 = item2;
        this.data = data;
        this.moonPhase = moonPhase;
    }

    private static RawMoonWelderShapedRecipe create(Integer width, Integer height, Ingredient item1, Ingredient item2, int moonPhase) {
        return new RawMoonWelderShapedRecipe(width, height, item1, item2, moonPhase, Optional.empty());
    }

    public static RawMoonWelderShapedRecipe create(Ingredient item1, Ingredient item2, int MoonPhase) {
        Data data = new Data(item1, item2, MoonPhase);
        return RawMoonWelderShapedRecipe.fromData(data).getOrThrow();
    }


    private static DataResult<RawMoonWelderShapedRecipe> fromData(Data data) {
        return DataResult.success(new RawMoonWelderShapedRecipe(2, 1, data.item1, data.item2, data.moonPhase, Optional.of(data)));
    }

    public boolean matches(MoonWelderRecipeInput input, World world) {
        Ingredient item1 = this.item1;
        Ingredient item2 = this.item2;
        ItemStack stack;
        ItemStack stack2;
        try {
            stack = input.getStackInSlot(0);
        } catch (Exception e) {
            stack = new ItemStack(Blocks.AIR.asItem());
        }
        try {
            stack2 = input.getStackInSlot(1);
        } catch (Exception e) {
            stack2 = new ItemStack(Blocks.AIR.asItem());
        }
        if (this.getMoonPhase() > 7) {
            if (world.isNight()) return false;
            return Ingredient.matches(Optional.of(item1), stack) && Ingredient.matches(Optional.of(item2), stack2);
        }
//        if (this.getMoonPhase() != world.getMoonPhase() || world.isDay()) return false;
        return Ingredient.matches(Optional.of(item1), stack) && Ingredient.matches(Optional.of(item2), stack2);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Ingredient getItem1() {
        return this.item1;
    }

    public Ingredient getItem2() {
        return this.item2;
    }

    public int getMoonPhase() {
        return this.moonPhase;
    }

    public record Data(Ingredient item1, Ingredient item2, int moonPhase) {
        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("item1").forGetter(Data::item1),
                Ingredient.CODEC.fieldOf("item2").forGetter(Data::item2),
                Codecs.NON_NEGATIVE_INT.fieldOf("moon_phase").forGetter(Data::moonPhase)
        ).apply(instance, Data::new));
    }

}
