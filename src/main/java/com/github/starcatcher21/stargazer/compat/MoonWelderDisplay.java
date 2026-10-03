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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
//? if >= 26.2 {
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
//?}
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MoonWelderDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable private List<Optional<Ingredient>> place;
    @Nullable private ShapedMoonWelderRecipe recipe;
    private final EntryIngredient out;

    /* ---------------- 26.2+ ------------------------------------------- */
    //? if >= 26.2 {
    public MoonWelderDisplay(ShapedMoonWelderRecipe recipe) {
        this(recipe.placementInfo(),
                Collections.singletonList(EntryIngredients.ofItemStacks(
                        Collections.singletonList(recipe.getResult().copy()))),
                recipe.getIngredients());
        this.recipe = recipe;
    }
    //?} else {
    /*public MoonWelderDisplay(ShapedMoonWelderRecipe recipe) {
        this(toInputs(recipe.getIngredients()),
             Collections.singletonList(EntryIngredients.ofItemStacks(
                 Collections.singletonList(recipe.getResult()))));
        this.place = recipe.getIngredients();
        this.recipe = recipe;
    }
    *///?}

    public MoonWelderDisplay(List<EntryIngredient> inputs, EntryIngredient outputs) {
        this(inputs, Collections.singletonList(outputs));
    }

    //? if >= 26.2 {
    public MoonWelderDisplay(PlacementInfo placement,
                             List<EntryIngredient> outputs,
                             List<Optional<Ingredient>> ingredient) {
        super(EntryIngredients.ofIngredients(placement.ingredients()), outputs);
        this.in    = EntryIngredients.ofIngredients(placement.ingredients());
        this.place = ingredient;
        this.out   = outputs.getFirst();
    }
    //?}

    /** Universal ctor — initialises in/out on every branch. */
    public MoonWelderDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in  = inputs;
        this.out = outputs.getFirst();
    }

    //? if >= 26.2 {
    public MoonWelderDisplay(ShapedMoonWelderRecipeDisplay d, Optional<RecipeDisplayId> id) {
        this(getRecipe(id));
    }
    //?}

    public MoonWelderDisplay(Recipe<?> recipe) {
        this((ShapedMoonWelderRecipe) recipe);
    }

    /* ---------------- helpers ----------------------------------------- */

    private static List<EntryIngredient> toInputs(List<Optional<Ingredient>> src) {
        List<EntryIngredient> out = new ArrayList<>(src.size());
        for (Optional<Ingredient> opt : src) {
            out.add(opt.map(EntryIngredients::ofIngredient).orElseGet(EntryIngredient::empty));
        }
        return out;
    }

    private static ShapedMoonWelderRecipe emptyRecipe() {
        //? if >= 26.2 {
        return new ShapedMoonWelderRecipe("",
                new RawMoonWelderShapedRecipe(0, 0,
                        Ingredient.of(Blocks.AIR.asItem()),
                        Ingredient.of(Blocks.AIR.asItem()),
                        0, Optional.empty()),
                ItemStackTemplate.fromStack(ItemStack.EMPTY));
        //?} else {
        /*return new ShapedMoonWelderRecipe("",
            new RawMoonWelderShapedRecipe(0, 0,
                Ingredient.of(Blocks.AIR.asItem()),
                Ingredient.of(Blocks.AIR.asItem()),
                0, Optional.empty()),
            ItemStack.EMPTY);
        *///?}
    }

    //? if >= 26.2 {
    public static ShapedMoonWelderRecipe getRecipe(Optional<RecipeDisplayId> id) {
        if (id.isPresent() && Minecraft.getInstance().getSingleplayerServer() != null) {
            var info = Minecraft.getInstance().getSingleplayerServer()
                    .getRecipeManager().getRecipeFromDisplay(id.get());
            if (info != null && info.parent().value() instanceof ShapedMoonWelderRecipe s) return s;
        }
        return emptyRecipe();
    }
    //?}

    public static ShapedMoonWelderRecipe getRecipe(Identifier id) {
        var server = Minecraft.getInstance().getSingleplayerServer();
        if (server != null) {
            //? if >= 26.2 {
            Optional<? extends RecipeHolder<?>> h =
                    server.getRecipeManager().byKey(ResourceKey.create(Registries.RECIPE, id));
            //?} else {
            /*Optional<? extends RecipeHolder<?>> h =
                server.getRecipeManager().byKey(id);
            *///?}
            if (h.isPresent() && h.get().value() instanceof ShapedMoonWelderRecipe s) return s;
        }
        return emptyRecipe();
    }

    public static MoonWelderDisplay of(RecipeHolder<? extends Recipe<?>> holder) {
        return holder.value() instanceof ShapedMoonWelderRecipe s
                ? new MoonWelderDisplay(s)
                : new MoonWelderDisplay(emptyRecipe());
    }

    List<EntryIngredient> getIngedientsList() {
        List<EntryIngredient> list = new ArrayList<>(14);
        List<EntryIngredient> src = getInputEntries();
        for (int i = 0; i < 2; i++) {
            try { list.add(src.get(i)); } catch (Exception ignored) {}
        }
        return list;
    }

    @Override public CategoryIdentifier<?> getCategoryIdentifier() {
        return MoonWelderCategory.STARFORGE;
    }
    public EntryIngredient result() { return this.out; }
    public List<Optional<Ingredient>> placement() { return this.place; }
    public List<EntryIngredient> ingredients() { return this.in; }
    public ShapedMoonWelderRecipe recipe() { return this.recipe; }

    //? if >= 26.2 {
    @Override
    //?}
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.MOONWELDER;
    }
}
