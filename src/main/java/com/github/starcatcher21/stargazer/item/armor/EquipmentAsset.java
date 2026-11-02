package com.github.starcatcher21.stargazer.item.armor;

import com.github.starcatcher21.stargazer.Stargazer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;

public interface EquipmentAsset {
    static ResourceLocation id(final String name) {
        return ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name);
    }
    ResourceLocation MASK_OF_LUNA_ID = id("mask_of_luna");
    ResourceLocation JESTER_ID = id("jester");
    ResourceLocation MOON_ID = id("moon");
    ResourceLocation AMETHYST_ID = id("amethyst");
    //? if >= 26.2 {
    /*ResourceKey<? extends Registry<net.minecraft.world.item.equipment.EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));

    ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> MASK_OF_LUNA = createId("mask_of_luna");
    ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> JESTER = createId("jester");
    ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> MOON = createId("moon");
    ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> AMETHYST = createId("amethyst");

    static ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> createId(final String name) {
        return ResourceKey.create(ROOT_ID,ResourceLocation.fromNamespaceAndPath(Stargazer.MOD_ID, name));
    }
    *///?}
}
