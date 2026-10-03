package com.github.starcatcher21.stargazer.screens.recipe;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.RECIPE_TYPE);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.RECIPE_SERIALIZER);

    // Recipe Types
    public static final RegistrySupplier<RecipeType<StarforgeRecipe>> STARFORGE =
            registerType("starforge");
    public static final RegistrySupplier<RecipeType<MoonWelderRecipe>> MOON_WELDER =
            registerType("moon_welder");
    public static final RegistrySupplier<RecipeType<StarCrusherRecipe>> STAR_CRUSHER =
            registerType("star_crusher");

    // Recipe Serializers
    public static final RegistrySupplier<RecipeSerializer<ShapedStarforgeRecipe>> STARFORGE_SERIALIZER =
            RECIPE_SERIALIZERS.register("starforge", () -> ShapedStarforgeRecipe.SERIALIZER);
    public static final RegistrySupplier<RecipeSerializer<ShapedMoonWelderRecipe>> MOON_WELDER_SERIALIZER =
            RECIPE_SERIALIZERS.register("moon_welder", () -> ShapedMoonWelderRecipe.SERIALIZER);
    public static final RegistrySupplier<RecipeSerializer<ShapedStarCrusherRecipe>> STAR_CRUSHER_SERIALIZER =
            RECIPE_SERIALIZERS.register("star_crusher", () -> ShapedStarCrusherRecipe.SERIALIZER);

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> registerType(String id) {
        return RECIPE_TYPES.register(id, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return id;
            }
        });
    }

    public static void init() {
        RECIPE_TYPES.register();
        RECIPE_SERIALIZERS.register();
    }
}
