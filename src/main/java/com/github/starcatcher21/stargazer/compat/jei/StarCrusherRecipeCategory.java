package com.github.starcatcher21.stargazer.compat.jei;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.energy.machines.StarCrusher;
import com.github.starcatcher21.stargazer.block.register.Energy;
import com.github.starcatcher21.stargazer.screens.recipe.StarCrusherRecipe;
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

public class StarCrusherRecipeCategory
        implements IRecipeCategory<RecipeHolder<StarCrusherRecipe>> {

    //? if >= 26.1.2 {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/star_crusher_gui.png");
    //?} else {
    /*private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/star_crusher_gui.png");
    *///?}

    private final IDrawable icon;
    private final IDrawable overlay;

    public StarCrusherRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 146, 102);
        this.icon    = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(Energy.STAR_CRUSHER.get()));
    }

    //? if >= 26.1.2 {
    @Override
    public IRecipeType<RecipeHolder<StarCrusherRecipe>> getRecipeType() {
        return StargazerJEIPlugin.STAR_CRUSHER;
    }
    //?} else {
    /*@Override
    public RecipeType<RecipeHolder<StarCrusherRecipe>> getRecipeType() {
        return StargazerJEIPlugin.STAR_CRUSHER;
    }
    *///?}

    @Override
    public Component getTitle() {
        return StarCrusher.TITLE;
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
                          RecipeHolder<StarCrusherRecipe> holder,
                          IFocusGroup focuses) {
        StarCrusherRecipe recipe = holder.value();

        // getHeldStacks() is always empty for these recipes; the real input is the ingredient.
        //? if >= 26.1.2 {
        recipe.getIngredients().get(0).ifPresent(ing ->
                builder.addSlot(RecipeIngredientRole.INPUT, 48, 13).add(ing));
        //?} else {
        /*builder.addSlot(RecipeIngredientRole.INPUT, 48, 13)
                .addIngredients(recipe.getIngredients().get(0));
        *///?}

        builder.addSlot(RecipeIngredientRole.OUTPUT, 48, 80)
                //? if >= 26.1.2 {
                .add(recipe.getResult());
                //?} else {
                /*.addItemStack(recipe.getResult());
                *///?}
    }

    //? if >= 26.1.2 {
    @Override
    public void draw(RecipeHolder<StarCrusherRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphicsExtractor guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    //?} else {
    /*@Override
    public void draw(RecipeHolder<StarCrusherRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphics guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    *///?}
}
