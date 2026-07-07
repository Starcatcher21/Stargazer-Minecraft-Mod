package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.RawMoonWelderShapedRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipeDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MoonWelderDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable
    private List<Optional<Ingredient>> place;
    @Nullable
    private ShapedMoonWelderRecipe recipe;
    private final EntryIngredient out;
    public MoonWelderDisplay(ShapedMoonWelderRecipe recipe) {
        this(recipe.getIngredientPlacement(), Collections.singletonList(EntryIngredients.of(recipe.getResult())), recipe.getIngredients());
        this.recipe = recipe;
    }

    public MoonWelderDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    public MoonWelderDisplay(IngredientPlacement placement, List<EntryIngredient> outputs, List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.getIngredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.getIngredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }

    public MoonWelderDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    public MoonWelderDisplay(ShapedMoonWelderRecipeDisplay shapedMoonWelderRecipeDisplay, Optional<NetworkRecipeId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }

    public MoonWelderDisplay(Recipe<?> recipe) {
        this((ShapedMoonWelderRecipe) recipe);
    }

    public static ShapedMoonWelderRecipe getRecipe(Optional<NetworkRecipeId> networkRecipeId) {
        if (networkRecipeId.isPresent() && MinecraftClient.getInstance().getServer() != null) {
            ServerRecipeManager.ServerRecipe recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedMoonWelderRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedMoonWelderRecipe("", new RawMoonWelderShapedRecipe(0,0, Ingredient.ofItem(Blocks.AIR.asItem()), Ingredient.ofItem(Blocks.AIR.asItem()), 0, Optional.empty()), ItemStack.EMPTY);
    }

    public static ShapedMoonWelderRecipe getRecipe(Identifier id) {
        if (MinecraftClient.getInstance().getServer() != null) {
            Optional<RecipeEntry<?>> recip = MinecraftClient.getInstance().getServer().getRecipeManager().get(RegistryKey.of(RegistryKeys.RECIPE, id));
            if (recip.isPresent() && recip.get().value() instanceof ShapedMoonWelderRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedMoonWelderRecipe("", new RawMoonWelderShapedRecipe(0,0, Ingredient.ofItem(Blocks.AIR.asItem()), Ingredient.ofItem(Blocks.AIR.asItem()), 0, Optional.empty()), ItemStack.EMPTY);
    }

    @Nullable
    public static MoonWelderDisplay of(RecipeEntry<? extends Recipe<?>> holder) {
        Recipe<?> recipe = holder.value();
        if (recipe instanceof ShapedMoonWelderRecipe ssr) {
            return new MoonWelderDisplay(ssr);
        }

        return null;
    }

    List<EntryIngredient> getIngedientsList() {

        List<EntryIngredient> list = new ArrayList<>(14);

        List<EntryIngredient> inputEntries = getInputEntries();

        for (int i = 0; i < 2; i++) {
            EntryIngredient stacks;
            try {
                stacks = inputEntries.get(i);
                list.add(stacks);
            } catch (Exception ignored) {}
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MoonWelderCategory.STARFORGE;
    }

    public EntryIngredient result() {
        return this.out;
    }

    public List<Optional<Ingredient>> placement() {
        return this.place;
    }

    public List<EntryIngredient> ingredients() {
        return this.in;
    }
    public ShapedMoonWelderRecipe recipe() {
        return this.recipe;
    }


    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.MOONWELDER;
    }
}
