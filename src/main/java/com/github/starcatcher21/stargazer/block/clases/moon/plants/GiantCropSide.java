package com.github.starcatcher21.stargazer.block.clases.moon.plants;

import net.minecraft.util.StringIdentifiable;

public enum GiantCropSide implements StringIdentifiable {
    nwd("nwd"),
    ned("ned"),
    swd("swd"),
    sed("sed"),
    nwu("nwu"),
    neu("neu"),
    swu("swu"),
    seu("seu");

    public final String ID;

    GiantCropSide(String id) {
        ID = id;
    }

    @Override
    public String asString() {
        return ID;
    }
}
