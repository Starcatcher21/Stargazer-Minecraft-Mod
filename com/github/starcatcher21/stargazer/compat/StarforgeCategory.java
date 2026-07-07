package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.moon.starforge.Starforge;
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
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StarforgeCategory implements DisplayCategory<StarforgeDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/starforge/starforge_gui.png");
    public static final CategoryIdentifier<StarforgeDisplay> STARFORGE = CategoryIdentifier.of(Stargazer.MOD_ID, "starforge");
    @Override
    public CategoryIdentifier<? extends StarforgeDisplay> getCategoryIdentifier() {
        return STARFORGE;
    }

    @Override
    public Text getTitle() {
        return Starforge.TITLE;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(MoonBlocks.STAR_FORGE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(StarforgeDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 72, bounds.getMinY() + 10);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));

        List<Optional<Ingredient>> input = display.placement();
        List<EntryIngredient> entry = display.getIngedientsList();

        List<Slot> slots = Lists.newArrayList();

        slots.add(Widgets.createSlot(new Point(startPoint.x + 2 * 18, startPoint.y)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 2 * 18, startPoint.y + 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 3 * 18, startPoint.y + 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 2 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 2 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 2 * 18, startPoint.y + 2 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 3 * 18, startPoint.y + 2 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 4 * 18, startPoint.y + 2 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 3 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 2 * 18, startPoint.y + 3 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 3 * 18, startPoint.y + 3 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 4 * 18)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 4 * 18, startPoint.y + 4 * 18)).backgroundEnabled(false).markInput());

        int curEntry = 0;
        if (input != null) {
            for (int i = 0; i < 14; i++) {
                Optional<Ingredient> ingredient = input.get(i);
                if (ingredient.isPresent()) {
                    slots.get(i).entries(entry.get(curEntry));
                    curEntry++;
                }
            }
        } else {
            for (int i = 0; i < 14; i++) {
                slots.get(i).entries(entry.getFirst());
            }
        }
        widgets.addAll(slots);

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 112, startPoint.y + 22)).backgroundEnabled(false)
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 105;
    }
}
