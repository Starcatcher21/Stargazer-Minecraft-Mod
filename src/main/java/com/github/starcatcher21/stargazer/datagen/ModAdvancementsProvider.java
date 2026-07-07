package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.CustomTags;
import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.block.register.RedOrbBlocks;
import com.github.starcatcher21.stargazer.entity.EntityRegistry;
import com.github.starcatcher21.stargazer.item.ModItems;
import com.github.starcatcher21.stargazer.item.WishingStars;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.*;
import com.github.starcatcher21.stargazer.worldgen.BiomeReg;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.jpountz.util.Utils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.NbtPredicate;
import net.minecraft.advancements.criterion.PlayerHurtEntityTrigger;
import net.minecraft.advancements.criterion.PlayerPredicate;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.advancements.criterion.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public static final TagKey<DamageType> STAR_TRAP = TagKey.create(Registries.DAMAGE_TYPE, DamageTypeRegistry.STAR_TRAP.identifier());

    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
        HolderGetter registryEntryLookupEntity = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderGetter registryEntryLookupItem = wrapperLookup.lookupOrThrow(Registries.ITEM);
        AdvancementHolder stars = Advancement.Builder.advancement()
                .display(
                        MoonBlocks.MOON_ROCK, // The display icon
                        Component.literal("Stargazer"), // The title
                        Component.literal("Are you ready for cosmic adventures"), // The description
                        Identifier.fromNamespaceAndPath(Stargazer.MOD_ID, "gui/advancements/backgrounds/stars"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        false, // Show the toast when completing it
                        false, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("none", PlayerTrigger.TriggerInstance.tick())
                .save(consumer, Stargazer.MOD_ID + ":stars");
        AdvancementHolder airplane = Advancement.Builder.advancement()
                .parent(stars)
                .display(
                        Items.SPYGLASS, // The display icon
                        Component.literal("Is that airplane"), // The title
                        Component.literal("Can we pretend that airplanes in the night sky are like shooting stars?"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("watch_stars", Criterias.starcatching.createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":airplane");
        AdvancementHolder book = Advancement.Builder.advancement()
                .parent(airplane)
                .display(
                        Items.SPYGLASS, // The display icon
                        Component.literal("Old Book"), // The title
                        Component.literal("I found this wierd old book"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("watch_stars", Criterias.starcatching.createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, ModItems.STAR_BOOK).build()))))
                .save(consumer, Stargazer.MOD_ID + ":book");
        AdvancementHolder negative = Advancement.Builder.advancement()
                .parent(stars)
                .display(
                        ModBlock.NEGATIVE_BLOCK.asItem(), // The display icon
                        Component.literal("evitageN"), // The title
                        Component.literal("I see in negative"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("negative", Criterias.negative.createCriterion(new Negative.Conditions(Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":negative");
        AdvancementHolder brick = Advancement.Builder.advancement()
                .parent(airplane)
                .display(
                        Items.SPYGLASS, // The display icon
                        Component.literal("Is that a BRICK!!!"), // The title
                        Component.literal("BRICK B R I C K BRICK It's fun"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("watch_stars", Criterias.starcatching.createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, CustomTags.CHESS_BRICK).build()))))
                .save(consumer, Stargazer.MOD_ID + ":brick");

        AdvancementHolder portal = Advancement.Builder.advancement()
                .parent(airplane)
                .display(
                        Blocks.CUT_COPPER, // The display icon
                        Component.literal("Where are we going?"), // The title
                        Component.literal("Open a portal to the unknown"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("open_portal", Criterias.cosmicPortal.createCriterion(new CosmicPortal.Conditions(Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":portal");

        TagPredicate<DamageType> star = TagPredicate.is(STAR_TRAP);

        AdvancementHolder teeth_plant = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        MoonBlocks.STAR_TRAP, // The display icon
                        Component.literal("Does this plant have teeth?"), // The title
                        Component.literal("Ouch that hurts"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("hurt", Criterias.starTrap.createCriterion(new StarTrap.Conditions(Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":teeth_plant");



        AdvancementHolder ghost = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Component.literal("I think i saw a ghost"), // The title
                        Component.empty(), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("ghost", PlayerTrigger.TriggerInstance.located(
                        Optional.of(EntityPredicate.Builder.entity()
                                .subPredicate(PlayerPredicate.Builder.player()
                                        .setLookingAt(EntityPredicate.Builder.entity()
                                                .of(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                        ).build())
                                .build())))
                .save(consumer, Stargazer.MOD_ID + ":ghost");

        AdvancementHolder gravity = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.ECTOPLASM, // The display icon
                        Component.literal("Do you believe in Gravity"), // The title
                        Component.literal("This is some kind of gravity ghosts"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("hurt", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(Optional.empty(), Optional.of(EntityPredicate.Builder.entity().of(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY).build())))
                .save(consumer, Stargazer.MOD_ID + ":gravity");

        AdvancementHolder pac = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.COOLER_ECTOPLASM, // The display icon
                        Component.literal("Time to eat some ghosts"), // The title
                        Component.literal("It's all about the game"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("pacman look", PlayerTrigger.TriggerInstance.located(
                        Optional.of(EntityPredicate.Builder.entity()
                                .subPredicate(PlayerPredicate.Builder.player()
                                        .setLookingAt(EntityPredicate.Builder.entity()
                                                .of(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                                    nbt.putString("tag", "pacman");
                                                })))
                                        ).build())
                                .build())))
                .save(consumer, Stargazer.MOD_ID + ":pac");


        AdvancementHolder meet_again = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Component.literal("We'll meet again"), // The title
                        Component.literal("don't know where don't know when"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("meet again", PlayerTrigger.TriggerInstance.located(
                        Optional.of(EntityPredicate.Builder.entity()
                                .subPredicate(PlayerPredicate.Builder.player()
                                        .setLookingAt(EntityPredicate.Builder.entity()
                                                .of(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                                    nbt.putString("tag", "bill");
                                                })))
                                        ).build())
                                .build())))
                .save(consumer, Stargazer.MOD_ID + ":bill");

        AdvancementHolder adventures = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG, // The display icon
                        Component.literal("It's Adventure Time"), // The title
                        Component.literal("Look Jake I'm a ghost"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("adventure", PlayerTrigger.TriggerInstance.located(
                        Optional.of(EntityPredicate.Builder.entity()
                                .subPredicate(PlayerPredicate.Builder.player()
                                        .setLookingAt(EntityPredicate.Builder.entity()
                                                .of(registryEntryLookupEntity, EntityRegistry.GHOST_ENTITY)
                                                .nbt(new NbtPredicate(Util.make(new CompoundTag(), nbt -> {
                                                    nbt.putString("tag", "adventure");
                                                })))
                                        ).build())
                                .build())))
                .save(consumer, Stargazer.MOD_ID + ":adventure");

        requireListedBiomesVisited(Advancement.Builder.advancement(), wrapperLookup, BiomeReg.MoonList)
                .parent(portal)
                .display(
                        MoonBlocks.MOON_LOG, // The display icon
                        Component.literal("Exotic Tourism"), // The title
                        Component.literal("Visit all biomes in cosmic dimension"), // The description
                        null,
                        AdvancementType.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .rewards(AdvancementRewards.Builder.experience(500))
                .save(consumer, Stargazer.MOD_ID + ":exotic_turist");
        AdvancementHolder Wishing = Advancement.Builder.advancement()
                .parent(portal)
                .addCriterion("craft", Criterias.forgeCraft.createCriterion(new ForgeCraft.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, WishingStars.WISHING_STAR).build()))))
                .display(
                        WishingStars.WISHING_STAR,
                        Component.literal("Make a Wish"),
                        Component.literal("Create Wishing Star"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .save(consumer, Stargazer.MOD_ID + ":wishing");

        AdvancementHolder Milk = Advancement.Builder.advancement()
                .parent(Wishing)
                .addCriterion("milk", Criterias.starModifier.createCriterion(new StarModifier.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, Items.MILK_BUCKET).build()))))
                .display(
                        WishingStars.WISHING_STAR,
                        Component.literal("This isn't a milk"),
                        Component.literal("Create Transgendere Wishing Star"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .save(consumer, Stargazer.MOD_ID + ":milk");

        AdvancementHolder Home = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        ModItems.STARDUST, // The display icon
                        Component.literal("I took a part to home"), // The title
                        Component.literal("Craft cosmic block"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("craft", Criterias.forgeCraft.createCriterion(new ForgeCraft.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, CustomTags.COSMIC).build()))))
                .save(consumer, Stargazer.MOD_ID + ":home");
        AdvancementHolder Red = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        RedOrbBlocks.RED_ROCK, // The display icon
                        Component.literal("Red Planet"), // The title
                        Component.literal("What next Green™ Planet"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("craft", Criterias.moonWeld.createCriterion(new MoonWeld.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, RedOrbBlocks.RED_ORB_PLATFORM.asItem()).build()))))
                .save(consumer, Stargazer.MOD_ID + ":red");
    }

    protected static Advancement.Builder requireListedBiomesVisited(Advancement.Builder builder, HolderLookup.Provider registries, List<ResourceKey<Biome>> biomes) {
        HolderGetter registryEntryLookup = registries.lookupOrThrow(Registries.BIOME);
        for (ResourceKey<Biome> registryKey : biomes) {
            builder.addCriterion(
                    "visited_" + registryKey.identifier().getPath(),
                    PlayerTrigger.TriggerInstance.located(
                            LocationPredicate.Builder.inBiome(registryEntryLookup.getOrThrow(registryKey))
                    )
            );
        }
        return builder;
    }
}
