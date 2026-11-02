package com.github.starcatcher21.stargazer.compat.jei;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerNetworking;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.MoonWelder;
import com.github.starcatcher21.stargazer.screens.recipe.MoonWelderRecipe;
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
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
*///?} else {
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
//?}
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import static com.github.starcatcher21.stargazer.screens.handled.MoonWelderHandled.*;

public class MoonWelderRecipeCategory
        implements IRecipeCategory<RecipeHolder<MoonWelderRecipe>> {

    //? if >= 26.2 {
    /*private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder_gui.png");
    *///?} else {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder_gui.png");
    //?}

    private final IDrawable icon;
    private final IDrawable overlay;

    public MoonWelderRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 146, 102);
        this.icon    = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlock.MOON_WELDER.get()));
    }

    //? if >= 26.2 {
    /*@Override
    public IRecipeType<RecipeHolder<MoonWelderRecipe>> getRecipeType() {
        return StargazerJEIPlugin.MOON_WELDER;
    }
    *///?} else {
    @Override
    public RecipeType<RecipeHolder<MoonWelderRecipe>> getRecipeType() {
        return StargazerJEIPlugin.MOON_WELDER;
    }
    //?}

    @Override
    public Component getTitle() {
        return MoonWelder.TITLE;
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
                          RecipeHolder<MoonWelderRecipe> holder,
                          IFocusGroup focuses) {
        MoonWelderRecipe recipe = holder.value();
        var ingredients = recipe.getIngredients();

        // getHeldStacks() is always empty for these recipes; the real inputs are the ingredients.
        //? if >= 26.2 {
        /*ingredients.get(0).ifPresent(ing ->
                builder.addSlot(RecipeIngredientRole.INPUT, 25, 43).add(ing));
        ingredients.get(1).ifPresent(ing ->
                builder.addSlot(RecipeIngredientRole.INPUT, 61, 43).add(ing));
        *///?} else {
        builder.addSlot(RecipeIngredientRole.INPUT, 25, 43).addIngredients(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 61, 43).addIngredients(ingredients.get(1));
        //?}

        builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 43)
                //? if >= 26.2 {
                /*.add(recipe.getResult());
                *///?} else {
                .addItemStack(recipe.getResult());
                //?}
    }

    //? if >= 26.2 {
    /*@Override
    public void draw(RecipeHolder<MoonWelderRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphicsExtractor guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);

        if (StargazerNetworking.isMoonRuleEnabled()) {
            var tex = moonTexture(holder.value().getMoonPhase());
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, tex, 10, 10, 0, 0, 16, 16, 16, 16);
        }
    }
    *///?} else {
    @Override
    public void draw(RecipeHolder<MoonWelderRecipe> holder,
                     IRecipeSlotsView slots,
                     GuiGraphics guiGraphics,
                     double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);

        if (StargazerNetworking.isMoonRuleEnabled()) {
            var tex = moonTexture(holder.value().getMoonPhase());
            guiGraphics.blit(tex, 10, 10, 0, 0, 16, 16, 16, 16);
        }
    }
    //?}

    // `var` keeps this free of the ResourceLocation / ResourceLocation split.
    private static <T> T pick(int phase,
                              T full, T waningGibbous, T third, T waningCrescent,
                              T newMoon, T waxingCrescent, T first, T waxingGibbous, T sun) {
        return switch (phase) {
            case 0 -> full;
            case 1 -> waningGibbous;
            case 2 -> third;
            case 3 -> waningCrescent;
            case 4 -> newMoon;
            case 5 -> waxingCrescent;
            case 6 -> first;
            case 7 -> waxingGibbous;
            default -> sun;
        };
    }

    //? if >= 26.2 {
    /*private static ResourceLocation moonTexture(int phase) {
    *///?} else {
    private static ResourceLocation moonTexture(int phase) {
    //?}
        return pick(phase, FULL, WANING_GIBBOUS, THIRD, WANING_CRESCENT,
                NEW, WAXING_CRESCENT, FIRST, WAXING_GIBBOUS, SUN);
    }
}
