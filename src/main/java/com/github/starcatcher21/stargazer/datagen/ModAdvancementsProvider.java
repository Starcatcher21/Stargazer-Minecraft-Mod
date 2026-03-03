package com.github.starcatcher21.stargazer.datagen;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.block.ModBlock;
import com.github.starcatcher21.stargazer.block.clases.moon.star_trap.StarTrapEntity;
import com.github.starcatcher21.stargazer.block.register.MoonBlocks;
import com.github.starcatcher21.stargazer.mechanics.DamageTypeRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.CosmicPortal;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.advancements.StarTrap;
import com.github.starcatcher21.stargazer.mechanics.advancements.Starcatching;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Items;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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
                .criterion("watch_stars", Criterias.starcatching.create(new Starcatching.Conditions(Optional.empty())))
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
    }
}
