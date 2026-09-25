package com.github.starcatcher21.stargazer.villager;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.mechanics.PointOfIntrests;
import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
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
    public static final DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.VILLAGER_PROFESSION);

    public static final RegistrySupplier<VillagerProfession> ASTROLOGISTS = PROFESSIONS.register("astrologists",
            () -> new VillagerProfession(
                    Component.translatable("entity.minecraft.villager.stargazer.astrologists"),
                    entry -> entry.is(PointOfIntrests.STAR_FORGE_KEY),
                    entry -> entry.is(PointOfIntrests.STAR_FORGE_KEY),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_LIBRARIAN,
                    ModTraids.ASTROLOGISTS_MAP
            )
    );

    public static final ResourceKey<VillagerProfession> ASTROLOGISTS_KEY =
            ResourceKey.create(Registries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "astrologists"));

    public static void init() {
        PROFESSIONS.register();
    }
}