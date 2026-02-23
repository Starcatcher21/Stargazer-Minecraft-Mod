package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.screens.handled.StarforgeHandled;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipeDisplay;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class StargazerREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new StarforgeCategory());
        registry.addWorkstations(StarforgeCategory.STARFORGE, EntryStacks.of(MoonBlocks.STAR_FORGE.asItem().getDefaultStack()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.beginRecipeFiller(ShapedStarforgeRecipeDisplay.class)
                .filterType(ShapedStarforgeRecipeDisplay.SERIALIZER)
                .fill(StarforgeDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(
                (screen.width - 180) / 2 + 90,
                (screen.height - 205) / 2 + 22,
                27, 13), StarforgeHandled.class, StarforgeCategory.STARFORGE
        );
    }
}
