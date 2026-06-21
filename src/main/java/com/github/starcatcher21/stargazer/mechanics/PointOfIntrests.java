package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class PointOfIntrests {
    public static final PointOfInterestType COPPER_TELEPORTER = PointOfInterestHelper.register(Identifier.of(Stargazer.MOD_ID, "copper_teleporter"), 0, 1, ModBlock.COPPER_TELEPORTER);
    public static final PointOfInterestType CHESS_TELEPORTER = PointOfInterestHelper.register(Identifier.of(Stargazer.MOD_ID, "chessboard"), 0, 1, Chess.CHESSBOARD);
    public static final PointOfInterestType RED_ORB_TELEPORTER = PointOfInterestHelper.register(Identifier.of(Stargazer.MOD_ID, "red_orb_platform"), 0, 1, RedOrbBlocks.RED_ORB_PLATFORM);
    public static final PointOfInterestType DARK_TELEPORTER = PointOfInterestHelper.register(Identifier.of(Stargazer.MOD_ID, "dark_teleporter"), 0, 1, ModBlock.DARK_TELEPORTER);

    public static void init() {}
}
