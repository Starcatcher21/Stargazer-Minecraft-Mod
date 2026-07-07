package com.github.starcatcher21.stargazer.block.clases.teleporter;

import net.minecraft.util.StringRepresentable;

public enum CopperTeleporterState implements StringRepresentable {
    middle("middle"),
    north("north"),
    south("south"),
    west("west"),
    east("east"),
    north_east("north_east"),
    north_west("north_west"),
    south_east("south_east"),
    south_west("south_west"),
    ne_up("ne_up"),
    nw_up("nw_up"),
    se_up("se_up"),
    sw_up("sw_up");

    private final String ID;

    CopperTeleporterState(String id) {
        ID = id;
    }

    @Override
    public String getSerializedName() {
        return this.ID;
    }
}
