package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.serializer.*;
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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
//? if >= 26.2 {
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.PlacementInfo;
//?}
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StarCrusherDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable private List<Optional<Ingredient>> place;
    @Nullable private ShapedStarCrusherRecipe recipe;
    private final EntryIngredient out;

    /* ------------------------------------------------------------------ */
    /* 26.2+ : recipe carries PlacementInfo + ItemStackTemplate result    */
    /* ------------------------------------------------------------------ */
    //? if >= 26.2 {
    public StarCrusherDisplay(ShapedStarCrusherRecipe recipe) {
        this(recipe.placementInfo(),
             Collections.singletonList(EntryIngredients.ofItemStacks(
                 Collections.singletonList(recipe.getResult().copy()))),
             recipe.getIngredients());
        this.recipe = recipe;
    }
    //?} else {
    /*/^ ------------------------------------------------------------------ ^/
    /^ 1.21.1 : no PlacementInfo, getResult() returns ItemStack           ^/
    /^ ------------------------------------------------------------------ ^/
    public StarCrusherDisplay(ShapedStarCrusherRecipe recipe) {
        this(toInputs(recipe.getIngredients()),
                Collections.singletonList(EntryIngredients.ofItemStacks(
                        Collections.singletonList(recipe.getResult()))));
        this.place = recipe.getIngredients();
        this.recipe = recipe;
    }
    *///?}

    public StarCrusherDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    //? if >= 26.2 {
    public StarCrusherDisplay(PlacementInfo placement,
                              List<EntryIngredient> outputs,
                              List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.ingredients()), outputs);
        this.in = EntryIngredients.ofIngredients(placement.ingredients());
        this.place = ingredient;
        this.out = outputs.getFirst();
    }
    //?}

    /** Universal ctor — guarantees in/out are assigned on every branch. */
    public StarCrusherDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs.getFirst();
    }

    //? if >= 26.2 {
    public StarCrusherDisplay(StarCrusherRecipeDisplay display,
                              Optional<RecipeDisplayId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }
    //?}

    public StarCrusherDisplay(Recipe<?> recipe) {
        this((ShapedStarCrusherRecipe) recipe);
    }

    /* ------------------------------------------------------------------ */
    /* helpers                                                            */
    /* ------------------------------------------------------------------ */

    /** List<Optional<Ingredient>> -> List<EntryIngredient> using only the
     *  most portable REI entry points (no ofIngredients(Collection) needed). */
    private static List<EntryIngredient> toInputs(List<Optional<Ingredient>> src) {
        List<EntryIngredient> out = new ArrayList<>(src.size());
        for (Optional<Ingredient> opt : src) {
            out.add(opt.map(EntryIngredients::ofIngredient).orElseGet(EntryIngredient::empty));
        }
        return out;
    }

    private static ShapedStarCrusherRecipe emptyRecipe() {
        //? if >= 26.2 {
        return new ShapedStarCrusherRecipe("",
            new StarCrusherShapedRecipe(Ingredient.of(Blocks.AIR.asItem()), Optional.empty()),
            ItemStackTemplate.fromStack(Blocks.AIR.asItem().getDefaultInstance()));
        //?} else {
        /*return new ShapedStarCrusherRecipe("",
                new StarCrusherShapedRecipe(Ingredient.of(Blocks.AIR.asItem()), Optional.empty()),
                Blocks.AIR.asItem().getDefaultInstance());
        *///?}
    }

    //? if >= 26.2 {
    public static ShapedStarCrusherRecipe getRecipe(Optional<RecipeDisplayId> networkRecipeId) {
        if (networkRecipeId.isPresent() && Minecraft.getInstance().getSingleplayerServer() != null) {
            RecipeManager.ServerDisplayInfo recip = Minecraft.getInstance()
                .getSingleplayerServer().getRecipeManager()
                .getRecipeFromDisplay(networkRecipeId.get());
            if (recip != null && recip.parent().value() instanceof ShapedStarCrusherRecipe ssr) {
                return ssr;
            }
        }
        return emptyRecipe();
    }
    //?}

    public static ShapedStarCrusherRecipe getRecipe(Identifier id) {
        if (Minecraft.getInstance().getSingleplayerServer() != null) {
            //? if >= 26.2 {
            Optional<RecipeHolder<?>> recip = Minecraft.getInstance()
                .getSingleplayerServer().getRecipeManager()
                .byKey(ResourceKey.create(Registries.RECIPE, id));
            //?} else {
            /*Optional<RecipeHolder<?>> recip = Minecraft.getInstance()
                    .getSingleplayerServer().getRecipeManager()
                    .byKey(id);
            *///?}
            if (recip.isPresent() && recip.get().value() instanceof ShapedStarCrusherRecipe ssr) {
                return ssr;
            }
        }
        return emptyRecipe();
    }

    public static StarCrusherDisplay of(RecipeHolder<? extends Recipe<?>> holder) {
        Recipe<?> recipe = holder.value();
        if (recipe instanceof ShapedStarCrusherRecipe ssr) {
            return new StarCrusherDisplay(ssr);
        }
        return new StarCrusherDisplay(emptyRecipe());
    }

    List<EntryIngredient> getIngedientsList() {
        List<EntryIngredient> list = new ArrayList<>(14);
        List<EntryIngredient> inputEntries = getInputEntries();
        for (int i = 0; i < 2; i++) {
            try {
                list.add(inputEntries.get(i));
            } catch (Exception ignored) {}
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return StarCrusherCategory.STARFORGE;
    }

    public EntryIngredient result() { return this.out; }
    public List<Optional<Ingredient>> placement() { return this.place; }
    public List<EntryIngredient> ingredients() { return this.in; }
    public ShapedStarCrusherRecipe recipe() { return this.recipe; }

    //? if >= 26.2 {
    @Override
    //? }
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.STAR_CRUSHER;
    }
}
