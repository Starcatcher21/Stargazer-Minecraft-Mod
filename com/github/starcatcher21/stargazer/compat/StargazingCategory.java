package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.Stargazer;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class StargazingCategory implements DisplayCategory<StargazingDisplay> {
    public static final Identifier NIGHT_TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/stargazing_night.png");
    public static final Identifier DAY_TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/stargazing_day.png");
    public static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/stargazing.png");
    public static final CategoryIdentifier<StargazingDisplay> STARFORGE = CategoryIdentifier.of(Stargazer.MOD_ID, "stargazing");
    public static final String TranslationKey = "category.rei.stargazer.stargazing";
    @Override
    public CategoryIdentifier<? extends StargazingDisplay> getCategoryIdentifier() {
        return STARFORGE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable(TranslationKey);
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Items.SPYGLASS.getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(StargazingDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 72, bounds.getMinY() + 10);
        List<Widget> widgets = new LinkedList<>();

        // 1. Base recipe background
        widgets.add(Widgets.createRecipeBase(bounds));
        switch (display.getDaystate()) {
            case Day -> widgets.add(Widgets.createTexturedWidget(DAY_TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));
            case Night -> widgets.add(Widgets.createTexturedWidget(NIGHT_TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));
            case null, default -> widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));
        }
        // 2. Input Slot (Spyglass)
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 60, startPoint.y + 64))
                .backgroundEnabled(false)
                .entries(display.getInputEntries().get(0)) // Pulls safely from the display configuration
                .markInput());

        // 3. Output Slot (Flattens all output entries so the slot cycles through them)
        List<me.shedaniel.rei.api.common.entry.EntryStack<?>> totalOutputs = new LinkedList<>();
        for (me.shedaniel.rei.api.common.entry.EntryIngredient ingredient : display.getOutputEntries()) {
            totalOutputs.addAll(ingredient);
        }

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 60, startPoint.y + 16))
                .backgroundEnabled(false)
                .entries(totalOutputs) // This allows the output slot to cycle through all falling objects
                .markOutput());
        String path = display.getWorld().getValue().getPath();
        Text text = Text.of(path.substring(0, 1).toUpperCase() + path.substring(1));
        widgets.add(Widgets.createLabel(new Point(startPoint.x + 70, startPoint.y), text));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 105;
    }
}
