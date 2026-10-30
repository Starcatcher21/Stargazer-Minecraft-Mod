/*? if fabric || neoforge {*/
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
import dev.architectury.registry.registries.RegistrySupplier;
//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
//?} else {
/*import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
*///?}
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
//? if >= 26.2 {
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.NbtPredicate;
import net.minecraft.advancements.triggers.PlayerHurtEntityTrigger;
import net.minecraft.advancements.predicates.entity.PlayerPredicate;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.advancements.predicates.TagPredicate;
//?} else {
/*import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.advancements.critereon.PlayerPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.TagPredicate;
*///?}
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
//? if >= 26.2 {
import net.minecraft.util.Util;
//?} else {
/*import net.minecraft.Util;
*///?}
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementsProvider
//? if fabric {
        extends FabricAdvancementProvider {
    public AdvancementsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }
//?} else {
        /*extends AdvancementProvider {
    public AdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, List.of(AdvancementsProvider::generateAdvancements));
    }
*///?}

    public static final TagKey<DamageType> STAR_TRAP = TagKey.create(Registries.DAMAGE_TYPE, DamageTypeRegistry.STAR_TRAP.identifier());

    //? if fabric {
    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
        generateAdvancements(wrapperLookup, consumer);
    }
    //?}

    private static void generateAdvancements(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
        HolderGetter registryEntryLookupEntity = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderGetter registryEntryLookupItem = wrapperLookup.lookupOrThrow(Registries.ITEM);
        HolderGetter registryEntryLookupBiome = wrapperLookup.lookupOrThrow(Registries.BIOME);
        AdvancementHolder stars = Advancement.Builder.advancement()
                .display(
                        MoonBlocks.MOON_ROCK.get(), // The display icon
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
                .addCriterion("watch_stars", registryValue(Criterias.STARCATCHING).createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.empty())))
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
                .addCriterion("watch_stars", registryValue(Criterias.STARCATCHING).createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.of(itemPredicate(registryEntryLookupItem, ModItems.STAR_BOOK.get())))))
                .save(consumer, Stargazer.MOD_ID + ":book");
        AdvancementHolder negative = Advancement.Builder.advancement()
                .parent(stars)
                .display(
                        ModBlock.NEGATIVE_BLOCK.get().asItem(), // The display icon
                        Component.literal("evitageN"), // The title
                        Component.literal("I see in negative"), // The description

                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("negative", registryValue(Criterias.NEGATIVE).createCriterion(new Negative.Conditions(Optional.empty())))
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
                .addCriterion("watch_stars", registryValue(Criterias.STARCATCHING).createCriterion(new Starcatching.Conditions(Optional.empty(), Optional.of(itemTagPredicate(registryEntryLookupItem, CustomTags.CHESS_BRICK)))))
                .save(consumer, Stargazer.MOD_ID + ":brick");

        AdvancementHolder portal = Advancement.Builder.advancement()
                .parent(airplane)
                .display(
                        //? if >= 26.2 {
                        Blocks.CUT_COPPER.weathering().unaffected(),
                        //?} else {
                        /*Blocks.CUT_COPPER,
                        *///?}
                        Component.literal("Where are we going?"), // The title
                        Component.literal("Open a portal to the unknown"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("open_portal", registryValue(Criterias.COSMIC_PORTAL).createCriterion(new CosmicPortal.Conditions(Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":portal");

        TagPredicate<DamageType> star = TagPredicate.is(STAR_TRAP);

        AdvancementHolder teeth_plant = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        registryValue(MoonBlocks.STAR_TRAP), // The display icon
                        Component.literal("Does this plant have teeth?"), // The title
                        Component.literal("Ouch that hurts"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("hurt", registryValue(Criterias.STAR_TRAP).createCriterion(new StarTrap.Conditions(Optional.empty())))
                .save(consumer, Stargazer.MOD_ID + ":teeth_plant");



        AdvancementHolder ghost = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        ModItems.GHOST_SPAWN_EGG.get(), // The display icon
                        Component.literal("I think i saw a ghost"), // The title
                        Component.empty(), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("ghost", PlayerTrigger.TriggerInstance.located(
                        Optional.of(lookingAtEntityPredicate(
                                registryEntryLookupEntity,
                                registryValue(EntityRegistry.GHOST_ENTITY),
                                Optional.empty()
                        ).build())))
                .save(consumer, Stargazer.MOD_ID + ":ghost");

        AdvancementHolder gravity = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.ECTOPLASM.get(), // The display icon
                        Component.literal("Do you believe in Gravity"), // The title
                        Component.literal("This is some kind of gravity ghosts"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("hurt", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(Optional.empty(), Optional.of(entityPredicate(registryEntryLookupEntity, registryValue(EntityRegistry.GHOST_ENTITY)).build())))
                .save(consumer, Stargazer.MOD_ID + ":gravity");

        AdvancementHolder pac = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.COOLER_ECTOPLASM.get(), // The display icon
                        Component.literal("Time to eat some ghosts"), // The title
                        Component.literal("It's all about the game"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("pacman look", PlayerTrigger.TriggerInstance.located(
                        Optional.of(lookingAtEntityPredicate(
                                registryEntryLookupEntity,
                                registryValue(EntityRegistry.GHOST_ENTITY),
                                Optional.of("pacman")
                        ).build())))
                .save(consumer, Stargazer.MOD_ID + ":pac");


        AdvancementHolder meet_again = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG.get(), // The display icon
                        Component.literal("We'll meet again"), // The title
                        Component.literal("don't know where don't know when"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("meet again", PlayerTrigger.TriggerInstance.located(
                        Optional.of(lookingAtEntityPredicate(
                                registryEntryLookupEntity,
                                registryValue(EntityRegistry.GHOST_ENTITY),
                                Optional.of("bill")
                        ).build())))
                .save(consumer, Stargazer.MOD_ID + ":bill");

        AdvancementHolder adventures = Advancement.Builder.advancement()
                .parent(ghost)
                .display(
                        ModItems.GHOST_SPAWN_EGG.get(), // The display icon
                        Component.literal("It's Adventure Time"), // The title
                        Component.literal("Look Jake I'm a ghost"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("adventure", PlayerTrigger.TriggerInstance.located(
                        Optional.of(lookingAtEntityPredicate(
                                registryEntryLookupEntity,
                                registryValue(EntityRegistry.GHOST_ENTITY),
                                Optional.of("adventure")
                        ).build())))
                .save(consumer, Stargazer.MOD_ID + ":adventure");

        requireListedBiomesVisited(Advancement.Builder.advancement(), wrapperLookup, BiomeReg.MoonList)
                .parent(portal)
                .display(
                        MoonBlocks.MOON_LOG.get(), // The display icon
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
                .addCriterion("craft", registryValue(Criterias.FORGE_CRAFT).createCriterion(new ForgeCraft.Conditions(Optional.empty(), Optional.of(itemPredicate(registryEntryLookupItem, WishingStars.WISHING_STAR.get())))))
                .display(
                        WishingStars.WISHING_STAR.get(),
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
                .addCriterion("milk", registryValue(Criterias.STAR_MODIFIER).createCriterion(new StarModifier.Conditions(Optional.empty(), Optional.of(itemPredicate(registryEntryLookupItem, Items.MILK_BUCKET)))))
                .display(
                        WishingStars.WISHING_STAR.get(),
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
                        ModItems.STARDUST.get(), // The display icon
                        Component.literal("I took a part to home"), // The title
                        Component.literal("Craft cosmic block"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion("craft", registryValue(Criterias.FORGE_CRAFT).createCriterion(new ForgeCraft.Conditions(Optional.empty(), Optional.of(itemTagPredicate(registryEntryLookupItem, CustomTags.COSMIC)))))
                .save(consumer, Stargazer.MOD_ID + ":home");
        AdvancementHolder Red = Advancement.Builder.advancement()
                .parent(portal)
                .display(
                        RedOrbBlocks.RED_ROCK.get(), // The display icon
                        Component.literal("Red Planet"), // The title
                        Component.literal("What next Green™ Planet"), // The description
                        null,
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .addCriterion(
                        "visit_red_planet",
                        PlayerTrigger.TriggerInstance.located(
                                LocationPredicate.Builder.inBiome(registryEntryLookupBiome.getOrThrow(BiomeReg.REDNESS))
                        )
                )
//                .addCriterion("craft", Criterias.MOON_WELD.get().createCriterion(new MoonWeld.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryEntryLookupItem, RedOrbBlocks.RED_ORB_PLATFORM.get().asItem()).build()))))
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

    private static <T> T registryValue(T value) {
        return value;
    }

    private static <T> T registryValue(RegistrySupplier<T> value) {
        return value.get();
    }

    private static ItemPredicate itemPredicate(HolderGetter<Item> items, Item item) {
        //? if >= 26.2 {
        return ItemPredicate.Builder.item().of(items, item).build();
        //?} else {
        /*return ItemPredicate.Builder.item().of(item).build();
        *///?}
    }

    private static ItemPredicate itemTagPredicate(HolderGetter<Item> items, TagKey<Item> tag) {
        //? if >= 26.2 {
        return ItemPredicate.Builder.item().of(items, tag).build();
        //?} else {
        /*return ItemPredicate.Builder.item().of(tag).build();
        *///?}
    }

    private static EntityPredicate.Builder entityPredicate(
            HolderGetter<EntityType<?>> entities, EntityType<?> entityType) {
        //? if >= 26.2 {
        return EntityPredicate.Builder.entity().of(entities, entityType);
        //?} else {
        /*return EntityPredicate.Builder.entity().of(entityType);
        *///?}
    }

    private static EntityPredicate.Builder lookingAtEntityPredicate(
            HolderGetter<EntityType<?>> entities, EntityType<?> entityType, Optional<String> tag) {
        EntityPredicate.Builder lookedAt = entityPredicate(entities, entityType);
        tag.ifPresent(value -> lookedAt.nbt(new NbtPredicate(Util.make(
                new CompoundTag(), nbt -> nbt.putString("tag", value)))));
        PlayerPredicate.Builder player = PlayerPredicate.Builder.player().setLookingAt(lookedAt);
        //? if >= 26.2 {
        return EntityPredicate.Builder.entity().player(player.build());
        //?} else {
        /*return EntityPredicate.Builder.entity().subPredicate(player.build());
        *///?}
    }
}
//?}
