package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CrystalizerCategory implements DisplayCategory<CrystalizerDisplay> {
    public static final String TranslationKey = "category.rei.stargazer.crystalizer";
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, "textures/gui/crystalizer_gui.png");
    public static final CategoryIdentifier<CrystalizerDisplay> CRYSTALIZER =
            CategoryIdentifier.of(Stargazer.MOD_ID, "crystalizer");

    @Override
    public CategoryIdentifier<? extends CrystalizerDisplay> getCategoryIdentifier() {
        return CRYSTALIZER;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(TranslationKey);
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(MoonBlocks.CRYSTALIZER.get().asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(CrystalizerDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 72, bounds.getMinY() + 10);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(
                TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));

        List<Optional<Ingredient>> input = display.placement();
        List<EntryIngredient> entry = display.getIngedientsList();

        List<Slot> slots = Lists.newArrayList();
        slots.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 36))
                .backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 36))
                .backgroundEnabled(false).markInput());

        int curEntry = 0;
        if (input != null) {
            for (int i = 0; i < 2; i++) {
                Optional<Ingredient> ingredient = input.get(i);
                if (ingredient.isPresent()) {
                    slots.get(i).entries(entry.get(curEntry));
                    curEntry++;
                }
            }
        } else {
            for (int i = 0; i < 2; i++) {
                slots.get(i).entries(entry.getFirst());
            }
        }
        widgets.addAll(slots);

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 36))
                .backgroundEnabled(false)
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 105;
    }
}
