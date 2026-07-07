package com.github.starcatcher21.stargazer.villager;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import static com.github.starcatcher21.stargazer.villager.ModVillagers.ASTROLOGISTS_KEY;

public class ModTraids {
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 1, factories -> {
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(4, 7)),
                    new ItemStack(ModItems.PURPLE_STAR, random.nextBetween(3, 5)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(4, 7)),
                    new ItemStack(ModItems.BLUE_STAR, random.nextBetween(3, 5)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(4, 7)),
                    new ItemStack(ModItems.RED_STAR, random.nextBetween(3, 5)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(4, 7)),
                    new ItemStack(ModItems.YELLOW_STAR, random.nextBetween(3, 5)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.GLASS, random.nextBetween(6, 10)),
                    new ItemStack(Items.AMETHYST_SHARD, random.nextBetween(1, 3)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.COPPER_INGOT, random.nextBetween(4, 8)),
                    new ItemStack(Items.AMETHYST_SHARD, random.nextBetween(1, 5)), random.nextBetween(4, 10), 4, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.RAW_COPPER, random.nextBetween(8, 16)),
                    new ItemStack(Items.AMETHYST_SHARD, random.nextBetween(1, 2)), random.nextBetween(4, 10), 4, 0.04f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 2, factories -> {
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 16)),
                    new ItemStack(ModItems.DREAM_BUCKET, 1), random.nextBetween(3, 9), 5, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 9)),
                    new ItemStack(ModItems.STAR_BANNER_PATTERN, 1), random.nextBetween(5, 14), 5, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, random.nextBetween(2, 6)),
                    new ItemStack(Items.SPYGLASS, 1), random.nextBetween(3, 6), 5, 0.04f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 3, factories -> {
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 16)),
                    new ItemStack(ModBlock.SPRINKLER, random.nextBetween(1,3)), random.nextBetween(5, 10), 6, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 9)),
                    new ItemStack(MoonBlocks.BLACK_MOON_ROCK, random.nextBetween(8, 32)), random.nextBetween(10, 16), 6, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 9)),
                    new ItemStack(MoonBlocks.MOON_ROCK, random.nextBetween(8, 32)), random.nextBetween(10, 16), 6, 0.04f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 4, factories -> {
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 16)),
                    new ItemStack(MoonBlocks.SUN_ENRICHED_MOON_ROCK, random.nextBetween(1,4)), random.nextBetween(10, 16), 10, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 9)),
                    new ItemStack(ModItems.COOKED_GEODE_FRUIT, random.nextBetween(2, 6)), random.nextBetween(10, 16), 10, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(6, 20)),
                    new ItemStack(StarBlocks.STAR_DISPLAY, random.nextBetween(1, 2)), random.nextBetween(13, 18), 12, 0.04f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 5, factories -> {
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(3, 5)),
                    new ItemStack(ModItems.THROWABLE_STAR, random.nextBetween(1, 2)), random.nextBetween(4, 8), 10, 0.04f
            )));
            factories.add(((world, entity, random) -> new TradeOffer(
                    new TradedItem(Items.AMETHYST_SHARD, random.nextBetween(32, 128)),
                    new ItemStack(ModItems.WINGED_STAR, 1), 1, 100, 0.04f
            )));
        });
    }
}
