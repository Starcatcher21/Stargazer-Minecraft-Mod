package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Energy;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.github.starcatcher21.stargazer.screens.handled.MoonWelderHandled;
import com.github.starcatcher21.stargazer.screens.handled.StarCrusherHandled;
import com.github.starcatcher21.stargazer.screens.handled.StarforgeHandled;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedMoonWelderRecipeDisplay;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.ShapedStarforgeRecipeDisplay;
import com.github.starcatcher21.stargazer.screens.recipe.serializer.StarCrusherRecipeDisplay;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.Items;

public class StargazerREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new StarforgeCategory());
        registry.addWorkstations(StarforgeCategory.STARFORGE, EntryStacks.of(MoonBlocks.STAR_FORGE.asItem().getDefaultInstance()));
        registry.add(new MoonWelderCategory());
        registry.addWorkstations(MoonWelderCategory.STARFORGE, EntryStacks.of(ModBlock.MOON_WELDER.asItem().getDefaultInstance()));
        registry.add(new StargazingCategory());
        registry.addWorkstations(StargazingCategory.STARFORGE, EntryStacks.of(Items.SPYGLASS.getDefaultInstance()));
        registry.add(new StarCrusherCategory());
        registry.addWorkstations(StarCrusherCategory.STARFORGE, EntryStacks.of(Energy.STAR_CRUSHER.asItem().getDefaultInstance()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.beginRecipeFiller(ShapedStarforgeRecipeDisplay.class)
                .filterType(ShapedStarforgeRecipeDisplay.SERIALIZER)
                .fill(StarforgeDisplay::new);
        registry.beginRecipeFiller(ShapedMoonWelderRecipeDisplay.class)
                .filterType(ShapedMoonWelderRecipeDisplay.SERIALIZER)
                .fill(MoonWelderDisplay::new);
        registry.beginRecipeFiller(StarCrusherRecipeDisplay.class)
                .filterType(StarCrusherRecipeDisplay.SERIALIZER)
                .fill(StarCrusherDisplay::new);

        for (FallingObjectsList objectsList : FallingObjectsList.list2) {
            registry.add(new StargazingDisplay(objectsList));
        }
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(
                (screen.width - 180) / 2 + 90,
                (screen.height - 205) / 2 + 22,
                27, 13), StarforgeHandled.class, StarforgeCategory.STARFORGE
        );

        registry.registerClickArea(screen -> new Rectangle(
                (screen.width - 180) / 2 + 92,
                (screen.height - 205) / 2 + 43,
                9, 10), MoonWelderHandled.class, MoonWelderCategory.STARFORGE
        );

        registry.registerClickArea(screen -> new Rectangle(
                (screen.width - 180) / 2 + 52,
                (screen.height - 205) / 2 + 31,
                9, 47), StarCrusherHandled.class, StarCrusherCategory.STARFORGE
        );
    }
}
