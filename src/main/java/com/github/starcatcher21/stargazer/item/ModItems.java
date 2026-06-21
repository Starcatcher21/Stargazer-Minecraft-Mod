package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.classes.DarkStar;
import com.github.starcatcher21.stargazer.item.classes.LodeStar;
import net.minecraft.block.Block;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.function.Function;

public final class ModItems {
    public static final Item STARDUST = register("stardust", Item::new, new Item.Settings());
    public static final Item MOON_GLASS_SHARD = register("moon_glass_shard", Item::new, new Item.Settings());
    public static final Item PRISMATIC_SHARD = register("prismatic_shard", Item::new, new Item.Settings());
    public static final Item PRISMATIC_INGOT = register("prismatic_ingot", Item::new, new Item.Settings());
    public static final Item YELLOW_STAR = register("yellow_star", Item::new, new Item.Settings());
    public static final Item SUN_ENRICHED_YELLOW_STAR = register("sun_enriched_yellow_star", Item::new, new Item.Settings());
    public static final Item WINGED_STAR = register("winged_star", Item::new, new Item.Settings());
    public static final Item WISHING_STAR = register("wishing_star", (Item.Settings settings) -> new BoatItem(EntityRegistry.STAR_ENTITY, settings), new Item.Settings());
    public static final Item RED_STAR = register("red_star", Item::new, new Item.Settings());
    public static final Item BLUE_STAR = register("blue_star", Item::new, new Item.Settings());
    public static final Item PURPLE_STAR = register("purple_star", Item::new, new Item.Settings());
    public static final Item DREAM_STAR = register("dream_star", Item::new, new Item.Settings());
    public static final Item LODESTAR = register("lodestar", LodeStar::new, new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item DARKSTAR = register("darkstar", DarkStar::new, new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item GEODE_FRUIT = register("geode_fruit", Item::new, new Item.Settings());
    public static final Item GHOST_SPAWN_EGG = register("ghost_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.GHOST_ENTITY));
    public static final Item EYE_BAT_SPAWN_EGG = register("eye_bat_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.EYE_BAT_ENTITY));
    public static final Item AMETHYST_TURTLE_SPAWN_EGG = register("amethyst_turtle_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.AMETHYST_TURTLE_ENTITY));
    public static final Item ROOK_SPAWN_EGG = register("rook_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.ROOK_ENTITY));
    public static final Item BLACK_ROOK_SPAWN_EGG = register("black_rook_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.BLACK_ROOK_ENTITY));
    public static final Item SCRUBY_SPAWN_EGG = register("scruby_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(EntityRegistry.SCRUBY_ENTITY));
    public static final Item DEAD_EYE_BAT = register("dead_eye_bat", Item::new, new Item.Settings());
    public static final Item LIVING_EYE = register("living_eye", Item::new, new Item.Settings());
    public static final Item COOKED_GEODE_FRUIT = register("cooked_geode_fruit", Item::new, new Item.Settings()
            .food(new FoodComponent(8, 4, true))
    );
    public static final Item FULL_COOKED_GEODE_FRUIT = register("full_cooked_geode_fruit", Item::new, new Item.Settings()
            .food(new FoodComponent(14, 20, true),
                    ConsumableComponent.builder()
                            .consumeEffect(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return Type.APPLY_EFFECTS;
                                }

                                @Override
                                public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
                                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.COSMO, 1200));
                                    user.addStatusEffect(new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.ABSORPTION, 1200, 2));
                                    return true;
                                }
                            })
                            .build()
            )
    );
    public static final Item BLACK_COOKED_GEODE_FRUIT = register("black_cooked_geode_fruit", Item::new, new Item.Settings()
            .food(new FoodComponent(4, 0, true),
                    ConsumableComponent.builder()
                            .consumeEffect(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return Type.APPLY_EFFECTS;
                                }

                                @Override
                                public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
                                    user.addStatusEffect(new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.DARKNESS, 1200));
                                    user.addStatusEffect(new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.INSTANT_DAMAGE, 1));
                                    return true;
                                }
                            })
                            .build()
            )
    );
    public static final Item ECTOPLASM = register("ectoplasm", Item::new, new Item.Settings().maxCount(16));
    public static final Item COOLER_ECTOPLASM = register("cooler_ectoplasm", Item::new, new Item.Settings().maxCount(16));
    public static final Item RED_ORB_PLATFORM_BASE = register("red_orb_platform_base", Item::new, new Item.Settings());
    public static final Item WHITE_BRICK = register("white_brick", Item::new, new Item.Settings());
    public static final Item BLACK_BRICK = register("black_brick", Item::new, new Item.Settings());
    public static final Item STAR_HAMMER = register("star_hammer", Item::new, star_hammer(ToolMaterial.WOOD, 1.0f, 1.0f).repairable(STARDUST).maxCount(1).maxDamage(500));

    public static Item.Settings tool(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds) {
        Item.Settings settings = new Item.Settings();
        return material.applyToolSettings(settings, effectiveBlocks, attackDamage, attackSpeed, disableBlockingForSeconds);
    }

    public static Item.Settings star_hammer(ToolMaterial material, float attackDamage, float attackSpeed) {
        return tool(material, CustomTags.STAR_HAMMER_MINABLE, attackDamage, attackSpeed, 0.0f);
    }
    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Stargazer.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static void init() {}
}