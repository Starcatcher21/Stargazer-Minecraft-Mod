package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipe;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.minecraft.util.Identifier;

public class StargazerREICommon implements REICommonPlugin {
    public static DisplaySerializer<StarforgeDisplay> STARFORGE = new StarforgeDisplaySerializer();
    public static DisplaySerializer<MoonWelderDisplay> MOONWELDER = new MoonWelderDisplaySerializer();
    public static DisplaySerializer<StargazingDisplay> STARGAZING = new StargazingDisplaySerializer();
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(Identifier.of(Stargazer.MOD_ID, "starforge_display_serializer"), STARFORGE);
        registry.register(Identifier.of(Stargazer.MOD_ID, "moon_welder_display_serializer"), MOONWELDER);
        registry.register(Identifier.of(Stargazer.MOD_ID, "stargazing_display_serializer"), STARGAZING);
        REICommonPlugin.super.registerDisplaySerializer(registry);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(ShapedStarforgeRecipe.class)
                .filterType(RecipeTypes.STARFORGE)
                .fill(StarforgeDisplay::of);
        registry.beginFiller(FallingObjectsList.class)
                .fill(StargazingDisplay::new);
        registry.beginRecipeFiller(ShapedMoonWelderRecipe.class)
                .filterType(RecipeTypes.MOON_WELDER)
                .fill(MoonWelderDisplay::of);
        REICommonPlugin.super.registerDisplays(registry);
    }

}
