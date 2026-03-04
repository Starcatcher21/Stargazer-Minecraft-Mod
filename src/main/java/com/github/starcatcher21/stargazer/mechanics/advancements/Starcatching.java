package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class Starcatching extends AbstractCriterion<Starcatching.Conditions> {
    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, Item item) {
        trigger(player, conditions -> conditions.requirementsMet(item));
    }

    // The 'Conditions' record defines what the JSON file looks like
    public record Conditions(Optional<LootContextPredicate> player, Optional<ItemPredicate> output) implements CriterionConditions, AbstractCriterion.Conditions {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                        ItemPredicate.CODEC.optionalFieldOf("output").forGetter(Conditions::output)
                ).apply(instance, Conditions::new)
        );

        public boolean requirementsMet(Item item) {
            if (output.isEmpty()) return true;
            if (output.get().items().isEmpty()) return true;
            return output.get().items().get().contains(item.getRegistryEntry());
        }

        @Override
        public void validate(LootContextPredicateValidator validator) {

        }
    }
}
