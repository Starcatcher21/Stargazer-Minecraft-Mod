// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.classes.WishingStarItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import static com.github.starcatcher21.stargazer.item.ModItems.register;

public class WishingStars {
    public static final RegistrySupplier<Item> WHITE_WISHING_STAR = register("white_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.WHITE, settings), new Item.Properties());
    public static final RegistrySupplier<Item> LIGHT_GRAY_WISHING_STAR = register("light_gray_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.LIGHT_GRAY, settings), new Item.Properties());
    public static final RegistrySupplier<Item> GRAY_WISHING_STAR = register("gray_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.GRAY, settings), new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_WISHING_STAR = register("black_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.BLACK, settings), new Item.Properties());
    public static final RegistrySupplier<Item> RED_WISHING_STAR = register("red_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.RED, settings), new Item.Properties());
    public static final RegistrySupplier<Item> LIME_WISHING_STAR = register("lime_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.LIME, settings), new Item.Properties());
    public static final RegistrySupplier<Item> GREEN_WISHING_STAR = register("green_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.GREEN, settings), new Item.Properties());
    public static final RegistrySupplier<Item> CYAN_WISHING_STAR = register("cyan_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.CYAN, settings), new Item.Properties());
    public static final RegistrySupplier<Item> LIGHT_BLUE_WISHING_STAR = register("light_blue_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.LIGHT_BLUE, settings), new Item.Properties());
    public static final RegistrySupplier<Item> BLUE_WISHING_STAR = register("blue_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.BLUE, settings), new Item.Properties());
    public static final RegistrySupplier<Item> PURPLE_WISHING_STAR = register("purple_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.PURPLE, settings), new Item.Properties());
    public static final RegistrySupplier<Item> MAGENTA_WISHING_STAR = register("magenta_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.MAGENTA, settings), new Item.Properties());
    public static final RegistrySupplier<Item> PINK_WISHING_STAR = register("pink_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.PINK, settings), new Item.Properties());
    public static final RegistrySupplier<Item> ORANGE_WISHING_STAR = register("orange_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.ORANGE, settings), new Item.Properties());
    public static final RegistrySupplier<Item> BROWN_WISHING_STAR = register("brown_wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.BROWN, settings), new Item.Properties());
    public static final RegistrySupplier<Item> WISHING_STAR = register("wishing_star", (Item.Properties settings) -> new WishingStarItem(EntityRegistry.STAR_ENTITY.get(), DyeColor.YELLOW, settings), new Item.Properties());
    public static void init() {}
}
