package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.InfestedCalcite;
import com.github.starcatcher21.stargazer.block.clases.Sprinkler;
import com.github.starcatcher21.stargazer.block.clases.grave.Grave;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlock;
import com.github.starcatcher21.stargazer.block.clases.teleporter.CopperTeleporter;
import com.github.starcatcher21.stargazer.block.register.*;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Set;
import java.util.function.Function;

public class ModBlock {
    public static final Block GRAVE = register("grave", Grave::new, AbstractBlock.Settings.create()
            .strength(1.0f)
            .nonOpaque()
            .sounds(BlockSoundGroup.STONE)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block NEGATIVE_BLOCK = register("negative_block", NegativeBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .noCollision()
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block INFESTED_CALCITE = register("infested_calcite", InfestedCalcite::new, AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .strength(1.4f)
            .ticksRandomly()
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
    );
    public static final Block BONE_LEAVES = register("bone_leaves", Block::new, AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.WHITE)
    );

    public static final Block COPPER_TELEPORTER = registerWoItem("copper_teleporter", CopperTeleporter::new, AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.COPPER)
    );

    public static final Block SPRINKLER = register("sprinkler", Sprinkler::new, AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .sounds(BlockSoundGroup.METAL)
    );

    public static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(Stargazer.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }
    public static Block registerWoItem(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(Stargazer.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        final Block block = Blocks.register(registryKey, factory, settings);
        return block;
    }

    public static Set<Block> saplings = new ObjectArraySet<>();
    public static void init() {
        MoonBlocks.init();
        StarBlocks.init();
        EyeBloodBlocks.init();
        Crops.init();
        Darkness.init();
        saplings.add(StarBlocks.STAR_SAPLING);
        saplings.add(MoonBlocks.MOON_SAPLING);
    }
}
