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
import com.github.starcatcher21.stargazer.item.ModItems;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.github.starcatcher21.stargazer.block.register.MoonBlocks.COLORED_PLANKS;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Stargazer.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Stargazer.MOD_ID, Registries.ITEM);

    private static final Map<Identifier, Block> INSTANCE_CACHE = new ConcurrentHashMap<>();

    public static void cacheBlock(Identifier id, Block block) {
        if (id != null && block != null) {
            INSTANCE_CACHE.put(id, block);
        }
    }

    public static Block getRaw(RegistrySupplier<Block> supplier) {
        if (supplier == null) return null;
        Identifier id = supplier.getId();
        if (id != null) {
            Block cached = INSTANCE_CACHE.get(id);
            if (cached != null) return cached;
        }
        return supplier.get();
    }

    public static final RegistrySupplier<Block> GRAVE = register("grave", Grave::new, BlockBehaviour.Properties.of()
            .strength(1.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final RegistrySupplier<Block> NEGATIVE_BLOCK = register("negative_block", NegativeBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );

    public static final RegistrySupplier<Block> NORED_BLOCK = register("no_red_block", NoRedBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );

    public static final RegistrySupplier<Block> NOGREEN_BLOCK = register("no_green_block", NoGreenBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );

    public static final RegistrySupplier<Block> NOBLUE_BLOCK = register("no_blue_block", NoBlueBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion()
            .noCollision()
            .requiresCorrectToolForDrops()
            .strength(0.2f)
            .pushReaction(PushReaction.BLOCK)
    );

    public static final RegistrySupplier<Block> INFESTED_CALCITE = register("infested_calcite", InfestedCalcite::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .strength(1.4f)
            .randomTicks()
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
    );

    public static final RegistrySupplier<Block> BONE_LEAVES = register("bone_leaves", Block::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .strength(0.2F)
            .mapColor(MapColor.SNOW)
    );

    public static final RegistrySupplier<Block> BONEFLOWER = register("boneflower", settings -> new CosmicFlower(MobEffects.BLINDNESS, 5.0f, settings), BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
    );

    public static final RegistrySupplier<Block> POTTED_BONEFLOWER = registerWoItem("potted_boneflower", settings -> new FlowerPotBlock(getRaw(BONEFLOWER), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM).noOcclusion());

    public static final RegistrySupplier<Block> COPPER_TELEPORTER = registerWoItem("copper_teleporter", CopperTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.COPPER)
    );

    public static final RegistrySupplier<Block> DARK_TELEPORTER = registerWoItem("dark_teleporter", DarkTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.NETHER_BRICKS)
    );

    public static final RegistrySupplier<Block> END_TELEPORTER = registerWoItem("end_teleporter", EndTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
    );

    public static final RegistrySupplier<Block> RED_TELEPORTER = registerWoItem("red_teleporter", RedTeleporter::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .noOcclusion()
            .sound(SoundType.STONE)
    );

    public static final RegistrySupplier<Block> SPRINKLER = register("sprinkler", Sprinkler::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .requiresCorrectToolForDrops().strength(2.0f, 40.0f)
            .sound(SoundType.METAL)
    );

    public static final RegistrySupplier<Block> MOON_WELDER = register("moon_welder", MoonWelder::new, BlockBehaviour.Properties.of()
            .forceSolidOn()
            .noOcclusion()
            .requiresCorrectToolForDrops().strength(2.0f, 2.0f)
            .sound(SoundType.METAL)
    );

    public static final RegistrySupplier<Block> AMETHYST_DUST_BLOCK = register("amethyst_dust_block", properties -> new SandBlock(new ColorRGBA(0x8B5DCAFF), properties), BlockBehaviour.Properties.of()
            .sound(SoundType.SAND)
    );
    public static final RegistrySupplier<Block> PURE_AMETHYST_DUST_BLOCK = register("pure_amethyst_dust_block", properties -> new SandBlock(new ColorRGBA(0x8B5DCAFF), properties), BlockBehaviour.Properties.of()
            .sound(SoundType.SAND)
    );

    public static RegistrySupplier<Block> register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        RegistrySupplier<Block> blockSupplier = registerWoItem(path, factory, settings);

        Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, identifier);

        ITEMS.register(path, () -> new BlockItem(getRaw(blockSupplier), new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));

        return blockSupplier;
    }

    public static RegistrySupplier<Block> registerWoItem(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, identifier);

        return BLOCKS.register(path, () -> {
            Block block = factory.apply(settings.setId(registryKey));
            INSTANCE_CACHE.put(identifier, block);
            return block;
        });
    }

    public static void init() {
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

        BLOCKS.register();
        ITEMS.register();

        COLORED_PLANKS.put(ModItems.RED_STAR, MoonBlocks.RED_MOON_PLANKS);
        COLORED_PLANKS.put(ModItems.BLUE_STAR, MoonBlocks.BLUE_MOON_PLANKS);
        COLORED_PLANKS.put(ModItems.YELLOW_STAR, MoonBlocks.YELLOW_MOON_PLANKS);
        COLORED_PLANKS.put(ModItems.PURPLE_STAR, MoonBlocks.PURPLE_MOON_PLANKS);
    }
}