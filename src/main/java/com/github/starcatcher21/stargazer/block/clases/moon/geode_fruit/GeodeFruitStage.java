package com.github.starcatcher21.stargazer.block.clases.moon.geode_fruit;

import net.minecraft.util.StringRepresentable;

public enum GeodeFruitStage implements StringRepresentable {
    start("start"),
    middle("middle"),
    ending("ending"),
    grown("grown");

    public final String ID;

    GeodeFruitStage(String id) {
        ID = id;
    }

    @Override
    public String getSerializedName() {
        return ID;
    }
}
