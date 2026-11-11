package com.github.starcatcher21.stargazer.compat.jei;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Energy;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.screens.recipe.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
//? if >= 26.2 {
import mezz.jei.api.recipe.types.IRecipeType;
//?} else {
/*import mezz.jei.api.recipe.RecipeType;
 *///?}
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
//? if >= 26.2 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.Identifier;
 *///?}
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

@JeiPlugin
public class StargazerJEIPlugin implements IModPlugin {

    private static final Identifier UUID =
            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "jei_plugin");

    /* ------------------------------------------------------------------ */
    /* Recipe Type constants                                              */
    /* ------------------------------------------------------------------ */
    //? if >= 26.2 {
    public static final IRecipeType<RecipeHolder<StarCrusherRecipe>> STAR_CRUSHER = holderType("star_crusher");
    public static final IRecipeType<RecipeHolder<MoonWelderRecipe>> MOON_WELDER = holderType("moon_welder");
    public static final IRecipeType<RecipeHolder<StarforgeRecipe>> STARFORGE = holderType("starforge");
    public static final IRecipeType<RecipeHolder<CrystalizerRecipe>> CRYSTALIZER = holderType("crystalizer");

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <R extends Recipe<?>> IRecipeType<RecipeHolder<R>> holderType(String path) {
        return (IRecipeType) IRecipeType.create(Stargazer.MOD_ID, path, RecipeHolder.class);
    }
    //?} else {
    /*public static final RecipeType<RecipeHolder<StarCrusherRecipe>> STAR_CRUSHER =
            RecipeType.createRecipeHolderType(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_crusher"));
    public static final RecipeType<RecipeHolder<MoonWelderRecipe>> MOON_WELDER =
            RecipeType.createRecipeHolderType(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_welder"));
    public static final RecipeType<RecipeHolder<StarforgeRecipe>> STARFORGE =
            RecipeType.createRecipeHolderType(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starforge"));
    public static final RecipeType<RecipeHolder<CrystalizerRecipe>> CRYSTALIZER =
            RecipeType.createRecipeHolderType(
                    Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "crystalizer"));
    *///?}

    //? if >= 26.2 {
    @Override
    public Identifier getPluginUid() {
        return UUID;
    }
    //?} else {
    /*@Override
    public Identifier getPluginUid() {
        return UUID;
    }
    *///?}

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new StarCrusherRecipeCategory(guiHelper),
                new MoonWelderRecipeCategory(guiHelper),
                new StarforgeRecipeCategory(guiHelper),
                new CrystalizerRecipeCategory(guiHelper)
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }
        //? if >= 26.2 {
        registration.addRecipes(STAR_CRUSHER, ClientRecipes.all(RecipeTypes.STAR_CRUSHER.get()));
        registration.addRecipes(MOON_WELDER, ClientRecipes.all(RecipeTypes.MOON_WELDER.get()));
        registration.addRecipes(STARFORGE, ClientRecipes.all(RecipeTypes.STARFORGE.get()));
        registration.addRecipes(CRYSTALIZER, ClientRecipes.all(RecipeTypes.CRYSTALIZER.get()));
        //?} else {
        /*var manager = level.getRecipeManager();
        registration.addRecipes(STAR_CRUSHER,
                manager.getAllRecipesFor(RecipeTypes.STAR_CRUSHER.get()));
        registration.addRecipes(MOON_WELDER,
                manager.getAllRecipesFor(RecipeTypes.MOON_WELDER.get()));
        registration.addRecipes(STARFORGE,
                manager.getAllRecipesFor(RecipeTypes.STARFORGE.get()));
        registration.addRecipes(CRYSTALIZER,
                manager.getAllRecipesFor(RecipeTypes.CRYSTALIZER.get()));
        *///?}
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        //? if >= 26.2 {
        registration.addCraftingStation(STAR_CRUSHER,
                new ItemStack(Energy.STAR_CRUSHER.get()));
        registration.addCraftingStation(MOON_WELDER,
                new ItemStack(ModBlock.MOON_WELDER.get()));
        registration.addCraftingStation(STARFORGE,
                new ItemStack(MoonBlocks.STAR_FORGE.get()));
        registration.addCraftingStation(CRYSTALIZER,
                new ItemStack(MoonBlocks.CRYSTALIZER.get()));
        //?} else {
        /*registration.addRecipeCatalyst(
                new ItemStack(Energy.STAR_CRUSHER.get()), STAR_CRUSHER);
        registration.addRecipeCatalyst(
                new ItemStack(ModBlock.MOON_WELDER.get()), MOON_WELDER);
        registration.addRecipeCatalyst(
                new ItemStack(MoonBlocks.STAR_FORGE.get()), STARFORGE);
        registration.addRecipeCatalyst(
                new ItemStack(MoonBlocks.CRYSTALIZER.get()), CRYSTALIZER);
        *///?}
    }
}
