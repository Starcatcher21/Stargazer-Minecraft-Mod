package com.github.starcatcher21.stargazer.item.ConsumeEffects;

import com.github.starcatcher21.stargazer.item.ConsumeEffectsRegistry;
import com.github.starcatcher21.stargazer.mechanics.advancements.Criterias;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObject;
import com.github.starcatcher21.stargazer.mechanics.star.FallingObjectsList;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Collections;

public record StarGazeConsume() implements ConsumeEffect {
        public static final MapCodec<StarGazeConsume> CODEC = MapCodec.unit(new StarGazeConsume());
        public static final PacketCodec<RegistryByteBuf, StarGazeConsume> PACKET_CODEC = PacketCodecs.registryCodec(CODEC.codec());

        public StarGazeConsume() {
        }

        @Override
        public ConsumeEffect.Type<StarGazeConsume> getType() {
            return ConsumeEffectsRegistry.STARGAZE;
        }

        @Override
        public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
            for (FallingObjectsList list : FallingObjectsList.list2) {
                if (user.getEntityWorld().getRegistryKey().equals(list.world)) {
                    Collections.shuffle(list.weightedList);
                    FallingObject star = list.weightedList.get(user.getRandom().nextInt(list.weightedList.size()));
                    if (user instanceof ServerPlayerEntity spe) {
                        Criterias.starcatching.trigger(spe, star.item.value());
                    }
                    if (user instanceof PlayerEntity pe) {
                        star.spawn(MinecraftClient.getInstance(), pe, user.getEntityWorld());
                    }
                }
            }
            return true;
        }
}
