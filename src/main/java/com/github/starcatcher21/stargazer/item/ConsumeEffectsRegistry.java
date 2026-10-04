//? if >= 26.2 {
/*package com.github.starcatcher21.stargazer.item;

import com.github.starcatcher21.stargazer.Stargazer;
import com.github.starcatcher21.stargazer.item.ConsumeEffects.StarGazeConsume;
import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class ConsumeEffectsRegistry {
    public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECT_TYPES =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.CONSUME_EFFECT_TYPE);

    public static final RegistrySupplier<ConsumeEffect.Type<StarGazeConsume>> STARGAZE = register(
            "stargaze", StarGazeConsume.CODEC, StarGazeConsume.PACKET_CODEC
    );

    private static <T extends ConsumeEffect> RegistrySupplier<ConsumeEffect.Type<T>> register(String id, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> packetCodec) {
        return CONSUME_EFFECT_TYPES.register(id, () -> new ConsumeEffect.Type<>(codec, packetCodec));
    }

    public static void init() {
        CONSUME_EFFECT_TYPES.register();
    }
}
*///? }
