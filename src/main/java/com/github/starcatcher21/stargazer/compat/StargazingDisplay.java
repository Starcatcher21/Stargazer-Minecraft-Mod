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
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StargazingDisplay extends BasicDisplay {
    private final List<EntryIngredient> in;
    @Nullable
    private final List<EntryIngredient> out;
    private RegistryKey<World> world;
    private FallingObjectsList list;
    private FallingObjectDayState dayState;

    public StargazingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
        this.in = inputs;
        this.out = outputs;
        this.world = RegistryKey.of(RegistryKeys.WORLD, Identifier.ofVanilla("overworld"));
        this.dayState = FallingObjectDayState.Night;
    }

    public StargazingDisplay(List<FallingObject> list) {
        this(List.of(EntryIngredients.of(Items.SPYGLASS.getDefaultStack())), list.stream().map(fallingObject -> EntryIngredients.of(fallingObject.item.value().getDefaultStack())).toList());
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

    public RegistryKey<World> getWorld() {
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
        return StargazerREICommon.STARFORGE;
    }
}
