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
import com.github.starcatcher21.stargazer.block.clases.teleporter.EndTeleporter;
import com.github.starcatcher21.stargazer.block.clases.teleporter.RedTeleporter;
import com.github.starcatcher21.stargazer.block.register.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.function.Function;

public class ModBlock {
    public static final Block GRAVE = register("grave", Grave::new, BlockBehaviour.Properties.of()
            .strength(1.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block NEGATIVE_BLOCK = register("negative_block", NegativeBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block NORED_BLOCK = register("no_red_block", NoRedBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block NOGREEN_BLOCK = register("no_green_block", NoGreenBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block NOBLUE_BLOCK = register("no_blue_block", NoBlueBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );
    public static final Block INFESTED_CALCITE = register("infested_calcite", InfestedCalcite::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .strength(1.4f)
            .randomTicks()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
    );
    public static final Block BONE_LEAVES = register("bone_leaves", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.SNOW)
    );
    public static final Block BONEFLOWER = register("boneflower", settings -> new CosmicFlower(MobEffects.BLINDNESS, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );
    public static final Block POTTED_BONEFLOWER = registerWoItem("potted_boneflower", settings -> new FlowerPotBlock(BONEFLOWER, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final Block COPPER_TELEPORTER = registerWoItem("copper_teleporter", CopperTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.COPPER)
    );

    public static final Block DARK_TELEPORTER = registerWoItem("dark_teleporter", DarkTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.NETHER_BRICKS)
    );
    public static final Block END_TELEPORTER = registerWoItem("end_teleporter", EndTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
    );
    public static final Block RED_TELEPORTER = registerWoItem("red_teleporter", RedTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
    );

    public static final Block SPRINKLER = register("sprinkler", Sprinkler::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .sound(SoundType.METAL)
    );

    public static final Block MOON_WELDER = register("moon_welder", MoonWelder::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .requiresCorrectToolForDrops().strength(2.0f, 2.0f)
            .sound(SoundType.METAL)
    );

    public static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        final ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, identifier);
        final Block block = Blocks.register(registryKey, factory, settings);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifier);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return block;
    }
    public static Block registerWoItem(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        final ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, identifier);
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
        Energy.init();
    }
}
