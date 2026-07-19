package com.github.starcatcher21.stargazer.villager;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;

public class ModVillagers {
    public static final VillagerProfession ASTROLOGISTS = registerProfession("astrologists", PointOfIntrests.STAR_FORGE_KEY);
    public static final ResourceKey<VillagerProfession> ASTROLOGISTS_KEY = ResourceKey.create(Registries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "astrologists"));

    private static VillagerProfession registerProfession(String name, ResourceKey<PoiType> type) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, name),
                new VillagerProfession(Component.translatable("entity.minecraft.villager.stargazer." + name), entry -> entry.is(type), entry -> entry.is(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN, Int2ObjectMap.ofEntries())
        );
    }

    public static void init() {}
}