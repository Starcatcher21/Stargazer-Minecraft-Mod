package com.github.starcatcher21.stargazer.villager;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.google.common.collect.ImmutableSet;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final VillagerProfession ASTROLOGISTS = registerProfession("astrologists", PointOfIntrests.STAR_FORGE_KEY);
    public static final RegistryKey<VillagerProfession> ASTROLOGISTS_KEY = RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, Identifier.of(Stargazer.MOD_ID, "astrologists"));

     private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
         return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(Stargazer.MOD_ID, name),
                 new VillagerProfession(Text.translatable("entity.minecraft.villager.stargazer." + name), entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                         ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN)
         );
     }

    public static void init() {}
}
