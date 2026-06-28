package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.classes.WishingStarItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

import static com.github.starcatcher21.stargazer.item.ModItems.register;

public class WishingStars {
    public static final Item WHITE_WISHING_STAR = register("white_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.WHITE, settings), new Item.Settings());
    public static final Item LIGHT_GRAY_WISHING_STAR = register("light_gray_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.LIGHT_GRAY, settings), new Item.Settings());
    public static final Item GRAY_WISHING_STAR = register("gray_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.GRAY, settings), new Item.Settings());
    public static final Item BLACK_WISHING_STAR = register("black_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.BLACK, settings), new Item.Settings());
    public static final Item RED_WISHING_STAR = register("red_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.RED, settings), new Item.Settings());
    public static final Item LIME_WISHING_STAR = register("lime_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.LIME, settings), new Item.Settings());
    public static final Item GREEN_WISHING_STAR = register("green_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.GREEN, settings), new Item.Settings());
    public static final Item CYAN_WISHING_STAR = register("cyan_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.CYAN, settings), new Item.Settings());
    public static final Item LIGHT_BLUE_WISHING_STAR = register("light_blue_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.LIGHT_BLUE, settings), new Item.Settings());
    public static final Item BLUE_WISHING_STAR = register("blue_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.BLUE, settings), new Item.Settings());
    public static final Item PURPLE_WISHING_STAR = register("purple_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.PURPLE, settings), new Item.Settings());
    public static final Item MAGENTA_WISHING_STAR = register("magenta_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.MAGENTA, settings), new Item.Settings());
    public static final Item PINK_WISHING_STAR = register("pink_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.PINK, settings), new Item.Settings());
    public static final Item ORANGE_WISHING_STAR = register("orange_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.ORANGE, settings), new Item.Settings());
    public static final Item BROWN_WISHING_STAR = register("brown_wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.BROWN, settings), new Item.Settings());
    public static final Item WISHING_STAR = register("wishing_star", (Item.Settings settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY, DyeColor.YELLOW, settings), new Item.Settings());
    public static void init() {}
}
