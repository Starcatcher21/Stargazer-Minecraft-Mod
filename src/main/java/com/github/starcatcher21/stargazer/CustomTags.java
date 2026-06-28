package com.github.starcatcher21.stargazer;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CustomTags {
    public static final TagKey<Item> STARDUST = register("stardust");
    public static final TagKey<Item> ICECREAM = register("icecream");
    public static final TagKey<Item> STAR = register("star");
    public static final TagKey<Item> MOON_LOG = register("moon_log");
    public static final TagKey<Item> STAR_LOG = register("star_log");
    public static final TagKey<Item> CURVE_LOG = register("curve_log");
    public static final TagKey<Item> PURPLE_STAR = register("purple_star");
    public static final TagKey<Item> YELLOW_STAR = register("yellow_star");
    public static final TagKey<Item> RED_STAR = register("red_star");
    public static final TagKey<Item> BLUE_STAR = register("blue_star");
    public static final TagKey<Item> STAR_FLOWER = register("star_flower");
    public static final TagKey<Item> COSMIC = register("cosmic");
    public static final TagKey<Item> DARKNESS_LOG = register("darkness_log");
    public static final TagKey<Item> ECTOPLASM = register("ectoplasm");
    public static final TagKey<Item> CHESS_BRICK = register("chess_brick");

    public static final TagKey<Block> COPPER_BLOCKS = registerBlock("copper_block");
    public static final TagKey<Block> STAR_HAMMER_MINABLE = registerBlock("star_hammer_minable");

    public static final TagKey<BannerPattern> STAR_PATTERNS = registerPatterns("star");

    private static TagKey<Item> register(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Stargazer.MOD_ID, name));
    }
    private static TagKey<Block> registerBlock(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Stargazer.MOD_ID, name));
    }
    private static TagKey<BannerPattern> registerPatterns(String id) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(Stargazer.MOD_ID, id));
    }
    public static void init() {}
}
