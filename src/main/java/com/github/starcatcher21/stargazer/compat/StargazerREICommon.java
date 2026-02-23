package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.screens.recipe.RecipeTypes;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipe;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.minecraft.util.Identifier;

public class StargazerREICommon implements REICommonPlugin {
    public static DisplaySerializer<StarforgeDisplay> STARFORGE = new StarforgeDisplaySerializer();
    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(Identifier.of(Stargazer.MOD_ID, "starforge_display_serializer"), STARFORGE);
        REICommonPlugin.super.registerDisplaySerializer(registry);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(ShapedStarforgeRecipe.class)
                .filterType(RecipeTypes.STARFORGE)
                .fill(StarforgeDisplay::of);
        REICommonPlugin.super.registerDisplays(registry);
    }

}
