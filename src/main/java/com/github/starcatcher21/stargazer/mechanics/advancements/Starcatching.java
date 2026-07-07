package com.github.starcatcher21.stargazer.mechanics.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.CriterionValidator;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

public class Starcatching extends SimpleCriterionTrigger<Starcatching.Conditions> {
    @Override
    public Codec<Conditions> codec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, Item item) {
        trigger(player, conditions -> conditions.requirementsMet(item));
    }

    // The 'Conditions' record defines what the JSON file looks like
    public record Conditions(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> output) implements CriterionTriggerInstance, SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                        ItemPredicate.CODEC.optionalFieldOf("output").forGetter(Conditions::output)
                ).apply(instance, Conditions::new)
        );

        public boolean requirementsMet(Item item) {
            if (output.isEmpty()) return true;
            if (output.get().items().isEmpty()) return true;
            return output.get().items().get().contains(item.builtInRegistryHolder());
        }

        @Override
        public void validate(CriterionValidator validator) {

        }
    }
}
