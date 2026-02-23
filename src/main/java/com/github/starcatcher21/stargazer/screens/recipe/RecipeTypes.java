package com.github.starcatcher21.stargazer.screens.recipe;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RecipeTypes {
    public static final RecipeType<StarforgeRecipe> STARFORGE = register("starforge");
    public static final RecipeSerializer<StarforgeRecipe> STARFORGE_SERIALIZER = registerSerializer("starforge", (RecipeSerializer) new ShapedStarforgeRecipe.Serializer());

    public static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(Stargazer.MOD_ID, id), new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return (S)Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Stargazer.MOD_ID, id), serializer);
    }

    public static void init() {
    }
}
