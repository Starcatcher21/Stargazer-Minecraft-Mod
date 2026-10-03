package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.StargazerAttributes;
import com.github.starcatcher21.stargazer.helpers.BlockItem;
import com.github.starcatcher21.stargazer.effects.StatusEffects;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.armor.ArmorMaterial;
import com.github.starcatcher21.stargazer.item.classes.*;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
//? if >= 26.2 {
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.enchantment.Repairable;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
//?} else {
/*import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
*///? }
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import java.util.List;
import java.util.function.Function;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Stargazer.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> STARDUST = register("stardust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> FULL_MOON_DUST = register("full_moon_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> AMETHYST_DUST = register("amethyst_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PURE_AMETHYST_DUST = register("pure_amethyst_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PURE_AMETHYST_INGOT = register("pure_amethyst_ingot", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> MOON_GLASS_SHARD = register("moon_glass_shard", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PRISMATIC_SHARD = register("prismatic_shard", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PRISMATIC_INGOT = register("prismatic_ingot", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> END_STAR = register("end_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> YELLOW_STAR = register("yellow_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> SUN_ENRICHED_YELLOW_STAR = register("sun_enriched_yellow_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> WINGED_STAR = register("winged_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> RED_STAR = register("red_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> BLUE_STAR = register("blue_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PURPLE_STAR = register("purple_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> DREAM_STAR = register("dream_star", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> LODESTAR = register("lodestar", LodeStar::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final RegistrySupplier<Item> DARKSTAR = register("darkstar", DarkStar::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final RegistrySupplier<Item> GEODE_FRUIT = register("geode_fruit", Item::new, new Item.Properties());

    //? if <= 1.21.1 {
    /*public static final RegistrySupplier<Item> GHOST_SPAWN_EGG = register("ghost_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.GHOST_ENTITY.get(), 0x3b3b3b, 0x9b9b9b, properties), new Item.Properties());
    public static final RegistrySupplier<Item> EYE_BAT_SPAWN_EGG = register("eye_bat_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.EYE_BAT_ENTITY.get(), 0x222222, 0xff0000, properties), new Item.Properties());
    public static final RegistrySupplier<Item> AMETHYST_TURTLE_SPAWN_EGG = register("amethyst_turtle_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.AMETHYST_TURTLE_ENTITY.get(), 0x552255, 0xaa44aa, properties), new Item.Properties());
    public static final RegistrySupplier<Item> ROOK_SPAWN_EGG = register("rook_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.ROOK_ENTITY.get(), 0x444444, 0x888888, properties), new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_ROOK_SPAWN_EGG = register("black_rook_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.BLACK_ROOK_ENTITY.get(), 0x111111, 0x333333, properties), new Item.Properties());
    public static final RegistrySupplier<Item> SCRUBY_SPAWN_EGG = register("scruby_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.SCRUBY_ENTITY.get(), 0x55ff55, 0x00aa00, properties), new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_FOX_SPAWN_EGG = register("black_fox_spawn_egg", properties -> new SpawnEggItem(EntityRegistry.BLACK_FOX_ENTITY.get(), 0x222222, 0xffaa00, properties), new Item.Properties());
    *///? } else {
    public static final RegistrySupplier<Item> GHOST_SPAWN_EGG = register("ghost_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.GHOST_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> EYE_BAT_SPAWN_EGG = register("eye_bat_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.EYE_BAT_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> AMETHYST_TURTLE_SPAWN_EGG = register("amethyst_turtle_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.AMETHYST_TURTLE_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> ROOK_SPAWN_EGG = register("rook_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.ROOK_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_ROOK_SPAWN_EGG = register("black_rook_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.BLACK_ROOK_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> SCRUBY_SPAWN_EGG = register("scruby_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.SCRUBY_ENTITY.get())), new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_FOX_SPAWN_EGG = register("black_fox_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(EntityRegistry.BLACK_FOX_ENTITY.get())), new Item.Properties());
    //? }

    public static final RegistrySupplier<Item> DEAD_EYE_BAT = register("dead_eye_bat", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> LIVING_EYE = register("living_eye", Item::new, new Item.Properties());

    public static final RegistrySupplier<Item> COOKED_GEODE_FRUIT = register("cooked_geode_fruit", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(8, 4, true))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(8).saturationModifier(4.0f).alwaysEdible().build())
            *///? }
    );

    public static final RegistrySupplier<Item> FULL_COOKED_GEODE_FRUIT = register("full_cooked_geode_fruit", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(14, 20, true),
                            Consumable.builder()
                                    .onConsume(new ConsumeEffect() {
                                        @Override
                                        public Type<? extends ConsumeEffect> getType() {
                                            return Type.APPLY_EFFECTS;
                                        }

                                        @Override
                                        public boolean apply(Level world, ItemStack stack, LivingEntity user) {
                                            user.addEffect(new MobEffectInstance(StatusEffects.getHolder(StatusEffects.COSMO.get()), 1200));
                                            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2));
                                            return true;
                                        }
                                    })
                                    .build()
                    )
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(14).saturationModifier(20.0f).alwaysEdible()
                            .effect(() -> new MobEffectInstance(StatusEffects.getHolder(StatusEffects.COSMO.get()), 1200), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2), 1.0f)
                            .build()
                    )
            *///? }
    );

    public static final RegistrySupplier<Item> BLACK_COOKED_GEODE_FRUIT = register("black_cooked_geode_fruit", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(4, 0, true),
                            Consumable.builder()
                                    .onConsume(new ConsumeEffect() {
                                        @Override
                                        public Type<? extends ConsumeEffect> getType() {
                                            return Type.APPLY_EFFECTS;
                                        }

                                        @Override
                                        public boolean apply(Level world, ItemStack stack, LivingEntity user) {
                                            user.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 1200));
                                            user.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1));
                                            return true;
                                        }
                                    })
                                    .build()
                    )
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.0f).alwaysEdible()
                            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 1200), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.HARM, 1), 1.0f)
                            .build()
                    )
            *///? }
    );

    public static final RegistrySupplier<Item> GUMMY_FISH = register("gummy_fish", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(1, 2, false))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(1).saturationModifier(2.0f).build())
            *///? }
    );
    public static final RegistrySupplier<Item> GUMMY_WORM = register("gummy_worm", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(1, 2, false))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(1).saturationModifier(2.0f).build())
            *///? }
    );
    public static final RegistrySupplier<Item> COSMO_FISH = register("cosmo_fish", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(3, 6, false),
                            ConsumableComponents.STARGAZE)
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(3).saturationModifier(6.0f).build())
            *///? }
    );
    public static final RegistrySupplier<Item> ENDER_FISH = register("ender_fish", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(3, 6, false),
                            Consumables.CHORUS_FRUIT)
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(3).saturationModifier(6.0f).build())
            *///? }
    );
    public static final RegistrySupplier<Item> GOLDEN_CRUCIAN = register("golden_crucian", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(2, 4, false))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(2).saturationModifier(4.0f).build())
            *///? }
    );
    public static final RegistrySupplier<Item> MOON_COOKIE = register("moon_cookie", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(1, 2, true))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(1).saturationModifier(2.0f).alwaysEdible().build())
            *///? }
    );
    public static final RegistrySupplier<Item> STAR_COOKIE = register("star_cookie", Item::new, new Item.Properties()
                    //? if >= 26.2 {
                    
                    .food(new FoodProperties(1, 2, true))
                    //?} else {
                    /*.food(new FoodProperties.Builder().nutrition(1).saturationModifier(2.0f).alwaysEdible().build())
            *///? }
    );
    public static final RegistrySupplier<Item> ECTOPLASM = register("ectoplasm", Item::new, new Item.Properties().stacksTo(16));
    public static final RegistrySupplier<Item> COOLER_ECTOPLASM = register("cooler_ectoplasm", Item::new, new Item.Properties().stacksTo(16));
    public static final RegistrySupplier<Item> RED_ORB_PLATFORM_BASE = register("red_orb_platform_base", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> WHITE_BRICK = register("white_brick", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> BLACK_BRICK = register("black_brick", Item::new, new Item.Properties());

    public static final RegistrySupplier<Item> STAR_HAMMER = register("star_hammer", Item::new, repairable(star_hammer(
            //? if >= 26.2 {
            ToolMaterial.WOOD
            //?} else {
            /*Tiers.WOOD
            *///? }
            , 1.0f, 1.0f), CustomTags.STARDUST).stacksTo(1).durability(500));

    public static final RegistrySupplier<Item> THROWABLE_STAR = register("throwable_star", ThrowableStar::new, new Item.Properties().stacksTo(16));

    public static final RegistrySupplier<Item> STAR_BANNER_PATTERN = register("star_banner_pattern", Item::new, new Item.Properties().stacksTo(1));

    public static final RegistrySupplier<Item> LUCKY_COMET = register("lucky_comet", LuckyComet::new, new Item.Properties());
    public static final RegistrySupplier<Item> COMET_FRAGMENT = register("comet_fragment", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> AURORA_FRAGMENT = register("aurora_fragment", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> STAR_BOOK = register("star_book", StarBook::new, new Item.Properties());
    public static final RegistrySupplier<Item> IRON_DUST = register("iron_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> GOLD_DUST = register("gold_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> COPPER_DUST = register("copper_dust", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> SUPERNOVA = register("supernova", Item::new, new Item.Properties().stacksTo(16));

    public static final RegistrySupplier<Item> MASK_OF_LUNA = register("mask_of_luna",
            //? if >= 26.2 {
            Item::new,
            new Item.Properties().stacksTo(1)
                    .humanoidArmor(
                    ArmorMaterial.MASK_OF_LUNA_MATERIAL,
                    ArmorType.HELMET
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.MASK_OF_LUNA_MATERIAL.value()), ArmorItem.Type.HELMET, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.mask_of_luna.dash"),
                                                    1.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .build()
                    )
    );
    public static final RegistrySupplier<Item> JESTER_HELMET = register("jester_helmet",
            //? if >= 26.2 {
            Item::new,
            new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.HELMET)
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.JESTER_MATERIAL.value()), ArmorItem.Type.HELMET, properties.stacksTo(1)), new Item.Properties()
            *///? }
    );
    public static final RegistrySupplier<Item> JESTER_CHESTPLATE = register("jester_chestplate",
            //? if >= 26.2 {
            Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.CHESTPLATE)
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.JESTER_MATERIAL.value()), ArmorItem.Type.CHESTPLATE, properties.stacksTo(1)), new Item.Properties()
            *///? }
    );
    public static final RegistrySupplier<Item> JESTER_LEGGINS = register("jester_leggins",
            //? if >= 26.2 {
            Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.LEGGINGS)
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.JESTER_MATERIAL.value()), ArmorItem.Type.LEGGINGS, properties.stacksTo(1)), new Item.Properties()
            *///? }
    );
    public static final RegistrySupplier<Item> JESTER_BOOTS = register("jester_boots",
            //? if >= 26.2 {
            Item::new, new Item.Properties().stacksTo(1).humanoidArmor(ArmorMaterial.JESTER_MATERIAL, ArmorType.BOOTS)
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.JESTER_MATERIAL.value()), ArmorItem.Type.BOOTS, properties.stacksTo(1)), new Item.Properties()
            *///? }
    );
    public static final RegistrySupplier<Item> CRYSTAL_MOON = register("crystal_moon", Item::new, new Item.Properties().stacksTo(16));

    public static final RegistrySupplier<Item> MOON_HELMET = register("moon_helmet",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.MOON_MATERIAL,
                    ArmorType.HELMET
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.MOON_MATERIAL.value()), ArmorItem.Type.HELMET, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.armor"),
                                                    3.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_helmet.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> MOON_CHESTPLATE = register("moon_chestplate",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1)
                    .humanoidArmor(
                    ArmorMaterial.MOON_MATERIAL,
                    ArmorType.CHESTPLATE
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.MOON_MATERIAL.value()), ArmorItem.Type.CHESTPLATE, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.armor"),
                                                    8.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_chestplate.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> MOON_LEGGINS = register("moon_leggins",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.MOON_MATERIAL,
                    ArmorType.LEGGINGS
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.MOON_MATERIAL.value()), ArmorItem.Type.LEGGINGS, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggins.armor"),
                                                    6.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggins.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_leggins.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> MOON_BOOTS = register("moon_boots",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.MOON_MATERIAL,
                    ArmorType.BOOTS
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.MOON_MATERIAL.value()), ArmorItem.Type.BOOTS, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.armor"),
                                                    3.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.moon_boots.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> SEED_PACKET = register("seed_packet", SeedPacket::new, new Item.Properties().stacksTo(16));

    public static Item.Properties repairable(Item.Properties properties, TagKey<Item> repairItems) {
        return properties;
    }

    public static Item.Properties tool(
            //? if >= 26.2 {
            ToolMaterial
            //?} else {
            /*Tier
                    *///? }
                    material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, float disableBlockingForSeconds) {
        Item.Properties settings = new Item.Properties();
        return settings.durability(
                        //? if >= 26.2 {
                        material.durability()
                        //?} else {
                        /*material.getUses()
                        *///? }
                )
                //? if >= 26.2 {
                .enchantable(
                        material.enchantmentValue()
                )
                //? }
                //? if >= 26.2 {
                
                .delayedComponent(DataComponents.REPAIRABLE, context -> new Repairable(context.getOrThrow(material.repairItems())))
                .delayedComponent(DataComponents.TOOL, context -> new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(context.getOrThrow(material.incorrectBlocksForDrops())),
                                Tool.Rule.minesAndDrops(context.getOrThrow(effectiveBlocks), material.speed())
                        ),
                        1.0f,
                        1,
                        true
                ))
                
                //?} else {
                /*.component(DataComponents.TOOL, new Tool(
                        List.of(
                                Tool.Rule.minesAndDrops(effectiveBlocks, material.getSpeed())
                        ),
                        1.0f,
                        1
                ))
                *///? }
                .attributes(ItemAttributeModifiers.builder()
                        .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double)(attackDamage +
                                //? if >= 26.2 {
                                material.attackDamageBonus()
                                //?} else {
                                /*material.getAttackDamageBonus()
                                *///? }
                        ), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build()
                );
    }

    public static final RegistrySupplier<Item> AMETHYST_HELMET = register("amethyst_helmet",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.AMETHYST_MATERIAL,
                    ArmorType.HELMET
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.AMETHYST_MATERIAL.value()), ArmorItem.Type.HELMET, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_helmet.armor"),
                                                    3.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethhyst_helmet.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_helmet.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_helmet.dash"),
                                                    0.25,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.HEAD
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> AMETHYST_CHESTPLATE = register("amethyst_chestplate",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.AMETHYST_MATERIAL,
                    ArmorType.CHESTPLATE
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.AMETHYST_MATERIAL.value()), ArmorItem.Type.CHESTPLATE, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_chestplate.armor"),
                                                    8.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_chestplate.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_chestplate.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_chestplate.dash"),
                                                    0.25,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.CHEST
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> AMETHYST_LEGGINS = register("amethyst_leggins",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.AMETHYST_MATERIAL,
                    ArmorType.LEGGINGS
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.AMETHYST_MATERIAL.value()), ArmorItem.Type.LEGGINGS, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_leggings.armor"),
                                                    6.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethhyst_leggings.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_leggings.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_leggings.dash"),
                                                    0.25,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.LEGS
                                    )
                                    .build()
                    ));

    public static final RegistrySupplier<Item> AMETHYST_BOOTS = register("amethyst_boots",
            //? if >= 26.2 {
            Item::new, () -> new Item.Properties().stacksTo(1).humanoidArmor(
                    ArmorMaterial.AMETHYST_MATERIAL,
                    ArmorType.BOOTS
            )
            //?} else {
            /*properties -> new ArmorItem(Holder.direct(ArmorMaterial.AMETHYST_MATERIAL.value()), ArmorItem.Type.BOOTS, properties.stacksTo(1)),
            new Item.Properties()
                    *///? }
                    .attributes(
                            ItemAttributeModifiers.builder()
                                    .add(
                                            Attributes.ARMOR,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_boots.armor"),
                                                    3.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .add(
                                            Attributes.ARMOR_TOUGHNESS,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_boots.toughness"),
                                                    2.0,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .add(
                                            Attributes.KNOCKBACK_RESISTANCE,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_boots.knockback_resistance"),
                                                    0.1,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .add(
                                            StargazerAttributes.DASH_LEVEL,
                                            new AttributeModifier(
                                                  Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "armor.amethyst_boots.dash"),
                                                    0.25,
                                                    AttributeModifier.Operation.ADD_VALUE
                                            ),
                                            EquipmentSlotGroup.FEET
                                    )
                                    .build()
                    ));

    public static Item.Properties star_hammer(
            //? if >= 26.2 {
            ToolMaterial
            //?} else {
            /*Tier
                    *///? }
                    material, float attackDamage, float attackSpeed) {
        return tool(material, CustomTags.STAR_HAMMER_MINABLE, attackDamage, attackSpeed, 0.0f);
    }

    public static RegistrySupplier<Item> register(String path, Function<Item.Properties, Item> factory, java.util.function.Supplier<Item.Properties> settingsSupplier) {
        //? if >= 26.2 {
        Identifier identifier =Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, path);
        ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, identifier);
        //? }
        return ITEMS.register(path, () -> {
            return factory.apply(settingsSupplier.get()
                    //? if >= 26.2 {
                            .setId(registryKey)
                    //? }
            );
        });
    }

    public static RegistrySupplier<Item> register(String path, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return register(path, factory, () -> settings);
    }

    public static Function<Item.Properties, Item> createBlockItemWithUniqueName(RegistrySupplier<Block> block) {
        return settings -> new BlockItem(block, settings);
    }

    public static void init() {
        WishingStars.init();
        ITEMS.register();
    }
}
