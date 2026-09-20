package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.block.ModFluids;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.armor.ArmorMaterial;
import com.github.starcatcher21.stargazer.item.classes.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import java.util.function.Function;

public final class ModItems {
    public static final Item STARDUST = register("stardust", Item::new, new Item.Properties());
    public static final Item MOON_GLASS_SHARD = register("moon_glass_shard", Item::new, new Item.Properties());
    public static final Item PRISMATIC_SHARD = register("prismatic_shard", Item::new, new Item.Properties());
    public static final Item PRISMATIC_INGOT = register("prismatic_ingot", Item::new, new Item.Properties());
    public static final Item END_STAR = register("end_star", Item::new, new Item.Properties());
    public static final Item YELLOW_STAR = register("yellow_star", Item::new, new Item.Properties());
    public static final Item SUN_ENRICHED_YELLOW_STAR = register("sun_enriched_yellow_star", Item::new, new Item.Properties());
    public static final Item WINGED_STAR = register("winged_star", Item::new, new Item.Properties());
    public static final Item RED_STAR = register("red_star", Item::new, new Item.Properties());
    public static final Item BLUE_STAR = register("blue_star", Item::new, new Item.Properties());
    public static final Item PURPLE_STAR = register("purple_star", Item::new, new Item.Properties());
    public static final Item DREAM_STAR = register("dream_star", Item::new, new Item.Properties());
    public static final Item LODESTAR = register("lodestar", LodeStar::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item DARKSTAR = register("darkstar", DarkStar::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item GEODE_FRUIT = register("geode_fruit", Item::new, new Item.Properties());
    public static final Item GHOST_SPAWN_EGG = register("ghost_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.GHOST_ENTITY));
    public static final Item EYE_BAT_SPAWN_EGG = register("eye_bat_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.EYE_BAT_ENTITY));
    public static final Item AMETHYST_TURTLE_SPAWN_EGG = register("amethyst_turtle_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.AMETHYST_TURTLE_ENTITY));
    public static final Item ROOK_SPAWN_EGG = register("rook_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.ROOK_ENTITY));
    public static final Item BLACK_ROOK_SPAWN_EGG = register("black_rook_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.BLACK_ROOK_ENTITY));
    public static final Item SCRUBY_SPAWN_EGG = register("scruby_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.SCRUBY_ENTITY));
    public static final Item BLACK_FOX_SPAWN_EGG = register("black_fox_spawn_egg", SpawnEggItem::new, new Item.Properties().spawnEgg(EntityRegistry.BLACK_FOX_ENTITY));
    public static final Item DEAD_EYE_BAT = register("dead_eye_bat", Item::new, new Item.Properties());
    public static final Item LIVING_EYE = register("living_eye", Item::new, new Item.Properties());
    public static final Item COOKED_GEODE_FRUIT = register("cooked_geode_fruit", Item::new, new Item.Properties()
            .food(new FoodProperties(8, 4, true))
    );
    public static final Item FULL_COOKED_GEODE_FRUIT = register("full_cooked_geode_fruit", Item::new, new Item.Properties()
            .food(new FoodProperties(14, 20, true),
                    Consumable.builder()
                            .onConsume(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return Type.APPLY_EFFECTS;
                                }

                                @Override
                                public boolean apply(Level world, ItemStack stack, LivingEntity user) {
                                    user.addEffect(new MobEffectInstance(StatusEffects.COSMO, 1200));
                                    user.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.ABSORPTION, 1200, 2));
                                    return true;
                                }
                            })
                            .build()
            )
    );
    public static final Item BLACK_COOKED_GEODE_FRUIT = register("black_cooked_geode_fruit", Item::new, new Item.Properties()
            .food(new FoodProperties(4, 0, true),
                    Consumable.builder()
                            .onConsume(new ConsumeEffect() {
                                @Override
                                public Type<? extends ConsumeEffect> getType() {
                                    return Type.APPLY_EFFECTS;
                                }

                                @Override
                                public boolean apply(Level world, ItemStack stack, LivingEntity user) {
                                    user.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DARKNESS, 1200));
                                    user.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.INSTANT_DAMAGE, 1));
                                    return true;
                                }
                            })
                            .build()
            )
    );
    public static final Item GUMMY_FISH = register("gummy_fish", Item::new, new Item.Properties()
            .food(new FoodProperties(1, 2, false))
    );
    public static final Item GUMMY_WORM = register("gummy_worm", Item::new, new Item.Properties()
            .food(new FoodProperties(1, 2, false))
    );
    public static final Item COSMO_FISH = register("cosmo_fish", Item::new, new Item.Properties()
            .food(new FoodProperties(3, 6, false),
                    com.github.starcatcher21.stargazer.item.ConsumableComponents.STARGAZE)
    );
    public static final Item ENDER_FISH = register("ender_fish", Item::new, new Item.Properties()
            .food(new FoodProperties(3, 6, false),
                    Consumables.CHORUS_FRUIT)
    );
    public static final Item GOLDEN_CRUCIAN = register("golden_crucian", Item::new, new Item.Properties()
            .food(new FoodProperties(2, 4, false))
    );
    public static final Item MOON_COOKIE = register("moon_cookie", Item::new, new Item.Properties()
            .food(new FoodProperties(1, 2, true))
    );
    public static final Item STAR_COOKIE = register("star_cookie", Item::new, new Item.Properties()
            .food(new FoodProperties(1, 2, true))
    );
    public static final Item ECTOPLASM = register("ectoplasm", Item::new, new Item.Properties().stacksTo(16));
    public static final Item COOLER_ECTOPLASM = register("cooler_ectoplasm", Item::new, new Item.Properties().stacksTo(16));
    public static final Item RED_ORB_PLATFORM_BASE = register("red_orb_platform_base", Item::new, new Item.Properties());
    public static final Item WHITE_BRICK = register("white_brick", Item::new, new Item.Properties());
    public static final Item BLACK_BRICK = register("black_brick", Item::new, new Item.Properties());
    public static final Item STAR_HAMMER = register("star_hammer", Item::new, star_hammer(ToolMaterial.WOOD, 1.0f, 1.0f).repairable(STARDUST).stacksTo(1).durability(500));
    public static final Item DREAM_BUCKET = register(
            "dream_bucket",
            props -> new BucketItem(ModFluids.DREAM, props),
            new Item.Properties()
                    .stacksTo(1)
                    .craftRemainder(Items.BUCKET)
    );
    public static final Item THROWABLE_STAR = register("throwable_star", ThrowableStar::new, new Item.Properties().stacksTo(16));

