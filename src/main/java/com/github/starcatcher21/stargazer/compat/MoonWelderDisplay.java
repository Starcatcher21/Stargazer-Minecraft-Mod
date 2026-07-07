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
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.level.block.Blocks;
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
        this(recipe.placementInfo(), Collections.singletonList(EntryIngredients.of(recipe.getResult())), recipe.getIngredients());
        this.recipe = recipe;
    }

    public MoonWelderDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    public MoonWelderDisplay(PlacementInfo placement, List<EntryIngredient> outputs, List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.ingredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.ingredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }

    public MoonWelderDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    public MoonWelderDisplay(ShapedMoonWelderRecipeDisplay shapedMoonWelderRecipeDisplay, Optional<RecipeDisplayId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }

    public MoonWelderDisplay(Recipe<?> recipe) {
        this((ShapedMoonWelderRecipe) recipe);
    }

    public static ShapedMoonWelderRecipe getRecipe(Optional<RecipeDisplayId> networkRecipeId) {
        if (networkRecipeId.isPresent() && Minecraft.getInstance().getSingleplayerServer() != null) {
            RecipeManager.ServerDisplayInfo recip = Minecraft.getInstance().getSingleplayerServer().getRecipeManager().getRecipeFromDisplay(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedMoonWelderRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedMoonWelderRecipe("", new RawMoonWelderShapedRecipe(0,0, Ingredient.of(Blocks.AIR.asItem()), Ingredient.of(Blocks.AIR.asItem()), 0, Optional.empty()), ItemStack.EMPTY);
    }

    public static ShapedMoonWelderRecipe getRecipe(Identifier id) {
        if (Minecraft.getInstance().getSingleplayerServer() != null) {
            Optional<RecipeHolder<?>> recip = Minecraft.getInstance().getSingleplayerServer().getRecipeManager().byKey(ResourceKey.create(Registries.RECIPE, id));
            if (recip.isPresent() && recip.get().value() instanceof ShapedMoonWelderRecipe ssr) {
                return ssr;
            }
        }
        return new ShapedMoonWelderRecipe("", new RawMoonWelderShapedRecipe(0,0, Ingredient.of(Blocks.AIR.asItem()), Ingredient.of(Blocks.AIR.asItem()), 0, Optional.empty()), ItemStack.EMPTY);
    }

    @Nullable
    public static MoonWelderDisplay of(RecipeHolder<? extends Recipe<?>> holder) {
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
