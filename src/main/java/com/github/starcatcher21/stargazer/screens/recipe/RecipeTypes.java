package com.github.starcatcher21.stargazer.screens.recipe;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.StarCrusherShapedRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypes {
    public static final RecipeType<StarforgeRecipe> STARFORGE = register("starforge");
    public static final RecipeSerializer<StarforgeRecipe> STARFORGE_SERIALIZER = registerSerializer("starforge", (RecipeSerializer) new ShapedStarforgeRecipe.Serializer());
    public static final RecipeType<MoonWelderRecipe> MOON_WELDER = register("moon_welder");
    public static final RecipeSerializer<MoonWelderRecipe> MOON_WELDER_SERIALIZER = registerSerializer("moon_welder", (RecipeSerializer) new ShapedMoonWelderRecipe.Serializer());
    public static final RecipeType<StarCrusherRecipe> STAR_CRUSHER = register("star_crusher");
    public static final RecipeSerializer<StarCrusherRecipe> STAR_CRUSHER_SERIALIZER = registerSerializer("star_crusher", (RecipeSerializer) new ShapedStarCrusherRecipe.Serializer());

    public static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id), new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return (S)Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, id), serializer);
    }

    public static void init() {
    }
}
