package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.StarBlocks;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.entity.Star;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.*;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public static final TagKey<DamageType> STAR_TRAP = TagKey.of(RegistryKeys.DAMAGE_TYPE, DamageTypeRegistry.STAR_TRAP.getValue());

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        RegistryEntryLookup registryEntryLookupEntity = wrapperLookup.getOrThrow(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup registryEntryLookupItem = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
        AdvancementEntry stars = Advancement.Builder.create()
                .display(
                        MoonBlocks.MOON_ROCK, // The display icon
                        Text.literal("Stargazer"), // The title
                        Text.literal("Are you ready for cosmic adventures"), // The description
                        Identifier.of(Stargazer.MOD_ID, "gui/advancements/backgrounds/stars"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        false, // Show the toast when completing it
                        false, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("none", TickCriterion.Conditions.createTick())
                .build(consumer, Stargazer.MOD_ID + ":stars");
        AdvancementEntry airplane = Advancement.Builder.create()
                .parent(stars)
                .display(
                        Items.SPYGLASS, // The display icon
                        Text.literal("Is that airplane"), // The title
                        Text.literal("Can we pretend that airplanes in the night sky are like shooting stars?"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("watch_stars", Criterias.starcatching.create(new Starcatching.Conditions(Optional.empty(), Optional.empty())))
                .build(consumer, Stargazer.MOD_ID + ":airplane");

        AdvancementEntry portal = Advancement.Builder.create()
                .parent(airplane)
                .display(
                        Blocks.CUT_COPPER, // The display icon
                        Text.literal("Where are we going?"), // The title
                        Text.literal("Open a portal to the unknown"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("open_portal", Criterias.cosmicPortal.create(new CosmicPortal.Conditions(Optional.empty())))
                .build(consumer, Stargazer.MOD_ID + ":portal");

        TagPredicate<DamageType> star = TagPredicate.expected(STAR_TRAP);

        AdvancementEntry teeth_plant = Advancement.Builder.create()
                .parent(portal)
                .display(
                        MoonBlocks.STAR_TRAP, // The display icon
                        Text.literal("Does this plant have teeth?"), // The title
                        Text.literal("Ouch that hurts"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("hurt", Criterias.starTrap.create(new StarTrap.Conditions(Optional.empty())))
                .build(consumer, Stargazer.MOD_ID + ":teeth_plant");



        AdvancementEntry ghost = Advancement.Builder.create()
                .parent(portal)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Text.literal("I think i saw a ghost"), // The title
                        Text.empty(), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("ghost", TickCriterion.Conditions.createLocation(
                        Optional.of(EntityPredicate.Builder.create()
                                .typeSpecific(PlayerPredicate.Builder.create()
                                        .lookingAt(EntityPredicate.Builder.create()
                                                .type(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                        ).build())
                                .build())))
                .build(consumer, Stargazer.MOD_ID + ":ghost");

        AdvancementEntry gravity = Advancement.Builder.create()
                .parent(ghost)
                .display(
                        ModItems.ECTOPLASM, // The display icon
                        Text.literal("Do you believe in Gravity"), // The title
                        Text.literal("This is some kind of gravity ghosts"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("hurt", PlayerHurtEntityCriterion.Conditions.create(Optional.empty(), Optional.of(EntityPredicate.Builder.create().type(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY).build())))
                .build(consumer, Stargazer.MOD_ID + ":gravity");

        AdvancementEntry pac = Advancement.Builder.create()
                .parent(ghost)
                .display(
                        ModItems.COOLER_ECTOPLASM, // The display icon
                        Text.literal("Time to eat some ghosts"), // The title
                        Text.literal("It's all about the game"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("pacman look", TickCriterion.Conditions.createLocation(
                        Optional.of(EntityPredicate.Builder.create()
                                .typeSpecific(PlayerPredicate.Builder.create()
                                        .lookingAt(EntityPredicate.Builder.create()
                                                .type(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new NbtCompound(), nbt -> {
                                                    nbt.put("tag", Codec.STRING, "pacman");
                                                })))
                                        ).build())
                                .build())))
                .build(consumer, Stargazer.MOD_ID + ":pac");


        AdvancementEntry meet_again = Advancement.Builder.create()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Text.literal("We'll meet again"), // The title
                        Text.literal("don't know where don't know when"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("meet again", TickCriterion.Conditions.createLocation(
                        Optional.of(EntityPredicate.Builder.create()
                                .typeSpecific(PlayerPredicate.Builder.create()
                                        .lookingAt(EntityPredicate.Builder.create()
                                                .type(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new NbtCompound(), nbt -> {
                                                    nbt.put("tag", Codec.STRING, "bill");
                                                })))
                                        ).build())
                                .build())))
                .build(consumer, Stargazer.MOD_ID + ":bill");

        AdvancementEntry adventures = Advancement.Builder.create()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Text.literal("It's Adventure Time"), // The title
                        Text.literal("Look Jake I'm a ghost"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("adventure", TickCriterion.Conditions.createLocation(
                        Optional.of(EntityPredicate.Builder.create()
                                .typeSpecific(PlayerPredicate.Builder.create()
                                        .lookingAt(EntityPredicate.Builder.create()
                                                .type(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new NbtCompound(), nbt -> {
                                                    nbt.put("tag", Codec.STRING, "adventure");
                                                })))
                                        ).build())
                                .build())))
                .build(consumer, Stargazer.MOD_ID + ":adventure");

        requireListedBiomesVisited(Advancement.Builder.create(), wrapperLookup, BiomeReg.MoonList)
                .parent(portal)
                .display(
                        MoonBlocks.MOON_LOG, // The display icon
                        Text.literal("Exotic Tourism"), // The title
                        Text.literal("Visit all biomes in cosmic dimension"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .rewards(AdvancementRewards.Builder.experience(500))
                .build(consumer, Stargazer.MOD_ID + ":exotic_turist");

        AdvancementEntry Home = Advancement.Builder.create()
                .parent(portal)
                .display(
                        ModItems.STARDUST, // The display icon
                        Text.literal("I took a part to home"), // The title
                        Text.literal("Craft cosmic block"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("craft", Criterias.forgeCraft.create(new ForgeCraft.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.create().tag(registryEntryLookupItem, CustomTags.COSMIC).build()))))
                .build(consumer, Stargazer.MOD_ID + ":home");
    }

    protected static Advancement.Builder requireListedBiomesVisited(Advancement.Builder builder, RegistryWrapper.WrapperLookup registries, List<RegistryKey<Biome>> biomes) {
        RegistryEntryLookup registryEntryLookup = registries.getOrThrow(RegistryKeys.BIOME);
        for (RegistryKey<Biome> registryKey : biomes) {
            builder.criterion(
                    "visited_" + registryKey.getValue().getPath(),
                    TickCriterion.Conditions.createLocation(
                            LocationPredicate.Builder.createBiome(registryEntryLookup.getOrThrow(registryKey))
                    )
            );
        }
        return builder;
    }
}
