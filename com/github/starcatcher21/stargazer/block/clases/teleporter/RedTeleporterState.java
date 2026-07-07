package com.github.starcatcher21.stargazer.block.clases.teleporter;

import net.minecraft.util.StringIdentifiable;

public enum RedTeleporterState implements StringIdentifiable {
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
    public String asString() {
        return this.ID;
    }
}
