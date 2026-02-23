package com.github.starcatcher21.stargazer.datagen.lang;

import com.github.starcatcher21.stargazer.CreativeTab.ItemGroup;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.Crops;
import com.github.starcatcher21.stargazer.block.register.EyeBloodBlocks;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
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
        // Moon Tree
        translationBuilder.add(MoonBlocks.MOON_LEAVES, "Moon Leaves");
        translationBuilder.add(MoonBlocks.MOON_LOG, "Moon Log");
        translationBuilder.add(MoonBlocks.STRIPPED_MOON_LOG, "Stripped Moon Log");
        translationBuilder.add(MoonBlocks.MOON_SAPLING, "Moon Sapling");
        translationBuilder.add(MoonBlocks.MOON_PLANKS, "Moon Planks");
        translationBuilder.add(MoonBlocks.MOON_PLANKS_DOOR, "Moon Planks Door");
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
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_SLAB, "Curve Slab");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_STAIRS, "Curve Stairs");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_FENCE, "Curve Fence");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_FENCE_GATE, "Curve Fence Gate");
        translationBuilder.add(MoonBlocks.CURVE_PLANKS_BUTTON, "Curve Button");
        // Shroom
        translationBuilder.add(MoonBlocks.PURPLE_MUSHROOM_BLOCK, "Purple Mushroom Block");
        translationBuilder.add(MoonBlocks.PURPLE_MUSHROOM, "Purple Mushroom");
        // Blood Birch
        translationBuilder.add(EyeBloodBlocks.EYE_LOG, "Eye Birch Log");
        translationBuilder.add(EyeBloodBlocks.STRIPPED_EYE_LOG, "Stripped Eye Birch Log");
        translationBuilder.add(EyeBloodBlocks.EYE_LEAVES, "Eye Birch Leaves");
        // Items
        translationBuilder.add(ModItems.GRAVICE, "Gravel Ice");
        translationBuilder.add(ModItems.STARDUST, "Stardust");
        translationBuilder.add(ModItems.YELLOW_STAR, "Star");
        translationBuilder.add(ModItems.RED_STAR, "Star");
        translationBuilder.add(ModItems.BLUE_STAR, "Star");
        translationBuilder.add(ModItems.PURPLE_STAR, "Star");
        translationBuilder.add(ModItems.LODESTAR, "Lodestar");
        translationBuilder.add(ModItems.GEODE_FRUIT, "Geode Fruit");
        translationBuilder.add(ModItems.PRISMATIC_SHARD, "Prismatic Shard");
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
        translationBuilder.add(StarBlocks.CELESTIAL_STAR_FLOWER, "Celestial Star Flower");
        translationBuilder.add(MoonBlocks.MOON_GRASS, "Moon Grass");
        translationBuilder.add(MoonBlocks.TALL_MOON_GRASS, "Tall Moon Grass");
        translationBuilder.add(MoonBlocks.STAR_TRAP, "Star Trap");
        translationBuilder.add(MoonBlocks.MOON_FERN, "Moon Fern");
        translationBuilder.add(EyeBloodBlocks.EYE_FERN, "Eye Fern");
        translationBuilder.add(EyeBloodBlocks.EYES, "Eyes");
        translationBuilder.add(MoonBlocks.FORGET_ME_NOW, "Forget Me Now");
        // Crops
        translationBuilder.add(Crops.DRAGON_CARROT, "Dragon Carrot");
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
        // Death
        translationBuilder.add("death.attack.star_trap", "%s was bitten by Star Trap");
        translationBuilder.add("death.attack.water", "%s was burn alive by water");
    }
}
