package com.github.starcatcher21.stargazer.block;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.clases.CosmicFlower;
import com.github.starcatcher21.stargazer.block.clases.InfestedCalcite;
import com.github.starcatcher21.stargazer.block.clases.MoonWelder;
import com.github.starcatcher21.stargazer.block.clases.Sprinkler;
import com.github.starcatcher21.stargazer.block.clases.grave.Grave;
import com.github.starcatcher21.stargazer.block.clases.negative.NegativeBlock;
import com.github.starcatcher21.stargazer.block.clases.noblue.NoBlueBlock;
import com.github.starcatcher21.stargazer.block.clases.nogreen.NoGreenBlock;
import com.github.starcatcher21.stargazer.block.clases.nored.NoRedBlock;
import com.github.starcatcher21.stargazer.block.clases.teleporter.CopperTeleporter;
import com.github.starcatcher21.stargazer.block.clases.teleporter.DarkTeleporter;
import com.github.starcatcher21.stargazer.block.clases.teleporter.RedTeleporter;
import com.github.starcatcher21.stargazer.block.register.*;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

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
            .requiresTool()
            .strength(0.2f)
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block NORED_BLOCK = register("no_red_block", NoRedBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .noCollision()
            .requiresTool()
            .strength(0.2f)
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block NOGREEN_BLOCK = register("no_green_block", NoGreenBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .noCollision()
            .requiresTool()
            .strength(0.2f)
            .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block NOBLUE_BLOCK = register("no_blue_block", NoBlueBlock::new, AbstractBlock.Settings.create()
            .nonOpaque()
            .noCollision()
            .requiresTool()
            .strength(0.2f)
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
    public static final Block BONEFLOWER = register("boneflower", settings -> new CosmicFlower(StatusEffects.BLINDNESS, 5.0f, settings), AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .offset(AbstractBlock.OffsetType.XZ)
            .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_BONEFLOWER = registerWoItem("potted_boneflower", settings -> new FlowerPotBlock(BONEFLOWER, settings), AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM).nonOpaque());

    public static final Block COPPER_TELEPORTER = registerWoItem("copper_teleporter", CopperTeleporter::new, AbstractBlock.Settings.create()
            .solid()
            .requiresTool().strength(2.0f, 40.0f)
            .nonOpaque()
            .sounds(BlockSoundGroup.COPPER)
    );

    public static final Block DARK_TELEPORTER = registerWoItem("dark_teleporter", DarkTeleporter::new, AbstractBlock.Settings.create()
            .solid()
            .requiresTool().strength(2.0f, 40.0f)
            .nonOpaque()
            .sounds(BlockSoundGroup.NETHER_BRICKS)
    );
    public static final Block RED_TELEPORTER = registerWoItem("red_teleporter", RedTeleporter::new, AbstractBlock.Settings.create()
            .solid()
            .requiresTool().strength(2.0f, 40.0f)
            .nonOpaque()
            .sounds(BlockSoundGroup.STONE)
    );

    public static final Block SPRINKLER = register("sprinkler", Sprinkler::new, AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .requiresTool().strength(2.0f, 40.0f)
            .sounds(BlockSoundGroup.METAL)
    );

    public static final Block MOON_WELDER = register("moon_welder", MoonWelder::new, AbstractBlock.Settings.create()
            .solid()
            .nonOpaque()
            .requiresTool().strength(2.0f, 2.0f)
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

    public static void init() {
        Fluids.init();
        MoonBlocks.init();
        StarBlocks.init();
        EyeBloodBlocks.init();
        Crops.init();
        Darkness.init();
        Nebulas.init();
        Chess.init();
        RedOrbBlocks.init();
        Hedges.init();
        Wander.init();
    }
}
