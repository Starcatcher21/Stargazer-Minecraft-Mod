package com.github.starcatcher21.stargazer.particle;

import com.github.starcatcher21.stargazer.Stargazer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Supplier;

public class Particles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Stargazer.MOD_ID, Registries.PARTICLE_TYPE);

    // Simple particles
    public static final RegistrySupplier<SimpleParticleType> YELLOW_STAR =
            PARTICLES.register("yellow_star", (Supplier<SimpleParticleType>) () -> new SimpleParticleType(false) {});
    public static final RegistrySupplier<SimpleParticleType> RED_STAR =
            PARTICLES.register("red_star", (Supplier<SimpleParticleType>) () -> new SimpleParticleType(false) {});
    public static final RegistrySupplier<SimpleParticleType> BLUE_STAR =
            PARTICLES.register("blue_star", (Supplier<SimpleParticleType>) () -> new SimpleParticleType(false) {});
    public static final RegistrySupplier<SimpleParticleType> PURPLE_STAR =
            PARTICLES.register("purple_star", (Supplier<SimpleParticleType>) () -> new SimpleParticleType(false) {});
    public static final RegistrySupplier<SimpleParticleType> STAR =
            PARTICLES.register("star", (Supplier<SimpleParticleType>) () -> new SimpleParticleType(false) {});

    // Complex particle (Tinted Star) implemented by extending ParticleType inline
    public static final RegistrySupplier<ParticleType<ColorParticleOption>> TINTED_STAR =
            PARTICLES.register("tinted_star", () -> new ParticleType<ColorParticleOption>(false) {
                @Override
                public MapCodec<ColorParticleOption> codec() {
                    return ColorParticleOption.codec(this);
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOption> streamCodec() {
                    return ColorParticleOption.streamCodec(this);
                }
            });

    public static void init() {
        PARTICLES.register();
    }
}