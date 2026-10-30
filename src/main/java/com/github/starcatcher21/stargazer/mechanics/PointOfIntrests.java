package com.github.starcatcher21.stargazer.mechanics;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Chess;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;

public class PointOfIntrests {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Stargazer.MOD_ID, Registries.POINT_OF_INTEREST_TYPE);

    public static final RegistrySupplier<PoiType> COPPER_TELEPORTER = registerPOI("copper_teleporter",ModBlock.COPPER_TELEPORTER, 0, 1);
    public static final RegistrySupplier<PoiType> CHESS_TELEPORTER = registerPOI("chessboard", Chess.CHESSBOARD, 0, 1);
    public static final RegistrySupplier<PoiType> RED_ORB_TELEPORTER = registerPOI("red_orb_platform", ModBlock.RED_TELEPORTER, 0, 1);
    public static final RegistrySupplier<PoiType> DARK_TELEPORTER = registerPOI("dark_teleporter", ModBlock.DARK_TELEPORTER, 0, 1);
    public static final RegistrySupplier<PoiType> STAR_FORGE = registerPOI("star_forge", MoonBlocks.STAR_FORGE, 1, 1);

    public static final ResourceKey<PoiType> COPPER_TELEPORTER_KEY = registerPOIKey("copper_teleporter");
    public static final ResourceKey<PoiType> CHESS_TELEPORTER_KEY = registerPOIKey("chessboard");
    public static final ResourceKey<PoiType> RED_ORB_TELEPORTER_KEY = registerPOIKey("red_orb_platform");
    public static final ResourceKey<PoiType> DARK_TELEPORTER_KEY = registerPOIKey("dark_teleporter");
    public static final ResourceKey<PoiType> STAR_FORGE_KEY = registerPOIKey("star_forge");

    public static ResourceKey<PoiType> registerPOIKey(String name) {
        return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }

    private static RegistrySupplier<PoiType> registerPOI(String name, RegistrySupplier<Block> block, int ticketCount, int searchDistance) {
        return POI_TYPES.register(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name), () -> new PoiType(
                        ImmutableSet.copyOf(block.get().getStateDefinition().getPossibleStates()), ticketCount,
                        searchDistance
                )
        );
    }

    public static void init() {
        POI_TYPES.register();
    }
}
