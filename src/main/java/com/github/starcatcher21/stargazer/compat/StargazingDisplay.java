package com.github.starcatcher21.stargazer.compat;

import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectDayState;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StargazingDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable
    private final List<EntryIngredient> out;
    private ResourceKey<Level> world;
    private FallingObjectsList list;
    private FallingObjectDayState dayState;

    public StargazingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs;
        this.world = ResourceKey.create(Registries.DIMENSION, Identifier.withDefaultNamespace("overworld"));
        this.dayState = FallingObjectDayState.Night;
    }

    public StargazingDisplay(List<FallingObject> list) {
        this(List.of(EntryIngredients.of(Items.SPYGLASS.getDefaultInstance())), list.stream().map(fallingObject -> EntryIngredients.of(fallingObject.item.value().getDefaultInstance())).toList());
    }

    public StargazingDisplay(FallingObjectsList fallingObjectsList) {
        this(fallingObjectsList.list);
        this.list = fallingObjectsList;
        this.world = fallingObjectsList.world;
        this.dayState = fallingObjectsList.dayState;
    }

    public FallingObjectsList getList() {
        return this.list;
    }

    public ResourceKey<Level> getWorld() {
        return this.world;
    }

    public FallingObjectDayState getDaystate() {
        return this.dayState;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return StargazingCategory.STARFORGE;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return StargazerREICommon.STARGAZING;
    }
}
