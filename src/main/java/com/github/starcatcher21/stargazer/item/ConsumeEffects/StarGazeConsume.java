package com.github.starcatcher21.stargazer.item.ConsumeEffects;

import com.github.starcatcher21.stargazer.item.ConsumeEffectsRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.mojang.serialization.MapCodec;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record StarGazeConsume() implements ConsumeEffect {
        public static final MapCodec<StarGazeConsume> CODEC = MapCodec.unit(new StarGazeConsume());
        public static final StreamCodec<RegistryFriendlyByteBuf, StarGazeConsume> PACKET_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC.codec());

        public StarGazeConsume() {
        }

        @Override
        public ConsumeEffect.Type<StarGazeConsume> getType() {
            return ConsumeEffectsRegistry.STARGAZE;
        }

        @Override
        public boolean apply(Level world, ItemStack stack, LivingEntity user) {
            for (FallingObjectsList list : FallingObjectsList.list2) {
                if (user.level().dimension().equals(list.world)) {
                    Collections.shuffle(list.weightedList);
                    FallingObject star = list.weightedList.get(user.getRandom().nextInt(list.weightedList.size()));
                    if (user instanceof ServerPlayer spe) {
                        Criterias.starcatching.trigger(spe, star.item.value());
                    }
                    if (user instanceof Player pe) {
                        star.spawn(Minecraft.getInstance(), pe, user.level());
                    }
                }
            }
            return true;
        }
}
