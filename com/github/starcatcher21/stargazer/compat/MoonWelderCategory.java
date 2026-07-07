package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.GameRules;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.MoonWelder;
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
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.github.starcatcher21.stargazer.screens.handled.MoonWelderHandled.*;

public class MoonWelderCategory implements DisplayCategory<MoonWelderDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Stargazer.MOD_ID, "textures/gui/moon_welder/moon_welder_gui.png");
    public static final CategoryIdentifier<MoonWelderDisplay> STARFORGE = CategoryIdentifier.of(Stargazer.MOD_ID, "moon_welder");
    @Override
    public CategoryIdentifier<? extends MoonWelderDisplay> getCategoryIdentifier() {
        return STARFORGE;
    }

    @Override
    public Text getTitle() {
        return MoonWelder.TITLE;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlock.MOON_WELDER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(MoonWelderDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 72, bounds.getMinY() + 10);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x - 7, startPoint.y - 7, 146, 102)));
        int moonphase = display.recipe().getMoonPhase();
        if (MinecraftClient.getInstance().getServer().getSpawnWorld().getGameRules().getValue(GameRules.MOON)) {
            switch (moonphase) {
                case 0:
                    widgets.add(Widgets.createTexturedWidget(FULL, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 1:
                    widgets.add(Widgets.createTexturedWidget(WANING_GIBBOUS, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 2:
                    widgets.add(Widgets.createTexturedWidget(THIRD, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 3:
                    widgets.add(Widgets.createTexturedWidget(WANING_CRESCENT, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 4:
                    widgets.add(Widgets.createTexturedWidget(NEW, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 5:
                    widgets.add(Widgets.createTexturedWidget(WAXING_CRESCENT, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 6:
                    widgets.add(Widgets.createTexturedWidget(FIRST, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                case 7:
                    widgets.add(Widgets.createTexturedWidget(WAXING_GIBBOUS, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
                default:
                    widgets.add(Widgets.createTexturedWidget(SUN, startPoint.x + 10, startPoint.y + 10, 0, 0, 16, 16, 16, 16));
                    break;
            }
        }


        List<Optional<Ingredient>> input = display.placement();
        List<EntryIngredient> entry = display.getIngedientsList();

        List<Slot> slots = Lists.newArrayList();

        slots.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 36)).backgroundEnabled(false).markInput());
        slots.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 36)).backgroundEnabled(false).markInput());

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

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 36)).backgroundEnabled(false)
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 105;
    }
}
