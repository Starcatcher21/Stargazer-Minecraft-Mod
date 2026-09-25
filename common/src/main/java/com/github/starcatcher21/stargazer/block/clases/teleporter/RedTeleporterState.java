package com.github.starcatcher21.stargazer.block.clases.teleporter;

import net.minecraft.util.StringRepresentable;

public enum RedTeleporterState implements StringRepresentable {
    middle("middle"),
    north("north"),
    south("south"),
    west("west"),
    east("east");

    private final String ID;

    RedTeleporterState(String id) {
        ID = id;
    }

    @Override
    public String getSerializedName() {
        return this.ID;
    }
}
