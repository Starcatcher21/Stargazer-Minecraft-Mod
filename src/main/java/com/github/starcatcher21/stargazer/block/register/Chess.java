package com.github.starcatcher21.stargazer.block.register;

import com.github.starcatcher21.stargazer.block.clases.Chessboard;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static com.github.starcatcher21.stargazer.block.ModBlock.register;

public class Chess {

    public static final RegistrySupplier<Block> WHITE_CHESSBOARD = register("white_chessboard", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.0F)
            .mapColor(MapColor.SNOW)
    );

    public static final RegistrySupplier<Block> BLACK_CHESSBOARD = register("black_chessboard", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );

    public static final RegistrySupplier<Block> CHESSBOARD = register("chessboard", Chessboard::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.WOOD)
            .strength(0.4f)
            .mapColor(MapColor.PODZOL)
    );

    public static final RegistrySupplier<Block> WHITE_BRICKS = register("white_bricks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.0F)
            .mapColor(MapColor.SNOW)
    );

    public static final RegistrySupplier<Block> BLACK_BRICKS = register("black_bricks", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.0F)
            .mapColor(MapColor.COLOR_BLACK)
    );

    public static void init() {
    }
}