package com.github.starcatcher21.stargazer.compat.jei;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.compat.CrystalizerCategory;
import com.github.starcatcher21.stargazer.screens.recipe.CrystalizerRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
//? if >= 26.1.2 {
import mezz.jei.api.recipe.types.IRecipeType;
//?} else {
/*import mezz.jei.api.recipe.RecipeType;
*///?}
import mezz.jei.api.recipe.category.IRecipeCategory;
//? if >= 26.1.2 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
*///?}
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class CrystalizerRecipeCategory
        implements IRecipeCategory<RecipeHolder<CrystalizerRecipe>> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/crystalizer_gui.png");

    private final IDrawable icon;
    private final IDrawable overlay;

    public CrystalizerRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 146, 102);
        this.icon    = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(MoonBlocks.CRYSTALIZER.get()));
    }

    //? if >= 26.1.2 {
    @Override
    public IRecipeType<RecipeHolder<CrystalizerRecipe>> getRecipeType() {
        return StargazerJEIPlugin.CRYSTALIZER;
    }
    //?} else {
    /*@Override
    public RecipeType<RecipeHolder<CrystalizerRecipe>> getRecipeType() {
        return StargazerJEIPlugin.CRYSTALIZER;
    }
    *///?}

    @Override
    public Component getTitle() {
        return Component.translatable(CrystalizerCategory.TranslationKey);
    }

    @Override
    public int getWidth()  { return 146; }

    @Override
    public int getHeight() { return 102; }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder,
                          RecipeHolder<CrystalizerRecipe> holder,
                          IFocusGroup focuses) {
        CrystalizerRecipe recipe = holder.value();
        var ingredients = recipe.getIngredients();

        //? if >= 26.1.2 {
        ingredients.get(0).ifPresent(ing ->
                builder.addSlot(RecipeIngredientRole.INPUT, 25, 43).add(ing));
        ingredients.get(1).ifPresent(ing ->
                builder.addSlot(RecipeIngredientRole.INPUT, 61, 43).add(ing));
        //?} else {
        /*builder.addSlot(RecipeIngredientRole.INPUT, 25, 43).addIngredients(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 61, 43).addIngredients(ingredients.get(1));
        *///?}

        builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 43)
                //? if >= 26.1.2 {
                .add(recipe.getResult());
                //?} else {
                /*.addItemStack(recipe.getResult());
                *///?}
    }

    //? if >= 26.1.2 {
    @Override
    public void draw(RecipeHolder<CrystalizerRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphicsExtractor guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    //?} else {
    /*@Override
    public void draw(RecipeHolder<CrystalizerRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphics guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    *///?}
}
