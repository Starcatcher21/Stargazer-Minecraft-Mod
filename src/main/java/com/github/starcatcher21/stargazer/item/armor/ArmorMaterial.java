package com.github.starcatcher21.stargazer.item.armor;

import com.github.starcatcher21.stargazer.CustomTags;
//? if >= 26.2 {
import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.Map;
//? } else {
/*import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
*///? }

//? if >= 26.2 {
public interface ArmorMaterial {
    private static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }

    net.minecraft.world.item.equipment.ArmorMaterial MASK_OF_LUNA_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            5, makeDefense(0, 0, 0, 0, 0), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, CustomTags.STAR, EquipmentAsset.MASK_OF_LUNA
    );

    net.minecraft.world.item.equipment.ArmorMaterial JESTER_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            7, makeDefense(1, 3, 5, 2, 5), 40, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAsset.JESTER
    );

    net.minecraft.world.item.equipment.ArmorMaterial MOON_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            13, makeDefense(3, 6, 7, 2, 5), 40, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.3F, CustomTags.REPAIR_MOON, EquipmentAsset.MOON
    );

    net.minecraft.world.item.equipment.ArmorMaterial AMETHYST_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            13, makeDefense(3, 6, 7, 2, 5), 40, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.3F, CustomTags.REPAIR_AMETHYST, EquipmentAsset.AMETHYST
    );
}
//? } else {
/*public class ArmorMaterial {

    public static final Holder<net.minecraft.world.item.ArmorMaterial> MASK_OF_LUNA_MATERIAL = register(
            "mask_of_luna",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 0);
                map.put(ArmorItem.Type.LEGGINGS, 0);
                map.put(ArmorItem.Type.CHESTPLATE, 0);
                map.put(ArmorItem.Type.HELMET, 0);
                map.put(ArmorItem.Type.BODY, 0);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER.value(),
            0.0F,
            0.0F,
            () -> Ingredient.of(CustomTags.STAR),
            EquipmentAsset.MASK_OF_LUNA_ID.getPath()
    );

    public static final Holder<net.minecraft.world.item.ArmorMaterial> JESTER_MATERIAL = register(
            "jester",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }),
            40,
            SoundEvents.ARMOR_EQUIP_LEATHER.value(),
            0.0F,
            0.0F,
            () -> Ingredient.of(ItemTags.RABBIT_FOOD),
            EquipmentAsset.JESTER_ID.getPath()
    );

    public static final Holder<net.minecraft.world.item.ArmorMaterial> MOON_MATERIAL = register(
            "moon",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }),
            40,
            SoundEvents.ARMOR_EQUIP_DIAMOND.value(),
            1.0F,
            0.3F,
            () -> Ingredient.of(CustomTags.REPAIR_MOON),
            EquipmentAsset.MOON_ID.getPath()
    );

    public static final Holder<net.minecraft.world.item.ArmorMaterial> AMETHYST_MATERIAL = register(
            "amethyst",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }),
            40,
            SoundEvents.ARMOR_EQUIP_DIAMOND.value(),
            1.0F,
            0.3F,
            () -> Ingredient.of(CustomTags.REPAIR_AMETHYST),
            EquipmentAsset.AMETHYST_ID.getPath()
    );

    private static Holder<net.minecraft.world.item.ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defenseMap,
            int enchantability,
            net.minecraft.sounds.SoundEvent equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient,
            String assetName
    ) {
        List<net.minecraft.world.item.ArmorMaterial.Layer> layers = List.of(new net.minecraft.world.item.ArmorMaterial.Layer(Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, assetName)));
        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
              Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name),
                new net.minecraft.world.item.ArmorMaterial(defenseMap, enchantability, net.minecraft.core.Holder.direct(equipSound), repairIngredient, layers, toughness, knockbackResistance)
        );
    }

    public static void register() {
        // Simple method to force class loading during mod initialization if needed
    }
}*///? }
