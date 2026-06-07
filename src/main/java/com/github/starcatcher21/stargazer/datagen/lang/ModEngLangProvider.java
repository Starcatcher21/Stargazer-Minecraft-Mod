package com.github.starcatcher21.stargazer.datagen.lang;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.*;
import com.github.starcatcher21.stargazer.effects.Potions;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEngLangProvider extends FabricLanguageProvider {
    public ModEngLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        // Specifying en_us is optional, by default it is en_us.
        super(dataGenerator, "en_us", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemGroup.STAR_GROUP_KEY, "Stargazer");
        // misc
        translationBuilder.add(ModBlock.COPPER_TELEPORTER, "Copper Teleporter");
        translationBuilder.add(MoonBlocks.GEODE_FRUIT, "Geode Fruit");
        translationBuilder.add(ModBlock.MOON_WELDER, "Moon Welder");
        // Blocks
        translationBuilder.add(ModBlock.GRAVE, "Grave");
        translationBuilder.add(ModBlock.NEGATIVE_BLOCK, "Negative Block");
        translationBuilder.add(ModBlock.INFESTED_CALCITE, "Infested Calcite");
        translationBuilder.add(ModBlock.BONE_LEAVES, "Bone Leaves");
        translationBuilder.add(EyeBloodBlocks.EYE_JAR, "Eye Jar");
        translationBuilder.add(ModBlock.SPRINKLER, "Sprinkler");
        // Moon Rock
        translationBuilder.add(MoonBlocks.MOON_ROCK, "Moon Rock");
        translationBuilder.add(MoonBlocks.POLISHED_MOON_ROCK, "Polished Moon Rock");
        translationBuilder.add(MoonBlocks.MOON_FARMLAND, "Moon Farmland");
        translationBuilder.add(MoonBlocks.MOON_ROCK_NYLIUM, "Moon Rock Nylium");
        translationBuilder.add(MoonBlocks.BLACK_MOON_ROCK, "Black Moon Rock");
        translationBuilder.add(MoonBlocks.POLISHED_BLACK_MOON_ROCK, "Polished Black Moon Rock");
        translationBuilder.add(MoonBlocks.POLISHED_BLACK_MOON_ROCK_PURPLE, "Polished Purple Black Moon Rock");
        translationBuilder.add(MoonBlocks.MOON_ROCK_TILES, "Moon Rock Tiles");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_ROCK_TILES, "Purple Moon Rock Tiles");
        translationBuilder.add(MoonBlocks.MOON_ROCK_BRICKS, "Moon Rock Bricks");
        translationBuilder.add(MoonBlocks.MOON_ROCK_BRICKS_SLAB, "Moon Rock Bricks Slab");
        translationBuilder.add(MoonBlocks.MOON_ROCK_BRICKS_STAIRS, "Moon Rock Bricks Stairs");
        translationBuilder.add(MoonBlocks.CRACKED_MOON_ROCK_BRICKS, "Cracked Moon Rock Bricks");
        translationBuilder.add(MoonBlocks.CHISELED_MOON_ROCK_BRICKS, "Chiseled Moon Rock Bricks");
        translationBuilder.add(MoonBlocks.STAR_FORGE, "Star Forge");
        translationBuilder.add(MoonBlocks.PRISMATIC_ORE, "Prismatic Ore");
        translationBuilder.add(MoonBlocks.PRISMATIC_SHARD_BLOCK, "Prismatic Shard Block");
        translationBuilder.add(ModItems.MOON_GLASS_SHARD, "Moon Glass Shard");
        translationBuilder.add(MoonBlocks.SUN_ENRICHED_MOON_ROCK, "Sun Enriched Moon Rock");
        translationBuilder.add(MoonBlocks.POLISHED_SUN_ENRICHED_MOON_ROCK, "Polished Sun Enriched Moon Rock");
        // Moon Tree
        translationBuilder.add(MoonBlocks.MOON_LEAVES, "Moon Leaves");
        translationBuilder.add(MoonBlocks.MOON_LOG, "Moon Log");
        translationBuilder.add(MoonBlocks.FULL_MOON_LOG, "Full Moon Log");
        translationBuilder.add(MoonBlocks.FULL_MOON_CORE, "Full Moon Core");
        translationBuilder.add(MoonBlocks.STRIPPED_MOON_LOG, "Stripped Moon Log");
        translationBuilder.add(MoonBlocks.MOON_SAPLING, "Moon Sapling");
        translationBuilder.add(MoonBlocks.MOON_PLANKS, "Moon Planks");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_DOOR, "Moon Door");
        translationBuilder.add(StarBlocks.STAR_PLANKS_DOOR, "Star Door");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_SLAB, "Moon Planks Slab");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_STAIRS, "Moon Planks Stairs");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_BUTTON, "Moon Planks Button");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_FENCE, "Moon Planks Fence");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_FENCE_GATE, "Moon Planks Fence Gate");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS, "Red Moon Planks");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS_SLAB, "Red Moon Planks Slab");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS_STAIRS, "Red Moon Planks Stairs");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS_BUTTON, "Red Moon Planks Button");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS_FENCE, "Red Moon Planks Fence");
        translationBuilder.add(MoonBlocks.RED_MOON_PLANKS_FENCE_GATE, "Red Moon Planks Fence Gate");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS, "Blue Moon Planks");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS_SLAB, "Blue Moon Planks Slab");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS_STAIRS, "Blue Moon Planks Stairs");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS_BUTTON, "Blue Moon Planks Button");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS_FENCE, "Blue Moon Planks Fence");
        translationBuilder.add(MoonBlocks.BLUE_MOON_PLANKS_FENCE_GATE, "Blue Moon Planks Fence Gate");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS, "Purple Moon Planks");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS_SLAB, "Purple Moon Planks Slab");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS_STAIRS, "Purple Moon Planks Stairs");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS_BUTTON, "Purple Moon Planks Button");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE, "Purple Moon Planks Fence");
        translationBuilder.add(MoonBlocks.PURPLE_MOON_PLANKS_FENCE_GATE, "Purple Moon Planks Fence Gate");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS, "Yellow Moon Planks");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS_SLAB, "Yellow Moon Planks Slab");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS_STAIRS, "Yellow Moon Planks Stairs");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS_BUTTON, "Yellow Moon Planks Button");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE, "Yellow Moon Planks Fence");
        translationBuilder.add(MoonBlocks.YELLOW_MOON_PLANKS_FENCE_GATE, "Yellow Moon Planks Fence Gate");
        // Star
        translationBuilder.add(StarBlocks.COSMIC_BLOCK, "Cosmic Skybox Block");
        translationBuilder.add(StarBlocks.BORDER_BLOCK, "Border Block");
        translationBuilder.add(StarBlocks.STAR_BARRIER_BLOCK, "Star Barrier");
        translationBuilder.add(StarBlocks.STAR_LOG, "Star Log");
        translationBuilder.add(StarBlocks.STRIPPED_STAR_LOG, "Stripped Star Log");
        translationBuilder.add(StarBlocks.STAR_PLANKS, "Star Planks");
        translationBuilder.add(StarBlocks.STAR_PLANKS_SLAB, "Star Slab");
        translationBuilder.add(StarBlocks.STAR_PLANKS_STAIRS, "Star Stairs");
        translationBuilder.add(StarBlocks.STAR_PLANKS_FENCE, "Star Fence");
        translationBuilder.add(StarBlocks.STAR_PLANKS_FENCE_GATE, "Star Fence Gate");
        translationBuilder.add(StarBlocks.STAR_PLANKS_BUTTON, "Star Button");
        translationBuilder.add(StarBlocks.STAR_LEAVES, "Star Leaves");
        translationBuilder.add(StarBlocks.STAR_SAPLING, "Star Sapling");
        translationBuilder.add(MoonBlocks.STAR_STONE, "Starstone");
        // Curve
        translationBuilder.add(MoonBlocks.CURVE_LOG, "Curve Log");
        translationBuilder.add(MoonBlocks.STRIPPED_CURVE_LOG, "Stripped Curve Log");
        translationBuilder.add(MoonBlocks.CURVE_LEAVES, "Curve Leaves");
        translationBuilder.add(MoonBlocks.CURVE_SAPLING, "Curve Sapling");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS, "Curve Planks");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_DOOR, "Curve Door");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_SLAB, "Curve Planks Slab");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_STAIRS, "Curve Planks Stairs");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_FENCE, "Curve Planks Fence");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_FENCE_GATE, "Curve Planks Fence Gate");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_BUTTON, "Curve Planks Button");
        // Shroom
        translationBuilder.add(MoonBlocks.PURPLE_MUSHROOM_BLOCK, "Purple Mushroom Block");
        translationBuilder.add(MoonBlocks.PURPLE_MUSHROOM, "Purple Mushroom");
        // Blood Birch
        translationBuilder.add(EyeBloodBlocks.EYE_LOG, "Eye Birch Log");
        translationBuilder.add(EyeBloodBlocks.STRIPPED_EYE_LOG, "Stripped Eye Birch Log");
        translationBuilder.add(EyeBloodBlocks.EYE_LEAVES, "Eye Birch Leaves");
        // Darkness
        translationBuilder.add(Darkness.LOG_OF_DARKNESS, "Log of Darkness");
        translationBuilder.add(Darkness.DYLIUM, "Dylium");
        translationBuilder.add(Darkness.ROSE_OF_PAIN, "Rose of Pain");
        translationBuilder.add(Darkness.STRIPPED_LOG_OF_DARKNESS, "Stripped Log of Darkness");
        translationBuilder.add(Darkness.DARKNESS_LEAVES, "Darkness Leaves");
        translationBuilder.add(Darkness.DARKNESS_SAPLING, "Darkness Sapling");
        translationBuilder.add(Darkness.DARKNESS_PLANKS, "Darkness Planks");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_DOOR, "Darkness Door");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_SLAB, "Darkness Planks Slab");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_STAIRS, "Darkness Planks Stairs");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_FENCE, "Darkness Planks Fence");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_FENCE_GATE, "Darkness Planks Fence Gate");
        translationBuilder.add(Darkness.DARKNESS_PLANKS_BUTTON, "Darkness Planks Button");
        // Items
        translationBuilder.add(ModItems.GRAVICE, "Gravel Ice");
        translationBuilder.add(ModItems.STARDUST, "Stardust");
        translationBuilder.add(ModItems.YELLOW_STAR, "Yellow Star");
        translationBuilder.add(ModItems.WISHING_STAR, "Wishing Star");
        translationBuilder.add(ModItems.DREAM_STAR, "Dream Star");
        translationBuilder.add(StarBlocks.RED_STAR_BLOCK, "Red Star Block");
        translationBuilder.add(StarBlocks.BLUE_STAR_BLOCK, "Blue Star Block");
        translationBuilder.add(StarBlocks.YELLOW_STAR_BLOCK, "Yellow Star Block");
        translationBuilder.add(StarBlocks.PURPLE_STAR_BLOCK, "Purple Star Block");
        translationBuilder.add(ModItems.SUN_ENRICHED_YELLOW_STAR, "Sun Enriched Yellow Star");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_LOG, "Yellow Nebula Core");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS, "Yellow Nebula Planks");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS_BUTTON, "Yellow Nebula Button");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS_FENCE, "Yellow Nebula Fence");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS_FENCE_GATE, "Yellow Nebula Fence Gate");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS_SLAB, "Yellow Nebula Slab");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_PLANKS_STAIRS, "Yellow Nebula Stairs");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_REGROW_CORE, "Yellow Nebula Regrow Core");
        translationBuilder.add(Nebulas.YELLOW_NEBULA_LEAVES, "Yellow Nebula Stars");
        translationBuilder.add(ModItems.RED_STAR, "Red Star");
        translationBuilder.add(Nebulas.RED_NEBULA_LOG, "Red Nebula Core");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS, "Red Nebula Planks");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS_BUTTON, "Red Nebula Button");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS_FENCE, "Red Nebula Fence");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS_FENCE_GATE, "Red Nebula Fence Gate");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS_SLAB, "Red Nebula Slab");
        translationBuilder.add(Nebulas.RED_NEBULA_PLANKS_STAIRS, "Red Nebula Stairs");
        translationBuilder.add(Nebulas.RED_NEBULA_REGROW_CORE, "Red Nebula Regrow Core");
        translationBuilder.add(Nebulas.RED_NEBULA_LEAVES, "Red Nebula Stars");
        translationBuilder.add(ModItems.BLUE_STAR, "Blue Star");
        translationBuilder.add(Nebulas.BLUE_NEBULA_LOG, "Blue Nebula Core");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS, "Blue Nebula Planks");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS_BUTTON, "Blue Nebula Button");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS_FENCE, "Blue Nebula Fence");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS_FENCE_GATE, "Blue Nebula Fence Gate");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS_SLAB, "Blue Nebula Slab");
        translationBuilder.add(Nebulas.BLUE_NEBULA_PLANKS_STAIRS, "Blue Nebula Stairs");
        translationBuilder.add(Nebulas.BLUE_NEBULA_REGROW_CORE, "Blue Nebula Regrow Core");
        translationBuilder.add(Nebulas.BLUE_NEBULA_LEAVES, "Blue Nebula Stars");
        translationBuilder.add(ModItems.PURPLE_STAR, "Purple Star");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_LOG, "Purple Nebula Core");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS, "Purple Nebula Planks");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS_BUTTON, "Purple Nebula Button");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS_FENCE, "Purple Nebula Fence");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS_FENCE_GATE, "Purple Nebula Fence Gate");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS_SLAB, "Purple Nebula Slab");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_PLANKS_STAIRS, "Purple Nebula Stairs");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_REGROW_CORE, "Purple Nebula Regrow Core");
        translationBuilder.add(Nebulas.PURPLE_NEBULA_LEAVES, "Purple Nebula Stars");
        translationBuilder.add(ModItems.LODESTAR, "Lodestar");
        translationBuilder.add(ModItems.GEODE_FRUIT, "Geode Fruit");
        translationBuilder.add(ModItems.COOKED_GEODE_FRUIT, "Cooked Geode Fruit");
        translationBuilder.add(ModItems.FULL_COOKED_GEODE_FRUIT, "Cooked Geode Fruit");
        translationBuilder.add(ModItems.BLACK_COOKED_GEODE_FRUIT, "Cooked Geode Fruit");
        translationBuilder.add(ModItems.PRISMATIC_SHARD, "Prismatic Shard");
        translationBuilder.add(ModItems.ECTOPLASM, "Ectoplasm");
        translationBuilder.add(ModItems.COOLER_ECTOPLASM, "Ectoplasm but 20% Cooler");
        translationBuilder.add(ModItems.PRISMATIC_INGOT, "Prismatic Ingot");
        // Spawn Eggs
        translationBuilder.add(ModItems.GHOST_SPAWN_EGG, "Ghost Spawn Egg");
        translationBuilder.add(ModItems.AMETHYST_TURTLE_SPAWN_EGG, "Amethyst Turtle Spawn Egg");
        translationBuilder.add(ModItems.EYE_BAT_SPAWN_EGG, "Eye Bat Spawn Egg");
        // Entity
        translationBuilder.add(EntityRegistry.GHOST_ENTITY, "Ghost");
        translationBuilder.add(EntityRegistry.AMETHYST_TURTLE_ENTITY, "Amethyst Turtle");
        translationBuilder.add(EntityRegistry.EYE_BAT_ENTITY, "Eye Bat");
        // Vegetation
        translationBuilder.add(StarBlocks.STAR_FLOWER, "Star Flower");
        translationBuilder.add(MoonBlocks.SPRUNGUS, "Sprungus");
        translationBuilder.add(StarBlocks.CELESTIAL_STAR_FLOWER, "Celestial Star Flower");
        translationBuilder.add(MoonBlocks.MOON_GRASS, "Moon Grass");
        translationBuilder.add(MoonBlocks.TALL_MOON_GRASS, "Tall Moon Grass");
        translationBuilder.add(MoonBlocks.STAR_TRAP, "Star Trap");
        translationBuilder.add(MoonBlocks.MOON_FERN, "Moon Fern");
        translationBuilder.add(EyeBloodBlocks.EYE_FERN, "Eye Fern");
        translationBuilder.add(EyeBloodBlocks.EYES, "Eyes");
        translationBuilder.add(ModItems.DEAD_EYE_BAT, "Dead Eye Bat");
        translationBuilder.add(ModItems.LIVING_EYE, "Living Eye");
        translationBuilder.add(MoonBlocks.FORGET_ME_NOW, "Forget Me Now");
        translationBuilder.add(ModBlock.BONEFLOWER, "Boneflower");
        translationBuilder.add(Nebulas.RED_TENTACLE_FLOWER, "Red Tentacle flower");
        translationBuilder.add(Nebulas.BLUE_TENTACLE_FLOWER, "Blue Tentacle flower");
        translationBuilder.add(Nebulas.YELLOW_TENTACLE_FLOWER, "Yellow Tentacle flower");
        translationBuilder.add(Nebulas.PURPLE_TENTACLE_FLOWER, "Purple Tentacle flower");
        // Crops
        translationBuilder.add(Crops.DRAGON_CARROT, "Dragon Carrot");
        translationBuilder.add(Crops.BROODY, "Broody");
        translationBuilder.add(Crops.EYE_BALLS, "Eye Balls");
        translationBuilder.add(Crops.GIANT_DRAGON_CARROT, "Giant Dragon Carrot");
        // Potions
        translationBuilder.add("item.minecraft.potion.effect."+Registries.POTION.get(Potions.CosmoFeel.getKey().get()).getBaseName(), "Potion of Cosmic Feeling");
        translationBuilder.add("item.minecraft.splash_potion.effect."+Registries.POTION.get(Potions.CosmoFeel.getKey().get()).getBaseName(), "Splash Potion of Cosmic Feeling");
        translationBuilder.add("item.minecraft.lingering_potion.effect."+Registries.POTION.get(Potions.CosmoFeel.getKey().get()).getBaseName(), "Lingering Potion of Cosmic Feeling");
        translationBuilder.add("item.minecraft.potion.effect."+Registries.POTION.get(Potions.GlassHands.getKey().get()).getBaseName(), "Potion of Glass Hands");
        translationBuilder.add("item.minecraft.splash_potion.effect."+Registries.POTION.get(Potions.GlassHands.getKey().get()).getBaseName(), "Splash Potion of Glass Hands");
        translationBuilder.add("item.minecraft.lingering_potion.effect."+Registries.POTION.get(Potions.GlassHands.getKey().get()).getBaseName(), "Lingering Potion of Glass Hands");
        translationBuilder.add("item.minecraft.potion.effect."+Registries.POTION.get(Potions.Hydro.getKey().get()).getBaseName(), "Potion of Hydrophobic");
        translationBuilder.add("item.minecraft.splash_potion.effect."+Registries.POTION.get(Potions.Hydro.getKey().get()).getBaseName(), "Splash Potion of Hydrophobic");
        translationBuilder.add("item.minecraft.lingering_potion.effect."+Registries.POTION.get(Potions.Hydro.getKey().get()).getBaseName(), "Lingering Potion of Hydrophobic");
        // Effects
        translationBuilder.add("effect.stargazer.hydrophobic", "Hydrophobic");
        translationBuilder.add("effect.stargazer.cosmofeeling", "Cosmic Feeling");
        translationBuilder.add("effect.stargazer.glasshands", "Glass Hands");
        // Screens
        translationBuilder.add("container.starforge", "Starforge");
        translationBuilder.add("container.moon_welder", "Moon Welder");
        // Death
        translationBuilder.add("death.attack.star_trap", "%s was bitten by Star Trap");
        translationBuilder.add("death.attack.water", "%s was burn alive by water");
        // Chess
        translationBuilder.add(Chess.BLACK_CHESSBOARD, "Black Chessboard");
        translationBuilder.add(Chess.WHITE_CHESSBOARD, "White Chessboard");
        translationBuilder.add(Chess.CHESSBOARD, "Chessboard");
        // Hedge
        translationBuilder.add(Hedges.ACACIA_HEDGE, "Acacia Hedge");
        translationBuilder.add(Hedges.BIRCH_HEDGE, "Birch Hedge");
        translationBuilder.add(Hedges.CHERRY_HEDGE, "Cherry Hedge");
        translationBuilder.add(Hedges.CURVE_HEDGE, "Curve Hedge");
        translationBuilder.add(Hedges.DARK_OAK_HEDGE, "Dark Oak Hedge");
        translationBuilder.add(Hedges.DARKNESS_HEDGE, "Darkness Hedge");
        translationBuilder.add(Hedges.JUNGLE_HEDGE, "Jungle Hedge");
        translationBuilder.add(Hedges.MANGROVE_HEDGE, "Mangrove Hedge");
        translationBuilder.add(Hedges.MOON_HEDGE, "Moon Hedge");
        translationBuilder.add(Hedges.OAK_HEDGE, "Oak Hedge");
        translationBuilder.add(Hedges.PALE_HEDGE, "Pale Oak Hedge");
        translationBuilder.add(Hedges.SPRUCE_HEDGE, "Spruce Hedge");
        translationBuilder.add(Hedges.STAR_HEDGE, "Star Hedge");
        // Red Orb
        translationBuilder.add(RedOrbBlocks.RED_ROCK, "Red Rock");
    }
}
