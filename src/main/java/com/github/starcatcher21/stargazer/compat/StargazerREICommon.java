package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarCrusherRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import com.github.starcatcher21.starlib.mechanics.star.FallingObjectsList;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
//? if >= 26.2 {
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
//? } else {
/*import me.shedaniel.rei.api.common.entry.settings.EntrySettingsAdapterRegistry;
import me.shedaniel.rei.api.common.entry.type.EntryTypeRegistry;
import me.shedaniel.rei.api.common.plugins.REIPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
*///? }
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class StargazerREICommon implements
        //? if >= 26.2 {
        REICommonPlugin
        //? } else {
        /*REIPlugin
        *///? }
{
    public static DisplaySerializer<StarforgeDisplay> STARFORGE = new StarforgeDisplaySerializer();
    public static DisplaySerializer<MoonWelderDisplay> MOONWELDER = new MoonWelderDisplaySerializer();
    public static DisplaySerializer<StargazingDisplay> STARGAZING = new StargazingDisplaySerializer();
    public static DisplaySerializer<StarCrusherDisplay> STAR_CRUSHER = new StarCrusherDisplaySerializer();

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        //? if >= 26.2 {
        registry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "starforge_display_serializer"), STARFORGE);
        registry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "moon_welder_display_serializer"), MOONWELDER);
        registry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "stargazing_display_serializer"), STARGAZING);
        registry.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "star_crusher_display_serializer"), STAR_CRUSHER);
        REICommonPlugin.super.registerDisplaySerializer(registry);
        //? } else {
        /*registry.register(CategoryIdentifier.of(Stargazer.MOD_ID, "starforge_display_serializer"), STARFORGE);
        registry.register(CategoryIdentifier.of(Stargazer.MOD_ID, "moon_welder_display_serializer"), MOONWELDER);
        registry.register(CategoryIdentifier.of(Stargazer.MOD_ID, "stargazing_display_serializer"), STARGAZING);
        registry.register(CategoryIdentifier.of(Stargazer.MOD_ID, "star_crusher_display_serializer"), STAR_CRUSHER);
        REIPlugin.super.registerDisplaySerializer(registry);
        *///? }
    }

    //? if >= 26.2 {
    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(ShapedStarforgeRecipe.class)
                .filterType(RecipeTypes.STARFORGE.get())
                .fill(StarforgeDisplay::of);
        registry.beginFiller(FallingObjectsList.class)
                .fill(StargazingDisplay::new);
        registry.beginRecipeFiller(ShapedMoonWelderRecipe.class)
                .filterType(RecipeTypes.MOON_WELDER.get())
                .fill(MoonWelderDisplay::of);
        registry.beginRecipeFiller(ShapedStarCrusherRecipe.class)
                .filterType(RecipeTypes.STAR_CRUSHER.get())
                .fill(StarCrusherDisplay::of);
        REICommonPlugin.super.registerDisplays(registry);
    }
    //? }

    //? if < 26.2 {
    /*@Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }

    @Override
    public Class getPluginProviderClass() {
        return null;
    }
    *///? }
}
