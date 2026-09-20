package com.github.starcatcher21.stargazer.item.armor;

import com.github.starcatcher21.stargazer.CustomTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterials;

public interface ArmorMaterial {
    net.minecraft.world.item.equipment.ArmorMaterial MASK_OF_LUNA_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            5, ArmorMaterials.makeDefense(0, 0, 0, 0, 0), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, CustomTags.STAR, EquipmentAsset.MASK_OF_LUNA
    );

    net.minecraft.world.item.equipment.ArmorMaterial JESTER_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            7, ArmorMaterials.makeDefense(1, 3, 5, 2, 5), 40, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAsset.JESTER
    );

    net.minecraft.world.item.equipment.ArmorMaterial MOON_MATERIAL = new net.minecraft.world.item.equipment.ArmorMaterial(
            13, ArmorMaterials.makeDefense(3, 6, 7, 2, 5), 40, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.0F, 0.3F, CustomTags.REPAIR_MOON, EquipmentAsset.MOON
    );
}
