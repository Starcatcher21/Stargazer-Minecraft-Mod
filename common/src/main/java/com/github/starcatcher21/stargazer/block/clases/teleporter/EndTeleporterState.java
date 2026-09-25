package com.github.starcatcher21.stargazer.block.clases.teleporter;

import net.minecraft.util.StringRepresentable;

public enum EndTeleporterState implements StringRepresentable {
    middle("middle"),
    north("north"),
    south("south"),
    west("west"),
    east("east"),
    north_east("north_east"),
    north_west("north_west"),
    south_east("south_east"),
    south_west("south_west");

    private final String ID;

    EndTeleporterState(String id) {
        ID = id;
    }

    @Override
    public String getSerializedName() {
        return this.ID;
    }
}
