package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.screens.recipe.CrystalizerRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.RawCrystalizerRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedCrystalizerRecipe;
//? if >= 26.2 {
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedCrystalizerRecipeDisplay;
//?}
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.client.Minecraft;
//? if >= 26.2 {
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeManager;
//?}
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CrystalizerDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable private List<Optional<Ingredient>> place;
    @Nullable private ShapedCrystalizerRecipe recipe;
    private final EntryIngredient out;

    //? if >= 26.2 {
    public CrystalizerDisplay(ShapedCrystalizerRecipe recipe) {
        this(recipe.placementInfo(),
             Collections.singletonList(EntryIngredients.ofItemStacks(
                 Collections.singletonList(recipe.getResult()))),
             recipe.getIngredients());
        this.recipe = recipe;
    }
    //?} else {
    /*public CrystalizerDisplay(ShapedCrystalizerRecipe recipe) {
        this(toInputs(recipe.getIngredients()),
                Collections.singletonList(EntryIngredients.ofItemStacks(
                        Collections.singletonList(recipe.getResult()))));
        this.place = toOptionalList(recipe.getIngredients());
        this.recipe = recipe;
    }
    *///?}

    public CrystalizerDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    //? if >= 26.2 {
    public CrystalizerDisplay(PlacementInfo placement,
                              List<EntryIngredient> outputs,
                              List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.ingredients()), outputs);
        this.in    = EntryIngredients.ofIngredients(placement.ingredients());
        this.place = ingredient;
        this.out   = outputs.getFirst();
    }
    //?}

    public CrystalizerDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in  = inputs;
        this.out = outputs.getFirst();
    }

    //? if >= 26.2 {
    public CrystalizerDisplay(ShapedCrystalizerRecipeDisplay display,
                              Optional<RecipeDisplayId> networkRecipeId) {
        this(getRecipe(networkRecipeId));
    }
    //?}

    public CrystalizerDisplay(Recipe<?> recipe) {
        this((ShapedCrystalizerRecipe) recipe);
    }

    //? if < 26.2 {
    /*public CrystalizerDisplay(RecipeHolder<CrystalizerRecipe> holder) {
        this(holder.value());
    }
    *///? }

    //? if >= 26.2 {
    private static List<EntryIngredient> toInputs(List<Optional<Ingredient>> src) {
        List<EntryIngredient> out = new ArrayList<>(src.size());
        for (Optional<Ingredient> opt : src) {
            out.add(opt.map(EntryIngredients::ofIngredient).orElseGet(EntryIngredient::empty));
        }
        return out;
    }
    //?} else {
    /*private static List<EntryIngredient> toInputs(List<Ingredient> src) {
        List<EntryIngredient> out = new ArrayList<>(src.size());
        for (Ingredient ing : src) {
            out.add(EntryIngredients.ofIngredient(ing));
        }
        return out;
    }

    private static List<Optional<Ingredient>> toOptionalList(List<Ingredient> src) {
        List<Optional<Ingredient>> out = new ArrayList<>(src.size());
        for (Ingredient ing : src) {
            out.add(Optional.of(ing));
        }
        return out;
    }
    *///?}

    private static ShapedCrystalizerRecipe emptyRecipe() {
        //? if >= 26.2 {
        return new ShapedCrystalizerRecipe("",
                new RawCrystalizerRecipe(2, 1,
                        Ingredient.of(Blocks.AIR.asItem()),
                        Ingredient.of(Blocks.AIR.asItem()),
                        Optional.empty()),
                ItemStackTemplate.fromStack(Blocks.AIR.asItem().getDefaultInstance()));
        //?} else {
        /*return new ShapedCrystalizerRecipe("",
                new RawCrystalizerRecipe(2, 1,
                        Ingredient.of(Blocks.AIR.asItem()),
                        Ingredient.of(Blocks.AIR.asItem()),
                        Optional.empty()),
                Blocks.AIR.asItem().getDefaultInstance());
        *///?}
    }

    //? if >= 26.2 {
    public static ShapedCrystalizerRecipe getRecipe(Optional<RecipeDisplayId> networkRecipeId) {
        if (networkRecipeId.isPresent() && Minecraft.getInstance().getSingleplayerServer() != null) {
            RecipeManager.ServerDisplayInfo info = Minecraft.getInstance()
                    .getSingleplayerServer().getRecipeManager()
                    .getRecipeFromDisplay(networkRecipeId.get());
            if (info != null && info.parent().value() instanceof ShapedCrystalizerRecipe s) {
                return s;
            }
        }
        return emptyRecipe();
    }
    //?}

    public static ShapedCrystalizerRecipe getRecipe(Identifier id) {
        if (Minecraft.getInstance().getSingleplayerServer() != null) {
            //? if >= 26.2 {
            Optional<RecipeHolder<?>> holder = Minecraft.getInstance()
                    .getSingleplayerServer().getRecipeManager()
                    .byKey(ResourceKey.create(Registries.RECIPE, id));
            //?} else {
            /*Optional<RecipeHolder<?>> holder = Minecraft.getInstance()
                    .getSingleplayerServer().getRecipeManager()
                    .byKey(id);
            *///?}
            if (holder.isPresent() && holder.get().value() instanceof ShapedCrystalizerRecipe s) {
                return s;
            }
        }
        return emptyRecipe();
    }

    public static CrystalizerDisplay of(RecipeHolder<? extends Recipe<?>> holder) {
        return holder.value() instanceof ShapedCrystalizerRecipe s
                ? new CrystalizerDisplay(s)
                : new CrystalizerDisplay(emptyRecipe());
    }

    public List<EntryIngredient> getIngedientsList() {
        return getInputEntries();
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CrystalizerCategory.CRYSTALIZER;
    }

    public EntryIngredient result() { return this.out; }
    public List<Optional<Ingredient>> placement() { return this.place; }
    public List<EntryIngredient> ingredients() { return this.in; }
    public ShapedCrystalizerRecipe recipe() { return this.recipe; }

    //? if >= 26.2 {
    @Override
     //?}
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.CRYSTALIZER;
    }
}
