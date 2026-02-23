package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class PointOfIntrests {
    public static final PointOfInterestType COPPER_TELEPORTER = PointOfInterestHelper.register(Identifier.of(Stargazer.MOD_ID, "copper_teleporter"), 0, 1, ModBlock.COPPER_TELEPORTER);

    public static void init() {}
}