    public static final Item STAR_BANNER_PATTERN = register("star_banner_pattern", Item::new, new Item.Properties().stacksTo(1).delayedComponent(DataComponents.PROVIDES_BANNER_PATTERNS, context -> context.getOrThrow(CustomTags.STAR_PATTERNS)));

    public static final Item LUCKY_COMET = register("lucky_comet", LuckyComet::new, new Item.Properties());
    public static final Item COMET_FRAGMENT = register("comet_fragment", Item::new, new Item.Properties());
    public static final Item AURORA_FRAGMENT = register("aurora_fragment", Item::new, new Item.Properties());
    public static final Item STAR_BOOK = register("star_book", StarBook::new, new Item.Properties());
    public static final Item IRON_DUST = register("iron_dust", Item::new, new Item.Properties());
    public static final Item GOLD_DUST = register("gold_dust", Item::new, new Item.Properties());
    public static final Item COPPER_DUST = register("copper_dust", Item::new, new Item.Properties());
    public static final Item SUPERNOVA = register("supernova", Item::new, new Item.Properties().stacksTo(16));
    public static final Item MASK_OF_LUNA = register("mask_of_luna",
            Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ArmorMaterial.MASK_OF_LUNA_MATERIAL, ArmorType.HELMET)
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                    // Make sure this identifier string is unique to prevent clashes
                                                    Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.mask_of_luna.dash"),
                                                    1.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .build()
                    )
    );

    public static final Item JESTER_HELMET = register("jester_helmet", Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.HELMET));
    public static final Item JESTER_CHESTPLATE = register("jester_chestplate", Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.CHESTPLATE));
    public static final Item JESTER_LEGGINS = register("jester_leggins", Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.LEGGINGS));
    public static final Item JESTER_BOOTS = register("jester_boots", Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.BOOTS));
    public static final Item CRYSTAL_MOON = register("crystal_moon", Item::new, new Item.Properties().stacksTo(16));
    public static final Item MOON_HELMET = register("moon_helmet", Item::new, new Item.Properties()
            .stacksTo(1)
            .humanoidArmor(ArmorMaterial.MOON_MATERIAL, ArmorType.HELMET)
            .attributes(
                    ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.ARMOR,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.armor"),
                                            ArmorMaterial.MOON_MATERIAL.defense().get(ArmorType.HELMET),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.HEAD
                            )
                            .add(
                                    Attributes.ARMOR_TOUGHNESS,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.toughness"),
                                            ArmorMaterial.MOON_MATERIAL.toughness(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.HEAD
                            )
                            .add(
                                    Attributes.KNOCKBACK_RESISTANCE,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.knockback_resistance"),
                                            ArmorMaterial.MOON_MATERIAL.knockbackResistance(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.HEAD
                            )
                            .add(
                                    StargazerAttributes.DASH_LEVEL,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.dash"),
                                            0.25,
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.HEAD
                            )
                            .build()
            )
    );

    public static final Item MOON_CHESTPLATE = register("moon_chestplate", Item::new, new Item.Properties()
            .stacksTo(1)
            .humanoidArmor(ArmorMaterial.MOON_MATERIAL, ArmorType.CHESTPLATE)
            .attributes(
                    ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.ARMOR,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.armor"),
                                            ArmorMaterial.MOON_MATERIAL.defense().get(ArmorType.CHESTPLATE),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.CHEST
                            )
                            .add(
                                    Attributes.ARMOR_TOUGHNESS,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.toughness"),
                                            ArmorMaterial.MOON_MATERIAL.toughness(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.CHEST
                            )
                            .add(
                                    Attributes.KNOCKBACK_RESISTANCE,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.knockback_resistance"),
                                            ArmorMaterial.MOON_MATERIAL.knockbackResistance(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.CHEST
                            )
                            .add(
                                    StargazerAttributes.DASH_LEVEL,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.dash"),
                                            0.25,
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.CHEST
                            )
                            .build()
            )
    );

    public static final Item MOON_LEGGINS = register("moon_leggins", Item::new, new Item.Properties()
            .stacksTo(1)
            .humanoidArmor(ArmorMaterial.MOON_MATERIAL, ArmorType.LEGGINGS)
            .attributes(
                    ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.ARMOR,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggings.armor"),
                                            ArmorMaterial.MOON_MATERIAL.defense().get(ArmorType.LEGGINGS),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.LEGS
                            )
                            .add(
                                    Attributes.ARMOR_TOUGHNESS,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggings.toughness"),
                                            ArmorMaterial.MOON_MATERIAL.toughness(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.LEGS
                            )
                            .add(
                                    Attributes.KNOCKBACK_RESISTANCE,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggings.knockback_resistance"),
                                            ArmorMaterial.MOON_MATERIAL.knockbackResistance(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.LEGS
                            )
                            .add(
                                    StargazerAttributes.DASH_LEVEL,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggings.dash"),
                                            0.25,
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.LEGS
                            )
                            .build()
            )
    );

    public static final Item MOON_BOOTS = register("moon_boots", Item::new, new Item.Properties()
            .stacksTo(1)
            .humanoidArmor(ArmorMaterial.MOON_MATERIAL, ArmorType.BOOTS)
            .attributes(
                    ItemAttributeModifiers.builder()
                            .add(
                                    Attributes.ARMOR,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.armor"),
                                            ArmorMaterial.MOON_MATERIAL.defense().get(ArmorType.BOOTS),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.FEET
                            )
                            .add(
                                    Attributes.ARMOR_TOUGHNESS,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.toughness"),
                                            ArmorMaterial.MOON_MATERIAL.toughness(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.FEET
                            )
                            .add(
                                    Attributes.KNOCKBACK_RESISTANCE,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.knockback_resistance"),
                                            ArmorMaterial.MOON_MATERIAL.knockbackResistance(),
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.FEET
                            )
                            .add(
                                    StargazerAttributes.DASH_LEVEL,
                                    new AttributeModifier(
                                            Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.dash"),
                                            0.25,
                                            AttributeModifier.Operation.ADD_VALUE
                                    ),
                                    EquipmentSlotGroup.FEET
                            )
                            .build()
            )
    );
    public static final Item SEED_PACKET = register("seed_packet", SeedPacket::new, new Item.Properties().stacksTo(16));
    public static Item.Properties tool(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds) {
        Item.Properties settings = new Item.Properties();
        return material.applyToolProperties(settings, effectiveBlocks, attackDamage, attackSpeed, disableBlockingForSeconds);
    }

    public static Item.Properties star_hammer(ToolMaterial material, float attackDamage, float attackSpeed) {
        return tool(material, CustomTags.STAR_HAMMER_MINABLE, attackDamage, attackSpeed, 0.0f);
    }
    public static Item register(String path, Function<Item.Properties, Item> factory, Item.Properties settings) {
        final ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path));
        Item item = factory.apply((settings.setId(registryKey)));
        Registry.register(BuiltInRegistries.ITEM, registryKey, item);
        return item;
    }

    public static Function<Item.Properties, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useItemDescriptionPrefix());
    }

    public static void init() {
        WishingStars.init();
    }
}