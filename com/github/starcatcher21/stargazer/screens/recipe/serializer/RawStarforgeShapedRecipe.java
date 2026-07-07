package com.github.starcatcher21.stargazer.screens.recipe.serializer;

import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipeInput;
import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.dynamic.Codecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RawStarforgeShapedRecipe {
    private static final int MAX_WIDTH_AND_HEIGHT = 5;
    public static final char SPACE = ' ';
    public static final MapCodec<RawStarforgeShapedRecipe> CODEC = Data.CODEC.flatXmap(
            RawStarforgeShapedRecipe::fromData,
            recipe -> recipe.data.map(DataResult::success).orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe")));
    public static final PacketCodec<RegistryByteBuf, RawStarforgeShapedRecipe> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.VAR_INT,
            recipe -> recipe.width, PacketCodecs.VAR_INT,
            recipe -> recipe.height, Ingredient.OPTIONAL_PACKET_CODEC.collect(PacketCodecs.toList()),
            recipe -> recipe.ingredients, RawStarforgeShapedRecipe::create);
    private static final Logger log = LoggerFactory.getLogger(RawStarforgeShapedRecipe.class);
    private final int width;
    private final int height;
    private final List<Optional<Ingredient>> ingredients;
    private final Optional<Data> data;
    private final int ingredientCount;
    private final boolean symmetrical;


    public RawStarforgeShapedRecipe(int width, int height, List<Optional<Ingredient>> ingredients, Optional<Data> data) {
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.data = data;
        this.ingredientCount = (int)ingredients.stream().flatMap(Optional::stream).count();
        this.symmetrical = false;
    }

    private static RawStarforgeShapedRecipe create(Integer width, Integer height, List<Optional<Ingredient>> ingredients) {
        return new RawStarforgeShapedRecipe(width, height, ingredients, Optional.empty());
    }

    public static RawStarforgeShapedRecipe create(Map<Character, Ingredient> key, String ... pattern) {
        return RawStarforgeShapedRecipe.create(key, List.of(pattern));
    }

    public static RawStarforgeShapedRecipe create(Map<Character, Ingredient> key, List<String> pattern) {
        Data data = new Data(key, pattern);
        return RawStarforgeShapedRecipe.fromData(data).getOrThrow();
    }


    private static DataResult<RawStarforgeShapedRecipe> fromData(Data data) {
        String[] strings = RawStarforgeShapedRecipe.removePadding(data.pattern);
        ArrayList<Optional<Ingredient>> list = new ArrayList<Optional<Ingredient>>(25);
        CharArraySet charSet = new CharArraySet(data.key.keySet());
        for (String c : strings) {
            if (c == null) continue;
            Optional<Ingredient> optional;
            if (c.equals(" ")) {
                optional = Optional.empty();
            } else {
                Ingredient ingredient = data.key.get(c.charAt(0));
                if (ingredient == null) {
                    return DataResult.error(() -> "Pattern references symbol '" + c + "' but it's not defined in the key");
                }
                optional = Optional.of(ingredient);
            }
            charSet.remove(c.charAt(0));
            list.add(optional);
        }
        if (!charSet.isEmpty()) {
            return DataResult.error(() -> "Key defines symbols that aren't used in pattern: " + String.valueOf(charSet));
        }
        return DataResult.success(new RawStarforgeShapedRecipe(5, 5, list, Optional.of(data)));
    }

    private static final String PATTERN =
            "00100" +
                    "01110" +
                    "11111" +
                    "01110" +
                    "10001";

    @VisibleForTesting
    static String[] removePadding(List<String> pattern) {
        String[] strings = new String[15];
        int cur = 0;
        StringBuilder patti = new StringBuilder();
        for (String s : pattern) {
            patti.append(s);
        }
        for (int i = 0; i < 25; i++) {
            if (PATTERN.charAt(i) == '1') {
                strings[cur] = String.valueOf(patti.charAt(i));
                cur++;
            }
        }
        return strings;
    }

    public boolean matches(StarforgeRecipeInput input) {
        if (input.getStackCount() != this.ingredientCount) {
            return false;
        }
        for (int i = 0; i < 14; i++) {
            Optional<Ingredient> optional = this.ingredients.get(i);
            ItemStack stack;
            try {
                stack = input.getStackInSlot(i);
            } catch (Exception e) {
                stack = new ItemStack(Blocks.AIR.asItem());
            }
            if (Ingredient.matches(optional, stack)) continue;
            return false;
        }
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Optional<Ingredient>> getIngredients() {
        return this.ingredients;
    }

    public record Data(Map<Character, Ingredient> key, List<String> pattern) {
        private static final Codec<List<String>> PATTERN_CODEC = Codec.STRING.listOf().comapFlatMap(pattern -> {
            if (pattern.size() > 5) {
                return DataResult.error(() -> "Invalid pattern: too many rows, 5 is maximum");
            }
            if (pattern.isEmpty()) {
                return DataResult.error(() -> "Invalid pattern: empty pattern not allowed");
            }
            int i = ((String)pattern.getFirst()).length();
            for (String string : pattern) {
                if (string.length() > 5) {
                    return DataResult.error(() -> "Invalid pattern: too many columns, 5 is maximum");
                }
                if (i == string.length()) continue;
                return DataResult.error(() -> "Invalid pattern: each row must be the same width");
            }
            return DataResult.success(pattern);
        }, Function.identity());
        private static final Codec<Character> KEY_ENTRY_CODEC = Codec.STRING.comapFlatMap(keyEntry -> {
            if (keyEntry.length() != 1) {
                return DataResult.error(() -> "Invalid key entry: '" + keyEntry + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(keyEntry)) {
                return DataResult.error(() -> "Invalid key entry: ' ' is a reserved symbol.");
            }
            return DataResult.success(Character.valueOf(keyEntry.charAt(0)));
        }, String::valueOf);
        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codecs.strictUnboundedMap(KEY_ENTRY_CODEC, Ingredient.CODEC).fieldOf("key").forGetter(Data::key),
                PATTERN_CODEC.fieldOf("pattern").forGetter(Data::pattern)
        ).apply(instance, Data::new));
    }
}
