package com.github.starcatcher21.stargazer.compat.jei;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.moon.starforge.Starforge;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.screens.recipe.StarforgeRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
//? if >= 26.2 {
/*import mezz.jei.api.recipe.types.IRecipeType;
*///?} else {
import mezz.jei.api.recipe.RecipeType;
//?}
import mezz.jei.api.recipe.category.IRecipeCategory;
//? if >= 26.2 {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.ResourceLocation;
*///?} else {
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
//?}
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class StarforgeRecipeCategory
        implements IRecipeCategory<RecipeHolder<StarforgeRecipe>> {

    //? if >= 26.2 {
    /*private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/starforge/starforge_gui.png");
    *///?} else {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/starforge/starforge_gui.png");
    //?}

    private final IDrawable icon;
    private final IDrawable overlay;

    public StarforgeRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 146, 102);
        this.icon    = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(MoonBlocks.STAR_FORGE.get()));
    }

    //? if >= 26.2 {
    /*@Override
    public IRecipeType<RecipeHolder<StarforgeRecipe>> getRecipeType() {
        return StargazerJEIPlugin.STARFORGE;
    }
    *///?} else {
    @Override
    public RecipeType<RecipeHolder<StarforgeRecipe>> getRecipeType() {
        return StargazerJEIPlugin.STARFORGE;
    }
    //?}

    @Override
    public Component getTitle() {
        return Starforge.TITLE;
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
                          RecipeHolder<StarforgeRecipe> holder,
                          IFocusGroup focuses) {
        StarforgeRecipe recipe = holder.value();

        // 26.2: List<Optional<Ingredient>> with gaps kept, so indices line up with xs/ys.
        // (placementInfo().ingredients() drops empty slots and would shift every slot.)
        var ingredients = recipe.getIngredients();

        int[] xs = { 43, 25, 43, 61, 7, 25, 43, 61,   79, 25, 43, 61, 7,   79 };
        int[] ys = { 7, 25, 25, 25,  43, 43, 43, 43,  43, 61, 61, 61, 79,  79 };

        for (int i = 0; i < Math.min(ingredients.size(), xs.length); i++) {
            var ing = ingredients.get(i);
            //? if >= 26.2 {
            /*final int slotX = xs[i];
            final int slotY = ys[i];
            ing.ifPresent(present ->
                    builder.addSlot(RecipeIngredientRole.INPUT, slotX, slotY).add(present));
            *///?} else {
            if (ing != null && !ing.isEmpty()) {
                builder.addSlot(RecipeIngredientRole.INPUT, xs[i], ys[i]).addIngredients(ing);
            }
            //?}
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 29)
                //? if >= 26.2 {
                /*.add(recipe.getResult());
                *///?} else {
                .addItemStack(recipe.getResult());
                //?}
    }

    //? if >= 26.2 {
    /*@Override
    public void draw(RecipeHolder<StarforgeRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphicsExtractor guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    *///?} else {
    @Override
    public void draw(RecipeHolder<StarforgeRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphics guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
    //?}
}
