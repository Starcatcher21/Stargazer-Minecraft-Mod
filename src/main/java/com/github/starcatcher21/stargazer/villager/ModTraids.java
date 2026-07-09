package com.github.starcatcher21.stargazer.villager;

import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import static com.github.starcatcher21.stargazer.villager.ModVillagers.ASTROLOGISTS_KEY;

public class ModTraids {
    public static void init() {
//        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 1, factories -> {
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(4, 7)),
//                    new ItemStack(ModItems.PURPLE_STAR, random.nextIntBetweenInclusive(3, 5)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(4, 7)),
//                    new ItemStack(ModItems.BLUE_STAR, random.nextIntBetweenInclusive(3, 5)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(4, 7)),
//                    new ItemStack(ModItems.RED_STAR, random.nextIntBetweenInclusive(3, 5)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(4, 7)),
//                    new ItemStack(ModItems.YELLOW_STAR, random.nextIntBetweenInclusive(3, 5)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.GLASS, random.nextIntBetweenInclusive(6, 10)),
//                    new ItemStack(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(1, 3)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.COPPER_INGOT, random.nextIntBetweenInclusive(4, 8)),
//                    new ItemStack(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(1, 5)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.RAW_COPPER, random.nextIntBetweenInclusive(8, 16)),
//                    new ItemStack(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(1, 2)), random.nextIntBetweenInclusive(4, 10), 4, 0.04f
//            )));
//        });
//        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 2, factories -> {
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 16)),
//                    new ItemStack(ModItems.DREAM_BUCKET, 1), random.nextIntBetweenInclusive(3, 9), 5, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 9)),
//                    new ItemStack(ModItems.STAR_BANNER_PATTERN, 1), random.nextIntBetweenInclusive(5, 14), 5, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.EMERALD, random.nextIntBetweenInclusive(2, 6)),
//                    new ItemStack(Items.SPYGLASS, 1), random.nextIntBetweenInclusive(3, 6), 5, 0.04f
//            )));
//        });
//        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 3, factories -> {
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 16)),
//                    new ItemStack(ModBlock.SPRINKLER, random.nextIntBetweenInclusive(1,3)), random.nextIntBetweenInclusive(5, 10), 6, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 9)),
//                    new ItemStack(MoonBlocks.BLACK_MOON_ROCK, random.nextIntBetweenInclusive(8, 32)), random.nextIntBetweenInclusive(10, 16), 6, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 9)),
//                    new ItemStack(MoonBlocks.MOON_ROCK, random.nextIntBetweenInclusive(8, 32)), random.nextIntBetweenInclusive(10, 16), 6, 0.04f
//            )));
//        });
//        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 4, factories -> {
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 16)),
//                    new ItemStack(MoonBlocks.SUN_ENRICHED_MOON_ROCK, random.nextIntBetweenInclusive(1,4)), random.nextIntBetweenInclusive(10, 16), 10, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 9)),
//                    new ItemStack(ModItems.COOKED_GEODE_FRUIT, random.nextIntBetweenInclusive(2, 6)), random.nextIntBetweenInclusive(10, 16), 10, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(6, 20)),
//                    new ItemStack(StarBlocks.STAR_DISPLAY, random.nextIntBetweenInclusive(1, 2)), random.nextIntBetweenInclusive(13, 18), 12, 0.04f
//            )));
//        });
//        TradeOfferHelper.registerVillagerOffers(ASTROLOGISTS_KEY, 5, factories -> {
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(3, 5)),
//                    new ItemStack(ModItems.THROWABLE_STAR, random.nextIntBetweenInclusive(1, 2)), random.nextIntBetweenInclusive(4, 8), 10, 0.04f
//            )));
//            factories.add(((world, entity, random) -> new MerchantOffer(
//                    new ItemCost(Items.AMETHYST_SHARD, random.nextIntBetweenInclusive(32, 128)),
//                    new ItemStack(ModItems.WINGED_STAR, 1), 1, 100, 0.04f
//            )));
//        });
    }
}
